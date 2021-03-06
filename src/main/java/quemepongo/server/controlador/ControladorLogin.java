package quemepongo.server.controlador;

import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioUsuario;
import quemepongo.server.rutas.RutasConstantes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Optional;

public class ControladorLogin {

    public ModelAndView getLoginPage(Request request, Response response) {
        // Se obtiene datosIncorrectos por si hay que mostrar un mensaje de error en la pantalla
        Boolean datosIncorrectos = request.session().attribute("datosIncorrectos");
        request.session().removeAttribute("datosIncorrectos");
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("datosIncorrectos", datosIncorrectos);
        return new ModelAndView(map, "login.hbs");
    }

    public Void login(Request request, Response response) {
        String username = request.queryParams("usuario");
        String pass = request.queryParams("contrasenia");
        Optional<Usuario> usuario = RepositorioUsuario.instancia().getUsuarioByUsername(username);
        if (usuario.isPresent() && usuario.get().isPasswordOk(pass)) {
            request.session(true);
            request.session().attribute("user", username);
            request.session().attribute("id", usuario.get().getId());
            response.redirect(RutasConstantes.HOME_URL);
        } else {
            // Se setea datosIncorrectos en TRUE ya que se ingresó mal el usuario o password
            request.session().attribute("datosIncorrectos", true);
            response.status(401);
            response.redirect(RutasConstantes.LOGIN_URL);
        }
        return null;
    }

    public ModelAndView logout(Request request, Response response) {
        request.session().removeAttribute("user");
        return new ModelAndView(new HashMap<>(), "login.hbs");
    }

}
