package quemepongo.server.controlador;

import quemepongo.server.rutas.RutasConstantes;
import spark.Request;
import spark.Response;

/**
 * Clase que provee una verificación para comprobar si el usuario está logueado. Los controladores que requieran
 * autenticación deberían heredar de esta clase
 */
public class ControladorAutenticado {

    protected void autenticar(Request request, Response response) {
        String username = request.session().attribute("user");
        if (username == null){
            response.redirect(RutasConstantes.LOGIN_URL);
        }
    }

}
