package virtual_museum.backend.virtual_museum_backend.services;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.apache.coyote.Response;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.EntityResponse;

import javax.mail.Multipart;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@CrossOrigin("*")
@RestController
@RequestMapping("/korisnik")
public class DownloadService {
  private  final String FOLDER="presentations";
  private  final String VIDEO="video.txt";
  public DownloadService(){}
  @SneakyThrows
  @GetMapping()
  public List<String> downloadPictures(@RequestParam("pictures") String nameOfPresentation){
    List<String> list=new ArrayList<>();
    if(loadPresentationProperties(nameOfPresentation)) {
      File folder = new File(FOLDER + File.separator + nameOfPresentation);
      File[] files = folder.listFiles();
      if (files == null) {
        files = new File[]{};
      }
      list= Arrays.stream(files)
        .filter(this::isFilePicture)
        .map(file -> generateUrl(nameOfPresentation, file))
        .collect(Collectors.toList());
    }
    return list;
  }
  @SneakyThrows
  @GetMapping("/video")
  public ResponseEntity<String> downloadVideo(@RequestParam("video") String nameOfPresentation){
    String videoLink="";
    if(loadPresentationProperties(nameOfPresentation)) {
      File folder = new File(FOLDER + File.separator + nameOfPresentation);
      File[] files = folder.listFiles();
      if (files == null) {
        files = new File[]{};
      }
      Optional<File>videoFile=Arrays.stream(files)
        .filter(elem->elem.getName().equals(VIDEO)).findFirst();
      if(videoFile.isPresent()){
        videoLink=new Gson().toJson(Files.readString(videoFile.get().toPath()));
      }
    }
    System.out.println(videoLink);
    return ResponseEntity.ok().contentType(MediaType.TEXT_PLAIN).body(videoLink);
  }
  @SneakyThrows
  private boolean loadPresentationProperties(String nameOfPresentation){
    File folder = new File(FOLDER + File.separator + nameOfPresentation);
    File[] files = folder.listFiles();
    if (files == null) {
     return false;
    }
    Optional<File> optionalFile=Arrays.stream(files)
      .filter(this::isFileProperties).findFirst();
    if(!optionalFile.isPresent()){
      return  false;
    }
    System.out.println(optionalFile.get().getName());
    File propertiesFile=optionalFile.get();
    Properties prop = new Properties();
    InputStream input = null;

      input = new FileInputStream(propertiesFile);
      prop.load(input);
      String date=prop.getProperty("DATE");
      String start = prop.getProperty("START");
      String duration = prop.getProperty("DURATION");
    System.out.println(start+" "+duration);
    LocalTime currTime= LocalDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS);
    LocalDate today=LocalDateTime.now().toLocalDate();

    String [] timeStartGroup=start.split(":");
    String[] durationGroup=duration.split(":");
    LocalTime endTime=null;
    int hourStart=Integer.valueOf(timeStartGroup[0]);
    int hourDuration=Integer.valueOf(durationGroup[0]);
    int minuteStart=Integer.valueOf(timeStartGroup[1]);
    int minuteDuration=Integer.valueOf(durationGroup[1]);
    int secondStart=Integer.valueOf(timeStartGroup[2]);
    int secondDuration=Integer.valueOf(durationGroup[2]);
    if(hourStart+hourDuration<24) {
      if (minuteStart+minuteDuration<60) {
        if (secondStart + secondDuration < 60)
          endTime = LocalTime.of(hourStart + hourDuration, minuteStart + minuteDuration, secondStart + secondDuration);
        else
          endTime = LocalTime.of(hourStart + hourDuration, minuteStart + minuteDuration+1, secondDuration - (60 - secondStart));
      }
      else{
        endTime = LocalTime.of(hourStart + hourDuration + 1, minuteDuration - (60 - minuteStart), secondStart + secondDuration);
      }
    }
    System.out.println(endTime+ " "+currTime);
    if(endTime.isBefore(currTime)){
      return  false;
    }
    return  true;
  }
  private String generateUrl(String nameOfPresentation, File file) {
    String nameOfPresentationEncoded=URLEncoder.encode(nameOfPresentation,StandardCharsets.UTF_8);
    return "http://localhost:8080/korisnik/" + nameOfPresentationEncoded + "/" + file.getName();
  }

  private boolean isFilePicture(File file) {
    String fileName = file.getName();
    List<String> supportedExts = List.of(".jpg", ".jpeg", ".png", ".gif");
    return supportedExts.stream().anyMatch(fileName::endsWith);
  }
  private boolean isFileProperties(File file) {
    String fileName = file.getName();
    return fileName.endsWith(".properties");
  }
  @SneakyThrows
  @GetMapping("/{nameOfPresentation}/{picture}")
  public ResponseEntity<byte[]> downloadPicture(@PathVariable ("nameOfPresentation") String nameOfPresentation, @PathVariable("picture") String picture) {
    File pictureFile=new File(FOLDER+File.separator+nameOfPresentation+File.separator+picture);
    byte[] bytes = Files.readAllBytes(pictureFile.toPath());
    MediaType contentType = null;
    if(picture.endsWith(".jpg") || picture.endsWith(".jpeg")){
      contentType = MediaType.IMAGE_JPEG;
    } else if(picture.endsWith(".png")) {
      contentType = MediaType.IMAGE_PNG;
    } else if(picture.endsWith(".gif")){
      contentType = MediaType.IMAGE_GIF;
    }
    if(contentType == null) {
      return ResponseEntity.ok(bytes);
    }
    return ResponseEntity.ok().contentType(contentType).body(bytes);
  }


  @SneakyThrows
  @PostMapping("/upload-video")
  public void uploadVideo(@RequestParam("link") String link,@RequestParam("folderName") String name){
    File folder=new File(FOLDER+File.separator+name);
    folder.mkdirs();
    File file=new File(folder.getAbsolutePath()+File.separator+VIDEO);
    file.createNewFile();
    PrintWriter pw=new PrintWriter(new FileWriter(file),true);
    pw.write(link);
    pw.close();

  }
}
