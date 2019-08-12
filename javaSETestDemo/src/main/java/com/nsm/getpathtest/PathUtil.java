package com.nsm.getpathtest;

public class PathUtil {

    public void getPath(){
        String path1 = this.getClass().getResource("/").getPath();
        String path2 = this.getClass().getResource("").getPath();
        String path3 = System.getProperty("user.dir");

        System.out.println(path1);
        System.out.println(path2);
        System.out.println(path3);
    }

    public static void main(String[] args) {

        PathUtil util = new PathUtil();
        util.getPath();

    }
}
