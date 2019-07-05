package quemepongo.api.dto;

public class Velocidad {

    private ValorConSistemaDeUnidad Metric;
    private ValorConSistemaDeUnidad Imperial;

    public ValorConSistemaDeUnidad getMetric() {
        return Metric;
    }

    public void setMetric(ValorConSistemaDeUnidad metric) {
        Metric = metric;
    }

    public ValorConSistemaDeUnidad getImperial() {
        return Imperial;
    }

    public void setImperial(ValorConSistemaDeUnidad imperial) {
        Imperial = imperial;
    }
}
