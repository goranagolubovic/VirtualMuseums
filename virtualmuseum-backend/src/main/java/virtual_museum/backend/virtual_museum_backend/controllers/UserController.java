package virtual_museum.backend.virtual_museum_backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import virtual_museum.backend.virtual_museum_backend.models.User;
import virtual_museum.backend.virtual_museum_backend.repositories.UserDAO;
import virtual_museum.backend.virtual_museum_backend.services.UserService;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
  private UserService userService;
  @Autowired
  public void setUserService(UserService userService) {
    this.userService = userService;
  }
  @GetMapping
  public @ResponseBody
  List<User> getAll(){
    return userService.getAll();
  }
}
