package quemepongo.server.rutas;

import quemepongo.server.controlador.ControladorGuardarropas;

import static spark.Spark.get;

public class RutasGuardarropa extends Rutas {

    private ControladorGuardarropas controlador = new ControladorGuardarropas();

    @Override
    public void registrar() {

        get(RutasConstantes.GUARDARROPA_URL,
                controlador::prendasByGuardarropaId,
                templateEngine);

        get(RutasConstantes.GUARDARROPAS_URL,
                controlador::guardarropas,
                templateEngine);
    }
}
