package quemepongo.model;

public enum TipoAlerta {

	METEOROLOGICA{
		public void alertar(Usuario usuario){
			usuario.getAlertador().notificarAlertaMeteorologica();
		}
	},
	EVENTO_PROXIMO{
		public void alertar(Usuario usuario){
			usuario.getAlertador().notificarAlertaEventoProximo();
		}
	};
	
	public void alertar(Usuario usuario) {
		
	}
	
}
