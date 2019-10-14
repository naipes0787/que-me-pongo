package quemepongo.servicio;

import org.junit.Assert;
import org.junit.Test;
import quemepongo.servicio.clima.ApiDeClima;
import quemepongo.dominio.FactorClimatico;
import quemepongo.dominio.Temperatura;
import quemepongo.dominio.evento.Localizacion;

import java.util.Arrays;
import java.util.List;

/**
 * Cliente utilizado para simular una API de Clima y realizar así los tests
 */
public class ClienteTest implements ApiDeClima{

    private static final Double TEMPERATURA_FIJA = 10D;

    @Override
    public Temperatura obtenerTemperaturaActual(Localizacion localizacion) {
    	return new Temperatura(TEMPERATURA_FIJA);
    }

    @Override
    public List<FactorClimatico> obtenerAlertasActuales(Localizacion localizacion) {
        return Arrays.asList();
    }

    @Test
    public void devuelvaTemperaturaCorrecta(){
    	Temperatura temperatura = this.obtenerTemperaturaActual(Localizacion.CABA);
        Assert.assertEquals(temperatura.getTemperatura(), TEMPERATURA_FIJA);
    }
}