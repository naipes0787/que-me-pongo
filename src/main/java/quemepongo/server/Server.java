package quemepongo.server;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import quemepongo.server.rutas.*;
import spark.Spark;

public class Server implements WithGlobalEntityManager, TransactionalOps {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        new Server().iniciar();
    }

    private void iniciar() {
        new DataInicial().cargar();

        Spark.port(9000);
        Spark.staticFileLocation("/public");
        Spark.init();

        Spark.before((req, res) -> beginTransaction());
        Spark.after((req, res) -> commitTransaction());
        Spark.exception(Exception.class,
            (e, req, res) -> {
                logger.error("Ocurrió un error", e);
                rollbackTransaction();
            }
        );

        Lists.newArrayList(
                new RutasGuardarropas(),
                new RutasAtuendos(),
                new RutasLogin(),
                new RutasHome(),
                new RutasEventos(),
                new RutasPrendas(),
                new RutasSugerencias()
        ).forEach(Rutas::registrar);
    }

}