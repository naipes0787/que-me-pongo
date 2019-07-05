package quemepongo.api.clientes;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import quemepongo.api.dto.Lluvia;
import quemepongo.api.dto.OpenWeatherResponseDTO;
import quemepongo.exceptions.ApiDeClimaException;
import quemepongo.exceptions.ObjectMapperException;
import quemepongo.model.Alerta;
import quemepongo.model.Temperatura;
import quemepongo.model.evento.Localizacion;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    private String parametrosGenerales(){
        //TODO hacerlo parametrizable y usar Query
        return "&appid="+key+"&units=metric&lang=es";
    }

    private String getLocationKey(Localizacion localizacion) {
        //TODO la idea es tener un mapa, propio de OpenWeather,
        // cuya key sea una localizacion y el valor sea el id
        return "3433955";
    }

    private OpenWeatherResponseDTO obtenerClimaActual(String locationKey){
        try {
            String respuesta = cliente.getAsString(PRONOSTICO_ACTUAL + locationKey + parametrosGenerales());
            return mapper.readValue(respuesta, OpenWeatherResponseDTO.class);
        }catch (IOException|ObjectMapperException exc){
            throw new ApiDeClimaException("Pronostico Actual");
        }
    }

    private Boolean vaALlover(Lluvia lluvia){
        Boolean llueveEnLaProximaHora = lluvia.getUnaHora() != null;
        Boolean llueveEnLasProximasTresHoras = lluvia.getTresHoras() != null;
        return llueveEnLaProximaHora || llueveEnLasProximasTresHoras;
    }

    private Boolean hayAlertaDeLluvia(OpenWeatherResponseDTO climaActual){
        Lluvia lluvia = climaActual.getRain();
        return lluvia != null && vaALlover(lluvia);
    }

    private Boolean hayAlertaDeViento(OpenWeatherResponseDTO climaActual){
        Double VELOCIDAD_MAXIMA_DE_VIENTO = 1D;
        //verificar a partir de que velocidad de viento se considera como alerta
        //la velocidad esta en m/s
        return climaActual.getWind().getSpeed() > VELOCIDAD_MAXIMA_DE_VIENTO;
    }

    private Boolean hayAlertaDeSol(OpenWeatherResponseDTO climaActual){
        //La api no aporta ningun dato como el indice UV, lo mas cercano que encontre es
        //si hay o no nubes, no es preciso, pero se puede poner que cuando haya mas
        // de cierto porcentaje de nubes, se considere como que hay mucho sol
        Integer PORCENTAJE_DE_NUBOCIDAD_MAXIMA = 0;
        return climaActual.getClouds().getAll() > PORCENTAJE_DE_NUBOCIDAD_MAXIMA;
    }

    private List<Alerta> definirAlertasActuales(OpenWeatherResponseDTO climaActual){
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
                .getMain().getTemp();
        if (temperaturaObtenida == null) throw new ApiDeClimaException("Pronostico Actual");
        return new Temperatura(temperaturaObtenida);
    }

    @Override
    public List<Alerta> obtenerAlertasActuales(Localizacion localizacion) {
        String locationKey = getLocationKey(localizacion);
        OpenWeatherResponseDTO climaActual = obtenerClimaActual(locationKey);
        return definirAlertasActuales(climaActual);
    }
}
