package com.nsm.classtest;

import com.nsm.lamada.People;
import com.nsm.lamada.Student;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author NSM
 * @Date 2019/10/8 16:24
 * @Version 1.0
 **/
public class TestDemo {


    /**
     * 将一个对象的字段复制到另一个对象中
     * @throws IllegalAccessException
     */
    public static void copyClass() throws IllegalAccessException {
        People p = new People();
        p.setSex("man");
        Student stu = new Student();
        Class<People> clazz = People.class;
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            field.set(stu, field.get(p));
        }
        System.out.println(stu.getSex());
    }

    public static void main(String[] args) {
        try {
            copyClass();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
