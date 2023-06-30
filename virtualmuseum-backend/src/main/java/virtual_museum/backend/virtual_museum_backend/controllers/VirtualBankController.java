package virtual_museum.backend.virtual_museum_backend.controllers;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import virtual_museum.backend.virtual_museum_backend.VirtualMuseumBackendApplication;
import virtual_museum.backend.virtual_museum_backend.models.entities.RacunEntity;
import virtual_museum.backend.virtual_museum_backend.models.entities.TransakcijaEntity;
import virtual_museum.backend.virtual_museum_backend.repositories.TransakcijaEntityRepository;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@CrossOrigin("*")
@RestController
@RequestMapping("/virtual-bank")
public class VirtualBankController {
  @Autowired
  BankAccountController bankAccountController;
  @Autowired
  TransakcijaEntityRepository transakcijaEntityRepository;
  @PostMapping
  public String buyTicket(@RequestBody String body,@RequestParam String id){
    Gson gson=new Gson();
    JsonObject account=gson.fromJson(body,JsonObject.class);
    String name=gson.fromJson(account.get("name"),String.class);
    String surname=gson.fromJson(account.get("surname"),String.class);
    String creditCardType=gson.fromJson(account.get("creditCardType"),String.class);
    String pin=gson.fromJson(account.get("pin"),String.class);
    String date=gson.fromJson(account.get("date"),String.class);
    Double ticketPrice= gson.fromJson(account.get("ticketPrice"),Double.class);
    System.out.println(ticketPrice);
    String creditCardNumber=gson.fromJson(account.get("creditCardNumber"),String.class);

    List<RacunEntity> accountsList = bankAccountController.findAll();
    System.out.println(pin+","+date+","+name+","+ticketPrice+","+creditCardType+","+surname);
    Optional<RacunEntity> accountOpt=accountsList.stream().filter(e->e.getBrojKartice().toString().equals(creditCardNumber)
    && e.getTipKreditneKartice().equalsIgnoreCase(creditCardType) && e.getDatumIsteka().toString().equals(date) && e.getPin().toString().equals(pin) && e.getKorisnik().getIme().equals(name) && e.getKorisnik().getPrezime().equals(surname)).findFirst();
    if(accountOpt.isPresent()){
      RacunEntity accountEntity=accountOpt.get();
      if(accountEntity.getDozvolaPlacanja()==0){
        return "Payment is not approved.";
      }
      if(accountEntity.getStanjeRacuna().doubleValue()>= ticketPrice) {
        LocalDate today=LocalDateTime.now().toLocalDate();
        LocalTime time=LocalDateTime.now().toLocalTime();
        TransakcijaEntity transakcijaEntity=new TransakcijaEntity(Date.valueOf(today), Time.valueOf(time),BigDecimal.valueOf(ticketPrice),Integer.valueOf(id),null);
        transakcijaEntityRepository.save(transakcijaEntity);
        accountEntity.setStanjeRacuna(BigDecimal.valueOf(accountEntity.getStanjeRacuna().doubleValue()-ticketPrice));
        bankAccountController.updateEntry(accountEntity.getStanjeRacuna(),Integer.valueOf(creditCardNumber));
        VirtualMuseumBackendApplication.writeLog("User has buyed ticket.");
        return "Successfully buyed.";
      }
    }
    else{
      VirtualMuseumBackendApplication.writeLog("Unsuccessfully try of buying ticket.");
      return "Incorrect card info.";
    }
    VirtualMuseumBackendApplication.writeLog("Unsuccessfully try of buying ticket.");
    return "You don't have enough money for this action.";
  }
}
