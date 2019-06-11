package com.hai.interview.collectionstudy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * java数据结构：https://blog.csdn.net/peiqin/article/details/660408
 * @author Administrator
 *
 */
public class CollectionStudy {
	private Collection<String> collection;
	private List<String> list;
	private Set<String> set;
	private Queue<Integer> queue;
	
	
	
	public static void main(String[] args) {
		int num = 50;
		num = num++ * 2;
		System.out.println(num);
	}

}
