package quemepongo.dominio.notificador.alerta;

import quemepongo.dominio.notificador.Notificador;

public class AlertaEventoProximo implements Alerta {

    @Override
    public void alertar(Notificador notificador) {
        notificador.notificarSugerenciasListas();
    }
}