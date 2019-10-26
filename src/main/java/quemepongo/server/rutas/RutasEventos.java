package quemepongo.server.rutas;

import quemepongo.server.controlador.evento.ControladorEventos;

import static spark.Spark.get;
import static spark.Spark.post;

public class RutasEventos extends Rutas {

    private ControladorEventos controlador = new ControladorEventos();

    @Override
    public void registrar() {

        get(RutasConstantes.FORMULARIO_ALTA_EVENTOS,
                controlador::formulario,
                templateEngine);

        post(RutasConstantes.EVENTOS_URL,
                controlador::guardarEvento);

        get(RutasConstantes.EVENTOS_URL,
                controlador::eventosByUsuarioId,
                templateEngine);

        get(RutasConstantes.EVENTO_URL,
                controlador::mostrarEvento,
                templateEngine);

    }
}
