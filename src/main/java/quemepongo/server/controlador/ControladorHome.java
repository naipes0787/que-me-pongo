package quemepongo.server.controlador;

import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.persistencia.RepositorioGuardarropa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class ControladorHome extends ControladorAutenticado{

    public ModelAndView homepage(Request req, Response res) {
        this.autenticar(req, res);
        return new ModelAndView(new HashMap<>(), "home.hbs");
    }

}
