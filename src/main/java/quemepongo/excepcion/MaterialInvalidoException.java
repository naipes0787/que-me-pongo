package quemepongo.excepcion;

import quemepongo.dominio.prenda.Material;

@SuppressWarnings("serial")
public class MaterialInvalidoException extends RuntimeException {
	
	public MaterialInvalidoException(Material material) {
		super("El material " + material.toString() + " no es válido para el tipo de prenda.");
	}
	
}
