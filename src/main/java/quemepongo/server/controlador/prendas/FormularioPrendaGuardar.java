package quemepongo.server.controlador.prendas;

import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.prenda.CreadorDePrenda;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioGuardarropa;
import quemepongo.server.rutas.RutasConstantes;
import quemepongo.util.MultipartFormData;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static quemepongo.util.RequestUtils.obtenerUsuario;
import static quemepongo.util.RequestUtils.parsearId;

public class FormularioPrendaGuardar extends FormularioPrenda {

    public FormularioPrendaGuardar(CreadorDePrenda creadorPrenda) {
        super(3, creadorPrenda);
    }

    @Override
    protected Map<String, Object> datosVista(Request req) {
        return new HashMap<>();
    }

    @Override
    public void guardar(Request req, Response res) {
        MultipartFormData data = new MultipartFormData(req);
        creadorPrenda.setNombre(data.get("nombre"));
        if (data.get("foto") != null) {
            creadorPrenda.setUrlFoto(data.get("foto"));
        }
        Usuario usuario = obtenerUsuario(req);
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