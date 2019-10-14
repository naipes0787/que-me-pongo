package quemepongo.server.controlador;

import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.persistencia.RepositorioGuardarropa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControladorGuardarropas {

    public ModelAndView prendas(Request req, Response res) {
        Long id = Long.valueOf(req.params("id"));
        Guardarropa guardarropa = RepositorioGuardarropa.instancia().get(id);
        return new ModelAndView(guardarropa, "guardarropas.hbs");
    }
}
