package Datos;

import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailData {
	
	public static void sendEmail (String host, String port,
          final String userName, final String password, String toAddress,
         String subject, String reportUser, String details, String c) throws MessagingException {
		  Properties props = new Properties();
		  props.put("mail.smtp.user", userName);
		  props.put("mail.smtp.host", host);
		  props.put("mail.smtp.port", port);
		  props.put("mail.smtp.starttls.enable","true");
		  props.put("mail.smtp.auth", "true");
		  props.put("mail.smtp.ssl.trust", host);
        Authenticator auth = new Authenticator() {
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(userName, password);
        }
        };
		  Session session = Session.getInstance(props, auth);
		  MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        msg.setContent(
        		 "<html>"
        		+ "<h2>Reportado por: "+ c +"</h2>"
        		+ "<h3> Detalles del reporte: </h3>"
        		+ "<p> Reportado por el usuario: "+ reportUser +" </p>"  
        		+ "<p>" + details + "</p>"
        		+ "</html>"
        		, "text/html");
        Transport.send(msg);
		}
}
