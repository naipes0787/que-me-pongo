package quemepongo.api.clientes;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import quemepongo.api.dto.OpenWeatherResponseDTO;
import quemepongo.exceptions.ApiDeClimaException;
import quemepongo.exceptions.ObjectMapperException;
import quemepongo.model.Localizacion;
import quemepongo.model.Temperatura;

import java.io.IOException;

public class ClienteOpenWeather implements ApiDeClima {

    private Cliente cliente;
    private ObjectMapper mapper;
    //TODO agregar log y que el host y la key vengan de un .properties
    private static final String host = "http://api.openweathermap.org/data/2.5";
    private static final String key = "d0f7630cb095c3adf9622fdb01cd87c9";
    private static final String PRONOSTICO_ACTUAL = "/weather?id=";

    public ClienteOpenWeather() {
        this.cliente = new Cliente(host);
        this.mapper =  new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SnakeCaseStrategy.SNAKE_CASE);
    }

    private OpenWeatherResponseDTO obtenerTemperaturaActual(String locationKey){
        try {
            String respuesta = cliente.getAsString(PRONOSTICO_ACTUAL + locationKey + parametrosGenerales());
            return mapper.readValue(respuesta, OpenWeatherResponseDTO.class);
        }catch (IOException|ObjectMapperException exc){
            throw new ApiDeClimaException("Pronostico Actual");
        }
    }

    private String parametrosGenerales(){
        //TODO hacerlo parametrizable y usar Query
        return "&appid="+key+"&units=metric&lang=es";
    }

    @Override
    public Temperatura obtenerTemperaturaActual(Localizacion localizacion) {
        String locationKey = getLocationKey(localizacion);
        Temperatura temperatura = new Temperatura(obtenerTemperaturaActual(locationKey)
        		.getMain()
        		.getTemp());
        return temperatura;
    }

    private String getLocationKey(Localizacion localizacion) {
        //TODO la idea es tener un mapa, propio de OpenWeather, cuya key sea una localizacion y el valor sea el id
        return "3433955";
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}
