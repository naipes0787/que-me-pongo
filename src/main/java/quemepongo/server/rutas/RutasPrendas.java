package quemepongo.server.rutas;

import quemepongo.persistencia.RepositorioGuardarropa;
import quemepongo.server.controlador.prendas.ControladorWizardPrenda;

import static quemepongo.util.RequestUtils.parsearId;
import static spark.Spark.*;

public class RutasPrendas extends Rutas {

    private ControladorWizardPrenda controladorPrendas = new ControladorWizardPrenda();

    @Override
    public void registrar() {
        get(RutasConstantes.FORMULARIO_ALTA_PRENDAS,
                controladorPrendas::formulario,
                templateEngine);

        post(RutasConstantes.PRENDAS_URL,
                controladorPrendas::guardar);

        before(RutasConstantes.FORMULARIO_ALTA_PRENDAS,
                (req, res) -> RepositorioGuardarropa.instancia().buscarPorId(parsearId(req)));

    }
}
