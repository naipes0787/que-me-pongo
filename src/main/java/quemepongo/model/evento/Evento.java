package quemepongo.model.evento;

import quemepongo.exceptions.FechaEventoNoValidaException;
import quemepongo.model.sugerencia.Atuendo;

import java.time.LocalDateTime;

public class Evento{
	private Localizacion lugar;
	private LocalDateTime fecha;
	private Atuendo sugerenciaAceptada;
	
	public Evento(Localizacion nuevoLugar, LocalDateTime nuevaFecha) {
		lugar = nuevoLugar;
		if(nuevaFecha.toLocalDate().isBefore(LocalDateTime.now().toLocalDate())) {
			throw new FechaEventoNoValidaException();
		}
		fecha = nuevaFecha;
	}
	
	public Localizacion getLugar() {
		return lugar;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public Atuendo getSugerenciaAceptada() {
		return sugerenciaAceptada;
	}
}