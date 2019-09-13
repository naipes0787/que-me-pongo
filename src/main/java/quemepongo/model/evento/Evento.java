package quemepongo.model.evento;

import org.uqbar.commons.model.annotations.Observable;
import quemepongo.model.Entidad;
import quemepongo.model.evento.tipo.Anticipacion;
import quemepongo.model.evento.tipo.ConversorAnticipacion;
import quemepongo.model.evento.tipo.TipoEvento;
import quemepongo.model.sugerencia.Atuendo;

import javax.persistence.*;
import java.time.LocalDateTime;

@Observable
@Entity
public class Evento extends Entidad {

	private String titulo;
	@Enumerated
	private Localizacion lugar;
	@OneToOne(cascade = CascadeType.ALL)
	private TipoEvento tipo;
	@Transient
	private Atuendo sugerenciaAceptada;
	@Convert(converter = ConversorAnticipacion.class)
	private Anticipacion anticipacion;

	public Evento(String titulo, Localizacion lugar, TipoEvento tipo, Anticipacion anticipacion) {
		this.titulo = titulo;
		this.lugar = lugar;
		this.tipo = tipo;
		this.anticipacion = anticipacion;
	}

	public Evento() {}

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
		LocalDateTime fechaAnticipacion = anticipacion.getFecha(fechaDelEvento);
		return ahora.isAfter(fechaAnticipacion);
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public boolean tieneSugerenciaAceptada() {
		return sugerenciaAceptada != null;
	}

}