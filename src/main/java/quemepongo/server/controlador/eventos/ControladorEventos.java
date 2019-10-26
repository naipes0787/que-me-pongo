package quemepongo.server.controlador.eventos;

import quemepongo.dominio.evento.ContenedorEventos;
import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.evento.InformacionDeEvento;
import quemepongo.dominio.evento.Localizacion;
import quemepongo.dominio.evento.tipo.Anticipacion;
import quemepongo.dominio.evento.tipo.EventoUnico;
import quemepongo.excepcion.ControladorException;
import quemepongo.excepcion.FechaEventoNoValidaException;
import quemepongo.persistencia.RepositorioEvento;
import quemepongo.server.controlador.Controlador;
import quemepongo.server.rutas.RutasConstantes;
import quemepongo.util.FechaUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

public class ControladorEventos implements Controlador {

    public ModelAndView formulario(Request request, Response response) {
        Optional<Boolean> isFechaInvalida = Optional.ofNullable(request.session().attribute("isFechaInvalida"));
        request.session().removeAttribute("isFechaInvalida");
        VistaEvento vistaEvento = new VistaEvento(isFechaInvalida.orElse(false));
        return new ModelAndView(vistaEvento, "formulario_evento.hbs");
    }

    public Void guardarEvento(Request req, Response res) {
        try {
            LocalDateTime fecha = FechaUtils.parsear(req.queryParams("fecha"), req.queryParams("hora"), req.queryParams("minutos"));
            Evento evento = new Evento(
                    req.queryParams("titulo"),
                    Localizacion.valueOf(req.queryParams("lugar")),
                    new EventoUnico(fecha),
                    new Anticipacion(ChronoUnit.DAYS, Integer.valueOf(req.queryParams("anticipacion")))
            );
            obtenerUsuario(req).agregarEvento(evento);
            RepositorioEvento.instancia().guardar(evento);
            res.status(201);
            res.redirect(RutasConstantes.HOME_URL);
        } catch (FechaEventoNoValidaException e) {
            req.session().attribute("isFechaInvalida", true);
            res.redirect(RutasConstantes.FORMULARIO_ALTA_EVENTOS);
        }
        return null;
    }

    public ModelAndView mostrarEvento(Request req, Response res) {
        Evento evento = RepositorioEvento.instancia().buscarEvento(parsearId(req))
                .orElseThrow(() -> new ControladorException("Evento " + parsearId(req) + " no encontrado"));;
        return new ModelAndView(evento, "evento_particular.hbs");
    }

    public ModelAndView eventosByUsuarioId(Request req, Response res) {
        List<Evento> eventos = RepositorioEvento.instancia().getEventosByUsuarioId(obtenerIdActivo(req));
        return new ModelAndView(new ContenedorEventos(InformacionDeEvento.armarEventosParaCalendario(eventos)), "calendario.hbs");
    }

}
