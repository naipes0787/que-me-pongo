package quemepongo.api.servicio;

import quemepongo.api.clientes.ApiDeClima;
import quemepongo.api.clientes.ClienteAccuWeather;

public class SelectorDeProveedorDeClima {

    private static SelectorDeProveedorDeClima selector;

    private ApiDeClima proovedorDeClima = new ClienteAccuWeather();

    public static SelectorDeProveedorDeClima getInstancia() {
        if (selector == null) {
            selector = new SelectorDeProveedorDeClima();
        }
        return selector;
    }

    public ApiDeClima getProovedorDeClima() {
        return proovedorDeClima;
    }

    public void setProovedorDeClima(ApiDeClima proovedorDeClima) {
        this.proovedorDeClima = proovedorDeClima;
    }

}
