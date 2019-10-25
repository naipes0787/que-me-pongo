package quemepongo.server.rutas;

import quemepongo.server.controlador.prendas.ControladorPrendas;
import static spark.Spark.*;

public class RutasPrendas extends Rutas {

    private ControladorPrendas controladorPrendas = new ControladorPrendas();

    @Override
    public void registrar() {
        get(RutasConstantes.NUEVAS_PRENDAS_URL,
                controladorPrendas::getBuilderPrendaForm,
                templateEngine);
    }
}
