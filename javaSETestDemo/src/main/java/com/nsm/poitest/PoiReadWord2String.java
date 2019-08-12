package com.nsm.poitest;

import java.io.IOException;

import org.apache.poi.POIXMLDocument;
import org.apache.poi.POIXMLTextExtractor;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;

/** 
 * @author nasm 
 * @date 创建时间：2019年4月18日 上午10:33:30 
 * @version 1.0  
 */
public class PoiReadWord2String {
	public String readWord(String path) {
		String str = "";
		OPCPackage opcPackage = null;
		POIXMLTextExtractor extractor = null;
		try {
//			InputStream is = new FileInputStream(new File(path));
//			WordExtractor ex = new WordExtractor(is);
//			buffer = ex.getText();
//			ex.close();
			opcPackage = POIXMLDocument.openPackage(path);
			extractor = new XWPFWordExtractor(opcPackage);
			str = extractor.getText();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			if(extractor != null){
				try {
					extractor.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return str;
	}
	public static void main(String[] args) {
		PoiReadWord2String po = new PoiReadWord2String();
		String readWord = po.readWord("D:\\xdocTest\\PWAJJTJ23.171");
		String[] split = readWord.split("\\s{3,}");
		System.out.println(split.length);
		for (String string : split) {
			System.out.println(string.trim());
		}
	}
}
