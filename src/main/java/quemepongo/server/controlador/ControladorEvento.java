package quemepongo.server.controlador;

import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.evento.Localizacion;
import quemepongo.dominio.evento.tipo.Anticipacion;
import quemepongo.dominio.evento.tipo.EventoUnico;
import quemepongo.excepcion.ControladorException;
import quemepongo.persistencia.RepositorioEvento;
import quemepongo.server.rutas.RutasConstantes;
import quemepongo.util.FechaUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class ControladorEvento implements Controlador {

    public ModelAndView formulario(Request request, Response response) {
        return new ModelAndView(new HashMap<String, Localizacion[]>() {{
            put("lugares", Localizacion.values());
        }}, "formulario_evento.hbs");
    }

    public Void guardarEvento(Request req, Response res) {
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
        return null;
    }

    public ModelAndView mostrarEvento(Request req, Response res) {
        Evento evento = RepositorioEvento.instancia().buscarEvento(parsearId(req))
                .orElseThrow(() -> new ControladorException("Evento " + parsearId(req) + " no encontrado"));;
        return new ModelAndView(evento, "evento_particular.hbs");
    }

}
