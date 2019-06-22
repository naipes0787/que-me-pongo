package quemepongo;

import org.junit.Test;
import quemepongo.exceptions.ColoresRepetidosException;
import quemepongo.exceptions.MaterialInvalidoException;
import quemepongo.exceptions.PathInvalidoException;
import quemepongo.model.*;

import java.awt.*;
import java.awt.image.BufferedImage;

import static org.junit.Assert.assertEquals;

/**
 * Test de la construcción y el uso de la clase Prenda
 */
public class PrendaTest {
	
	/**
	 * Al crear prendas sin tipo se arroja {@link NullPointerException}
	 */
	@Test(expected = NullPointerException.class)
	@SuppressWarnings("unused")
	public void construirPrendaSinTipo() {
		Prenda prenda = new CreadorDePrenda()
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build();
	}
	
	/**
	 * Al crear prendas sin color principal {@link NullPointerException}
	 */
	@Test(expected = NullPointerException.class)
	@SuppressWarnings("unused")
	public void construirPrendaSinMaterial() {
		Prenda prenda = new CreadorDePrenda()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10)))
				.setColorPrincipal(Color.BLACK)
				.build();
	}
	
	/**
	 * Al crear prendas sin material {@link NullPointerException}
	 */
	@Test(expected = NullPointerException.class)
	@SuppressWarnings("unused")
	public void construirPrendaSinColorPrincipal() {
		Prenda prenda = new CreadorDePrenda()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10)))
				.setMaterial(Material.ALGODON)
				.build();
	}
	
	/**
	 * No se permiten crear prendas con materiales inválidos
	 */
	@Test(expected = MaterialInvalidoException.class)
	@SuppressWarnings("unused")
	public void construirPrendaInvalida() {
		Prenda prenda = new CreadorDePrenda()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10)))
				.setMaterial(Material.SEDA)
				.setColorPrincipal(Color.BLACK)
				.build();
	}
	
	/**
	 * No se permiten crear prendas con el mismo color como principal y secundario
	 */
	@Test(expected = ColoresRepetidosException.class)
	public void construirPrendaMismosColores() {
		Prenda prenda = new CreadorDePrenda()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoInferior(15)))
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
	public void construirPrendaSinColorSecundario() {
		Prenda prenda = new CreadorDePrenda()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(15)))
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.build();
		assertEquals(prenda.getCategoria(), Categoria.PRENDA_SUPERIOR);
	}
	
	/**
	 * Se permite crear prendas completas con colores distintos
	 */
	@Test
	public void construirPrendaCompletaSinFoto() {
		Prenda prenda = new CreadorDePrenda()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoInferior(15)))
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.setColorSecundario(Color.WHITE)
				.build();
		assertEquals(prenda.getCategoria(), Categoria.PRENDA_INFERIOR);
	}
	
	/**
	 * Se permite crear prendas con fotos que estén en ubicaciones válidas
	 */
	@Test
	public void construirPrendaCompletaConFoto() {
		Prenda prenda = new CreadorDePrenda()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoInferior(15)))
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.setColorSecundario(Color.WHITE)
				.setFoto("./src/test/resources/test-foto.jpg")
				.build();
		assertEquals(prenda.getCategoria(), Categoria.PRENDA_INFERIOR);
	}
	
	/**
	 * Si se utiliza una ubicación inválida para la foto, se arroja PathInvalidoException
	 */
	@Test(expected = PathInvalidoException.class)
	public void construirPrendaConFotoInvalida() {
		Prenda prenda = new CreadorDePrenda()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoInferior(15)))
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.setColorSecundario(Color.WHITE)
				.setFoto("./src/test/resources/test-foto-no-existe.jpg")
				.build();
		assertEquals(prenda.getCategoria(), Categoria.PRENDA_INFERIOR);
	}
	
	/**
	 * Al crear prendas con foto, la foto se normaliza
	 */
	@Test
	public void construirPrendaCompletaConFoto2() {
		CreadorDePrenda creadorPrenda = new CreadorDePrenda()
				.setTipoPrenda(TipoPrenda.diseniarTipo(new FabricadorTipoInferior(15)))
				.setMaterial(Material.ALGODON)
				.setColorPrincipal(Color.BLACK)
				.setColorSecundario(Color.WHITE)
				.setFoto("./src/test/resources/test-foto.jpg");
		BufferedImage foto = creadorPrenda.getFoto();
		assertEquals(foto.getWidth(), CreadorDePrenda.ANCHO_FOTO.intValue());
		assertEquals(foto.getHeight(), CreadorDePrenda.ALTO_FOTO.intValue());
	}

}