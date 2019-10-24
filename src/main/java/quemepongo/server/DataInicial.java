package quemepongo.server;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.prenda.*;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.persistencia.RepositorioGuardarropa;
import quemepongo.persistencia.RepositorioUsuario;

public class DataInicial implements WithGlobalEntityManager, TransactionalOps {

    private RepositorioGuardarropa repositorioGuardarropa = RepositorioGuardarropa.instancia();
    private RepositorioUsuario repositorioUsuario = RepositorioUsuario.instancia();

    public void cargar() {
        withTransaction(() -> {
            Guardarropa guardarropa = cargarGuardarropa();
            cargarUsuario(guardarropa);
        });
    }

    private Guardarropa cargarGuardarropa() {
        Guardarropa guardarropa = new Guardarropa("Guardarropa informal");
        guardarropa.agregarPrenda(
            new CreadorDePrenda()
                .setNombre("Campera de cuero")
                .setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorAbrigo(50)))
                .setMaterial(Material.CUERO)
                .setColorPrincipal(Color.MARRON)
//                .setUrlFoto("/home/utn/Desktop/test-foto.jpg")
                .build()
        );
        repositorioGuardarropa.guardar(guardarropa);
        return guardarropa;
    }

    private void cargarUsuario(Guardarropa guardarropa) {
        Usuario usuarioObtenido = repositorioUsuario.getUsuarioByUsername("usuario1");
        // Si usuario1 no está cargado, es porque nunca se ejecuto la carga inicial de datos y se procede a insertar
        if (usuarioObtenido == null) {
            Usuario usuario1 = new Usuario("usuario1", "pass");
            usuario1.agregarGuardarropa(guardarropa);
            Usuario usuario2 = new Usuario("usuario2", "pass");
            usuario2.agregarGuardarropa(guardarropa);
            repositorioUsuario.guardar(usuario1);
            repositorioUsuario.guardar(usuario2);
        }
    }

}
