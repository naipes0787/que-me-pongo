package quemepongo.dominio.notificador;

public class NotificadorWhatsapp implements Notificador {

	private String nroCelular;

	public NotificadorWhatsapp(String nroCelular){
		this.nroCelular = nroCelular;
	}

	// TODO: Implementar el envío de mensajes vía whatsapp
	@Override
	public void notificarAlertaMeteorologica() {
		System.out.println("Avisando alerta meteorológica por Whatsapp a " + this.nroCelular);
	}

	@Override
	public void notificarSugerenciasListas() {
		System.out.println("Avisando alerta evento próximo por Whatsapp a " + this.nroCelular);
	}
	
}
