package com.hai.interview.threadstudy;

public class Test {
	public static void main(String[] args) {
		// 初始化存钱罐1000
		MoneyBox money = new MoneyBox(1000);
		System.out.println("----初始化余额" + money.getBalance() + "----");

		ZhaoMi zhaomi = new ZhaoMi("赵敏", money);
		Zhwuji zhwuji = new Zhwuji("张无忌", money);

		Thread t1 = new Thread(zhaomi, "赵敏访问");
		Thread t2 = new Thread(zhwuji, "张无忌访问");

		// 启动线程
		t1.start();
		t2.start();

		// 主线程睡眠4秒，以使其他两个线程先执行完
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// 打印存钱罐中最终的余额
		System.out.println(money.getBalance());

	}

}
