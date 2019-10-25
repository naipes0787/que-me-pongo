package quemepongo.persistencia;

import org.junit.Assert;
import org.junit.Test;
import quemepongo.config.TestBase;
import quemepongo.dominio.prenda.*;

public class RepositorioPrendaTest extends TestBase {

    @Test
    public void siSeGuardaUnaPrenda_SeAsignaIdCorrectamente(){
        RepositorioPrenda.instancia().guardar(JeanDeOxfordNegro);
        Assert.assertNotNull(JeanDeOxfordNegro.getId());
    }

    @Test
    public void siSeGuardaUnaPrenda_PuedeSerObtenidoDeLaDB(){
        RepositorioPrenda.instancia().guardar(PolleraDeAlgodonNegra);
        Prenda prendaRecuperada = RepositorioPrenda.instancia().obtenerPrenda(PolleraDeAlgodonNegra.getId());
        Assert.assertEquals(prendaRecuperada.getCapa(), PolleraDeAlgodonNegra.getCapa());
        Assert.assertEquals(prendaRecuperada.getCategoria(), PolleraDeAlgodonNegra.getCategoria());
    }

    @Test
    public void siSeGuardaUnaPrendaConFoto_LaFotoVieneDeLaDB(){
        Prenda prenda = new CreadorDePrenda()
                .setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoInferior(15)))
                .setMaterial(Material.ALGODON)
                .setColorPrincipal(Color.NEGRO)
                .setColorSecundario(Color.BLANCO)
                .setUrlFoto("./src/test/resources/test-foto.jpg")
                .build();
        RepositorioPrenda.instancia().guardar(prenda);
        Prenda prendaRecuperada = RepositorioPrenda.instancia().obtenerPrenda(prenda.getId());
        Assert.assertEquals(prendaRecuperada.getCapa(), prenda.getCapa());
        Assert.assertEquals(prendaRecuperada.getCategoria(), prenda.getCategoria());
    }

    @Test
    public void siSeGuardaUnaPrenda_PuedeSerEliminadaDeLaDB(){
        RepositorioPrenda.instancia().guardar(MusculosaDeAlgodonNegra);
        RepositorioPrenda.instancia().borrar(MusculosaDeAlgodonNegra);
        Assert.assertEquals(RepositorioPrenda.instancia().obtenerPrenda(MusculosaDeAlgodonNegra.getId()), null);
    }

}
