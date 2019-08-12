package com.nsm.collectiontest;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void testDemo1(){

        Map<String,String> map = new HashMap<>();
        map.put("1", "hehe");
        System.out.println(map.get("2"));
    }

    public static void main(String[] args) {
        testDemo1();
    }
}
