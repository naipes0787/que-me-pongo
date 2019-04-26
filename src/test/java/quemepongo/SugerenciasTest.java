package quemepongo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Set;

import org.junit.Test;

import quemepongo.model.Atuendo;
import quemepongo.model.Categoria;
import quemepongo.model.Guardarropa;
import quemepongo.model.Prenda;
import quemepongo.model.TipoAccesorio;
import quemepongo.model.TipoCalzado;
import quemepongo.model.TipoInferior;
import quemepongo.model.TipoPrenda;
import quemepongo.model.TipoSuperior;

public class SugerenciasTest {

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
            assertPrenda(atuendo.getPrendaSuperior(), Categoria.PRENDA_SUPERIOR);
            assertPrenda(atuendo.getPrendaInferior(), Categoria.PRENDA_INFERIOR);
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
