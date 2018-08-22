package com.cdut.b2p.common.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Random;

import com.cdut.b2p.common.exception.UtilsException;

public class SecurityUtils {
	
	public static String getOneMD5(String str){
	    try {  
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(str.getBytes());
	        return new BigInteger(1, md.digest()).toString(16);
	    } catch (Exception e) {
	    	throw new UtilsException();
	    }
	}
	
	public static String getMD5(String str){
	    return getOneMD5(getOneMD5(str));
	}
	
}
