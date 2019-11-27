package quemepongo.server.rutas;

import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioGuardarropa;
import quemepongo.server.controlador.ControladorGuardarropas;

import static quemepongo.util.RequestUtils.obtenerUsuario;
import static quemepongo.util.RequestUtils.parsearId;
import static spark.Spark.before;
import static spark.Spark.get;

public class RutasGuardarropas extends Rutas {

    private ControladorGuardarropas controlador = new ControladorGuardarropas();

    @Override
    public void registrar() {

        get(RutasConstantes.PRENDAS_URL,
                controlador::prendasByGuardarropaId,
                templateEngine);

        before(RutasConstantes.PRENDAS_URL, (req, res) -> {
            Usuario usuario = obtenerUsuario(req);
            Guardarropa guardarropa = RepositorioGuardarropa.instancia().buscarPorId(parsearId(req));
            if (!usuario.tieneGuardarropa(guardarropa)) {
                throw new RuntimeException("El guardarropa " + guardarropa.getId() + " no pertenece al usuario " + usuario.getId());
            }
        });
    }
}
