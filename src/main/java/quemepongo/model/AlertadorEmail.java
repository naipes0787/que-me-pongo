package quemepongo.model;

public class AlertadorEmail implements Alertador{

	// TODO: Implementar el envío de mensajes vía Email
	@Override
	public void notificarAlertaMeteorologica() {
		System.out.println("Avisando alerta meteorológica por Email");
	}

	@Override
	public void notificarAlertaEventoProximo() {
		System.out.println("Avisando alerta evento próximo por Email");
	}

}
