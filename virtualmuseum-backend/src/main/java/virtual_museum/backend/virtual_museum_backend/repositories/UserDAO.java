package virtual_museum.backend.virtual_museum_backend.repositories;

import org.springframework.stereotype.Repository;
import virtual_museum.backend.virtual_museum_backend.models.User;

import java.util.Arrays;
import java.util.List;

@Repository
public class UserDAO {
  public List<User> getAll(){
    return Arrays.asList(new User("Marko","Markovic"),new User("Marija","Maric"));
  }
}
