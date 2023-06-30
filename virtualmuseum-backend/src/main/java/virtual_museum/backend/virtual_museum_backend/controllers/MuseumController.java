package virtual_museum.backend.virtual_museum_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import virtual_museum.backend.virtual_museum_backend.VirtualMuseumBackendApplication;
import virtual_museum.backend.virtual_museum_backend.models.entities.KorisnikEntity;
import virtual_museum.backend.virtual_museum_backend.models.entities.MuzejEntity;
import virtual_museum.backend.virtual_museum_backend.repositories.KorisnikEntityRepository;
import virtual_museum.backend.virtual_museum_backend.repositories.MuzejEntityRepository;

import java.util.ArrayList;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/museums")
public class MuseumController {
  private  final MuzejEntityRepository muzejEntityRepository;
  private  final KorisnikEntityRepository korisnikEntityRepository;
  public MuseumController(MuzejEntityRepository muzejEntityRepository,KorisnikEntityRepository korisnikEntityRepository) {
    this.muzejEntityRepository = muzejEntityRepository;
    this.korisnikEntityRepository=korisnikEntityRepository;
  }
  @GetMapping
  public ResponseEntity<List<MuzejEntity>> findAll(){
    VirtualMuseumBackendApplication.writeLog("User has viewed musems.");
    List<MuzejEntity>list=new ArrayList<>();
      list=muzejEntityRepository.findAll();
      return ResponseEntity.ok().body(list);

  }

}
