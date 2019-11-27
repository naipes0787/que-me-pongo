package quemepongo.server.controlador.prendas;

import quemepongo.dominio.prenda.CreadorDePrenda;
import quemepongo.dominio.prenda.TipoPrenda;
import quemepongo.persistencia.RepositorioTipoPrenda;
import spark.Request;
import spark.Response;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FormularioPrendaTipo extends FormularioPrenda {

    public FormularioPrendaTipo() {
        super(1);
    }

    @Override
    protected Map<String, Object> datosVista(Request req, CreadorDePrenda borradorPrenda) {
        return new HashMap<String, Object>() {{
            put("tipos", opciones(borradorPrenda.getTipoPrenda(), RepositorioTipoPrenda.instancia().getTipos()));
            put("tipoSeleccionado", borradorPrenda.getTipoPrenda());
        }};
    }

    private List<TipoPrenda> opciones(TipoPrenda seleccionado, List<TipoPrenda> tipos) {
        if (seleccionado != null) {
            return tipos.stream().filter(e -> !e.getId().equals(seleccionado.getId())).collect(Collectors.toList());
        }
        return tipos;
    }

    @Override
    public void guardar(Request req, CreadorDePrenda borradorPrenda) {
        borradorPrenda.setTipoPrenda(
            RepositorioTipoPrenda.instancia().buscarPorId(Long.valueOf(req.queryParams("tipo")))
        );
    }

    @Override
    public void avanzar(Request req, Response res) {
        res.redirect(urlPaso(req, 2));
    }
}