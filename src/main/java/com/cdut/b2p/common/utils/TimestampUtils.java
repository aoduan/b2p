package com.cdut.b2p.common.utils;


public class TimestampUtils {
	
	public static Long now(){
        return System.currentTimeMillis();
    }
	
	public static Long timeAfter(long time){
        return System.currentTimeMillis() + time;
    }
	
	
	public static boolean IsAfter(Long time) {
		Long s = time - System.currentTimeMillis();
		return s < 0;
	}
}
