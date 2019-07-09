package quemepongo.model.sugerencia;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import quemepongo.model.Temperatura;
import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.prenda.CreadorDePrenda;
import quemepongo.model.prenda.FabricadorTipoCalzado;
import quemepongo.model.prenda.FabricadorTipoInferior;
import quemepongo.model.prenda.FabricadorTipoSuperiorBase;
import quemepongo.model.prenda.Material;
import quemepongo.model.prenda.TipoPrenda;
import quemepongo.model.usuario.Usuario;

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
    	Usuario usuario = new Usuario();
        Set<Atuendo> sugerencias = guardarropa.sugerencias(usuario, temperatura.convertirANivelDeAbrigo());
        assertEquals(4 * 3 * 3, sugerencias.size());
    }



}
