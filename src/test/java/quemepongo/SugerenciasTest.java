package quemepongo;

import org.junit.Assert;
import org.junit.Test;
import quemepongo.model.*;

import java.util.Arrays;

public class SugerenciasTest {

    private static final TipoPrenda JEAN = new TipoPrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.values()));
    private static final TipoPrenda CARGO = new TipoPrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.values()));
    private static final TipoPrenda POLLERA = new TipoPrenda(Categoria.PARTE_INFERIOR, Arrays.asList(Material.values()));

    private static final TipoPrenda REMERA = new TipoPrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.values()));
    private static final TipoPrenda MUSCULOSA = new TipoPrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.values()));
    private static final TipoPrenda SWEATER = new TipoPrenda(Categoria.PARTE_SUPERIOR, Arrays.asList(Material.values()));

    private static final TipoPrenda BOTAS = new TipoPrenda(Categoria.CALZADO, Arrays.asList(Material.values()));
    private static final TipoPrenda BORCEGOS = new TipoPrenda(Categoria.CALZADO, Arrays.asList(Material.values()));
    private static final TipoPrenda ZAPATILLAS = new TipoPrenda(Categoria.CALZADO, Arrays.asList(Material.values()));

    private static final TipoPrenda ANTEOJOS = new TipoPrenda(Categoria.ACCESORIOS, Arrays.asList(Material.values()));
    private static final TipoPrenda PULSERA = new TipoPrenda(Categoria.ACCESORIOS, Arrays.asList(Material.values()));

    private static Guardarropa guardarropaSinAccesorios;

    static {
        guardarropaSinAccesorios = new Guardarropa();
        guardarropaSinAccesorios.agregarPrenda(new Prenda(JEAN, null, null, null, null));
        guardarropaSinAccesorios.agregarPrenda(new Prenda(CARGO, null, null, null, null));
        guardarropaSinAccesorios.agregarPrenda(new Prenda(POLLERA, null, null, null, null));
        guardarropaSinAccesorios.agregarPrenda(new Prenda(REMERA, null, null, null, null));
        guardarropaSinAccesorios.agregarPrenda(new Prenda(MUSCULOSA, null, null, null, null));
        guardarropaSinAccesorios.agregarPrenda(new Prenda(SWEATER, null, null, null, null));
        guardarropaSinAccesorios.agregarPrenda(new Prenda(BOTAS, null, null, null, null));
        guardarropaSinAccesorios.agregarPrenda(new Prenda(BORCEGOS, null, null, null, null));
        guardarropaSinAccesorios.agregarPrenda(new Prenda(ZAPATILLAS, null, null, null, null));
    }

    @Test
    public void guardarropa_sin_accesorios() {
        Usuario usuario = new Usuario();
        usuario.agregarGuardarropa(guardarropaSinAccesorios);
        usuario.sugerencias().forEach(a -> Assert.assertEquals(3, a.partes()));
    }

}
