package quemepongo.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import quemepongo.api.clientes.Cliente;
import quemepongo.api.clientes.ClienteOpenWeather;
import quemepongo.exceptions.ApiDeClimaException;
import quemepongo.exceptions.ClienteHttpException;
import quemepongo.model.Temperatura;
import quemepongo.model.evento.Localizacion;

public class ClienteOpenWeatherTest {

    @Mock
    private Cliente clienteTest;
    private ClienteOpenWeather clienteOpenWeather;
    private static final Localizacion LOCALIZACION = Localizacion.CABA;
    private static final String PATH = "/weather?id=3433955&appid=d0f7630cb095c3adf9622fdb01cd87c9&units=metric&lang=es";
    private String TEMPERATURA_CORRECTA = "10";
    private String RESPUESTA_CORRECTA = "{\"main\":{\"temp\":"+TEMPERATURA_CORRECTA+"}}";
    private String RESPUESTA_MAL_FORMADA = "{\"main\":{}";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        clienteOpenWeather = new ClienteOpenWeather();
        clienteOpenWeather.setCliente(clienteTest);
    }

    @Test
    public void retorneRespuestaCorrecta(){
        Mockito.when(clienteTest.getAsString(PATH)).thenReturn(RESPUESTA_CORRECTA);

        Temperatura temperatura = clienteOpenWeather.obtenerTemperaturaActual(LOCALIZACION);

        Assert.assertEquals(temperatura.getTemperatura(), Double.parseDouble(TEMPERATURA_CORRECTA), 0.0);
        Mockito.verify(clienteTest).getAsString(PATH);
        Mockito.verifyNoMoreInteractions(clienteTest);
    }

    @Test(expected = ApiDeClimaException.class)
    public void fallaPorRespuestaMalFormada(){
        Mockito.when(clienteTest.getAsString(PATH)).thenReturn(RESPUESTA_MAL_FORMADA);

        clienteOpenWeather.obtenerTemperaturaActual(LOCALIZACION);
    }

    @Test(expected = ClienteHttpException.class)
    public void fallaPorFallaDelLadoCliente(){
        Mockito.when(clienteTest.getAsString(PATH)).thenThrow(new ClienteHttpException(""));

        clienteOpenWeather.obtenerTemperaturaActual(LOCALIZACION);
    }


}
