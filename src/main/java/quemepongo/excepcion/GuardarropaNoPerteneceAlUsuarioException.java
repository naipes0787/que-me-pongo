package quemepongo.excepcion;

@SuppressWarnings("serial")
public class GuardarropaNoPerteneceAlUsuarioException extends RuntimeException {

    public GuardarropaNoPerteneceAlUsuarioException() {
        super("El guardarropas no le pertenece al usuario.");
    }
}