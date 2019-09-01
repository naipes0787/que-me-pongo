package quemepongo.config;

import com.google.common.collect.Sets;
import org.junit.Before;
import quemepongo.model.calificacion.Calificacion;
import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.guardarropa.GuardarropaCompartido;
import quemepongo.model.prenda.FabricadorTipoCalzado;
import quemepongo.model.prenda.TipoPrenda;
import quemepongo.model.usuario.Usuario;
import quemepongo.model.usuario.UsuarioPremium;

import static quemepongo.model.calificacion.OpcionesCalificacion.*;

public class UsuarioTestConfig extends TestConfigGeneral {

    //Calificaciones de Prueba
    protected static final Calificacion agradableGeneral = new Calificacion(AGRADABLE, AGRADABLE, AGRADABLE, AGRADABLE);
    protected static final Calificacion calurosoGeneral = new Calificacion(CALUROSO, CALUROSO, CALUROSO, CALUROSO);
    protected static final Calificacion congeladoGeneral = new Calificacion(CONGELADO, CONGELADO, CONGELADO, CONGELADO);
    protected static final Calificacion calurosoSoloCabeza = new Calificacion(AGRADABLE, AGRADABLE, AGRADABLE, CALUROSO);
    protected static final Calificacion calurosoSoloManos = new Calificacion(AGRADABLE, CALUROSO, AGRADABLE, AGRADABLE);
    protected static final Calificacion calurosoSoloCuello = new Calificacion(AGRADABLE, AGRADABLE, CALUROSO, AGRADABLE);
    protected static final Calificacion congeladoSoloCabeza = new Calificacion(AGRADABLE, AGRADABLE, AGRADABLE, CONGELADO);
    protected static final Calificacion congeladoSoloManos = new Calificacion(AGRADABLE, CONGELADO, AGRADABLE, AGRADABLE);
    protected static final Calificacion congeladoSoloCuello = new Calificacion(AGRADABLE, AGRADABLE, CONGELADO, AGRADABLE);
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
