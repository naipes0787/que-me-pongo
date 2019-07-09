package quemepongo.model.notificador.alerta;

import quemepongo.model.notificador.Notificador;

public class AlertaEventoProximo implements Alerta {

    @Override
    public void alertar(Notificador notificador) {
        notificador.notificarSugerenciasListas();
    }
}