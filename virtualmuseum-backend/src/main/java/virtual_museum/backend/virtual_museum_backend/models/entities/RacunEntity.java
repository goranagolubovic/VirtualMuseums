package virtual_museum.backend.virtual_museum_backend.models.entities;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Table(name = "racun", schema = "museum", catalog = "")
public class RacunEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "broj_kartice")
  private Integer brojKartice;
  @Basic
  @Column(name = "tip_kreditne_kartice")
  private String tipKreditneKartice;
  @Basic
  @Column(name = "pin")
  private Integer pin;
  @Basic
  @Column(name = "stanje_racuna")
  private BigDecimal stanjeRacuna;
  @Basic
  @Column(name = "datum_isteka")
  private Date datumIsteka;
  @Basic
  @Column(name = "korisnik_id")
  private Integer korisnikId;
  @Basic
  @Column(name = "dozvola_placanja")
  private Byte dozvolaPlacanja;
  @ManyToOne
  @JoinColumn(name = "korisnik_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
  private KorisnikEntity korisnik;

}
