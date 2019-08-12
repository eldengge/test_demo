package com.nsm.stringtest;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringTest {


    public static void RegexTest(){
        //Pattern p = Pattern.compile("^\\d{8,}$");
        Pattern p = Pattern.compile("(.*/)(.*jpg|.*gif|.*png)(.*)");
		Matcher m = p.matcher("http://image.nmc.cn/product/2019/06/28/WESA/medium/SEVP_NMC_WESA_SFER_EGH_ACWP_L92_P9_20190628000000000.gif?v=1561684316599");
		if (m.find()){
            System.out.println(m.group(2));
        }
		System.out.println(m.matches());

    }

    public static void main(String[] args) {

        StringTest.RegexTest();


    }
}
