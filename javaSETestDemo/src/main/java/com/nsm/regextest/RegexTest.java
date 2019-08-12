package com.nsm.regextest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/** 
 * @author nasm 
 * @date 创建时间：2019年3月26日 上午10:43:33 
 * @version 1.0  
 */
public class RegexTest {
	public static void main(String[] args) {
		Pattern p = Pattern.compile("^\\d{3,}");
		Matcher m = p.matcher("123nsmzhenbang");
		System.out.println(m.matches());
		String str ="ZCZC "
				+ "GG ZBBBYPYX ZBAAYZYX ZBAAYMYX 0003 271115 "
				+ "FTCI63 ZBMZ 271115 AAA "
				+ "TAF AMD ZBMZ 271115Z 2706/2812 CNL="
				+ ""
				+ "NNNN";
		StringBuilder sb = new StringBuilder(str);
		String st = sb.substring(sb.indexOf("ZCZC")+4, sb.indexOf("=")+1);
		System.out.println(st.trim());
	}
}
