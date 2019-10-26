package quemepongo.server.controlador.atuendos;

import quemepongo.dominio.evento.Evento;
import quemepongo.server.controlador.Controlador;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.stream.Collectors;

public class ControladorAtuendos implements Controlador {

    public ModelAndView mostrarAtuendos(Request req, Response res) {
        return new ModelAndView(
                obtenerUsuario(req).getEventos().stream()
                    .filter(Evento::tieneSugerenciaAceptada)
                    .map(evento -> new VistaAtuendo(evento.getSugerenciaAceptada(), evento))
                    .collect(Collectors.toSet()),
                "atuendos.hbs");
    }

}
