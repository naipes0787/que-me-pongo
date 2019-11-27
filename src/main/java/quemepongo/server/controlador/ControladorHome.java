package quemepongo.server.controlador;

import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

import static quemepongo.util.RequestUtils.obtenerUsuario;

public class ControladorHome {

    public ModelAndView homepage(Request req, Response res) {
        return new ModelAndView(obtenerUsuario(req), "home.hbs");
    }

}
