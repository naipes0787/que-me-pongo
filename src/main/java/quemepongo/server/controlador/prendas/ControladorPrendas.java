package quemepongo.server.controlador.prendas;

import org.apache.commons.lang.StringUtils;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

public class ControladorPrendas {

    public ModelAndView formulario(Request req, Response resp) {
        return wizard(req)
                .obtenerFormulario(numeroDePaso(req))
                .vista(req);
    }

    public Void guardar(Request req, Response res) {
        FormularioPrenda formulario = wizard(req).obtenerFormulario(numeroDePaso(req));
        formulario.guardar(req, res);
        formulario.siguiente(req, res);
        return null;
    }

    private int numeroDePaso(Request req) {
        return req.queryParams("paso") == null || !StringUtils.isNumeric(req.queryParams("paso")) ? 1 : Integer.parseInt(req.queryParams("paso"));
    }

    private WizardPrenda wizard(Request req) {
        if (req.session().attribute("wizard_prenda") == null) {
            req.session().attribute("wizard_prenda", new WizardPrenda());
        }
        return req.session().attribute("wizard_prenda");
    }

}
