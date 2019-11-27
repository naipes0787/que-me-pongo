package quemepongo.server.controlador.prendas;

import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import quemepongo.dominio.prenda.CreadorDePrenda;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.List;

public class ControladorWizardPrenda {

    private List<FormularioPrenda> pasos = Lists.newArrayList(
            new FormularioPrendaTipo(),
            new FormularioPrendaDetalles(),
            new FormularioPrendaGuardar()
    );

    public ModelAndView formulario(Request req, Response resp) {
        return obtenerFormulario(req).vista(req, borradorPrenda(req));
    }

    public Void guardar(Request req, Response res) {
        FormularioPrenda formulario = obtenerFormulario(req);
        formulario.guardar(req, borradorPrenda(req));
        formulario.avanzar(req, res);
        return null;
    }

    public FormularioPrenda obtenerFormulario(Request req) {
        int pasoActual = req.queryParams("paso") == null || !StringUtils.isNumeric(req.queryParams("paso")) ? 1 : Integer.parseInt(req.queryParams("paso"));
        return pasos.stream().filter(p -> pasoActual == p.getPaso()).findFirst().orElseGet(() -> pasos.get(0));
    }

    protected CreadorDePrenda borradorPrenda(Request req) {
        if (req.session().attribute("borrador_prenda") == null) {
            req.session().attribute("borrador_prenda", new CreadorDePrenda());
        }
        return req.session().attribute("borrador_prenda");
    }

}
