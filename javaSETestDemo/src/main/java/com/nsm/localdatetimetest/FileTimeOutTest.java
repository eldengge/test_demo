package com.nsm.localdatetimetest;

import java.io.File;
import java.time.Instant;


/** 
 * @author nasm 
 * @date 创建时间：2018年11月27日 上午10:39:43 
 * @version 1.0  
 */
public class FileTimeOutTest {
	
	private final static Integer fileExistTime = 1;
	
	public void traverseDir(File file,TimeOutFileCount count){
		if(file.isDirectory()){
			File[] listFiles = file.listFiles();
			for (File dirFile : listFiles) {
				traverseDir(dirFile,count);
			}
		}else{
			Integer number = count.getCount();
			count.setCount(isSourceFileOutTime(file)?++number:number);
		}
	}
	public boolean isSourceFileOutTime(File file){
		long time = file.lastModified();
		Instant now = Instant.now();
		if(time <= now.minusSeconds(fileExistTime*60).toEpochMilli()){
			return true;
		}else{
			return false;
		}
	}
	
	public static void main(String[] args) {
		TimeOutFileCount count = new TimeOutFileCount();
		FileTimeOutTest test = new FileTimeOutTest();
		test.traverseDir(new File("D:/weap"), count);
		System.out.println(count.getCount());
	}
}
