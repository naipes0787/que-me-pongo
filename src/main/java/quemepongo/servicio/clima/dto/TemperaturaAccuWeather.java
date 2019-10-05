package quemepongo.servicio.clima.dto;

public class TemperaturaAccuWeather {

    private TemperaturaConSistema Metric;
    private TemperaturaConSistema Imperial;

    public TemperaturaConSistema getMetric() {
        return Metric;
    }

    public void setMetric(TemperaturaConSistema metric) {
        Metric = metric;
    }

    public TemperaturaConSistema getImperial() {
        return Imperial;
    }

    public void setImperial(TemperaturaConSistema imperial) {
        Imperial = imperial;
    }
}
