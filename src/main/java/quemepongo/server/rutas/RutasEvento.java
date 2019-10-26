package quemepongo.server.rutas;

import quemepongo.server.controlador.evento.ControladorEvento;

import static spark.Spark.get;
import static spark.Spark.post;

public class RutasEvento extends Rutas {

    private ControladorEvento controlador = new ControladorEvento();

    @Override
    public void registrar() {

        get(RutasConstantes.FORMULARIO_ALTA_EVENTOS,
                controlador::formulario,
                templateEngine);

        post(RutasConstantes.EVENTOS_URL,
                controlador::guardarEvento);

        get(RutasConstantes.EVENTO_URL,
                controlador::mostrarEvento,
                templateEngine);

    }
}
