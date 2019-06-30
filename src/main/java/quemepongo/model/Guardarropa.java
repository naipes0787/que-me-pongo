package quemepongo.model;

import com.google.common.collect.Sets;

import java.util.Set;
import java.util.stream.Collectors;

public class Guardarropa {
	
    private double margenError = 0.1;
    private Set<Prenda> prendas = Sets.newHashSet();

    public void agregarPrenda(Prenda prenda) {
        prendas.add(prenda);
    }

    public Set<Prenda> prendasDeCategoria(Categoria categoria) {
        return prendas.stream().
                filter(prenda -> prenda.getCategoria() == categoria)
                .collect(Collectors.toSet());
    }

    public Set<Atuendo> sugerencias(Temperatura temperatura) {
        return filtrarAtuendosPorTemperatura(generarAtuendos(), temperatura);
    }

    public Set<Atuendo> sugerenciasConAccesorios(Temperatura temperatura) {
        return filtrarAtuendosPorTemperatura(generarAtuendosConAccesorios(), temperatura);
    }

    private Set<Atuendo> generarAtuendos(){
        return Sets.cartesianProduct(
                Combinador.combinarMultiple(
                        this.prendasDeCategoria(Categoria.PRENDA_SUPERIOR)),
                Combinador.combinarSimple(
                        this.prendasDeCategoria(Categoria.PRENDA_INFERIOR)),
                Combinador.combinarSimple(
                        this.prendasDeCategoria(Categoria.CALZADO)))
                .stream()
                .map(c -> new Atuendo(c.get(0), c.get(1), c.get(2)))
                .collect(Collectors.toSet());
    }

    private Set<Atuendo> generarAtuendosConAccesorios(){
        return Sets.cartesianProduct(
                Combinador.combinarMultiple(
                        this.prendasDeCategoria(Categoria.PRENDA_SUPERIOR)),
                Combinador.combinarSimple(
                        this.prendasDeCategoria(Categoria.PRENDA_INFERIOR)),
                Combinador.combinarSimple(
                        this.prendasDeCategoria(Categoria.CALZADO)),
                Combinador.combinarSimple(
                        this.prendasDeCategoria(Categoria.ACCESORIO)))
                .stream()
                .map(c -> new Atuendo(c.get(0), c.get(1), c.get(2)).conAccesorio(c.get(3)))
                .collect(Collectors.toSet());
    }

    private Set<Atuendo> filtrarAtuendosPorTemperatura(Set<Atuendo> atuendos, Temperatura temperatura){
        if(atuendos.size() == 0) {
            return Sets.newHashSet();
        }else{
            return sugerenciasSegunMargen(atuendos, temperatura, getMargenError());
        }
    }

    public Set<Atuendo> sugerenciasSegunMargen(Set<Atuendo> atuendos, Temperatura temperatura, double margen) {
        Set<Atuendo> atuendosFiltrados = atuendos.stream()
                .filter(atuendo -> atuendo.abrigaLoSuficiente(temperatura, margen))
                .collect(Collectors.toSet());
        if (atuendosFiltrados.size() == 0)
        {return sugerenciasSegunMargen(atuendos, temperatura, ampliarMargen(margen));}
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

}