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

    public FormularioPrendaDetalles() {
        super(2);
    }

    @Override
    protected Map<String, Object> datosVista(Request req, CreadorDePrenda borradorPrenda) {
        return new HashMap<String, Object>() {{
            put("materiales", opciones(borradorPrenda.getMaterial(), borradorPrenda.getTipoPrenda().getMaterialesValidos()));
            put("coloresPrincipales", opciones(borradorPrenda.getColorPrincipal(), Color.getTodos()));
            put("coloresSecundarios", opciones(borradorPrenda.getColorSecundario(), Color.getTodos()));
            put("materialSeleccionado", borradorPrenda.getMaterial());
            put("colorPrincipalSeleccionado", borradorPrenda.getColorPrincipal());
            put("colorSecundarioSeleccionado", borradorPrenda.getColorSecundario());
        }};
    }

    @Override
    public void guardar(Request req, CreadorDePrenda borradorPrenda) {
        borradorPrenda
            .setMaterial(Material.valueOf(req.queryParams("material")))
            .setColorPrincipal(Color.valueOf(req.queryParams("color_principal")));

        if (StringUtils.isNotBlank(req.queryParams("color_secundario"))){
            borradorPrenda.setColorSecundario(Color.valueOf(req.queryParams("color_secundario")));
        }
    }

    @Override
    public void avanzar(Request req, Response res) {
        res.redirect(urlPaso(req,3));
    }

    protected <T> Collection<T> opciones(@Nullable T seleccionado, Collection<T> opciones) {
        if (seleccionado != null) {
            return opciones.stream().filter(e -> !e.equals(seleccionado)).collect(Collectors.toList());
        }
        return opciones;
    }
}
