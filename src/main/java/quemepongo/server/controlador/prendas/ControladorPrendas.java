package quemepongo.server.controlador.prendas;

import quemepongo.dominio.FactorClimatico;
import quemepongo.dominio.prenda.*;
import quemepongo.persistencia.RepositorioPrenda;
import quemepongo.server.controlador.Controlador;
import quemepongo.server.rutas.RutasConstantes;
import spark.ModelAndView;
import spark.Request;
import spark.Response;

import java.util.LinkedHashSet;


public class ControladorPrendas implements Controlador {

    public ModelAndView getBuilderPrendaForm(Request req, Response resp){
        VistaPrenda vistaPrenda = new VistaPrenda(parsearId(req));
        return new ModelAndView(vistaPrenda,"formulario_prenda.hbs");
    }

    public Void guardarPrenda(Request req, Response res) {
        LinkedHashSet<Material> materiales = new LinkedHashSet<>();
        materiales.add(Material.valueOf(req.queryParams("material")));
        LinkedHashSet<FactorClimatico> factoresClimaticos = new LinkedHashSet<>();
        factoresClimaticos.add(FactorClimatico.valueOf(req.queryParams("efectividad")));
        TipoPrenda tipoPrenda = new TipoPrenda(
                Categoria.valueOf(req.queryParams("categoria")),
                materiales,
                Long.valueOf(req.queryParams("nivel-abrigo")),
                Capa.valueOf(req.queryParams("capa")),
                factoresClimaticos
        );
        Prenda prenda = new Prenda(
                req.queryParams("nombre"),
                tipoPrenda,
                Material.valueOf(req.queryParams("material")),
                Color.valueOf(req.queryParams("color-principal")),
                Color.valueOf(req.queryParams("color-secundario")),
                null
        );
        RepositorioPrenda.instancia().guardar(prenda);
        res.status(201);
        res.redirect(RutasConstantes.GUARDARROPAS_URL);
        return null;
    }

}
