package quemepongo.api;

import com.fasterxml.jackson.databind.ObjectMapper;

import quemepongo.api.clientes.ApiDeClima;
import quemepongo.api.clientes.Cliente;
import quemepongo.model.Localizacion;
import quemepongo.model.Temperatura;

public class ClienteTest extends Cliente implements ApiDeClima{

    private static final String host = "";

    public ClienteTest() {
        super(host);
    }

    @Override
    public Temperatura obtenerTemperaturaActual(Localizacion localizacion) {
    	Temperatura temperatura = new Temperatura();
    	temperatura.setTemperatura(Double.valueOf("10"));
        return temperatura;
    }

    @Override
    public ObjectMapper buildMapper() {
        return null;
    }
}