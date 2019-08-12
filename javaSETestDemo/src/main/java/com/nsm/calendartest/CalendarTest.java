package com.nsm.calendartest;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CalendarTest{
	
	public static void testException(int i) throws Exception{
		if (i==2){
			throw new Exception("2333333333333");
		}
	}
	
	public static void main(String[] args) {
//		Calendar calendar = Calendar.getInstance();
//		calendar.set(Calendar.HOUR_OF_DAY, calendar.get(Calendar.HOUR_OF_DAY)-4);
//		System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(calendar.getTime()));
		for(int i=0;i<10;++i){
			try {
				testException(i);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			System.out.println(i);
		}
	}
}
