package virtual_museum.backend.virtual_museum_backend.controllers;

import com.google.gson.Gson;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import virtual_museum.backend.virtual_museum_backend.VirtualMuseumBackendApplication;
import virtual_museum.backend.virtual_museum_backend.models.entities.KorisnikEntity;
import virtual_museum.backend.virtual_museum_backend.repositories.KorisnikEntityRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/korisnici")
public class KorisnikController {
  private final KorisnikEntityRepository repository;

  public KorisnikController(KorisnikEntityRepository repository) {
    this.repository = repository;
  }
  @GetMapping
  public List<KorisnikEntity> findAll(){
    return repository.findAll();
  }

  @PostMapping(consumes = "application/json")
  public HttpStatus register(@RequestBody String body){
    System.out.println(body);
    KorisnikEntity regUser=new Gson().fromJson(body,KorisnikEntity.class);
    System.out.println(regUser);
   Optional<KorisnikEntity>user= repository.findAll().stream()
      .filter(elem->elem.getKorisnickoIme().equals(regUser.getKorisnickoIme())||elem.getLozinka().equals(regUser.getLozinka())).findFirst();
   if(user.isPresent()){
     VirtualMuseumBackendApplication.writeLog("Unsuccessfully try of registration.");
     return  HttpStatus.FOUND;
   }
    repository.save(regUser);
   VirtualMuseumBackendApplication.writeLog("User is registrated successfully.");
    return HttpStatus.OK;
  }
  @PutMapping(consumes = "application/json")
  public KorisnikEntity update(@RequestBody KorisnikEntity regUser) {
    repository.findById(regUser.getId())
      .map(user -> {
        user.setIsActive(regUser.getIsActive());
        VirtualMuseumBackendApplication.writeLog("User is logged in.");
        return repository.save(user);
      });
    VirtualMuseumBackendApplication.writeLog("Unsuccessfully try of logging.");
    return null;
  }

  /*@GetMapping(consumes = "application/json")
  public RegistrovaniKorisnikEntity findUser(@Param("Username") String username,@Param("Password") String password){
    return repository.findOne({_korisnickoIme:username});
  }*/
}
