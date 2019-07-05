package quemepongo.api.clientes;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import quemepongo.api.dto.AccuweatherResponseDTO;
import quemepongo.exceptions.ApiDeClimaException;
import quemepongo.exceptions.ObjectMapperException;
import quemepongo.model.Alerta;
import quemepongo.model.Temperatura;
import quemepongo.model.evento.Localizacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClienteAccuWeather implements ApiDeClima{

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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private String parametrosGenerales(){
        //TODO hacerlo parametrizable y usar Query
        return "?apikey="+key+"&language=es-AR&details=true&metric=true";
    }

    private List<AccuweatherResponseDTO> obtenerClimaActual(String locationKey){
        try {
            String respuesta = cliente.getAsString(PRONOSTICO_ACTUAL + locationKey + parametrosGenerales());
            return mapper.readValue(respuesta, new TypeReference<List<AccuweatherResponseDTO>>(){});
        }catch (IOException|ObjectMapperException exc){
            throw new ApiDeClimaException("Pronostico Actual");
        }
    }

    private String getLocationKey(Localizacion localizacion) {
        //TODO la idea es tener un mapa, propio de AccuWeather,
        // cuya key sea una localizacion y el valor sea el id
        return "7894";
    }

    private Boolean hayAlertaDeLluvia(AccuweatherResponseDTO climaActual){
        return climaActual.getHasPrecipitation();
    }

    private Boolean hayAlertaDeViento(AccuweatherResponseDTO climaActual){
        Double VELOCIDAD_DE_VIENTO_MAXIMA = 10D;
        //definir a partir de cuanto se considera que hay alerta, la velocidad esta en Km/h por default
        return climaActual.getWind().getSpeed().getMetric().getValue() > VELOCIDAD_DE_VIENTO_MAXIMA;
    }

    private Boolean hayAlertaDeSol(AccuweatherResponseDTO climaActual){
        Integer INDICE_UV_MAXIMO = 3;
        //definir a partir de cuanto se considera que hay alerta de sol
        return climaActual.getUVIndex() > INDICE_UV_MAXIMO;
    }

    private List<Alerta> definirAlertasActuales(AccuweatherResponseDTO climaActual){
        List<Alerta> alertas= new ArrayList<>();
        if(hayAlertaDeLluvia(climaActual)){
            alertas.add(Alerta.LLUVIA);
        }
        if(hayAlertaDeViento(climaActual)){
            alertas.add(Alerta.VIENTO);
        }
        if(hayAlertaDeSol(climaActual)){
            alertas.add(Alerta.SOL);
        }
        return alertas;
    }

    @Override
    public Temperatura obtenerTemperaturaActual(Localizacion localizacion) {
        String locationKey = getLocationKey(localizacion);
        Double temperaturaObtenida = obtenerClimaActual(locationKey)
                .get(0).getTemperature().getMetric().getValue();
        if (temperaturaObtenida == null) throw new ApiDeClimaException("Pronostico Actual");
        return new Temperatura(temperaturaObtenida);
    }

    @Override
    public List<Alerta> obtenerAlertasActuales(Localizacion localizacion) {
        String locationKey = getLocationKey(localizacion);
        AccuweatherResponseDTO climaActual = obtenerClimaActual(locationKey).get(0);
        return definirAlertasActuales(climaActual);
    }
}