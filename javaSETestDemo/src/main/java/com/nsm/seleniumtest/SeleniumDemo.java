package com.nsm.seleniumtest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SeleniumDemo {

    private List<String> String2List(String str) throws Exception {
        if (str == null||"".equals(str)){
            return null;
        }
        List<String> list = new ArrayList<>();
        String[] split = str.split(",");
        Collections.addAll(list, split);
        return list;
    }

    private String getCurrentTimeStr(String pattern){
        Date date = new Date();
        SimpleDateFormat sp = new SimpleDateFormat(pattern);
        String timeStr = sp.format(date);
        return timeStr;
    }

    private String List2String(List<String> strList){
        if (strList == null){
            return null;
        }
        String returnStr = "";
        for (String str : strList) {
            returnStr += str + ",";
        }
        return  returnStr;
    }

    private Pattern getImgNameRegex(List<String> suffixList){
        if (suffixList == null||suffixList.size() == 0){
            return null;
        }
        StringBuilder sb = new StringBuilder("(.*/)(.*");
        for(int i = 0;i<suffixList.size();++i){
            sb.append(suffixList.get(i));
            if (i<suffixList.size()-1){
                sb.append("|.*");
            }
        }
        sb.append(")(.*)");
        Pattern p = Pattern.compile(sb.toString());
        return p;
    }

    public void downloadImg(GetPictureConfig config) throws Exception {

        String dirPath = config.getFilePath()+"/"+getCurrentTimeStr("yyyyMMdd");
        DownloadWebImgUtil.makeDir(dirPath);

        List<String> imgUrls = getImgUrls(config);
        //如果配置了图片名匹配的正则就匹配 如果没配置就获取全部
        Pattern p =  (config.getRegex() == null || "".equals(config.getRegex()))?Pattern.compile(".*"):Pattern.compile(config.getRegex());
        for (String url: imgUrls) {
            String imgName = getImgName(url, config.getImgSuffix());
            if (!"".equals(imgName)){
                System.out.println(imgName);
                System.out.println(url);
                Matcher m = p.matcher(imgName);
                if (m.matches()){
                    try {
                        DownloadWebImgUtil.downloadWebImg(url, dirPath + "/"+imgName);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }

    private String getImgName(String imgUrl,String imgSuffix) throws Exception {
        if (imgSuffix ==  null||"".equals(imgSuffix)){
            throw new Exception("img后缀未配置");
        }
        List<String> suffixList = String2List(imgSuffix);
        Pattern p = getImgNameRegex(suffixList);
        Matcher m = p.matcher(imgUrl);
        if (m.find()){
            return m.group(2);
        }
        return "";
    }

    private List<String> getImgUrls(GetPictureConfig config) throws Exception {

        WebDriver driver = null;
        List<String> imgUrlList = null;
        try {
            String driverPath = SeleniumDemo.class.getResource("/").getPath()+"/"+"geckodriver.exe";
            File pathToFirefoxBinary =new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
            FirefoxBinary firefoxBinary = new FirefoxBinary(pathToFirefoxBinary);
            firefoxBinary.addCommandLineOptions("--headless"); //此句注释掉会显示浏览器
            System.setProperty("webdriver.gecko.driver", driverPath);
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            firefoxOptions.setBinary(firefoxBinary);
            driver = new FirefoxDriver(firefoxOptions);

            driver.get(config.getUrl());
            //休眠一会儿等待页面加载完成
            Thread.sleep(config.getSleepTime());

            //获取页面全部img标签
            List<WebElement> imgTagList = driver.findElements(By.tagName("img"));

            //根据配置获取img标签属性值也就是图片url

            String imgTagAttribute = config.getImgTagAttribute();
            String imgSuffix = config.getImgSuffix();
            if(imgTagAttribute == null||"".equals(imgTagAttribute)){
                throw new Exception("img标签属性未配置");
            }
            if (imgSuffix ==  null||"".equals(imgSuffix)){
                throw new Exception("img后缀未配置");
            }
            imgUrlList = new ArrayList<>();
            List<String> attributeList = String2List(imgTagAttribute);
            List<String> suffixList = String2List(imgSuffix);
            Pattern p = getImgNameRegex(suffixList);
            Matcher m = null;
            for(WebElement ele:imgTagList){
                for (String attribute:attributeList) {
                    String url = ele.getAttribute(attribute);
                    if (url != null && !"".equals(url)){
                        //使用配置好的img后缀正则匹配图片url 如果url中不包含配置后缀 直接丢弃
                        m = p.matcher(url);
                        if (m.matches()){
                            imgUrlList.add(url);
                        }
                    }

                }

            }

        }finally {
            driver.quit();
        }
        return imgUrlList;
    }



    ////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        SeleniumDemo s = new SeleniumDemo();
        GetPictureConfig config = new GetPictureConfig();
        config.setFilePath("D:/test");
        //config.setSleepTime(10000);
        //config.setUrl("http://www.jma.go.jp/jp/gms/");
        config.setUrl("http://www.nmc.cn/publish/observations/china/dm/weatherchart-h925.htm");
        config.setImgSuffix("jpg,png,gif");
        config.setImgTagAttribute("src,data-original");
        //config.setRegex(".*jpg");

        try {
            s.downloadImg(config);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
    ///////////////////////////////////////////////////////////////////////////////////

    public static  void  seleniumTest(){
//        WebDriver driver = null;
//
//        try {
//            String driverPath = SeleniumDemo.class.getResource("/").getPath()+"/"+"geckodriver.exe";
//            File pathToFirefoxBinary =new File("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
//            FirefoxBinary firefoxBinary = new FirefoxBinary(pathToFirefoxBinary);
//            //firefoxBinary.addCommandLineOptions("--headless"); //此句注释掉会显示浏览器
//            System.setProperty("webdriver.gecko.driver", driverPath);
//            FirefoxOptions firefoxOptions = new FirefoxOptions();
//            firefoxOptions.setBinary(firefoxBinary);
//            driver = new FirefoxDriver(firefoxOptions);
//            //driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);//等待错误之前设置等待页面加载完成的时间量
//            //driver.manage().timeouts().setScriptTimeout(30,TimeUnit.SECONDS);//等待异步脚本在执行错误之前执行的时间量
//
//            driver.get("https://www.baidu.com");
//
//            String title = driver.getTitle();
//            WebElement text = driver.findElement(By.id("kw"));
//            WebElement button = driver.findElement(By.id("su"));
//            text.sendKeys("痞老板");
//            button.click();
//            Thread.sleep(Long.parseLong("2000"));
//            WebElement daohang = driver.findElement(By.id("s_tab"));
//            List<WebElement> buttons = daohang.findElements(By.tagName("a"));
//            for (WebElement tag:buttons) {
//                String tagText = tag.getText();
//                if ("图片".equals(tagText)){
//                    tag.click();
//                    break;
//                }
//            }
//            Thread.sleep(Long.parseLong("2000"));
//
//        }catch (Exception e){
//            e.printStackTrace();
//
//        }finally {
//            driver.quit();
//        }
    }
}
