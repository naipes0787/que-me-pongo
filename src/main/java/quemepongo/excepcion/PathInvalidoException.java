package quemepongo.excepcion;

@SuppressWarnings("serial")
public class PathInvalidoException extends RuntimeException {
	
	public PathInvalidoException(String path) {
		super("El path " + path + " no existe.");
	}
	
}
