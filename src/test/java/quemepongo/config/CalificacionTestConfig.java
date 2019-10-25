package quemepongo.config;

import org.apache.commons.collections15.map.HashedMap;
import org.junit.Before;
import quemepongo.dominio.calificacion.Calificacion;
import quemepongo.dominio.calificacion.Puntuacion;
import quemepongo.dominio.calificacion.TipoCalificacion;
import quemepongo.dominio.usuario.Usuario;
import quemepongo.dominio.usuario.UsuarioPremium;

import java.util.Map;

import static quemepongo.dominio.calificacion.Puntuacion.*;

public class CalificacionTestConfig extends TestBase {

    //Calificaciones de Prueba
    protected Calificacion agradableGeneral = calificacion(AGRADABLE, AGRADABLE, AGRADABLE, AGRADABLE);
    protected Calificacion calurosoGeneral = calificacion(CALUROSO, CALUROSO, CALUROSO, CALUROSO);
    protected Calificacion congeladoGeneral = calificacion(CONGELADO, CONGELADO, CONGELADO, CONGELADO);
    protected Calificacion calurosoSoloCabeza = calificacion(AGRADABLE, AGRADABLE, AGRADABLE, CALUROSO);
    protected Calificacion calurosoSoloManos = calificacion(AGRADABLE, CALUROSO, AGRADABLE, AGRADABLE);
    protected Calificacion calurosoSoloCuello = calificacion(AGRADABLE, AGRADABLE, CALUROSO, AGRADABLE);
    protected Calificacion congeladoSoloCabeza = calificacion(AGRADABLE, AGRADABLE, AGRADABLE, CONGELADO);
    protected Calificacion congeladoSoloManos = calificacion(AGRADABLE, CONGELADO, AGRADABLE, AGRADABLE);
    protected Calificacion congeladoSoloCuello = calificacion(AGRADABLE, AGRADABLE, CONGELADO, AGRADABLE);

    //Usuario de Prueba
    protected Usuario usuarioBasico;
    protected Usuario usuarioPremium;

    @Before
    public void ejecutarAntesDeCadaTest() {
        usuarioBasico = usuarioBasico();
        usuarioPremium = new Usuario(new UsuarioPremium());
    }

    private Calificacion calificacion(Puntuacion global, Puntuacion manos, Puntuacion cuello, Puntuacion cabeza) {
        Map<TipoCalificacion, Puntuacion> puntuaciones = new HashedMap<TipoCalificacion, Puntuacion>() {{
           put(TipoCalificacion.GLOBAL, global);
           put(TipoCalificacion.MANOS, manos);
           put(TipoCalificacion.CUELLO, cuello);
           put(TipoCalificacion.CABEZA, cabeza);
        }};
        return new Calificacion(puntuaciones);
    }
}
