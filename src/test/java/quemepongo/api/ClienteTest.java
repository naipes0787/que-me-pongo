package quemepongo.api;

import quemepongo.api.clientes.ApiDeClima;
import quemepongo.model.FactorClimatico;
import quemepongo.model.Temperatura;
import quemepongo.model.evento.Localizacion;

import java.util.ArrayList;
import java.util.List;

/**
 * Cliente utilizado para simular una API de Clima y realizar así los tests
 */
public class ClienteTest implements ApiDeClima{

    private static final Double TEMPERATURA_FIJA = 10D;
    private List<FactorClimatico> alertasAMandar = new ArrayList<>();

    @Override
    public Temperatura obtenerTemperaturaActual(Localizacion localizacion) {
    	return new Temperatura(TEMPERATURA_FIJA);
    }

    @Override
    public List<FactorClimatico> obtenerAlertasActuales(Localizacion localizacion) {
        return alertasAMandar;
    }

    public void setAlertasAMandar(List<FactorClimatico> alertasAMandar) {
        this.alertasAMandar = alertasAMandar;
    }
}