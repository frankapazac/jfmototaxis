package com.munichosica.myapp.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	public static String getFechaActual(){
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy");
		return format.format(date);
	}
	
	public static String getFechaHoraActual(){
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("dd/MM/yyyy HH:mm");
		return format.format(date);
	}
	
	public static String getHoraActual(){
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("HH:mm");
		return format.format(date);
	}	
}
