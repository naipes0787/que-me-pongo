package quemepongo.dominio;

import org.junit.Test;
import quemepongo.config.CalificacionTestConfig;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class CalificacionTest extends CalificacionTestConfig {

    @Test
    public void siUsuarioGratuitoNoRealizaCalificacionAlguna_NoSeraFrioLentoYSuSensibilidadAlClimaSeraDe1(){
        assertEquals(1, usuarioBasico.getSensibilidadClima(), 0);
        assertFalse(usuarioBasico.esFriolentoDeCabeza());
        assertFalse(usuarioBasico.esFriolentoDeManos());
        assertFalse(usuarioBasico.esFriolentoDeCuello());
    }

    @Test
    public void siUsuarioPremiumNoRealizaCalificacionAlguna_NoSeraFrioLentoYSuSensibilidadAlClimaSeraDe1(){
        assertEquals(1, usuarioPremium.getSensibilidadClima(), 0);
        assertFalse(usuarioPremium.esFriolentoDeCabeza());
        assertFalse(usuarioPremium.esFriolentoDeManos());
        assertFalse(usuarioPremium.esFriolentoDeCuello());
    }

    @Test
    public void siUsuarioCalificaAgradable_NoSeModificaLaSensibilidadDelClima(){
        assertEquals(1, usuarioBasico.getSensibilidadClima(), 0);

        usuarioBasico.calificar(agradableGeneral);
        assertEquals(1, usuarioBasico.getSensibilidadClima(), 0);

        usuarioBasico.calificar(agradableGeneral);
        assertEquals(1, usuarioBasico.getSensibilidadClima(), 0);

        usuarioBasico.calificar(agradableGeneral);
        assertEquals(1, usuarioBasico.getSensibilidadClima(), 0);
    }

    @Test
    public void siUsuarioGratuitoRealizaDosCalificacionesCalurosasYUnaCongelada_SuSensibilidadClimaDaria0_9(){
        usuarioBasico.calificar(calurosoGeneral);
        usuarioBasico.calificar(congeladoGeneral);
        usuarioBasico.calificar(calurosoGeneral);

        assertEquals( 0.9, usuarioBasico.getSensibilidadClima(), 0);
    }

    @Test
    public void siUsuarioPremiumRealizaQuinceCalificacionesCalurosasYUnaCongelada_SuSensibilidadClimaDaria_Negativo0_4(){
        for(int i = 0; i < 15; i++){
            usuarioBasico.calificar(calurosoGeneral);
        }
        usuarioBasico.calificar(congeladoGeneral);

        assertEquals( -0.4, usuarioBasico.getSensibilidadClima(), 0.01);
    }

    @Test
    public void siUsuarioGratuitoCalificaMuchosAtuendosCongeladosEnLasManos_EsFrioLentoEnLasManos(){
        usuarioBasico.calificar(congeladoSoloManos);
        usuarioBasico.calificar(congeladoSoloManos);
        usuarioBasico.calificar(congeladoSoloManos);

        assertTrue( usuarioBasico.esFriolentoDeManos() );
        assertEquals(1, usuarioBasico.getSensibilidadClima(), 0);
        assertFalse( usuarioBasico.esFriolentoDeCuello() );
        assertFalse( usuarioBasico.esFriolentoDeCabeza() );
    }

    @Test
    public void siUsuarioPremiumCalificaMuchosAtuendosCalurososEnLasManos_NoEsFrioLentoEnLasManos(){
        usuarioPremium.calificar(calurosoSoloManos);
        usuarioPremium.calificar(calurosoSoloManos);
        usuarioPremium.calificar(calurosoSoloManos);

        assertFalse( usuarioPremium.esFriolentoDeManos() );
    }

    @Test
    public void siUsuarioGratuitoCalificaMuchosAtuendosCongeladosEnElCuello_EsFrioLentoEnElCuello(){
        usuarioBasico.calificar(congeladoSoloCuello);
        usuarioBasico.calificar(congeladoSoloCuello);
        usuarioBasico.calificar(congeladoSoloCuello);

        assertTrue( usuarioBasico.esFriolentoDeCuello() );
        assertEquals(1, usuarioBasico.getSensibilidadClima(), 0);
        assertFalse( usuarioBasico.esFriolentoDeManos() );
        assertFalse( usuarioBasico.esFriolentoDeCabeza() );
    }

    @Test
    public void siUsuarioPremiumCalificaMuchosAtuendosCalurososElCuello_NoEsFrioLentoElCuello(){
        usuarioPremium.calificar(calurosoSoloCuello);
        usuarioPremium.calificar(calurosoSoloCuello);
        usuarioPremium.calificar(calurosoSoloCuello);

        assertFalse( usuarioPremium.esFriolentoDeCuello() );
    }

    @Test
    public void siUsuarioPremiumCalificaMuchosAtuendosCongeladosEnLaCabeza_EsFrioLentoEnLaCabezaPeroNoEnElRestoDelCuerpo(){
        usuarioPremium.calificar(congeladoSoloCabeza);
        usuarioPremium.calificar(congeladoSoloCabeza);
        usuarioPremium.calificar(congeladoSoloCabeza);

        assertTrue( usuarioPremium.esFriolentoDeCabeza() );
        assertEquals(1, usuarioPremium.getSensibilidadClima(), 0);
        assertFalse( usuarioPremium.esFriolentoDeManos() );
        assertFalse( usuarioPremium.esFriolentoDeCuello() );
    }

    @Test
    public void siUsuarioGratuitoCalificaMuchosAtuendosCalurososLaCabeza_NoEsFrioLentoLaCabeza(){
        usuarioBasico.calificar(calurosoSoloCabeza);
        usuarioBasico.calificar(calurosoSoloCabeza);
        usuarioBasico.calificar(calurosoSoloCabeza);

        assertFalse( usuarioBasico.esFriolentoDeCabeza() );
    }

}
