package quemepongo.config;

import org.junit.After;
import quemepongo.dominio.frecuencia.FormatoFrecuencia;
import quemepongo.dominio.frecuencia.Frecuencia;
import quemepongo.dominio.frecuencia.FrecuenciaBuilder;
import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.evento.Localizacion;
import quemepongo.dominio.evento.tipo.Anticipacion;
import quemepongo.dominio.evento.tipo.EventoRepetitivo;
import quemepongo.dominio.evento.tipo.EventoUnico;
import quemepongo.persistencia.RepositorioEvento;

import java.time.LocalDateTime;

public abstract class RepositorioEventoTestConfig extends EventoTestConfig {

    protected RepositorioEvento repositorio = RepositorioEvento.instancia();

    protected static final Frecuencia TODOS_LOS_DIAS_A_LAS_10_AM = new FrecuenciaBuilder(FormatoFrecuencia.DIARIA).setMinuto(0).setHora(10).build();

    protected Evento eventoRepetitivo(Frecuencia frecuencia) {
        return new Evento("el mejor evento", Localizacion.CABA, new EventoRepetitivo(frecuencia), Anticipacion.UNA_HORA_ANTES);
    }

    protected Evento eventoBasico() {
        return new Evento("unEvento", Localizacion.CABA, new EventoUnico(LocalDateTime.now()), Anticipacion.UNA_HORA_ANTES);
    }

    @After
    public void borrarTodo() {
        repositorio.getEventos().forEach(e -> repositorio.borrar(e));
    }
}
