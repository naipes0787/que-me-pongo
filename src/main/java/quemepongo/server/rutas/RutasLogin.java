package quemepongo.server.rutas;

import quemepongo.server.controlador.ControladorLogin;
import spark.Spark;

public class RutasLogin extends Rutas {

    private ControladorLogin controlador = new ControladorLogin();

    @Override
    public void registrar() {

        Spark.get("/login",
                controlador::getLoginPage,
                templateEngine);

        Spark.post("/login",
                controlador::login);

    }
}
