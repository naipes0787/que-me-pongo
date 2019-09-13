package quemepongo.model.usuario;

import quemepongo.model.Entidad;
import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.prenda.Prenda;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@DiscriminatorColumn(name = "codigo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class TipoUsuario extends Entidad {
	public abstract void agregarPrenda(Prenda prenda, Guardarropa guardarropa);
}