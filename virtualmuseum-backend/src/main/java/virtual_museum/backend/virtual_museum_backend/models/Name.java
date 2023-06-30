package virtual_museum.backend.virtual_museum_backend.models;

public class Name {

  public String getOfficial() {
    return official;
  }

  public void setOfficial(String official) {
    this.official = official;
  }

  public Name(String official, String common, String nativeName) {
    this.official = official;
    this.common = common;
  }

  public String getCommon() {
    return common;
  }

  public void setCommon(String common) {
    this.common = common;
  }

  private String official;
  private String common;


}
