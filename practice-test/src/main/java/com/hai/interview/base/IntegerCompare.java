package com.hai.interview.base;
/**
 * 简单解释一下：
 * 使用==的情况：
 * 如果比较Integer变量，默认比较的是地址值。
 * Java的Integer维护了从-128~127的缓存池
 * 如果比较的某一边有操作表达式(例如a+b)，那么比较的是具体数值
 * 使用equals()的情况：
 * 无论是Integer还是Long中的equals()默认比较的是数值。
 * Long的equals()方法，JDK的默认实现：会判断是否是Long类型
 * 注意自动拆箱，自动装箱问题。
 *
 */
public class IntegerCompare {
	public static void main(String[] args) {
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 321;
		Integer f = 321;
		Long g = 3L;
		Long h = 2L;
		System.out.println(c == d);          //true    Java的Integer维护了从-128~127的缓存池   ==默认比较的是地址
		System.out.println(e == f);          //false   超出緩存池
		System.out.println(c == (a + b));    //true    比较的是值
		System.out.println(c.equals(a + b)); //true    equals比较的是数值
		System.out.println(g == (a + b));   //true     存在a+b数值表达式，比较的是数值
		System.out.println(g.equals(a + b));//false    比较的是数值   Long的equals()先判断传递进来的是不是Long类型，而a+b自动装箱的是Integer类型
		System.out.println(g.equals(a + h));//ture     自动装箱，小转大，为Long
	}

}
