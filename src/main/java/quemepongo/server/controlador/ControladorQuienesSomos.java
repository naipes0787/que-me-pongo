package quemepongo.server.controlador;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class ControladorQuienesSomos {

    public ModelAndView quienesSomos(Request req, Response res) {
        return new ModelAndView(new HashMap<>(), "quienes-somos.hbs");
    }

}
