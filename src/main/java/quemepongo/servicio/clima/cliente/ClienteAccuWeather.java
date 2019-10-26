package quemepongo.servicio.clima.cliente;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import quemepongo.dominio.FactorClimatico;
import quemepongo.dominio.Temperatura;
import quemepongo.dominio.evento.Localizacion;
import quemepongo.excepcion.ApiDeClimaException;
import quemepongo.excepcion.ObjectMapperException;
import quemepongo.servicio.Cliente;
import quemepongo.servicio.clima.ApiDeClima;
import quemepongo.servicio.clima.dto.AccuweatherResponseDTO;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ClienteAccuWeather implements ApiDeClima {

    private Cliente cliente;
    private ObjectMapper mapper;
    //TODO agregar log y que el host y la key vengan de un .properties
    private static final String host = "http://dataservice.accuweather.com";
    private static final String key = "4zxMMc9pFj6f6pOdoQ2TirQCUwLTmG9S";
    private static final String PRONOSTICO_ACTUAL = "/currentconditions/v1/";

    public ClienteAccuWeather() {
        cliente = new Cliente(host);
        mapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SnakeCaseStrategy.UPPER_CAMEL_CASE);
    }

    private List<AccuweatherResponseDTO> obtenerTemperaturaActual(String locationKey){
        try {
            String respuesta = cliente.getAsString(PRONOSTICO_ACTUAL + locationKey + parametrosGenerales());
            return mapper.readValue(respuesta, new TypeReference<List<AccuweatherResponseDTO>>(){});
        }catch (IOException|ObjectMapperException exc){
            throw new ApiDeClimaException("Pronostico Actual");
        }
    }

    private String parametrosGenerales(){
        //TODO hacerlo parametrizable y usar Query
        return "?apikey="+key+"&language=es-AR&details=true&metric=true";
    }

    @Override
    public Temperatura obtenerTemperaturaActual(Localizacion localizacion) {
        String locationKey = getLocationKey(localizacion);
        Double temperaturaObtenida = obtenerTemperaturaActual(locationKey)
                .get(0).getTemperature().getMetric().getValue();
        if (temperaturaObtenida == null) throw new ApiDeClimaException("Pronostico Actual");
        return new Temperatura(temperaturaObtenida);
    }

    @Override
    public List<FactorClimatico> obtenerAlertasActuales(Localizacion localizacion) {
        return Arrays.asList();
    }

    private String getLocationKey(Localizacion localizacion) {
        //TODO la idea es tener un mapa, propio de AccuWeather,
        // cuya key sea una localizacion y el valor sea el id
        return "7894";
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}