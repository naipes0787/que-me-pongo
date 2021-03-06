package quemepongo.ui.arena.evento;

import com.google.common.collect.Sets;
import org.uqbar.commons.model.annotations.Observable;
import quemepongo.dominio.evento.Evento;
import quemepongo.persistencia.RepositorioEvento;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Clase encargada de listar eventos, mapeando a Evento
 * 
 */
@Observable
public class ListarEventos {

	private String diaDesde;
	private String mesDesde;
	private String anioDesde;
	private String diaHasta;
	private String mesHasta;
	private String anioHasta;
	private Set<Evento> resultados = Sets.newHashSet();
	private Evento eventoSeleccionado;
	
	public void search(){ 
		List<Evento> eventosTotales = RepositorioEvento.instancia().getEventos();

		final LocalDateTime fechaDesde = getFechaDesdeInput(diaDesde, mesDesde, anioDesde)
				.orElse(LocalDateTime.MIN);
		final LocalDateTime fechaHasta = getFechaDesdeInput(diaHasta, mesHasta, anioHasta)
				.orElse(LocalDateTime.MAX);
		
		resultados = eventosTotales
			.stream()
			.filter(evento -> (
						evento.getFecha().isAfter(fechaDesde) && 
						evento.getFecha().isBefore(fechaHasta)
					))
			.collect(Collectors.toSet());
	}

	/**
	 * Devuelve un Optional con la fecha generada a partir del input brindado, en caso de que 
	 * con el input no pueda generarse una fecha se devolverá un Optional vacío
	 * @param dia
	 * @param mes
	 * @param anio
	 * @return Optional<LocalDateTime>
	 */
	private Optional<LocalDateTime> getFechaDesdeInput(String dia, String mes, String anio) {
		LocalDateTime fecha = null;
		if(dia != null && mes != null && anio != null) {
			try {
				Integer diaInt = Integer.valueOf(dia);
				Integer mesInt = Integer.valueOf(mes);
				Integer anioInt = Integer.valueOf(anio);
				fecha = LocalDateTime.of(anioInt, Month.of(mesInt), diaInt, 0, 0);
			} catch(NumberFormatException e) {
				fecha = null;
			}
		}
		return Optional.ofNullable(fecha);
	}
	
	public void clear(){
		this.diaDesde = null;
		this.mesDesde = null;
		this.anioDesde = null;
		this.diaHasta = null;
		this.mesHasta = null;
		this.anioHasta = null;
		this.resultados = Sets.newHashSet();
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