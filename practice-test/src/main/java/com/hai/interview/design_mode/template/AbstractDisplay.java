package com.hai.interview.design_mode.template;
/**
 * 模板模式，将具体实现，交给子类。
 * @author Administrator
 *
 */
public abstract class AbstractDisplay {
	//open()/print()/close()加给子类实现。
	public abstract void open();
	public abstract void print();
	public abstract void close();
	//display()不可被重写、覆盖（final修饰）
	public final void display() {
		open();
		for(int i= 0;i<5;i++) {
			print();
		}
		close();
	}
}
