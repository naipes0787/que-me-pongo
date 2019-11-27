package quemepongo.server.controlador.prendas;

import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.prenda.CreadorDePrenda;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioGuardarropa;
import quemepongo.server.rutas.RutasConstantes;
import quemepongo.util.MultipartFormData;
import quemepongo.util.RepositorioImagenes;
import spark.Request;
import spark.Response;

import java.util.HashMap;
import java.util.Map;

import static quemepongo.util.RequestUtils.obtenerUsuario;
import static quemepongo.util.RequestUtils.parsearId;

public class FormularioPrendaGuardar extends FormularioPrenda {

    public FormularioPrendaGuardar() {
        super(3);
    }

    @Override
    protected Map<String, Object> datosVista(Request req, CreadorDePrenda borradorPrenda) {
        return new HashMap<>();
    }

    @Override
    public void guardar(Request req, CreadorDePrenda borradorPrenda) {
        MultipartFormData data = new MultipartFormData(req);
        borradorPrenda.setNombre(data.get("nombre"));

        if (data.get("foto") != null) {
            String urlFoto = RepositorioImagenes.instancia().subir(data.get("foto"));
            borradorPrenda.setUrlFoto(urlFoto);
        }
        Usuario usuario = obtenerUsuario(req);
        Guardarropa guardarropa = RepositorioGuardarropa.instancia().buscarPorId(parsearId(req));
        usuario.agregarPrenda(borradorPrenda.build(), guardarropa);
    }

    @Override
    public void avanzar(Request req, Response res) {
        req.session().removeAttribute("borrador_prenda");
        res.status(201);
        res.redirect(RutasConstantes.PRENDAS_URL.replace(":id", req.params("id")));
    }
}