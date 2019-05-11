# springMVC 的web.xml文件解析#



```xml
<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	     xmlns="http://java.sun.com/xml/ns/javaee"
	xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-	app_3_0.xsd" version="3.0">
  <!--
 <display-name>practice-manager-web</display-name>
 首页：
 <welcome-file-list>
   <welcome-file>index.html</welcome-file>
   <welcome-file>index.htm</welcome-file>
   <welcome-file>index.jsp</welcome-file>
   <welcome-file>default.html</welcome-file>
   <welcome-file>default.htm</welcome-file>
   <welcome-file>default.jsp</welcome-file>
 </welcome-file-list>
-->
  
<!--配置 DispatcherServlet-->
<servlet>
	<!--DispatcherServlet的名字，要与下面的<servlet-name>的名字相同 --> 
	<servlet-name>example</servlet-name>  
	<servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class> 
    <!-- 初始化参数，即让容器找到springMVC的配置文件，可以不配置contextConfigLocation
    	使用默认的的配置文件为: /WEB-INF/<servlet-name>-servlet.xml，因为使用了默认，肯定也有默认路		径，新建的文件也要放在 /WEB-INF/文件夹下！！
    --> 
	<init-param>  
		<param-name>contextConfigLocation</param-name>  
		<param-value>classpath:springmvc.xml</param-value> 
	</init-param> 
	
    <!-- 优先启动此servlet(必须是整数，
		小于0或者没有指定时，则表示容器在该servlet被选择时才会去加载。
		当值为0或者大于0时，表示容器在应用启动时就加载并初始化这个servlet；
		当值相同时，容器就会自己选择顺序来加载。整数值越小越优先，
		即启动的时候就加载DispatcherServlet，且优先级为1
    	 ) 
    -->
    <load-on-startup>1</load-on-startup>  
 </servlet> 
 
 <servlet-mapping>  
	<servlet-name>example</servlet-name>  
	<url-pattern>/example/*</url-pattern>  
 </servlet-mapping> 
 <!-- 以上配置的意思是：
 		所有路径以 /example 开头的请求都会被名字为 example 的 DispatcherServlet 处理。
  -->
 
<!-- spring核心(加载spring的配置文件) <context-param>和<init-param>下面有补充 
	listener标签中的类获取<context-param>中配置的文件信息。-->
<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<context-param>
	<param-name>contextConfigLocation</param-name>
	<param-value>classpath:applicationContext.xml</param-value>
</context-param>



<!-- 设置字符编码方式-->
  
 <filter> 
	<filter-name>setcharacter</filter-name> 
	<filter-class>org.springframework.web.filter.CharacterEncodingFilter</filter-class> 
	<init-param> 
		<param-name>encoding</param-name> 
		<param-value>utf-8</param-value> 
	</init-param> 
 </filter> 
 <filter-mapping> 
 	<filter-name>setcharacter</filter-name> 
	<url-pattern>/*</url-pattern> 
</filter-mapping> 

</web-app>

```

补充：

<init-param> 和  <context-param>的区别:

(1)application范围内的参数，存放在servletcontext中，

(2)servlet范围内的参数，只能在servlet的init()方法中取得。

在servlet中可以通过代码分别取用：

```xml
<context-param>
           <param-name>context/param</param-name>
           <param-value>avalible during application</param-value>
</context-param>
<servlet>
    <servlet-name>MainServlet</servlet-name>
    <servlet-class>com.wes.controller.MainServlet</servlet-class>
    <init-param>
       <param-name>param1</param-name>
       <param-value>avalible in servlet init()</param-value>
    </init-param>
    <load-on-startup>0</load-on-startup>
</servlet>
```



```java
package com.wes.controller;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
public class MainServlet extends HttpServlet ...{
	public MainServlet() ...{
		super();
	}
	public void init() throws ServletException ...{

         	System.out.println("下面的两个参数param1是在servlet中存放的");
	        System.out.println(this.getInitParameter("param1"));
			System.out.println("下面的参数是存放在servletcontext中的");
			System.out.println(getServletContext().getInitParameter("context/param"));
	}

}

```

**第一种参数在servlet里面可以通过getServletContext().getInitParameter("context/param")得到**
**第二种参数只能在servlet的init()方法中通过this.getInitParameter("param1")取得.**

**web.xml的配置中<context-param>配置作用：****

1.启动一个WEB项目的时候,容器(如:Tomcat)会去读它的配置文件web.xml.读两个节点: <listener></listener> 和 <context-param></context-param>
2.紧接着,容器创建一个ServletContext(上下文),这个WEB项目所有部分都将共享这个上下文.
3.容器将<context-param></context-param>转化为键值对,并交给ServletContext.
4.容器创建<listener></listener>中的类实例,即创建监听.
5.在监听中会有contextInitialized(ServletContextEvent args)初始化方法,在这个方法中获得ServletContext = ServletContextEvent.getServletContext();
context-param的值 = ServletContext.getInitParameter("context-param的键");
6.得到这个context-param的值之后,你就可以做一些操作了.注意,这个时候你的WEB项目还没有完全启动完成.这个动作会比所有的Servlet都要早.
换句话说,这个时候,你对<context-param>中的键值做的操作,将在你的WEB项目完全启动之前被执行.
7.举例.你可能想在项目启动之前就打开数据库.
那么这里就可以在<context-param>中设置数据库的连接方式,在监听类中初始化数据库的连接.
8.这个监听是自己写的一个类,除了初始化方法,它还有销毁方法.用于关闭应用前释放资源.比如说数据库连接的关闭.

```xml
<!-- spring核心(加载spring的配置文件) -->
<listener>
		<listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
</listener>
<context-param>
	<param-name>contextConfigLocation</param-name>
	<param-value>classpath:applicationContext.xml</param-value>
</context-param>
<!--自定义context-param,且自定义listener来获取这些信息 -->

<context-param>
    <param-name>urlrewrite</param-name>
    <param-value>false</param-value>
</context-param>
<context-param>
    <param-name>cluster</param-name>
    <param-value>false</param-value>
</context-param>
<context-param>
    <param-name>servletmapping</param-name>
    <param-value>*.bbscs</param-value>
</context-param>
<context-param>
    <param-name>poststoragemode</param-name>
    <param-value>1</param-value>
</context-param>
<listener>
    <listener-class>com.laoer.bbscs.web.servlet.SysListener</listener-class>
</listener>
```


```java
public class SysListener extends HttpServlet implements ServletContextListener {
	private static final Log logger = LogFactory.getLog(SysListener.class);
	public void contextDestroyed(ServletContextEvent sce) {
   		//用于在容器关闭时,操作
	}
	//用于在容器开启时,操作
	public void contextInitialized(ServletContextEvent sce) {
   		String rootpath = sce.getServletContext().getRealPath("/");
   		System.out.println("-------------rootPath:"+rootpath);
   		if (rootpath != null) {
    		rootpath = rootpath.replaceAll("\\\\", "/");
   		} else {
    		rootpath = "/";
   		}
   		if (!rootpath.endsWith("/")) {
    			rootpath = rootpath + "/";
   			}
   		Constant.ROOTPATH = rootpath;
   		logger.info("Application Run Path:" + rootpath);
   		String urlrewrtie = sce.getServletContext().getInitParameter("urlrewrite");
   		boolean burlrewrtie = false;
   		if (urlrewrtie != null) {
    			burlrewrtie = Boolean.parseBoolean(urlrewrtie);
   			}
   		Constant.USE_URL_REWRITE = burlrewrtie;
   		logger.info("Use Urlrewrite:" + burlrewrtie);
  		 // 其它略之....
   }
}
```

## DispatcherServlet 的处理流程：

>​	配置好 DispatcherServlet 以后，开始有请求会经过这个 DispatcherServlet 。此时， DispatcherServlet 会依照以下的次序对请求进行处理：
>
>  	（1）首先，搜索应用的 上下文对象 WebApplicationContext  并把它作为一个属性（attribute）绑定到该请求上，以便控制器和其他组件能够使用它。属性的键名默认为 DispatcherServlet.WEB_APPLICATION_CONTEXT_ATTRIBUTE；
>
>  	（2）将地区（locale）解析器绑定到请求上，以便 其他组件在处理请求（渲染视图、准备数据等）时可以获取区域相关的信息。如果你的应用不需要解析区域相关的信息，忽略它即可。
>
> 	 （3）将主题（theme）解析器绑定到请求上，以便其他组件（比如视图等）能够了解要渲染哪个主题文件。同样，如果你不需要使用主题相关的特性，忽略它即可;
>
>  	（4） 如果你配置了multipart文件处理器，那么框架将查找该文件是不是multipart（分为多个部分连续上传）的。若是，则将该请求包装成一个MultipartHttpServletRequest 对象，以便处理链中的其他组件对它做进一步的处理。关于Spring对multipart文件传输处理的支持，读者可以参考21.10 Spring的multipart（文件上传）支持一小节；
>
>  	（5）为该请求查找一个合适的处理器。如果可以找到对应的处理器，则与该处理器关联的整条执行链（前处理器、后处理器、控制器等）都会被执行，以完成相应模型的准备或视图的渲染；
>
>  	（6）如果处理器返回的是一个模型（model），那么框架将渲染相应的视图。若没有返回任何模型（可能是因为前后的处理器出于某些原因拦截了请求等，比如，安全问题），则框架不会渲染任何视图，此时认为对请求的处理可能已经由处理链完成了。

## filter和Interceptor的区别

- Filter是基于函数回调的，而Interceptor则是基于Java反射的。
- Filter依赖于Servlet容器，而Interceptor不依赖于Servlet容器。
- Filter对几乎所有的请求起作用，而Interceptor只能对action请求起作用。
- Interceptor可以访问Action的上下文，值栈里的对象，而Filter不能。
- 在action的生命周期里，Interceptor可以被多次调用，而Filter只能在容器初始化时调用一次。

