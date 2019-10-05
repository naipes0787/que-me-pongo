package quemepongo.dominio.frecuencia;

import java.util.function.Function;

// Formato de expresion: {segundo} {minuto} {hora} {diaDelMes} {mes} {diaDeSemana} {año}
public enum FormatoFrecuencia {
    DIARIA(f -> String.format("0 %s %s * * ? *", f.getMinuto(), f.getHora())),
    SEMANAL(f -> String.format("0 %s %s ? * %s *", f.getMinuto(), f.getHora(), f.getDiaDeLaSemana())),
    MENSUAL(f -> String.format("0 %s %s %s * ? *", f.getMinuto(), f.getHora(), f.getDiaDelMes())),
    ANUAL(f -> String.format("0 %s %s %s %s ? *", f.getMinuto(), f.getHora(), f.getDiaDelMes(), f.getMes()));

    private Function<FrecuenciaBuilder, String> formateador;

    FormatoFrecuencia(Function<FrecuenciaBuilder, String> formateador) {
        this.formateador = formateador;
    }

    public String obtenerExpresionCron(FrecuenciaBuilder datosFrecuencia) {
        return formateador.apply(datosFrecuencia);
    }

}
