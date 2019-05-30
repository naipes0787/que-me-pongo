package quemepongo;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import java.util.Set;

import org.junit.Test;

import quemepongo.model.Atuendo;
import quemepongo.model.FabricadorTipoAccesorio;
import quemepongo.model.FabricadorTipoCalzado;
import quemepongo.model.FabricadorTipoInferior;
import quemepongo.model.FabricadorTipoSuperiorBase;
import quemepongo.model.Guardarropa;
import quemepongo.model.Material;
import quemepongo.model.Prenda;
import quemepongo.model.TipoPrenda;
import quemepongo.model.Usuario;
import quemepongo.model.CreadorDePrenda;

public class GuardarropaTest {

	private static final TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior());
	private static final TipoPrenda POLLERA = TipoPrenda.diseniarTipo(new FabricadorTipoInferior());
	private static final TipoPrenda REMERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase());
	private static final TipoPrenda MUSCULOSA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase());
	private static final TipoPrenda CAMISA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase());
	private static final TipoPrenda ZAPATILLAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado());
	private static final TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado());
	private static final TipoPrenda ANTEOJOS = TipoPrenda.diseniarTipo(new FabricadorTipoAccesorio());

	private static final Prenda JeanDeOxfordNegro = new CreadorDePrenda()
			.setTipoPrenda(JEAN)
			.setMaterial(Material.OXFORD)
			.setColorPrincipal(Color.BLACK)
			.build();
	private static final Prenda PolleraDeAlgodonNegra = new CreadorDePrenda()
			.setTipoPrenda(POLLERA)
			.setMaterial(Material.ALGODON)
			.setColorPrincipal(Color.BLACK)
			.build();
	private static final Prenda RemeraDeAlgodonNegra = new CreadorDePrenda()
			.setTipoPrenda(REMERA)
			.setMaterial(Material.ALGODON)
			.setColorPrincipal(Color.BLACK)
			.build();
	private static final Prenda MusculosaDeAlgodonNegra = new CreadorDePrenda()
			.setTipoPrenda(MUSCULOSA)
			.setMaterial(Material.ALGODON)
			.setColorPrincipal(Color.BLACK)
			.build();
	private static final Prenda CamisaDeAlgodonNegra = new CreadorDePrenda()
			.setTipoPrenda(CAMISA)
			.setMaterial(Material.ALGODON)
			.setColorPrincipal(Color.BLACK)
			.build();
	private static final Prenda ZapatillasDeLonaNegras = new CreadorDePrenda()
			.setTipoPrenda(ZAPATILLAS)
			.setMaterial(Material.LONA)
			.setColorPrincipal(Color.BLACK)
			.build();
	private static final Prenda BotasDeCueroNegras = new CreadorDePrenda()
			.setTipoPrenda(BOTAS)
			.setMaterial(Material.CUERO)
			.setColorPrincipal(Color.BLACK)
			.build();
	private static final Prenda AnteojosDePlasticoNegros = new CreadorDePrenda()
			.setTipoPrenda(ANTEOJOS)
			.setMaterial(Material.PLASTICO)
			.setColorPrincipal(Color.BLACK)
			.build();

	@Test
	public void usuariosUsandoGuardarropas() {

		Usuario usuario1 = new Usuario();
		Usuario usuario2 = new Usuario();
		Guardarropa guardarropa1 = new Guardarropa();
		Guardarropa guardarropa2 = new Guardarropa();
		Guardarropa guardarropa3 = new Guardarropa();

		guardarropa1.agregarPrendaInferior(JeanDeOxfordNegro);
		guardarropa2.agregarPrendaInferior(PolleraDeAlgodonNegra);
		guardarropa3.agregarPrendaInferior(JeanDeOxfordNegro);
		guardarropa1.agregarPrendaSuperior(RemeraDeAlgodonNegra);
		guardarropa2.agregarPrendaSuperior(RemeraDeAlgodonNegra);
		guardarropa2.agregarPrendaSuperior(MusculosaDeAlgodonNegra);
		guardarropa3.agregarPrendaSuperior(CamisaDeAlgodonNegra);
		guardarropa1.agregarCalzado(ZapatillasDeLonaNegras);
		guardarropa2.agregarCalzado(ZapatillasDeLonaNegras);
		guardarropa2.agregarCalzado(BotasDeCueroNegras);
		guardarropa3.agregarCalzado(ZapatillasDeLonaNegras);
		guardarropa3.agregarAccesorio(AnteojosDePlasticoNegros);

		usuario1.agregarGuardarropa(guardarropa1);
		usuario1.agregarGuardarropa(guardarropa2);
		usuario2.agregarGuardarropa(guardarropa3);
		// En el guardarropa 1 hay sólo una prenda de cada categoría
		final Integer cantidadAtuendosGuardarropa1 = 1;
		// En el guardarropa 2 hay sólo una prenda inferior, dos superiores y dos calzados
		final Integer cantidadAtuendosGuardarropa2 = 1 * 2 * 2;
		Set<Atuendo> sugerenciasUsuario1 = usuario1.sugerencias();
		assertEquals(cantidadAtuendosGuardarropa1 + cantidadAtuendosGuardarropa2, sugerenciasUsuario1.size());
		// En el guardarropa 3 hay sólo una prenda inferior, una superior, un calzado y
		// un accesorio
		// cantidadAtuendosGuardarropa3 = 1 * 1 * 1 * 1;
		Set<Atuendo> sugerenciasUsuario2 = usuario2.sugerencias();
		assertEquals(1, sugerenciasUsuario2.size());
	}

	
	@Test // Este guardarropa no tendrá Prendas superiores, por ende se espera que no de ninguna sugerencia
	public void sugerenciasDeGuardarropasSinPrendasSuperiores() {
		Guardarropa guardarropaSinPrendasSuperiores = new Guardarropa();

		guardarropaSinPrendasSuperiores.agregarPrendaInferior(JeanDeOxfordNegro);
		guardarropaSinPrendasSuperiores.agregarPrendaInferior(PolleraDeAlgodonNegra);
		guardarropaSinPrendasSuperiores.agregarCalzado(ZapatillasDeLonaNegras);
		guardarropaSinPrendasSuperiores.agregarCalzado(BotasDeCueroNegras);
		
		Set<Atuendo> sugerencias = guardarropaSinPrendasSuperiores.sugerencias();
		assertEquals(0, sugerencias.size());
	}

	@Test // Este guardarropa no tendrá Prendas inferiores, por ende se espera que no de ninguna sugerencia
	public void sugerenciasDeGuardarropasSinPrendasInferiores() {
		Guardarropa guardarropaSinPrendasInferiores = new Guardarropa();

		guardarropaSinPrendasInferiores.agregarPrendaSuperior(RemeraDeAlgodonNegra);
		guardarropaSinPrendasInferiores.agregarPrendaSuperior(CamisaDeAlgodonNegra);
		guardarropaSinPrendasInferiores.agregarCalzado(ZapatillasDeLonaNegras);
		guardarropaSinPrendasInferiores.agregarCalzado(BotasDeCueroNegras);
		
		Set<Atuendo> sugerencias = guardarropaSinPrendasInferiores.sugerencias();
		assertEquals(0, sugerencias.size());
	}

	@Test // Este guardarropa no tendrá Prendas de calzado, por ende se espera que no de ninguna sugerencia
	public void sugerenciasDeGuardarropasSinCalzados() {
		Guardarropa guardarropaSinCalzados = new Guardarropa();

		guardarropaSinCalzados.agregarPrendaSuperior(RemeraDeAlgodonNegra);
		guardarropaSinCalzados.agregarPrendaSuperior(CamisaDeAlgodonNegra);
		guardarropaSinCalzados.agregarPrendaInferior(JeanDeOxfordNegro);
		guardarropaSinCalzados.agregarPrendaInferior(PolleraDeAlgodonNegra);

		Set<Atuendo> sugerencias = guardarropaSinCalzados.sugerencias();
		assertEquals(0, sugerencias.size());
	}

}
