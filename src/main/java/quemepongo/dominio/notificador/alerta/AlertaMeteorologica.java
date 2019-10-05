package quemepongo.dominio.notificador.alerta;

import quemepongo.dominio.notificador.Notificador;

public class AlertaMeteorologica implements Alerta {

    @Override
    public void alertar(Notificador notificador) {
        notificador.notificarAlertaMeteorologica();
    }
}
