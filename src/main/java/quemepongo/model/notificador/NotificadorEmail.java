package quemepongo.model.notificador;

public class NotificadorEmail implements Notificador {

	// TODO: Implementar el envío de mensajes vía Email
	@Override
	public void notificarAlertaMeteorologica() {
		System.out.println("Avisando alerta meteorológica por Email");
	}

	@Override
	public void notificarSugerenciasListas() {
		System.out.println("Avisando alerta evento próximo por Email");
	}

}
