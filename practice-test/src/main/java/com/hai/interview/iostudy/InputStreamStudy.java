package com.hai.interview.iostudy;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class InputStreamStudy {

	public static void main(String[] args) {
		// 申明file对象
		File file = new File("C:/java/workspoce/practice/ref-doc/file/interview/image/io/简单介绍.txt");
		InputStream inputStream = null;
		
		try {
			inputStream = new FileInputStream(file);
//			byte[]	b = new byte[(int) file.length()];
//			inputStream.read(b);
//			inputStream.close();
//			System.out.println(new String(b));
			BufferedReader b = new BufferedReader(new InputStreamReader(inputStream, "GBK"));
			String str = null;
			while ((str=b.readLine())!=null) {
				System.out.println(str);
			}
		} catch (IOException e) {
			System.out.println("error:" + e);
		}

	}
}
