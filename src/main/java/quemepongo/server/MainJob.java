package quemepongo.server;

import org.quartz.SchedulerException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;
import quemepongo.job.ComprobadorDeAlertas;

public class MainJob implements WithGlobalEntityManager, TransactionalOps {
    private static final Logger logger = LoggerFactory.getLogger(MainJob.class);

    public static void main(String[] args) throws SchedulerException {
        logger.info("Starting jobs");
        new ComprobadorDeAlertas().iniciar();
    }

}