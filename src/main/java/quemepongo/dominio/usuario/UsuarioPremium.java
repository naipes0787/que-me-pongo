package quemepongo.dominio.usuario;

import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.prenda.Prenda;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PREMIUM")
public class UsuarioPremium extends TipoUsuario{
	
	public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
		
		guardarropa.agregarPrenda(prenda);
	}
}