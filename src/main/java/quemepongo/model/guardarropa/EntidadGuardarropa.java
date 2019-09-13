package quemepongo.model.guardarropa;

import quemepongo.model.Entidad;
import quemepongo.model.prenda.Prenda;
import quemepongo.model.usuario.EntidadUsuario;

import javax.persistence.CascadeType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Set;

public class EntidadGuardarropa extends Entidad {

    //@Column(columnDefinition = "double defaul 0.1") es necesario esta definicion?
    private double margenError = 0.1;

    //oneToMany ?
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Prenda> prendas;

    @ManyToMany(mappedBy = "guardarropas")
    private Set<EntidadUsuario> usuarios;

    //necesito un discriminador por tipo de guardarropa?

    public EntidadGuardarropa(List<Prenda> prendas, Set<EntidadUsuario> usuarios, double margenError) {
        this.margenError = margenError;
        this.prendas = prendas;
        this.usuarios = usuarios;
    }

    public double getMargenError() {
        return margenError;
    }

    public List<Prenda> getPrendas() {
        return prendas;
    }

    public Set<EntidadUsuario> getUsuarios() {
        return usuarios;
    }
}
