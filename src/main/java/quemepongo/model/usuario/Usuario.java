package quemepongo.model.usuario;

import com.google.common.collect.Sets;
import quemepongo.api.servicio.SelectorDeProveedorDeClima;
import quemepongo.exceptions.GuardarropaNoPerteneceAlUsuarioException;
import quemepongo.model.Entidad;
import quemepongo.model.Temperatura;
import quemepongo.model.calificacion.Calificacion;
import quemepongo.model.evento.Evento;
import quemepongo.model.guardarropa.Guardarropa;
import quemepongo.model.guardarropa.GuardarropaCompartido;
import quemepongo.model.notificador.Notificador;
import quemepongo.model.prenda.Prenda;
import quemepongo.model.sugerencia.Atuendo;
import quemepongo.model.sugerencia.ComandoAtuendo;
import quemepongo.model.sugerencia.ComandoAtuendoAceptar;
import quemepongo.model.sugerencia.ComandoAtuendoRechazar;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Usuario extends Entidad {

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "usuario_guardarropa",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "guardarropa_id")
    )
    private Set<Guardarropa> guardarropas = Sets.newHashSet();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "usuario_id")
    private Set<Evento> eventos = Sets.newHashSet();

    @OneToOne(cascade = CascadeType.ALL)
    private TipoUsuario tipoUsuario;

    @Transient
    private Sensibilidad sensibilidad = new Sensibilidad();

    @Transient
    private Notificador notificador;

    public Usuario() {
    	tipoUsuario = new UsuarioGratuito();
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
    
    public void cambiarSuscripcion(TipoUsuario tipoUsuario) {
    	this.tipoUsuario = tipoUsuario;
	}
	
    public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
        if(!tieneGuardarropa(guardarropa))
            throw new GuardarropaNoPerteneceAlUsuarioException();

    	tipoUsuario.agregarPrenda(prenda, guardarropa);
    }

    public boolean tieneGuardarropa(Guardarropa guardarropa){ return guardarropas.contains(guardarropa); }
  
    public void aceptarSugerencia(Evento evento, Atuendo atuendo) {
        ComandoAtuendo comandoAtuendo = new ComandoAtuendoAceptar(atuendo);
        comandoAtuendo.ejecutar();
        evento.setSugerenciaAceptada(atuendo);
    }

    public void rechazarSugerencia(Atuendo atuendo) {
        ComandoAtuendo comandoAtuendo = new ComandoAtuendoRechazar(atuendo);
        comandoAtuendo.ejecutar();
    }

    public void deshacerUltimaOperacion(Atuendo atuendo) {
        atuendo.getUltimoComando().deshacer();
    }
    
    public void setNotificador(Notificador notificador) {
    	this.notificador = notificador;
    }
    
    public Optional<Notificador> getNotificador() {
    	return Optional.ofNullable(this.notificador);
    }

    public boolean estaUsandoAlgunaPrendaDe(Atuendo atuendo) {
        Set<Prenda> prendasEnUso = eventos.stream().filter(Evento::tieneSugerenciaAceptada)
                                                   .map(Evento::getSugerenciaAceptada)
                                                   .flatMap(a -> a.prendas().stream())
                                                   .collect(Collectors.toSet());
        return atuendo.prendas().stream().anyMatch(prendasEnUso::contains);
    }

    public void calificar(Calificacion calificacion){
        sensibilidad.modificarSensibilidad(calificacion);
    }

    public boolean esFriolentoDeManos(){ return sensibilidad.getSensibilidadManos() > 0; }

    public boolean esFriolentoDeCuello(){ return sensibilidad.getSensibilidadCuello() > 0; }

    public boolean esFriolentoDeCabeza() { return sensibilidad.getSensibilidadCabeza() > 0; }

    public double getSensibilidadClima(){
        return sensibilidad.getSensibilidadClima();
    }

    public double obtenerNivelDeAbrigo(Temperatura temperatura) {
        return temperatura.convertirANivelDeAbrigo() * getSensibilidadClima();
    }

    public Set<Evento> getEventos() {
        return eventos;
    }

    public Set<Guardarropa> guardarropas() {
        return guardarropas;
    }
}
