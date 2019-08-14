package quemepongo.model.sugerencia;

import org.junit.Test;
import quemepongo.config.EstadoAtuendoTestConfig;

import static org.junit.Assert.assertEquals;

public class EstadoAtuendoTest extends EstadoAtuendoTestConfig {

    @Test
    public void sinDecision() {
        assertEquals(EstadoAtuendo.NUEVO, atuendo.getEstado());
        usuario.deshacerUltimaOperacion(atuendo);
        assertEquals(EstadoAtuendo.NUEVO, atuendo.getEstado());
    }

    @Test
    public void usuarioAceptaSugerenciaYDeshace() {
        usuario.aceptarSugerencia(evento, atuendo);
        assertEquals(EstadoAtuendo.ACEPTADO, atuendo.getEstado());
        usuario.deshacerUltimaOperacion(atuendo);
        assertEquals(EstadoAtuendo.NUEVO, atuendo.getEstado());
    }

    @Test
    public void usuarioRechazaSugerenciaYDeshace() {
        usuario.rechazarSugerencia(atuendo);
        assertEquals(EstadoAtuendo.RECHAZADO, atuendo.getEstado());
        usuario.deshacerUltimaOperacion(atuendo);
        assertEquals(EstadoAtuendo.NUEVO, atuendo.getEstado());
    }

    @Test
    public void noSePasaDeAceptadoARechazado() {
        usuario.aceptarSugerencia(evento, atuendo);
        usuario.rechazarSugerencia(atuendo);
        assertEquals(EstadoAtuendo.ACEPTADO, atuendo.getEstado());
    }

    @Test
    public void noSePasaRechazadoAAceptado() {
        usuario.rechazarSugerencia(atuendo);
        usuario.aceptarSugerencia(evento, atuendo);
        assertEquals(EstadoAtuendo.RECHAZADO, atuendo.getEstado());
    }
}
