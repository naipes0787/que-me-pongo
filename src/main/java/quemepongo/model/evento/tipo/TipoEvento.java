package quemepongo.model.evento.tipo;

import quemepongo.model.Entidad;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDateTime;

@Entity
@DiscriminatorColumn(name = "codigo")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class TipoEvento extends Entidad {

    public abstract LocalDateTime getFecha();

}
