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

    String nombre;

    Color(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }
}
