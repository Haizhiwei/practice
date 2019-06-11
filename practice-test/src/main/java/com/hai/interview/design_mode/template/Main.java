package com.hai.interview.design_mode.template;
/**
 * 模板类为AbstractDisplay
 * 实现类为CharDisplay和StringDisplay
 * @author Administrator
 *
 */
public class Main {
	public static void main(String[] args) {
		//1号模板
		AbstractDisplay d1 = new CharDisplay('H');
		//2号模板
		AbstractDisplay d2 = new StringDisplay("Hello World!");
		AbstractDisplay d3 = new StringDisplay("你好 ，世界");
		//调用父类display()方法，(父类定义规则（流程）)
		d1.display();
		d2.display();
		d3.display();
	}

}
