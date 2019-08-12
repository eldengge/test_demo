package com.nsm.iotest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileUtil {

    private final  static Integer HOLD_DAY = 0;

    public static void copyFile(File file1 , File file2){
        FileInputStream in = null;
        FileOutputStream out = null;
        try {
            in = new FileInputStream(file1);
            out = new FileOutputStream(file2);
            byte[] temp = new byte[3];
            int i;
            while((i = in.read(temp, 0, temp.length))!=-1){
                out.write(temp, 0, i);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if(in != null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void delFile(File file){
        if (file.isFile()){
            file.delete();
        }else{
            File[] files = file.listFiles();
            for (File childFile:files) {
                delFile(childFile);
            }
            file.delete();
        }
    }
    //项目用清理过期文件
    public static void delTrash(File file, Pattern p, Calendar c){
        if(file.isFile()){
            return;
        }else{
            Matcher m = p.matcher(file.getName());
            if(m.matches()){
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(file.lastModified());
                SimpleDateFormat sp = new SimpleDateFormat("yyyyMMddHHmmss");
                String timeStr = sp.format(calendar.getTime());
                System.out.println( file.getName()+":"+timeStr);
                if (calendar.compareTo(c)<0){
                    delFile(file);
                }
            }else{
                File[] files = file.listFiles();
                for (File childFile:files) {
                    delTrash(childFile, p, c);
                }
            }

        }

    }

    public static void main(String[] args) {
//        String classPath = FileUtil.class.getResource("/").getPath()+"/";
//        File file1 = new File(classPath + "file1.txt");
//        File file2 = new File(classPath + "file2.txt");
//        System.out.println(file1.getAbsolutePath());
//        System.out.println(file2.getAbsolutePath());
//        copyFile(file1, file2);
        Pattern p = Pattern.compile("^\\d{8,}$");
        Map<String,File> dirMap = new HashMap<String, File>();
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -(HOLD_DAY-1));
        c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
        SimpleDateFormat sp = new SimpleDateFormat("yyyyMMddHHmmss");
        String timeStr = sp.format(c.getTime());
        System.out.println("c:"+timeStr);
        delTrash(new File("C:\\work\\workspace\\TJ.reportManage\\war\\ftp\\trash"), p, c);

//        Set<String> dirNameSet = dirMap.keySet();
//        dirNameSet.forEach((dirName)->{
//            Calendar c = Calendar.getInstance();
//            c.add(Calendar.DAY_OF_MONTH, -2);
//            SimpleDateFormat sp = new SimpleDateFormat("yyyyMMddHHmmss");
//
//            File file = dirMap.get(dirName);
//            Date date = new Date(file.lastModified());
//
//
//            String timeStr = sp.format(new Date(file.lastModified()));
//            System.out.println(dirName+":"+timeStr);
//        });
    }
}
