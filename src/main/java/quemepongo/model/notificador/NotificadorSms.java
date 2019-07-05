package quemepongo.model.notificador;

public class NotificadorSms implements Notificador {

	// TODO: Implementar el envío de mensajes vía SMS
	@Override
	public void notificarAlertaMeteorologica() {
		System.out.println("Avisando alerta meteorológica por SMS");
	}

	@Override
	public void notificarSugerenciasListas() {
		System.out.println("Avisando alerta evento próximo por SMS");
	}
	
}
