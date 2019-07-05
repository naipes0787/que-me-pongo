package quemepongo.api.dto;

public class ValorConSistemaDeUnidad {

    private Double Value;
    private Unidad Unit;

    public Double getValue() {
        return Value;
    }

    public void setValue(Double value) {
        Value = value;
    }

    public Unidad getUnit() {
        return Unit;
    }

    public void setUnit(Unidad unit) {
        Unit = unit;
    }
}
