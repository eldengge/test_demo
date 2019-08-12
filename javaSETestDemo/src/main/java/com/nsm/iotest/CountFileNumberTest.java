package com.nsm.iotest;

import java.io.File;

/** 
 * @author nasm 
 * @date 创建时间：2018年11月22日 上午9:55:36 
 * @version 1.0  
 */
public class CountFileNumberTest {
	
	public static void traverseDir(File file,CountTest test){
		if(file.isDirectory()){
			File[] listFiles = file.listFiles();
			for (File dirFile : listFiles) {
				traverseDir(dirFile,test);
			}
			Integer count = test.getCount();
			test.setCount(++count);
		}else{
		
		}
	}
	
	public class CountTest{
		Integer count;
		{
			count = 0;
		}

		public Integer getCount() {
			return count;
		}

		public void setCount(Integer count) {
			this.count = count;
		}
		
	}
	
	public static void main(String[] args) {
		File file = new File("D:/weap");
		CountTest test = new CountFileNumberTest().new CountTest();
		traverseDir(file, test);
		System.out.println(test.getCount());
	}
}
