package quemepongo.dominio.guardarropa;

import org.junit.Test;
import quemepongo.config.GuardarropaTestConfig;
import quemepongo.dominio.sugerencia.Atuendo;
import quemepongo.dominio.usuario.Usuario;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class GuardarropaTest extends GuardarropaTestConfig {

	@Test
	public void siUsuarioUsaDosGuardarropas_TieneSugerenciasDeAmbos() {
		Guardarropa guardarropa1 = guardarropaCon(JeanDeOxfordNegro, RemeraDeAlgodonNegra, ZapatillasDeLonaNegras);
		Guardarropa guardarropa2 = guardarropaCon(PolleraDeAlgodonNegra, RemeraDeAlgodonNegra, MusculosaDeAlgodonNegra,
				ZapatillasDeLonaNegras, BotasDeCueroNegras);

		Usuario usuario = usuarioConGuardarropas(guardarropa1, guardarropa2);

		// En guardarropa1 hay sólo una prenda de cada categoría
		final long cantidadAtuendosGuardarropa1 = 1;
		// En guardarropa2 hay sólo una prenda inferior, dos superiores y dos calzados
		final long cantidadAtuendosGuardarropa2 = 1 * 2 * 2;
		Set<Atuendo> sugerenciasUsuario = usuario.sugerencias(eventoBasico());

		assertEquals(cantidadAtuendosGuardarropa1 + cantidadAtuendosGuardarropa2, sugerenciasUsuario.size());
	}

	@Test
	public void siUsuarioUsaGuardarropasConUnaPrendaDeCadaTipo_TieneUnaSugerencia() {
		Guardarropa guardarropa = guardarropaCon(JeanDeOxfordNegro, CamisaDeAlgodonNegra, ZapatillasDeLonaNegras,
				BufandaDeLana);
		Usuario usuario = usuarioConGuardarropas(guardarropa);
		// En el guardarropa hay sólo una prenda inferior, una superior, un calzado y un accesorio
		final long cantidadAtuendosGuardarropa = 1 * 1 * 1 * 1;
		Set<Atuendo> sugerenciasUsuario = usuario.sugerencias(eventoBasico());
		assertEquals(cantidadAtuendosGuardarropa, sugerenciasUsuario.size());
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
