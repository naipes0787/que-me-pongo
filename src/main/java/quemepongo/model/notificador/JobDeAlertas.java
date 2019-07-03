package quemepongo.model.notificador;

import java.util.Set;

import quemepongo.model.usuario.RepositorioUsuario;
import quemepongo.model.usuario.Usuario;

public class JobDeAlertas {

	public void comprobarAlertas() {
		// TODO: Lógica para comprobar alertas
		Set<Usuario> usuarios = RepositorioUsuario.getInstancia().getUsuarios();
		usuarios.forEach(usuario -> usuario.actuarAnte(TipoAlerta.METEOROLOGICA));
		usuarios.forEach(usuario -> usuario.actuarAnte(TipoAlerta.EVENTO_PROXIMO));
	}
	
}
