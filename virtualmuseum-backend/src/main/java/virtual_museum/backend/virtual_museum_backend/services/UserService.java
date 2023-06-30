package virtual_museum.backend.virtual_museum_backend.services;

import org.springframework.stereotype.Service;
import virtual_museum.backend.virtual_museum_backend.models.User;
import virtual_museum.backend.virtual_museum_backend.repositories.UserDAO;

import java.util.Arrays;
import java.util.List;

@Service
public class UserService {
  private final UserDAO userDAO;
  public UserService(UserDAO userDAO){
    this.userDAO=userDAO;
  }
  public List<User> getAll(){
    return userDAO.getAll();
  }
}
