package quemepongo.config;

import com.google.common.collect.Sets;
import org.junit.Before;
import quemepongo.api.ClienteTest;
import quemepongo.api.clientes.ApiDeClima;
import quemepongo.api.servicio.SelectorDeProveedorDeClima;
import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.usuario.Usuario;

import java.util.Arrays;
import java.util.Set;

public abstract class GuardarropaCompartidoTestConfig extends TestConfigGeneral {

    @Before
    public void preparar() {
        ApiDeClima proovedorDeClima = new ClienteTest();
        SelectorDeProveedorDeClima.getInstancia().setProovedorDeClima(proovedorDeClima);
    }

    protected Guardarropa guardarropaCompartido(Usuario... usuarios) {
        Set<Usuario> setUsuarios = Sets.newHashSet(Arrays.asList(usuarios));
        Guardarropa guardarropa = new Guardarropa();
        guardarropa.agregarPrenda(JeanDeOxfordNegro);
        guardarropa.agregarPrenda(CamisaDeAlgodonNegra);
        guardarropa.agregarPrenda(RemeraDeAlgodonNegra);
        guardarropa.agregarPrenda(ZapatillasDeLonaNegras);
        setUsuarios.forEach(u -> u.agregarGuardarropa(guardarropa));
        guardarropa.setUsuarios(setUsuarios);
        return guardarropa;
    }

}
