package quemepongo.model.usuario;

import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.prenda.Prenda;

public class UsuarioPremium implements TipoUsuario{
	
	public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
		
		guardarropa.agregarPrenda(prenda);
	}
}