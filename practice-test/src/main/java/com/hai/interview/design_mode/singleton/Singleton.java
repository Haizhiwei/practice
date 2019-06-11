package com.hai.interview.design_mode.singleton;

public class Singleton {
	private static Singleton singleton = new Singleton();
	private Singleton() {
		System.out.println("只生成一个实例");
	}
	public static Singleton getInstance() {
		return singleton;
	}
}
