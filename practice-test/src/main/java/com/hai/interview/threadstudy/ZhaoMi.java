package com.hai.interview.threadstudy;
/**
 * 线程实现的两种方法：
 *  implements Runnable 接口
 *  extends  Thread 类
 * 
 * @author Administrator
 *
 */
public class ZhaoMi implements Runnable {
	private String name;
	private MoneyBox moneyBox;
	
	
	public ZhaoMi(String name, MoneyBox moneyBox) {
		super();
		this.name = name;
		this.moneyBox = moneyBox;
	}


	@Override
	public void run() {
		add();
		
	}
	
	public void add() {
		for (int frequency = 1; frequency <= 10; frequency++) {
			System.out.println("----我是" + name + ",这是我第" + frequency + "次存钱----");
			moneyBox.add(100, name, frequency);
		}
	}



}
