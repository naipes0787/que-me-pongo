package quemepongo.model.usuario;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import quemepongo.exceptions.GuardarropaNoPerteneceAlUsuarioException;
import quemepongo.exceptions.LimiteDeGuardarropasException;
import quemepongo.model.calificacion.Calificacion;
import quemepongo.model.calificacion.OpcionesCalificacion;
import quemepongo.model.guardarropa.GuardarropaCompartido;
import quemepongo.model.prenda.CreadorDePrenda;
import quemepongo.model.prenda.FabricadorTipoCalzado;
import quemepongo.model.prenda.FabricadorTipoSuperiorBase;
import quemepongo.model.prenda.Material;
import quemepongo.model.prenda.TipoPrenda;
import quemepongo.model.guardarropa.Guardarropa;

import java.awt.*;

import static org.junit.Assert.*;

public class UsuarioTest {

	//Usuario de Prueba
	Usuario johnnyBravo = new Usuario();
	Usuario montgomeryBurns = new Usuario(new UsuarioPremium());

	//Guardarropa
	Guardarropa guardarropa = new Guardarropa();
	GuardarropaCompartido guardarropaCompartidoEntreJohnnyYBurns = new GuardarropaCompartido(Sets.newHashSet(johnnyBravo, montgomeryBurns));

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
	private Calificacion calurosoSoloCabeza = new Calificacion(OpcionesCalificacion.AGRADABLE, OpcionesCalificacion.AGRADABLE,
			OpcionesCalificacion.AGRADABLE, OpcionesCalificacion.CALUROSO);
	private Calificacion calurosoSoloManos = new Calificacion(OpcionesCalificacion.AGRADABLE, OpcionesCalificacion.CALUROSO,
			OpcionesCalificacion.AGRADABLE, OpcionesCalificacion.AGRADABLE);
	private Calificacion calurosoSoloCuello = new Calificacion(OpcionesCalificacion.AGRADABLE, OpcionesCalificacion.AGRADABLE,
			OpcionesCalificacion.CALUROSO, OpcionesCalificacion.AGRADABLE);
	private Calificacion congeladoSoloCabeza = new Calificacion(OpcionesCalificacion.AGRADABLE, OpcionesCalificacion.AGRADABLE,
			OpcionesCalificacion.AGRADABLE, OpcionesCalificacion.CONGELADO);
	private Calificacion congeladoSoloManos = new Calificacion(OpcionesCalificacion.AGRADABLE, OpcionesCalificacion.CONGELADO,
			OpcionesCalificacion.AGRADABLE, OpcionesCalificacion.AGRADABLE);
	private Calificacion congeladoSoloCuello = new Calificacion(OpcionesCalificacion.AGRADABLE, OpcionesCalificacion.AGRADABLE,
			OpcionesCalificacion.CONGELADO, OpcionesCalificacion.AGRADABLE);

	//Metodos
	private void johnnyAgregaUnaPredaNueva(Guardarropa guardarropa){
		johnnyBravo.agregarPrenda(
				new CreadorDePrenda()
						.setTipoPrenda(REMERA_NEGRA)
						.setMaterial(Material.ALGODON)
						.setColorPrincipal(Color.BLACK)
						.build(),
				guardarropa);
	}

	private void burnsAgregaUnaPredaNueva(Guardarropa guardarropa){
		montgomeryBurns.agregarPrenda(
				new CreadorDePrenda()
						.setTipoPrenda(PANTUFLAS_CORTE_FINO_DE_RINOCERONTE_ALBINO)
						.setMaterial(Material.CUERO)
						.setColorPrincipal(Color.WHITE)
						.build(),
				guardarropa);
	}

	@Before
	public void ejecutarAntesDeCadaTest() {
		johnnyBravo = new Usuario();
		montgomeryBurns = new Usuario(new UsuarioPremium());
		guardarropa = new Guardarropa();
		guardarropaCompartidoEntreJohnnyYBurns = new GuardarropaCompartido(Sets.newHashSet(johnnyBravo, montgomeryBurns));
	}

	@Test
	public void johnny_TendraUnGuardarropasSiLoAgrega(){
		assertFalse(johnnyBravo.tieneGuardarropa(guardarropa));

		johnnyBravo.agregarGuardarropa(guardarropa);

		assertTrue(johnnyBravo.tieneGuardarropa(guardarropa));
	}

	@Test
	public void johnnyYBurns__YaTendranUnGuardarropasCompartidoAlCrearse(){
		GuardarropaCompartido guardarropaNuevoCompartido = new GuardarropaCompartido(Sets.newHashSet(johnnyBravo, montgomeryBurns));

		assertTrue(johnnyBravo.tieneGuardarropa(guardarropaNuevoCompartido));
		assertTrue(montgomeryBurns.tieneGuardarropa(guardarropaNuevoCompartido));
	}

	@Test(expected = LimiteDeGuardarropasException.class)
	public void johnny_quiereAgregarMuchasPrendasASuGuardarropa_PeroEncontraraExcepcionPorLimiteAlSerGratuito(){
		johnnyBravo.agregarGuardarropa(guardarropa);

		for(int i = 0;i < 200;i++) {
			johnnyAgregaUnaPredaNueva(guardarropa);
		}
	}

	@Test(expected = LimiteDeGuardarropasException.class)
	public void johnny_quiereAgregarMuchasPrendasASuGuardarropaCompartido_PeroEncontraraExcepcionPorLimiteAlSerGratuito(){

		for(int i = 0;i < 200;i++) {
			johnnyAgregaUnaPredaNueva(guardarropaCompartidoEntreJohnnyYBurns);
		}
	}

	@Test
	public void burns_quiereAgregarMuchasPrendasASuGuardarropa_YNoEncontraraExcepcionPorLimiteAlSerPremium(){
		montgomeryBurns.agregarGuardarropa(guardarropa);
		for(int i = 0;i < 200;i++) {
			burnsAgregaUnaPredaNueva(guardarropa);
		}

		assertEquals(200, guardarropa.cantidadDePrendas());
	}

	@Test
	public void burns_quiereAgregarMuchasPrendasASuGuardarropaCompartido_YNoEncontraraExcepcionPorLimiteAlSerPremium(){
		for(int i = 0;i < 200;i++) {
			burnsAgregaUnaPredaNueva(guardarropaCompartidoEntreJohnnyYBurns);
		}

		assertEquals(200, guardarropaCompartidoEntreJohnnyYBurns.cantidadDePrendas());
	}

	@Test(expected = GuardarropaNoPerteneceAlUsuarioException.class)
	public void johnneQuiereAgregarUnaPrendaAUnGuardarropa_EncontraExcepcionSiNoTieneEseGuardarropa(){
		assertFalse(johnnyBravo.tieneGuardarropa(guardarropa));

		for(int i = 0;i < 200;i++) {
			johnnyAgregaUnaPredaNueva(guardarropa);
		}
	}

	@Test(expected = GuardarropaNoPerteneceAlUsuarioException.class)
	public void burnsQuiereAgregarUnaPrendaAUnGuardarropa_EncontraExcepcionSiNoTieneEseGuardarropa(){
		assertFalse(montgomeryBurns.tieneGuardarropa(guardarropa));

		for(int i = 0;i < 200;i++) {
			burnsAgregaUnaPredaNueva(guardarropa);
		}
	}

	@Test
	public void siJohnnyCambiaSuSuscripcionAPremiumYAgrega200PrendasASuGuardarropa_SuGuardarropaTendra200Prendas(){
		johnnyBravo.cambiarSuscripcion(new UsuarioPremium());
		johnnyBravo.agregarGuardarropa(guardarropa);

		for(int i = 0;i < 200;i++) {
			johnnyAgregaUnaPredaNueva(guardarropa);
		}

		assertEquals(200, guardarropa.cantidadDePrendas());
	}

	@Test
	public void siJohnnyNoRealizaCalificacionAlguna_NoSeraFrioLentoYSuSensibilidadAlClimaSeraDe1(){
		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);
		assertFalse(johnnyBravo.esFriolentoDeCabeza());
		assertFalse(johnnyBravo.esFriolentoDeManos());
		assertFalse(johnnyBravo.esFriolentoDeCuello());
	}

	@Test
	public void siBurnsNoRealizaCalificacionAlguna_NoSeraFrioLentoYSuSensibilidadAlClimaSeraDe1(){
		assertEquals(1, montgomeryBurns.getSensibilidadClima(), 0);
		assertFalse(montgomeryBurns.esFriolentoDeCabeza());
		assertFalse(montgomeryBurns.esFriolentoDeManos());
		assertFalse(montgomeryBurns.esFriolentoDeCuello());
	}

	@Test
	public void lasCalificacionesAgragablesDeUnUsuarioNoModificanLaSensibilidadDelClima(){
		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);

		johnnyBravo.calificar(agradableGeneral);
		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);

		johnnyBravo.calificar(agradableGeneral);
		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);

		johnnyBravo.calificar(agradableGeneral);
		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);
	}

	@Test
	public void jonnhyRealizaDosCalificacionesCalurosasYUnaCongelada_SuSensibilidadClimaDaria0_9(){
		johnnyBravo.calificar(calurosoGeneral);
		johnnyBravo.calificar(congeladoGeneral);
		johnnyBravo.calificar(calurosoGeneral);

		assertEquals( 0.9, johnnyBravo.getSensibilidadClima(), 0);
	}

	@Test
	public void burnsRealizaQuinceCalificacionesCalurosasYUnaCongelada_SuSensibilidadClimaDaria_Negativo0_4(){
		for(int i = 0; i < 15; i++){
			johnnyBravo.calificar(calurosoGeneral);
		}
		johnnyBravo.calificar(congeladoGeneral);

		assertEquals( -0.4, johnnyBravo.getSensibilidadClima(), 0.01);
	}

	@Test
	public void siJohnnyCalificaMuchosAtuendosCongeladosEnLasManos_EsFrioLentoEnLasManos(){
		johnnyBravo.calificar(congeladoSoloManos);
		johnnyBravo.calificar(congeladoSoloManos);
		johnnyBravo.calificar(congeladoSoloManos);

		assertTrue( johnnyBravo.esFriolentoDeManos() );
		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);
		assertFalse( johnnyBravo.esFriolentoDeCuello() );
		assertFalse( johnnyBravo.esFriolentoDeCabeza() );
	}

	@Test
	public void siBurnsCalificaMuchosAtuendosCalurososEnLasManos_NoEsFrioLentoEnLasManos(){
		montgomeryBurns.calificar(calurosoSoloManos);
		montgomeryBurns.calificar(calurosoSoloManos);
		montgomeryBurns.calificar(calurosoSoloManos);

		assertFalse( montgomeryBurns.esFriolentoDeManos() );
	}

	@Test
	public void siJohnnyCalificaMuchosAtuendosCongeladosEnElCuello_EsFrioLentoEnElCuello(){
		johnnyBravo.calificar(congeladoSoloCuello);
		johnnyBravo.calificar(congeladoSoloCuello);
		johnnyBravo.calificar(congeladoSoloCuello);

		assertTrue( johnnyBravo.esFriolentoDeCuello() );
		assertEquals(1, johnnyBravo.getSensibilidadClima(), 0);
		assertFalse( johnnyBravo.esFriolentoDeManos() );
		assertFalse( johnnyBravo.esFriolentoDeCabeza() );
	}

	@Test
	public void siBurnsCalificaMuchosAtuendosCalurososElCuello_NoEsFrioLentoElCuello(){
		montgomeryBurns.calificar(calurosoSoloCuello);
		montgomeryBurns.calificar(calurosoSoloCuello);
		montgomeryBurns.calificar(calurosoSoloCuello);

		assertFalse( montgomeryBurns.esFriolentoDeCuello() );
	}

	@Test
	public void siBurnsCalificaMuchosAtuendosCongeladosEnLaCabeza_EsFrioLentoEnLaCabezaPeroNoEnElRestoDelCuerpo(){
		montgomeryBurns.calificar(congeladoSoloCabeza);
		montgomeryBurns.calificar(congeladoSoloCabeza);
		montgomeryBurns.calificar(congeladoSoloCabeza);

		assertTrue( montgomeryBurns.esFriolentoDeCabeza() );
		assertEquals(1, montgomeryBurns.getSensibilidadClima(), 0);
		assertFalse( montgomeryBurns.esFriolentoDeManos() );
		assertFalse( montgomeryBurns.esFriolentoDeCuello() );
	}

	@Test
	public void siJohnnyCalificaMuchosAtuendosCalurososLaCabeza_NoEsFrioLentoLaCabeza(){
		johnnyBravo.calificar(calurosoSoloCabeza);
		johnnyBravo.calificar(calurosoSoloCabeza);
		johnnyBravo.calificar(calurosoSoloCabeza);

		assertFalse( johnnyBravo.esFriolentoDeCabeza() );
	}
}