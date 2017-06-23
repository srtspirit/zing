package com.zingcrm.utility;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.springframework.stereotype.Component;

@Component
public class Encrypt {

	public static String encryptPwd(String pwd)
	{
		    MessageDigest messageDigest;

	        try {
	            messageDigest = MessageDigest.getInstance("MD5"); // Encryption algorithm
	            messageDigest.update(pwd.getBytes(),0, pwd.length());  
	            String hashedPass = new BigInteger(1,messageDigest.digest()).toString(16);  // Encrypted string	            
	            return hashedPass;
	        } catch (NoSuchAlgorithmException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }  
	        return pwd;
	    }
	
}
