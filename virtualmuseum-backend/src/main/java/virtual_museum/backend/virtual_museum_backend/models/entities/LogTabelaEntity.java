package virtual_museum.backend.virtual_museum_backend.models.entities;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "log_tabela", schema = "museum", catalog = "")
@IdClass(LogTabelaEntityPK.class)
public class LogTabelaEntity {
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "datum")
  private String datum;
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "vrijeme")
  private String vrijeme;
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Id
  @Column(name = "id_korisnik")
  private Integer idKorisnik;
  @Basic
  @Column(name = "datum_isteka")
  private String datumIsteka;
  @Basic
  @Column(name = "vrijeme_isteka")
  private String vrijemeIsteka;

}
