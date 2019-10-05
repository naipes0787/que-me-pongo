package quemepongo.dominio.guardarropa;

import com.google.common.collect.Sets;
import quemepongo.dominio.Entidad;
import quemepongo.dominio.prenda.Categoria;
import quemepongo.dominio.prenda.CombinacionPrenda;
import quemepongo.dominio.prenda.Combinador;
import quemepongo.dominio.prenda.Prenda;
import quemepongo.dominio.sugerencia.Atuendo;
import quemepongo.dominio.usuario.Usuario;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
public class Guardarropa extends Entidad {

    public String nombre;
	
    private double margenError = 0.1;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Prenda> prendas = Sets.newHashSet();

    public Guardarropa(String nombre) {
        this.nombre = nombre;
    }

    public Guardarropa() {}

    public void agregarPrenda(Prenda prenda) {
        prendas.add(prenda);
    }

    public Set<Prenda> prendasDeCategoria(Categoria categoria) {
        return prendas.stream().
                filter(prenda -> prenda.getCategoria() == categoria)
                .collect(Collectors.toSet());
    }

    public Set<Atuendo> sugerencias(Usuario usuario, double nivelAbrigo) {
        List<Categoria> accesoriosAIncluir = new ArrayList<>();
        if (usuario.esFriolentoDeManos() && poseeAccesorioDeManos()){
            accesoriosAIncluir.add(Categoria.ACCESORIO_MANOS);
        }
        if (usuario.esFriolentoDeCuello() && poseeAccesorioDeCuello()){
            accesoriosAIncluir.add(Categoria.ACCESORIO_CUELLO);
        }
        if (usuario.esFriolentoDeCabeza() && poseeAccesorioDeCabeza()){
            accesoriosAIncluir.add(Categoria.ACCESORIO_CABEZA);
        }
        return filtrarAtuendosPorNivelAbrigo(generarAtuendos(accesoriosAIncluir), nivelAbrigo);
    }

/* YA NO APLICA:
    public Set<Atuendo> sugerenciasConAccesorios(double nivelAbrigo) {
        return filtrarAtuendosPorNivelAbrigo(generarAtuendosConAccesorios(), nivelAbrigo);
    }

 */

    private Set<Atuendo> generarAtuendos(List<Categoria> accesoriosAIncluir){
        List<Set<CombinacionPrenda>> listadoACombinar = new ArrayList<Set<CombinacionPrenda>>();
        listadoACombinar.add(Combinador.combinarMultiple(this.prendasDeCategoria(Categoria.PRENDA_SUPERIOR)));
        listadoACombinar.add(Combinador.combinarSimple(this.prendasDeCategoria(Categoria.PRENDA_INFERIOR)));
        listadoACombinar.add(Combinador.combinarSimple(this.prendasDeCategoria(Categoria.CALZADO)));

        accesoriosAIncluir.stream()
                .forEach(cat -> listadoACombinar.add(Combinador.combinarSimple(this.prendasDeCategoria(cat))));
        final Integer cantAccesorios = accesoriosAIncluir.size();

        return Sets.cartesianProduct(listadoACombinar)
                .stream()
                .map(c -> convertirCombinacionEnAtuendo(c, cantAccesorios))
                .collect(Collectors.toSet());
    }

    private Atuendo convertirCombinacionEnAtuendo(List<CombinacionPrenda> c, Integer cantAccesorios){
        Atuendo atuendo = new Atuendo(c.get(0), c.get(1), c.get(2));
        for (int i = 0; i < cantAccesorios; i++) {
            atuendo.agregarAccesorio(c.get(i + 3));
        }
        return atuendo;
    }

    private Set<Atuendo> filtrarAtuendosPorNivelAbrigo(Set<Atuendo> atuendos, double nivelAbrigo){
        if(atuendos.size() == 0) {
            return Sets.newHashSet();
        }else{
            return sugerenciasSegunMargen(atuendos, nivelAbrigo, getMargenError());
        }
    }

    public Set<Atuendo> sugerenciasSegunMargen(Set<Atuendo> atuendos, double nivelAbrigo, double margen) {
        Set<Atuendo> atuendosFiltrados = atuendos.stream()
                .filter(atuendo -> atuendo.abrigaLoSuficiente(nivelAbrigo, margen))
                .collect(Collectors.toSet());
        if (atuendosFiltrados.size() == 0)
        {return sugerenciasSegunMargen(atuendos, nivelAbrigo, ampliarMargen(margen));}
        else
        {return atuendosFiltrados;}
    }

    public int cantidadDePrendas() {
        return prendas.size();
    }

    private double getMargenError(){
    	return margenError;
    }

    private double ampliarMargen(double margenError){
    	return margenError + 0.1;
    }

    private boolean poseeAccesorioDeManos(){
        return prendas.stream().anyMatch(p -> p.getCategoria() == Categoria.ACCESORIO_MANOS);
    }
    private boolean poseeAccesorioDeCuello(){
        return prendas.stream().anyMatch(p -> p.getCategoria() == Categoria.ACCESORIO_CUELLO);
    }
    private boolean poseeAccesorioDeCabeza(){
        return prendas.stream().anyMatch(p -> p.getCategoria() == Categoria.ACCESORIO_CABEZA);
    }

    public Set<Prenda> getPrendas() {
        return prendas;
    }

    public String getNombre() {
        return nombre;
    }
}