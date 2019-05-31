package quemepongo.exceptions;

public class ObjectMapperException extends RuntimeException {

    public ObjectMapperException(){
        super("No se pudo convertir la respuesta a un Objeto Java");
    }
}
