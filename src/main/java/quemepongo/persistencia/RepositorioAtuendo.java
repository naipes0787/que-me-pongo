package quemepongo.persistencia;

import quemepongo.dominio.sugerencia.Atuendo;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Repositorio de las {@link Atuendo}s del sistema
 */
public class RepositorioAtuendo extends Repositorio<Atuendo> {

    private static RepositorioAtuendo instancia;

    private RepositorioAtuendo() {

    }

    public static RepositorioAtuendo instancia() {
        if (instancia == null) {
            instancia = new RepositorioAtuendo();
        }
        return instancia;
    }

    public Atuendo buscarAtuendo(Long atuendoId){
        return find(Atuendo.class, atuendoId);
    }

    public List<Atuendo> atuendos() {
        TypedQuery<Atuendo> query = createQuery("from Atuendo", Atuendo.class);
        return query.getResultList();
    }
}
