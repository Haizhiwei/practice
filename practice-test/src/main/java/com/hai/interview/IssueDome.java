package com.hai.interview;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.WeakHashMap;


public class IssueDome {
	
	public static void main(String[] args) throws FileNotFoundException {
		//InputStreamReader in = new InputStreamReader(new FileInputStream("C:/java/workspoce/practice/ref-doc/file/txt/spring.txt"));
		//String str=null;
		int[] arr =new int[] {1,2,3,4,5};
		System.out.println(arr.length);
		String str = "123456";
		System.out.println(str.length());
		
		int c;
		c=2;
		System.out.println(c);
		System.out.println(c++);
		System.out.println(c);
		
		
		
		
		
		Thread t = new Thread() {
			public void run() {
				pong();
			}
		};

		t.run();
		System.out.print("ping");

	}

	static void pong() {
		System.out.print("pong");
	}

}
