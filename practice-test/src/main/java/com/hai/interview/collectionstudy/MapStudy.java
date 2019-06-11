package com.hai.interview.collectionstudy;
/*
 * 
 */

import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.Date;
import java.util.SortedMap;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
/**
 * Map的几种遍历方式
 * @author Administrator
 *
 */
public class MapStudy {
	public static void main(String[] args) {
		 Map<String, Integer> map  = new HashMap<String, Integer>();
		 //Map要求key唯一，不能重复，如果重复后存入的key覆盖前存入的key
		 map.put("语文", 120);
		 map.put("语文", 130); //示例，不要这样做
		 map.put("数学", 140);
		 map.put("英语", 127);
		 map.put("化学", 89);
		 map.put("生物", 92);
		 
		 //第一种遍历key,然后获取value
		 for(String key : map.keySet()) {
			System.out.println("key:"+key+",velue:"+map.get(key));
		}
		 System.out.println("----------------");
		 //第二种遍历key,value
		 for(Entry<String, Integer> entry : map.entrySet()) {
			 System.out.println("key:"+entry.getKey()+",velue:"+entry.getKey());
		 }
		 System.out.println("----------------");
		 //第三种遍历value,此遍历不能获取到key
		 for(Integer value : map.values()) {
			 System.out.println("value:"+value);
		 }
		 System.out.println("-----------------");
		 //第四种遍历通过Map.entrySet使用iterator遍历key和value
		 Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
		 while(iterator.hasNext()) {
			 Map.Entry<String, Integer> entry = iterator.next();
			 System.out.println("key:"+entry.getKey()+",velue:"+entry.getKey());
		 }
		 
		
	}
	
	
	
	
	
	
	
	
//	public static void test() {
//		int[][] set = new int[1000000][1000000] ;
//		//这两个for那个执行的快，为什么
//		for(int i = 0;i < set.length;i++) {
//			for(int k = 0;k < set.length;k++) {
//				System.out.println(set[i][k]);
//			}
//			
//		}
//		for(int i = 0;i < set.length;i++) {
//			for(int k = 0;k < set.length;k++) {
//				System.out.println(set[k][i]);
//			}
//		}
//	}
//	
}
