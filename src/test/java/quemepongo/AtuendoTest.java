package quemepongo;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import quemepongo.model.*;
import quemepongo.model.prenda.CombinacionPrenda;
import quemepongo.model.prenda.CreadorDePrenda;
import quemepongo.model.prenda.FabricadorTipoCalzado;
import quemepongo.model.prenda.FabricadorTipoInferior;
import quemepongo.model.prenda.FabricadorTipoSuperiorBase;
import quemepongo.model.prenda.Material;
import quemepongo.model.prenda.TipoPrenda;

import java.awt.Color;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test de la Atuendo
 */
public class AtuendoTest {

    private static final TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(15));
    private static final TipoPrenda REMERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10));
    private static final TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(7));

    private Atuendo atuendo;
	
    @Before
    public void setup() {
        CombinacionPrenda jean = new CombinacionPrenda(Sets.newHashSet(new CreadorDePrenda()
                                                .setTipoPrenda(JEAN)
                                                .setMaterial(Material.OXFORD)
                                                .setColorPrincipal(Color.BLACK)
                                                .build()));
        CombinacionPrenda remera = new CombinacionPrenda(Sets.newHashSet(new CreadorDePrenda()
                .setTipoPrenda(REMERA)
                .setMaterial(Material.ALGODON)
                .setColorPrincipal(Color.BLACK)
                .build()));

        CombinacionPrenda botas = new CombinacionPrenda(Sets.newHashSet(new CreadorDePrenda()
                .setTipoPrenda(BOTAS)
                .setMaterial(Material.CUERO)
                .setColorPrincipal(Color.BLACK)
                .build()));
        atuendo = new Atuendo(remera, jean, botas);
    }
	
	@Test
	public void atuendoAbrigaLoSuficiente() {
		Temperatura temperatura = new Temperatura(34D);;
		/* El atuendo brinda 32 de nivel de abrigo (15+10+7), necesitaría 
		 * 27 como mínimo y 33 como máximo, así que daría TRUE */
		assertTrue(atuendo.abrigaLoSuficiente(temperatura, 0.1D));
	}
	
	@Test
	public void atuendoNoAbrigaLoSuficiente() {
		Temperatura temperatura = new Temperatura(25D);;
		/* El atuendo brinda 32 de nivel de abrigo (15+10+7), necesitaría 
		 * 67.5 como mínimo y 82.5 como máximo, así que daría FALSE */
		assertFalse(atuendo.abrigaLoSuficiente(temperatura, 0.1D));
	}
	
	@Test
	public void obtenerCantidadPrendasCorrectamente() {
		assertTrue(atuendo.getCantidadPrendas() == 3);
	}

}
