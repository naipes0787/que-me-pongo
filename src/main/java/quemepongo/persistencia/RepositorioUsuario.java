package quemepongo.persistencia;

import quemepongo.dominio.usuario.Usuario;

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
        TypedQuery<Usuario> query = createQuery("from Usuario", Usuario.class);
        return query.getResultList();
    }

    // TODO: Es solo un mock, falta agregar el método real
    public Usuario getUsuarioByUsername(String username) {
        return new Usuario();
    }

}
