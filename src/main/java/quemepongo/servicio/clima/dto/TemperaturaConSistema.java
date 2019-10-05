package quemepongo.servicio.clima.dto;

public class TemperaturaConSistema {

    private Double Value;
    private UnidadDeTemperatura Unit;

    public Double getValue() {
        return Value;
    }

    public void setValue(Double value) {
        Value = value;
    }

    public UnidadDeTemperatura getUnit() {
        return Unit;
    }

    public void setUnit(UnidadDeTemperatura unit) {
        Unit = unit;
    }
}
