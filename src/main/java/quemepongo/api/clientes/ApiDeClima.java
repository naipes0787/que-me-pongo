package quemepongo.api.clientes;

import quemepongo.model.Localizacion;

public interface ApiDeClima {

    Double obtenerTemperaturaActual(Localizacion localizacion);
}
