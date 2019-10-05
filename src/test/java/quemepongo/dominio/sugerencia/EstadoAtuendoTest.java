package quemepongo.dominio.sugerencia;

import org.junit.Before;
import org.junit.Test;
import quemepongo.config.TestConfigGeneral;
import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.usuario.Usuario;

import static org.junit.Assert.assertEquals;

public class EstadoAtuendoTest extends TestConfigGeneral {

    private Atuendo atuendo;
    private Usuario usuario;
    private Evento evento;

    @Before
    public void setup() {
        atuendo = atuendoBasico();
        usuario = usuarioBasico();
        evento = eventoBasico();

    }

    @Test
    public void alDeshacerAtuendoSinCalificar_SeMantieneEnEstadoNuevo() {
        assertEquals(EstadoAtuendo.NUEVO, atuendo.getEstado());
        usuario.deshacerUltimaOperacion(atuendo);
        assertEquals(EstadoAtuendo.NUEVO, atuendo.getEstado());
    }

    @Test
    public void alDeshacerAtuendoAceptado_VuelveAEstadoNuevo() {
        usuario.aceptarSugerencia(evento, atuendo);
        assertEquals(EstadoAtuendo.ACEPTADO, atuendo.getEstado());
        usuario.deshacerUltimaOperacion(atuendo);
        assertEquals(EstadoAtuendo.NUEVO, atuendo.getEstado());
    }

    @Test
    public void alDeshacerAtuendoRechazado_VuelveAEstadoNuevo() {
        usuario.rechazarSugerencia(atuendo);
        assertEquals(EstadoAtuendo.RECHAZADO, atuendo.getEstado());
        usuario.deshacerUltimaOperacion(atuendo);
        assertEquals(EstadoAtuendo.NUEVO, atuendo.getEstado());
    }

    @Test
    public void alRechazarAtuendoAceptado_NoSeCambiaEstado() {
        usuario.aceptarSugerencia(evento, atuendo);
        usuario.rechazarSugerencia(atuendo);
        assertEquals(EstadoAtuendo.ACEPTADO, atuendo.getEstado());
    }

    @Test
    public void alAceptarAtuendoRechazado_NoSeCambiaEstado() {
        usuario.rechazarSugerencia(atuendo);
        usuario.aceptarSugerencia(evento, atuendo);
        assertEquals(EstadoAtuendo.RECHAZADO, atuendo.getEstado());
    }
}
