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

public class ControladorCalificacion {

    public ModelAndView mostrarFormulario(Request req, Response res) {
        Long idAtuendo = Long.parseLong(req.params("id"));
        Atuendo atuendo = RepositorioAtuendo.instancia().buscarAtuendo(idAtuendo);
        return new ModelAndView(new VistaCalificacion(atuendo), "calificacion.hbs");
    }

    public Void calificar(Request req, Response res) {
        Map<TipoCalificacion, Puntuacion> puntuaciones = req.queryParams().stream().collect(Collectors.toMap(
                TipoCalificacion::valueOf,
                tipoCalificacion -> Puntuacion.valueOf(req.queryParams(tipoCalificacion)))
        );
        Long idAtuendo = Long.parseLong(req.params("id"));
        Atuendo atuendo = RepositorioAtuendo.instancia().buscarAtuendo(idAtuendo);
        atuendo.setCalificacion(new Calificacion(puntuaciones));
        res.redirect("/atuendos");
        return null;
    }

}
