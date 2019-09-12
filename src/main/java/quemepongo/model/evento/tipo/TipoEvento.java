package quemepongo.model.evento.tipo;

import quemepongo.model.Entidad;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDateTime;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class TipoEvento extends Entidad {

    public abstract LocalDateTime getFecha();

}
