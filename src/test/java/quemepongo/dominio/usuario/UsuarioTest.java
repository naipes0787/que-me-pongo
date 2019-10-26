package quemepongo.dominio.usuario;

import com.google.common.collect.Sets;
import org.junit.Test;
import quemepongo.config.UsuarioTestConfig;
import quemepongo.dominio.guardarropa.GuardarropaCompartido;
import quemepongo.excepcion.GuardarropaNoPerteneceAlUsuarioException;
import quemepongo.excepcion.LimiteDeGuardarropasException;

import static org.junit.Assert.*;

public class UsuarioTest extends UsuarioTestConfig {

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

}