package quemepongo.server.rutas;

import quemepongo.server.controlador.prendas.ControladorPrendas;
import static spark.Spark.*;

public class RutasPrendas extends Rutas {

    private ControladorPrendas controladorPrendas = new ControladorPrendas();

    @Override
    public void registrar() {
        get(RutasConstantes.FORMULARIO_ALTA_PRENDAS,
                controladorPrendas::getBuilderPrendaForm,
                templateEngine);

        post(RutasConstantes.PRENDAS_URL,
                controladorPrendas::guardarPrenda);

    }
}
