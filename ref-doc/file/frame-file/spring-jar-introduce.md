#Spring的jar包介绍#

spring.jar是包含有完整发布的单个jar 包，spring.jar中包含除了spring-mock.jar里所包含的内容外其它所有jar包的内容，因为只有在开发环境下才会用到 spring-mock.jar来进行辅助测试，正式应用系统中是用不得这些类的。

除了spring.jar文件，Spring还包括有其它13个独立的jar包，各自包含着对应的Spring组件，用户可以根据自己的需要来选择组合自己的jar包，而不必引入整个spring.jar的所有类文件。

## 一.各个jar包的介绍（基于spring2.x,

## 3.x版本以后包有大的改动）

### (1) spring-core.jar

​     这个jar文件包含Spring框架基本的核心工具类，Spring其它组件要都要使用到这个包里的类，是其它组件的基本核心，当然你也可以在自己的应用系统中使用这些工具类。

### (2) spring-beans.jar

​    这个jar文件是所有应用都要用到的，它包含访问配置文件、创建和管理bean以及进行Inversion of Control / Dependency Injection（IoC/DI）操作相关的所有类。如果应用只需基本的IoC/DI支持，引入spring-core.jar及spring-beans.jar文件就可以了。

###(3) spring-aop.jar

​     这个jar文件包含在应用中使用Spring的AOP特性时所需的类。使用基于AOP的Spring特性，如声明型事务管理（Declarative Transaction Management），也要在应用里包含这个jar包。

###(4) spring-context.jar

　　这个jar文件为Spring核心提供了大量扩展。可以找到使用Spring ApplicationContext特性时所需的全部类，JDNI所需的全部类，UI方面的用来与模板（Templating）引擎如Velocity、FreeMarker、JasperReports集成的类，以及校验Validation方面的相关类。

###(5) spring-dao.jar

　　这个jar文件包含Spring DAO、Spring Transaction进行数据访问的所有类。为了使用声明型事务支持，还需在自己的应用里包含spring-aop.jar。

###(6) spring-hibernate.jar

　　这个jar文件包含Spring对Hibernate 2及Hibernate 3进行封装的所有类。

###(7) spring-jdbc.jar

　　这个jar文件包含对Spring对JDBC数据访问进行封装的所有类。

###(8) spring-orm.jar

　　这个jar文件包含Spring对DAO特性集进行了扩展，使其支持 iBATIS、JDO、OJB、TopLink，因为Hibernate已经独立成包了，现在不包含在这个包里了。这个jar文件里大部分的类都要依赖spring-dao.jar里的类，用这个包时你需要同时包含spring-dao.jar包。

###(9) spring-remoting.jar

　　这个jar文件包含支持EJB、JMS、远程调用Remoting（RMI、Hessian、Burlap、Http Invoker、JAX-RPC）方面的类。

###(10) spring-support.jar

　　这个jar文件包含支持缓存Cache（ehcache）、JCA、JMX、邮件服务（Java Mail、COS Mail）、任务计划Scheduling（Timer、Quartz）方面的类。

###(11) spring-web.jar

　　这个jar文件包含Web应用开发时，用到Spring框架时所需的核心类，包括自动载入WebApplicationContext特性的类、Struts与JSF集成类、文件上传的支持类、Filter类和大量工具辅助类。

###(12) spring-webmvc.jar

　　这个jar文件包含Spring MVC框架相关的所有类。包含国际化、标签、Theme、视图展现的FreeMarker、JasperReports、Tiles、Velocity、XSLT相关类。当然，如果你的应用使用了独立的MVC框架，则无需这个JAR文件里的任何类。

###(13) spring-mock.jar

​    这个jar文件包含Spring一整套mock类来辅助应用的测试。Spring测试套件使用了其中大量mock类，这样测试就更加简单。模拟HttpServletRequest和HttpServletResponse类在Web应用单元测试是很方便的。

## 二.补充##

###1.spring4.x各个jar的介绍

```txt
spring Aspects：Spring提供的对AspectJ框架的整合

Spring Beans：Spring IOC的基础实现，包含访问配置文件、创建和管理bean等。

Spring Context：在基础IOC功能上提供扩展服务，此外还提供许多企业级服务的支持，有邮件服务、任务调度、JNDI定位，EJB集成、远程访问、缓存以及多种视图层框架的支持。

Spring Context Support：Spring context的扩展支持，用于MVC方面。

Spring Core：Spring的核心工具包

Spring expression：Spring表达式语言

Spring Framework Bom：

Spring Instrument：Spring对服务器的代理接口

Spring Instrument Tomcat：Spring对tomcat连接池的集成

Spring JDBC：对JDBC 的简单封装

Spring JMS：为简化jms api的使用而做的简单封装

Spring Messaging：

Spring orm：整合第三方的orm实现，如hibernate，ibatis，jdo以及spring 的jpa实现

Spring oxm：Spring对于object/xml映射的支持，可以让JAVA与XML之间来回切换

Spring test：对JUNIT等测试框架的简单封装

Spring tx：为JDBC、Hibernate、JDO、JPA等提供的一致的声明式和编程式事务管理。

Spring web：包含Web应用开发时，用到Spring框架时所需的核心类，包括自动载入WebApplicationContext特性的类、Struts与JSF集成类、文件上传的支持类、Filter类和大量工具辅助类。

Spring webmvc：包含SpringMVC框架相关的所有类。包含国际化、标签、Theme、视图展现的FreeMarker、JasperReports、Tiles、Velocity、XSLT相关类。当然，如果你的应用使用了独立的MVC框架，则无需这个JAR文件里的任何类。

Spring webmvc portlet：Spring MVC的增强

```



###2.spring3.x:

```tx
org.springframework.aop- 3.0.0.RELEASE--------------------Spring的面向切面编程,提供AOP(面向切面编程)实现

org.springframework.asm- 3.0.0.RELEASE--------------------Spring独立的asm程序,相遇Spring2.5.6的时候需要asmJar 包.3.0开始提供他自己独立的asmJar

org.springframework.aspects- 3.0.0.RELEASE----------------Spring提供对AspectJ框架的整合\

org.springframework.beans- 3.0.0.RELEASE------------------SpringIoC(依赖注入)的基础实现

org.springframework.context.support- 3.0.0.RELEASE--------Spring-context的扩展支持,用于MVC方面

org.springframework.context- 3.0.0.RELEASE----------------Spring提供在基础IoC功能上的扩展服务，此外还提供许多企业级服务的支持，如邮件服务、任务调度、JNDI定位、EJB集成、远程访问、缓存以及各种视图层框架的封装等

org.springframework.core- 3.0.0.RELEASE-------------------Spring3.0的核心工具包

org.springframework.expression- 3.0.0.RELEASE-------------Spring表达式语言

org.springframework.instrument.tomcat- 3.0.0.RELEASE------Spring3.0对Tomcat的连接池的集成

org.springframework.instrument- 3.0.0.RELEASE-------------Spring3.0对服务器的代理接口

org.springframework.jdbc- 3.0.0.RELEASE-------------------对JDBC的简单封装

org.springframework.jms- 3.0.0.RELEASE--------------------为简化JMS API的使用而作的简单封装

org.springframework.orm- 3.0.0.RELEASE--------------------整合第三方的ORM框架，如hibernate,ibatis,jdo，以及 spring的JPA实现

org.springframework.oxm-3.0.0.RELEASE--------------------Spring 对Object/XMl的映射支持,可以让Java与XML之间来回切换

org.springframework.test- 3.0.0.RELEASE--------------------对Junit等测试框架的简单封装

org.springframework.transaction- 3.0.0.RELEASE-------------为JDBC、Hibernate、JDO、JPA等提供的一致的声明式和编程式事务管理

org.springframework.web.portlet- 3.0.0.RELEASE-------------SpringMVC的增强

org.springframework.web.servlet- 3.0.0.RELEASE-------------对JEE6.0 Servlet3.0的支持

org.springframework.web.struts- 3.0.0.RELEASE--------------整合Struts的时候的支持

org.springframework.web- 3.0.0.RELEASE--------------------SpringWeb下的工具包
```

Spring核心组件详解（Bean、Context、Core）：参考
https://blog.csdn.net/zlfprogram/article/details/75937935


