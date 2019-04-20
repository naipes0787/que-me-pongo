package quemepongo.model;

import java.util.Set;

public class Atuendo {

    private Set<Prenda> prendas;

    public Atuendo(Set<Prenda> prendas) {
        this.prendas = prendas;
    }

    public boolean valido() {
        return prendas.stream().map(Prenda::getCategoria).distinct().count() == prendas.size();
    }

    public int partes() {
        return prendas.size();
    }
}
