package quemepongo.persistencia;

import org.junit.Test;
import quemepongo.config.RepositorioEventoTestConfig;
import quemepongo.dominio.evento.Evento;

import static org.junit.Assert.*;

public class RepositorioEventoTest extends RepositorioEventoTestConfig {

    @Test
    public void siSeGuardaUnEvento_SeAsignaId() {
        Evento evento = eventoBasico();
        assertNull(evento.getId());
        repositorio.guardar(evento);
        assertEquals(1, repositorio.getEventos().size());
        assertNotNull(evento.getId());
    }

    @Test
    public void siSeModificaUnEvento_seHaceUnUpdate() {
        Evento evento = eventoBasico();
        repositorio.guardar(evento);
        evento.setTitulo("Otro titulo");
        repositorio.entityManager().detach(evento);
        repositorio.guardar(evento);
        assertEquals(1, repositorio.getEventos().size());
        assertEquals("Otro titulo", repositorio.getEventos().get(0).getTitulo());
    }

}
