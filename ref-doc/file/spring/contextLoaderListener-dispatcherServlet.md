# ContextLoaderListener和DispatcherServlet的解读#

Tomcat或Jetty作为Servlet容器会为每一个Web应用构建一个ServletContext用于存放所有的Servlet, Filter, Listener。Spring MVC 启动的时候主要涉及到DispatcherServlet 与 ContextLoaderListener。



关于ContextLoaderListener和DispatcherServlet

##ContextLoaderListener##

1. ContextLoaderListener 作为一个Listener会首先启动，创建一个WebApplicationContext用于加载除Controller等Web组件以外的所有bean，这个ApplicationContext作为根容器存在，对于整个Web应用来说，只能存在一个，也就是父容器，会被所有子容器共享，子容器可以访问父容器里的bean，反过来则不行。

   > 在web.xml中如果没有配置ContextLoaderListener，就会默认创建一个XmlWebApplicationContext（父容器）

2. **A**. XML配置下会直接创建ContextLoaderListener，然后在contextInitialized方法中初始化WebApplicationContext。
   **B.** 如果使用的是AnnotationConfig，则通过AnnotationConfigWebApplicationContext获取一个WebApplicationContext之后传给ContextLoadListener。
   之后再contextInitialized方法中调用父类ContextLoader的initWebApplicationContext进行初始化。

```java
public class ContextLoader {
     /**
     * The root WebApplicationContext instance that this loader manages.
     */
    private WebApplicationContext context;

    public WebApplicationContext initWebApplicationContext(ServletContext servletContext) {
     this.context = createWebApplicationContext(servletContext);
     servletContext.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, this.context);
     return this.context;
    }
}
```

可以看到ContextLoader作为ContextLoaderListener的父类在创建了WebApplicationContext之后（针对的是XML配置，使用contextConfigLocation参数指定的配置文件），会通过WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE这个属性将ApplicationContext保存到ServletContext之中，而ContextLoader在创建WebApplicationContext之前也会检查这个属性是否有关联的对象，保证了整个Servletcontext之中只会存在一个根WebApplicationContext。

## DispatcherServlet

1. 在加载了Listener，Filter之后，DispatcherServlet作为ServletContext之中很可能唯一的一个Servlet被初始化，作为整个Web应用的Front Controller进行请求转发。
2. **A**. XML配置，生成默认的DispatcherServlet，init时通过init-param中的contextConfigLocation指定的配置文件创建WebApplicationContext。
   **B**.AnnotationConfig，通过AnnotationConfigWebApplicationContext读取JavaConfig后生成WebApplicationContext，传递给DispatcherServlet进行构造。
3. DispatcherServlet构造完成之后，调用init方法进行初始化，将DispatcherServlet关联的WebApplicationContext的父容器设为之前ContextLoaderListener创建的WebApplicationContext。
4. DispatcherServlet关联的WebApplicationContext会在请求到来的时候被设为request的attribute暴露给handlers和之后的web组件。

------

由此可知，对于一个使用SpringMVC构建的Web应用来说，ContextLoaderListener虽然只能加载一个根容器，但其实是可选的，而DispatcherServlet作为请求转发处理返回结果的核心是必不可少的，而且可以不唯一。
但在Web应用中还是建议使用这种分层的容器结构，更为清晰，也便于之后的扩展。

## web.xml与Servlet3.0

传统的web.xml配置适用于Servlet3规范之前的Servlet容器，而Servlet3之后的Servlet容器可以使用注解或是Java代码的方式进行配置。
Servlet3.0环境中，容器会在classpath下寻找ServletContainerInitializer接口的实现类，用于配置Servlet容器。Spring的SpringServletContainerInitializer类就实现了这个接口，并会寻找WebApplicationInitializer接口的实现类完成上面两个组件的加载。

```java
public interface WebApplicationInitializer {
    /**
     * Configure the given {@link ServletContext} with any servlets, filters, listeners
     * context-params and attributes necessary for initializing this web application. See
     * examples {@linkplain WebApplicationInitializer above}.
     * @param servletContext the {@code ServletContext} to initialize
     * @throws ServletException if any call against the given {@code ServletContext}
     * throws a {@code ServletException}
     */
    void onStartup(ServletContext servletContext) throws ServletException;
}
```

这个接口的两个主要实现基类为==AbstractContextLoaderInitializer==与==AbstractDispatcherServletInitializer==，分别负责将ContextLoaderListener与DispatcherServlet加载到ServletContext之中，但是这两个类分别获取WebApplicationContext的函数并没有实现，如果如需要，你可以通过自己实现通过XML获取WebApplicationContext，而Spring提供了一个AbstractAnnotationConfigDispatcherServletInitializer供你来继承，如名可知，是使用JavaConfig来加载WebApplicationContext的。

当然你也可以自己实现WebApplicationInitializer手动将Servlet和Listener这些加载如ServletContext。

强调：Servlet3.0容器会自己检查classpath下实现了ServletContainerInitializer的类，使用这个接口的onStartup函数进行ServletContext的初始化。如果不适用这个，那么就用传统的web.xml进行ContextLoaderListener与DispatcherServlet的装配。

### 强调

对于DispatcherServlet与ContextLoaderListener的配置分为web.xml与Servlet容器发现两种，而对于这两个组件的WebApplicationContext的配置也分为XML方式和JavaConfig方式。

1. web.xml + application.xml =

   ```xml
   <listener>
         <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>
   </listener>
   <context-param>
         <param-name>contextConfigLocation</param-name>
         <param-value>classpath*:config/spring-context.xml</param-value>
   </context-param>
   //<br/>
   <servlet>
         <servlet-name>ebook-manage</servlet-name>
         <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>
         <init-param>
           <param-name>contextConfigLocation</param-name>
           <param-value>classpath:config/spring-servlet.xml</param-value>
         </init-param>
         <load-on-startup>1</load-on-startup>
         <async-supported>true</async-supported>
   </servlet>
   <servlet-mapping>
           <servlet-name>ebook-manage</servlet-name>
           <url-pattern>/</url-pattern>
   </servlet-mapping>
   ```

   2.web.xml + JavaConfig =

   ```xml
   <context-param>
   	<param-name>contextClass</param-name>
   	<param-value>
    		org.springframework.web.context.support.AnnotationConfigWebApplicationContext
   	</param-value>
   </context-param>
   <context-param>
   	<param-name>contextConfigLocation</param-name>
   	<param-value>com.habuma.spitter.config.RootConfig</param-value>
   </context-param>
   <listener>
   	<listener-class>
   		org.springframework.web.context.ContextLoaderListener
   	</listener-class>
   </listener>
   <servlet>
   	<servlet-name>appServlet</servlet-name>
   	<servlet-class>
   		org.springframework.web.servlet.DispatcherServlet
   	</servlet-class>
   	<init-param>
   		<param-name>contextClass</param-name>
   		<param-value>
   			org.springframework.web.context.support.AnnotationConfigWebApplicationContext
   		</param-value>
   	</init-param>
   	<init-param>
   		<param-name>contextConfigLocation</param-name>
   		<param-value>
   			com.habuma.spitter.config.WebConfigConfig
   		</param-value>
   	</init-param>
   	<load-on-startup>1</load-on-startup>
   </servlet>
   <servlet-mapping>
   	<servlet-name>appServlet</servlet-name>
   	<url-pattern>/</url-pattern>
   </servlet-mapping>

   ```

   3.ServletContainerInitializer(WebApplicationInitializer) + JavaConfig =

   ```java
   public class SpittrWebAppInitializer
   extends AbstractAnnotationConfigDispatcherServletInitializer {
   @Override
   protected String[] getServletMappings() {
   return new String[] { "/" };
   }
   @Override
   protected Class<?>[] getRootConfigClasses() {
   return new Class<?>[] { RootConfig.class };
   }
   @Override
   protected Class<?>[] getServletConfigClasses() {
   return new Class<?>[] { WebConfig.class };
   }
   }

   ```

   4.ServletContainerInitializer(WebApplicationInitializer) + application.xml =
   需要继承AbstractDispatcherServletInitializer然后自己实现通过application.xml加载ApplicationContext的函数。