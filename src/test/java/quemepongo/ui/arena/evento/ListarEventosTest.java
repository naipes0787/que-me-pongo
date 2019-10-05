package quemepongo.ui.arena.evento;

import org.junit.Test;
import quemepongo.config.ListarEventosTestConfig;
import quemepongo.dominio.evento.Evento;
import quemepongo.persistencia.RepositorioEvento;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListarEventosTest extends ListarEventosTestConfig {

    @Test
    public void siBuscamosEventosConElMetodoDeLaUI_SeDevuelvenSegunLaFechaIngresada(){
        generarEventosDe2020();

        List<Evento> eventos = RepositorioEvento.instancia().getEventos();

        long cantidadEventos2020 = eventos.stream()
                .filter(evento -> evento.getFecha().getYear() == 2020).count();

        ListarEventos listarEventos = new ListarEventos();
        listarEventos.setDiaDesde("01");
        listarEventos.setMesDesde("01");
        listarEventos.setAnioDesde("2020");
        listarEventos.setDiaHasta("31");
        listarEventos.setMesHasta("12");
        listarEventos.setAnioHasta("2020");
        listarEventos.search();
        assertEquals(cantidadEventos2020, listarEventos.getResultados().size());
        listarEventos.setMesHasta("05");
        listarEventos.search();
        assertEquals(cantidadEventos2020 - 2, listarEventos.getResultados().size());
    }



}
