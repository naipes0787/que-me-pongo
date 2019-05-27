package quemepongo.api.clientes;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.HttpResponse;
import quemepongo.api.dto.AccuweatherResponseDTO;
import quemepongo.exceptions.AccuWeatherException;
import quemepongo.exceptions.ObjectMapperException;

import java.io.IOException;
import java.util.List;

public class ClienteAccuWeather extends Cliente implements ApiDeClima{

    //TODO agregar log
    private String key;
    //private static final String PRONOSTICO_UNA_HORA = "/forecasts/v1/hourly/1hour/";
    //private static final String PRONOSTICO_UN_DIA = "/forecasts/v1/daily/1day/";
    private static final String PRONOSTICO_ACTUAL = "/currentconditions/v1/";

    public ClienteAccuWeather(String host, String key) {
        super(host);
        this.key = key;
    }

    public List<AccuweatherResponseDTO> obtenerTemperaturaActual(String locationKey){
        HttpResponse respuesta = get(PRONOSTICO_ACTUAL + locationKey + parametrosGenerales());
        if(terminoEnError(respuesta)){
            throw new AccuWeatherException("Pronostico Actual");
        }
        try{
            return mapper.readValue(respuesta.getEntity().getContent(), new TypeReference<List<AccuweatherResponseDTO>>(){});
        }catch (IOException exc){
            throw new ObjectMapperException();
        }
    }

    private String parametrosGenerales(){
        //TODO hacerlo parametrizable y usar Query
        return "?apikey="+key+"&language=es-AR&details=true&metric=true";
    }

    @Override
    public Double obtenerTemperaturaActual() {
        String locationKey ="7894";
        return obtenerTemperaturaActual(locationKey).get(0).getTemperature().getMetric().getValue();
    }
}