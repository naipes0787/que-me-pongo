package quemepongo.server;

import javafx.scene.paint.Color;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.prenda.CreadorDePrenda;
import quemepongo.dominio.prenda.FabricadorTipoSuperiorAbrigo;
import quemepongo.dominio.prenda.Material;
import quemepongo.dominio.prenda.TipoPrenda;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioGuardarropa;
import quemepongo.persistencia.RepositorioUsuario;

public class DataInicial implements WithGlobalEntityManager, TransactionalOps {

    private RepositorioGuardarropa repositorioGuardarropa = RepositorioGuardarropa.instancia();
    private RepositorioUsuario repositorioUsuario = RepositorioUsuario.instancia();

    public void cargar() {
        withTransaction(() -> {
            cargarGuardarropa();
            cargarUsuario();
        });
    }

    private void cargarGuardarropa() {
        Guardarropa guardarropa = new Guardarropa("Guardarropa informal");
        guardarropa.agregarPrenda(
            new CreadorDePrenda()
                .setNombre("Campera de cuero")
                .setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorAbrigo(50)))
                .setMaterial(Material.CUERO)
                .setColorPrincipal(Color.BROWN)
                .setUrlFoto("./src/main/resources/public/test-foto.jpg")
                .build()
        );
        repositorioGuardarropa.guardar(guardarropa);
    }

    private void cargarUsuario() {
        Usuario usuarioObtenido = repositorioUsuario.getUsuarioByUsername("usuario1");
        if (usuarioObtenido == null) {
            Usuario usuario1 = new Usuario("usuario1", "pass");
            Usuario usuario2 = new Usuario("usuario2", "pass");
            repositorioUsuario.guardar(usuario1);
            repositorioUsuario.guardar(usuario2);
        }
    }

}
