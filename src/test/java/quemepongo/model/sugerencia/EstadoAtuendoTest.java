package quemepongo.model.sugerencia;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import quemepongo.model.evento.Evento;
import quemepongo.model.evento.FechaEspecifica;
import quemepongo.model.evento.Localizacion;
import quemepongo.model.prenda.CombinacionPrenda;
import quemepongo.model.prenda.CreadorDePrenda;
import quemepongo.model.prenda.FabricadorTipoCalzado;
import quemepongo.model.prenda.FabricadorTipoInferior;
import quemepongo.model.prenda.FabricadorTipoSuperiorBase;
import quemepongo.model.prenda.Material;
import quemepongo.model.prenda.TipoPrenda;
import quemepongo.model.sugerencia.Atuendo;
import quemepongo.model.sugerencia.EstadoAtuendo;
import quemepongo.model.usuario.Usuario;

import java.awt.*;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class EstadoAtuendoTest {
    private static final TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(15));
    private static final TipoPrenda REMERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(12));
    private static final TipoPrenda BOTAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10));

    private Atuendo atuendo;
    private Usuario usuario;
    private Evento evento;

    @Before
    public void setup() {
        CombinacionPrenda jean = new CombinacionPrenda(Sets.newHashSet(new CreadorDePrenda()
                                                .setTipoPrenda(JEAN)
                                                .setMaterial(Material.OXFORD)
                                                .setColorPrincipal(Color.BLACK)
                                                .build()));
        CombinacionPrenda remera = new CombinacionPrenda(Sets.newHashSet(new CreadorDePrenda()
                .setTipoPrenda(REMERA)
                .setMaterial(Material.ALGODON)
                .setColorPrincipal(Color.BLACK)
                .build()));

        CombinacionPrenda botas = new CombinacionPrenda(Sets.newHashSet(new CreadorDePrenda()
                .setTipoPrenda(BOTAS)
                .setMaterial(Material.CUERO)
                .setColorPrincipal(Color.BLACK)
                .build()));
        atuendo = new Atuendo(remera, jean, botas);
        usuario = new Usuario();
        evento = new Evento("unEvento", Localizacion.CABA, new FechaEspecifica(LocalDateTime.now()));

    }

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
