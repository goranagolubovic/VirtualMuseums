package virtual_museum.backend.virtual_museum_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virtual_museum.backend.virtual_museum_backend.models.entities.MuzejEntity;

public interface MuzejEntityRepository extends JpaRepository<MuzejEntity,Integer> {
}
