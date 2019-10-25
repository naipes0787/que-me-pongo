package quemepongo.server.rutas;

import quemepongo.server.controlador.ControladorEvento;
import spark.Spark;

public class RutasEvento extends Rutas {

    private ControladorEvento controlador = new ControladorEvento();

    @Override
    public void registrar() {

        Spark.get(RutasConstantes.FORMULARIO_ALTA_EVENTOS,
                controlador::getEventoPage,
                templateEngine);

        Spark.post(RutasConstantes.FORMULARIO_ALTA_EVENTOS,
                controlador::guardarEvento);

    }
}
