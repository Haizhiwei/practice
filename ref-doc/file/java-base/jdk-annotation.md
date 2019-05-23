# jdk原生注解使用和理解()

##一.介绍(java.lang.annotation)

注解是在jdk1.5以后引入的新特性！有利于代码的解藕、整洁，提升开发的效率！通俗来讲，注解就是标签，标签的内容可以变动！
注解也是java中的一种数据类型！注解的创建和接口的创建很类似！使用@Interface修饰！既然说到注解了，就要说到jdk本身自带的几种原生注解！这是原生注解是用于修饰自定义注解！如果自定义注解没有这些原生注解修饰，自定义注解不能正常工作使用！

##二.目前，jdk自带的原生注解有6个：

```txt
@Retention , @Target , @Inherited , @Documented , @Repeatable , @Native
其中，@Native、@Repeabable是在jdk1.8之后推出的元注解！
@Retention: 表示注解保留周期
@Target: 表示注解可以使用在什么地方
@Documented: 注解写入文档
@Inherited : 子类继承父类的注解（子类没有任何注解修饰）
@Repeatable : 表示注解的属性可以重复！@Repeatable通俗来讲，就是注解容器！

补充：
说完原生注解，说说功能性注解：
jdk自定了一些功能性注解，帮助我们更加方便的开发程序！
@Deprecated:用于标志过时的类、方法和成员变量
@Override:用于修饰重写的方法
@SuppressWarnings:用户忽略@Deprecated标志过的警告
@SafeVarargs：参数安全类型注解，用于提示用户参数安全（jdk1.7）
@FunctionalInterface：函数式接口注解，用于定义函数式接口(jdk1.8)
```

###1.@Target:注解的作用目标

​	**@Target**说明了Annotation所修饰的对象范围：Annotation可被用于 packages、types（类、接口、枚举、Annotation类型）、类型成员（方法、构造方法、成员变量、枚举值）、方法参数和本地变量（如循环变量、catch参数）。在Annotation类型的声明中使用了target可更加明晰其修饰的目标。

　   **作用：用于描述注解的使用范围（即：被描述的注解可以用在什么地方）**

　   **取值(ElementType)有：**

　　　　1.CONSTRUCTOR:用于描述构造器
　　　　2.FIELD:用于描述域
　　　　3.LOCAL_VARIABLE:用于描述局部变量
　　　　4.METHOD:用于描述方法
　　　　5.PACKAGE:用于描述包
　　　　6.PARAMETER:用于描述参数
　　　　7.TYPE:用于描述类、接口(包括注解类型) 或enum声明

**e.g:**

```java
/* @Target(ElementType.TYPE)   //接口、类、枚举、注解
　 @Target(ElementType.FIELD) //字段、枚举的常量
　 @Target(ElementType.METHOD) //方法
　 @Target(ElementType.PARAMETER) //方法参数
　 @Target(ElementType.CONSTRUCTOR)  //构造函数
　 @Target(ElementType.LOCAL_VARIABLE)//局部变量
　 @Target(ElementType.ANNOTATION_TYPE)//注解
　 @Target(ElementType.PACKAGE) ///包   
*/
//表名@ExcelField可以用于方法(METHOD)、字段、枚举的常量(FIELD)、接口、类、枚举、注解上(TYPE)
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
public @interface ExcelField {
	String value() default "";
}
```

### 2. @Retention：注解的保留位置###

@Retention(RetentionPolicy.SOURCE)   //注解仅存在于源码中，在class字节码文件中不包含
@Retention(RetentionPolicy.CLASS)     // 默认的保留策略，注解会在class字节码文件中存在，但运行时无法获得，

@Retention(RetentionPolicy.RUNTIME)  // 注解会在class字节码文件中存在，在运行时可以通过反射获取到

**e.g**

```java
@Target({ElementType.METHOD, ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME) // 注解会在class字节码文件中存在，在运行时可以通过反射获取到
public @interface ExcelField {
	String value() default "";
}
```

### 3.@Document：说明该注解将被包含在javadoc中

### 4.@Inherited：说明子类可以继承父类中的该注解

## ....有待补充....##







