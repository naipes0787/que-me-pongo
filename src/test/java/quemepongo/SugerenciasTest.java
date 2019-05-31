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
	
	private static final TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(20));
    private static final TipoPrenda CARGO = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(15));
    private static final TipoPrenda POLLERA = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(10));
    private static final TipoPrenda CALZA = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(18));

    private static final TipoPrenda REMERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10));
    private static final TipoPrenda MUSCULOSA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(5));
    private static final TipoPrenda CAMISA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(15));

    private static final TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10));
    private static final TipoPrenda BORCEGOS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(15));
    private static final TipoPrenda ZAPATILLAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(5));

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

        temperatura = new Temperatura();
        temperatura.setTemperatura(20.0);
    }
/*
    @Test
    public void guardarropaSinAccesorios() {
        Set<Atuendo> sugerencias = guardarropa.sugerencias();
        assertEquals(4 * 3 * 3, sugerencias.size());
        sugerencias.forEach(atuendo -> {
            prendaTieneCategoriaEsperada(atuendo.getPrendaSuperior(), Categoria.PRENDA_SUPERIOR);
            prendaTieneCategoriaEsperada(atuendo.getPrendaInferior(), Categoria.PRENDA_INFERIOR);
            prendaTieneCategoriaEsperada(atuendo.getCalzado(), Categoria.CALZADO);
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
            prendaTieneCategoriaEsperada(atuendo.getPrendaSuperior(), Categoria.PRENDA_SUPERIOR);
            prendaTieneCategoriaEsperada(atuendo.getPrendaInferior(), Categoria.PRENDA_INFERIOR);
            prendaTieneCategoriaEsperada(atuendo.getCalzado(), Categoria.CALZADO);
            prendaTieneCategoriaEsperada(atuendo.getAccesorio(), Categoria.ACCESORIO);
        });
    }*/

    private void prendaTieneCategoriaEsperada(Prenda prenda, Categoria categoriaEsperada) {
        assertNotNull(prenda);
        assertEquals(categoriaEsperada, prenda.getCategoria());
    }

}
