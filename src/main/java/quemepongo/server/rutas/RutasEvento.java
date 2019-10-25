package quemepongo.server.rutas;

import quemepongo.server.controlador.ControladorEvento;
import spark.Spark;

import static quemepongo.server.rutas.RutasConstantes.EVENTO_URL;
import static spark.Spark.get;
import static spark.Spark.post;

public class RutasEvento extends Rutas {

    private ControladorEvento controlador = new ControladorEvento();

    @Override
    public void registrar() {

        get(RutasConstantes.FORMULARIO_EVENTOS_URL,
                controlador::formulario,
                templateEngine);

        post(RutasConstantes.FORMULARIO_EVENTOS_URL,
                controlador::guardarEvento);

        get(EVENTO_URL,
                controlador::mostrarEvento,
                templateEngine);

    }
}
