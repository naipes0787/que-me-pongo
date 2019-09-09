package quemepongo.frecuencia;

import com.cronutils.model.Cron;
import com.cronutils.model.time.ExecutionTime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

public class Frecuencia {
    private FormatoFrecuencia formato;
    private int minuto;
    private int hora;
    private int mes;
    private int diaDelMes;
    private int diaDeLaSemana;
    private int anio;

    public Frecuencia(FormatoFrecuencia formato) {
        this.formato = formato;
    }

    public Frecuencia setMinuto(int minuto) {
        this.minuto = minuto;
        return this;
    }

    public int getMinuto() {
        return minuto;
    }

    public Frecuencia setHora(int hora) {
        this.hora = hora;
        return this;
    }

    public int getHora() {
        return hora;
    }

    public Frecuencia setMes(int mes) {
        this.mes = mes;
        return this;
    }

    public int getMes() {
        return mes;
    }

    public Frecuencia setDiaDelMes(int diaDelMes) {
        this.diaDelMes = diaDelMes;
        return this;
    }

    public int getDiaDelMes() {
        return diaDelMes;
    }

    public Frecuencia setDiaDeLaSemana(int diaDeLaSemana) {
        this.diaDeLaSemana = diaDeLaSemana;
        return this;
    }

    public int getDiaDeLaSemana() {
        return diaDeLaSemana;
    }

    public Frecuencia setAnio(int anio) {
        this.anio = anio;
        return this;
    }

    public int getAnio() {
        return anio;
    }

    public LocalDateTime proximaOcurrencia() {
        Cron cron = formato.obtenerExpresionCron(this);
        ZonedDateTime ahora = LocalDateTime.now().atZone(ZoneId.systemDefault());
        ZonedDateTime proxima = ExecutionTime.forCron(cron).nextExecution(ahora).orElseThrow(() -> new RuntimeException(""));
        return proxima.toLocalDateTime();
    }
}
