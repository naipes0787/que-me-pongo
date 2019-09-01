package quemepongo.ui.arena.evento;

import org.junit.Test;
import quemepongo.model.evento.Evento;
import quemepongo.model.evento.FechaEspecifica;
import quemepongo.model.evento.Localizacion;
import quemepongo.model.evento.RepositorioEvento;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class ListarEventosTest {

    @Test
    public void siBuscamosEventosConElMetodoDeLaUI_SeDevuelvenSegunLaFechaIngresada(){
        Evento evento1 = new Evento("Casamiento de Roberto Carlos", Localizacion.CABA,
                new FechaEspecifica(LocalDateTime.of(2020, Month.JANUARY, 15, 0, 0))
                , Duration.ofHours(1));
        Evento evento2 = new Evento("Cumpleaños de Martita", Localizacion.CABA,
                new FechaEspecifica(LocalDateTime.of(2020, Month.JULY, 1, 0, 0))
                , Duration.ofHours(1));
        Evento evento3 = new Evento("Hackaton UTN", Localizacion.CABA,
                new FechaEspecifica(LocalDateTime.of(2020, Month.NOVEMBER, 1, 0, 0))
                , Duration.ofHours(1));

        Set<Evento> eventos = RepositorioEvento.getInstancia().getEventos();

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
