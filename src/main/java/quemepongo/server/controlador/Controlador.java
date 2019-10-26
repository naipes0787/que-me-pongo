package quemepongo.server.controlador;

import quemepongo.dominio.usuario.Usuario;
import quemepongo.excepcion.ControladorException;
import quemepongo.persistencia.RepositorioUsuario;
import spark.Request;

public interface Controlador {

    default Long parsearId(Request req) {
        return Long.parseLong(req.params("id"));
    }

    default Usuario obtenerUsuario(Request req) {
        return RepositorioUsuario.instancia().getUsuarioByUsername(req.session().attribute("user"))
                .orElseThrow(() -> new ControladorException("Usuario no encontrado"));
    }

}