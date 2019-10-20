package quemepongo.config;

import org.junit.Before;
import quemepongo.dominio.calificacion.Calificacion;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.dominio.usuario.UsuarioPremium;

import static quemepongo.dominio.calificacion.OpcionesCalificacion.*;

public class CalificacionTestConfig extends TestBase {

    //Calificaciones de Prueba
    protected static final Calificacion agradableGeneral = new Calificacion(AGRADABLE, AGRADABLE, AGRADABLE, AGRADABLE);
    protected static final Calificacion calurosoGeneral = new Calificacion(CALUROSO, CALUROSO, CALUROSO, CALUROSO);
    protected static final Calificacion congeladoGeneral = new Calificacion(CONGELADO, CONGELADO, CONGELADO, CONGELADO);
    protected static final Calificacion calurosoSoloCabeza = new Calificacion(AGRADABLE, AGRADABLE, AGRADABLE, CALUROSO);
    protected static final Calificacion calurosoSoloManos = new Calificacion(AGRADABLE, CALUROSO, AGRADABLE, AGRADABLE);
    protected static final Calificacion calurosoSoloCuello = new Calificacion(AGRADABLE, AGRADABLE, CALUROSO, AGRADABLE);
    protected static final Calificacion congeladoSoloCabeza = new Calificacion(AGRADABLE, AGRADABLE, AGRADABLE, CONGELADO);
    protected static final Calificacion congeladoSoloManos = new Calificacion(AGRADABLE, CONGELADO, AGRADABLE, AGRADABLE);
    protected static final Calificacion congeladoSoloCuello = new Calificacion(AGRADABLE, AGRADABLE, CONGELADO, AGRADABLE);

    //Usuario de Prueba
    protected Usuario usuarioBasico;
    protected Usuario usuarioPremium;

    @Before
    public void ejecutarAntesDeCadaTest() {
        usuarioBasico = usuarioBasico();
        usuarioPremium = new Usuario(new UsuarioPremium());
    }
}
