package virtual_museum.backend.virtual_museum_backend;
import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.TextBuilder;
import com.aspose.pdf.TextFragment;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.List;

@SpringBootApplication

public class VirtualMuseumBackendApplication {
  private static final String LOG_FILE="users.log";
  private  static final String RESOURCES="src/main/resources/static";
  @SneakyThrows
  public static void main(String[] args) {
    File file=new File(RESOURCES+File.separator+LOG_FILE);
    if(!file.exists()) {
        file.createNewFile();
    }
    SpringApplication.run(VirtualMuseumBackendApplication.class, args);
  }
  @SneakyThrows
  public static void writeLog(String value) {
    PrintWriter pw=new PrintWriter(new FileWriter(new File(RESOURCES+File.separator+LOG_FILE),true));
    pw.write(value+System.lineSeparator());
    pw.close();

  }
  @SneakyThrows
  public static List<String> readLogs(){
    File file=new File(RESOURCES+File.separator+LOG_FILE);
   return Files.readAllLines(file.toPath());
  }

}

