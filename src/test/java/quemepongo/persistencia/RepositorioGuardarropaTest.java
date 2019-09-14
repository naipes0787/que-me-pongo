package quemepongo.persistencia;

import org.junit.Test;
import quemepongo.config.GuardarropaCompartidoTestConfig;
import quemepongo.model.usuario.Usuario;

public class RepositorioGuardarropaTest extends GuardarropaCompartidoTestConfig {


    @Test
    public void test() {
        Usuario u1 = usuarioBasico();
        Usuario u2 = usuarioBasico();
        guardarropaCompartido(u1, u2);

        RepositorioUsuario.instancia().guardar(u1);
        RepositorioUsuario.instancia().guardar(u2);

        RepositorioUsuario.instancia().entityManager().detach(u1);

        Usuario u= RepositorioUsuario.instancia().entityManager().find(Usuario.class, u1.getId());

//        Usuario u3 = usuarioConGuardarropas(guardarropaCon(JeanDeOxfordNegro, RemeraDeAlgodonNegra));
//        assertEquals("Guardarropa", u.guardarropas().iterator().next().getClass().getSimpleName());

    }

}
