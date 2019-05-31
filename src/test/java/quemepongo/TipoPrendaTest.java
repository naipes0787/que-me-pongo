package quemepongo;
import org.junit.Test;

import quemepongo.model.Categoria;
import quemepongo.model.Material;
import quemepongo.model.FabricadorTipoAccesorio;
import quemepongo.model.FabricadorTipoCalzado;
import quemepongo.model.FabricadorTipoInferior;
import quemepongo.model.TipoPrenda;
import quemepongo.model.FabricadorTipoSuperiorBase;


import static org.junit.Assert.*;

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

    @Test
    public void tipoAccesorio(){
        TipoPrenda cadenita = TipoPrenda.diseniarTipo(new FabricadorTipoAccesorio());
        assertEquals(cadenita.getCategoria(), Categoria.ACCESORIO);
        tipoPrendaContieneMaterialesValidos(cadenita, Material.ORO, Material.LANA);
    }

    private void tipoPrendaContieneMaterialesValidos(TipoPrenda tipo, Material materialValido, Material materialInvalido) {
        assertTrue(tipo.getMaterialesValidos().contains(materialValido));
        assertFalse(tipo.getMaterialesValidos().contains(materialInvalido));
    }

}
