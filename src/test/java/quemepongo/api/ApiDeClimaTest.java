package quemepongo.api;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import quemepongo.api.clientes.ApiDeClima;
import quemepongo.api.servicio.SelectorDeProveedorDeClima;
import quemepongo.exceptions.ApiDeClimaException;
import quemepongo.model.Localizacion;
import quemepongo.model.Temperatura;

public class ApiDeClimaTest {

    @Mock
    private ApiDeClima apiDeClima;
    @Mock
    private ApiDeClima apiDeClimaSecundario;
    //@InjectMocks
    private SelectorDeProveedorDeClima selector;

    private static final Localizacion LOCALIZACION = Localizacion.CABA;
    private static final String MENSAJE_EXCEPTION_TEMPERATURA_ACTUAL = "Pronostico Actual";
    private static Temperatura temperatura = new Temperatura();
    private static final Double VALOR_TEMPERATURA = 10.0;
    private static Temperatura temperaturaSecundaria = new Temperatura();
    private static final Double VALOR_TEMPERATURA_SECUNDARIA = 20.0;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        selector = new SelectorDeProveedorDeClima(apiDeClima);
        temperatura.setTemperatura(VALOR_TEMPERATURA);
        temperaturaSecundaria.setTemperatura(VALOR_TEMPERATURA_SECUNDARIA);
    }

    @Test
    public void devuelvaTemperaturaCorrecta(){
        Mockito.when(apiDeClima.obtenerTemperaturaActual(LOCALIZACION)).thenReturn(temperatura);

        Temperatura temperaturaObtenida = selector.getProovedorDeClima().obtenerTemperaturaActual(LOCALIZACION);

        Assert.assertEquals(temperaturaObtenida.getTemperatura(), VALOR_TEMPERATURA);
        Mockito.verify(apiDeClima).obtenerTemperaturaActual(LOCALIZACION);
        Mockito.verifyNoMoreInteractions(apiDeClima);
    }

    @Test(expected = ApiDeClimaException.class)
    public void noRetorneTemperatura(){
        Mockito.when(apiDeClima.obtenerTemperaturaActual(LOCALIZACION))
                .thenThrow(new ApiDeClimaException(MENSAJE_EXCEPTION_TEMPERATURA_ACTUAL));

        selector.getProovedorDeClima().obtenerTemperaturaActual(LOCALIZACION);
    }

    @Test
    public void cambiarApisDeClima(){
        Mockito.when(apiDeClima.obtenerTemperaturaActual(LOCALIZACION)).thenReturn(temperatura);
        Mockito.when(apiDeClimaSecundario.obtenerTemperaturaActual(LOCALIZACION)).thenReturn(temperaturaSecundaria);

        Temperatura temperaturaObtenida = selector.getProovedorDeClima().obtenerTemperaturaActual(LOCALIZACION);

        Assert.assertEquals(temperaturaObtenida.getTemperatura(), VALOR_TEMPERATURA);
        Mockito.verify(apiDeClima).obtenerTemperaturaActual(LOCALIZACION);

        selector.setProovedorDeClima(apiDeClimaSecundario);
        Temperatura segundaTemperaturaObtenida = selector.getProovedorDeClima().obtenerTemperaturaActual(LOCALIZACION);

        Assert.assertEquals(segundaTemperaturaObtenida.getTemperatura(), VALOR_TEMPERATURA_SECUNDARIA);
        Mockito.verify(apiDeClimaSecundario).obtenerTemperaturaActual(LOCALIZACION);

        Mockito.verifyNoMoreInteractions(apiDeClima, apiDeClimaSecundario);
    }
}
