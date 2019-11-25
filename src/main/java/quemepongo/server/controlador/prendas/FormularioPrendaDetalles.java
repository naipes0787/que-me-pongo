package quemepongo.server.controlador.prendas;

import org.apache.commons.lang3.StringUtils;
import quemepongo.dominio.prenda.Color;
import quemepongo.dominio.prenda.CreadorDePrenda;
import quemepongo.dominio.prenda.Material;
import spark.Request;
import spark.Response;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class FormularioPrendaDetalles extends FormularioPrenda {

    public FormularioPrendaDetalles(CreadorDePrenda creadorPrenda) {
        super(2, creadorPrenda);
    }

    @Override
    protected Map<String, Object> datosVista(Request req) {
        return new HashMap<String, Object>() {{
            put("materiales", opciones(creadorPrenda.getMaterial(), creadorPrenda.getTipoPrenda().getMaterialesValidos()));
            put("coloresPrincipales", opciones(creadorPrenda.getColorPrincipal(), Color.getTodos()));
            put("coloresSecundarios", opciones(creadorPrenda.getColorSecundario(), Color.getTodos()));
            put("materialSeleccionado", creadorPrenda.getMaterial());
            put("colorPrincipalSeleccionado", creadorPrenda.getColorPrincipal());
            put("colorSecundarioSeleccionado", creadorPrenda.getColorSecundario());
        }};
    }

    @Override
    public void guardar(Request req, Response res) {
        creadorPrenda
            .setMaterial(Material.valueOf(req.queryParams("material")))
            .setColorPrincipal(Color.valueOf(req.queryParams("color_principal")));

        if (StringUtils.isNotBlank(req.queryParams("color_secundario"))){
            creadorPrenda.setColorSecundario(Color.valueOf(req.queryParams("color_secundario")));
        }
    }

    @Override
    public void siguiente(Request req, Response res) {
        res.redirect(urlPaso(req,3));
    }

    protected <T> Collection<T> opciones(@Nullable T seleccionado, Collection<T> opciones) {
        if (seleccionado != null) {
            return opciones.stream().filter(e -> !e.equals(seleccionado)).collect(Collectors.toList());
        }
        return opciones;
    }
}
