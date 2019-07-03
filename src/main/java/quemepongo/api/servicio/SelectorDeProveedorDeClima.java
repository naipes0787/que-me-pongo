package quemepongo.api.servicio;

import quemepongo.api.clientes.ApiDeClima;
import quemepongo.api.clientes.ClienteAccuWeather;

public class SelectorDeProveedorDeClima {

    private static SelectorDeProveedorDeClima selector;

    private ApiDeClima proveedorDeClima;
    
    private SelectorDeProveedorDeClima() {
    	// Por default se utiliza AccuWeather
    	this.proveedorDeClima = new ClienteAccuWeather();
    }

    public static SelectorDeProveedorDeClima getInstancia() {
        if (selector == null) {
            selector = new SelectorDeProveedorDeClima();
        }
        return selector;
    }

    public ApiDeClima getProovedorDeClima() {
        return proveedorDeClima;
    }

    public void setProovedorDeClima(ApiDeClima proovedorDeClima) {
        this.proveedorDeClima = proovedorDeClima;
    }

}
