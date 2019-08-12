package com.nsm.countfilebytime;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.TreeMap;

/** 
 * @author nasm 
 * @date 创建时间：2018年11月28日 下午6:26:32 
 * @version 1.0  
 */
public class CountTest {
	private static final String PATH = "C:/2018年11月27日  气象数据库资料/20120822/20120822/";
	
	public TreeMap<LocalDateTime, CountData> getCountDataMap(String name){
		TreeMap<LocalDateTime, CountData> dataMap = new TreeMap<>();
		String path = PATH+name;
		File file = new File(path);
		File[] listFiles = file.listFiles((pathname)->!pathname.isDirectory());
		for (File file2 : listFiles) {
			//LocalDateTime time = getAwosTime(file2);
			//LocalDateTime time = getCacTime(file2);
			//LocalDateTime time = getBufrTime(file2);
			//LocalDateTime time = getfaxTime(file2);
			//LocalDateTime time = getgribTime(file2);
			LocalDateTime time = getMsgTime(file2);
			//LocalDateTime time = getProgTime(file2);
			//LocalDateTime time = getRadTime(file2);
			//LocalDateTime time = getStarTime(file2);
			if(time==null){
				continue;
			}
			if(dataMap.get(time)==null){
				CountData data = new CountData();
				data.setSize(file2.length());
				data.setNumber(1);
				data.setTime(time);
				dataMap.put(time, data);
			}else{
				Integer number = dataMap.get(time).getNumber();
				long length = dataMap.get(time).getSize();
				dataMap.get(time).setNumber(++number);
				dataMap.get(time).setSize(length+file2.length());
			}
		}
		return dataMap;
	}
	
	private LocalDateTime getAwosTime(File file){
		String fileName = file.getName();
		String timeStr = fileName.substring(4, fileName.indexOf('.'));
		return LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
	}
	
	private LocalDateTime getBufrTime(File file){
		String fileName = file.getName();
		String timeStr = "19940323";
		timeStr += fileName.substring(fileName.indexOf('.')+3,fileName.indexOf('.')+5);
		return LocalDateTime.parse(timeStr,DateTimeFormatter.ofPattern("yyyyMMddHH"));
	}
	
	private LocalDateTime getCacTime(File file){
		String fileName = file.getName();
		String timeStr = fileName.substring(2, 2+12);
		return LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyyMMddHHmm"));
	}
	
	private LocalDateTime  getfaxTime(File file){
		String fileName = file.getName();
		char ch = fileName.charAt(fileName.length()-1);
		String timeStr = "19940323";
		switch (ch) {
		case '0':
			timeStr+="00"; break;
		case '2':
			timeStr+="12"; break;
		case '6':
			timeStr+="06"; break;
		case '8':
			timeStr+="18"; break;
		}
		return LocalDateTime.parse(timeStr,DateTimeFormatter.ofPattern("yyyyMMddHH"));
	}
	
	private LocalDateTime  getgribTime(File file){
		String fileName = file.getName();
		char ch = fileName.charAt(fileName.length()-1);
		String timeStr = "19940323";
		switch (ch) {
		case '0':
			timeStr+="00"; break;
		case '2':
			timeStr+="12"; break;
		case '6':
			timeStr+="06"; break;
		case '8':
			timeStr+="18"; break;
		}
		return LocalDateTime.parse(timeStr,DateTimeFormatter.ofPattern("yyyyMMddHH"));
	}
	
	private LocalDateTime getMsgTime(File file){
		String fileName = file.getName();
		String timeStr = fileName.substring(2, 2+12);
		return LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyyMMddHHmm")).withMinute(0);
	}
	
	private LocalDateTime  getProgTime(File file){
		String fileName = file.getName();
		String sub = fileName.substring(fileName.indexOf('.')+1,fileName.indexOf('.')+4);
		if(!sub.substring(0, 2).equals("22")){
			return null;
		}
		char ch = sub.charAt(2);
		String timeStr = "19940323";
		switch (ch) {
		case '0':
			timeStr+="00"; break;
		case '2':
			timeStr+="12"; break;
		case '6':
			timeStr+="06"; break;
		case '8':
			timeStr+="18"; break;
		default:
			return null;
		}
		return LocalDateTime.parse(timeStr,DateTimeFormatter.ofPattern("yyyyMMddHH"));
	}
	
	private LocalDateTime getRadTime(File file){
		String fileName = file.getName();
		String timeStr = "19940323";
		timeStr+=fileName.substring(4, 6);
		return LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyyMMddHH"));
	}
	
	private LocalDateTime getStarTime(File file){
		String fileName = file.getName();
		String timeStr = "1994";
		timeStr+=fileName.substring(3, 9);
		return LocalDateTime.parse(timeStr, DateTimeFormatter.ofPattern("yyyyMMddHH"));
	}
	
	public static void main(String[] args) {
		TreeMap<LocalDateTime, CountData> map = new CountTest().getCountDataMap("msg");
		Collection<CountData> values = map.values();
		long number = 0L;
		long size = 0L;
		for (CountData countData : values) {
			System.out.println(countData);
			number+=countData.getNumber();
			size+=countData.getSize()/1024;
		}
		System.out.println("avg ["+"number="+number/values.size()+", "+"size="+size/values.size()+"kb]");
	}
}
