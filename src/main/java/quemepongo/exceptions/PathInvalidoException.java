package quemepongo.exceptions;

@SuppressWarnings("serial")
public class PathInvalidoException extends RuntimeException {
	
	public PathInvalidoException(String path) {
		super("El path " + path + " no existe.");
	}
	
}
