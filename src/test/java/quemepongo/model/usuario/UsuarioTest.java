package quemepongo.model.usuario;

import org.junit.Test;
import quemepongo.exceptions.LimiteDeGuardarropasException;
import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.prenda.CreadorDePrenda;
import quemepongo.model.prenda.FabricadorTipoCalzado;
import quemepongo.model.prenda.FabricadorTipoSuperiorBase;
import quemepongo.model.prenda.Material;
import quemepongo.model.prenda.TipoPrenda;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class UsuarioTest {
	
	//Usuario de Prueba
	private static final Usuario johnnyBravo = new Usuario();
	private static final Usuario montgomeryBurns = new Usuario(new UsuarioPremium());
	
	
	//TipoPrenda
	private TipoPrenda REMERA_NEGRA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10));
	private TipoPrenda PANTUFLAS_CORTE_FINO_DE_RINOCERONTE_ALBINO = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10));
	
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
}