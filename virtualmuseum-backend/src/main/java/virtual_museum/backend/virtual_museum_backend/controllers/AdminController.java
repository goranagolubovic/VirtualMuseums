package virtual_museum.backend.virtual_museum_backend.controllers;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import virtual_museum.backend.virtual_museum_backend.VirtualMuseumBackendApplication;
import virtual_museum.backend.virtual_museum_backend.models.*;
import virtual_museum.backend.virtual_museum_backend.models.entities.KorisnikEntity;
import virtual_museum.backend.virtual_museum_backend.models.entities.LogTabelaEntity;
import virtual_museum.backend.virtual_museum_backend.models.entities.MuzejEntity;
import virtual_museum.backend.virtual_museum_backend.models.entities.VirtuelnaPosjetaEntity;
import virtual_museum.backend.virtual_museum_backend.repositories.Active24Repository;
import virtual_museum.backend.virtual_museum_backend.repositories.ActiveUsersRepository;
import virtual_museum.backend.virtual_museum_backend.repositories.CurrentVirtualVisitsRepository;
import virtual_museum.backend.virtual_museum_backend.repositories.MuzejEntityRepository;

import javax.annotation.Resources;
import java.io.File;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class AdminController {
  private ActiveUsersRepository activeUsersRepository;
  private Active24Repository active24Repository;
  private MuzejEntityRepository muzejEntityRepository;
  private CurrentVirtualVisitsRepository currentVirtualVisitsRepository;
  private final String FOLDER="src/main/resources/static";
  private final String LOG_FILE="users.log";
  public AdminController(ActiveUsersRepository activeUsersRepository, Active24Repository active24Repository, MuzejEntityRepository muzejEntityRepository,CurrentVirtualVisitsRepository currentVirtualVisitsRepository) {
    this.activeUsersRepository = activeUsersRepository;
    this.active24Repository = active24Repository;
    this.muzejEntityRepository = muzejEntityRepository;
    this.currentVirtualVisitsRepository=currentVirtualVisitsRepository;
  }

  @GetMapping("/aktivni")
  public ResponseEntity<List<KorisnikEntity>> findActive(@RequestParam String id) {
    List<KorisnikEntity>list=new ArrayList<>();
    if(isUserAdmin(id)) {
       list = activeUsersRepository.findAll().stream().filter(elem -> elem.getIsActive() == 1).collect(Collectors.toList());
      return ResponseEntity.ok().body(list);
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(list);
  }
  @GetMapping("/registrovani")
  public List<KorisnikEntity> findRegistred() {
    return activeUsersRepository.findAll().stream().filter(elem -> elem.getIsApproved() == 1 && elem.getIsAdmin()==0).collect(Collectors.toList());
  }
  @GetMapping("/logs")
  public ResponseEntity<List<String>> getLogs(@RequestParam String id){
    List<String> logs= new ArrayList<>();
    if(isUserAdmin(id)) {
      logs= VirtualMuseumBackendApplication.readLogs();
      return ResponseEntity.ok().body(logs);
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(logs);
  }
  @SneakyThrows
  @GetMapping("/logs/download")
  public ResponseEntity<byte[]> downloadFile(@RequestParam String id){
    byte[] bytes={};
    if(isUserAdmin(id)) {
      File file = new File(FOLDER + File.separator + LOG_FILE);
      bytes = Files.readAllBytes(file.toPath());
      return ResponseEntity.ok().body(bytes);
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(bytes);
  }


  @GetMapping("/aktivni24")
  public List<LogTabelaEntity> findActiveIn24() {
    LocalDate today = LocalDate.now();
    LocalDate yesterday = today.minusDays(1);
    DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("yyyy-M-dd");
    String currDate = formatDate.format(today);
    String yesterdayDate = formatDate.format(yesterday);
    DateTimeFormatter formatTime = DateTimeFormatter.ofPattern("HH:mm:ss");
    String currTime = formatTime.format(LocalDateTime.now());

    LocalTime t2 = LocalTime.parse(currTime);
    Time time2 = new Time(t2.getHour(), t2.getMinute(), t2.getSecond());

    List<LogTabelaEntity> list = active24Repository.findAll();
    return list.stream().filter(elem -> elem.getDatum().equals(currDate)
      || (elem.getDatum().equals(yesterdayDate)
      && new Time(Integer.valueOf(elem.getVrijeme().split(":")[0]), Integer.valueOf(elem.getVrijeme().split(":")[1]), Integer.valueOf(elem.getVrijeme().split(":")[2])).compareTo(time2) >= 0)).collect(Collectors.toList());
  }

  @PostMapping("/aktivni24")
  public LogTabelaEntity addActiveIn24(@RequestBody String body) {
    Gson gson = new Gson();
    LogTabelaEntity logTabelaEntity = gson.fromJson(body, LogTabelaEntity.class);
    active24Repository.save(logTabelaEntity);
    return logTabelaEntity;
  }

  @GetMapping("/aktivniPoSatima")
  public Map<String, Long> groupByHour() {
    System.out.println(findActiveIn24().stream().map(elem -> elem.getVrijeme().split(":")[0]).
      collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    return findActiveIn24().stream().map(elem -> elem.getVrijeme().split(":")[0]).
      collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
  }

  @GetMapping("/pronadjiDrzave")
  public List<CountryName> findCountries() {
    Gson gson = new Gson();
    RestTemplate restTemplate = new RestTemplate();
    List<String> countries = new ArrayList<>();
    final String uri = "https://restcountries.com/v3.1/region/europe";
    CountryName[] listOfCountries = gson.fromJson(restTemplate.getForObject(uri, String.class), CountryName[].class);
      /*for(CountryName cn:listOfCountries){
        countries.add(cn.getName().getCommon());
      }*/

    return Arrays.asList(listOfCountries);
  }

  @GetMapping("/pronadjiRegione")
  public List<String> findRegions(@RequestParam String countryCode) {
    countryCode = countryCode.toLowerCase();
    Gson gson = new Gson();
    List<String> regions = new ArrayList<>();
    RestTemplate restTemplate = new RestTemplate();

    final String uriRegion = "http://battuta.medunes.net/api/region/" + countryCode + "/all/?key=00000000000000000000000000000000";
    Region[] resultRegion = gson.fromJson(restTemplate.getForObject(uriRegion, String.class), Region[].class);
    for (Region r : resultRegion) {
      regions.add(r.getRegion());
    }

    return regions;
  }

  @GetMapping("/pronadjiGradove")
  public List<String> findCities(@RequestParam String countryCode, @RequestParam String region) {
    countryCode = countryCode.toLowerCase();
    Gson gson = new Gson();
    List<String> cities = new ArrayList<>();
    RestTemplate restTemplate = new RestTemplate();

    String uriCity = "http://battuta.medunes.net/api/city/" + countryCode + "/search?region=" + region + "&key=00000000000000000000000000000000";
    City[] resultCity = gson.fromJson(restTemplate.getForObject(uriCity, String.class), City[].class);
    for (City c : resultCity) {
      cities.add(c.getCity());
    }
    return cities;
  }

  @GetMapping("/pronadjiMuzej")
  public  ResponseEntity<MuzejEntity> getMuseum(@RequestParam String name,@RequestParam String id){
    MuzejEntity muzejEntity=new MuzejEntity();
    if(isUserAdmin(id)) {
      muzejEntity= muzejEntityRepository.findAll().stream().filter(elem -> elem.getNaziv().equals(name)).collect(Collectors.toList()).get(0);
      return ResponseEntity.ok().body(muzejEntity);
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(muzejEntity);
  }

  @RequestMapping("/dodajVirtuelnuPosjetu")
  @PostMapping(consumes = "application/json")
  public ResponseEntity<VirtuelnaPosjetaEntity> addVirtualTour(@RequestBody String body,@RequestParam String id){
    VirtuelnaPosjetaEntity virtuelnaPosjetaEntity=new VirtuelnaPosjetaEntity();
    if(isUserAdmin(id)) {
      Gson gson = new Gson();
      virtuelnaPosjetaEntity = gson.fromJson(body, VirtuelnaPosjetaEntity.class);

      this.currentVirtualVisitsRepository.save(virtuelnaPosjetaEntity);
      System.out.println(virtuelnaPosjetaEntity);
      return ResponseEntity.ok().body(virtuelnaPosjetaEntity);
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(virtuelnaPosjetaEntity);
  }

  @RequestMapping("/dodajMuzej")
  @PostMapping(consumes = "application/json")
  public ResponseEntity<MuzejEntity> addMuseum(@RequestBody MuzejEntity body,@RequestParam String id) {
    if(isUserAdmin(id)) {
      this.muzejEntityRepository.save(body);
      return ResponseEntity.ok().body(body);
    }
    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(null);
  }
  private boolean isUserAdmin(String id){
    KorisnikEntity user= activeUsersRepository.findAll().stream()
      .filter(elem->elem.getId()==Integer.valueOf(id)).findFirst().get();
    if(user.getIsAdmin()==1){
      return true;
    }
    return false;
  }
}
