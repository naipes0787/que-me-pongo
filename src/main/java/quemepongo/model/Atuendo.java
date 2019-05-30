package quemepongo.model;

public class Atuendo {

    private Prenda prendaSuperior;
    private Prenda prendaInferior;
    private Prenda calzado;
    private Prenda accesorio;

    private EstadoAtuendo estado = EstadoAtuendo.NUEVO;

    public Atuendo(Prenda prendaSuperior, Prenda prendaInferior, Prenda calzado) {
        this.prendaSuperior = prendaSuperior;
        this.prendaInferior = prendaInferior;
        this.calzado = calzado;
    }

    public Atuendo conAccesorio(Prenda accesorio) {
        this.accesorio = accesorio;
        return this;
    }

    public Prenda getPrendaSuperior() {
        return prendaSuperior;
    }

    public Prenda getPrendaInferior() {
        return prendaInferior;
    }

    public Prenda getCalzado() {
        return calzado;
    }

    public Prenda getAccesorio() {
        return accesorio;
    }

    public void aceptar() {
        estado = EstadoAtuendo.ACEPTADO;
    }

    public void rechazar() {
        estado = EstadoAtuendo.RECHAZADO;
    }

    public void deshacerDecision() {
        estado = EstadoAtuendo.NUEVO;
    }

}
