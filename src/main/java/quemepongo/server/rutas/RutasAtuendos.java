package quemepongo.server.rutas;

import quemepongo.server.controlador.atuendos.ControladorAtuendos;
import quemepongo.server.controlador.calificacion.ControladorCalificacion;

import static spark.Spark.*;

public class RutasAtuendos extends Rutas {

    private ControladorAtuendos controladorAtuendos = new ControladorAtuendos();
    private ControladorCalificacion controladorCalificacion = new ControladorCalificacion();

    @Override
    public void registrar() {
        get("/atuendos",
                controladorAtuendos::mostrarAtuendos,
                templateEngine);

        get("/atuendos/:id/calificacion",
                controladorCalificacion::mostrarFormulario,
                templateEngine);

        post("/atuendos/:id/calificacion", controladorCalificacion::calificar);
    }
}
