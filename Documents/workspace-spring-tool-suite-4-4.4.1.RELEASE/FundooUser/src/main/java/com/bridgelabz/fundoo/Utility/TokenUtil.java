package com.bridgelabz.fundoo.Utility;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;
import com.bridgelabz.fundoo.model.EmailModel;

@Component
public class TokenUtil {
	

	  @Autowired
	  private JavaMailSender mailsender;
	  
	  final String token = "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJ1c2VyX2lkIjoiNWRjOTJkMzI0MmQ4ZTExNDdkNWZiNmEzIn0.LQf7jX-B7sNlJq9CeadiFu0s-bBm9KfW3It6sHeABRY";
	  
	 
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
	
	
	public DecodedJWT getVerify(String msg) throws JWTVerificationException, UnsupportedEncodingException
	{
		return TokenUtil.verify(token);
	}
	
	/**
	 * Purpose : API for Create Token
	 */

	public final static String SECRET_TOKEN = "userlr";

	public static String createtoken(String id) {
		try {

			Algorithm algorithm = Algorithm.HMAC256(SECRET_TOKEN); 

			String token = JWT.create().withClaim("user_id", id).sign(algorithm);

			return token;

		} catch (JWTCreationException e) {

			e.printStackTrace();
		} catch (IllegalStateException e) {
			e.printStackTrace();
		}

		return null;

	}

	/**
	 * Purpose: API for DecodeToken
	 * @param token
	 * @return
	 */
	public static String decodetoken(String token) {
		String user_id;

		Verification verification = null;

		try {

			verification = JWT.require(Algorithm.HMAC256(SECRET_TOKEN));

		} catch (IllegalArgumentException e) {

			e.printStackTrace();
		}
		

		JWTVerifier jwtverifier = verification.build();
		
		DecodedJWT decodejwt = jwtverifier.verify(token);

		Claim claim = decodejwt.getClaim("user_id");

		user_id = claim.asString();

		return user_id;
	}
	
	 public static DecodedJWT verify(String token) throws JWTVerificationException, UnsupportedEncodingException {
	        JWTVerifier verifier = JWT.require(Algorithm.HMAC256(Base64.getDecoder().decode(SECRET_TOKEN)))
	                .withIssuer("auth0")
	                .acceptLeeway(1)
	                .acceptExpiresAt(5 * 60)
	                .build();

	        return verifier.verify(token);
	    }
	 

}