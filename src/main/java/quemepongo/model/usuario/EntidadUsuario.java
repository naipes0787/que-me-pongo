package quemepongo.model.usuario;

import quemepongo.model.Entidad;
import quemepongo.model.evento.Evento;
import quemepongo.model.guardarropa.Guardarropa;

import javax.persistence.*;
import java.util.List;

@Entity
public class EntidadUsuario extends Entidad {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Evento> eventos;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "usuario_guardarropa",
            joinColumns = @JoinColumn(name = "usuario_id"),
            inverseJoinColumns = @JoinColumn(name = "guardarropa_id")
    )
    private List<Guardarropa> guardarropas;

    public EntidadUsuario(List<Evento> eventos, List<Guardarropa> guardarropas) {
        this.eventos = eventos;
        this.guardarropas = guardarropas;
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public List<Guardarropa> getGuardarropas() {
        return guardarropas;
    }
}
