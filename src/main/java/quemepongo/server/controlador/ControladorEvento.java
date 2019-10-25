package quemepongo.server.controlador;

import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.evento.Localizacion;
import quemepongo.dominio.evento.tipo.Anticipacion;
import quemepongo.dominio.evento.tipo.EventoUnico;
import quemepongo.dominio.evento.tipo.TipoEvento;
import quemepongo.persistencia.RepositorioEvento;
import quemepongo.server.rutas.RutasConstantes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;

public class ControladorEvento implements Controlador {

    public ModelAndView getEventoPage(Request request, Response response) {
        return new ModelAndView(new HashMap<String, Localizacion[]>() {{
            put("lugares", Localizacion.values());
        }}, "formulario_evento.hbs");
    }

    public Void guardarEvento(Request request, Response response) {
        Evento evento = new Evento(
                request.queryParams("titulo"),
                Localizacion.valueOf(request.queryParams("lugar")),
                new EventoUnico(parsearFecha(request)),
                new Anticipacion(ChronoUnit.DAYS, Integer.valueOf(request.queryParams("anticipacion"))));

        usuarioActivo(request).agregarEvento(evento);
        RepositorioEvento.instancia().guardar(evento);
        response.status(201);
        response.redirect(RutasConstantes.HOME_URL);
        return null;
    }

    private LocalDateTime parsearFecha(Request request) {
        String fecha = request.queryParams("fecha");
        String hora = request.queryParams("hora");
        String minutos = request.queryParams("minutos");
        return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("yyyy-MM-dd"))
                .atTime(Integer.parseInt(hora), Integer.parseInt(minutos));
    }

}
