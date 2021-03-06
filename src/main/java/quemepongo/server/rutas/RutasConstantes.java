package quemepongo.server.rutas;

public final class RutasConstantes {

    public static final String LOGIN_URL = "/login";
    public static final String LOGOUT_URL = "/logout";
    public static final String HOME_URL = "/home";
    public static final String QUIENES_SOMOS_URL = "/quienes-somos";

    public static final String FORMULARIO_ALTA_EVENTOS = "/eventos/creacion";
    public static final String EVENTOS_URL = "/eventos";
    public static final String EVENTO_URL = "/eventos/:id";

    public static final String PRENDAS_URL = "/guardarropas/:id/prendas";
    public static final String FORMULARIO_ALTA_PRENDAS = "/guardarropas/:id/prendas/nueva";

    public static final String ATUENDOS_URL = "/atuendos";

    public static final String CALIFICACIONES_URL = "/atuendos/:id/calificaciones";

    public static final String SUGERIR_URL = "/eventos/:id/sugerencias";

}
