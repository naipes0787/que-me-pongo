package quemepongo.model.notificador.alerta;

import quemepongo.model.notificador.Notificador;

public class AlertaMeteorologica implements Alerta {

    @Override
    public void alertar(Notificador notificador) {
        notificador.notificarAlertaMeteorologica();
    }
}
