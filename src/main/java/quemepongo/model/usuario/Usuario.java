package quemepongo.model.usuario;

import com.google.common.collect.Sets;
import quemepongo.api.servicio.SelectorDeProveedorDeClima;
import quemepongo.model.Temperatura;
import quemepongo.model.calificacion.Calificacion;
import quemepongo.model.evento.Evento;
import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.guardarropa.GuardarropaCompartido;
import quemepongo.model.notificador.Notificador;
import quemepongo.model.notificador.NotificadorEmail;
import quemepongo.model.prenda.Prenda;
import quemepongo.model.sugerencia.Atuendo;

import java.util.Set;
import java.util.stream.Collectors;

public class Usuario {

    private Set<Guardarropa> guardarropas = Sets.newHashSet();
    private Set<Evento> eventos = Sets.newHashSet();
    private TipoUsuario tipoUsuario;
    private Notificador notificador;
    private double sensibilidadClima = 1;
    private double sensibilidadManos;
    private double sensibilidadCuello;
    private double sensibilidadCabeza;

    public Usuario() {
    	tipoUsuario = new UsuarioGratuito();
    	// Por default se crea con un notificador por Email
    	notificador = new NotificadorEmail();
    	RepositorioUsuario.getInstancia().agregarUsuario(this);
    }

    public Usuario(TipoUsuario nuevaSuscripcion) {
    	tipoUsuario = nuevaSuscripcion;
    }
    
    public void agregarGuardarropa(Guardarropa guardarropa) {
        guardarropas.add(guardarropa);
    }

    public void agregarGuardarropa(GuardarropaCompartido guardarropa) {
        guardarropas.add(guardarropa);
    }

    public Set<Atuendo> sugerencias(Evento evento) {
        Temperatura temperatura = SelectorDeProveedorDeClima.getInstancia().getProovedorDeClima().obtenerTemperaturaActual(evento.getLugar());
        return guardarropas.stream().flatMap(g -> g.sugerencias(this, obtenerNivelDeAbrigo(temperatura)).stream()).collect(Collectors.toSet());
    }

    public void agregarEvento(Evento evento) {
        eventos.add(evento);
    }
    
    public TipoUsuario getTipoUsuario() {
    	return tipoUsuario;
    }
    
    public void cambiarSuscripcion(TipoUsuario tipoUsuario) {
    	this.tipoUsuario = tipoUsuario;
	}
	
    public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
    	  tipoUsuario.agregarPrenda(prenda, guardarropa);
    }

    public void aceptarSugerencia(Evento evento, Atuendo atuendo) {
        atuendo.aceptar();
        evento.setSugerenciaAceptada(atuendo);
    }

    public void rechazarSugerencia(Atuendo atuendo) {
        atuendo.rechazar();
    }

    public void deshacerUltimaOperacion(Atuendo atuendo) {
        atuendo.deshacerDecision();
    }
    
    public void setNotificador(Notificador notificador) {
    	this.notificador = notificador;
    }
    
    public Notificador getNotificador() {
    	return this.notificador;
    }

    public boolean estaUsandoAlgunaPrendaDe(Atuendo atuendo) {
        Set<Prenda> prendasEnUso = eventos.stream().filter(Evento::tieneSugerenciaAceptada)
                                                   .map(Evento::getSugerenciaAceptada)
                                                   .flatMap(a -> a.prendas().stream())
                                                   .collect(Collectors.toSet());
        return atuendo.prendas().stream().anyMatch(prendasEnUso::contains);
    }

    public double getSensibilidadClima() {
        return this.sensibilidadClima;
    }

    public void calificar(Calificacion calificacion){
        this.sensibilidadClima += calificacion.getCalificacionGlobal().varianzaSensibilidad;
        this.sensibilidadManos += calificacion.getCalificacionManos().varianzaSensibilidad;
        this.sensibilidadCuello += calificacion.getCalificacionCuello().varianzaSensibilidad;
        this.sensibilidadCabeza += calificacion.getCalificacionCabeza().varianzaSensibilidad;
    }

    public boolean esFriolentoDeManos(){
        return sensibilidadManos > 0;
    }

    public boolean esFriolentoDeCuello(){
        return sensibilidadCuello > 0;
    }

    public boolean esFriolentoDeCabeza(){
        return sensibilidadCabeza > 0;
    }

    public double obtenerNivelDeAbrigo(Temperatura temperatura) {
        return temperatura.convertirANivelDeAbrigo() * getSensibilidadClima();
    }

    public Set<Evento> getEventos() {
        return eventos;
    }
}

