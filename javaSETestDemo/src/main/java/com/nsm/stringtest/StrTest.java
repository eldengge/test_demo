package com.nsm.stringtest;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StrTest {
	public static void main(String[] args) {
		String test = "NSM";
		Pattern pa = Pattern.compile("^(\\+|\\-)?\\p{Upper}+$");
		Matcher ma = pa.matcher(test);
		System.out.println(ma.matches());
		List<String> list = new ArrayList<String>();
	}
}
