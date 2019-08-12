package com.nsm.poitest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.poi.xwpf.converter.core.BasicURIResolver;
import org.apache.poi.xwpf.converter.core.FileImageExtractor;
import org.apache.poi.xwpf.converter.xhtml.XHTMLConverter;
import org.apache.poi.xwpf.converter.xhtml.XHTMLOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;

/** 
 * @author nasm 
 * @date 创建时间：2019年3月14日 下午2:23:27 
 * @version 1.0  
 */
public class PoiTest {
	public static void main(String[] args) {
		 	String filepath = "D:\\xdocTest\\";
	        String sourceFileName =filepath+"PWAJJTJ19.docx"; 
	        String targetFileName = filepath+"PWAJJTJ19.html"; 
	        String imagePathStr = filepath+"/image/";  
	        OutputStreamWriter outputStreamWriter = null; 
	        try { 
	          XWPFDocument document = new XWPFDocument(new FileInputStream(sourceFileName)); 
	          XHTMLOptions options = XHTMLOptions.create(); 
	          // 存放图片的文件夹 
	          options.setExtractor(new FileImageExtractor(new File(imagePathStr))); 
	          // html中图片的路径 
	          options.URIResolver(new BasicURIResolver("image")); 
	          outputStreamWriter = new OutputStreamWriter(new FileOutputStream(targetFileName), "utf-8"); 
	          XHTMLConverter xhtmlConverter = (XHTMLConverter) XHTMLConverter.getInstance(); 
	          xhtmlConverter.convert(document, outputStreamWriter, options); 
	        } catch(Exception e){
	        	e.printStackTrace();
	        }finally { 
		        if (outputStreamWriter != null) { 
			         try {
						outputStreamWriter.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} 
			    } 
			}
	}
}
