package quemepongo;

import org.junit.Before;
import org.junit.Test;
import quemepongo.model.*;

import java.awt.Color;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class SugerenciasTest {

	Guardarropa guardarropa = new Guardarropa();
	
	private static final TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(10));
    private static final TipoPrenda CARGO = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(10));
    private static final TipoPrenda POLLERA = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(10));
    private static final TipoPrenda CALZA = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(10));

    private static final TipoPrenda REMERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10));
    private static final TipoPrenda MUSCULOSA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10));
    private static final TipoPrenda CAMISA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10));

    private static final TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10));
    private static final TipoPrenda BORCEGOS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10));
    private static final TipoPrenda ZAPATILLAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10));

    private static final TipoPrenda ANTEOJOS = TipoPrenda.diseniarTipo(new FabricadorTipoAccesorio());
    private static final TipoPrenda PULSERA = TipoPrenda.diseniarTipo(new FabricadorTipoAccesorio());

    private Temperatura temperatura;
    
    @Before
    public void ejecutarAntesDeCadaTest() {
    	guardarropa = new Guardarropa();
        guardarropa.agregarPrenda(new CreadorDePrenda()
				.setTipoPrenda(JEAN)
				.setMaterial(Material.OXFORD)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrenda(new CreadorDePrenda()
				.setTipoPrenda(CARGO)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrenda(new CreadorDePrenda()
				.setTipoPrenda(CALZA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrenda(new CreadorDePrenda()
				.setTipoPrenda(POLLERA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrenda(new CreadorDePrenda()
				.setTipoPrenda(REMERA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrenda(new CreadorDePrenda()
				.setTipoPrenda(MUSCULOSA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrenda(new CreadorDePrenda()
				.setTipoPrenda(CAMISA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrenda(new CreadorDePrenda()
				.setTipoPrenda(BOTAS)
				.setMaterial(Material.CUERO)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrenda(new CreadorDePrenda()
				.setTipoPrenda(BORCEGOS)
				.setMaterial(Material.CUERO)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrenda(new CreadorDePrenda()
				.setTipoPrenda(ZAPATILLAS)
				.setMaterial(Material.LONA)
				.setColorPrincipal(Color.BLACK)
				.build());

        temperatura = new Temperatura(10.0);
    }

    @Test
    public void guardarropaSinAccesorios() {
        Set<Atuendo> sugerencias = guardarropa.sugerencias(temperatura);
        assertEquals(4 * 3 * 3, sugerencias.size());
    }

    @Test
    public void guardarropaConAccesorios() {
        guardarropa.agregarPrenda(new CreadorDePrenda()
				.setTipoPrenda(ANTEOJOS)
				.setMaterial(Material.PLASTICO)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrenda(new CreadorDePrenda()
				.setTipoPrenda(PULSERA)
				.setMaterial(Material.PLATA)
				.setColorPrincipal(Color.GRAY)
				.build());
        Set<Atuendo> sugerencias = guardarropa.sugerenciasConAccesorios(temperatura);
        assertEquals(4 * 3 * 3 * 2, sugerencias.size());
    }

}
