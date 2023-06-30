package virtual_museum.backend.virtual_museum_backend.controllers;

import ch.qos.logback.classic.Logger;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParser;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import virtual_museum.backend.virtual_museum_backend.VirtualMuseumBackendApplication;
import virtual_museum.backend.virtual_museum_backend.models.*;

import javax.websocket.server.PathParam;
import java.net.http.HttpClient;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@CrossOrigin("*")
@RestController
@RequestMapping("museum-details/weather")
public class WeatherController {
  private int NUM_CITIES=3;
  @GetMapping
    public List<Weather> getWeatherFor3Cities(@RequestParam String country) throws JsonProcessingException {
    VirtualMuseumBackendApplication.writeLog("User watched weather for three cities.");
    List<City> citiesCollection = new ArrayList<>();
    List<Weather>listOfWeathers=new ArrayList<>();
    RestTemplate restTemplate = new RestTemplate();
    Gson gson=new Gson();

    final String uriIso2 = "https://countriesnow.space/api/v0.1/countries/iso";


    CountryCode resultCountryCode = gson.fromJson(restTemplate.getForObject(uriIso2,String.class), CountryCode.class);
    List<CountryData>listOfCountries= Arrays.asList(resultCountryCode.getData());
    Optional<CountryData> countryData=listOfCountries.stream().filter(e -> e.getName().equals(country)).findFirst();
    String countryCode=countryData.get().getIso2().toLowerCase();

     final String uriRegion="http://battuta.medunes.net/api/region/"+countryCode+"/all/?key=00000000000000000000000000000000";
    Region[] resultRegion=gson.fromJson(restTemplate.getForObject(uriRegion,String.class), Region[].class);
    String region=resultRegion[0].getRegion();

    final String uriCity="https://geo-battuta.net/api/city/"+countryCode+"/search?region="+region+"&key=00000000000000000000000000000000";
    City[] resultCity=gson.fromJson(restTemplate.getForObject(uriCity,String.class), City[].class);
    List<City> citiesToShow=new ArrayList<>();
    Random random=new Random();
    int previousPos=-1;
    while(citiesToShow.size()<NUM_CITIES) {
      int pos = random.nextInt(resultCity.length - 1);
        citiesToShow.add(resultCity[pos]);
        citiesToShow=citiesToShow.stream().distinct().collect(Collectors.toList());
    }

    for(City c:citiesToShow){
       String uriWeather="https://api.openweathermap.org/data/2.5/weather?lat="+c.getLatitude()+"&lon="+c.getLongitude()+"&appid=14117d7ae291c79fecef7f9800468c63";
      JsonObject weather=gson.fromJson(restTemplate.getForObject(uriWeather,String.class), JsonObject.class);
      JsonObject weatherData=gson.fromJson(weather.get("main"), JsonObject.class);
      JsonArray weatherIconDataArray=gson.fromJson(weather.get("weather"), JsonArray.class);
      JsonObject weatherIconData=gson.fromJson(weatherIconDataArray.get(0), JsonObject.class);
      String weatherIcon=gson.fromJson(weatherIconData.get("icon"), String.class);
      System.out.println(weatherIcon);
      Double temp= Math.floor(Double.parseDouble(String.valueOf(weatherData.get("temp")))-273.15);
      Double feelTemp= Math.floor(Double.parseDouble(String.valueOf(weatherData.get("feels_like")))-273.15);
      String icon="http://openweathermap.org/img/wn/"+weatherIcon+"@2x.png";
      Weather w=new Weather(temp.toString(),feelTemp.toString(), LocalDateTime.now().getHour()+":"+LocalDateTime.now().getMinute(),c.getCity(),icon);
      listOfWeathers.add(w);
    }
    return  listOfWeathers;
  }
}
