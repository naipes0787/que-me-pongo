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
                usuarioActivo(req).getEventos().stream()
                    .filter(Evento::tieneAtuendo)
                    .map(evento -> new VistaAtuendo(evento.getAtuendo(), evento))
                    .collect(Collectors.toSet()),
                "atuendos.hbs");
    }

}
