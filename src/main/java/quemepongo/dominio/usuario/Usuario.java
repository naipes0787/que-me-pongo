package quemepongo.dominio.usuario;

import com.google.common.collect.Sets;
import quemepongo.dominio.Entidad;
import quemepongo.dominio.Temperatura;
import quemepongo.dominio.calificacion.Calificacion;
import quemepongo.dominio.evento.Evento;
import quemepongo.dominio.guardarropa.Guardarropa;
import quemepongo.dominio.guardarropa.GuardarropaCompartido;
import quemepongo.dominio.notificador.Notificador;
import quemepongo.dominio.prenda.Prenda;
import quemepongo.dominio.sugerencia.Atuendo;
import quemepongo.excepcion.GuardarropaNoPerteneceAlUsuarioException;
import quemepongo.servicio.clima.SelectorDeProveedorDeClima;

import javax.persistence.*;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Usuario extends Entidad {

    @ManyToMany(cascade = CascadeType.ALL)
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

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
    private Sensibilidad sensibilidad = new Sensibilidad();

    @Transient
    private Notificador notificador;

    private String password;

    private String username;

    public Usuario() {
        this.tipoUsuario = new UsuarioGratuito();
    }

    public Usuario(String username, String password) {
    	this.tipoUsuario = new UsuarioGratuito();
    	this.username = username;
    	this.password = password;
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
  
    public void setNotificador(Notificador notificador) {
    	this.notificador = notificador;
    }
    
    public Optional<Notificador> getNotificador() {
    	return Optional.ofNullable(this.notificador);
    }

    public boolean estaUsandoAlgunaPrendaDe(Atuendo atuendo) {
        Set<Prenda> prendasEnUso = eventos.stream().filter(Evento::tieneSugerenciaAceptada)
                                                   .map(Evento::getSugerenciaAceptada)
                                                   .flatMap(a -> a.getPrendas().stream())
                                                   .collect(Collectors.toSet());
        return atuendo.getPrendas().stream().anyMatch(prendasEnUso::contains);
    }

    public void modificarSensibilidad(Calificacion calificacion){
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

    public Set<Guardarropa> getGuardarropas() {
        return guardarropas;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }
}
