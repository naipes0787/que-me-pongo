
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
		new ListarEventosWindow().startApplication();
	}
	
}