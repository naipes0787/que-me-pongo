package quemepongo.model;

import quemepongo.exceptions.LimiteDeGuardarropasException;

;

public class UsuarioGratuito implements TipoUsuario{
	
	private int limiteDeCapacidad = 25;
	
	public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
		
		if(limiteDeCapacidad <= guardarropa.cantidadDePrendas()) {
			throw new LimiteDeGuardarropasException();
		}
		
		guardarropa.agregarPrenda(prenda);
	}
}