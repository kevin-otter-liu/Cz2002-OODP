package test.Notifications;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 * Controller class that sends emails
 * @author kevin
 *
 */
public class SendMailTLS implements Notification{

private String email;

/**
 * Constructor for SendMailTLS class
 * @param email
 */
public SendMailTLS(String email){
	this.email = email;
}

/**
 * Method to send email
 */
public void sendNotification() {
	// following is not a working gmail. have to put in real gmail for it to work
	final String username = "abcd@gmail.com"; // Enter email here
	final String password = "testpassword"; // and password here

	Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");

	Session session = Session.getInstance(props,
	  new javax.mail.Authenticator() {
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(username, password);
		}
	  });

	try {

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress("from-email@gmail.com"));
		message.setRecipients(Message.RecipientType.TO,
			InternetAddress.parse(email)); // to be added an email addr
		message.setSubject("Testing Subject");
		message.setText("You Got Your Course!!!");

		Transport.send(message);

		System.out.println("Done");

	} catch (MessagingException e) {
		throw new RuntimeException(e);
	}
}

}