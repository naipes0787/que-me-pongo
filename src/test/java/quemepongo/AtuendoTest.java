package quemepongo;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.Sets;

import quemepongo.model.Atuendo;
import quemepongo.model.CombinacionPrenda;
import quemepongo.model.CreadorDePrenda;
import quemepongo.model.FabricadorTipoCalzado;
import quemepongo.model.FabricadorTipoInferior;
import quemepongo.model.FabricadorTipoSuperiorBase;
import quemepongo.model.Material;
import quemepongo.model.Temperatura;
import quemepongo.model.TipoPrenda;

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
