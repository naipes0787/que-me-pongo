package quemepongo.server.controlador;

import quemepongo.dominio.evento.ContenedorEventos;
import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.evento.InformacionDeEvento;
import quemepongo.persistencia.RepositorioEvento;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;

public class ControladorEventos extends ControladorAutenticado {

    public ModelAndView eventosByUsuarioId(Request req, Response res) {
        this.autenticar(req, res);
        Long id = Long.valueOf(req.params("id"));
        List<Evento> eventos = RepositorioEvento.instancia().getEventos();
        return new ModelAndView(new ContenedorEventos(InformacionDeEvento.armarEventosParaCalendario(eventos)), "eventos.hbs");
    }

}
