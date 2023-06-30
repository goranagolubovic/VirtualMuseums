package virtual_museum.backend.virtual_museum_backend.models.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Data
public class LogTabelaEntityPK implements Serializable {
  @Column(name = "datum")
  @Id
  private String datum;
  @Column(name = "vrijeme")
  @Id
  private String vrijeme;
  @Column(name = "id_korisnik")
  @Id
  private Integer idKorisnik;

}
