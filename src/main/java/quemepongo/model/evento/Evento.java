package quemepongo.model.evento;

import java.time.Duration;
import java.time.LocalDateTime;

import org.uqbar.commons.model.annotations.Observable;

import quemepongo.model.sugerencia.Atuendo;

@Observable
public class Evento {

	private String titulo;
	private Localizacion lugar;
	private Ocurrencia ocurrencia;
	private Atuendo sugerenciaAceptada;

	public Evento(String titulo, Localizacion lugar, Ocurrencia ocurrencia) {
		this.titulo = titulo;
		this.lugar = lugar;
		this.ocurrencia = ocurrencia;
		RepositorioEvento.getInstancia().agregarEvento(this);
	}
	
	public Localizacion getLugar() {
		return this.lugar;
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

	public Ocurrencia getOcurrencia() {
		return ocurrencia;
	}

}