package virtual_museum.backend.virtual_museum_backend.comonents;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import virtual_museum.backend.virtual_museum_backend.models.entities.UlaznicaEntity;
import virtual_museum.backend.virtual_museum_backend.repositories.MuzejEntityRepository;
import virtual_museum.backend.virtual_museum_backend.repositories.UlaznicaRepository;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.sql.Array;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Component
@EnableScheduling
public class NotificationComponent {
  @Autowired
  JavaMailSender javaMailSender;
  private UlaznicaRepository ulaznicaRepository;
  private MuzejEntityRepository muzejEntityRepository;
  private List<UlaznicaEntity>listStart=new ArrayList<>();
  private List<UlaznicaEntity>listEnd=new ArrayList<>();
  public  NotificationComponent(UlaznicaRepository ulaznicaRepository, MuzejEntityRepository muzejEntityRepository){
    this.ulaznicaRepository=ulaznicaRepository;
    this.muzejEntityRepository=muzejEntityRepository;
  }
  @Scheduled(fixedRate = 60, timeUnit = TimeUnit.SECONDS)
  public void sendStartNotification(){
    this.ulaznicaRepository.findAll().stream().forEach(elem-> {
      LocalTime currTime = LocalDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS);
      LocalDate today = LocalDateTime.now().toLocalDate();

      String[] timeStartGroup = elem.getVirtuelnaPosjetaVrijeme().split(":");
      String[] durationGroup = elem.getVirtuelnaPosjetaTrajanje().split(":");

      LocalTime timeForOneHour;

      LocalTime elemTime = LocalTime.of(Integer.valueOf(timeStartGroup[0]),Integer.valueOf(timeStartGroup[1]), currTime.getSecond(), 0);

        timeForOneHour =currTime.plusHours(1);
      if (elem.getVirtuelnaPosjetaDatum().equals(today.toString()) && elemTime.equals(timeForOneHour)) {
        String museum = muzejEntityRepository.findAll().stream()
          .filter(e -> e.getId() == elem.getVirtuelnaPosjetaMuzejId())
          .collect(Collectors.toList()).stream().findFirst().get().getNaziv();
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
          public void prepare(MimeMessage mimeMessage) throws Exception {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(elem.getKorisnik().getEmail()));
            mimeMessage.setFrom(new InternetAddress("virtualmuseum200@gmail.com"));
            mimeMessage.setSubject("START NOTIFICATION");
            mimeMessage.setText("Virtual  tour for " + museum + " will start in 1 hour.");
          }
        };

        try {

          if(!listStart.contains(elem)) {
            listStart.add(elem);
            javaMailSender.send(preparator);
          }
        } catch (Exception ex) {
          System.err.println(ex.getMessage());
        }
      }
    });
  }
  @Scheduled(fixedRate = 60, timeUnit = TimeUnit.SECONDS)
  public void sendEndNotification(){
    this.ulaznicaRepository.findAll().stream().forEach(elem-> {
      LocalTime currTime=LocalDateTime.now().toLocalTime().truncatedTo(ChronoUnit.SECONDS);
      LocalDate today=LocalDateTime.now().toLocalDate();

      String [] timeStartGroup=elem.getVirtuelnaPosjetaVrijeme().split(":");
      String[] durationGroup=elem.getVirtuelnaPosjetaTrajanje().split(":");

      LocalTime timeForFiveMinutes;
      LocalTime elemEndTime;

      timeForFiveMinutes=currTime.plusMinutes(5);
        elemEndTime=LocalTime.of(
          Integer.parseInt(timeStartGroup[0]),
          Integer.parseInt(timeStartGroup[1]),
          currTime.getSecond(),
          0
        ).plusHours(Integer.parseInt(durationGroup[0])).plusMinutes(Integer.parseInt(durationGroup[1]));


      if( elem.getVirtuelnaPosjetaDatum().equals(today.toString()) && elemEndTime.equals(timeForFiveMinutes)) {
        String museum=muzejEntityRepository.findAll().stream()
          .filter(e->e.getId()==elem.getVirtuelnaPosjetaMuzejId())
          .collect(Collectors.toList()).stream().findFirst().get().getNaziv();
        MimeMessagePreparator preparator = new MimeMessagePreparator()
        {
          public void prepare(MimeMessage mimeMessage) throws Exception
          {
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(elem.getKorisnik().getEmail()));
            mimeMessage.setFrom(new InternetAddress("virtualmuseum200@gmail.com"));
            mimeMessage.setSubject("END NOTIFICATION");
            mimeMessage.setText("Virtual  tour "+museum+" will expiry in 5 minutes.");
          }
        };

        try {
          if(!listEnd.contains(elem)) {
            javaMailSender.send(preparator);
            listEnd.add(elem);
          }
        }
        catch (Exception ex) {
          System.err.println(ex.getMessage());
        }
      }
    });
  }
}
