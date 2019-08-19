package quemepongo.model.sugerencia;

import org.junit.Test;
import quemepongo.config.AtuendoTestConfig;
import quemepongo.model.FactorClimatico;
import quemepongo.model.Temperatura;

import static org.junit.Assert.*;

/**
 * Test de la Atuendo
 */
public class AtuendoTest extends AtuendoTestConfig {

	@Test
	public void atuendoAbrigaLoSuficiente() {
		Temperatura temperatura = new Temperatura(34D);

		/* El atuendo brinda 32 de nivel de abrigo (15+10+7), necesitaría 
		 * 27 como mínimo y 33 como máximo, así que daría TRUE */
		assertTrue(atuendo.abrigaLoSuficiente(temperatura.convertirANivelDeAbrigo(), 0.1D));
	}
	
	@Test
	public void atuendoNoAbrigaLoSuficiente() {
		Temperatura temperatura = new Temperatura(25D);;
		/* El atuendo brinda 32 de nivel de abrigo (15+10+7), necesitaría 
		 * 67.5 como mínimo y 82.5 como máximo, así que daría FALSE */
		assertFalse(atuendo.abrigaLoSuficiente(temperatura.convertirANivelDeAbrigo(), 0.1D));
	}
	
	@Test
	public void obtenerCantidadPrendasCorrectamente() {
		assertEquals(3, atuendo.getCantidadPrendas());
	}

	@Test
	public void atuendoConPilotoDeberiaSerAptoParaLluvia() {
		assertTrue(atuendoAptoParaLluvia().esAptoPara(FactorClimatico.LLUVIA));
	}

	@Test
	public void atuendoBasicoNoDeberiaSerAptoParaLluvia() {
		assertFalse(atuendo.esAptoPara(FactorClimatico.LLUVIA));
	}

}
