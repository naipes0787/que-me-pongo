package quemepongo.server.controlador;

import quemepongo.dominio.evento.Evento;
import quemepongo.persistencia.RepositorioEvento;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;

public class ControladorEventos extends ControladorAutenticado {

    public ModelAndView eventosByUsuarioId(Request req, Response res) {
        this.autenticar(req, res);
        Long id = Long.valueOf(req.params("id"));
        List<Evento> eventos = RepositorioEvento.instancia().getEventosByUsuarioId(id);
        return new ModelAndView(eventos, "prendas.hbs");
    }

}
