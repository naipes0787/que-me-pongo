package quemepongo.persistencia;

import quemepongo.model.sugerencia.Atuendo;

import javax.persistence.TypedQuery;
import java.util.List;

public class RepositorioAtuendo extends Repositorio<Atuendo> {
    private RepositorioAtuendo() {
    }

    public Atuendo obtenerAtuendo(Long atuendoId){
        return entityManager().find(Atuendo.class, atuendoId);
    }

    public List<Atuendo> getAtuendo() {
        TypedQuery<Atuendo> query = entityManager().createQuery("from Atuendo", Atuendo.class);
        return query.getResultList();
    }
}
