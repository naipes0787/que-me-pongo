package quemepongo.model.usuario;

import quemepongo.exceptions.LimiteDeGuardarropasException;
import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.prenda.Prenda;

public class UsuarioGratuito implements TipoUsuario{
	
	private int limiteDeCapacidad = 25;
	
	public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
		
		if(limiteDeCapacidad <= guardarropa.cantidadDePrendas()) {
			throw new LimiteDeGuardarropasException();
		}
		
		guardarropa.agregarPrenda(prenda);
	}
}