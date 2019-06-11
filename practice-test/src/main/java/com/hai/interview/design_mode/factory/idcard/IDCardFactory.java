package com.hai.interview.design_mode.factory.idcard;

import java.util.ArrayList;
import java.util.List;

import com.hai.interview.design_mode.factory.Factory;
import com.hai.interview.design_mode.factory.Product;
/**
 * IDCardFactory生成实例，具体处理类
 * @author Administrator
 *
 */
public class IDCardFactory extends Factory {
	private List owners = new ArrayList();
	@Override
	//生成IDCard实例
	protected Product createProduct(String owner) {
		return new IDCard(owner);
	}

	@Override
	protected void registerProduct(Product p) {
		owners.add(((IDCard)p).getOwner());
	}
	public List getOwners() {
		return owners;
	}

}
