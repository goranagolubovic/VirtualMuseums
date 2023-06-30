package virtual_museum.backend.virtual_museum_backend.models;

public class CountryData {
  private String name;
  private String Iso2;
  private String Iso3;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getIso2() {
    return Iso2;
  }

  public void setIso2(String iso2) {
    Iso2 = iso2;
  }

  public String getIso3() {
    return Iso3;
  }

  public void setIso3(String iso3) {
    Iso3 = iso3;
  }
}
