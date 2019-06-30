package quemepongo.api.clientes;

import quemepongo.model.Temperatura;
import quemepongo.model.evento.Localizacion;

public interface ApiDeClima {

    Temperatura obtenerTemperaturaActual(Localizacion localizacion);

}
