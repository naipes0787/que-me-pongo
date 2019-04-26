package quemepongo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.awt.Color;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import quemepongo.model.Atuendo;
import quemepongo.model.Categoria;
import quemepongo.model.Guardarropa;
import quemepongo.model.Material;
import quemepongo.model.Prenda;
import quemepongo.model.TipoAccesorio;
import quemepongo.model.TipoCalzado;
import quemepongo.model.TipoInferior;
import quemepongo.model.TipoPrenda;
import quemepongo.model.TipoSuperior;

public class SugerenciasTest {

	Guardarropa guardarropa = new Guardarropa();
	
	private static final TipoPrenda JEAN = TipoPrenda.diseniarTipo(new TipoInferior());
    private static final TipoPrenda CARGO = TipoPrenda.diseniarTipo(new TipoInferior());
    private static final TipoPrenda POLLERA = TipoPrenda.diseniarTipo(new TipoInferior());
    private static final TipoPrenda CALZA = TipoPrenda.diseniarTipo(new TipoInferior());

    private static final TipoPrenda REMERA = TipoPrenda.diseniarTipo(new TipoSuperior());
    private static final TipoPrenda MUSCULOSA = TipoPrenda.diseniarTipo(new TipoSuperior());
    private static final TipoPrenda CAMISA = TipoPrenda.diseniarTipo(new TipoSuperior());

    private static final TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new TipoCalzado());
    private static final TipoPrenda BORCEGOS = TipoPrenda.diseniarTipo(new TipoCalzado());
    private static final TipoPrenda ZAPATILLAS = TipoPrenda.diseniarTipo(new TipoCalzado());

    private static final TipoPrenda ANTEOJOS = TipoPrenda.diseniarTipo(new TipoAccesorio());
    private static final TipoPrenda PULSERA = TipoPrenda.diseniarTipo(new TipoAccesorio());
    
    @Before
    public void executeBeforeEachTest() {
    	guardarropa = new Guardarropa();
        guardarropa.agregarPrendaInferior(new Prenda.Builder()
				.setTipoPrenda(JEAN)
				.setMaterial(Material.OXFORD)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrendaInferior(new Prenda.Builder()
				.setTipoPrenda(CARGO)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrendaInferior(new Prenda.Builder()
				.setTipoPrenda(CALZA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrendaInferior(new Prenda.Builder()
				.setTipoPrenda(POLLERA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrendaSuperior(new Prenda.Builder()
				.setTipoPrenda(REMERA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrendaSuperior(new Prenda.Builder()
				.setTipoPrenda(MUSCULOSA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarPrendaSuperior(new Prenda.Builder()
				.setTipoPrenda(CAMISA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarCalzado(new Prenda.Builder()
				.setTipoPrenda(BOTAS)
				.setMaterial(Material.CUERO)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarCalzado(new Prenda.Builder()
				.setTipoPrenda(BORCEGOS)
				.setMaterial(Material.CUERO)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarCalzado(new Prenda.Builder()
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
            assertPrenda(atuendo.getPrendaSuperior(), Categoria.PRENDA_SUPERIOR);
            assertPrenda(atuendo.getPrendaInferior(), Categoria.PRENDA_INFERIOR);
            assertPrenda(atuendo.getCalzado(), Categoria.CALZADO);
        });
    }

    @Test
    public void guardarropaConAccesorios() {
        guardarropa.agregarAccesorio(new Prenda.Builder()
				.setTipoPrenda(ANTEOJOS)
				.setMaterial(Material.PLASTICO)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa.agregarAccesorio(new Prenda.Builder()
				.setTipoPrenda(PULSERA)
				.setMaterial(Material.PLATA)
				.setColorPrincipal(Color.GRAY)
				.build());
        Set<Atuendo> sugerencias = guardarropa.sugerenciasConAccesorios();
        assertEquals(4 * 3 * 3 * 2, sugerencias.size());

        sugerencias.forEach(atuendo -> {
            assertPrenda(atuendo.getPrendaSuperior(), Categoria.PRENDA_SUPERIOR);
            assertPrenda(atuendo.getPrendaInferior(), Categoria.PRENDA_INFERIOR);
            assertPrenda(atuendo.getCalzado(), Categoria.CALZADO);
            assertPrenda(atuendo.getAccesorio(), Categoria.ACCESORIO);
        });
    }

    private void assertPrenda(Prenda prenda, Categoria categoriaEsperada) {
        assertNotNull(prenda);
        assertEquals(categoriaEsperada, prenda.getCategoria());
    }

}
