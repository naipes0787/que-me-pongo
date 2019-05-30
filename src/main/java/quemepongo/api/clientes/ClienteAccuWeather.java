package quemepongo.api.clientes;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.http.HttpResponse;
import quemepongo.api.dto.AccuweatherResponseDTO;
import quemepongo.exceptions.ApiDeClimaException;
import quemepongo.exceptions.ObjectMapperException;
import quemepongo.model.Localizacion;
import quemepongo.model.Temperatura;

import java.io.IOException;
import java.util.List;

public class ClienteAccuWeather extends Cliente implements ApiDeClima{

    //TODO agregar log y que el host y la key vengan de un .properties
    private static final String host = "http://dataservice.accuweather.com";
    private static final String key = "4zxMMc9pFj6f6pOdoQ2TirQCUwLTmG9S";
    private static final String PRONOSTICO_ACTUAL = "/currentconditions/v1/";

    public ClienteAccuWeather() {
        super(host);
    }

    private List<AccuweatherResponseDTO> obtenerTemperaturaActual(String locationKey){
        HttpResponse respuesta = get(PRONOSTICO_ACTUAL + locationKey + parametrosGenerales());
        if(terminoEnError(respuesta)){
            throw new ApiDeClimaException("Pronostico Actual");
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
    public Temperatura obtenerTemperaturaActual(Localizacion localizacion) {
        String locationKey = getLocationKey(localizacion);
        Temperatura temperatura = new Temperatura();
        temperatura.setTemperatura(obtenerTemperaturaActual(locationKey).get(0).getTemperature().getMetric().getValue());
        return temperatura;
    }

    private String getLocationKey(Localizacion localizacion) {
        //TODO la idea es tener un mapa, propio de AccuWeather, cuya key sea una localizacion y el valor sea el id
        return "7894";
    }
}