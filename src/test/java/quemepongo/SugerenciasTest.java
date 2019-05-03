package quemepongo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import quemepongo.model.*;

public class SugerenciasTest {

	Guardarropa guardarropa = new Guardarropa();
	
	private static final TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior());
    private static final TipoPrenda CARGO = TipoPrenda.diseniarTipo(new FabricadorTipoInferior());
    private static final TipoPrenda POLLERA = TipoPrenda.diseniarTipo(new FabricadorTipoInferior());
    private static final TipoPrenda CALZA = TipoPrenda.diseniarTipo(new FabricadorTipoInferior());

    private static final TipoPrenda REMERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperior());
    private static final TipoPrenda MUSCULOSA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperior());
    private static final TipoPrenda CAMISA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperior());

    private static final TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado());
    private static final TipoPrenda BORCEGOS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado());
    private static final TipoPrenda ZAPATILLAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado());

    private static final TipoPrenda ANTEOJOS = TipoPrenda.diseniarTipo(new FabricadorTipoAccesorio());
    private static final TipoPrenda PULSERA = TipoPrenda.diseniarTipo(new FabricadorTipoAccesorio());
    
    @Before
    public void ejecutarAntesDeCadaTest() {
    	guardarropa = new Guardarropa();
        guardarropa.agregarPrendaInferior(new CreadorDePrenda()
				.setTipoPrenda(JEAN)
				.setMaterial(Material.OXFORD)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrendaInferior(new CreadorDePrenda()
				.setTipoPrenda(CARGO)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrendaInferior(new CreadorDePrenda()
				.setTipoPrenda(CALZA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrendaInferior(new CreadorDePrenda()
				.setTipoPrenda(POLLERA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrendaSuperior(new CreadorDePrenda()
				.setTipoPrenda(REMERA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrendaSuperior(new CreadorDePrenda()
				.setTipoPrenda(MUSCULOSA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrendaSuperior(new CreadorDePrenda()
				.setTipoPrenda(CAMISA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarCalzado(new CreadorDePrenda()
				.setTipoPrenda(BOTAS)
				.setMaterial(Material.CUERO)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarCalzado(new CreadorDePrenda()
				.setTipoPrenda(BORCEGOS)
				.setMaterial(Material.CUERO)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarCalzado(new CreadorDePrenda()
				.setTipoPrenda(ZAPATILLAS)
				.setMaterial(Material.LONA)
				.setColorPrincipal(Color.BLACK)
				.build());
    }

    @Test
    public void guardarropaSinAccesorios() {
        Set<Atuendo> sugerencias = guardarropa.sugerencias();
        assertEquals(4 * 3 * 3, sugerencias.size());
        sugerencias.forEach(atuendo -> {
            verificarPrendar(atuendo.getPrendaSuperior(), Categoria.PRENDA_SUPERIOR);
            verificarPrendar(atuendo.getPrendaInferior(), Categoria.PRENDA_INFERIOR);
            verificarPrendar(atuendo.getCalzado(), Categoria.CALZADO);
        });
    }

    @Test
    public void guardarropaConAccesorios() {
        guardarropa.agregarAccesorio(new CreadorDePrenda()
				.setTipoPrenda(ANTEOJOS)
				.setMaterial(Material.PLASTICO)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarAccesorio(new CreadorDePrenda()
				.setTipoPrenda(PULSERA)
				.setMaterial(Material.PLATA)
				.setColorPrincipal(Color.GRAY)
				.build());
        Set<Atuendo> sugerencias = guardarropa.sugerenciasConAccesorios();
        assertEquals(4 * 3 * 3 * 2, sugerencias.size());

        sugerencias.forEach(atuendo -> {
            verificarPrendar(atuendo.getPrendaSuperior(), Categoria.PRENDA_SUPERIOR);
            verificarPrendar(atuendo.getPrendaInferior(), Categoria.PRENDA_INFERIOR);
            verificarPrendar(atuendo.getCalzado(), Categoria.CALZADO);
            verificarPrendar(atuendo.getAccesorio(), Categoria.ACCESORIO);
        });
    }

    private void verificarPrendar(Prenda prenda, Categoria categoriaEsperada) {
        assertNotNull(prenda);
        assertEquals(categoriaEsperada, prenda.getCategoria());
    }

}
