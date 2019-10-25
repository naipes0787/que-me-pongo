package quemepongo.dominio.sugerencia;

import org.junit.Before;
import org.junit.Test;
import quemepongo.config.TestBase;

import static org.junit.Assert.assertEquals;

public class EstadoAtuendoTest extends TestBase {

    private Atuendo atuendo;

    @Before
    public void setup() {
        atuendo = atuendoBasico();
    }

    @Test
    public void alDeshacerAtuendoSinCalificar_SeMantieneEnEstadoNuevo() {
        assertEquals(EstadoAtuendo.NUEVO, atuendo.getEstado());
        atuendo.deshacerUltimaOperacion();
        assertEquals(EstadoAtuendo.NUEVO, atuendo.getEstado());
    }

    @Test
    public void alDeshacerAtuendoAceptado_VuelveAEstadoNuevo() {
        atuendo.aceptar();
        assertEquals(EstadoAtuendo.ACEPTADO, atuendo.getEstado());
        atuendo.deshacerUltimaOperacion();
        assertEquals(EstadoAtuendo.NUEVO, atuendo.getEstado());
    }

    @Test
    public void alDeshacerAtuendoRechazado_VuelveAEstadoNuevo() {
        atuendo.rechazar();
        assertEquals(EstadoAtuendo.RECHAZADO, atuendo.getEstado());
        atuendo.deshacerUltimaOperacion();
        assertEquals(EstadoAtuendo.NUEVO, atuendo.getEstado());
    }

    @Test
    public void alRechazarAtuendoAceptado_NoSeCambiaEstado() {
        atuendo.aceptar();
        atuendo.rechazar();
        assertEquals(EstadoAtuendo.ACEPTADO, atuendo.getEstado());
    }

    @Test
    public void alAceptarAtuendoRechazado_NoSeCambiaEstado() {
        atuendo.rechazar();
        atuendo.aceptar();
        assertEquals(EstadoAtuendo.RECHAZADO, atuendo.getEstado());
    }
}
