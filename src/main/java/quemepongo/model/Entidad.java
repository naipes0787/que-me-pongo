package quemepongo.model;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class Entidad {

    @Id
    @GeneratedValue
    private Long id;

    public Long getId() {
        return id;
    }

}

