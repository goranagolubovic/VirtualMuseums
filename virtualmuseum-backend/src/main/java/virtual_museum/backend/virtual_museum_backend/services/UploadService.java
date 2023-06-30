package virtual_museum.backend.virtual_museum_backend.services;

import com.google.gson.Gson;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.Multipart;
import java.io.*;
import java.nio.file.Files;
import java.util.Properties;
import java.util.stream.Stream;

@CrossOrigin("*")
@RestController
@RequestMapping("/admin")
public class UploadService {
  private static final String PROPERTIES ="presentation.properties";
  private  final String FOLDER="presentations";
  private  final String VIDEO="video.txt";
  public UploadService(){}
  @SneakyThrows
  @PostMapping("/upload-picture")
  public void uploadPicture(@RequestParam("files") MultipartFile[] files,@RequestParam("folderName") String name,@RequestParam("date") String date,@RequestParam("time") String time,@RequestParam("duration") String duration){
    File folder=new File(FOLDER+File.separator+name);
    folder.mkdirs();
    createPropertyFile(folder,date,time,duration);
    Stream.of(files).forEach(file -> {
      try {
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File(folder.getAbsolutePath()+File.separator+file.getOriginalFilename()));
      } catch (IOException e) {
        e.printStackTrace();
      }
    });
  }

  @SneakyThrows
  private void createPropertyFile(File folder, String date, String time, String duration) {
    File propertiesFile=new File(folder+File.separator+PROPERTIES);
    propertiesFile.createNewFile();
    Properties props = new Properties();
    props.put("DATE", date);
    props.put("START", time);
    props.put("DURATION", duration);

    FileOutputStream outputStrem = new FileOutputStream(propertiesFile);
    //Storing the properties file
    props.store(outputStrem,"");
  }

  @SneakyThrows
  @PostMapping("/upload-video")
  public void uploadVideo(@RequestParam("link") String link,@RequestParam("folderName") String name,@RequestParam("date") String date,@RequestParam("time") String time,@RequestParam("duration") String duration){
    File folder=new File(FOLDER+File.separator+name);
    folder.mkdirs();
    createPropertyFile(folder,date,time,duration);
    File file=new File(folder.getAbsolutePath()+File.separator+VIDEO);
    file.createNewFile();
    PrintWriter pw=new PrintWriter(new FileWriter(file),true);
    pw.write(link);
    pw.close();

  }
}
