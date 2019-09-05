package com.nsm.threadtest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Testttt {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList();
        for (int i =0;i<10;i++){
            list.add(1);
        }
        System.out.println(Arrays.toString(list.toArray()));
        Thread t1 = new Thread(()->{
            for (int i = 0;i<list.size();++i){
                int a = list.get(i);
                ++a;
                list.set(i, a);
            }
        });

        Thread t2 = new Thread(()->{
            for (int i = 0;i<list.size();++i){
                int a = list.get(i);
                --a;
                list.set(i, a);
            }
        });
        t1.start();
        t2.start();
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(Arrays.toString(list.toArray()));
        System.out.println(Arrays.toString(list.toArray()));
    }
}
