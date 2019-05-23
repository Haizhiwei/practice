package com.hai.interview.threadstudy;

public class MoneyBox {
	//存钱罐的余额
	private double balance;
	
	public MoneyBox(double balance) {
		this.balance = balance;
	}
	public double getBalance() {
		return balance;
	}
	
	//存钱
	//synchronized（锁方法-粗粒度）
	/**
	 * 
	 * @param inMoney     存入的钱（每次1000）
	 * @param name        
	 * @param frequency   次数
	 */
	public void add(double inMoney, String threadName, int number) {
		
		try {
            Thread.sleep(50);
        } catch (Exception e) {
            e.printStackTrace();
        }
		//锁代码块（细粒度）
		synchronized (this) {
			balance += inMoney;
			System.out.println("----" + threadName + "第" + number + "次存了" + inMoney + "元，现在存钱罐中的余额=" + balance);
		}
		
	}
	//取钱
	public void get(double outMoney, String threadName, int number) {
		//拿到旧的金额后，让当前线程睡眠会以使别的线程操作它
		//先存后取（sleep让出cpu给其它线程，（如果在synchronized）但不会释放锁）
		try {
            Thread.sleep(40);
        } catch (Exception e) {
            e.printStackTrace();
        }
		
		balance -= outMoney;
		 System.out.println("----" + threadName + "第" + number + "次取了" + outMoney + "元，现在存钱罐中的余额=" + balance);
	 }
	
	@Override
	public String toString() {
		return "----我是存钱罐，我现在的余额=" + balance + "----";
	}


}
