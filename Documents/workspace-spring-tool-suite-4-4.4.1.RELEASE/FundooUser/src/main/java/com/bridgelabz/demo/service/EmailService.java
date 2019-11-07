package com.bridgelabz.demo.service;

import javax.mail.internet.MimeMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

public class EmailService {
	
	private JavaMailSender mailsender;

	public EmailService(JavaMailSender mailsender) {
		this.mailsender = mailsender;
	}
	
	public EmailService()
	{
		
	}
	
	/*
	 * @Autowired private TokenUtil tokenutility;
	 */
	

	/**
	 * Purpose :Implementation Of Java Mail 
	 * @throws Exception
	 */
	
	  public void sendEmail() throws Exception{
	        MimeMessage message = mailsender.createMimeMessage();
	        MimeMessageHelper helper = new MimeMessageHelper(message);
	         
	        helper.setTo("chaudharipratibha26@gmail.com");
	        helper.setText("How are you?");
	        helper.setSubject("Hi");
	         
	        mailsender.send(message);
	    }
	  
	  /**
		 * Purpose :Implementation Of Java Mail sender
		 * @return
		 
	    @RequestMapping("/simpleemail")
	    @ResponseBody
	  public  String home() {
	     try {
	            sendEmail();
	            return "Email Sent!";
	        }catch(Exception ex) {
	            return "Error in sending email: "+ex;
	        }
	    }
	  
	 */

	  
	
	/*
	 * public String getLink(String link,String id) { return
	 * link+TokenUtil.createtoken(id); }
	 */
	 
	 
}
