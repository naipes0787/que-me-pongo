package quemepongo.server.rutas;

import quemepongo.server.controlador.ControladorLogin;
import spark.Spark;

public class RutasLogin extends Rutas {

    private ControladorLogin controlador = new ControladorLogin();

    @Override
    public void registrar() {

        Spark.get(RutasConstantes.LOGIN_URL,
                controlador::getLoginPage,
                templateEngine);

        Spark.post(RutasConstantes.LOGIN_URL,
                controlador::login);

    }
}
