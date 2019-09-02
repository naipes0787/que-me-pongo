package quemepongo.config;

import quemepongo.model.evento.Evento;
import quemepongo.model.evento.Localizacion;
import quemepongo.model.evento.tipo.TipoEvento;

import java.time.Duration;

public abstract class EventoTestConfig {

    protected Evento evento(TipoEvento tipo) {
        return new Evento("unEvento", Localizacion.CABA, tipo, Duration.ofHours(1));
    }

}
