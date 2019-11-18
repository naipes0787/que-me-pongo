package quemepongo.server.rutas;

import quemepongo.server.controlador.ControladorQuienesSomos;
import static spark.Spark.get;

public class RutasQuienesSomos extends Rutas {

    private ControladorQuienesSomos controlador = new ControladorQuienesSomos();

    @Override
    public void registrar() {

        get(RutasConstantes.QUIENES_SOMOS_URL,
                controlador::quienesSomos,
                templateEngine);

    }
}
