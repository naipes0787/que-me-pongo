package quemepongo.server.controlador.prendas;

import quemepongo.server.controlador.Controlador;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class ControladorPrendas implements Controlador {

    public ModelAndView getBuilderPrendaForm(Request req, Response resp){
        VistaPrenda vistaPrenda = new VistaPrenda();
        return new ModelAndView(vistaPrenda,"formulario_prenda.hbs");
    }
}



