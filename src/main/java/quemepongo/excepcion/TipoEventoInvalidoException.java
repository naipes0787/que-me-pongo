package quemepongo.excepcion;

@SuppressWarnings("serial")
public class TipoEventoInvalidoException extends RuntimeException {

	public TipoEventoInvalidoException() {
		super("El tipo de evento no existe");
		
	}
}