package quemepongo.excepcion;

@SuppressWarnings("serial")
public class ClienteHttpException extends RuntimeException {

    public ClienteHttpException(String url){
        super("Hubo un error al llamar al siguiente servicio "+url);
    }
}
