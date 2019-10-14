package quemepongo.servicio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import quemepongo.servicio.clima.cliente.ClienteAccuWeather;
import quemepongo.excepcion.ApiDeClimaException;
import quemepongo.excepcion.ClienteHttpException;
import quemepongo.dominio.Temperatura;
import quemepongo.dominio.evento.Localizacion;

public class ClienteAccuWeatherTest {

    @Mock
    private Cliente clienteTest;
    private ClienteAccuWeather clienteAccuWeather;
    private static final Localizacion LOCALIZACION = Localizacion.CABA;
    private static final String PATH = "/currentconditions/v1/7894?apikey=4zxMMc9pFj6f6pOdoQ2TirQCUwLTmG9S&language=es-AR&details=true&metric=true";
    private String TEMPERATURA_CORRECTA = "10";
    private String RESPUESTA_CORRECTA = "[{\"Temperature\":{\"Metric\":{\"Value\":"+TEMPERATURA_CORRECTA+"}}}]";
    private String RESPUESTA_MAL_FORMADA = "[{\"Temperature\":{\"Metric\":{}}}]";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        clienteAccuWeather = new ClienteAccuWeather();
        clienteAccuWeather.setCliente(clienteTest);
    }

    @Test
    public void retorneRespuestaCorrecta(){
        Mockito.when(clienteTest.getAsString(PATH)).thenReturn(RESPUESTA_CORRECTA);

        Temperatura temperatura = clienteAccuWeather.obtenerTemperaturaActual(LOCALIZACION);

        Assert.assertEquals(temperatura.getTemperatura(), Double.parseDouble(TEMPERATURA_CORRECTA), 0.0);
        Mockito.verify(clienteTest).getAsString(PATH);
        Mockito.verifyNoMoreInteractions(clienteTest);
    }

    @Test(expected = ApiDeClimaException.class)
    public void fallaPorRespuestaMalFormada(){
        Mockito.when(clienteTest.getAsString(PATH)).thenReturn(RESPUESTA_MAL_FORMADA);

        clienteAccuWeather.obtenerTemperaturaActual(LOCALIZACION);
    }

    @Test(expected = ClienteHttpException.class)
    public void fallaPorFallaDelLadoCliente(){
        Mockito.when(clienteTest.getAsString(PATH)).thenThrow(new ClienteHttpException(""));

        clienteAccuWeather.obtenerTemperaturaActual(LOCALIZACION);
    }

}
