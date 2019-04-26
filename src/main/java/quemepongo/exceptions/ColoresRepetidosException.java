package quemepongo.exceptions;

@SuppressWarnings("serial")
public class ColoresRepetidosException extends RuntimeException {
	
	public ColoresRepetidosException() {
		super("La prenda no puede tener el mismo color como principal y secundario.");
	}
	
}
