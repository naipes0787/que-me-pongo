package quemepongo.server.controlador.calificacion;

import quemepongo.dominio.calificacion.Calificacion;
import quemepongo.dominio.calificacion.Puntuacion;
import quemepongo.dominio.calificacion.TipoCalificacion;
import quemepongo.dominio.sugerencia.Atuendo;
import quemepongo.persistencia.RepositorioAtuendo;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Map;
import java.util.stream.Collectors;

import static quemepongo.server.rutas.RutasConstantes.ATUENDOS_URL;
import static quemepongo.util.RequestUtils.obtenerUsuario;
import static quemepongo.util.RequestUtils.parsearId;

public class ControladorCalificacion {

    public ModelAndView mostrarFormulario(Request req, Response res) {
        Atuendo atuendo = buscarAtuendo(req);
        return new ModelAndView(new VistaCalificacion(atuendo), "calificacion.hbs");
    }

    public Void calificar(Request req, Response res) {
        Map<TipoCalificacion, Puntuacion> puntuaciones = req.queryParams().stream().collect(Collectors.toMap(
                TipoCalificacion::valueOf,
                tipo -> Puntuacion.valueOf(req.queryParams(tipo)))
        );
        Atuendo atuendo = buscarAtuendo(req);
        Calificacion calificacion = new Calificacion(puntuaciones);
        atuendo.setCalificacion(calificacion);
        obtenerUsuario(req).modificarSensibilidad(calificacion);

        res.redirect(ATUENDOS_URL);
        return null;
    }

    private Atuendo buscarAtuendo(Request req) {
        return RepositorioAtuendo.instancia().buscarAtuendo(parsearId(req));
    }

}