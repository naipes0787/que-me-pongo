package quemepongo.config;

import quemepongo.model.calificacion.Calificacion;
import quemepongo.model.calificacion.OpcionesCalificacion;
import quemepongo.model.prenda.FabricadorTipoCalzado;
import quemepongo.model.prenda.TipoPrenda;
import quemepongo.model.usuario.Usuario;
import quemepongo.model.usuario.UsuarioPremium;

public class UsuarioTestConfig extends TestConfigGeneral {

    //Usuario de Prueba
    protected Usuario johnnyBravo = usuarioBasico();
    protected Usuario montgomeryBurns = new Usuario(new UsuarioPremium());

    //Calificaciones de Prueba
    protected Calificacion agradableGeneral = new Calificacion(OpcionesCalificacion.AGRADABLE, OpcionesCalificacion.AGRADABLE,
            OpcionesCalificacion.AGRADABLE, OpcionesCalificacion.AGRADABLE);
    protected Calificacion calurosoGeneral = new Calificacion(OpcionesCalificacion.CALUROSO, OpcionesCalificacion.CALUROSO,
            OpcionesCalificacion.CALUROSO, OpcionesCalificacion.CALUROSO);
    protected Calificacion congeladoGeneral = new Calificacion(OpcionesCalificacion.CONGELADO, OpcionesCalificacion.CONGELADO,
            OpcionesCalificacion.CONGELADO, OpcionesCalificacion.CONGELADO);

    //Prendas
    protected TipoPrenda PANTUFLAS_CORTE_FINO_DE_RINOCERONTE_ALBINO = TipoPrenda.diseniarTipo(new FabricadorTipoCalzado(10));
}
