package ar.edu.itba.paw.grupo1.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.DatatypeConverter;

import org.apache.log4j.Logger;

public class HashingService {
	
	private static final Logger logger = Logger.getLogger(HashingService.class); 
	
	public static String hash(String payload) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			logger.fatal("Failed to hash the user password", e);
			throw new RuntimeException(e);
		}
		
		byte[] hash;
		try {
			hash = md.digest(payload.getBytes("UTF-8"));
		} catch (UnsupportedEncodingException e) {
			logger.fatal("Failed to hash the user password", e);
			throw new RuntimeException(e);
		}
		
		return DatatypeConverter.printHexBinary(hash);
	}

}
