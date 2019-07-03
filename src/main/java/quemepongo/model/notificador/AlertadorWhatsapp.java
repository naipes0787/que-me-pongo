package quemepongo.model.notificador;

public class AlertadorWhatsapp implements Alertador{

	// TODO: Implementar el envío de mensajes vía whatsapp
	@Override
	public void notificarAlertaMeteorologica() {
		System.out.println("Avisando alerta meteorológica por Whatsapp");
	}

	@Override
	public void notificarAlertaEventoProximo() {
		System.out.println("Avisando alerta evento próximo por Whatsapp");
	}
	
}
