package quemepongo.model.notificador;

import quemepongo.model.usuario.Usuario;

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
