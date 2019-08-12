package com.nsm.seleniumtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GetPictureConfig {

    private String url;

    private String regex;

    private String filePath;

    private String cron;

    //要抓取图片的img标签的属性名 例如src
    private String imgTagAttribute;

    //图片后缀名 需要用来截取图片url中的图片文件名
    private String imgSuffix;

    //线程休眠时间 有些网页的图片是异步加载出来的 需要线程休眠一段时间等到图片加载完成后再进行抓取
    private long sleepTime;

    private List<String> imgTagAttributeList;

    private List<String> imgSuffixList;

    public GetPictureConfig() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getCron() {
        return cron;
    }

    public void setCron(String cron) {
        this.cron = cron;
    }

    public String getImgTagAttribute() {
        return imgTagAttribute;
    }

    public void setImgTagAttribute(String imgTagAttribute) {
        this.imgTagAttribute = imgTagAttribute;
    }

    public String getImgSuffix() {
        return imgSuffix;
    }

    public void setImgSuffix(String imgSuffix) {
        this.imgSuffix = imgSuffix;
    }

    public long getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public List<String> getImgTagAttributeList() {
        return imgTagAttributeList;
    }

    public void setImgTagAttributeList(List<String> imgTagAttributeList) {
        this.imgTagAttributeList = imgTagAttributeList;
    }

    public List<String> getImgSuffixList() {
        return imgSuffixList;
    }

    public void setImgSuffixList(List<String> imgSuffixList) {
        this.imgSuffixList = imgSuffixList;
    }

}
