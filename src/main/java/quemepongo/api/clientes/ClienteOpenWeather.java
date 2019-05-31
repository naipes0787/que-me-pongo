package quemepongo.api.clientes;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import org.apache.http.HttpResponse;
import quemepongo.api.dto.OpenWeatherResponseDTO;
import quemepongo.exceptions.ApiDeClimaException;
import quemepongo.exceptions.ObjectMapperException;
import quemepongo.model.Localizacion;
import quemepongo.model.Temperatura;

import java.io.IOException;

public class ClienteOpenWeather extends Cliente implements ApiDeClima {

    //TODO agregar log y que el host y la key vengan de un .properties
    private static final String host = "http://api.openweathermap.org/data/2.5";
    private static final String key = "d0f7630cb095c3adf9622fdb01cd87c9";
    private static final String PRONOSTICO_ACTUAL = "/weather?id=";

    public ClienteOpenWeather() {
        super(host);
    }

    private OpenWeatherResponseDTO obtenerTemperaturaActual(String locationKey){
        HttpResponse respuesta = get(PRONOSTICO_ACTUAL + locationKey + parametrosGenerales());
        if(terminoEnError(respuesta)){
            throw new ApiDeClimaException("Pronostico Actual");
        }
        try{
            return mapper.readValue(respuesta.getEntity().getContent(), OpenWeatherResponseDTO.class);
        }catch (IOException exc){
            throw new ObjectMapperException();
        }
    }

    private String parametrosGenerales(){
        //TODO hacerlo parametrizable y usar Query
        return "&appid="+key+"&units=metric&lang=es";
    }

    @Override
    public Temperatura obtenerTemperaturaActual(Localizacion localizacion) {
        String locationKey = getLocationKey(localizacion);
        Temperatura temperatura = new Temperatura();
        temperatura.setTemperatura(obtenerTemperaturaActual(locationKey).getMain().getTemp());
        return temperatura;
    }

    private String getLocationKey(Localizacion localizacion) {
        //TODO la idea es tener un mapa, propio de OpenWeather, cuya key sea una localizacion y el valor sea el id
        return "3433955";
    }

    @Override
    public ObjectMapper buildMapper() {
        return new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SnakeCaseStrategy.SNAKE_CASE);
    }
}
