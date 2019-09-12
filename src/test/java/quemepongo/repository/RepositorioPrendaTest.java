package quemepongo.repository;

import org.junit.Assert;
import org.junit.Test;
import quemepongo.config.TestConfigGeneral;
import quemepongo.model.prenda.*;

import java.awt.*;

public class RepositorioPrendaTest extends TestConfigGeneral {

    @Test
    public void siSeGuardaUnaPrenda_SeAsignaIdCorrectamente(){
        RepositorioPrenda repositorioPrenda = new RepositorioPrenda();
        repositorioPrenda.guardarPrenda(JeanDeOxfordNegro);
        Assert.assertNotNull(JeanDeOxfordNegro.getId());
    }

    @Test
    public void siSeGuardaUnaPrenda_PuedeSerObtenidoDeLaDB(){
        RepositorioPrenda repositorioPrenda = new RepositorioPrenda();
        repositorioPrenda.guardarPrenda(PolleraDeAlgodonNegra);
        Prenda prendaRecuperada = repositorioPrenda.obtenerPrenda(PolleraDeAlgodonNegra.getId());
        Assert.assertEquals(prendaRecuperada.getCapa(), PolleraDeAlgodonNegra.getCapa());
        Assert.assertEquals(prendaRecuperada.getCategoria(), PolleraDeAlgodonNegra.getCategoria());
    }

    @Test
    public void siSeGuardaUnaPrendaConFoto_LaFotoVieneDeLaDB(){
        RepositorioPrenda repositorioPrenda = new RepositorioPrenda();
        Prenda prenda = new CreadorDePrenda()
                .setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoInferior(15)))
                .setMaterial(Material.ALGODON)
                .setColorPrincipal(Color.BLACK)
                .setColorSecundario(Color.WHITE)
                .setFoto("./src/test/resources/test-foto.jpg")
                .build();
        repositorioPrenda.guardarPrenda(prenda);
        Prenda prendaRecuperada = repositorioPrenda.obtenerPrenda(prenda.getId());
        Assert.assertEquals(prendaRecuperada.getCapa(), prenda.getCapa());
        Assert.assertEquals(prendaRecuperada.getCategoria(), prenda.getCategoria());
    }

}
