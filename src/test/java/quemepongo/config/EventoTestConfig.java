package quemepongo.config;

import quemepongo.model.evento.Evento;
import quemepongo.model.evento.Localizacion;
import quemepongo.model.evento.Ocurrencia;

public abstract class EventoTestConfig extends TestConfig {

    protected Evento evento(Ocurrencia ocurrencia) {
        return new Evento("unEvento", Localizacion.CABA, ocurrencia);
    }

}
