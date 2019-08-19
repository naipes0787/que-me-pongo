package quemepongo.config;

import quemepongo.model.evento.Evento;
import quemepongo.model.evento.Localizacion;
import quemepongo.model.evento.Ocurrencia;

import java.time.Duration;

public abstract class EventoTestConfig {

    protected Evento evento(Ocurrencia ocurrencia) {
        return new Evento("unEvento", Localizacion.CABA, ocurrencia, Duration.ofHours(1));
    }

}
