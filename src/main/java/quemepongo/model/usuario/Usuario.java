package quemepongo.model.usuario;

import com.google.common.collect.Sets;
import quemepongo.api.servicio.SelectorDeProveedorDeClima;
import quemepongo.exceptions.GuardarropaNoPerteneceAlUsuarioException;
import quemepongo.model.guardarropa.GuardarropaCompartido;
import quemepongo.model.sugerencia.Atuendo;
import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.Temperatura;
import quemepongo.model.evento.Evento;
import quemepongo.model.notificador.Alertador;
import quemepongo.model.notificador.AlertadorEmail;
import quemepongo.model.notificador.TipoAlerta;
import quemepongo.model.prenda.Prenda;
import quemepongo.model.calificacion.Calificacion;

import java.util.Set;
import java.util.stream.Collectors;

public class Usuario {

    private Set<Guardarropa> guardarropas = Sets.newHashSet();
    private Set<Evento> eventos = Sets.newHashSet();
    private TipoUsuario tipoUsuario;
    private Alertador alertador;
    private Sensibilidad sensibilidad = new Sensibilidad();

    public Usuario() {
    	tipoUsuario = new UsuarioGratuito();
    	// Por default se crea con un alertador por Email
    	alertador = new AlertadorEmail();
    	RepositorioUsuario.getInstancia().agregarUsuario(this);
    }

    public Usuario(TipoUsuario nuevaSuscripcion) {
    	tipoUsuario = nuevaSuscripcion;
        alertador = new AlertadorEmail();
        RepositorioUsuario.getInstancia().agregarUsuario(this);
    }
    
    public void agregarGuardarropa(Guardarropa guardarropa) {
        guardarropas.add(guardarropa);
    }

    public void agregarGuardarropaCompartido(GuardarropaCompartido guardarropa) {
        guardarropas.add(guardarropa);
    }

    public Set<Atuendo> sugerencias(Evento evento) {
        Temperatura temperatura = SelectorDeProveedorDeClima.getInstancia().getProovedorDeClima().obtenerTemperaturaActual(evento.getLugar());
        return guardarropas.stream().flatMap(g -> g.sugerencias(this, obtenerNivelDeAbrigo(temperatura)).stream()).collect(Collectors.toSet());
    }

    public void agregarEvento(Evento evento) {
        eventos.add(evento);
    }
    
    public void cambiarSuscripcion(TipoUsuario tipoUsuario) {
    	this.tipoUsuario = tipoUsuario;
	}
	
    public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
        if(!tieneGuardarropa(guardarropa))
            throw new GuardarropaNoPerteneceAlUsuarioException();

    	tipoUsuario.agregarPrenda(prenda, guardarropa);
    }

    public boolean tieneGuardarropa(Guardarropa guardarropa){ return guardarropas.contains(guardarropa); }

    public void aceptarSugerencia(Atuendo atuendo) {
        atuendo.aceptar();
    }

    public void rechazarSugerencia(Atuendo atuendo) {
        atuendo.rechazar();
    }

    public void deshacerUltimaOperacion(Atuendo atuendo) {
        atuendo.deshacerDecision();
    }
    
    public Alertador getAlertador() {
    	return this.alertador;
    }

    public void actuarAnte(TipoAlerta tipoAlerta) {
    	tipoAlerta.alertar(this);
    }

    public void calificar(Calificacion calificacion){
        sensibilidad.modificarSensibilidad(calificacion);
    }

    public boolean esFriolentoDeManos(){
        return sensibilidad.getSensibilidadManos() > 0;
    }

    public boolean esFriolentoDeCuello(){
        return sensibilidad.getSensibilidadCuello() > 0;
    }

    public boolean esFriolentoDeCabeza() { return sensibilidad.getSensibilidadCabeza() > 0; }

    public boolean aceptoAlgunaPrendaDe(Atuendo atuendo) {
        Set<Prenda> prendasAceptadas = eventos.stream().map(Evento::getSugerenciaAceptada)
                                                       .flatMap(a -> a.prendas().stream())
                                                       .collect(Collectors.toSet());
        return atuendo.prendas().stream().anyMatch(p -> prendasAceptadas.contains(p));
    }
    public double getSensibilidadclima(){
        return sensibilidad.getSensibilidadClima();
    }
    public double obtenerNivelDeAbrigo(Temperatura temperatura) {
        return temperatura.convertirANivelDeAbrigo() * getSensibilidadclima();
    }

    public double getSensibilidadClima() {
        return this.getSensibilidadclima();
    }

    public void setAlertador(Alertador alertador) {
        this.alertador = alertador;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }
}

