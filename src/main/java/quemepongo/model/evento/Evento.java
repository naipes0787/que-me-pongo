package quemepongo.model.evento;

import org.uqbar.commons.model.annotations.Observable;
import quemepongo.model.sugerencia.Atuendo;
import quemepongo.persistencia.RepositorioEvento;

import java.time.Duration;
import java.time.LocalDateTime;

@Observable
public class Evento {

	private String titulo;
	private Localizacion lugar;
	private Ocurrencia ocurrencia;
	private Atuendo sugerenciaAceptada;
	private Duration anticipacion;

	public Evento(String titulo, Localizacion lugar, Ocurrencia ocurrencia, Duration anticipacion) {
		this.titulo = titulo;
		this.lugar = lugar;
		this.ocurrencia = ocurrencia;
		this.anticipacion = anticipacion;
		RepositorioEvento.getInstancia().agregarEvento(this); }

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

	public boolean estaProximo() {
		return ocurrencia.estaProxima(anticipacion);
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