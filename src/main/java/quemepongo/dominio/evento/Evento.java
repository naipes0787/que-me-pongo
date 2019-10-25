package quemepongo.dominio.evento;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.uqbar.commons.model.annotations.Observable;
import quemepongo.dominio.Entidad;
import quemepongo.dominio.evento.tipo.Anticipacion;
import quemepongo.dominio.evento.tipo.ConversorAnticipacion;
import quemepongo.dominio.evento.tipo.TipoEvento;
import quemepongo.dominio.sugerencia.Atuendo;

import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Observable
@Entity
public class Evento extends Entidad {

	private String titulo;
	@Enumerated
	private Localizacion lugar;
	@OneToOne
	@Cascade({CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
	private TipoEvento tipo;
	@OneToOne
	@Cascade({CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
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

	public boolean tieneSugerenciaAceptada() {
		return sugerenciaAceptada != null;
	}

	public void setSugerenciaAceptada(Atuendo atuendo) {
		this.sugerenciaAceptada = atuendo;
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

}