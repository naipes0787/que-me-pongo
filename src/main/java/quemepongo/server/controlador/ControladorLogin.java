package quemepongo.server.controlador;

import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioUsuario;
import quemepongo.server.rutas.RutasConstantes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class ControladorLogin {

    public ModelAndView getLoginPage(Request request, Response response) {
        return new ModelAndView(new HashMap<>(), "login.hbs");
    }

    public Void login(Request request, Response response) {
        String username = request.queryParams("usuario");
        String pass = request.queryParams("contrasenia");
        Usuario usuario = RepositorioUsuario.instancia().getUsuarioByUsername(username);
        if (usuario != null && usuario.getPassword().equals(pass)) {
            request.session(true);
            request.session().attribute("user", username);
            response.redirect(RutasConstantes.GUARDARROPAS_URL);
        } else {
            response.status(401);
            response.redirect(RutasConstantes.LOGIN_URL);
        }
        return null;
    }
}
