package quemepongo.exceptions;

public class OpenWeatherException extends RuntimeException {

    public OpenWeatherException(String servicio){
        super("No se pudo obtener una respuesta del Servicio de Open Weather: "+servicio);
    }
}
