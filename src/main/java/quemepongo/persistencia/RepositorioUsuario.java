package quemepongo.persistencia;

import quemepongo.dominio.usuario.Usuario;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class RepositorioUsuario extends Repositorio<Usuario>{

    private static RepositorioUsuario instancia;

    public static RepositorioUsuario instancia() {
        if (instancia == null) {
            instancia = new RepositorioUsuario();
        }
        return instancia;
    }

    public List<Usuario> getUsuarios() {
        TypedQuery<Usuario> query = createQuery("from Usuario", Usuario.class);
        return query.getResultList();
    }

    public Optional<Usuario> getUsuarioByUsername(String username) {
        TypedQuery<Usuario> query = createQuery("from Usuario u WHERE u.username = :username", Usuario.class)
                .setParameter("username", username);
        try {
            return Optional.of(query.getSingleResult());
        } catch (NoResultException exception) {
            return Optional.empty();
        }

    }

}
