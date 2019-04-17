package quemepongo;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import quemepongo.model.PrendaBuilder;
import quemepongo.model.Categoria;
import quemepongo.model.Material;
import quemepongo.model.Prenda;
import quemepongo.model.TipoPrenda;
import quemepongo.model.Trama;

/**
 * Test de la construcción y el uso de la clase Prenda
 */
public class PrendaTest {
	
	/**
	 * Al crear prendas sin tipo se arroja {@link NullPointerException}
	 */
	@Test(expected = NullPointerException.class)
	public void buildPrendaWithoutTipo() {
		List<Material> materiales = new ArrayList<>();
		materiales.add(Material.ALGODON);
		Prenda prenda = new PrendaBuilder()
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.buildPrenda();
		prenda.getCategoria();
	}
	
	/**
	 * Al crear prendas sin color principal {@link NullPointerException}
	 */
	@Test(expected = NullPointerException.class)
	public void buildPrendaWithoutMaterial() {
		List<Material> materiales = new ArrayList<>();
		materiales.add(Material.ALGODON);
		Prenda prenda = new PrendaBuilder()
				.setTipoPrenda(new TipoPrenda(Categoria.PARTE_SUPERIOR, materiales))
				.setColorPrincipal(Color.BLACK)
				.buildPrenda();
		prenda.getCategoria();
	}
	
	/**
	 * Al crear prendas sin material {@link NullPointerException}
	 */
	@Test(expected = NullPointerException.class)
	public void buildPrendaWithoutColorPrincipal() {
		List<Material> materiales = new ArrayList<>();
		materiales.add(Material.ALGODON);
		Prenda prenda = new PrendaBuilder()
				.setTipoPrenda(new TipoPrenda(Categoria.PARTE_SUPERIOR, materiales))
				.setMaterial(Material.ALGODON)
				.buildPrenda();
		prenda.getCategoria();
	}
	
	/**
	 * Se permite crear prendas sin color secundario
	 */
	@Test
	public void buildPrendaWithoutColorSecundario() {
		List<Material> materiales = new ArrayList<>();
		materiales.add(Material.ALGODON);
		Prenda prenda = new PrendaBuilder()
				.setTipoPrenda(new TipoPrenda(Categoria.PARTE_SUPERIOR, materiales))
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.buildPrenda();
		assertEquals(prenda.getCategoria(), Categoria.PARTE_SUPERIOR);
	}
	
	/**
	 * Se permite crear prendas completas
	 */
	@Test
	public void buildPrendaCompleta() {
		List<Material> materiales = new ArrayList<>();
		materiales.add(Material.ALGODON);
		Prenda prenda = new PrendaBuilder()
				.setTipoPrenda(new TipoPrenda(Categoria.PARTE_INFERIOR, materiales))
				.setMaterial(Material.ALGODON)
				.setTrama(Trama.RALLADO)
				.setColorPrincipal(Color.BLACK)
				.buildPrenda();
		assertEquals(prenda.getCategoria(), Categoria.PARTE_INFERIOR);
	}

}