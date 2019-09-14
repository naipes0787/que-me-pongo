package quemepongo.config;

import com.google.common.collect.Sets;
import org.junit.Before;
import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.prenda.FabricadorTipoCalzado;
import quemepongo.model.prenda.TipoPrenda;
import quemepongo.model.usuario.Usuario;
import quemepongo.model.usuario.UsuarioPremium;

public class UsuarioTestConfig extends GuardarropaCompartidoTestConfig {

    // Límites en guardarropas
    protected static final int CANTIDAD_LIMITE_PRENDAS_USUARIO_GRATUITO = 25;
    protected static final int CANTIDAD_PRENDAS_EJEMPLO_PREMIUM = 200;

    private static final TipoPrenda PANTUFLAS_CORTE_FINO_DE_RINOCERONTE_ALBINO = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10));

    //Usuario de Prueba
    protected Usuario johnnyBravo;
    protected Usuario montgomeryBurns;

    //Guardarropa
    protected Guardarropa guardarropa;
    protected Guardarropa guardarropaCompartidoEntreJohnnyYBurns;

    @Before
    public void ejecutarAntesDeCadaTest() {
        johnnyBravo = usuarioBasico();
        montgomeryBurns = new Usuario(new UsuarioPremium());
        guardarropa = guardarropaCon();
        Guardarropa g = new Guardarropa();
        g.setUsuarios(Sets.newHashSet(johnnyBravo, montgomeryBurns));
        johnnyBravo.agregarGuardarropa(g);
        montgomeryBurns.agregarGuardarropa(g);
        guardarropaCompartidoEntreJohnnyYBurns = g;
    }

    protected void johnnyAgregaUnaPredaNueva(Guardarropa guardarropa) {
        johnnyBravo.agregarPrenda(crearPrenda(REMERA), guardarropa);
    }

    protected void burnsAgregaUnaPredaNueva(Guardarropa guardarropa) {
        montgomeryBurns.agregarPrenda(crearPrenda(PANTUFLAS_CORTE_FINO_DE_RINOCERONTE_ALBINO), guardarropa);
    }
}
