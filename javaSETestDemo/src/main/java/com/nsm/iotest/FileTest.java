package com.nsm.iotest;

import java.io.File;
import java.io.IOException;

public class FileTest {
	public static void main(String[] args) throws IOException {
		File file = new File("C:/work/testest");
		file.mkdir();
	}
}
