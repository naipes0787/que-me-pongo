package quemepongo.model;

import java.util.Set;

import com.google.common.collect.Sets;

/**
 * Clase (Singleton) que contiene a todos los usuarios del sistema
 */
public class RepositorioUsuario {

	private static RepositorioUsuario repositorioUsuario;
	private Set<Usuario> usuarios;
	
	private RepositorioUsuario() {
		Sets.newHashSet();
	}
	
    public static RepositorioUsuario getInstancia() {
        if (repositorioUsuario == null) {
        	repositorioUsuario = new RepositorioUsuario();
        }
        return repositorioUsuario;
    }

	void agregarUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
	
	public Set<Usuario> getUsuarios(){
		return this.usuarios;
	}
	
}
