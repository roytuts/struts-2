package com.roytuts.struts.send.email.attachment;

import java.io.File;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailAttachmentSender {

	public static void sendEmail(final String emailToAddress, final String emailSubject, final String emailBodyText,
			final File file, final String fileName) {
		// make sure you put your gmail address
		final String username = "gmail@gmail.com";

		// make sure you put your correct gmail address password
		final String password = "gmail password";

		// We will put some properties for smtp configurations
		Properties props = new Properties();

		// do not change - start
		props.put("mail.smtp.user", "gmail@gmail.com");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		// props.put("mail.debug", "true");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.EnableSSL.enable", "true");

		// do not change - end
		// we authentcate using your gmail email and password and on successful we
		// create the session
		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {
			// we create new message
			Message message = new MimeMessage(session);

			// set the from 'email address'
			message.setFrom(new InternetAddress(username));

			// set 'to email address'
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailToAddress));

			// set email subject
			message.setSubject(emailSubject);

			// set email message
			message.setText(emailBodyText);

			// create MimeBodyPart for file attachment
			MimeBodyPart messageBodyPart = new MimeBodyPart();

			// we need this also for file attachment
			Multipart multipart = new MimeMultipart();

			// file data
			DataSource source = new FileDataSource(file);

			// attach file
			messageBodyPart.setDataHandler(new DataHandler(source));

			// set meaningful file name
			messageBodyPart.setFileName(fileName);
			
			// set body text
			messageBodyPart.setText(emailBodyText);

			// add the whole content
			multipart.addBodyPart(messageBodyPart);

			// required for file
			message.setContent(multipart);
			
			System.out.println("Sending Email to " + emailToAddress + " from " + username + " with Subject '"
					+ emailSubject + "'.");

			// send the email
			Transport.send(message);

			System.out.println("Email with attachment successfully sent");
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}

}
