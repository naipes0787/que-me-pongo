package quemepongo.persistencia;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import quemepongo.dominio.Entidad;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
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

    private void transaccional(Consumer<EntityManager> accion) {
        EntityManager em = entityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            accion.accept(em);
            em.flush();
            tx.commit();
        } catch (RuntimeException e) {
            tx.rollback();
            throw e;
        }
    }

}
