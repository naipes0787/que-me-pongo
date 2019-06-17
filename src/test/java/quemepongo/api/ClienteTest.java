package quemepongo.api;

import org.junit.Assert;
import org.junit.Test;

import com.fasterxml.jackson.databind.ObjectMapper;

import quemepongo.api.clientes.ApiDeClima;
import quemepongo.api.clientes.Cliente;
import quemepongo.model.Localizacion;
import quemepongo.model.Temperatura;

/**
 * Cliente utilizado para simular una API de Clima y realizar así los tests
 */
public class ClienteTest extends Cliente implements ApiDeClima{

    private static final String host = "";
    private static final Double TEMPERATURA_FIJA = 10D;

    public ClienteTest() {
        super(host);
    }

    @Override
    public Temperatura obtenerTemperaturaActual(Localizacion localizacion) {
    	Temperatura temperatura = new Temperatura();
    	temperatura.setTemperatura(TEMPERATURA_FIJA);
        return temperatura;
    }

    @Override
    public ObjectMapper buildMapper() {
        return null;
    }
    
    @Test
    public void devuelvaTemperaturaCorrecta(){
    	Temperatura temperatura = this.obtenerTemperaturaActual(Localizacion.CABA);
        Assert.assertEquals(temperatura.getTemperatura(), TEMPERATURA_FIJA);
    }
}