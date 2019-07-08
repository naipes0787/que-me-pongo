package quemepongo.model.evento;

import quemepongo.model.sugerencia.Atuendo;

import java.time.Duration;
import java.time.LocalDateTime;

public class Evento {

	private String titulo;
	private Localizacion lugar;
	private Ocurrencia ocurrencia;
	private Atuendo sugerenciaAceptada;

	public Evento(String titulo, Localizacion lugar, Ocurrencia ocurrencia) {
		this.titulo = titulo;
		this.lugar = lugar;
		this.ocurrencia = ocurrencia;
	}
	
	public Localizacion getLugar() {
		return lugar;
	}

	public LocalDateTime getFecha() {
		return ocurrencia.fechaDelEvento();
	}

	public Atuendo getSugerenciaAceptada() {
		return sugerenciaAceptada;
	}

	public void setSugerenciaAceptada(Atuendo sugerenciaAceptada) {
		this.sugerenciaAceptada = sugerenciaAceptada;
	}

	public boolean estaProximoAOcurrir(Duration tiempoDeAnticipacion) {
		return ocurrencia.estaProxima(tiempoDeAnticipacion);
	}

	public String getTitulo() {
		return titulo;
	}

	public boolean tieneSugerenciaAceptada() {
		return sugerenciaAceptada != null;
	}
}