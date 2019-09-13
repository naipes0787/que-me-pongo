package quemepongo.persistencia;

import quemepongo.model.usuario.EntidadUsuario;
import quemepongo.model.usuario.Usuario;

import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RepositorioUsuario2 extends Repositorio<EntidadUsuario>{

    private static RepositorioUsuario2 instancia;

    private RepositorioUsuario2() {
    }

    public static RepositorioUsuario2 instancia() {
        if (instancia == null) {
            instancia = new RepositorioUsuario2();
        }
        return instancia;
    }

    public void agregarUsuario(Usuario usuario) {
        //instancia().guardar(usuarioEntidad); Como lo convierto de usuario a usuarioEntidad?
    }

    public Set<Usuario> getUsuarios(){
        TypedQuery<EntidadUsuario> query = entityManager().createQuery("from Usuario", EntidadUsuario.class);
        //return new HashSet<EntidadUsuario>(query.getResultList()); como paso de usuarioentidad a usuario?
        return new HashSet<>();
    }
}
