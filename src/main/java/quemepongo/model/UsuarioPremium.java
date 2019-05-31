package quemepongo.model;

public class UsuarioPremium implements TipoUsuario{
	
	public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
		
		guardarropa.agregarPrenda(prenda);
	}
}