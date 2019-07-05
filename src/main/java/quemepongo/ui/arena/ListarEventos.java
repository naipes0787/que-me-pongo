package quemepongo.ui.arena;

import org.uqbar.commons.model.annotations.Observable;

@Observable
public class ListarEventos {
	
	String localizacion;
	String fecha;
	
	public void search(){ 
//		// WORKAROUND para que refresque la grilla en las actualizaciones
//		Set<Evento> resultados = new LinkedHashSet<Evento>();
//
//		// FIN WORKAROUND
//		resultados = RepositorioEvento.getInstancia().getEventos();
		
	}

	public void clear(){
		localizacion = null;
		fecha = null;
	}

	public String getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(String localizacion) {
		this.localizacion = localizacion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
}