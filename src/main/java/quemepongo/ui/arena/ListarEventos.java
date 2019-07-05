package quemepongo.ui.arena;

import java.util.LinkedHashSet;
import java.util.Set;

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
		resultados = RepositorioEvento.getInstancia().getEventos();
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