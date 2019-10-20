package quemepongo.persistencia;

import org.junit.Before;
import org.junit.Test;
import quemepongo.config.TestBase;
import quemepongo.dominio.sugerencia.Atuendo;

import java.util.List;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class RepositorioAtuendoTest extends TestBase {

    private Atuendo atuendo;

    @Before
    public void inicializarAtuendo() {
        this.atuendo = atuendoBasico();
    }

    @Test
    public void siSeGuardaUnAtuendo_SeAsignaIdCorrectamente() {
        RepositorioAtuendo.instancia().guardar(atuendo);
        assertNotNull(atuendo.getId());
    }

    @Test
    public void siSeGuardaUnAtuendo_PuedeSerObtenidoDeLaDB(){
        RepositorioAtuendo.instancia().guardar(atuendo);
        List<Atuendo> listaAtuendos = RepositorioAtuendo.instancia().atuendos();
        assertTrue(listaAtuendos.contains(atuendo));
    }

}
