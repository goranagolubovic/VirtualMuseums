package virtual_museum.backend.virtual_museum_backend.models;

public class CountryCode {
  private String error;
  private String msg;
  private CountryData[] data;

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  public String getMsg() {
    return msg;
  }

  public void setMsg(String msg) {
    this.msg = msg;
  }

  public CountryData[] getData() {
    return data;
  }

  public void setData(CountryData[] data) {
    this.data = data;
  }
}
