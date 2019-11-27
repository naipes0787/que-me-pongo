package quemepongo.persistencia;

import quemepongo.dominio.guardarropa.Guardarropa;

import java.util.Optional;

public class RepositorioGuardarropa extends Repositorio<Guardarropa> {

    private static RepositorioGuardarropa instancia = new RepositorioGuardarropa();

    private RepositorioGuardarropa() {}

    public static RepositorioGuardarropa instancia() {
        return instancia;
    }

    public Guardarropa buscarPorId(Long id) {
        return Optional.ofNullable(find(Guardarropa.class, id)).orElseThrow(() -> new RuntimeException("Guardarropa no encontrado"));
    }

}
