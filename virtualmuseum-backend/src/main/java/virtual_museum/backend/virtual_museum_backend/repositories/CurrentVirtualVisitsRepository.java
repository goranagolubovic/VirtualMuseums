package virtual_museum.backend.virtual_museum_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virtual_museum.backend.virtual_museum_backend.models.entities.VirtuelnaPosjetaEntity;

public interface CurrentVirtualVisitsRepository extends JpaRepository<VirtuelnaPosjetaEntity,Integer> {
}

