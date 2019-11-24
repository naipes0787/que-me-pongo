package quemepongo.persistencia;

import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import quemepongo.dominio.Entidad;
import quemepongo.dominio.prenda.TipoPrenda;

import java.util.List;

public abstract class Repositorio<T extends Entidad> implements WithGlobalEntityManager, EntityManagerOps {

    public void guardar(T entidad) {
        if (entidad.getId() == null) {
            persist(entidad);
        } else {
            merge(entidad);
        }
    }

    public void borrar(T entidad) {
        remove(entidad);
    }
}
