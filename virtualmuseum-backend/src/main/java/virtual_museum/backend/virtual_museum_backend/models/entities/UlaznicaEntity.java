package virtual_museum.backend.virtual_museum_backend.models.entities;

import lombok.Data;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Data
@ToString
@Entity
@Table(name = "ulaznica", schema = "museum", catalog = "")
@IdClass(UlaznicaEntityPK.class)
public class UlaznicaEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "korisnik_id")
  private Integer korisnikId;
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "virtuelna_posjeta_muzej_id")
  private Integer virtuelnaPosjetaMuzejId;
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "virtuelna_posjeta_datum")
  private String virtuelnaPosjetaDatum;
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "virtuelna_posjeta_vrijeme")
  private String virtuelnaPosjetaVrijeme;
  @Id
  @Column(name = "virtuelna_posjeta_trajanje")
  private String virtuelnaPosjetaTrajanje;
  @ManyToOne
  @JoinColumn(name = "korisnik_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
  private KorisnikEntity korisnik;
  @ManyToOne
  @JoinColumns({@JoinColumn(name = "virtuelna_posjeta_muzej_id", referencedColumnName = "muzej_id", nullable = false,insertable = false,updatable = false), @JoinColumn(name = "virtuelna_posjeta_datum", referencedColumnName = "datum", nullable = false,insertable = false,updatable = false), @JoinColumn(name = "virtuelna_posjeta_vrijeme", referencedColumnName = "vrijeme", nullable = false,insertable = false,updatable = false),@JoinColumn(name = "virtuelna_posjeta_trajanje", referencedColumnName = "trajanje", nullable = false,insertable = false,updatable = false)})
  private VirtuelnaPosjetaEntity virtuelnaPosjeta;

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
    UlaznicaEntity that = (UlaznicaEntity) o;
    return korisnikId != null && Objects.equals(korisnikId, that.korisnikId)
      && virtuelnaPosjetaMuzejId != null && Objects.equals(virtuelnaPosjetaMuzejId, that.virtuelnaPosjetaMuzejId)
      && virtuelnaPosjetaDatum != null && Objects.equals(virtuelnaPosjetaDatum, that.virtuelnaPosjetaDatum)
      && virtuelnaPosjetaVrijeme != null && Objects.equals(virtuelnaPosjetaVrijeme, that.virtuelnaPosjetaVrijeme)
      && virtuelnaPosjetaTrajanje != null && Objects.equals(virtuelnaPosjetaTrajanje, that.virtuelnaPosjetaTrajanje);
  }

  @Override
  public int hashCode() {
    return Objects.hash(korisnikId,
      virtuelnaPosjetaMuzejId,
      virtuelnaPosjetaDatum,
      virtuelnaPosjetaVrijeme,
      virtuelnaPosjetaTrajanje);
  }
}
