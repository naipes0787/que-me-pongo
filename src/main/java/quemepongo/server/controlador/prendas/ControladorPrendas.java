package quemepongo.server.controlador;

import quemepongo.dominio.evento.Localizacion;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.HashMap;

public class ControladorPrendas implements Controlador {

    public ModelAndView getBuilderPrendaPage(Request req, Response resp){


    }
    public ModelAndView getEventoPage(Request request, Response response) {
        return new ModelAndView(new HashMap<String, Localizacion[]>() {{
            put("lugares", Localizacion.values());
        }}, "formulario_evento.hbs");
    }





}


