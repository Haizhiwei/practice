package com.hai.interview.design_mode.singleton;

public class SingletonLazy {
	private static SingletonLazy singletonLazy = null;
	private SingletonLazy() {
		System.out.println("生成了一个实例");
		slowdown();
	}
	//线程不安全
//	public static SingletonLazy getInstance() {
//		if(singletonLazy == null) {
//			singletonLazy = new SingletonLazy();
//		}
//		return singletonLazy;
//	}
	//线程安全，但锁的是方法，粗粒度锁
	public static synchronized SingletonLazy getInstance() {
		if(singletonLazy == null) {
			singletonLazy = new SingletonLazy();
		}
		return singletonLazy;
	}
	//先调用的停止一秒，多线程测试
	private void slowdown() {
		try {
			//sleep使当前线程让出cpu，但如果在锁内，它并不会让出锁。
			Thread.sleep(1000);
		}catch (InterruptedException e) {
			
		}
	}

}
