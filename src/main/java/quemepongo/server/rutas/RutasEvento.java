package quemepongo.server.rutas;

import quemepongo.server.controlador.ControladorEventos;
import static quemepongo.server.rutas.RutasConstantes.EVENTOS_URL;
import static spark.Spark.get;

public class RutasEvento extends Rutas {

    private ControladorEventos controlador = new ControladorEventos();

    @Override
    public void registrar() {

        get(EVENTOS_URL, controlador::eventosByUsuarioId, templateEngine);

    }
}
