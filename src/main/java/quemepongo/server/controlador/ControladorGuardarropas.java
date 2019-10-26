package quemepongo.server.controlador;

import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.persistencia.RepositorioGuardarropa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControladorGuardarropas implements Controlador {

    public ModelAndView prendasByGuardarropaId(Request req, Response res) {
        Guardarropa guardarropa = RepositorioGuardarropa.instancia().get(parsearId(req));
        return new ModelAndView(guardarropa, "guardarropa.hbs");
    }

    public ModelAndView guardarropas(Request req, Response res) {
        return new ModelAndView(obtenerUsuario(req), "guardarropas.hbs");
    }

}
