package virtual_museum.backend.virtual_museum_backend.models;

import javax.mail.Address;

public class MessageDetails {
  private String emailFrom;
  private String emailTo;
  private String subject;
  private String message;
 public MessageDetails(){}
  public MessageDetails(String emailFrom, String emailTo, String subject, String message) {
    this.emailFrom = emailFrom;
    this.emailTo = emailTo;
    this.subject = subject;
    this.message = message;
  }

  public String getEmailFrom() {
    return emailFrom;
  }

  public void setEmailFrom(String emailFrom) {
    this.emailFrom = emailFrom;
  }

  public String getEmailTo() {
    return emailTo;
  }

  public void setEmailTo(String emailTo) {
    this.emailTo = emailTo;
  }

  public String getSubject() {
    return subject;
  }

  public void setSubject(String subject) {
    this.subject = subject;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
