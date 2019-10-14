package quemepongo.excepcion;

@SuppressWarnings("serial")
public class FechaEventoNoValidaException extends RuntimeException {
	
	public FechaEventoNoValidaException() {
		super("La fecha ingresada para el evento no es valida.");
	}
	
}
