package com.nsm.consumetest;

/**
 * @Author NSM
 * @Date 2019/9/24 9:51
 * @Version 1.0
 **/
public class Student {

    private String name;

    private String age;

    public Student(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Student() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
