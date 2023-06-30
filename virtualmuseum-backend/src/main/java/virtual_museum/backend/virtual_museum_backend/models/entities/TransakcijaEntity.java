package virtual_museum.backend.virtual_museum_backend.models.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Data
@AllArgsConstructor
@Entity
@Table(name = "transakcija", schema = "museum", catalog = "")
@IdClass(TransakcijaEntityPK.class)
public class TransakcijaEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "datum")
  private Date datum;
  @Id
  @Column(name="vrijeme")
  private Time time;
  @Basic
  @Column(name = "odliv")
  private BigDecimal odliv;
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "korisnik_id")
  private Integer korisnikId;
  @ManyToOne
  @JoinColumn(name = "korisnik_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
  private KorisnikEntity korisnik;

  public TransakcijaEntity() {

  }
}
