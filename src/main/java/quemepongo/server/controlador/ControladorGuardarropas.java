package quemepongo.server.controlador;

import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.persistencia.RepositorioGuardarropa;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControladorGuardarropas {

    public ModelAndView prendasByGuardarropaId(Request req, Response res) {
        String username = req.session().attribute("user");
        Long id = Long.valueOf(req.params("id"));
        Guardarropa guardarropa = RepositorioGuardarropa.instancia().get(id);
        return new ModelAndView(guardarropa, "guardarropas.hbs");
    }

    // TODO: Crear la parte visual para poder mostrar todos los guardarropas con sus prendas
    public ModelAndView prendas(Request req, Response res) {
        Guardarropa guardarropa = RepositorioGuardarropa.instancia().getGuardarropas().get(0);
        String username = req.session().attribute("user");
        return new ModelAndView(guardarropa, "guardarropas.hbs");
    }
}
