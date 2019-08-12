package com.nsm.ftptest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ftpTest {
    public static void main(String[] args) {
        String ftpHost = "127.0.0.1";
        String ftpUserName = "nsm";
        String ftpPassword = "123456";
        int ftpPort = 21;
        String ftpPath = "data/test/";
        String localPath = "D:\\oracle语句.txt";
        String fileName = "oracle语句.txt";

        //上传一个文件
        try{
            FileInputStream in=new FileInputStream(new File(localPath));
            boolean test = FtpUtil.uploadFile(ftpHost, ftpUserName, ftpPassword, ftpPort, ftpPath, fileName,in);
            System.out.println(test);
        } catch (FileNotFoundException e){
            e.printStackTrace();
            System.out.println(e);
        }
    }
}
