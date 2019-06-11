package com.hai.interview.design_mode.factory;

/**
 * Factory用到模板模式（将具体的处理交给子类，父类只定义生产流程（规则））
 * @author Administrator
 *
 */
public abstract class Factory {
	//定义工厂（先生成产品，再注册产品）
	public final Product create(String owner) {
		Product p = createProduct(owner);
		registerProduct(p);
		return p;
	}
	//createProduct()--"用来生产产品",其具体实现交给其子类
	protected abstract  Product createProduct(String owner);
	//registerProduct()--"用来注册产品"，其具体实现交给子类
	protected abstract void registerProduct(Product p);

}
