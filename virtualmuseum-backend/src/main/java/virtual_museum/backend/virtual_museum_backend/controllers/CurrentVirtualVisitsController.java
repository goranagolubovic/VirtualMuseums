package virtual_museum.backend.virtual_museum_backend.controllers;

import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import virtual_museum.backend.virtual_museum_backend.VirtualMuseumBackendApplication;
import virtual_museum.backend.virtual_museum_backend.models.entities.KorisnikEntity;
import virtual_museum.backend.virtual_museum_backend.models.entities.VirtuelnaPosjetaEntity;
import virtual_museum.backend.virtual_museum_backend.repositories.CurrentVirtualVisitsRepository;
import virtual_museum.backend.virtual_museum_backend.repositories.KorisnikEntityRepository;

import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/virtual-visits")
public class CurrentVirtualVisitsController {
  private final CurrentVirtualVisitsRepository currentVirtualVisitsRepository;
  private final KorisnikEntityRepository korisnikEntityRepository;

  public CurrentVirtualVisitsController(CurrentVirtualVisitsRepository currentVirtualVisitsRepository, KorisnikEntityRepository korisnikEntityRepository) {
    this.currentVirtualVisitsRepository = currentVirtualVisitsRepository;
    this.korisnikEntityRepository=korisnikEntityRepository;
  }

  @RequestMapping("/available-virtual-visits")
  @GetMapping()
  public ResponseEntity<List<VirtuelnaPosjetaEntity>> findAllAvailable(@RequestParam String id){
    List<VirtuelnaPosjetaEntity>list=new ArrayList<>();
    if(!isUserAdmin(id)) {

      LocalTime currentTime = LocalTime.now();
      Time time = new Time(currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond());
      LocalDateTime currentDateTime = LocalDateTime.now();
      DateTimeFormatter format1 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      DateTimeFormatter format2 = DateTimeFormatter.ofPattern("HH:mm:ss");
      String currDate = currentDateTime.format(format1);
      String currTime = currentDateTime.format(format2);
   /* currentVirtualVisitsRepository.findAll().stream()
      .forEach(elem-> {
        System.out.println(String.valueOf(elem.getDatum()).equals(currDate));
        System.out.println(elem.getTrajanje().toLocalTime().getHour());
        System.out.println(elem.getVrijeme().toLocalTime().getHour());
        System.out.println( new Time((elem.getVrijeme().toLocalTime().getHour() + elem.getTrajanje().toLocalTime().getHour()),elem.getVrijeme().toLocalTime().getMinute(),elem.getVrijeme().toLocalTime().getSecond())+";"+time);
        }

      );*/
      currentVirtualVisitsRepository.findAll().stream().forEach(elem -> {
        System.out.println(String.valueOf(elem.getDatum()).compareTo(currDate) > 0);
        System.out.println(elem.getDatum());
        // System.out.println( new Time((elem.getVrijeme().toLocalTime().getHour() + elem.getTrajanje().toLocalTime().getHour()),elem.getVrijeme().toLocalTime().getMinute(),elem.getVrijeme().toLocalTime().getSecond()).
        // after(time));
      });
      list= currentVirtualVisitsRepository.findAll().stream()
        .filter(elem -> String.valueOf(elem.getDatum()).compareTo(currDate) > 0 || (String.valueOf(elem.getDatum()).equals(currDate) &&
          new Time(Integer.valueOf(elem.getVrijeme().split(":")[0]) + Integer.valueOf(elem.getTrajanje().split(":")[0]), Integer.valueOf(elem.getVrijeme().split(":")[1]), Integer.valueOf(elem.getVrijeme().split(":")[2])).
            after(time))
        )
        .collect(Collectors.toList());
      return ResponseEntity.ok().body(list);
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(list);
  }
  @GetMapping
  public ResponseEntity<List<VirtuelnaPosjetaEntity>> findAllActive(@RequestParam String id){
    List<VirtuelnaPosjetaEntity>list=new ArrayList<>();
    if(!isUserAdmin(id)) {
      VirtualMuseumBackendApplication.writeLog("User has watched all active virtual tours.");
      System.out.println("callled");
      LocalTime currentTime = LocalTime.now();
      Time time = new Time(currentTime.getHour(), currentTime.getMinute(), currentTime.getSecond());
      LocalDateTime currentDateTime = LocalDateTime.now();
      DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      String currDate = currentDateTime.format(format);
      list= currentVirtualVisitsRepository.findAll().stream()
        .filter(elem -> String.valueOf(elem.getDatum()).equals(currDate) && new Time(Integer.valueOf(elem.getVrijeme().split(":")[0]), Integer.valueOf(elem.getVrijeme().split(":")[1]), Integer.valueOf(elem.getVrijeme().split(":")[2])).before(time) &&
          new Time(Integer.valueOf(elem.getVrijeme().split(":")[0]) + Integer.valueOf(elem.getTrajanje().split(":")[0]), Integer.valueOf(elem.getVrijeme().split(":")[1]) + Integer.valueOf(elem.getTrajanje().split(":")[1]), Integer.valueOf(elem.getVrijeme().split(":")[2]) + Integer.valueOf(elem.getTrajanje().split(":")[2])).
            after(time)
        )
        .collect(Collectors.toList());
      return ResponseEntity.ok().body(list);
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(list);
  }
  private boolean isUserAdmin(String id) {
    KorisnikEntity user = korisnikEntityRepository.findAll().stream()
      .filter(elem -> elem.getId() == Integer.valueOf(id)).findFirst().get();
    if (user.getIsAdmin() == 1) {
      return true;
    }
    return false;
  }
}
