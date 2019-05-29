package quemepongo;
import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Test;

import quemepongo.exceptions.LimiteDeGuardarropasException;
import quemepongo.model.CreadorDePrenda;
import quemepongo.model.FabricadorTipoCalzado;
import quemepongo.model.FabricadorTipoSuperior;
import quemepongo.model.Guardarropa;
import quemepongo.model.Material;
import quemepongo.model.TipoPrenda;
import quemepongo.model.Usuario;
import quemepongo.model.UsuarioPremium;

public class UsuarioTest {
	
	//Usuario de Prueba
	private static final Usuario JohnnyBravo = new Usuario();
	private static final Usuario MontgomeryBurns = new Usuario(new UsuarioPremium());
	
	
	//TipoPrenda
	TipoPrenda REMERANEGRA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperior());
	TipoPrenda PANTUFLASCORTEFINODERINOCERONTEALBINO = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado());
	
	@Test(expected = LimiteDeGuardarropasException.class)
	public void arrojarExcepcionSiElUsuarioGratuitoAgregaMasPrendasAlGuardarropasDeLoPermitido(){
		
		Guardarropa guardarropa1 = new Guardarropa();
		
		JohnnyBravo.agregarGuardarropa(guardarropa1);
		
		for(int i = 0;i < 200;i++) {
			JohnnyBravo.agregarPrenda(
					new CreadorDePrenda()
						.setTipoPrenda(REMERANEGRA)
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
			MontgomeryBurns.agregarPrenda(
					new CreadorDePrenda()
						.setTipoPrenda(PANTUFLASCORTEFINODERINOCERONTEALBINO)
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
		
		JohnnyBravo.cambiarSuscripcion(new UsuarioPremium());
		
		for(int i = 0;i < 200;i++) {
			JohnnyBravo.agregarPrenda(
					new CreadorDePrenda()
						.setTipoPrenda(REMERANEGRA)
						.setMaterial(Material.ALGODON)
						.setColorPrincipal(Color.BLACK)
						.build(), 
					guardarropa1);
		}
		
		assertEquals(200, guardarropa1.cantidadDePrendas());
	}
}