package quemepongo.server.rutas;

import quemepongo.server.controlador.ControladorLogin;
import spark.Request;
import spark.Response;
import spark.Spark;

import static spark.Spark.*;

public class RutasLogin extends Rutas {

    private ControladorLogin controlador = new ControladorLogin();

    @Override
    public void registrar() {

        before(this::verificarLogin);

        get(RutasConstantes.LOGIN_URL,
                controlador::getLoginPage,
                templateEngine);

        post(RutasConstantes.LOGIN_URL,
                controlador::login);

    }

    private void verificarLogin(Request request, Response response) {
        if (!RutasConstantes.LOGIN_URL.equals(request.uri())) {
            String username = request.session().attribute("user");
            if (username == null) {
                response.redirect(RutasConstantes.LOGIN_URL);
                halt();
            }
        }
    }
}
