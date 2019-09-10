package quemepongo.model.evento;

import org.uqbar.commons.model.annotations.Observable;
import quemepongo.model.evento.tipo.TipoEvento;
import quemepongo.model.sugerencia.Atuendo;

import java.time.Duration;
import java.time.LocalDateTime;

@Observable
public class Evento {

	private String titulo;
	private Localizacion lugar;
	private TipoEvento tipo;
	private Atuendo sugerenciaAceptada;
	private Duration anticipacion;

	public Evento(String titulo, Localizacion lugar, TipoEvento tipo, Duration anticipacion) {
		this.titulo = titulo;
		this.lugar = lugar;
		this.tipo = tipo;
		this.anticipacion = anticipacion;
		RepositorioEvento.getInstancia().agregarEvento(this);
	}

	public Localizacion getLugar() {
		return this.lugar;
	}

	public LocalDateTime getFecha() {
		return tipo.getFecha();
	}

	public Atuendo getSugerenciaAceptada() {
		return sugerenciaAceptada;
	}

	public void setSugerenciaAceptada(Atuendo sugerenciaAceptada) {
		this.sugerenciaAceptada = sugerenciaAceptada;
	}

	/**
	 * @return
	 * true si la fecha del evento se encuentra a x tiempo de su fecha (indicado por el campo anticipacion) y
	 * false si el evento ya pasó
	 */
	public boolean estaProximo() {
		LocalDateTime fechaDelEvento = tipo.getFecha();
		LocalDateTime ahora = LocalDateTime.now();
		if (ahora.isAfter(fechaDelEvento)) {
			return false;
		}
		LocalDateTime fechaAnticipacion = fechaDelEvento.minus(anticipacion);
		return ahora.isAfter(fechaAnticipacion);
	}

	public String getTitulo() {
		return titulo;
	}

	public boolean tieneSugerenciaAceptada() {
		return sugerenciaAceptada != null;
	}

}