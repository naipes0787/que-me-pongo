package quemepongo;
import org.junit.Test;

import quemepongo.model.Categoria;
import quemepongo.model.Material;
import quemepongo.model.FabricadorTipoAccesorio;
import quemepongo.model.FabricadorTipoCalzado;
import quemepongo.model.FabricadorTipoInferior;
import quemepongo.model.TipoPrenda;
import quemepongo.model.FabricadorTipoSuperior;


import static org.junit.Assert.*;

public class TipoPrendaTest {

    @Test
    public void tipoPrendaSuperior(){
        TipoPrenda remera = TipoPrenda.diseniarTipo(new FabricadorTipoSuperior());
        assertEquals(remera.getCategoria(), Categoria.PRENDA_SUPERIOR);
        assertTipoPrenda(remera, Material.ALGODON, Material.BRONCE);
    }

    @Test
    public void tipoPrendaInferior(){
        TipoPrenda pantalon = TipoPrenda.diseniarTipo(new FabricadorTipoInferior());
        assertEquals(pantalon.getCategoria(), Categoria.PRENDA_INFERIOR);
        assertTipoPrenda(pantalon, Material.OXFORD, Material.BRONCE);
    }

    @Test
    public void tipoCalzado(){
        TipoPrenda zapatos = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado());
        assertEquals(zapatos.getCategoria(), Categoria.CALZADO);
        assertTipoPrenda(zapatos, Material.CUERO, Material.BRONCE);
    }

    @Test
    public void tipoAccesorio(){
        TipoPrenda cadenita = TipoPrenda.diseniarTipo(new FabricadorTipoAccesorio());
        assertEquals(cadenita.getCategoria(), Categoria.ACCESORIO);
        assertTipoPrenda(cadenita, Material.ORO, Material.LANA);
    }

    private void assertTipoPrenda(TipoPrenda tipo, Material materialValido, Material materialInvalido) {
        assertTrue(tipo.getMaterialesValidos().contains(materialValido));
        assertFalse(tipo.getMaterialesValidos().contains(materialInvalido));
    }

}
