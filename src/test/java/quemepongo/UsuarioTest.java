package quemepongo;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.Set;

import org.junit.Test;

import quemepongo.model.Atuendo;
import quemepongo.model.FabricadorTipoAccesorio;
import quemepongo.model.FabricadorTipoCalzado;
import quemepongo.model.FabricadorTipoInferior;
import quemepongo.model.FabricadorTipoSuperior;
import quemepongo.model.Guardarropa;
import quemepongo.model.Material;
import quemepongo.model.Prenda;
import quemepongo.model.TipoPrenda;
import quemepongo.model.Usuario;

/**
 * Test de la construcción y el uso de la clase Prenda
 */
public class UsuarioTest {
	
	@Test
	public void usuariosUsandoGuardarropas() {
		final TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior());
	    final TipoPrenda POLLERA = TipoPrenda.diseniarTipo(new FabricadorTipoInferior());
	    final TipoPrenda REMERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperior());
	    final TipoPrenda MUSCULOSA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperior());
	    final TipoPrenda CAMISA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperior());
	    final TipoPrenda ZAPATILLAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado());
	    final TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado());
	    final TipoPrenda ANTEOJOS = TipoPrenda.diseniarTipo(new FabricadorTipoAccesorio());

		Usuario usuario1 = new Usuario();
		Usuario usuario2 = new Usuario();
		Guardarropa guardarropa1 = new Guardarropa();
		Guardarropa guardarropa2 = new Guardarropa();
		Guardarropa guardarropa3 = new Guardarropa();
		guardarropa1.agregarPrendaInferior(new Prenda.Builder()
				.setTipoPrenda(JEAN)
				.setMaterial(Material.OXFORD)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa2.agregarPrendaInferior(new Prenda.Builder()
				.setTipoPrenda(POLLERA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
		guardarropa3.agregarPrendaInferior(new Prenda.Builder()
				.setTipoPrenda(JEAN)
				.setMaterial(Material.OXFORD)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa1.agregarPrendaSuperior(new Prenda.Builder()
				.setTipoPrenda(REMERA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa2.agregarPrendaSuperior(new Prenda.Builder()
				.setTipoPrenda(REMERA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa2.agregarPrendaSuperior(new Prenda.Builder()
				.setTipoPrenda(MUSCULOSA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa3.agregarPrendaSuperior(new Prenda.Builder()
				.setTipoPrenda(CAMISA)
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa1.agregarCalzado(new Prenda.Builder()
				.setTipoPrenda(ZAPATILLAS)
				.setMaterial(Material.LONA)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa2.agregarCalzado(new Prenda.Builder()
				.setTipoPrenda(ZAPATILLAS)
				.setMaterial(Material.LONA)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa2.agregarCalzado(new Prenda.Builder()
				.setTipoPrenda(BOTAS)
				.setMaterial(Material.CUERO)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa3.agregarCalzado(new Prenda.Builder()
				.setTipoPrenda(ZAPATILLAS)
				.setMaterial(Material.LONA)
				.setColorPrincipal(Color.BLACK)
				.build());
        guardarropa3.agregarAccesorio(new Prenda.Builder()
				.setTipoPrenda(ANTEOJOS)
				.setMaterial(Material.PLASTICO)
				.setColorPrincipal(Color.BLACK)
				.build());
		usuario1.agregarGuardarropa(guardarropa1);
		usuario1.agregarGuardarropa(guardarropa2);
		usuario2.agregarGuardarropa(guardarropa3);
		Set<Atuendo> sugerenciasUsuario1 = usuario1.sugerencias();
		// En el guardarropa 1 hay sólo una prenda de cada categoría
		// En el guardarropa 2 hay sólo una prenda inferior, dos superiores y dos calzados
		assertEquals(1 + 1*2*2, sugerenciasUsuario1.size());
		Set<Atuendo> sugerenciasUsuario2 = usuario2.sugerencias();
		// En el guardarropa 3 hay sólo una prenda inferior, una superior, un calzado y un accesorio
		assertEquals(1, sugerenciasUsuario2.size());
	}

}