package quemepongo.server.controlador;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class ControladorHome implements Controlador {

    public ModelAndView homepage(Request req, Response res) {
        return new ModelAndView(obtenerUsuario(req), "home.hbs");
    }

}
