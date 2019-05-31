package quemepongo.exceptions;

@SuppressWarnings("serial")
public class LimiteDeGuardarropasException extends RuntimeException {
	
	public LimiteDeGuardarropasException() {
		super("El guardarropas no puede agregar más atuendos.");
		
	}
}