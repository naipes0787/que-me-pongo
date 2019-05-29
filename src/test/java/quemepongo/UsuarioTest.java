package quemepongo;
import java.awt.Color;

import org.junit.Test;

import quemepongo.exceptions.LimiteDeGuardarropasException;
import quemepongo.model.CreadorDePrenda;
import quemepongo.model.FabricadorTipoAccesorio;
import quemepongo.model.Guardarropa;
import quemepongo.model.Material;
import quemepongo.model.TipoPrenda;
import quemepongo.model.Usuario;

public class UsuarioTest {
	
	//Usuario de Prueba
	private static final Usuario JohnnyBravo = new Usuario();
	
	//TipoPrenda
	TipoPrenda ANTEOJOS = TipoPrenda.diseniarTipo(new FabricadorTipoAccesorio());
	
	@Test(expected = LimiteDeGuardarropasException.class)
	public void arrojarExcepcionSiElUsuarioFreeAgregaMasPrendasAlGuardarropasDeLoPermitido(){
		
		Guardarropa guardarropa1 = new Guardarropa();
		
		JohnnyBravo.agregarGuardarropa(guardarropa1);
		
		for(;;) {
			JohnnyBravo.agregarPrenda(
					new CreadorDePrenda()
						.setTipoPrenda(ANTEOJOS)
						.setMaterial(Material.PLASTICO)
						.setColorPrincipal(Color.BLACK)
						.build(), 
					guardarropa1);
		}
	}
}