package quemepongo.model.usuario;

import org.junit.Before;
import org.junit.Test;
import quemepongo.exceptions.LimiteDeGuardarropasException;
import quemepongo.model.calificacion.Calificacion;
import quemepongo.model.calificacion.OpcionesCalificacion;
import quemepongo.model.prenda.CreadorDePrenda;
import quemepongo.model.prenda.FabricadorTipoCalzado;
import quemepongo.model.prenda.FabricadorTipoSuperiorBase;
import quemepongo.model.prenda.Material;
import quemepongo.model.prenda.TipoPrenda;
import quemepongo.model.guardarropa.Guardarropa;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class UsuarioTest {

	//Usuario de Prueba
	Usuario johnnyBravo = new Usuario();
	Usuario montgomeryBurns = new Usuario(new UsuarioPremium());

	//TipoPrenda
	private TipoPrenda REMERA_NEGRA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10));
	private TipoPrenda PANTUFLAS_CORTE_FINO_DE_RINOCERONTE_ALBINO = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10));

	//Calificaciones de Prueba
	private Calificacion agradableGeneral = new Calificacion(OpcionesCalificacion.AGRADABLE, OpcionesCalificacion.AGRADABLE,
			OpcionesCalificacion.AGRADABLE, OpcionesCalificacion.AGRADABLE);
	private Calificacion calurosoGeneral = new Calificacion(OpcionesCalificacion.CALUROSO, OpcionesCalificacion.CALUROSO,
			OpcionesCalificacion.CALUROSO, OpcionesCalificacion.CALUROSO);
	private Calificacion  congeladoGeneral = new Calificacion(OpcionesCalificacion.CONGELADO, OpcionesCalificacion.CONGELADO,
			OpcionesCalificacion.CONGELADO, OpcionesCalificacion.CONGELADO);

	@Before
	public void ejecutarAntesDeCadaTest() {
		johnnyBravo = new Usuario();
		montgomeryBurns = new Usuario(new UsuarioPremium());
	}

	@Test(expected = LimiteDeGuardarropasException.class)
	public void arrojarExcepcionSiElUsuarioGratuitoAgregaMasPrendasAlGuardarropasDeLoPermitido(){

		Guardarropa guardarropa1 = new Guardarropa();

		johnnyBravo.agregarGuardarropa(guardarropa1);

		for(int i = 0;i < 200;i++) {
			johnnyBravo.agregarPrenda(
					new CreadorDePrenda()
							.setTipoPrenda(REMERA_NEGRA)
							.setMaterial(Material.ALGODON)
							.setColorPrincipal(Color.BLACK)
							.build(),
					guardarropa1);
		}
	}

	@Test
	public void unPremiumSuperaElLimiteDePrendasDelGratuito(){

		Guardarropa guardarropa1 = new Guardarropa();

		for(int i = 0;i < 200;i++) {
			montgomeryBurns.agregarPrenda(
					new CreadorDePrenda()
							.setTipoPrenda(PANTUFLAS_CORTE_FINO_DE_RINOCERONTE_ALBINO)
							.setMaterial(Material.CUERO)
							.setColorPrincipal(Color.WHITE)
							.build(),
					guardarropa1);
		}

		assertEquals(200, guardarropa1.cantidadDePrendas());
	}

	@Test
	public void unPremiumQueAgrega200PrendasAlGuardarropa_Tendra200PrendasEnEseGuardarropaAlNoHaberLimite(){

		Guardarropa guardarropa1 = new Guardarropa();

		johnnyBravo.cambiarSuscripcion(new UsuarioPremium());

		for(int i = 0;i < 200;i++) {
			johnnyBravo.agregarPrenda(
					new CreadorDePrenda()
							.setTipoPrenda(REMERA_NEGRA)
							.setMaterial(Material.ALGODON)
							.setColorPrincipal(Color.BLACK)
							.build(),
					guardarropa1);
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
}