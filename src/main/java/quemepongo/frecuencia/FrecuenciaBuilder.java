package quemepongo.frecuencia;

public class FrecuenciaBuilder {
    
    private FormatoFrecuencia formato;

    private Integer minuto;
    private Integer hora;
    private Integer mes;
    private Integer diaDelMes;
    private Integer diaDeLaSemana;
    private Integer anio;
    
    public FrecuenciaBuilder(FormatoFrecuencia formato) {
        this.formato = formato;
    }

    public FrecuenciaBuilder setMinuto(int minuto) {
        this.minuto = minuto;
        return this;
    }

    public Integer getMinuto() {
        return minuto;
    }

    public FrecuenciaBuilder setHora(int hora) {
        this.hora = hora;
        return this;
    }

    public Integer getHora() {
        return hora;
    }

    public FrecuenciaBuilder setMes(int mes) {
        this.mes = mes;
        return this;
    }

    public Integer getMes() {
        return mes;
    }

    public FrecuenciaBuilder setDiaDelMes(int diaDelMes) {
        this.diaDelMes = diaDelMes;
        return this;
    }

    public Integer getDiaDelMes() {
        return diaDelMes;
    }

    public FrecuenciaBuilder setDiaDeLaSemana(int diaDeLaSemana) {
        this.diaDeLaSemana = diaDeLaSemana;
        return this;
    }

    public Integer getDiaDeLaSemana() {
        return diaDeLaSemana;
    }

    public FrecuenciaBuilder setAnio(int anio) {
        this.anio = anio;
        return this;
    }

    public Integer getAnio() {
        return anio;
    }

    public Frecuencia build() {
        return new Frecuencia(formato.obtenerExpresionCron(this));
    }
    
}
