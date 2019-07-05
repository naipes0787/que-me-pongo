package quemepongo.model.notificador;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class DisparadorComprobadorAlertas {

    private static final int frecuenciaEnSegundos = 10;

    public void disparar() throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        JobDetail comprobarAlertas = JobBuilder.newJob(ComprobadorDeAlertas.class).build();
        scheduler.scheduleJob(comprobarAlertas, cadaCiertoTiempo());
    }

    private Trigger cadaCiertoTiempo() {
        return TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(frecuenciaEnSegundos).repeatForever())
                .build();

    }
}
