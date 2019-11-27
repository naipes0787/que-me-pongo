package quemepongo.server.controlador;

import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.persistencia.RepositorioGuardarropa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import static quemepongo.util.RequestUtils.parsearId;

public class ControladorGuardarropas {

    public ModelAndView prendasByGuardarropaId(Request req, Response res) {
        Guardarropa guardarropa = RepositorioGuardarropa.instancia().buscarPorId(parsearId(req));
        return new ModelAndView(guardarropa, "guardarropa.hbs");
    }

}
