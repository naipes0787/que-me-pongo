package quemepongo.server.rutas;

import quemepongo.server.controlador.ControladorEvento;
import spark.Spark;

public class RutasEvento extends Rutas {

    private ControladorEvento controlador = new ControladorEvento();

    @Override
    public void registrar() {

        Spark.get(RutasConstantes.EVENTOS_URL,
                controlador::getEventoPage,
                templateEngine);

        Spark.post(RutasConstantes.EVENTOS_URL,
                controlador::guardarEvento);

    }
}
