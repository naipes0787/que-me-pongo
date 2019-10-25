package quemepongo.dominio.prenda;

public enum Color {
    ROJO("Rojo"),
    AZUL("Azul"),
    AMARILLO("Amarillo"),
    VERDE("Verde"),
    NARANJA("Naranja"),
    VIOLETA("Violeta"),
    NEGRO("Negro"),
    BLANCO("Blanco"),
    MARRON("Marrón"),
    BEIGE("Beige");

    private String nombreAMostrar;

    Color(String nombreAMostrar) {
        this.nombreAMostrar = nombreAMostrar;
    }

    public String getNombre() {
        return nombreAMostrar;
    }
}
