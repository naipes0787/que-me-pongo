package quemepongo.model.usuario;

import com.google.common.collect.Sets;
import org.junit.Test;
import quemepongo.config.UsuarioTestConfig;
import quemepongo.exceptions.GuardarropaNoPerteneceAlUsuarioException;
import quemepongo.exceptions.LimiteDeGuardarropasException;
import quemepongo.model.guardarropa.GuardarropaCompartido;

import static org.junit.Assert.*;

public class UsuarioTest extends UsuarioTestConfig {

	private static int CANTIDAD_LIMITE_PRENDAS_USUARIO_GRATUITO = 25;
	private static int CANTIDAD_PRENDAS_EJEMPLO_PREMIUM = 200;

	@Test
	public void siUsuarioAgregaUnGuardarropas_LoTendraAsociado(){
		assertFalse(johnnyBravo.tieneGuardarropa(guardarropa));

		johnnyBravo.agregarGuardarropa(guardarropa);

		assertTrue(johnnyBravo.tieneGuardarropa(guardarropa));
	}

	@Test
	public void siSeCreaUnGuardarropaCompartido_AmbosUsuariosLoTendranAsociado(){
		GuardarropaCompartido guardarropaNuevoCompartido = new GuardarropaCompartido(Sets.newHashSet(johnnyBravo, montgomeryBurns));

		assertTrue(johnnyBravo.tieneGuardarropa(guardarropaNuevoCompartido));
		assertTrue(montgomeryBurns.tieneGuardarropa(guardarropaNuevoCompartido));
	}

	@Test(expected = LimiteDeGuardarropasException.class)
	public void siUsuarioGratuitoAgregaMasDe25PrendasAGuardarropa_ExcepcionPorLimite(){
		johnnyBravo.agregarGuardarropa(guardarropa);

		for(int i = 0; i <= CANTIDAD_LIMITE_PRENDAS_USUARIO_GRATUITO; i++) {
			johnnyAgregaUnaPredaNueva(guardarropa);
		}
	}

	@Test(expected = LimiteDeGuardarropasException.class)
	public void siUsuarioGratuitoAgregaMasDe25PrendasAGuardarropaCompartido_ExcepcionPorLimite(){

		for(int i = 0; i <= CANTIDAD_LIMITE_PRENDAS_USUARIO_GRATUITO; i++) {
			johnnyAgregaUnaPredaNueva(guardarropaCompartidoEntreJohnnyYBurns);
		}
	}

	@Test
	public void siUsuarioPremiumAgregaMasDe25PrendasAGuardarropa_PermiteElAgregado(){
		montgomeryBurns.agregarGuardarropa(guardarropa);
		for(int i = 0; i < CANTIDAD_PRENDAS_EJEMPLO_PREMIUM; i++) {
			burnsAgregaUnaPredaNueva(guardarropa);
		}

		assertEquals(CANTIDAD_PRENDAS_EJEMPLO_PREMIUM, guardarropa.cantidadDePrendas());
	}

	@Test
	public void siUsuarioPremiumAgregaMasDe25PrendasAGuardarropaCompartido_PermiteElAgregado(){
		for(int i = 0; i < CANTIDAD_PRENDAS_EJEMPLO_PREMIUM; i++) {
			burnsAgregaUnaPredaNueva(guardarropaCompartidoEntreJohnnyYBurns);
		}

		assertEquals(CANTIDAD_PRENDAS_EJEMPLO_PREMIUM, guardarropaCompartidoEntreJohnnyYBurns.cantidadDePrendas());
	}

	@Test(expected = GuardarropaNoPerteneceAlUsuarioException.class)
	public void siUsuarioGratuitoAgregaPrendaAGuardarropaAjeno_Excepcion(){
		assertFalse(johnnyBravo.tieneGuardarropa(guardarropa));
		johnnyAgregaUnaPredaNueva(guardarropa);
	}

	@Test(expected = GuardarropaNoPerteneceAlUsuarioException.class)
	public void siUsuarioPremiumAgregaPrendaAGuardarropaAjeno_Excepcion(){
		assertFalse(montgomeryBurns.tieneGuardarropa(guardarropa));
		burnsAgregaUnaPredaNueva(guardarropa);
	}

	@Test
	public void siUsuarioGratuitoCambiaAPremium_PuedeAgregarMasPrendas(){
		johnnyBravo.cambiarSuscripcion(new UsuarioPremium());
		johnnyBravo.agregarGuardarropa(guardarropa);

		for(int i = 0; i < CANTIDAD_PRENDAS_EJEMPLO_PREMIUM; i++) {
			johnnyAgregaUnaPredaNueva(guardarropa);
		}

		assertEquals(CANTIDAD_PRENDAS_EJEMPLO_PREMIUM, guardarropa.cantidadDePrendas());
	}

	@Test
	public void siUsuarioGratuitoNoRealizaCalificacionAlguna_NoSeraFrioLentoYSuSensibilidadAlClimaSeraDe1(){
		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);
		assertFalse(johnnyBravo.esFriolentoDeCabeza());
		assertFalse(johnnyBravo.esFriolentoDeManos());
		assertFalse(johnnyBravo.esFriolentoDeCuello());
	}

	@Test
	public void siUsuarioPremiumNoRealizaCalificacionAlguna_NoSeraFrioLentoYSuSensibilidadAlClimaSeraDe1(){
		assertEquals(1, montgomeryBurns.getSensibilidadClima(), 0);
		assertFalse(montgomeryBurns.esFriolentoDeCabeza());
		assertFalse(montgomeryBurns.esFriolentoDeManos());
		assertFalse(montgomeryBurns.esFriolentoDeCuello());
	}

	@Test
	public void siUsuarioCalificaAgradable_NoSeModificaLaSensibilidadDelClima(){
		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);

		johnnyBravo.calificar(agradableGeneral);
		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);

		johnnyBravo.calificar(agradableGeneral);
		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);

		johnnyBravo.calificar(agradableGeneral);
		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);
	}

	@Test
	public void siUsuarioGratuitoRealizaDosCalificacionesCalurosasYUnaCongelada_SuSensibilidadClimaDaria0_9(){
		johnnyBravo.calificar(calurosoGeneral);
		johnnyBravo.calificar(congeladoGeneral);
		johnnyBravo.calificar(calurosoGeneral);

		assertEquals( 0.9, johnnyBravo.getSensibilidadClima(), 0);
	}

	@Test
	public void siUsuarioPremiumRealizaQuinceCalificacionesCalurosasYUnaCongelada_SuSensibilidadClimaDaria_Negativo0_4(){
		for(int i = 0; i < 15; i++){
			johnnyBravo.calificar(calurosoGeneral);
		}
		johnnyBravo.calificar(congeladoGeneral);

		assertEquals( -0.4, johnnyBravo.getSensibilidadClima(), 0.01);
	}

	@Test
	public void siUsuarioGratuitoCalificaMuchosAtuendosCongeladosEnLasManos_EsFrioLentoEnLasManos(){
		johnnyBravo.calificar(congeladoSoloManos);
		johnnyBravo.calificar(congeladoSoloManos);
		johnnyBravo.calificar(congeladoSoloManos);

		assertTrue( johnnyBravo.esFriolentoDeManos() );
		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);
		assertFalse( johnnyBravo.esFriolentoDeCuello() );
		assertFalse( johnnyBravo.esFriolentoDeCabeza() );
	}

	@Test
	public void siUsuarioPremiumCalificaMuchosAtuendosCalurososEnLasManos_NoEsFrioLentoEnLasManos(){
		montgomeryBurns.calificar(calurosoSoloManos);
		montgomeryBurns.calificar(calurosoSoloManos);
		montgomeryBurns.calificar(calurosoSoloManos);

		assertFalse( montgomeryBurns.esFriolentoDeManos() );
	}

	@Test
	public void siUsuarioGratuitoCalificaMuchosAtuendosCongeladosEnElCuello_EsFrioLentoEnElCuello(){
		johnnyBravo.calificar(congeladoSoloCuello);
		johnnyBravo.calificar(congeladoSoloCuello);
		johnnyBravo.calificar(congeladoSoloCuello);

		assertTrue( johnnyBravo.esFriolentoDeCuello() );
		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);
		assertFalse( johnnyBravo.esFriolentoDeManos() );
		assertFalse( johnnyBravo.esFriolentoDeCabeza() );
	}

	@Test
	public void siUsuarioPremiumCalificaMuchosAtuendosCalurososElCuello_NoEsFrioLentoElCuello(){
		montgomeryBurns.calificar(calurosoSoloCuello);
		montgomeryBurns.calificar(calurosoSoloCuello);
		montgomeryBurns.calificar(calurosoSoloCuello);

		assertFalse( montgomeryBurns.esFriolentoDeCuello() );
	}

	@Test
	public void siUsuarioPremiumCalificaMuchosAtuendosCongeladosEnLaCabeza_EsFrioLentoEnLaCabezaPeroNoEnElRestoDelCuerpo(){
		montgomeryBurns.calificar(congeladoSoloCabeza);
		montgomeryBurns.calificar(congeladoSoloCabeza);
		montgomeryBurns.calificar(congeladoSoloCabeza);

		assertTrue( montgomeryBurns.esFriolentoDeCabeza() );
		assertEquals(1, montgomeryBurns.getSensibilidadClima(), 0);
		assertFalse( montgomeryBurns.esFriolentoDeManos() );
		assertFalse( montgomeryBurns.esFriolentoDeCuello() );
	}

	@Test
	public void siUsuarioGratuitoCalificaMuchosAtuendosCalurososLaCabeza_NoEsFrioLentoLaCabeza(){
		johnnyBravo.calificar(calurosoSoloCabeza);
		johnnyBravo.calificar(calurosoSoloCabeza);
		johnnyBravo.calificar(calurosoSoloCabeza);

		assertFalse( johnnyBravo.esFriolentoDeCabeza() );
	}
}