package quemepongo.api;

import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import quemepongo.api.clientes.Cliente;
import quemepongo.api.clientes.ClienteAccuWeather;
import quemepongo.exceptions.ApiDeClimaException;
import quemepongo.exceptions.ClienteHttpException;
import quemepongo.model.Alerta;
import quemepongo.model.Temperatura;
import quemepongo.model.evento.Localizacion;

import java.util.Arrays;
import java.util.List;

import static quemepongo.model.Alerta.VIENTO;

public class ClienteAccuWeatherTest {

    @Mock
    private Cliente clienteTest;
    private ClienteAccuWeather clienteAccuWeather;
    private static final Localizacion LOCALIZACION = Localizacion.CABA;
    private static final String PATH = "/currentconditions/v1/7894?apikey=4zxMMc9pFj6f6pOdoQ2TirQCUwLTmG9S&language=es-AR&details=true&metric=true";
    private String TEMPERATURA_CORRECTA = "10";
    private String RESPUESTA_BIEN_FORMADA = "[{\"Temperature\":{\"Metric\":{\"Value\":"+TEMPERATURA_CORRECTA+"}}}]";
    private String RESPUESTA_MAL_FORMADA = "[{\"Temperature\":{\"Metric\":{}}}]";
    private String SIN_LLUVIA = "false";
    private String CON_LLUVIA = "true";
    private Double SIN_VIENTO = 1.0;
    private Double CON_VIENTO = 6.0;
    private Integer CON_SOL = 4;
    private Integer SIN_SOL = 1;
    private String RESPUESTA_CON_ALERTAS_A_DEFINIR = "[{\"HasPrecipitation\":%s,\"Wind\":{\"Speed\":{\"Metric\":{\"Value\":%f}}},\"UVIndex\":%d}]";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        clienteAccuWeather = new ClienteAccuWeather();
        clienteAccuWeather.setCliente(clienteTest);
    }

    @Test
    public void retorneRespuestaCorrecta(){
        Mockito.when(clienteTest.getAsString(PATH)).thenReturn(RESPUESTA_BIEN_FORMADA);

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

    @Test
    public void retorneSoloAlertaDeSol(){
        String RESPUESTA_SOLO_CON_ALERTA_DE_SOL= String.format(RESPUESTA_CON_ALERTAS_A_DEFINIR, SIN_LLUVIA, SIN_VIENTO, CON_SOL);
        Mockito.when(clienteTest.getAsString(PATH)).thenReturn(RESPUESTA_SOLO_CON_ALERTA_DE_SOL);

        List<Alerta> alertasObtenidas = clienteAccuWeather.obtenerAlertasActuales(LOCALIZACION);

        Assert.assertEquals(1, alertasObtenidas.size());
        Assert.assertEquals(Alerta.SOL, alertasObtenidas.get(0));
        Mockito.verify(clienteTest).getAsString(PATH);
        Mockito.verifyNoMoreInteractions(clienteTest);
    }

    @Test
    public void retorneSoloAlertaDeViento(){
        String RESPUESTA_SOLO_CON_ALERTA_DE_VIENTO= String.format(RESPUESTA_CON_ALERTAS_A_DEFINIR, SIN_LLUVIA, CON_VIENTO, SIN_SOL);
        Mockito.when(clienteTest.getAsString(PATH)).thenReturn(RESPUESTA_SOLO_CON_ALERTA_DE_VIENTO);

        List<Alerta> alertasObtenidas = clienteAccuWeather.obtenerAlertasActuales(LOCALIZACION);

        Assert.assertEquals(1, alertasObtenidas.size());
        Assert.assertEquals(VIENTO, alertasObtenidas.get(0));
        Mockito.verify(clienteTest).getAsString(PATH);
        Mockito.verifyNoMoreInteractions(clienteTest);
    }

    @Test
    public void retorneSoloAlertaDeLluvia(){
        String RESPUESTA_SOLO_CON_ALERTA_DE_LLUVIA= String.format(RESPUESTA_CON_ALERTAS_A_DEFINIR, CON_LLUVIA, SIN_VIENTO, SIN_SOL);
        Mockito.when(clienteTest.getAsString(PATH)).thenReturn(RESPUESTA_SOLO_CON_ALERTA_DE_LLUVIA);

        List<Alerta> alertasObtenidas = clienteAccuWeather.obtenerAlertasActuales(LOCALIZACION);

        Assert.assertEquals(1, alertasObtenidas.size());
        Assert.assertEquals(Alerta.LLUVIA, alertasObtenidas.get(0));
        Mockito.verify(clienteTest).getAsString(PATH);
        Mockito.verifyNoMoreInteractions(clienteTest);
    }

    @Test
    public void retorneLasTresAlertas(){
        String RESPUESTA_CON_TODAS_LAS_ALERTAS= String.format(RESPUESTA_CON_ALERTAS_A_DEFINIR, CON_LLUVIA, CON_VIENTO, CON_SOL);
        List<Alerta> TODAS_LAS_ALERTAS = Arrays.asList(Alerta.LLUVIA, Alerta.SOL, Alerta.VIENTO);
        Mockito.when(clienteTest.getAsString(PATH)).thenReturn(RESPUESTA_CON_TODAS_LAS_ALERTAS);

        List<Alerta> alertasObtenidas = clienteAccuWeather.obtenerAlertasActuales(LOCALIZACION);

        Assert.assertEquals(3, alertasObtenidas.size());
        Assert.assertTrue(alertasObtenidas.containsAll(TODAS_LAS_ALERTAS));
        Mockito.verify(clienteTest).getAsString(PATH);
        Mockito.verifyNoMoreInteractions(clienteTest);
    }

    @Test
    public void noRetorneAlertas(){
        String RESPUESTA_SIN_ALERTAS= String.format(RESPUESTA_CON_ALERTAS_A_DEFINIR, SIN_LLUVIA, SIN_VIENTO, SIN_SOL);
        Mockito.when(clienteTest.getAsString(PATH)).thenReturn(RESPUESTA_SIN_ALERTAS);

        List<Alerta> alertasObtenidas = clienteAccuWeather.obtenerAlertasActuales(LOCALIZACION);

        Assert.assertEquals(0, alertasObtenidas.size());
        Mockito.verify(clienteTest).getAsString(PATH);
        Mockito.verifyNoMoreInteractions(clienteTest);
    }

}
