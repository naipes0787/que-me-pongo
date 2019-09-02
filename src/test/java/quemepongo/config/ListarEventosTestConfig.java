package quemepongo.config;

import quemepongo.model.evento.Evento;
import quemepongo.model.evento.tipo.EventoUnico;
import quemepongo.model.evento.Localizacion;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Month;

public abstract class ListarEventosTestConfig {

    protected void generarEventosDe2020() {
        new Evento("Casamiento de Roberto Carlos", Localizacion.CABA,
                new EventoUnico(LocalDateTime.of(2020, Month.JANUARY, 15, 0, 0))
                , Duration.ofHours(1));
        new Evento("Cumpleaños de Martita", Localizacion.CABA,
                new EventoUnico(LocalDateTime.of(2020, Month.JULY, 1, 0, 0))
                , Duration.ofHours(1));
        new Evento("Hackaton UTN", Localizacion.CABA,
                new EventoUnico(LocalDateTime.of(2020, Month.NOVEMBER, 1, 0, 0))
                , Duration.ofHours(1));
    }
}
