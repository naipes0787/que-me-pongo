package quemepongo.persistencia;

import quemepongo.model.usuario.Usuario;

import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioUsuario extends Repositorio<Usuario>{

    private static RepositorioUsuario instancia;

    public static RepositorioUsuario instancia() {
        if (instancia == null) {
            instancia = new RepositorioUsuario();
        }
        return instancia;
    }

    public List<Usuario> getUsuarios() {
        TypedQuery<Usuario> query = entityManager().createQuery("from Usuario", Usuario.class);
        return query.getResultList();
    }
}
