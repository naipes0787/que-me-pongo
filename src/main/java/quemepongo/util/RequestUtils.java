package quemepongo.util;

import quemepongo.dominio.usuario.Usuario;
import quemepongo.excepcion.ControladorException;
import quemepongo.persistencia.RepositorioUsuario;
import spark.Request;

import java.io.InputStream;

public class RequestUtils {

    public static Long parsearId(Request req) {
        return Long.parseLong(req.params("id"));
    }

    public static Long obtenerIdActivo(Request req) {
        return req.session().attribute("id");
    }

    public static Usuario obtenerUsuario(Request req) {
        return RepositorioUsuario.instancia().getUsuarioByUsername(req.session().attribute("user"))
                .orElseThrow(() -> new ControladorException("Usuario no encontrado"));
    }

    public static InputStream obtenerArchivo(Request req, String key) {
        try {
            return req.raw().getPart(key).getInputStream();
        } catch (Exception e) {
            return null;
        }
    }
}
