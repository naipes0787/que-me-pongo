package quemepongo.dominio.usuario;

import quemepongo.excepcion.LimiteDeGuardarropasException;
import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.prenda.Prenda;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("GRATUITO")
public class UsuarioGratuito extends TipoUsuario{
	
	private int limiteDeCapacidad = 25;
	
	public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
		
		if(limiteDeCapacidad <= guardarropa.cantidadDePrendas()) {
			throw new LimiteDeGuardarropasException();
		}
		
		guardarropa.agregarPrenda(prenda);
	}
}