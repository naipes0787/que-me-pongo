package quemepongo.server.rutas;

import quemepongo.server.controlador.ControladorGuardarropas;

import static spark.Spark.get;

public class RutasGuardarropas extends Rutas {

    private ControladorGuardarropas controlador = new ControladorGuardarropas();

    @Override
    public void registrar() {

        get(RutasConstantes.PRENDAS_URL,
                controlador::prendasByGuardarropaId,
                templateEngine);

        get(RutasConstantes.GUARDARROPAS_URL,
                controlador::guardarropas,
                templateEngine);
    }
}
