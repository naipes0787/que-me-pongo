package quemepongo.excepcion;

@SuppressWarnings("serial")
public class ObjectMapperException extends RuntimeException {

    public ObjectMapperException(){
        super("No se pudo convertir la respuesta a un Objeto Java");
    }
}
