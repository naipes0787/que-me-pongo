package quemepongo.model.evento;

import java.time.LocalDateTime;

import org.uqbar.commons.model.annotations.Observable;

import quemepongo.exceptions.FechaEventoNoValidaException;
import quemepongo.model.sugerencia.Atuendo;

@Observable
public class Evento{
	
	private String descripcion;
	private Localizacion lugar;
	private LocalDateTime fecha;
	private Atuendo sugerenciaAceptada;
	
	public Evento(Localizacion nuevoLugar, LocalDateTime nuevaFecha, String descripcion) {
		if(nuevaFecha.toLocalDate().isBefore(LocalDateTime.now().toLocalDate())) {
			throw new FechaEventoNoValidaException();
		}
		this.lugar = nuevoLugar;
		this.fecha = nuevaFecha;
		this.descripcion = descripcion;
		RepositorioEvento.getInstancia().agregarEvento(this);
	}
	
	public Localizacion getLugar() {
		return this.lugar;
	}

	public LocalDateTime getFecha() {
		return this.fecha;
	}

	public String getDescripcion() {
		return this.descripcion;
	}
	
	public void setSugerenciaAceptada(Atuendo atuendo) {
		this.sugerenciaAceptada = atuendo;
	}
	
	public Atuendo getSugerenciaAceptada() {
		return sugerenciaAceptada;
	}

}