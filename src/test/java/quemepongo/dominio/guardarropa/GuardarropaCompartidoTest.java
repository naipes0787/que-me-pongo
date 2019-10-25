package quemepongo.dominio.guardarropa;

import org.junit.Test;
import quemepongo.config.GuardarropaCompartidoTestConfig;
import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.sugerencia.Atuendo;
import quemepongo.dominio.usuario.Usuario;

import java.util.Set;

import static org.junit.Assert.assertEquals;

public class GuardarropaCompartidoTest extends GuardarropaCompartidoTestConfig {

    @Test
    public void siUsuarioAceptaSugerenciaDeGuardarropaCompartido_EsasPrendasQuedanDescartadasParaProximaSugerencia() {
        Usuario usuario1 = usuarioBasico();
        Usuario usuario2 = usuarioBasico();
        guardarropaCompartido(usuario1, usuario2);

        Evento evento = eventoBasico();
        Set<Atuendo> sugerenciasEvento = usuario1.sugerencias(evento);
        Atuendo algunAtuendo = sugerenciasEvento.iterator().next();
        algunAtuendo.aceptar();
        evento.setAtuendo(algunAtuendo);

        /* Deberian generarse dos sugerencias:
            jean - camisa - zapatillas
            jean - remera - zapatillas
         */
        assertEquals(2, sugerenciasEvento.size());

        Evento otroEvento = eventoBasico();
        usuario2.agregarEvento(evento);
        Set<Atuendo> sugerenciasOtroEvento = usuario2.sugerencias(otroEvento);

        /* No se deberian generar sugerencias porque cualquiera de las dos
            que haya aceptado usuario1 contiene jean y zapatillas, que no podran ser usados por usuario2 */
        assertEquals(0, sugerenciasOtroEvento.size());
    }

}
