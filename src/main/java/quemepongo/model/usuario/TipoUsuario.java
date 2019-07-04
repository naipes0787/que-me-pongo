package quemepongo.model.usuario;

import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.prenda.Prenda;

public interface TipoUsuario {
	void agregarPrenda(Prenda prenda, Guardarropa guardarropa);
}