package quemepongo.model;

import java.util.Set;

public class JobDeAlertas {

	public void comprobarAlertas() {
		// TODO: Lógica para comprobar alertas
		Set<Usuario> usuarios = RepositorioUsuario.getInstancia().getUsuarios();
		usuarios.stream().forEach(usuario -> usuario.actuarAnte(TipoAlerta.METEOROLOGICA));
		usuarios.stream().forEach(usuario -> usuario.actuarAnte(TipoAlerta.EVENTO_PROXIMO));
	}
	
}
