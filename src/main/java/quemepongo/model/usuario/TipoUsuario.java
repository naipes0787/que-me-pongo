package quemepongo.model.usuario;

import quemepongo.model.Guardarropa;
import quemepongo.model.prenda.Prenda;

public interface TipoUsuario {
	void agregarPrenda(Prenda prenda, Guardarropa guardarropa);
}