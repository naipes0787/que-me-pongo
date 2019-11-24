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

public class FormularioPrendaPaso1 extends FormularioPrenda {

    public FormularioPrendaPaso1(CreadorDePrenda creadorPrenda) {
        super(1, creadorPrenda);
    }

    @Override
    protected Map<String, Object> datosVista(Request req) {
        return new HashMap<String, Object>() {{
            put("tipos", opciones(creadorPrenda.getTipoPrenda(), RepositorioTipoPrenda.instancia().getTipos()));
            put("tipoSeleccionado", creadorPrenda.getTipoPrenda());
        }};
    }

    private List<TipoPrenda> opciones(TipoPrenda seleccionado, List<TipoPrenda> tipos) {
        if (seleccionado != null) {
            return tipos.stream().filter(e -> !e.getId().equals(seleccionado.getId())).collect(Collectors.toList());
        }
        return tipos;
    }

    @Override
    public void guardar(Request req, Response res) {
        creadorPrenda.setTipoPrenda(
            RepositorioTipoPrenda.instancia().buscarPorId(Long.valueOf(req.queryParams("tipo")))
        );
    }

    @Override
    public void siguiente(Request req, Response res) {
        res.redirect(urlPaso(req, 2));
    }
}