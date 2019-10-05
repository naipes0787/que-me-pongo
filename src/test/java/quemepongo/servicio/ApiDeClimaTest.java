package quemepongo.servicio;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import quemepongo.servicio.clima.ApiDeClima;
import quemepongo.excepcion.ApiDeClimaException;
import quemepongo.dominio.Temperatura;
import quemepongo.dominio.evento.Localizacion;
import quemepongo.servicio.clima.SelectorDeProveedorDeClima;

public class ApiDeClimaTest {

    @Mock
    private ApiDeClima apiDeClima;
    @Mock
    private ApiDeClima apiDeClimaSecundario;

    private static final Localizacion LOCALIZACION = Localizacion.CABA;
    private static final String MENSAJE_EXCEPTION_TEMPERATURA_ACTUAL = "Pronostico Actual";
    private static Temperatura temperatura;
    private static final Double VALOR_TEMPERATURA = 10D;
    private static Temperatura temperaturaSecundaria;
    private static final Double VALOR_TEMPERATURA_SECUNDARIA = 20D;

    private SelectorDeProveedorDeClima selector = SelectorDeProveedorDeClima.getInstancia();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        selector.setProovedorDeClima(apiDeClima);
        temperatura = new Temperatura(VALOR_TEMPERATURA);
        temperaturaSecundaria = new Temperatura(VALOR_TEMPERATURA_SECUNDARIA);
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
    public void siLaAPIDevuelveError_SeArrojaExcepcion(){
        Mockito.when(apiDeClima.obtenerTemperaturaActual(LOCALIZACION))
                .thenThrow(new ApiDeClimaException(MENSAJE_EXCEPTION_TEMPERATURA_ACTUAL));

        selector.getProovedorDeClima().obtenerTemperaturaActual(LOCALIZACION);
    }

    @Test
    public void siCambiaLaAPIDeClima_SePuedeObtenerCorrectamenteLaTemperatura(){
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
