package Utility;

import java.io.File;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.Multipart;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeBodyPart;
import jakarta.mail.internet.MimeMessage;
import jakarta.mail.internet.MimeMultipart;

public class EmailUtility {

	 public static void sendReport(String reportPath) {

	        final String sender = System.getenv("REPORT_EMAIL");
	        final String password = System.getenv("REPORT_EMAIL_PASSWORD");

	        String receiver = "receiver@example.com";

	        if (sender == null || password == null) {
	            System.out.println("Email credentials not configured.");
	            return;
	        }

	        Properties properties = new Properties();

	        properties.put("mail.smtp.host", "smtp.gmail.com");
	        properties.put("mail.smtp.port", "587");
	        properties.put("mail.smtp.auth", "true");
	        properties.put("mail.smtp.starttls.enable", "true");

	        Session session = Session.getInstance(
	                properties,
	                new Authenticator() {
	                    protected PasswordAuthentication
	                    getPasswordAuthentication() {
	                        return new PasswordAuthentication(
	                                sender, password);
	                    }
	                });

	        try {

	            Message message = new MimeMessage(session);

	            message.setFrom(new InternetAddress(sender));

	            message.setRecipients(
	                    Message.RecipientType.TO,
	                    InternetAddress.parse(receiver));

	            message.setSubject("Selenium Automation Execution Report");

	            MimeBodyPart textPart = new MimeBodyPart();

	            textPart.setText(
	                    "Hi Team,\n\nPlease find attached the latest "
	                    + "Selenium Automation Execution Report.\n\n"
	                    + "Regards,\nNikhil");

	            MimeBodyPart attachmentPart = new MimeBodyPart();

	            File report = new File(reportPath);

	            attachmentPart.attachFile(report);

	            Multipart multipart = new MimeMultipart();

	            multipart.addBodyPart(textPart);
	            multipart.addBodyPart(attachmentPart);

	            message.setContent(multipart);

	            Transport.send(message);

	            System.out.println("Report email sent successfully!");

	        } catch (Exception e) {

	            e.printStackTrace();

	        }
	    }
}
