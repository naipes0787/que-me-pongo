package quemepongo.persistencia;

import quemepongo.dominio.guardarropa.Guardarropa;

public class RepositorioGuardarropa extends Repositorio<Guardarropa> {

    private static RepositorioGuardarropa instancia = new RepositorioGuardarropa();

    private RepositorioGuardarropa() {}

    public static RepositorioGuardarropa instancia() {
        return instancia;
    }

    public Guardarropa get(Long id) {
        return entityManager().find(Guardarropa.class, id);
    }

}
