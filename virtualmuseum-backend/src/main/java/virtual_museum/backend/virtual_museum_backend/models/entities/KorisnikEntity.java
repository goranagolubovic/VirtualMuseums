package virtual_museum.backend.virtual_museum_backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "korisnik", schema = "museum", catalog = "")
public class KorisnikEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id")
  private Integer id;
  @Basic
  @Column(name = "ime")
  private String ime;
  @Basic
  @Column(name = "prezime")
  private String prezime;
  @Basic
  @Column(name = "korisnicko_ime")
  private String korisnickoIme;
  @Basic
  @Column(name = "lozinka")
  private String lozinka;
  @Basic
  @Column(name = "email")
  private String email;
  @Basic
  @Column(name = "is_admin")
  private Byte isAdmin;
  @Basic
  @Column(name = "is_active")
  private Byte isActive;
  @Basic
  @Column(name = "is_approved")
  private Byte isApproved;
  @Basic
  @Column(name = "token")
  private String token;
  @OneToMany(mappedBy = "korisnik")
  @JsonIgnore
  private Collection<RacunEntity> racunsById;
  @OneToMany(mappedBy = "korisnik")
  @JsonIgnore
  private Collection<TransakcijaEntity> transakcijasById;
  @OneToMany(mappedBy = "korisnik")
  @JsonIgnore
  private Collection<UlaznicaEntity> ulaznicasById;

}
