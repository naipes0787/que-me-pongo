package quemepongo.excepcion;

public class ControladorException extends RuntimeException {

    public ControladorException(String mensaje) {
        super(mensaje);
    }
}
