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
        Boolean wrongUser = request.session().attribute("wrongUser");
        request.session().removeAttribute("wrongUser");
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("wrongUser", wrongUser);
        return new ModelAndView(map, "login.hbs");
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
            request.session().attribute("wrongUser", Boolean.TRUE);
            response.status(401);
            response.redirect(RutasConstantes.LOGIN_URL);
        }
        return null;
    }

}
