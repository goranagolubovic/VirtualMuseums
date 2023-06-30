package virtual_museum.backend.virtual_museum_backend.models;

public class RssFeedData {
  private String title;
  private String link;
  private String description;
  private String pubDate;

  public String getImage() {
    return image;
  }

  public void setImage(String image) {
    this.image = image;
  }

  private String image;
  public RssFeedData(){

  }
  public RssFeedData(String title, String link, String description, String pubDate,String image) {
    this.title = title;
    this.link = link;
    this.description = description;
    this.pubDate = pubDate;
    this.image=image;
  }


  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getLink() {
    return link;
  }

  public void setLink(String link) {
    this.link = link;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getPubDate() {
    return pubDate;
  }

  public void setPubDate(String pubDate) {
    this.pubDate = pubDate;
  }
}
