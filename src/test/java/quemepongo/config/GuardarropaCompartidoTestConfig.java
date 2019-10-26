package quemepongo.config;

import com.google.common.collect.Sets;
import org.junit.Before;
import quemepongo.dominio.guardarropa.GuardarropaCompartido;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.servicio.ClienteTest;
import quemepongo.servicio.clima.ApiDeClima;
import quemepongo.servicio.clima.SelectorDeProveedorDeClima;

import java.util.Arrays;
import java.util.Set;

public abstract class GuardarropaCompartidoTestConfig extends TestBase {

    @Before
    public void preparar() {
        ApiDeClima proovedorDeClima = new ClienteTest();
        SelectorDeProveedorDeClima.getInstancia().setProovedorDeClima(proovedorDeClima);
    }

    protected GuardarropaCompartido guardarropaCompartido(Usuario... usuarios) {
        Set<Usuario> setUsuarios = Sets.newHashSet(Arrays.asList(usuarios));
        GuardarropaCompartido guardarropa = new GuardarropaCompartido(setUsuarios);
        guardarropa.agregarPrenda(JeanDeOxfordNegro);
        guardarropa.agregarPrenda(CamisaDeAlgodonNegra);
        guardarropa.agregarPrenda(RemeraDeAlgodonNegra);
        guardarropa.agregarPrenda(ZapatillasDeLonaNegras);
        setUsuarios.forEach(u -> u.agregarGuardarropa(guardarropa));
        return guardarropa;
    }

}
