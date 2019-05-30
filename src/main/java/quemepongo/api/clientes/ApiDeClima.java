package quemepongo.api.clientes;

import quemepongo.model.Localizacion;
import quemepongo.model.Temperatura;

public interface ApiDeClima {

    Temperatura obtenerTemperaturaActual(Localizacion localizacion);
}
