package quemepongo.model.notificador;

public class AlertadorSms implements Alertador{

	// TODO: Implementar el envío de mensajes vía SMS
	@Override
	public void notificarAlertaMeteorologica() {
		System.out.println("Avisando alerta meteorológica por SMS");
	}

	@Override
	public void notificarAlertaEventoProximo() {
		System.out.println("Avisando alerta evento próximo por SMS");
	}
	
}
