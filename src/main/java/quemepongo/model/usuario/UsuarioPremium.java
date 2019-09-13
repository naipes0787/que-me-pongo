package quemepongo.model.usuario;

import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.prenda.Prenda;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("PREMIUM")
public class UsuarioPremium extends TipoUsuario{
	
	public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
		
		guardarropa.agregarPrenda(prenda);
	}
}