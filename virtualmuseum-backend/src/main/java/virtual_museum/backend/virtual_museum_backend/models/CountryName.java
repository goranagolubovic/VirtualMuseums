package virtual_museum.backend.virtual_museum_backend.models;

public class CountryName {
  private Name name;

  public String getCca2() {
    return cca2;
  }

  public void setCca2(String cca2) {
    this.cca2 = cca2;
  }

  private String cca2;

  public Name  getName() {
    return name;
  }

  public void setName(Name name) {
    this.name = name;
  }

  public CountryName(Name name,String cca2) {
    this.name = name;
    this.cca2=cca2;
  }
}
