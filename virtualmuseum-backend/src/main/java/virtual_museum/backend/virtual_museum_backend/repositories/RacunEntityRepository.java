package virtual_museum.backend.virtual_museum_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import virtual_museum.backend.virtual_museum_backend.models.entities.RacunEntity;

import java.math.BigDecimal;

public interface RacunEntityRepository  extends JpaRepository<RacunEntity,Integer> {
  @Transactional
  @Modifying
  @Query("UPDATE RacunEntity r SET r.stanjeRacuna = :accountState WHERE r.brojKartice = :creditCardNumber")
  void updatePriceByName(@Param("accountState") BigDecimal accountState,@Param("creditCardNumber") int creditCardNumber);
}
