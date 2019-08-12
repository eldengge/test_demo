package com.nsm.collectiontest;

import java.util.Iterator;
import java.util.List;

public class ListTest {

    public void testt(){
        System.out.printf("hehe");
    }


    public static void main(String[] args) {
        ListTest testObj = new ListTest(){
            @Override
            public void testt() {
                System.out.println("Long may the sun shine！");
            }
        };
        ListTest testObj2 = new ListTest();
        testObj.testt();
        System.out.println(testObj instanceof ListTest);
    }
}
