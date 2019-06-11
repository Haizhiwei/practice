package com.hai.interview.design_mode.factory;
/**
 * 工厂模式(factory Method)，将实例的生成交给子类。
 * 父类决定实例的生产方式，但并不决定所要生成的具体的类，具体的处理全部交给子类负责。这样可以将生产实例的框架（framework）
 * 和实际负责生成实例的类解耦。
 * @author Administrator
 * Product为"产品"类。该类的实现交给其子类。
 *
 */
public abstract class Product {
	public abstract void use();
}
