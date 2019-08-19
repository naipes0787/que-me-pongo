
package quemepongo;

import java.awt.Color;
import java.time.Duration;
import java.time.LocalDateTime;

import com.google.common.collect.Sets;

import quemepongo.model.evento.Evento;
import quemepongo.model.evento.FechaEspecifica;
import quemepongo.model.evento.Localizacion;
import quemepongo.model.prenda.CombinacionPrenda;
import quemepongo.model.prenda.CreadorDePrenda;
import quemepongo.model.prenda.FabricadorTipoCalzado;
import quemepongo.model.prenda.FabricadorTipoInferior;
import quemepongo.model.prenda.FabricadorTipoSuperiorBase;
import quemepongo.model.prenda.Material;
import quemepongo.model.prenda.TipoPrenda;
import quemepongo.model.sugerencia.Atuendo;
import quemepongo.ui.arena.evento.ListarEventosWindow;

public class QueMePongoApplication {

	public static void main(String[] args) {
		
		/************************************** INICIO EVENTOS DE PRUEBA ***************************************/ 
		// TODO: Estos son eventos de prueba, se eliminarían al terminar la parte visual
	    TipoPrenda REMERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(10));
	    TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(15));
	    TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(7));
        CombinacionPrenda jean = new CombinacionPrenda(Sets.newHashSet(new CreadorDePrenda()
                .setTipoPrenda(JEAN)
                .setMaterial(Material.OXFORD)
                .setColorPrincipal(Color.BLACK)
                .build()));
		CombinacionPrenda remera = new CombinacionPrenda(Sets.newHashSet(new CreadorDePrenda()
			.setTipoPrenda(REMERA)
			.setMaterial(Material.ALGODON)
			.setColorPrincipal(Color.BLACK)
			.build()));
        CombinacionPrenda botas = new CombinacionPrenda(Sets.newHashSet(new CreadorDePrenda()
                .setTipoPrenda(BOTAS)
                .setMaterial(Material.CUERO)
                .setColorPrincipal(Color.BLACK)
                .build()));
        Atuendo atuendo = new Atuendo(remera, jean, botas);
        Evento evento1 = new Evento("Evento random", Localizacion.CABA, new FechaEspecifica(LocalDateTime.now().plusDays(10)), Duration.ofHours(1));
		Evento evento2 = new Evento("Evento random 2", Localizacion.CABA, new FechaEspecifica(LocalDateTime.now().plusDays(30)), Duration.ofHours(1));
		Evento evento3 = new Evento("Evento random 3", Localizacion.CABA, new FechaEspecifica(LocalDateTime.now().plusDays(90)), Duration.ofHours(1));
		evento2.setSugerenciaAceptada(atuendo);
		/**************************************** FIN EVENTOS DE PRUEBA ****************************************/
		
		
		new ListarEventosWindow().startApplication();
	}
	
}