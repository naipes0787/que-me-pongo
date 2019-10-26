package quemepongo.server.rutas;

import quemepongo.server.controlador.ControladorSugerencias;
import spark.Spark;

public class RutasSugerencias extends Rutas {

    private ControladorSugerencias controlador = new ControladorSugerencias();

    @Override
    public void registrar() {

        Spark.get(RutasConstantes.SUGERIR_URL,
                controlador::obtenerAtuendos,
                templateEngine);

        Spark.post(RutasConstantes.SUGERIR_URL,
                controlador::aceptarAtuendo);

    }
}
