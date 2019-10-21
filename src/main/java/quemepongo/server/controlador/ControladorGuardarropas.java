package quemepongo.server.controlador;

import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioGuardarropa;
import quemepongo.persistencia.RepositorioUsuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControladorGuardarropas extends ControladorAutenticado{

    public ModelAndView prendasByGuardarropaId(Request req, Response res) {
        this.autenticar(req, res);
        Long id = Long.valueOf(req.params("id"));
        Guardarropa guardarropa = RepositorioGuardarropa.instancia().get(id);
        return new ModelAndView(guardarropa, "prendas.hbs");
    }

    public ModelAndView guardarropas(Request req, Response res) {
        this.autenticar(req, res);
        String username = req.session().attribute("user");
        Usuario usuario = RepositorioUsuario.instancia().getUsuarioByUsername(username);
        return new ModelAndView(usuario, "guardarropas.hbs");
    }

}
