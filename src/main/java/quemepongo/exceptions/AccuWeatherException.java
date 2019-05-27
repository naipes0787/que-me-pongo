package quemepongo.exceptions;

public class AccuWeatherException extends RuntimeException{

    public AccuWeatherException(String servicio){
        super("No se pudo obtener una respuesta del Servicio de AccuWeather: "+servicio);
    }
}
