package com.hai.interview.design_mode.singleton;

public class SingletonMain {
	public static void main(String[] args) {
		//会编译出错，构造函数Singleton()不可见（私有）
		//Singleton singleton = new Singleton();
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		if(s1 == s2) {
			System.out.println("s1和s2是相同的实例");
		}else {
			System.out.println("s1和s2是不同的实例");
		}
		
	}

}
