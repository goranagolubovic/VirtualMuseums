package virtual_museum.backend.virtual_museum_backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Data
@Entity
@Table(name = "muzej", schema = "museum", catalog = "")
public class MuzejEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private Integer id;
  @Basic
  @Column(name = "naziv")
  private String naziv;
  @Basic
  @Column(name = "adresa")
  private String adresa;
  @Basic
  @Column(name = "broj_telefona")
  private String brojTelefona;
  @Basic
  @Column(name = "grad")
  private String grad;
  @Basic
  @Column(name = "drzava")
  private String drzava;
  @Basic
  @Column(name = "geolokacija")
  private String geolokacija;
  @Basic
  @Column(name = "tip_muzeja_id")
  private String tipMuzejaId;
  @Basic
  @Column(name = "cijena_ulaznice")
  private BigDecimal cijenaUlaznice;
  @OneToMany(mappedBy = "muzej")
  @JsonIgnore
  private Collection<VirtuelnaPosjetaEntity> virtuelnaPosjetasById;

}
