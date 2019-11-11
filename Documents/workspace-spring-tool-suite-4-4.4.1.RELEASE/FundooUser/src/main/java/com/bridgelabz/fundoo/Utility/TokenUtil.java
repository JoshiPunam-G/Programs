package com.bridgelabz.fundoo.Utility;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.auth0.jwt.interfaces.Verification;

@Component
public class TokenUtil {

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

	public String decodetoken(String token) {
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
	
	
}