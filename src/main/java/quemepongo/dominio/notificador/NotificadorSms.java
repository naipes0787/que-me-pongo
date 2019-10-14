package quemepongo.dominio.notificador;

public class NotificadorSms implements Notificador {

	private String nroCelular;

	public NotificadorSms(String nroCelular){
		this.nroCelular = nroCelular;
	}

	// TODO: Implementar el envío de mensajes vía SMS
	@Override
	public void notificarAlertaMeteorologica() {
		System.out.println("Avisando alerta meteorológica a " + this.nroCelular);
	}

	@Override
	public void notificarSugerenciasListas() {
		System.out.println("Avisando alerta evento próximo a " + this.nroCelular);
	}
	
}
