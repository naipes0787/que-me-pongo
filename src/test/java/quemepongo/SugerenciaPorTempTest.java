package quemepongo;

import org.junit.Before;
import org.junit.Test;
import quemepongo.model.*;

import java.awt.*;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class SugerenciaPorTempTest {

    private static final TipoPrenda MUSCULOSA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10));
    private static final TipoPrenda CAMISA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10));

    private static final TipoPrenda SUDADERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorIntermedio(40));
    private static final TipoPrenda SWETER = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorIntermedio(40));

    private static final TipoPrenda CAMPERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorUltimaPrenda(60));
    private static final TipoPrenda MONGOMERY = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorUltimaPrenda(80));

    private static final TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(20));
    private static final TipoPrenda JOGGIN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(20));

    private static final TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(15));
    private static final TipoPrenda BORCEGOS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(15));

    private static Prenda Musculosa;
    private static Prenda Camisa;

    private static Prenda Sudadera;
    private static Prenda Sweter;

    private static Prenda Campera;
    private static Prenda Mongomery;

    private static Prenda Jean;
    private static Prenda Joggin;

    private static Prenda Botas;
    private static Prenda Borcegos;

    @Before
    public void ejecutarAntesDeCadaTest() {
        Jean = new CreadorDePrenda()
                .setTipoPrenda(JEAN)
                .setMaterial(Material.OXFORD)
                .setColorPrincipal(Color.BLACK)
                .build();
        Joggin = new CreadorDePrenda()
                .setTipoPrenda(JOGGIN)
                .setMaterial(Material.OXFORD)
                .setColorPrincipal(Color.BLACK)
                .build();
        Musculosa = new CreadorDePrenda()
                .setTipoPrenda(MUSCULOSA)
                .setMaterial(Material.ALGODON)
                .setColorPrincipal(Color.BLACK)
                .build();
        Camisa = new CreadorDePrenda()
                .setTipoPrenda(CAMISA)
                .setMaterial(Material.ALGODON)
                .setColorPrincipal(Color.BLACK)
                .build();
        Botas = new CreadorDePrenda()
                .setTipoPrenda(BOTAS)
                .setMaterial(Material.CUERO)
                .setColorPrincipal(Color.BLACK)
                .build();
        Sudadera = new CreadorDePrenda()
                .setTipoPrenda(SUDADERA)
                .setMaterial(Material.ALGODON)
                .setColorPrincipal(Color.BLACK)
                .build();
        Sweter = new CreadorDePrenda()
                .setTipoPrenda(SWETER)
                .setMaterial(Material.LINO)
                .setColorPrincipal(Color.BLACK)
                .build();
        Campera = new CreadorDePrenda()
                .setTipoPrenda(CAMPERA)
                .setMaterial(Material.ALGODON)
                .setColorPrincipal(Color.BLACK)
                .build();
        Mongomery = new CreadorDePrenda()
                .setTipoPrenda(MONGOMERY)
                .setMaterial(Material.GABARDINA)
                .setColorPrincipal(Color.BLACK)
                .build();
        Borcegos = new CreadorDePrenda()
                .setTipoPrenda(BORCEGOS)
                .setMaterial(Material.CUERO)
                .setColorPrincipal(Color.BLACK)
                .build();
    }

    @Test
    public void sugerenciasCon3Capas() {

        Temperatura temperatura = new Temperatura(10.0);
    /**
     *  Se comienza teniendo solo una combinación posible.
     */
        Guardarropa guardarropa = new Guardarropa();
        guardarropa.agregarPrenda(Camisa);
        guardarropa.agregarPrenda(Sudadera);
        guardarropa.agregarPrenda(Campera);
        guardarropa.agregarPrenda(Jean);
        guardarropa.agregarPrenda(Borcegos);

        Set<Atuendo> sugerencias = guardarropa.sugerencias(temperatura);

    /**
     *  Se verifica que solo haya encontrado un resultado.
     */
        assertEquals((1 * 1 * 1) * 1 * 1, sugerencias.size());
    /**
     *  Se verifica que esta combinación tenga 5 prendas en total (3 superiores, 1 inferior, 1 calzado).
     */
        assertEquals( 3 + 1 + 1, sugerencias.stream().mapToInt(atuendo -> atuendo.getCantidadPrendas()).sum());
    /**
     * Se agrega una prenda superior base con el mismo nivel de abrigo, por lo que la cantidad de combinaciones se duplica
     */
        guardarropa.agregarPrenda(Musculosa);
        Set<Atuendo> sugerencias2 = guardarropa.sugerencias(temperatura);
        assertEquals((2 * 1 * 1) * 1 * 1, sugerencias2.size());
     /**
     * * Se agrega una prenda superior intermedia. Ahora habrán 4 posibilidades de combinacione superiores
      *   camisa + sweter + campera
      *   camisa + sudadera + campera
      *   musculosa + sweter + campera
      *   musclosa + sudadera + campera
     */
        guardarropa.agregarPrenda(Sweter);
        Set<Atuendo> sugerencias3 = guardarropa.sugerencias(temperatura);
        assertEquals((2 * 2 * 1) * 1 * 1, sugerencias3.size());
    /**
     * * Se agrega una prenda superior Ultima. Ahora habrán 8 posibilidades de combinacione superiores.
     * Las aneriores más:
     *   camisa + sweter + mongomery
     *   camisa + sudadera + mongomery
     *   musculosa + sweter + mongomery
     *   musclosa + sudadera + mongomery
     */
        guardarropa.agregarPrenda(Mongomery);
        Set<Atuendo> sugerencias4 = guardarropa.sugerencias(temperatura);
        assertEquals((2 * 2* 2) * 1 * 1, sugerencias4.size());
    /**
     * * Se agrega una prenda inferior con la cual todas las combinaciones anteriores también funcionarán
     */
        guardarropa.agregarPrenda(Joggin);
        Set<Atuendo> sugerencias5 = guardarropa.sugerencias(temperatura);
        assertEquals((2 * 2* 2) * 2 * 1, sugerencias5.size());
    /**
     * * Se agrega un calzado con la cual todas las combinaciones anteriores también funcionarán
     */
        guardarropa.agregarPrenda(Botas);
        Set<Atuendo> sugerencias6 = guardarropa.sugerencias(temperatura);
        assertEquals((2 * 2* 2) * 2 * 2, sugerencias6.size());
    }

    @Test
    public void sugerenciasUnicaCapa() {
        Temperatura temperatura = new Temperatura(31.0);

        Guardarropa guardarropa = new Guardarropa();
        guardarropa.agregarPrenda(Camisa);
        guardarropa.agregarPrenda(Musculosa);
        guardarropa.agregarPrenda(Sudadera);
        guardarropa.agregarPrenda(Mongomery);
        guardarropa.agregarPrenda(Campera);
        guardarropa.agregarPrenda(Jean);
        guardarropa.agregarPrenda(Joggin);
        guardarropa.agregarPrenda(Botas);
        guardarropa.agregarPrenda(Borcegos);
        /**
         * * Se verifica que, como hace calor, no abrigará con prendas camperas, sudaderas, mongomerys o sweters.
         */
        Set<Atuendo> sugerencias = guardarropa.sugerencias(temperatura);
        assertTrue(sugerencias.stream().allMatch(atuendo-> atuendo.getPrendasSuperiores().getCantPrendas() == 1));
        /**
         * * Ademas la cantidad de atuendos sugeridos son 8:
         * musculosa + jean + botas
         * musculosa + jean + borcegos
         * musculosa + joggin + botas
         * musculosa + joggin + borcegos
         * camisa + jean + botas
         * camisa + jean + borcegos
         * camisa + joggin + botas
         * camisa + joggin + borcegos
         */
        assertEquals(2 * 2 * 2, sugerencias.size());
    }

}
