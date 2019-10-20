package quemepongo.server.controlador;

import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioUsuario;
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
        System.out.println("El usuario " + username + " quiere loguearse");

        Usuario usuario = RepositorioUsuario.instancia().getUsuarioByUsername(username);
        if (usuario.getPassword().equals(pass)) {
            System.out.println("El usuario " + username + " se logueó");
            request.session(true);
            request.attribute("user", usuario);
            response.redirect("/guardarropas/prendas");
        } else {
            System.out.println("El usuario " + username + " ingresó mal su contraseña");
            response.status(401);
            response.redirect("/login");
        }
        return null;
    }
}
