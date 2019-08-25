package quemepongo.ui.arena.evento;

import com.google.common.collect.Sets;
import org.junit.Test;
import quemepongo.model.evento.Evento;
import quemepongo.model.evento.FechaEspecifica;
import quemepongo.model.evento.Localizacion;
import quemepongo.model.prenda.*;
import quemepongo.model.sugerencia.Atuendo;

import java.awt.*;
import java.time.Duration;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class ListarEventosTest {

    @Test
    public void siBuscamosEventosConElMetodoDeLaUI_SeDevuelvenSegunLaFechaIngresada(){
        Evento evento1 = new Evento("Casamiento de Roberto Carlos", Localizacion.CABA,
                new FechaEspecifica(LocalDateTime.now().plusDays(10)), Duration.ofHours(1));
        Evento evento2 = new Evento("Cumpleaños de Martita", Localizacion.CABA,
                new FechaEspecifica(LocalDateTime.now().plusDays(30)), Duration.ofHours(1));
        Evento evento3 = new Evento("Hackaton UTN", Localizacion.CABA,
                new FechaEspecifica(LocalDateTime.now().plusDays(90)), Duration.ofHours(1));

        ListarEventos listarEventos = new ListarEventos();
        listarEventos.setDiaDesde("12");
        listarEventos.setMesDesde("12");
        listarEventos.setAnioDesde("1999");
        listarEventos.setDiaHasta("12");
        listarEventos.setMesHasta("12");
        listarEventos.setAnioHasta("2019");
        listarEventos.search();
        assertEquals(3, listarEventos.getResultados().size());
        listarEventos.setMesHasta("11");
        listarEventos.search();
        assertEquals(2, listarEventos.getResultados().size());
    }



}
