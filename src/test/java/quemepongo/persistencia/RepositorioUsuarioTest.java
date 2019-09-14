package quemepongo.persistencia;

import org.junit.Test;
import quemepongo.config.GuardarropaCompartidoTestConfig;
import quemepongo.model.usuario.Usuario;

import static org.junit.Assert.assertEquals;

public class RepositorioUsuarioTest extends GuardarropaCompartidoTestConfig {


    @Test
    public void siDosUsuariosTienenElMismoGuardarropa_AlPersistirseElGuardarropaDeberiaTenerALosDosUsuarios() {
        Usuario u1 = usuarioBasico();
        Usuario u2 = usuarioBasico();
        guardarropaCompartido(u1, u2);

        RepositorioUsuario.instancia().guardar(u1);
        RepositorioUsuario.instancia().guardar(u2);

        RepositorioUsuario.instancia().entityManager().detach(u1);

        assertEquals(2, u1.guardarropas().iterator().next().getUsuarios().size());
    }

}
