package quemepongo.model.notificador;

public class NotificadorEmail implements Notificador {

	private String email;

	public NotificadorEmail(String email){
		this.email = email;
	}

	// TODO: Implementar el envío de mensajes vía Email
	@Override
	public void notificarAlertaMeteorologica() {
		System.out.println("Avisando alerta meteorológica a " + this.email);
	}

	@Override
	public void notificarSugerenciasListas() {
		System.out.println("Avisando alerta evento próximo a " + this.email);
	}

}
