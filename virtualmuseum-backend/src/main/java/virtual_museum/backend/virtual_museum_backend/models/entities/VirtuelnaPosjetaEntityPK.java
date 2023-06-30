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
public class VirtuelnaPosjetaEntityPK implements Serializable {
  @Column(name = "muzej_id")
  @Id
  private Integer muzejId;
  @Column(name = "datum")
  @Id
  private String datum;
  @Column(name = "vrijeme")
  @Id
  private String vrijeme;
  @Column(name = "trajanje")
  @Id
  private String trajanje;

}
