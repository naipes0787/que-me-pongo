package quemepongo.frecuencia;

public class FrecuenciaBuilder {
    
    private FormatoFrecuencia formato;

    private int minuto;
    private int hora;
    private int mes;
    private int diaDelMes;
    private int diaDeLaSemana;
    private int anio;
    
    public FrecuenciaBuilder(FormatoFrecuencia formato) {
        this.formato = formato;
    }

    public FrecuenciaBuilder setMinuto(int minuto) {
        this.minuto = minuto;
        return this;
    }

    public int getMinuto() {
        return minuto;
    }

    public FrecuenciaBuilder setHora(int hora) {
        this.hora = hora;
        return this;
    }

    public int getHora() {
        return hora;
    }

    public FrecuenciaBuilder setMes(int mes) {
        this.mes = mes;
        return this;
    }

    public int getMes() {
        return mes;
    }

    public FrecuenciaBuilder setDiaDelMes(int diaDelMes) {
        this.diaDelMes = diaDelMes;
        return this;
    }

    public int getDiaDelMes() {
        return diaDelMes;
    }

    public FrecuenciaBuilder setDiaDeLaSemana(int diaDeLaSemana) {
        this.diaDeLaSemana = diaDeLaSemana;
        return this;
    }

    public int getDiaDeLaSemana() {
        return diaDeLaSemana;
    }

    public FrecuenciaBuilder setAnio(int anio) {
        this.anio = anio;
        return this;
    }

    public int getAnio() {
        return anio;
    }

    public Frecuencia build() {
        return new Frecuencia(formato.obtenerExpresionCron(this));
    }
    
}
