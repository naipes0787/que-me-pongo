package quemepongo.model.notificador;

import quemepongo.model.usuario.Usuario;

public enum TipoAlerta {

	METEOROLOGICA {
		@Override
		public void alertar(Usuario usuario){
			usuario.getAlertador().notificarAlertaMeteorologica();
		}
	},
	EVENTO_PROXIMO {
		@Override
		public void alertar(Usuario usuario){
			usuario.getAlertador().notificarAlertaEventoProximo();
		}
	};
	
	abstract public void alertar(Usuario usuario);

}
