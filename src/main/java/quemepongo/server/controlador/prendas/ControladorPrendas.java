package quemepongo.server.controlador.prendas;

import quemepongo.server.controlador.Controlador;
import spark.ModelAndView;
import spark.Request;
import spark.Response;


public class ControladorPrendas implements Controlador {

    public ModelAndView getBuilderPrendaForm(Request req, Response resp){
        return new ModelAndView(new VistaPrenda(),"formulario_prenda.hbs");
    }
}



