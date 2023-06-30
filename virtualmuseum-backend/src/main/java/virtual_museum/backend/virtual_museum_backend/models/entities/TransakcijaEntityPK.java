package virtual_museum.backend.virtual_museum_backend.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Data
public class TransakcijaEntityPK implements Serializable {
  @Column(name = "datum")
  @Id
  private Date datum;
  @Column(name = "korisnik_id")
  @Id
  private Integer korisnikId;
  @Column(name = "vrijeme")
  @Id
  private Time time;

}
