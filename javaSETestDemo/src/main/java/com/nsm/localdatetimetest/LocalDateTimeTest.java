package com.nsm.localdatetimetest;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.temporal.TemporalField;

/** 
 * @author nasm 
 * @date 创建时间：2018年11月27日 上午11:24:22 
 * @version 1.0  
 */
public class LocalDateTimeTest {
	public static void main(String[] args) {
		//LocalDateTime获取毫秒
		LocalDateTime time1 = LocalDateTime.now();
		//OffsetDateTime.now().getOffset()获取默认ZoneOffset
		long epochMilli1 = time1.toInstant(OffsetDateTime.now().getOffset()).toEpochMilli();
		System.out.println(epochMilli1);
		//LocalDateTime获取秒
		long epochSecond1 = time1.toEpochSecond(OffsetDateTime.now().getOffset());
		System.out.println(epochSecond1);
		//Instant获取毫秒
		long epochMilli2 = Instant.now().toEpochMilli();
		System.out.println(epochMilli2);
		
	}
}
