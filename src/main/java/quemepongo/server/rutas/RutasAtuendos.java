package quemepongo.server.rutas;

import quemepongo.server.controlador.atuendos.ControladorAtuendos;
import quemepongo.server.controlador.calificacion.ControladorCalificacion;

import static quemepongo.server.rutas.RutasConstantes.ATUENDOS_URL;
import static quemepongo.server.rutas.RutasConstantes.CALIFICACIONES_URL;
import static spark.Spark.*;

public class RutasAtuendos extends Rutas {

    private ControladorAtuendos controladorAtuendos = new ControladorAtuendos();
    private ControladorCalificacion controladorCalificacion = new ControladorCalificacion();

    @Override
    public void registrar() {
        get(ATUENDOS_URL,
                controladorAtuendos::mostrarAtuendos,
                templateEngine);

        get(CALIFICACIONES_URL,
                controladorCalificacion::mostrarFormulario,
                templateEngine);

        post(CALIFICACIONES_URL, controladorCalificacion::calificar);
    }
}
