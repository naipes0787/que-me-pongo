package quemepongo;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

import quemepongo.exceptions.ColoresRepetidosException;
import quemepongo.exceptions.MaterialInvalidoException;
import quemepongo.model.Categoria;
import quemepongo.model.FabricadorTipoCalzado;
import quemepongo.model.FabricadorTipoInferior;
import quemepongo.model.FabricadorTipoSuperior;
import quemepongo.model.Material;
import quemepongo.model.Prenda;
import quemepongo.model.TipoPrenda;

/**
 * Test de la construcción y el uso de la clase Prenda
 */
public class PrendaTest {
	
	/**
	 * Al crear prendas sin tipo se arroja {@link NullPointerException}
	 */
	@Test(expected = NullPointerException.class)
	@SuppressWarnings("unused")
	public void buildPrendaWithoutTipo() {
		Prenda prenda = new Prenda.Builder()
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build();
	}
	
	/**
	 * Al crear prendas sin color principal {@link NullPointerException}
	 */
	@Test(expected = NullPointerException.class)
	@SuppressWarnings("unused")
	public void buildPrendaWithoutMaterial() {
		Prenda prenda = new Prenda.Builder()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoSuperior()))
				.setColorPrincipal(Color.BLACK)
				.build();
	}
	
	/**
	 * Al crear prendas sin material {@link NullPointerException}
	 */
	@Test(expected = NullPointerException.class)
	@SuppressWarnings("unused")
	public void buildPrendaWithoutColorPrincipal() {
		Prenda prenda = new Prenda.Builder()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoSuperior()))
				.setMaterial(Material.ALGODON)
				.build();
	}
	
	/**
	 * No se permiten crear prendas con materiales inválidos
	 */
	@Test(expected = MaterialInvalidoException.class)
	@SuppressWarnings("unused")
	public void buildPrendaInvalida() {
		Prenda prenda = new Prenda.Builder()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoCalzado()))
				.setMaterial(Material.SEDA)
				.setColorPrincipal(Color.BLACK)
				.build();
	}
	
	/**
	 * No se permiten crear prendas con el mismo color como principal y secundario
	 */
	@Test(expected = ColoresRepetidosException.class)
	public void buildPrendaMismosColores() {
		Prenda prenda = new Prenda.Builder()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoInferior()))
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.setColorSecundario(Color.BLACK)
				.build();
		assertEquals(prenda.getCategoria(), Categoria.PRENDA_INFERIOR);
	}
	
	/**
	 * Se permite crear prendas sin color secundario
	 */
	@Test
	public void buildPrendaWithoutColorSecundario() {
		Prenda prenda = new Prenda.Builder()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoSuperior()))
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build();
		assertEquals(prenda.getCategoria(), Categoria.PRENDA_SUPERIOR);
	}
	
	/**
	 * Se permite crear prendas completas con colores distintos
	 */
	@Test
	public void buildPrendaCompleta() {
		Prenda prenda = new Prenda.Builder()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoInferior()))
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.setColorSecundario(Color.WHITE)
				.build();
		assertEquals(prenda.getCategoria(), Categoria.PRENDA_INFERIOR);
	}

}