package quemepongo.server;

import javafx.scene.paint.Color;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.prenda.CreadorDePrenda;
import quemepongo.dominio.prenda.FabricadorTipoSuperiorAbrigo;
import quemepongo.dominio.prenda.Material;
import quemepongo.dominio.prenda.TipoPrenda;
import quemepongo.persistencia.RepositorioGuardarropa;

public class DataInicial implements WithGlobalEntityManager, TransactionalOps {

    private RepositorioGuardarropa repositorioGuardarropa = RepositorioGuardarropa.instancia();

    public void cargar() {
        withTransaction(() -> {
            cargarGuardarropa();
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

}
