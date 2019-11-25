package quemepongo.server.controlador.prendas;

import com.google.common.collect.Lists;
import quemepongo.dominio.prenda.CreadorDePrenda;

import java.util.List;

public class WizardPrenda {

    private List<FormularioPrenda> pasos;

    public WizardPrenda() {
        CreadorDePrenda builder = new CreadorDePrenda();
        pasos = Lists.newArrayList(
            new FormularioPrendaTipo(builder),
            new FormularioPrendaDetalles(builder),
            new FormularioPrendaGuardar(builder)
        );
    }

    public FormularioPrenda obtenerFormulario(int paso) {
        return pasos.stream().filter(p -> paso == p.getPaso()).findFirst().orElseGet(() -> pasos.get(0));
    }

    public boolean esUltimoPaso(int paso) {
        return paso == pasos.size();
    }
}
