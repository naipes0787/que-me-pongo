package quemepongo.dominio.evento;

public enum Mes {
    JANUARY("ENE"), FEBRUARY("FEB"), MARCH("MAR"),
    APRIL("ABR"), MAY("MAY"), JUNE("JUN"),
    JULY("JUL"), AUGUST("AGO"), SEPTEMBER("SEP"),
    OCTOBER("OCT"), NOVEMBER("NOV"), DECEMBER("DIC");

    private String nombreCorto;

    Mes(String nombreCorto){
        this.nombreCorto = nombreCorto;
    }

    public String getNombreCorto() {
        return nombreCorto;
    }
}
