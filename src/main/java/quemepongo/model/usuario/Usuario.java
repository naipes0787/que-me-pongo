package quemepongo.model.usuario;

import com.google.common.collect.Sets;
import quemepongo.api.servicio.SelectorDeProveedorDeClima;
import quemepongo.model.guardarropa.GuardarropaCompartido;
import quemepongo.model.sugerencia.Atuendo;
import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.Temperatura;
import quemepongo.model.evento.Evento;
import quemepongo.model.notificador.Alertador;
import quemepongo.model.notificador.AlertadorEmail;
import quemepongo.model.notificador.TipoAlerta;
import quemepongo.model.prenda.Prenda;

import java.util.Set;
import java.util.stream.Collectors;

public class Usuario {

    private Set<Guardarropa> guardarropas = Sets.newHashSet();
    private Set<Evento> eventos = Sets.newHashSet();
    private TipoUsuario tipoUsuario;
    private Alertador alertador;
    private double sensibilidadClima = 1;

    public Usuario() {
    	tipoUsuario = new UsuarioGratuito();
    	// Por default se crea con un alertador por Email
    	alertador = new AlertadorEmail();
    	RepositorioUsuario.getInstancia().agregarUsuario(this);
    }

    public Usuario(TipoUsuario nuevaSuscripcion) {
    	tipoUsuario = nuevaSuscripcion;
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
    
    public TipoUsuario getTipoUsuario() {
    	return tipoUsuario;
    }
    
    public void cambiarSuscripcion(TipoUsuario tipoUsuario) {
    	this.tipoUsuario = tipoUsuario;
	}
	
    public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
    	  tipoUsuario.agregarPrenda(prenda, guardarropa);
    }

    public void aceptarSugerencia(Atuendo atuendo) {
        atuendo.aceptar();
    }

    public void rechazarSugerencia(Atuendo atuendo) {
        atuendo.rechazar();
    }

    public void deshacerUltimaOperacion(Atuendo atuendo) {
        atuendo.deshacerDecision();
    }
    
    public void setAlertador(Alertador alertador) {
    	this.alertador = alertador;
    }
    
    public Alertador getAlertador() {
    	return this.alertador;
    }
    
    public void actuarAnte(TipoAlerta tipoAlerta) {
    	tipoAlerta.alertar(this);
    }

    public boolean aceptoAlgunaPrendaDe(Atuendo atuendo) {
        Set<Prenda> prendasAceptadas = eventos.stream().map(Evento::getSugerenciaAceptada)
                                                       .flatMap(a -> a.prendas().stream())
                                                       .collect(Collectors.toSet());
        return atuendo.prendas().stream().anyMatch(p -> prendasAceptadas.contains(p));
    }
    public double getSensibilidadclima(){
        return sensibilidadClima;
    }
    public double obtenerNivelDeAbrigo(Temperatura temperatura) {
        return temperatura.convertirANivelDeAbrigo() * getSensibilidadclima();
    }

    //En los siguientes 3 metodos se tiene que merguear con la logica que armo Andy en base a las calificaciones
    public boolean esFriolentoDeManos(){
        return true;
    }
    public boolean esFriolentoDeCuello(){
        return true;
    }
    public boolean esFriolentoDeCabeza(){
        return true;
    }

}

