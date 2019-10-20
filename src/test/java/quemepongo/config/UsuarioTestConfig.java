package quemepongo.config;

import com.google.common.collect.Sets;
import org.junit.Before;
import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.guardarropa.GuardarropaCompartido;
import quemepongo.dominio.prenda.FabricadorTipoCalzado;
import quemepongo.dominio.prenda.TipoPrenda;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.dominio.usuario.UsuarioPremium;

public class UsuarioTestConfig extends TestBase {

    // Límites en guardarropas
    protected static final int CANTIDAD_LIMITE_PRENDAS_USUARIO_GRATUITO = 25;
    protected static final int CANTIDAD_PRENDAS_EJEMPLO_PREMIUM = 200;

    private static final TipoPrenda PANTUFLAS_CORTE_FINO_DE_RINOCERONTE_ALBINO = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10));

    //Usuario de Prueba
    protected Usuario johnnyBravo;
    protected Usuario montgomeryBurns;

    //Guardarropa
    protected Guardarropa guardarropa;
    protected GuardarropaCompartido guardarropaCompartidoEntreJohnnyYBurns;

    @Before
    public void ejecutarAntesDeCadaTest() {
        johnnyBravo = usuarioBasico();
        montgomeryBurns = new Usuario(new UsuarioPremium());
        guardarropa = guardarropaCon();
        guardarropaCompartidoEntreJohnnyYBurns = new GuardarropaCompartido(Sets.newHashSet(johnnyBravo, montgomeryBurns));
    }

    protected void johnnyAgregaUnaPredaNueva(Guardarropa guardarropa) {
        johnnyBravo.agregarPrenda(crearPrenda(REMERA), guardarropa);
    }

    protected void burnsAgregaUnaPredaNueva(Guardarropa guardarropa) {
        montgomeryBurns.agregarPrenda(crearPrenda(PANTUFLAS_CORTE_FINO_DE_RINOCERONTE_ALBINO), guardarropa);
    }
}
