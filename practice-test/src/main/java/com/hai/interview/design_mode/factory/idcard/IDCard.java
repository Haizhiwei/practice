package com.hai.interview.design_mode.factory.idcard;

import com.hai.interview.design_mode.factory.Product;
//IDCard负责生产(Product)实例
public class IDCard extends Product {
	private String owner;
	//使用默认修饰，让外包类不能生产IDCard的实例，必须通过IDFactory生成其实例
	IDCard(String owner){
		System.out.println("制作"+owner+"的ID卡");
		this.owner = owner;
	}
	@Override
	public void use() {
		System.out.println("使用"+ owner + "的ID卡");
		
	}
	public String getOwner() {
		return owner;
	}
	

}
