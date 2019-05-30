package quemepongo.api.clientes;

import org.apache.http.HttpResponse;
import quemepongo.api.dto.OpenWeatherResponseDTO;
import quemepongo.exceptions.AccuWeatherException;
import quemepongo.exceptions.ObjectMapperException;
import quemepongo.model.Localizacion;

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
            throw new AccuWeatherException("Pronostico Actual");
        }
        try{
            return mapper.readValue(respuesta.getEntity().getContent(), OpenWeatherResponseDTO.class);
        }catch (IOException exc){
            throw new ObjectMapperException();
        }
    }

    private String parametrosGenerales(){
        //TODO hacerlo parametrizable y usar Query
        return "&appid="+key+"=&units=metric&lang=es";
    }

    @Override
    public Double obtenerTemperaturaActual(Localizacion localizacion) {
        String locationKey = getLocationKey(localizacion);
        return obtenerTemperaturaActual(locationKey).getMain().getTemp();
    }

    private String getLocationKey(Localizacion localizacion) {
        //TODO la idea es tener un mapa, propio de OpenWeather, cuya key sea una localizacion y el valor sea el id
        return "3433955";
    }
}
