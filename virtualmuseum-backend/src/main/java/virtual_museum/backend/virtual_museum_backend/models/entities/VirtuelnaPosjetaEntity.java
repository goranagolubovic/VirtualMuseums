package virtual_museum.backend.virtual_museum_backend.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Collection;

@Data
@Entity
@Table(name = "virtuelna_posjeta", schema = "museum", catalog = "")
@IdClass(VirtuelnaPosjetaEntityPK.class)
public class VirtuelnaPosjetaEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "muzej_id")
  private Integer muzejId;
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "datum")
  private String datum;
  @Basic
  @Column(name = "trajanje")
  private String trajanje;
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "vrijeme")
  private String vrijeme;
  @OneToMany(mappedBy = "virtuelnaPosjeta")
  @JsonIgnore
  private Collection<UlaznicaEntity> ulaznicas;
  @ManyToOne
  @JoinColumn(name = "muzej_id", referencedColumnName = "id", nullable = false,insertable = false,updatable = false)
  private MuzejEntity muzej;

}
