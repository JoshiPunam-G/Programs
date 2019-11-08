package com.bridgelabz.demo.service;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.bridgelabz.demo.Utility.TokenUtil;
import com.bridgelabz.demo.model.EmailModel;

@Service
public class EmailService {

	  @Autowired
	  private JavaMailSender mailsender;
	 
	/*
	 * public EmailService(JavaMailSender mailsender) { this.mailsender =
	 * mailsender; }
	 * 
	 * public EmailService() {
	 * 
	 * }
	 */

	/**
	 * Purpose :Implementation Of Java Mail
	 * 
	 * @throws Exception
	 */

	public void sendEmail(EmailModel emailmodel) throws Exception {
	
		MimeMessage message = mailsender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);

		helper.setTo(emailmodel.getTo());
		helper.setText(emailmodel.getBody());
		helper.setSubject(emailmodel.getSubject());
	   

		mailsender.send(message);
	}

	public String getLink(String link, String id) {
		return link + TokenUtil.createtoken(id);
	}
}
