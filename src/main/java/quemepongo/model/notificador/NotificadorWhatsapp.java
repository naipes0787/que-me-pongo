package quemepongo.model.notificador;

public class NotificadorWhatsapp implements Notificador {

	// TODO: Implementar el envío de mensajes vía whatsapp
	@Override
	public void notificarAlertaMeteorologica() {
		System.out.println("Avisando alerta meteorológica por Whatsapp");
	}

	@Override
	public void notificarSugerenciasListas() {
		System.out.println("Avisando alerta evento próximo por Whatsapp");
	}
	
}
