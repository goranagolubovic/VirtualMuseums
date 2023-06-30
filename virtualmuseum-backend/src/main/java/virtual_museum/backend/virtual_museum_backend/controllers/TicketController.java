package virtual_museum.backend.virtual_museum_backend.controllers;


import com.google.gson.Gson;
import org.springframework.web.bind.annotation.*;
import virtual_museum.backend.virtual_museum_backend.VirtualMuseumBackendApplication;
import virtual_museum.backend.virtual_museum_backend.models.entities.UlaznicaEntity;
import virtual_museum.backend.virtual_museum_backend.repositories.UlaznicaRepository;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/ticket")
public class TicketController {
  private UlaznicaRepository ulaznicaRepository;
  public TicketController(UlaznicaRepository ulaznicaRepository){
    this.ulaznicaRepository=ulaznicaRepository;
  }
    @PostMapping(consumes = "application/json")
    public  void addTicket(@RequestBody String ticket){
    Gson gson=new Gson();
    UlaznicaEntity ulaznicaEntity=gson.fromJson(ticket,UlaznicaEntity.class);
    System.out.println(ulaznicaEntity);
    ulaznicaRepository.save(ulaznicaEntity);
  }
  @GetMapping
  public List<UlaznicaEntity> getTicketsOfUser(@RequestParam String id){
    VirtualMuseumBackendApplication.writeLog("User has watched buyed tickets.");
    return ulaznicaRepository.findAll().stream()
      .filter(elem->elem.getKorisnikId().toString().equals(id))
      .collect(Collectors.toList());
  }
}
