package quemepongo.model.guardarropa;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import quemepongo.api.ClienteTest;
import quemepongo.api.clientes.ApiDeClima;
import quemepongo.api.servicio.SelectorDeProveedorDeClima;
import quemepongo.model.evento.Evento;
import quemepongo.model.evento.FechaEspecifica;
import quemepongo.model.evento.Localizacion;
import quemepongo.model.prenda.*;
import quemepongo.model.sugerencia.Atuendo;
import quemepongo.model.usuario.Usuario;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import static org.junit.Assert.assertEquals;

public class GuardarropaCompartidoTest {

    private static final TipoPrenda JEAN = TipoPrenda.diseniarTipo(new FabricadorTipoInferior(50));
    private static final TipoPrenda CAMISA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(50));
    private static final TipoPrenda REMERA = TipoPrenda.diseniarTipo(new FabricadorTipoSuperiorBase(50));
    private static final TipoPrenda ZAPATILLAS = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(50));

    private static final Prenda jean = new CreadorDePrenda()
            .setTipoPrenda(JEAN)
            .setMaterial(Material.OXFORD)
            .setColorPrincipal(Color.BLACK)
            .build();
    private static final Prenda camisa = new CreadorDePrenda()
            .setTipoPrenda(CAMISA)
            .setMaterial(Material.ALGODON)
            .setColorPrincipal(Color.BLACK)
            .build();
    private static final Prenda remera = new CreadorDePrenda()
            .setTipoPrenda(REMERA)
            .setMaterial(Material.ALGODON)
            .setColorPrincipal(Color.WHITE)
            .build();
    private static final Prenda zapatillas = new CreadorDePrenda()
            .setTipoPrenda(ZAPATILLAS)
            .setMaterial(Material.LONA)
            .setColorPrincipal(Color.BLACK)
            .build();

    @Before
    public void preparar() {
        ApiDeClima proovedorDeClima = new ClienteTest();
        SelectorDeProveedorDeClima.getInstancia().setProovedorDeClima(proovedorDeClima);
    }

    @Test
    public void guardarropaCompartidoConDosUsuariosNoPuedeUsarLasPrendasDeOtro() {
        Usuario usuario1 = new Usuario();
        Usuario usuario2 = new Usuario();
        Evento evento = new Evento("unEvento", Localizacion.CABA, new FechaEspecifica(LocalDateTime.now()));
        usuario1.agregarEvento(evento);

        GuardarropaCompartido guardarropa = new GuardarropaCompartido(Sets.newHashSet(usuario1, usuario2));
        guardarropa.agregarPrenda(jean);
        guardarropa.agregarPrenda(camisa);
        guardarropa.agregarPrenda(remera);
        guardarropa.agregarPrenda(zapatillas);

        usuario1.agregarGuardarropa(guardarropa);
        usuario2.agregarGuardarropa(guardarropa);

        Set<Atuendo> sugerenciasEvento = usuario1.sugerencias(evento);
        Atuendo algunAtuendo = sugerenciasEvento.iterator().next();
        usuario1.aceptarSugerencia(evento, algunAtuendo);

        /* Deberian generarse dos sugerencias:
            jean - camisa - zapatillas
            jean - remera - zapatillas
         */
        assertEquals(2, sugerenciasEvento.size());

        Evento otroEvento = new Evento("otroEvento", Localizacion.CABA, new FechaEspecifica(LocalDateTime.now().plus(2, ChronoUnit.DAYS)));
        usuario2.agregarEvento(evento);
        Set<Atuendo> sugerenciasOtroEvento = usuario2.sugerencias(otroEvento);

        /* No se deberian generar sugerencias porque cualquiera de las dos
            que haya aceptado usuario1 contiene jean y zapatillas, que no podran ser usados por usuario2:
         */
        assertEquals(0, sugerenciasOtroEvento.size());
    }

}
