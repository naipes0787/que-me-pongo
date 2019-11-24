package quemepongo.server.controlador.prendas;

import org.apache.commons.lang.StringUtils;
import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.prenda.CreadorDePrenda;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioGuardarropa;
import quemepongo.server.rutas.RutasConstantes;
import quemepongo.util.RequestUtils;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static quemepongo.util.RequestUtils.parsearId;

public class FormularioPrendaPaso3 extends FormularioPrenda {

    public FormularioPrendaPaso3(CreadorDePrenda creadorPrenda) {
        super(3, creadorPrenda);
    }

    @Override
    protected Map<String, Object> datosVista(Request req) {
        return new HashMap<>();
    }

    @Override
    public void guardar(Request req, Response res) {
        creadorPrenda.setNombre(req.queryParams("nombre"));
        if (StringUtils.isNotBlank(req.queryParams("foto"))) {
            creadorPrenda.setUrlFoto(req.queryParams("foto"));
        }
        Usuario usuario = RequestUtils.obtenerUsuario(req);
        Guardarropa guardarropa = RepositorioGuardarropa.instancia().buscarPorId(parsearId(req));
        usuario.agregarPrenda(creadorPrenda.build(), guardarropa);
    }

    @Override
    public void siguiente(Request req, Response res) {
        req.session().removeAttribute("wizard_prenda");
        res.status(201);
        res.redirect(RutasConstantes.HOME_URL);
    }
}