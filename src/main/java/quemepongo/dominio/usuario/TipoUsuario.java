package quemepongo.dominio.usuario;

import quemepongo.dominio.Entidad;
import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.prenda.Prenda;

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