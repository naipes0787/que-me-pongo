package quemepongo.model.prenda;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import quemepongo.model.prenda.Categoria;
import quemepongo.model.prenda.FabricadorTipoCalzado;
import quemepongo.model.prenda.FabricadorTipoInferior;
import quemepongo.model.prenda.FabricadorTipoSuperiorBase;
import quemepongo.model.prenda.Material;
import quemepongo.model.prenda.TipoPrenda;

public class TipoPrendaTest {

    @Test
    public void tipoPrendaSuperior(){
        TipoPrenda remera = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(12));
        assertEquals(remera.getCategoria(), Categoria.PRENDA_SUPERIOR);
        tipoPrendaContieneMaterialesValidos(remera, Material.ALGODON, Material.BRONCE);
    }

    @Test
    public void tipoPrendaInferior(){
        TipoPrenda pantalon = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(20));
        assertEquals(pantalon.getCategoria(), Categoria.PRENDA_INFERIOR);
        tipoPrendaContieneMaterialesValidos(pantalon, Material.OXFORD, Material.BRONCE);
    }

    @Test
    public void tipoCalzado(){
        TipoPrenda zapatos = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10));
        assertEquals(zapatos.getCategoria(), Categoria.CALZADO);
        tipoPrendaContieneMaterialesValidos(zapatos, Material.CUERO, Material.BRONCE);
    }


    private void tipoPrendaContieneMaterialesValidos(TipoPrenda tipo, Material materialValido, Material materialInvalido) {
        assertTrue(tipo.getMaterialesValidos().contains(materialValido));
        assertFalse(tipo.getMaterialesValidos().contains(materialInvalido));
    }

}
