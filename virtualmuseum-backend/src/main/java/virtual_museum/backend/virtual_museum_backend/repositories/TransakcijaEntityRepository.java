package virtual_museum.backend.virtual_museum_backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import virtual_museum.backend.virtual_museum_backend.models.entities.TransakcijaEntity;

public interface TransakcijaEntityRepository  extends JpaRepository<TransakcijaEntity,Integer> {
}
