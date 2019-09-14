package quemepongo.model.guardarropa;

/*
@Entity
public class GuardarropaCompartido extends Guardarropa {

    @ManyToMany(mappedBy = "guardarropas")
    private Set<Usuario> usuarios;

    public GuardarropaCompartido(Set<Usuario> usuarios) {
        this.usuarios = usuarios;
        usuarios.forEach(usuario -> usuario.agregarGuardarropa(this));
    }

    public GuardarropaCompartido() {}

    @Override
    public Set<Atuendo> sugerencias(Usuario usuario, double nivelAbrigo) {
        Set<Atuendo> atuendos = super.sugerencias(usuario, nivelAbrigo);
        return atuendos.stream().filter(a -> usuarios.stream().noneMatch(u -> u.estaUsandoAlgunaPrendaDe(a))).collect(Collectors.toSet());
    }
}
*/
