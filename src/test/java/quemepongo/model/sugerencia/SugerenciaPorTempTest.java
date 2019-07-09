package quemepongo.model.sugerencia;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import quemepongo.model.Temperatura;
import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.prenda.CreadorDePrenda;
import quemepongo.model.prenda.FabricadorTipoCalzado;
import quemepongo.model.prenda.FabricadorTipoInferior;
import quemepongo.model.prenda.FabricadorTipoSuperiorAbrigo;
import quemepongo.model.prenda.FabricadorTipoSuperiorBase;
import quemepongo.model.prenda.Material;
import quemepongo.model.prenda.Prenda;
import quemepongo.model.prenda.TipoPrenda;
import quemepongo.model.usuario.Usuario;

public class SugerenciaPorTempTest {

    private static final TipoPrenda MUSCULOSA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(20));
    private static final TipoPrenda CAMISA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(20));

    private static final TipoPrenda CAMPERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorAbrigo(90));
    private static final TipoPrenda MONGOMERY = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorAbrigo(90));

    private static final TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(30));
    private static final TipoPrenda JOGGIN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(30));

    private static final TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(25));
    private static final TipoPrenda BORCEGOS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(25));

    private static Prenda Musculosa;
    private static Prenda Camisa;

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
        double nivelAbrigo = temperatura.convertirANivelDeAbrigo();
    /**
     *  Se comienza teniendo solo una combinación posible.
     */
        Guardarropa guardarropa = new Guardarropa();
        guardarropa.agregarPrenda(Camisa);
        guardarropa.agregarPrenda(Campera);
        guardarropa.agregarPrenda(Jean);
        guardarropa.agregarPrenda(Borcegos);

        Set<Atuendo> sugerencias = guardarropa.sugerencias(new Usuario(), nivelAbrigo);

    /**
     *  Se verifica que solo haya encontrado un resultado.
     */
        assertEquals((1 * 1 ) * 1 * 1, sugerencias.size());
    /**
     *  Se verifica que esta combinación tenga 5 prendas en total (3 superiores, 1 inferior, 1 calzado).
     */
        assertEquals( 2 + 1 + 1, sugerencias.stream().mapToInt(atuendo -> atuendo.getCantidadPrendas()).sum());
    /**
     * Se agrega una prenda superior base con el mismo nivel de abrigo, por lo que la cantidad de combinaciones se duplica
     */
        guardarropa.agregarPrenda(Musculosa);
        Set<Atuendo> sugerencias2 = guardarropa.sugerencias(new Usuario(), nivelAbrigo);
        assertEquals((2 * 1 ) * 1 * 1, sugerencias2.size());

    /**
     * * Se agrega una prenda superior Abrigo. Ahora habrán 8 posibilidades de combinacione superiores.
     * Las aneriores más:
     *   camisa + mongomery
     *   camisa + mongomery
     *   musculosa + mongomery
     *   musclosa + mongomery
     */
        guardarropa.agregarPrenda(Mongomery);
        Set<Atuendo> sugerencias4 = guardarropa.sugerencias(new Usuario(), nivelAbrigo);
        assertEquals((2 * 2) * 1 * 1, sugerencias4.size());
    /**
     * * Se agrega una prenda inferior con la cual todas las combinaciones anteriores también funcionarán
     */
        guardarropa.agregarPrenda(Joggin);
        Set<Atuendo> sugerencias5 = guardarropa.sugerencias(new Usuario(), nivelAbrigo);
        assertEquals((2 * 2) * 2 * 1, sugerencias5.size());
    /**
     * * Se agrega un calzado con la cual todas las combinaciones anteriores también funcionarán
     */
        guardarropa.agregarPrenda(Botas);
        Set<Atuendo> sugerencias6 = guardarropa.sugerencias(new Usuario(), nivelAbrigo);
        assertEquals((2 * 2) * 2 * 2, sugerencias6.size());
    }

    @Test
    public void sugerenciasUnicaCapa() {
        Temperatura temperatura = new Temperatura(31.0);
        double nivelAbrigo = temperatura.convertirANivelDeAbrigo();

        Guardarropa guardarropa = new Guardarropa();
        guardarropa.agregarPrenda(Camisa);
        guardarropa.agregarPrenda(Musculosa);
        guardarropa.agregarPrenda(Mongomery);
        guardarropa.agregarPrenda(Campera);
        guardarropa.agregarPrenda(Jean);
        guardarropa.agregarPrenda(Joggin);
        guardarropa.agregarPrenda(Botas);
        guardarropa.agregarPrenda(Borcegos);
        /**
         * * Se verifica que, como hace calor, no abrigará con prendas camperas, sudaderas, mongomerys o sweters.
         */
        Set<Atuendo> sugerencias = guardarropa.sugerencias(new Usuario(), nivelAbrigo);
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
