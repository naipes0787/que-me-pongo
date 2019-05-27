package quemepongo.api.clientes;

import org.apache.http.HttpResponse;
import quemepongo.api.dto.OpenWeatherResponseDTO;
import quemepongo.exceptions.AccuWeatherException;
import quemepongo.exceptions.ObjectMapperException;

import java.io.IOException;

public class ClienteOpenWeather extends Cliente implements ApiDeClima {

    //TODO agregar log
    private String key;
    private static final String PRONOSTICO_ACTUAL = "/weather?id=";

    //http://api.openweathermap.org/data/2.5
    //d0f7630cb095c3adf9622fdb01cd87c9
    public ClienteOpenWeather(String host, String key) {
        super(host);
        this.key = key;
    }

    public OpenWeatherResponseDTO obtenerTemperaturaActual(String locationKey){
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
    public Double obtenerTemperaturaActual() {
        String locationKey ="3433955";
        return obtenerTemperaturaActual(locationKey).getMain().getTemp();
    }
}
