package quemepongo.persistencia;

import org.junit.Test;
import quemepongo.config.GuardarropaCompartidoTestConfig;
import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.usuario.Usuario;

import static org.junit.Assert.assertEquals;

public class RepositorioGuardarropaTest extends GuardarropaCompartidoTestConfig {

    @Test
    public void test() {
        Guardarropa g = guardarropaCon(JeanDeOxfordNegro, RemeraDeAlgodonNegra);
        Usuario u = usuarioConGuardarropas(g);

        Usuario usuario1 = usuarioBasico();
        Usuario usuario2 = usuarioBasico();
        guardarropaCompartido(usuario1, usuario2);

        RepositorioUsuario.instancia().guardar(usuario1);
        RepositorioUsuario.instancia().guardar(usuario2);

        RepositorioUsuario.instancia().entityManager().detach(usuario1);

        Usuario usuario = RepositorioUsuario.instancia().entityManager().find(Usuario.class, usuario1.getId());
        assertEquals("GuardarropaCompartido", usuario.guardarropas().iterator().next().getClass().getSimpleName());

        RepositorioUsuario.instancia().guardar(u);
        assertEquals("Guardarropa", u.guardarropas().iterator().next().getClass().getSimpleName());
    }
}
