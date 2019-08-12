package com.nsm.lamada;
/** 
 * @author nasm 
 * @date 创建时间：2019年5月7日 下午1:45:13 
 * @version 1.0  
 */
public class TestClass {
	
	private String content;
	
	public TestClass(){}
	
	public TestClass(String content){
		this.content = content;
	}
	
	public void testMethod1(String str){
		System.out.println(str);
	}
	
	public static void testMethod2(String str){
		System.out.println(str);
	}

	@Override
	public String toString() {
		return "TestClass [content=" + content + "]";
	}
	
}
