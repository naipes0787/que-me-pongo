package quemepongo.persistencia;

import quemepongo.dominio.prenda.TipoPrenda;

import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioTipoPrenda extends Repositorio<TipoPrenda> {

    private static RepositorioTipoPrenda instancia = new RepositorioTipoPrenda();

    private RepositorioTipoPrenda() {
    }

    public static RepositorioTipoPrenda instancia() {
        return instancia;
    }

    public TipoPrenda buscarPorId(Long tipoId){
        return find(TipoPrenda.class, tipoId);
    }

    public List<TipoPrenda> getTipos() {
        TypedQuery<TipoPrenda> query = createQuery("from TipoPrenda", TipoPrenda.class);
        return query.getResultList();
    }
}
