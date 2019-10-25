package quemepongo.server.rutas;

import quemepongo.server.controlador.ControladorEventos;
import spark.Spark;

public class RutasEventos extends Rutas {

    private ControladorEventos controlador = new ControladorEventos();

    @Override
    public void registrar() {
        Spark.get(RutasConstantes.EVENTOS_URL,
                controlador::eventosByUsuarioId,
                templateEngine);
    }
}
