package quemepongo.config;

import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.evento.Localizacion;
import quemepongo.dominio.evento.tipo.Anticipacion;
import quemepongo.dominio.evento.tipo.EventoUnico;
import quemepongo.persistencia.RepositorioEvento;

import java.time.LocalDateTime;
import java.time.Month;

public abstract class ListarEventosTestConfig {

    protected void generarEventosDe2020() {
        RepositorioEvento repo = RepositorioEvento.instancia();
        repo.guardar(new Evento("Casamiento de Roberto Carlos", Localizacion.CABA,
                new EventoUnico(LocalDateTime.of(2020, Month.JANUARY, 15, 0, 0))
                , Anticipacion.UNA_HORA_ANTES));
        repo.guardar(new Evento("Cumpleaños de Martita", Localizacion.CABA,
                new EventoUnico(LocalDateTime.of(2020, Month.JULY, 1, 0, 0))
                , Anticipacion.UNA_HORA_ANTES));
        repo.guardar(new Evento("Hackaton UTN", Localizacion.CABA,
                new EventoUnico(LocalDateTime.of(2020, Month.NOVEMBER, 1, 0, 0))
                , Anticipacion.UNA_HORA_ANTES));
    }
}
