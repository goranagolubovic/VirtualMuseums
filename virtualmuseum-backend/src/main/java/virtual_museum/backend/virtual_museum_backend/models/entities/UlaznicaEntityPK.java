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
public class UlaznicaEntityPK implements Serializable {
  @Column(name = "korisnik_id")
  @Id
  private Integer korisnikId;
  @Column(name = "virtuelna_posjeta_muzej_id")
  @Id
  private Integer virtuelnaPosjetaMuzejId;
  @Column(name = "virtuelna_posjeta_datum")
  @Id
  private String virtuelnaPosjetaDatum;
  @Column(name = "virtuelna_posjeta_vrijeme")
  @Id
  private String virtuelnaPosjetaVrijeme;
  @Column(name = "virtuelna_posjeta_trajanje")
  @Id
  private String virtuelnaPosjetaTrajanje;

}
