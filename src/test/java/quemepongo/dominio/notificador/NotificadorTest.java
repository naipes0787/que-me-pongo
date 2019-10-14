package quemepongo.dominio.notificador;


import org.junit.Test;
import quemepongo.config.UsuarioTestConfig;

import java.util.Optional;

import static org.junit.Assert.assertSame;

public class NotificadorTest extends UsuarioTestConfig {

    @Test
    public void siConstruimosUnUsuario_TendraEmptyComoNotificador(){
        assertSame(johnnyBravo.getNotificador(), Optional.empty());
    }

    @Test
    public void siSeteamosNotificador_TendraElNotificadorEncapsuladoEnOptional(){
        NotificadorEmail notificadorEmail = new NotificadorEmail("johnny@test.com");
        johnnyBravo.setNotificador(notificadorEmail);
        assertSame(johnnyBravo.getNotificador().get(), notificadorEmail);
    }
}
