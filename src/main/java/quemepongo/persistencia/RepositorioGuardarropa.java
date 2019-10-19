package quemepongo.persistencia;

import quemepongo.dominio.guardarropa.Guardarropa;

import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioGuardarropa extends Repositorio<Guardarropa> {

    private static RepositorioGuardarropa instancia = new RepositorioGuardarropa();

    private RepositorioGuardarropa() {}

    public static RepositorioGuardarropa instancia() {
        return instancia;
    }

    public Guardarropa get(Long id) {
        return entityManager().find(Guardarropa.class, id);
    }

    public List<Guardarropa> getGuardarropas() {
        TypedQuery<Guardarropa> query = entityManager().createQuery("from Guardarropa", Guardarropa.class);
        return query.getResultList();
    }

}
