package quemepongo.persistencia;

import org.junit.Assert;
import org.junit.Test;
import quemepongo.config.TestConfigGeneral;
import quemepongo.model.prenda.*;

import java.awt.*;

public class RepositorioPrendaTest extends TestConfigGeneral {

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
                .setColorPrincipal(Color.BLACK)
                .setColorSecundario(Color.WHITE)
                .setFoto("./src/test/resources/test-foto.jpg")
                .build();
        RepositorioPrenda.instancia().guardar(prenda);
        Prenda prendaRecuperada = RepositorioPrenda.instancia().obtenerPrenda(prenda.getId());
        Assert.assertEquals(prendaRecuperada.getCapa(), prenda.getCapa());
        Assert.assertEquals(prendaRecuperada.getCategoria(), prenda.getCategoria());
    }

}
