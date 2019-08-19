package quemepongo.model.guardarropa;

import org.junit.Test;
import quemepongo.config.GuardarropaTestConfig;
import quemepongo.model.sugerencia.Atuendo;
import quemepongo.model.usuario.Usuario;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class GuardarropaTest extends GuardarropaTestConfig {

	@Test
	public void siUsuarioUsaDosGuardarropas_TieneSugerenciasDeAmbos() {
		Guardarropa guardarropa = guardarropaCon(JeanDeOxfordNegro, RemeraDeAlgodonNegra, ZapatillasDeLonaNegras);
		Guardarropa guardarropa2 = guardarropaCon(PolleraDeAlgodonNegra, RemeraDeAlgodonNegra, MusculosaDeAlgodonNegra,
				ZapatillasDeLonaNegras, BotasDeCueroNegras);

		Usuario usuario1 = usuarioConGuardarropas(guardarropa, guardarropa2);

		// En el guardarropa 1 hay sólo una prenda de cada categoría
		final long cantidadAtuendosGuardarropa1 = 1;
		// En el guardarropa 2 hay sólo una prenda inferior, dos superiores y dos calzados
		final long cantidadAtuendosGuardarropa2 = 1 * 2 * 2;
		Set<Atuendo> sugerenciasUsuario1 = usuario1.sugerencias(eventoBasico());

		assertEquals(cantidadAtuendosGuardarropa1 + cantidadAtuendosGuardarropa2, sugerenciasUsuario1.size());
	}

	@Test
	public void siUsuarioUsaGuardarropasConUnaPrendaDeCadaTipo_TieneUnaSugerencia() {
		Guardarropa guardarropa = guardarropaCon(JeanDeOxfordNegro, CamisaDeAlgodonNegra, ZapatillasDeLonaNegras,
				BufandaDeLana);
		Usuario usuario2 = usuarioConGuardarropas(guardarropa);
		// En el guardarropa 3 hay sólo una prenda inferior, una superior, un calzado y un accesorio
		final long cantidadAtuendosGuardarropa3 = 1 * 1 * 1 * 1;
		Set<Atuendo> sugerenciasUsuario2 = usuario2.sugerencias(eventoBasico());
		assertEquals(cantidadAtuendosGuardarropa3, sugerenciasUsuario2.size());
	}
	
	@Test // Este guardarropa no tendrá Prendas superiores, por ende se espera que no de ninguna sugerencia
	public void siGuardarropaNoTienePrendasSuperiores_NoHaySugerencias() {
		Set<Atuendo> sugerencias = guardarropaSinPrendasSuperiores().sugerencias(usuarioBasico(), nivelAbrigo);
		assertEquals(0, sugerencias.size());
	}

	@Test // Este guardarropa no tendrá Prendas inferiores, por ende se espera que no de ninguna sugerencia
	public void siGuardarropaNoTienePrendasInferiores_NoHaySugerencias() {
		Set<Atuendo> sugerencias = guardarropaSinPrendasInferiores().sugerencias(usuarioBasico(), nivelAbrigo);
		assertEquals(0, sugerencias.size());
	}

	@Test // Este guardarropa no tendrá Prendas de calzado, por ende se espera que no de ninguna sugerencia
	public void siGuardarropaNoTieneCalzados_NoHaySugerencias() {
		Set<Atuendo> sugerencias = guardarropaSinCalzados().sugerencias(usuarioBasico(), nivelAbrigo);
		assertEquals(0, sugerencias.size());
	}

}
