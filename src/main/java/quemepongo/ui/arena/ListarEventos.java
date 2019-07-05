package quemepongo.ui.arena;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbar.commons.model.annotations.Observable;

import quemepongo.model.evento.Evento;
import quemepongo.model.evento.RepositorioEvento;

@Observable
public class ListarEventos {
	
	String diaDesde;
	String mesDesde;
	String anioDesde;
	String diaHasta;
	String mesHasta;
	String anioHasta;
	Set<Evento> resultados;
	Evento eventoSeleccionado;
	
	public void search(){ 
		// WORKAROUND para que refresque la grilla en las actualizaciones
		resultados = new LinkedHashSet<Evento>();
		// FIN WORKAROUND
		Set<Evento> eventosTotales = RepositorioEvento.getInstancia().getEventos();
		LocalDateTime fechaDesde = LocalDateTime.MIN;
		LocalDateTime fechaHasta = LocalDateTime.MAX;
		if(diaDesde != null && mesDesde != null && anioDesde != null) {
			try {
				Integer diaDesdeInt = Integer.valueOf(diaDesde);
				Integer mesDesdeInt = Integer.valueOf(mesDesde);
				Integer anioDesdeInt = Integer.valueOf(anioDesde);
				fechaDesde = LocalDateTime.of(anioDesdeInt, Month.of(mesDesdeInt), diaDesdeInt, 0, 0);
			} catch(NumberFormatException e) {
				// TODO: Averiguar qué hacer en caso de excepción
			}
		}
			
		if(diaHasta != null && mesHasta != null && anioHasta != null) {
			try {
				Integer diaHastaInt = Integer.valueOf(diaHasta);
				Integer mesHastaInt = Integer.valueOf(mesHasta);
				Integer anioHastaInt = Integer.valueOf(anioHasta);
				fechaHasta = LocalDateTime.of(anioHastaInt, Month.of(mesHastaInt), diaHastaInt, 0, 0);
			} catch(NumberFormatException e) {
				// TODO: Averiguar qué hacer en caso de excepción
			}
		}
		
		final LocalDateTime finalFechaDesde = fechaDesde;
		final LocalDateTime finalFechaHasta = fechaHasta;
		
		resultados = eventosTotales
			.stream()
			.filter(evento -> (
						evento.getFecha().isAfter(finalFechaDesde) && 
						evento.getFecha().isBefore(finalFechaHasta)
					))
			.collect(Collectors.toSet());
	}

	public void clear(){
		this.diaDesde = null;
		this.mesDesde = null;
		this.anioDesde = null;
		this.diaHasta = null;
		this.mesHasta = null;
		this.anioHasta = null;
		this.resultados = null;
		this.eventoSeleccionado = null;
	}

	public String getDiaDesde() {
		return diaDesde;
	}

	public void setDiaDesde(String diaDesde) {
		this.diaDesde = diaDesde;
	}

	public String getMesDesde() {
		return mesDesde;
	}

	public void setMesDesde(String mesDesde) {
		this.mesDesde = mesDesde;
	}

	public String getAnioDesde() {
		return anioDesde;
	}

	public void setAnioDesde(String anioDesde) {
		this.anioDesde = anioDesde;
	}

	public String getDiaHasta() {
		return diaHasta;
	}

	public void setDiaHasta(String diaHasta) {
		this.diaHasta = diaHasta;
	}

	public String getMesHasta() {
		return mesHasta;
	}

	public void setMesHasta(String mesHasta) {
		this.mesHasta = mesHasta;
	}

	public String getAnioHasta() {
		return anioHasta;
	}

	public void setAnioHasta(String anioHasta) {
		this.anioHasta = anioHasta;
	}

	public Set<Evento> getResultados() {
		return resultados;
	}

	public void setResultados(Set<Evento> resultados) {
		this.resultados = resultados;
	}

	public Evento getEventoSeleccionado() {
		return eventoSeleccionado;
	}

	public void setEventoSeleccionado(Evento eventoSeleccionado) {
		this.eventoSeleccionado = eventoSeleccionado;
	}
	
}