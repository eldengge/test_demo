package com.nsm.seleniumtest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class DownloadWebImgUtil {
    /**
     *
     * @param imgUrl 图片url
     * @param filePath 本地保存路径
     */
    public static void downloadWebImg(String imgUrl , String filePath) throws IOException {


        URL url = new URL(imgUrl);
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5000);
        int responseCode = conn.getResponseCode();
        //如果地址不存在返回
        if(responseCode == HttpURLConnection.HTTP_NOT_FOUND){
           throw  new  IOException("The image address does not exist");
        }
        //如果状态码为200进行图片往本地写入操作
        if(responseCode == HttpURLConnection.HTTP_OK){
            InputStream inputStream = null;
            byte[] buff = new byte[8 * 1024];
            BufferedInputStream in  = null;
            BufferedOutputStream out = null;
            FileOutputStream fileOutputStream = null;
            File file = new File(filePath);
            if (file.exists()){
                return;
            }
            try {
                //获取输入流
                inputStream = conn.getInputStream();
                fileOutputStream = new FileOutputStream(file);
                in = new BufferedInputStream(inputStream);
                out = new BufferedOutputStream(fileOutputStream);
                int len = 0;
                while((len = in.read(buff, 0, buff.length))!=-1){
                    out.write(buff, 0, len);
                }
            }finally{
                if(null!=in){
                    in.close();
                }
                if(null!=out){
                    out.close();
                }
            }
        }else{
            throw new IOException("connection fail , status code："+responseCode);
        }

    }

    public static void makeDir(String filePath) throws Exception {
        if (filePath == null||filePath.equals("")){
            throw new Exception("filePath is null");
        }
        File file = new File(filePath);
        if (!file.exists()){
            file.mkdirs();
        }
    }

    public static void main(String[] args) {

        String imgUrl = "http://www.jma.go.jp/jp/gms/imgs/0/infrared/1/201906271210-00.png";
        String filePath = "D:\\test\\test.png";
        try {
            downloadWebImg(imgUrl, filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
