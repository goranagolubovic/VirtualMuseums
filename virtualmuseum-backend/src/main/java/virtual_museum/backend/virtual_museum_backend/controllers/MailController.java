package virtual_museum.backend.virtual_museum_backend.controllers;

import com.aspose.pdf.Document;
import com.aspose.pdf.Page;
import com.aspose.pdf.TextFragment;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.web.bind.annotation.*;
import virtual_museum.backend.virtual_museum_backend.models.MessageDetails;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/sendEmail")
public class MailController {


  @Autowired
  JavaMailSender javaMailSender;
  @PostMapping()
  public void sendTicket(@RequestBody String body) throws IOException {
    MessageDetails mailDetails=new ObjectMapper().readValue(body, MessageDetails.class);

    /*File fileName=new File("ticket.pdf");
    if(fileName.exists()){
      fileName.delete();
    }
    fileName.createNewFile();
    System.out.println(fileName.getAbsolutePath());
    PrintWriter pw=new PrintWriter((new BufferedWriter(new FileWriter(fileName,true))));
    pw.write(mailDetails.getMessage());*/
    File ticketFolder=new File("ticket");
    if(!ticketFolder.exists()){
      ticketFolder.mkdir();
    }
    Document document = new Document();
  System.out.println(mailDetails.getEmailTo());
//Add page
    Page page = document.getPages().add();

// Add text to new page
    page.getParagraphs().add(new TextFragment(mailDetails.getMessage()));

// Save updated PDF
    document.save(ticketFolder.getName()+File.separator+"ticket.pdf");
    MimeMessagePreparator preparator = new MimeMessagePreparator()
    {
      public void prepare(MimeMessage mimeMessage) throws Exception
      {
        mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailDetails.getEmailTo()));
        mimeMessage.setFrom(new InternetAddress("virtualmuseum200@gmail.com"));
        System.out.println(mimeMessage.getFrom().toString());
        mimeMessage.setSubject(mailDetails.getSubject());
        FileSystemResource file = new FileSystemResource(new File("ticket.pdf"));
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
        helper.addAttachment("ticket.pdf", file);
        helper.setText("",true);
      }
    };

    try {
      javaMailSender.send(preparator);
    }
    catch (Exception ex) {
      System.err.println(ex.getMessage());
    }
  }
}
