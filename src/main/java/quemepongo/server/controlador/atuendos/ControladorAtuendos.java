package quemepongo.server.controlador.atuendos;

import quemepongo.dominio.evento.Evento;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.stream.Collectors;

import static quemepongo.util.RequestUtils.obtenerUsuario;

public class ControladorAtuendos {

    public ModelAndView mostrarAtuendos(Request req, Response res) {
        return new ModelAndView(
                obtenerUsuario(req).getEventos().stream()
                    .filter(Evento::tieneSugerenciaAceptada)
                    .map(evento -> new VistaAtuendo(evento.getSugerenciaAceptada(), evento))
                    .collect(Collectors.toSet()),
                "atuendos.hbs");
    }

}
