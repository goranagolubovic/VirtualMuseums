package virtual_museum.backend.virtual_museum_backend.controllers;

import org.springframework.web.bind.annotation.*;
import virtual_museum.backend.virtual_museum_backend.models.entities.KorisnikEntity;
import virtual_museum.backend.virtual_museum_backend.models.entities.RacunEntity;
import virtual_museum.backend.virtual_museum_backend.repositories.KorisnikEntityRepository;
import virtual_museum.backend.virtual_museum_backend.repositories.RacunEntityRepository;

import java.math.BigDecimal;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/accounts")
public class BankAccountController {
  private  final RacunEntityRepository racunEntityRepository;
  public BankAccountController(RacunEntityRepository racunEntityRepository) {
    this.racunEntityRepository = racunEntityRepository;
  }

  public void updateEntry(BigDecimal accountState,int creditCardNumber){
    racunEntityRepository.updatePriceByName(accountState,creditCardNumber);
  }
  @GetMapping
  public List<RacunEntity> findAll(){
    return racunEntityRepository.findAll();
  }

}

