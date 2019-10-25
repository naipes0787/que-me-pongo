package quemepongo.server.rutas;

import quemepongo.server.controlador.ControladorGuardarropas;
import quemepongo.server.controlador.ControladorAtuendos;
import spark.Spark;

import static spark.Spark.get;

public class RutasSugerencias extends Rutas {

    private ControladorAtuendos controlador = new ControladorAtuendos();

    @Override
    public void registrar() {

        Spark.get(RutasConstantes.SUGERIR_URL,
                controlador::obtenerAtuendos,
                templateEngine);

        Spark.post(RutasConstantes.SUGERIR_URL,
                controlador::aceptarAtuendo);

    }
}
