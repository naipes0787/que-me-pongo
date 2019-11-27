package quemepongo.server.controlador.prendas;

import quemepongo.dominio.prenda.CreadorDePrenda;
import quemepongo.server.rutas.RutasConstantes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.Map;

import static quemepongo.util.RequestUtils.parsearId;

public abstract class FormularioPrenda {

    private int paso;

    public FormularioPrenda(int pasoActual) {
        this.paso = pasoActual;
    }

    public ModelAndView vista(Request req, CreadorDePrenda borradorPrenda) {
        Map<String, Object> datosVista = datosVista(req, borradorPrenda);
        datosVista.put("guardarropaId", parsearId(req));
        return new ModelAndView(datosVista, "wizard_prenda/wizard_prenda_paso_" + paso + ".hbs");
    }

    protected abstract Map<String, Object> datosVista(Request req, CreadorDePrenda borradorPrenda);

    public abstract void guardar(Request req, CreadorDePrenda borradorPrenda);

    public abstract void avanzar(Request req, Response res);

    public int getPaso() {
        return paso;
    }

    protected String urlPaso(Request request, int paso) {
        return RutasConstantes.FORMULARIO_ALTA_PRENDAS.replace(":id", request.params("id")) + "?paso=" + paso;
    }

}

