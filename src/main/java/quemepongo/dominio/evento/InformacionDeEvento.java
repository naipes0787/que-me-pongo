package quemepongo.dominio.evento;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class InformacionDeEvento {

    private String nombre;
    private Integer numeroDia;
    private String diaDeLaSemana;
    private String mes;
    private String cuandoEmpieza;
    private String donde;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getNumeroDia() {
        return numeroDia;
    }

    public void setNumeroDia(Integer numeroDia) {
        this.numeroDia = numeroDia;
    }

    public String getDiaDeLaSemana() {
        return diaDeLaSemana;
    }

    public String getDonde() {
        return donde;
    }

    public void setDiaDeLaSemana(String diaDeLaSemana) {
        this.diaDeLaSemana = diaDeLaSemana;
    }

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }

    public String getCuandoEmpieza() {
        return cuandoEmpieza;
    }

    public void setCuandoEmpieza(String cuandoEmpieza) {
        this.cuandoEmpieza = cuandoEmpieza;
    }

    public void setDonde(String donde) {
        this.donde = donde;
    }

    public static List<InformacionDeEvento> armarEventosParaCalendario(List<Evento> eventos){
        return eventos.stream().map(InformacionDeEvento::armarEventoParaCalendario).collect(Collectors.toList());
    }

    private static InformacionDeEvento armarEventoParaCalendario(Evento evento){
        InformacionDeEvento infoDeEvento = new InformacionDeEvento();
        infoDeEvento.setNombre(evento.getTitulo());
        LocalDateTime fecha = evento.getFecha();
        infoDeEvento.setNumeroDia(fecha.getDayOfMonth());
        infoDeEvento.setDiaDeLaSemana(fecha.getDayOfWeek().name());
        infoDeEvento.setMes(Mes.valueOf(fecha.getMonth().name()).getNombreCorto());
        infoDeEvento.setCuandoEmpieza(fecha.getHour()+":"+fecha.getMinute());
        infoDeEvento.setDonde(evento.getLugar().getNombre());
        return infoDeEvento;
    }
}
