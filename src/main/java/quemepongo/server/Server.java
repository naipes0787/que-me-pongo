package quemepongo.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import quemepongo.server.rutas.RutasGuardarropa;
import spark.Spark;

public class Server {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        new Bootstrap().ejecutar();

        Spark.port(9000);
        Spark.staticFiles.location("/public");
        Spark.init();

        Spark.exception(Exception.class, (e, req, res) -> logger.error("Ocurrió un error", e));

        new RutasGuardarropa().registrar();
    }

}
