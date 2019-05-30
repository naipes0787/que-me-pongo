package quemepongo;

import org.junit.Before;
import org.junit.Test;
import quemepongo.model.*;

import java.awt.*;

import static org.junit.Assert.assertEquals;

public class EstadoAtuendoTest {
    private static final TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior());
    private static final TipoPrenda REMERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperior());
    private static final TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado());

    private Atuendo atuendo;
    private Usuario usuario;

    @Before
    public void setup() {
        Prenda jean = new CreadorDePrenda()
                .setTipoPrenda(JEAN)
                .setMaterial(Material.OXFORD)
                .setColorPrincipal(Color.BLACK)
                .build();
        Prenda remera = new CreadorDePrenda()
                .setTipoPrenda(REMERA)
                .setMaterial(Material.ALGODON)
                .setColorPrincipal(Color.BLACK)
                .build();
        Prenda botas = new CreadorDePrenda()
                .setTipoPrenda(BOTAS)
                .setMaterial(Material.CUERO)
                .setColorPrincipal(Color.BLACK)
                .build();
        atuendo = new Atuendo(remera, jean, botas);
        usuario = new Usuario();
    }

    @Test
    public void sinDecision() {
        assertEquals(EstadoAtuendo.NUEVO, atuendo.getEstado());
        usuario.deshacerUltimaOperacion(atuendo);
        assertEquals(EstadoAtuendo.NUEVO, atuendo.getEstado());
    }

    @Test
    public void usuarioAceptaSugerenciaYDeshace() {
        usuario.aceptarSugerencia(atuendo);
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
        usuario.aceptarSugerencia(atuendo);
        usuario.rechazarSugerencia(atuendo);
        assertEquals(EstadoAtuendo.ACEPTADO, atuendo.getEstado());
    }

    @Test
    public void noSePasaRechazadoAAceptado() {
        usuario.rechazarSugerencia(atuendo);
        usuario.aceptarSugerencia(atuendo);
        assertEquals(EstadoAtuendo.RECHAZADO, atuendo.getEstado());
    }
}
