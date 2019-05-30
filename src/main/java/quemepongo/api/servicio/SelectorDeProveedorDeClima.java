package quemepongo.api.servicio;

import quemepongo.api.clientes.ApiDeClima;

public class SelectorDeProveedorDeClima {

    private ApiDeClima proovedorDeClima;

    public SelectorDeProveedorDeClima(ApiDeClima proovedorDeClima) {
        this.proovedorDeClima = proovedorDeClima;
    }

    public ApiDeClima getProovedorDeClima() {
        return proovedorDeClima;
    }

    public void setProovedorDeClima(ApiDeClima proovedorDeClima) {
        this.proovedorDeClima = proovedorDeClima;
    }
}
