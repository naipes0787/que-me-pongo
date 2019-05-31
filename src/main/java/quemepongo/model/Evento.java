package quemepongo.model;

import java.time.LocalDateTime;

import quemepongo.exceptions.FechaEventoNoValidaException;

public class Evento{
	private Localizacion lugar;
	private LocalDateTime fecha;
	
	public Evento(Localizacion nuevoLugar, LocalDateTime nuevaFecha) {
		lugar = nuevoLugar;
		if(nuevaFecha.isBefore(LocalDateTime.now()))
			throw new FechaEventoNoValidaException();
		fecha = nuevaFecha;
	}
	
	public Localizacion getLugar() {
		return lugar;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}
}