package virtual_museum.backend.virtual_museum_backend.models;

import java.time.LocalDateTime;

public class Weather {
  public Weather(String temp, String feelsLike, String dateTime,String city,String icon) {
    this.temp = temp;
    this.feelsLike = feelsLike;
    this.dateTime = dateTime;
    this.city=city;
    this.icon=icon;
  }

  public String getTemp() {
    return temp;
  }

  public void setTemp(String temp) {
    this.temp = temp;
  }

  public String getFeelsLike() {
    return feelsLike;
  }

  public void setFeelsLike(String feelsLike) {
    this.feelsLike = feelsLike;
  }

  public String getDateTime() {
    return dateTime;
  }

  public void setDateTime(String dateTime) {
    this.dateTime = dateTime;
  }

  private String temp;
  private String feelsLike;
  private String dateTime;

  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  private String city;

  public String getIcon() {
    return icon;
  }

  public void setIcon(String icon) {
    this.icon = icon;
  }

  private String icon;
}
