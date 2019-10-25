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
        Long usuario = req.session().attribute("id");
        List<Evento> eventos = RepositorioEvento.instancia().getEventosByUsuarioId(usuario);
        return new ModelAndView(new ContenedorEventos(InformacionDeEvento.armarEventosParaCalendario(eventos)), "calendario.hbs");
    }

}
