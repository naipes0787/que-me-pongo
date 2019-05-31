package quemepongo.exceptions;

@SuppressWarnings("serial")
public class ApiDeClimaException extends RuntimeException{

    public ApiDeClimaException(String servicio){
        super("No se pudo obtener una respuesta del Servicio: "+servicio);
    }

}
