package quemepongo.server;

import com.google.common.collect.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import quemepongo.server.rutas.*;
import quemepongo.util.RepositorioImagenes;
import spark.Spark;

import static spark.Spark.staticFiles;

public class Server implements WithGlobalEntityManager, TransactionalOps {
    private static final Logger logger = LoggerFactory.getLogger(Server.class);

    public static void main(String[] args) {
        new Server().iniciar();
    }

    private void iniciar() {
        new DataInicial().cargar();

        Spark.port(9000);
        Spark.staticFileLocation("/public");
        staticFiles.externalLocation(RepositorioImagenes.instancia().dir());
        Spark.init();

        Spark.before((req, res) -> beginTransaction());
        Spark.after((req, res) -> commitTransaction());
        Spark.exception(Exception.class,
            (e, req, res) -> {
                logger.error("Ocurrió un error", e);
                rollbackTransaction();
                res.status(500);
                res.redirect("/error");
            }
        );

        Lists.newArrayList(
                new RutasGuardarropas(),
                new RutasAtuendos(),
                new RutasLogin(),
                new RutasHome(),
                new RutasQuienesSomos(),
                new RutasEventos(),
                new RutasPrendas(),
                new RutasSugerencias(),
                new RutasError()
        ).forEach(Rutas::registrar);
    }

}