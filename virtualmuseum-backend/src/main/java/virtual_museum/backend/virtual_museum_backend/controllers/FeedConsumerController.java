package virtual_museum.backend.virtual_museum_backend.controllers;

import com.rometools.rome.feed.synd.SyndEntry;
import com.rometools.rome.feed.synd.SyndFeed;
import com.rometools.rome.io.SyndFeedInput;
import com.rometools.rome.io.XmlReader;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import virtual_museum.backend.virtual_museum_backend.VirtualMuseumBackendApplication;
import virtual_museum.backend.virtual_museum_backend.models.RssFeedData;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/rssfeed")
public class FeedConsumerController {
  private   String url = "https://www.huffpost.com/section/arts/feed";
  @GetMapping
  public List<RssFeedData> getRssFeedData() {
    VirtualMuseumBackendApplication.writeLog("User has watched culture news.");
   List<RssFeedData>listOfFeeds=new ArrayList<>();
   int i=0;
    try {
      try (XmlReader reader = new XmlReader(new URL(url))) {
        SyndFeed feed = new SyndFeedInput().build(reader);
        for (SyndEntry entry : feed.getEntries()) {
          //skip first
          if(i!=0) {
            RssFeedData rssFeedData = new RssFeedData(
              entry.getTitle(), entry.getLink(), entry.getDescription().getValue(), entry.getPublishedDate().toString(),entry.getEnclosures().get(0).getUrl());
            listOfFeeds.add(rssFeedData);
          }
          i++;
        }
      }
    }  catch (Exception e) {
      e.printStackTrace();
    }
  return  listOfFeeds;
  }
}
