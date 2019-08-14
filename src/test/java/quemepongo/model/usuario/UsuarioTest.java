package quemepongo.model.usuario;

import org.junit.Test;
import quemepongo.config.UsuarioTestConfig;
import quemepongo.exceptions.LimiteDeGuardarropasException;
import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.prenda.CreadorDePrenda;
import quemepongo.model.prenda.Material;

import java.awt.*;

import static org.junit.Assert.*;

public class UsuarioTest extends UsuarioTestConfig {

	@Test(expected = LimiteDeGuardarropasException.class)
	public void arrojarExcepcionSiElUsuarioGratuitoAgregaMasPrendasAlGuardarropasDeLoPermitido(){

		Guardarropa guardarropa1 = new Guardarropa();

		johnnyBravo.agregarGuardarropa(guardarropa1);

		for(int i = 0;i < 200;i++) {
			johnnyBravo.agregarPrenda(crearPrenda(REMERA), guardarropa1);
		}
	}

	@Test
	public void unPremiumSuperaElLimiteDePrendasDelGratuito(){

		Guardarropa guardarropa1 = new Guardarropa();

		for(int i = 0;i < 200;i++) {
			montgomeryBurns.agregarPrenda(crearPrenda(PANTUFLAS_CORTE_FINO_DE_RINOCERONTE_ALBINO), guardarropa1);
		}

		assertEquals(200, guardarropa1.cantidadDePrendas());
	}

	@Test
	public void unPremiumQueAgrega200PrendasAlGuardarropa_Tendra200PrendasEnEseGuardarropaAlNoHaberLimite(){

		Guardarropa guardarropa1 = new Guardarropa();

		johnnyBravo.cambiarSuscripcion(new UsuarioPremium());

		for(int i = 0;i < 200;i++) {
			johnnyBravo.agregarPrenda(crearPrenda(REMERA), guardarropa1);
		}

		assertEquals(200, guardarropa1.cantidadDePrendas());
	}

	@Test
	public void calificacionesAgradablesNoModificanLaSensibilidadDelClima(){

		johnnyBravo.calificar(agradableGeneral);
		johnnyBravo.calificar(agradableGeneral);
		johnnyBravo.calificar(agradableGeneral);

		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);
	}

	@Test
	public void dosCalificacionesCalurosasYUnaFriaDaria0_9(){

		johnnyBravo.calificar(calurosoGeneral);
		johnnyBravo.calificar(congeladoGeneral);
		johnnyBravo.calificar(calurosoGeneral);

		assertEquals( 0.9, johnnyBravo.getSensibilidadClima(), 0);
	}

	@Test
	public void siUnUsuarioCalificaMuchasAtuendosCongeladosEnLasManos_EsFrioLentoEnLasManos(){

		johnnyBravo.calificar(congeladoGeneral);
		johnnyBravo.calificar(congeladoGeneral);
		johnnyBravo.calificar(congeladoGeneral);

		assertTrue( johnnyBravo.esFriolentoDeManos() );
	}

	@Test
	public void siUnUsuarioCalificaMuchasAtuendosCalurososEnLasManos_NoEsFrioLentoEnLasManos(){

		johnnyBravo.calificar(calurosoGeneral);
		johnnyBravo.calificar(congeladoGeneral);
		johnnyBravo.calificar(calurosoGeneral);

		assertFalse( johnnyBravo.esFriolentoDeManos() );
	}
}