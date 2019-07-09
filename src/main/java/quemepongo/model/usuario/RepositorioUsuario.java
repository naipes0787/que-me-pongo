package quemepongo.model.usuario;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * Clase (Singleton) que contiene a todos los usuarios del sistema
 */
public class RepositorioUsuario {

	private static RepositorioUsuario repositorioUsuario;
	private Set<Usuario> usuarios;
	
	private RepositorioUsuario() {
		usuarios = Sets.newHashSet();
	}
	
    public static RepositorioUsuario getInstancia() {
        if (repositorioUsuario == null) {
        	repositorioUsuario = new RepositorioUsuario();
        }
        return repositorioUsuario;
    }

	public void agregarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public Set<Usuario> getUsuarios(){
		return this.usuarios;
	}
	
}
