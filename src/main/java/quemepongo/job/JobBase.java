package quemepongo.job;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

abstract public class JobBase implements Job {

    private int frecuencia;

    JobBase(int frencuenciaEnSegundos) {
        frecuencia = frencuenciaEnSegundos;
    }

    public void iniciar() throws SchedulerException {
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        JobDetail comprobarAlertas = JobBuilder.newJob(this.getClass()).build();
        scheduler.start();
        scheduler.scheduleJob(comprobarAlertas, disparador());
    }

    private Trigger disparador() {
        return TriggerBuilder.newTrigger()
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(frecuencia).repeatForever())
                .build();

    }
}