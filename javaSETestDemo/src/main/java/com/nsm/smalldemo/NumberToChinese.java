package com.nsm.smalldemo;

import java.util.Arrays;

/**
 * @author nasm 
 * @date 创建时间：2019年4月10日 下午4:47:06 
 * @version 1.0  
 */
public class NumberToChinese {

	int a;

	public static String fanStr(String str){
		String newStr = "";
		int i = 0;
		int j = str.length()-1;
		char[] temp = new char[str.length()];
		while(i<=j){
			temp[i] = str.charAt(j);
			temp[j] = str.charAt(i);
			i++;
			j--;
		}
		System.out.println(Arrays.toString(temp));
		return null;
	}

	private static final String[] shu = new String[]{"零","一","二","三","四","五","六","七","八","九"};
	private static final String[] wei = new String[]{"","十","百","千","万","十万","百万","千万","亿"};

	/**
	 * 1110 & 0001
	 * @param key
	 * @return
	 */

	public static int getHash(Object key){
		int h;
		return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
	}

	public static void main(String[] args) {
		NumberToChinese number = new NumberToChinese();
		System.out.println(number.hashCode());
		System.out.println(getHash(number));
		System.out.println(2^1);
	}




}
