package quemepongo.api;

import org.junit.Assert;
import org.junit.Test;
import quemepongo.api.clientes.ApiDeClima;
import quemepongo.model.Localizacion;
import quemepongo.model.Temperatura;

/**
 * Cliente utilizado para simular una API de Clima y realizar así los tests
 */
public class ClienteTest implements ApiDeClima{

    private static final Double TEMPERATURA_FIJA = 10D;

    @Override
    public Temperatura obtenerTemperaturaActual(Localizacion localizacion) {
    	return new Temperatura(TEMPERATURA_FIJA);
    }

    @Test
    public void devuelvaTemperaturaCorrecta(){
    	Temperatura temperatura = this.obtenerTemperaturaActual(Localizacion.CABA);
        Assert.assertEquals(temperatura.getTemperatura(), TEMPERATURA_FIJA);
    }
}