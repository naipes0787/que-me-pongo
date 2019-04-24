package quemepongo;

import org.junit.Test;
import quemepongo.model.*;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SugerenciasTest {

    private static final TipoPrenda JEAN = new TipoPrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.values()));
    private static final TipoPrenda CARGO = new TipoPrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.values()));
    private static final TipoPrenda POLLERA = new TipoPrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.values()));
    private static final TipoPrenda CALZA = new TipoPrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.values()));

    private static final TipoPrenda REMERA = new TipoPrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.values()));
    private static final TipoPrenda MUSCULOSA = new TipoPrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.values()));
    private static final TipoPrenda CAMISA = new TipoPrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.values()));

    private static final TipoPrenda BOTAS = new TipoPrenda(Categoria.CALZADO, Arrays.asList(Material.values()));
    private static final TipoPrenda BORCEGOS = new TipoPrenda(Categoria.CALZADO, Arrays.asList(Material.values()));
    private static final TipoPrenda ZAPATILLAS = new TipoPrenda(Categoria.CALZADO, Arrays.asList(Material.values()));

    private static final TipoPrenda ANTEOJOS = new TipoPrenda(Categoria.ACCESORIO, Arrays.asList(Material.values()));
    private static final TipoPrenda PULSERA = new TipoPrenda(Categoria.ACCESORIO, Arrays.asList(Material.values()));

    @Test
    public void guardarropaSinAccesorios() {
        Guardarropa guardarropa = new Guardarropa();
        guardarropa.agregarPrendaInferior(new Prenda(JEAN, null, null, null, null));
        guardarropa.agregarPrendaInferior(new Prenda(CARGO, null, null, null, null));
        guardarropa.agregarPrendaInferior(new Prenda(CALZA, null, null, null, null));
        guardarropa.agregarPrendaInferior(new Prenda(POLLERA, null, null, null, null));
        guardarropa.agregarPrendaSuperior(new Prenda(REMERA, null, null, null, null));
        guardarropa.agregarPrendaSuperior(new Prenda(MUSCULOSA, null, null, null, null));
        guardarropa.agregarPrendaSuperior(new Prenda(CAMISA, null, null, null, null));
        guardarropa.agregarCalzado(new Prenda(BOTAS, null, null, null, null));
        guardarropa.agregarCalzado(new Prenda(BORCEGOS, null, null, null, null));
        guardarropa.agregarCalzado(new Prenda(ZAPATILLAS, null, null, null, null));

        Set<Atuendo> sugerencias = guardarropa.sugerencias();
        assertEquals(4 * 3 * 3, sugerencias.size());
        sugerencias.forEach(atuendo -> {
            assertPrenda(atuendo.getPrendaSuperior(), Categoria.PARTE_SUPERIOR);
            assertPrenda(atuendo.getPrendaInferior(), Categoria.PARTE_INFERIOR);
            assertPrenda(atuendo.getCalzado(), Categoria.CALZADO);
        });
    }

    @Test
    public void guardarropaConAccesorios() {
        Guardarropa guardarropa = new Guardarropa();
        guardarropa.agregarPrendaInferior(new Prenda(JEAN, null, null, null, null));
        guardarropa.agregarPrendaInferior(new Prenda(CARGO, null, null, null, null));
        guardarropa.agregarPrendaInferior(new Prenda(CALZA, null, null, null, null));
        guardarropa.agregarPrendaInferior(new Prenda(POLLERA, null, null, null, null));
        guardarropa.agregarPrendaSuperior(new Prenda(REMERA, null, null, null, null));
        guardarropa.agregarPrendaSuperior(new Prenda(MUSCULOSA, null, null, null, null));
        guardarropa.agregarPrendaSuperior(new Prenda(CAMISA, null, null, null, null));
        guardarropa.agregarCalzado(new Prenda(BOTAS, null, null, null, null));
        guardarropa.agregarCalzado(new Prenda(BORCEGOS, null, null, null, null));
        guardarropa.agregarCalzado(new Prenda(ZAPATILLAS, null, null, null, null));
        guardarropa.agregarAccesorio(new Prenda(ANTEOJOS, null, null, null, null));
        guardarropa.agregarAccesorio(new Prenda(PULSERA, null, null, null, null));

        Set<Atuendo> sugerencias = guardarropa.sugerenciasConAccesorios();
        assertEquals(4 * 3 * 3 * 2, sugerencias.size());

        sugerencias.forEach(atuendo -> {
            assertPrenda(atuendo.getPrendaSuperior(), Categoria.PARTE_SUPERIOR);
            assertPrenda(atuendo.getPrendaInferior(), Categoria.PARTE_INFERIOR);
            assertPrenda(atuendo.getCalzado(), Categoria.CALZADO);
            assertPrenda(atuendo.getAccesorio(), Categoria.ACCESORIO);
        });
    }

    private void assertPrenda(Prenda prenda, Categoria categoriaEsperada) {
        assertNotNull(prenda);
        assertEquals(categoriaEsperada, prenda.getCategoria());
    }

}
