package quemepongo.persistencia;

import org.junit.Assert;
import org.junit.Test;
import quemepongo.config.AtuendoTestConfig;
import quemepongo.model.sugerencia.Atuendo;

import java.util.List;

public class RepositorioAtuendoTest extends AtuendoTestConfig {

    @Test
    public void siSeGuardaUnAtuendo_SeAsignaIdCorrectamente(){
        RepositorioAtuendo.instancia().guardar(atuendo);
        Assert.assertNotNull(atuendo.getId());
    }

    @Test
    public void siSeGuardaUnAtuendo_PuedeSerObtenidoDeLaDB(){
        RepositorioAtuendo.instancia().guardar(atuendo);
        List<Atuendo> listaAtuendos = RepositorioAtuendo.instancia().getAtuendo();
        Assert.assertTrue(listaAtuendos.contains(atuendo));
    }

}
