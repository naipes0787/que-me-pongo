package quemepongo.persistencia;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import quemepongo.model.Entidad;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.function.Consumer;

public abstract class Repositorio<T extends Entidad> implements WithGlobalEntityManager {

    public void guardar(T entidad) {
        if (entidad.getId() == null) {
            transaccional(em -> em.persist(entidad));
        } else {
            transaccional(em -> em.merge(entidad));
        }

    }

    public void borrar(T entidad) {
        transaccional(em -> em.remove(entidad));
    }

    public abstract List<T> obtenerTodos();

    private void transaccional(Consumer<EntityManager> action) {
        EntityTransaction tx = entityManager().getTransaction();
        try {
            tx.begin();
            action.accept(entityManager());
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

}
