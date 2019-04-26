package quemepongo;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import quemepongo.model.Categoria;
import quemepongo.model.Material;
import quemepongo.model.Prenda;
import quemepongo.model.TipoInferior;
import quemepongo.model.TipoPrenda;
import quemepongo.model.TipoSuperior;
import quemepongo.model.Trama;

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
		List<Material> materiales = new ArrayList<>();
		materiales.add(Material.ALGODON);
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
		List<Material> materiales = new ArrayList<>();
		materiales.add(Material.ALGODON);
		Prenda prenda = new Prenda.Builder()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new TipoSuperior()))
				.setColorPrincipal(Color.BLACK)
				.build();
	}
	
	/**
	 * Al crear prendas sin material {@link NullPointerException}
	 */
	@Test(expected = NullPointerException.class)
	@SuppressWarnings("unused")
	public void buildPrendaWithoutColorPrincipal() {
		List<Material> materiales = new ArrayList<>();
		materiales.add(Material.ALGODON);
		Prenda prenda = new Prenda.Builder()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new TipoSuperior()))
				.setMaterial(Material.ALGODON)
				.build();
	}
	
	/**
	 * Se permite crear prendas sin color secundario
	 */
	@Test
	public void buildPrendaWithoutColorSecundario() {
		List<Material> materiales = new ArrayList<>();
		materiales.add(Material.ALGODON);
		Prenda prenda = new Prenda.Builder()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new TipoSuperior()))
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build();
		assertEquals(prenda.getCategoria(), Categoria.PRENDA_SUPERIOR);
	}
	
	/**
	 * Se permite crear prendas completas
	 */
	@Test
	public void buildPrendaCompleta() {
		List<Material> materiales = new ArrayList<>();
		materiales.add(Material.ALGODON);
		Prenda prenda = new Prenda.Builder()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new TipoInferior()))
				.setMaterial(Material.ALGODON)
				.setTrama(Trama.RALLADO)
				.setColorPrincipal(Color.BLACK)
				.build();
		assertEquals(prenda.getCategoria(), Categoria.PRENDA_INFERIOR);
	}

}