package quemepongo.config;

import org.junit.Before;
import quemepongo.model.evento.Evento;
import quemepongo.model.sugerencia.Atuendo;
import quemepongo.model.usuario.Usuario;

public abstract class EstadoAtuendoTestConfig extends TestConfig {

    protected Atuendo atuendo;
    protected Usuario usuario;
    protected Evento evento;

    @Before
    public void setup() {
        atuendo = atuendoBasico();
        usuario = usuarioBasico();
        evento = eventoBasico();

    }
}
