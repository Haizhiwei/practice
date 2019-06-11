package com.hai.interview.design_mode.factory.main;

import com.hai.interview.design_mode.factory.Factory;
import com.hai.interview.design_mode.factory.Product;
import com.hai.interview.design_mode.factory.idcard.IDCardFactory;

public class Main {
	public static void main(String[] args) {
		Factory factory = new IDCardFactory();
		Product card1 = factory.create("小明");
		Product card2 = factory.create("小红");
		Product card3 = factory.create("小张");
		card1.use();
		card2.use();
		card3.use();
	}

}
