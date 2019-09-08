package quemepongo.model.prenda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TipoPrendaTest {

    @Test
    public void siSeGeneraUnaPrendaSuperior_SeCreaConLaCategoriaYMaterialesCorrectos(){
        TipoPrenda remera = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(12));
        assertEquals(remera.getCategoria(), Categoria.PRENDA_SUPERIOR);
        tipoPrendaContieneMaterialesValidos(remera, Material.ALGODON, Material.BRONCE);
    }

    @Test
    public void siSeGeneraUnaPrendaInferior_SeCreaConLaCategoriaYMaterialesCorrectos(){
        TipoPrenda pantalon = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(20));
        assertEquals(pantalon.getCategoria(), Categoria.PRENDA_INFERIOR);
        tipoPrendaContieneMaterialesValidos(pantalon, Material.OXFORD, Material.BRONCE);
    }

    @Test
    public void siSeGeneraUnCalzado_SeCreaConLaCategoriaYMaterialesCorrectos(){
        TipoPrenda zapatos = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10));
        assertEquals(zapatos.getCategoria(), Categoria.CALZADO);
        tipoPrendaContieneMaterialesValidos(zapatos, Material.CUERO, Material.BRONCE);
    }


    private void tipoPrendaContieneMaterialesValidos(TipoPrenda tipo, Material materialValido, Material materialInvalido) {
        assertTrue(tipo.getMaterialesValidos().contains(materialValido));
        assertFalse(tipo.getMaterialesValidos().contains(materialInvalido));
    }
}
