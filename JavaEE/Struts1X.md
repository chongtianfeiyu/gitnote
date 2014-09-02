#Struts 1.X
Struts特性：  
Struts是MVC设计模式的具体实现，之前我们使用MVC设计模式的时候，都是我们自己把所有部分都写出来，不过是M、V、C，全部要自己写，自己控制，但是使用了Struts框架，已经帮我们将很多总的框架写出来了，我们自己只用往里面天内容即可。我们不用自己去写所有的部分。使用Struts使MVC的结构更加清晰。  
Struts突出体现在视图层。  
Taglib是Struts的标记库。灵活使用，可以大大提高开发效率。标签库极其强大，精通标签使用之后，效率极高。  
简单的信息编辑。  
高效的后台验证。  
增强了代码的可维护性，重用性。  
使用Struts并不一定是为了减少工作量，其作用是使逻辑处理与页面显示分开。编程思路更加清晰。  

Struts 1中使用的技术：  
Http协议、servlet、JSP、JSP标签、JavaBean、Model2（JSP+Servlet+JavaBean）


注意一个误区：  
在我们的JSP中调用其他的Servlet的时候，并不是去通过Servlet的类名字去找到要调用的Servlet的，而是通过查找Servlet的mapping名字去查找调用相应的Servlet的。  

Struts 1.x的简单模拟实现：  
代码示例：  
>JSP代码,index.jsp：  
>
	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<html>
	  <head>
	    <title>My JSP 'index.jsp' starting page</title>
	  </head>
	  <body>
	    <a href="${pageContext.request.contextPath}\empAction.do?className=com.trilever.wt.empAction">连接</a>
	    <a href="${pageContext.request.contextPath}\CustomerAction.do?className=com.trilever.wt.CustomerAction">连接1</a>
	  </body>
	</html>
>Action接口代码,Action.java：  
>
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	public interface Action {
		public String execute(HttpServletRequest request,HttpServletResponse response);
	}
>Action接口实现类,CustomerAction.java：  
>
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	public class CustomerAction implements Action {
		@Override
		public String execute(HttpServletRequest request,
				HttpServletResponse response) {
			System.out.println("cus execute");
			return "/success.jsp";
		}
	}	
>Action接口实现类，empAction.java：  
>
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	public class empAction implements Action {
		@Override
		public String execute(HttpServletRequest request,
				HttpServletResponse response) {
			System.out.println("emp execute");
			return "/success.jsp";
		}
	}
>Servlet类，ActionServlet.java。用于处理JSP中的请求：  
>
	import java.io.IOException;
	import java.io.PrintWriter;
	import javax.servlet.RequestDispatcher;
	import javax.servlet.ServletException;
	import javax.servlet.http.HttpServlet;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import javax.xml.ws.Dispatch;
	public class ActionServlet extends HttpServlet {
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			String className = request.getParameter("className");
			String str=null;
			try {
				Action action = (Action) Class.forName(className).newInstance();
				str = action.execute(request, response);
			} catch (InstantiationException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			RequestDispatcher requestDispatcher = request.getRequestDispatcher(str);
			requestDispatcher.forward(request, response);
		}
		public void doPost(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException {
	
			this.doGet(request, response);
		}
	}
	另：该servlet的xml文件中的mapping名字是.do,这样可以让JSP中的请求被调用。  
>success.jsp,用于结果显示:  
>
	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html>
	  <head>
	  </head>
	  <body>
	   action
	  </body>
	</html>
	
	
以上代码时我们对Struts 1.x的简单模拟，实际中struts 1.x中，在JSP中请求Servlet请求Servlet的时候，并不是使用？的形式传递一个参数给Servlet，从而让servlet对不同的请求作出不同的处理，而是在Servlet中查找请求来源，依据查找的来源结果，来进行不同的处理。  


下面是正宗的Struts1.x框架的使用：  
Struts1.x常用类库jar包：  
Commons-beanutils.jar,commons-chain.jar,commons-digester.jar,commons-logging.jar,struts-core.jar,struts-extras.jar 6个包  
步骤如下：  
1. 添加需要的jar包。  
2. 添加视图组件，也就是jsp文件，用于显示。  
代码示例：  

3. 在web.xml中配置ActionServlet。相对于前面的模拟中我们所创建的ActionServlet，Struts中已经将ActionServlet帮我们创建好了，我们只用配置一下即可。就是在xml文件中注册Struts帮我们创建的ActionServlet  
代码示例：  
>
	<?xml version="1.0" encoding="UTF-8"?>
	<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
	  <display-name>Struts1</display-name>
	  <!--ActionServlet是struts的中央控制器，所有的请求，只要是*.do的形式，
	  		都要通过struts的控制器，也就是这个ActionServlet。
	  		在Struts中只有这一个ActionServlet,它是单示例，多线程运行。
	  -->
	  <servlet>
	  	<servlet-name>ActionServlet</servlet-name>
	  	<servlet-class>org.apache.struts.action.ActionServlet</servlet-class>
	  	<!--设置ActionServlet启动时加载配置文件 -->
	  	<init-param>
	  		<!-- 注意config这个参数名称是固定的 -->
	  		<param-name>config</param-name>
	  		<!-- 该struts配置文件名是随意的，但一般都是使用ActionServlet中写的那一个 -->
	  		<param-value>/WEB-INF/struts-config.xml</param-value>
	  	</init-param>
	  	<!-- 设置ActionServlet的启动顺序 -->
	  	<load-on-startup>0</load-on-startup>
	  </servlet>
	  <servlet-mapping>
	  	<servlet-name>ActionServlet</servlet-name>
		<url-pattern>*.do</url-pattern>
	  </servlet-mapping>
	  <welcome-file-list>
	    <welcome-file>index.html</welcome-file>
	    <welcome-file>index.htm</welcome-file>
	    <welcome-file>index.jsp</welcome-file>
	    <welcome-file>default.html</welcome-file>
	    <welcome-file>default.htm</welcome-file>
	    <welcome-file>default.jsp</welcome-file>
	  </welcome-file-list>
	</web-app>

4. 创建WEB-INF/struts-config.xml文件,增加配置。  
struts-config文件配置代码：  
>
	<?xml version="1.0" encoding="utf-8" ?>
	<!DOCTYPE struts-config PUBLIC
	          "-//Apache Software Foundation//DTD Struts Configuration 1.3//EN"
	          "http://struts.apache.org/dtds/struts-config_1_3.dtd">
	<struts-config>
	 <action-mappings>   
	   <!-- 
	      action-mappings是action的集合
	         path属性:表示action标签的唯一标识
	         type:表示在ActionServlet中要实例化的action类的路径
	         name属性：执行要封装页面的ActionForm,该属性的值必须在form-beans这个标签中存在
	         scope属性:指定ActionFormBean的作用域
	    -->
	    <!-- action标签供ActionServlet阅读，ActionServlet是中央控制器，其通过阅读解析action标签，将请求路径的来源(path)与将要调用的类(type)相联系起来-->
	   <action path="/empAction"  
	        type="com.trilever.wt.empAction"></action>
	   <action path="/CustomerAction"  
	        type="com.trilever.wt.CustomerAction"></action>
	 </action-mappings>
	</struts-config>

5. 创建Action的子类。在请求通过Struts的中央控制器ActionServlet时，ActionServlet会通过解析上面4中配置的struts-config文件，将请求转给响应的处理类，也就是这里我们要创建的Action的子类。注意，这里所有写的用于处理请求的类都必须继承自Action类。  
代码示例：  
>empAction类代码：  
>
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import org.apache.struts.action.Action;
	import org.apache.struts.action.ActionForm;
	import org.apache.struts.action.ActionForward;
	import org.apache.struts.action.ActionMapping;
	//Struts中所有的Action类都要继承自Action
	public class empAction extends Action{
		@Override
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			System.out.println("empaction exec--------------");
			return null;
		}
	}
>CustomerAction代码示例：  
>
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import org.apache.struts.action.Action;
	import org.apache.struts.action.ActionForm;
	import org.apache.struts.action.ActionForward;
	import org.apache.struts.action.ActionMapping;
	public class CustomerAction extends Action{
			@Override
			public ActionForward execute(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				System.out.println("cus exec--------------");
				return null;
			}
	}	
6. 发布运行。  

Struts1执行流程：  
1. 服务器启动时，根据Web.xml文件的配置信息进行实例化ActionServlet。  
2. ActionServlet加载struts-config.xml文件。读取信息放置在内存中。  
3. 客户端从JSP/servlet文件中发出请求，例如：请求路径是：/empAction.do（也就是*.do格式，这样才能走ActionServlet）  
4. ActionServlet对请求路径/empAction.do进行解析。例如，解析为/empAction。  
5. ActionServlet根据解析后的路径/empAction，查找其在struts-config.xml文件中所对应的Action类，也就是type属性的值所对应的类。  
6. ActionServlet中会自动创建5中查找到的我们自己写的Action继承类(如，com.trilever.wt.CustomerAction)的实例对象，并调用这个实例对象的execute()方法，以处理请求。  

使用Struts框架的开发人员的工作：  
1. 在Web.xml中定义请求路径是*.do。  
2. 定义类XXXAction extends Action，并且重写execute()方法。根据具体的业务处理  
3. 配置struts-config.xml文件。  
基本的Struts框架使用：  
>index.jsp  
>
	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<html>
	  <head>
	    <title>My JSP 'index.jsp' starting page</title>
	  </head>
	  <body>
	    <a href="${pageContext.request.contextPath}\empAction.do">连接</a>
	    <a href="${pageContext.request.contextPath}\CustomerAction.do">连接1</a>
	  </body>
	</html>
>web.xml配置  
>
	<?xml version="1.0" encoding="UTF-8"?>
	<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
	  <display-name>Struts1</display-name>
	  <!--ActionServlet是struts的中央控制器，所有的请求，只要是*.do的形式，
	  		都要通过struts的控制器，也就是这个ActionServlet。
	  		在Struts中只有这一个ActionServlet,它是单示例，多线程运行。
	  -->
	  <servlet>
	  	<servlet-name>ActionServlet</servlet-name>
	  	<servlet-class>org.apache.struts.action.ActionServlet</servlet-class>
	  	<!--设置ActionServlet启动时加载配置文件 -->
	  	<init-param>
	  		<!-- 注意config这个参数名称是固定的 -->
	  		<param-name>config</param-name>
	  		<!-- 该struts配置文件名是随意的，但一般都是使用ActionServlet中写的那一个 -->
	  		<param-value>/WEB-INF/struts-config.xml</param-value>
	  	</init-param>
	  	<!-- 设置ActionServlet的启动顺序 -->
	  	<load-on-startup>0</load-on-startup>
	  </servlet>
	  <servlet-mapping>
	  	<servlet-name>ActionServlet</servlet-name>
		<url-pattern>*.do</url-pattern>
	  </servlet-mapping>
	</web-app>
>struts-config.xml  
>
	<?xml version="1.0" encoding="utf-8" ?>
	<!DOCTYPE struts-config PUBLIC
	          "-//Apache Software Foundation//DTD Struts Configuration 1.3//EN"
	          "http://struts.apache.org/dtds/struts-config_1_3.dtd">
	<struts-config>
	 <action-mappings>   
	   <!-- 
	      action-mappings是action的集合
	         path属性:表示action标签的唯一标识
	         type:表示在ActionServlet中要实例化的action类的路径
	         name属性：执行要封装页面的ActionForm,该属性的值必须在form-beans这个标签中存在
	         scope属性:指定ActionFormBean的作用域
	    -->
	    <!-- action标签供ActionServlet阅读，ActionServlet是中央控制器，其通过阅读解析action标签，将请求路径的来源(path)与将要调用的类(type)相联系起来-->
	   <action path="/empAction"  
	        type="com.trilever.wt.empAction"></action>
	   <action path="/CustomerAction"  
	        type="com.trilever.wt.CustomerAction"></action>
	 </action-mappings>
	</struts-config>
>第一个Action子类  
>
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import org.apache.struts.action.Action;
	import org.apache.struts.action.ActionForm;
	import org.apache.struts.action.ActionForward;
	import org.apache.struts.action.ActionMapping;
	public class CustomerAction extends Action{
			@Override
			public ActionForward execute(ActionMapping mapping, ActionForm form,
					HttpServletRequest request, HttpServletResponse response)
					throws Exception {
				System.out.println("cus exec--------------");
				return null;
			}
	}
>第二个Action子类  
>
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import org.apache.struts.action.Action;
	import org.apache.struts.action.ActionForm;
	import org.apache.struts.action.ActionForward;
	import org.apache.struts.action.ActionMapping;
	//Struts中所有的Action类都要继承自Action
	public class empAction extends Action{
		@Override
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			System.out.println("empaction exec--------------");
			return null;
		}
	}	
	
在Struts中，当其带有表单提交的时候，要使用JavaBean来封装表单提交的数据，这个JavaBean对象要继承自ActionForm类。  
这里的Struts1执行流程就有一定的变化：  
1. 服务器启动时，根据Web.xml文件的配置信息进行实例化ActionServlet。  
2. ActionServlet加载struts-config.xml文件。读取信息放置在内存中。  
3. 客户端从JSP/servlet文件中发出请求，例如：请求路径是：/loginAction.do（也就是*.do格式，这样才能走ActionServlet）  
4. ActionServlet对请求路径/loginAction.do进行解析。例如，解析为/loginAction。ActionServlet是Struts框架帮我们写的。  
5. ActionServlet根据解析后的路径/loginAction，查找其在struts-config.xml文件中所对应的Action类，也就是type属性的值所对应的类。  
6. ActionServlet会依据struts-config.xml文件中第5步中所查找到的Action类的name属性，找到用于封装JavaBean类对象。并将Form表单中提交的数据填充封装到JavaBean对象中。  
7. ActionServlet中会自动创建5中查找到的我们自己写的Action继承类(如，com.trilever.wt.loginAction)的实例对象，并调用这个实例对象的execute()方法，以分发、处理Http请求。同时会依据struts-config.xml文件中第5步中所查找到的Action类的name属性，找到用于封装JavaBean类对象。将这个类对象作为execute()方法的一个参数传递给它。这样，就在消息的处理方法中获得了Form表单提交的数据。  
8. ActionServlet根据execute方法返回的ActionForward对象，查找struts-config.xml文件，然后通过其中的forward标签获得转发或者重定向的目标路径，这就是forward标签的使用。

在配置Action子对象的转发或者重定向目标时，就是配置forward标签，然后通过Action子对象的execute()方法中的参数mapping去查找forward标签中定义的重定向、转发目标。  
在配置forward标签的时候，可以配置全局forward标签，也可以配置局部forward标签。对于同一个Action而言，局部forward标签由于全局的forward标签。  

以上的带表单的Struts，开发人员的工作：  
1. 在Web.xml中定义请求路径是*.do。  
2. 定义类XXXAction extends Action，并且重写execute()方法。在其里面获得JavaBean中所封装的Form表单中提交的数据。根据具体的业务处理  
3. 配置struts-config.xml文件。  

带表单提交的Struts代码示例：  
>login.jsp  
>
	<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html>
	  <head>
	    <title>My JSP 'login.jsp' starting page</title>
	  </head>
	  <body>
	  <form action="${pageContext.request.contextPath}/login.do" method="post" name="loginForm">
	  <table>
	  		<tr>
	  			<td>name:</td>
	  			<td><input type="text" name="username"></td>
	  		</tr>
	  		<tr>
	  			<td>pwd:</td>
	  			<td><input type="password" name="password"></td>
	  		</tr>
	  		<tr>
	  			<td><input type="submit" name="submit"></td>
	  		</tr>
	  	</table>
	  	</form>
	  </body>
	</html>
>web.xml配置  
>
	<?xml version="1.0" encoding="UTF-8"?>
	<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
	  <display-name>Struts1</display-name>
	  <!--ActionServlet是struts的中央控制器，所有的请求，只要是*.do的形式，
	  		都要通过struts的控制器，也就是这个ActionServlet。
	  		在Struts中只有这一个ActionServlet,它是单示例，多线程运行。
	  -->
	  <servlet>
	  	<servlet-name>ActionServlet</servlet-name>
	  	<servlet-class>org.apache.struts.action.ActionServlet</servlet-class>
	  	<!--设置ActionServlet启动时加载配置文件 -->
	  	<init-param>
	  		<!-- 注意config这个参数名称是固定的 -->
	  		<param-name>config</param-name>
	  		<!-- 该struts配置文件名是随意的，但一般都是使用ActionServlet中写的那一个 -->
	  		<param-value>/WEB-INF/struts-config.xml</param-value>
	  	</init-param>
	  	<!-- 设置ActionServlet的启动顺序 -->
	  	<load-on-startup>0</load-on-startup>
	  </servlet>
	  <servlet-mapping>
	  	<servlet-name>ActionServlet</servlet-name>
		<url-pattern>*.do</url-pattern>
	  </servlet-mapping>
	</web-app>
>strus-config.xml配置  
>
	<?xml version="1.0" encoding="utf-8" ?>
	<!DOCTYPE struts-config PUBLIC
	          "-//Apache Software Foundation//DTD Struts Configuration 1.3//EN"
	          "http://struts.apache.org/dtds/struts-config_1_3.dtd">
	<struts-config>
	<form-beans>
		<!--
			form-beans是FormBean的集合 
			Action子对象(也就是用于处理请求的对象)查找想要的JavaBean时，是通过查找这里的Form-Bean找到对应的JavaBean的。
			form-bean用于配置封装表单数据的JavaBean 
			type属性表示JavaBean的完整类名
			name属性表示JavaBean对象的唯一标示，也是Action子对象(也就是用于处理请求的对象)在查找ActionForm子对象(也就是JavaBean对象)时的表示
		-->
		<form-bean name="loginForm" type="com.trilever.wt.loginForm"></form-bean>
	</form-beans>
		<!-- 
	 		这里是全局forward标签,对所有的action都有效
	 	 -->
	   <global-forwards>
		 	<forward name="success1" path="/success1.jsp" redirect="false"></forward>
		   	<forward name="failed1" path="/failed1.jsp"></forward>
	   </global-forwards>
	 <action-mappings>   
	   <!-- 
	      	 action-mappings是action的集合
	      	 ActionServlet(也就是中央处理器)在查找用于处理消息的对应Action子对象(也就是用于处理请求的对象)时，是通过查找这里的
	      	 Action来找到对应的Action子对象的。
	         path属性:表示action标签的唯一标识，也是ActionServlet(也就是中央处理器)在查找Action子对象(也就是用于处理请求的对象)时的标示
	         type:表示在ActionServlet中要实例化的action类的路径
	         name属性：表示用于封装form表单提交数据的JavaBean，也就是ActionForm子对象,该属性的值必须在form-beans这个标签中存在
	         name属性的作用是将用于处理消息的对应Action子对象(也就是用于处理请求的对象)与用于封装数据的JavaBean联系起来，这样Action子对象就可以方便地从JavaBean中直接取数据，
	          scope属性:指定ActionFormBean的作用域，默认是session，可以通过mapping.getScope()方法获得。实际上指定的就是actionForm存放在session还是request中。
         	attribute属性：
               如果struts-config.xml里的action标签里面设置了attribute属性值，那么在action子对象的execute()方法
               里面使用mapping.getAttribute()方法获得的就是这里设置的attribute属性值。
                如果struts-config.xml里的action标签里面没有设置attribute属性，那么在action子对象的execute()方法
               里面使用mapping.getAttribute()方法获得的就是这里设置的name属性值。
               如果struts-config.xml里的action标签里面没有设置attribute属性，也没有设置name属性值。那么在action子对象的execute()方法
               里面使用mapping.getAttribute()方法获得的就是null。
		-->
	    <!-- action标签供ActionServlet阅读，ActionServlet是中央控制器，其通过阅读解析action标签，将请求路径的来源(path)与将要调用的类(type)相联系起来-->
	   <action path="/login"  name="loginForm" scope="request" attribute="tt"
	        type="com.trilever.wt.loginAction">
	   <!-- 
	   		这里是局部forward标签,只对本action有效	
	   		本forward标签用于定义本action将要转向的页面路径
	   		name属性：表示本forward标签唯一的标示，自定义
	   		path属性：要转向的路径
	   		redirect属性：设置为true，表示使用重定向，默认是false。设置为true几乎是更好的选择。也就是说默认是请求转发，设置为true就是重定向.
	   		同时要注意这两个forward标签是针对本action的，是对本action处理的转向
	    -->    
	   <forward name="success" path="/success.jsp" redirect="false"></forward>
	   <forward name="failed" path="/failed.jsp"></forward>     
	   </action>
	 </action-mappings>
	</struts-config>
>loginForm.java  
>
	package com.trilever.wt;
	import javax.servlet.http.HttpServletRequest;
	import org.apache.struts.action.ActionErrors;
	import org.apache.struts.action.ActionForm;
	import org.apache.struts.action.ActionMapping;
	//该JavaBean类用于封装表单提交数据。必须继承actionform类。
	//而且此JavaBean中的属性名必须与JSP中的表单中数据域的name是一样的，
	//这样才能使用Structs的自动封装机制。
	public class loginForm extends ActionForm {
		//<td><input type="text" name="username"></td>
		private String username;
		//<td><input type="password" name="password"></td>
		private String password;
		public String getUsername() {
			return username;
		}
		public void setUsername(String username) {
			this.username = username;
		}
		public String getPassword() {
			return password;
		}
		public void setPassword(String password) {
			this.password = password;
		}
	}
>loginAction.java  
>
	package com.trilever.wt;
	import javax.servlet.ServletRequest;
	import javax.servlet.ServletResponse;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import org.apache.struts.action.Action;
	import org.apache.struts.action.ActionForm;
	import org.apache.struts.action.ActionForward;
	import org.apache.struts.action.ActionMapping;
	public class loginAction extends Action{
		//这个execute()方法中的几个参数的意义，form表示封装表单数据的JavaBean对象，
		//mapping表示在struts-config.xml文件中封装的当前action的信息,其里面不仅封装了这个action的name、path。
		//还封装了这个action里面的forward标签，forward标签里面封装的name、path信息。
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			String userName = ((loginForm)form).getUsername();
			String passWord = ((loginForm)form).getPassword();
			System.out.println(((loginForm)form).getUsername());
			//ActionForward对象实际上就是包装了转发路径。在Struts底层，就是获得了这个路径，然后帮助我们讲路径转发给路径对应的页面。
			ActionForward res = null;
			if("123".equals(userName)&&"123".equals(passWord))
			{
				//mapping去struts-config.xml文件中查找相应的ActionForward
				res = mapping.findForward("success1");
				request.setAttribute("deal", "成功");
			}
			else
			{
				//自己创建一个ActionForward,其参数就是struts-config.xml文件中相应的forward标签对应的name.
				res = new ActionForward("failed1");
				request.setAttribute("deal", "失败");
			}
			//获得Action里面设置的attribute属性。
			System.out.println("mapping attribute ---------------"+mapping.getAttribute());
			return  res;
		}
	}
>success.jsp  
>
	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html>
	  <head>
	    <title>My JSP 'success.jsp' starting page</title>
	  </head>
	  <body>
	    Success<br>
	    <h1>
	    ${deal}
	    </h1>
	  </body>
	</html>
>failed.jsp  
>
	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html>
	  <head>
	    <title>My JSP 'success.jsp' starting page</title>
	  </head>
	  <body>
	    failed<br>
	    <h1>
	    ${deal}
	    </h1>
	  </body>
	</html>
	
Struts1工作流程：见图Struts1_working_process，注意该图中的MVC模型的分类。  
Struts中对应的MVC各部分的组件见图Struts_Component。在我们上例中所写的Action的子对象还有Struts给我们写的ActionServlet(完全在幕后工作)都是属于控制层的内容。实际上尽管Structs号称是对MVC模型的具体实现，但是实际上运用的时候，更多地将其体现在表示层。这依据于Struts强大的标签库。  
实际上，在底层代码中，RequestProcessor是将ActionServlet封装起来的类，其实际上就是处理Servlet请求时遵循的控制逻辑。  
关于ActionForm(也就是所谓的JavaBean封装数据的对象)。其每一个属性必须对于HTML的Form数据域中的一个提交数据。  
ActionForm还需符合的一些要求：  
1. 如果你要去在Actionform被传递到Action子对象的execute()方法前(作为参数的形式)，校验数据的合理性，就必须实现该ActionForm子对象(也就是JavaBean)的validate()方法。  
2. 如果希望在将表单提交的数据包装成ActionForm之前，对ActionForm中的属性进行初始化，那么就必须重写ActionForm的reset()方法。  
ActionForm是一个多功能对象，可以扮演字段收集器，数据校验器，类型转换器，以及传输对象。  

动态ActionForm作用：  
例如，在一个网站的登陆与注册页面的时候，注册需要4个数据域，而登陆需要两个数据域。如果按照静态ActionForm的写法，那么对于登陆、注册各需要一个ActionForm类(也就是JavaBean)，但是如果使用动态ActionForm，那就只需要一个动态ActionForm即可。并且对于动态ActionForm，无需get、set方法。  
由于DynActionForm无需get、set方法，所以，其在封装数据是不是采用set方法，而是通过在底层封装成map类型，也就是键值对的形式。在Action中取出其封装的值的时候也不是采用get方法。  
代码示例如下：  
>login.jsp  
>
	<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=utf-8"%>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html>
	  <head>
	    <title>My JSP 'login.jsp' starting page</title>
	  </head>
	  <body>
	  <form action="${pageContext.request.contextPath}/login.do" method="post" name="loginForm">
	  <table>
	  		<tr>
	  			<td>name:</td>
	  			<td><input type="text" name="username"></td>
	  		</tr>
	  		<tr>
	  			<td>pwd:</td>
	  			<td><input type="password" name="password"></td>
	  		</tr>
	  		<tr>
	  			<td><input type="submit" name="submit"></td>
	  		</tr>
	  	</table>
	  	</form>
	  </body>
	</html>
>Web.xml  
>
	<?xml version="1.0" encoding="UTF-8"?>
	<web-app xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns="http://java.sun.com/xml/ns/javaee" xsi:schemaLocation="http://java.sun.com/xml/ns/javaee http://java.sun.com/xml/ns/javaee/web-app_3_0.xsd" id="WebApp_ID" version="3.0">
	  <display-name>Struts1</display-name>
	  <!--ActionServlet是struts的中央控制器，所有的请求，只要是*.do的形式，
	  		都要通过struts的控制器，也就是这个ActionServlet。
	  		在Struts中只有这一个ActionServlet,它是单示例，多线程运行。
	  -->
	  <servlet>
	  	<servlet-name>ActionServlet</servlet-name>
	  	<servlet-class>org.apache.struts.action.ActionServlet</servlet-class>
	  	<!--设置ActionServlet启动时加载配置文件 -->
	  	<init-param>
	  		<!-- 注意config这个参数名称是固定的 -->
	  		<param-name>config</param-name>
	  		<!-- 该struts配置文件名是随意的，但一般都是使用ActionServlet中写的那一个 -->
	  		<param-value>/WEB-INF/struts-config.xml</param-value>
	  	</init-param>
	  	<!-- 设置ActionServlet的启动顺序 -->
	  	<load-on-startup>0</load-on-startup>
	  </servlet>
	  <servlet-mapping>
	  	<servlet-name>ActionServlet</servlet-name>
		<url-pattern>*.do</url-pattern>
	  </servlet-mapping>
	</web-app>	
>Struts-Config.xml  
>
	<?xml version="1.0" encoding="utf-8" ?>
	<!DOCTYPE struts-config PUBLIC
	          "-//Apache Software Foundation//DTD Struts Configuration 1.3//EN"
	          "http://struts.apache.org/dtds/struts-config_1_3.dtd">
	<struts-config>
	<form-beans>
		<!-- 配置动态的ActionForm 
			type属性的值：永远是"org.apache.struts.action.DynaActionForm"
		-->
		<form-bean name="loginForm" type="org.apache.struts.action.DynaActionForm">
			<!-- 配置动态ActionForm中的属性，
				name值必须与表单中数据域的name一样
				type值为属性类型
				initial：属性的初始化值
			 -->
			 <form-property name="username" type="java.lang.String" initial="123"></form-property>
			 <form-property name="password" type="java.lang.String" initial="123"></form-property>
		</form-bean>
	</form-beans>
	 <action-mappings>   
	   <!-- 
	      	 action-mappings是action的集合
	      	 ActionServlet(也就是中央处理器)在查找用于处理消息的对应Action子对象(也就是用于处理请求的对象)时，是通过查找这里的
	      	 Action来找到对应的Action子对象的。
	         path属性:表示action标签的唯一标识，也是ActionServlet(也就是中央处理器)在查找Action子对象(也就是用于处理请求的对象)时的标示
	         type:表示在ActionServlet中要实例化的action类的路径
	         name属性：表示用于封装form表单提交数据的JavaBean，也就是ActionForm子对象,该属性的值必须在form-beans这个标签中存在
	         name属性的作用是将用于处理消息的对应Action子对象(也就是用于处理请求的对象)与用于封装数据的JavaBean联系起来，这样Action子对象就可以
	                      方便地从JavaBean中直接取数据，
	         scope属性:指定ActionFormBean的作用域
	    -->
	    <!-- action标签供ActionServlet阅读，ActionServlet是中央控制器，其通过阅读解析action标签，将请求路径的来源(path)与将要调用的类(type)相联系起来-->
	   <action path="/login"  name="loginForm"   scope="request"
	        type="com.trilever.wt.loginAction">
	   <!-- 
	   		这里是局部forward标签,只对本action有效	
	   		本forward标签用于定义本action将要转向的页面路径
	   		name属性：表示本forward标签唯一的标示，自定义
	   		path属性：要转向的路径
	   		redirect属性：设置为true，表示使用重定向，默认是false。设置为true几乎是更好的选择。也就是说默认是请求转发，设置为true就是重定向.
	   		同时要注意这两个forward标签是针对本action的，是对本action处理的转向
	    -->    
	   <forward name="success" path="/success.jsp" redirect="false"></forward>
	   <forward name="failed" path="/failed.jsp"></forward>     
	   </action>
	 </action-mappings>
	</struts-config>
>loginAction.java  
>
	package com.trilever.wt;
	import javax.servlet.ServletRequest;
	import javax.servlet.ServletResponse;
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	import org.apache.struts.action.Action;
	import org.apache.struts.action.ActionForm;
	import org.apache.struts.action.ActionForward;
	import org.apache.struts.action.ActionMapping;
	import org.apache.struts.action.DynaActionForm;
	public class loginAction extends Action{
		//这个execute()方法中的几个参数的意义，form表示封装表单数据的JavaBean对象，
		//mapping表示在struts-config.xml文件中封装的当前action的信息,其里面不仅封装了这个action的name、path。
		//还封装了这个action里面的forward标签，forward标签里面封装的name、path信息。
		public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
			System.out.println(form);
			DynaActionForm dynaActionForm = (DynaActionForm) form;
			System.out.println((String)dynaActionForm.get("username"));
			System.out.println((String)dynaActionForm.get("password"));
			return  null;
		}
	}
>success.jsp  
>
	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html>
	  <head>
	    <title>My JSP 'success.jsp' starting page</title>
	  </head>
	  <body>
	    Success<br>
	    <h1>
	    ${deal}
	    </h1>
	  </body>
	</html>
>failed.jsp  
>
	<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
	<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
	<html>
	  <head>
	    <title>My JSP 'success.jsp' starting page</title>
	  </head>
	  <body>
	    failed<br>
	    <h1>
	    ${deal}
	    </h1>
	  </body>
	</html>
	

Struts中对ActionForm(也就是JavaBean)的处理步骤：  
也就是在数据封装过程中的实际步骤。  
1. 检查确认在struts-config.xml文件中的action中已经配置了对ActionForm的配置，也就是说，该action中的name属性值指向一个ActionForm(用于封装数据的JavaBean)。如果action中没有name属性，就不能封装form中的数据。  
2. 根据第一步中查找到的ActionForm，查询ActionForm类中的属性是否与JSP文件中提交的Form表单的各个数据域的name值相符。  
3. 检查该ActionForm(就是本action中的name属性的值)的使用范围(就是该action的scope属性的值，有request与session之分)。查找在该范围内是否已经有ActionForm的实例对象，如果已经存在了，那就说明，已经有数据保存在这个JavaBean中了，那么直接使用已经存在的ActionForm即可，如果不存在，那就再重新创建一个ActionForm实例对象。mapping里面封装了action里面的一切东西。所以可以通过mapping查找到action的属性如scope等属性值。  
4. 调用FormBean的reset()方法来对其内部属性值进行重置。相当于初始化JavaBean。  
ActionForm中的reset()方法的作用就是将ActionForm中的属性值全部置空，这样才可以。为什么要置空？因为有时候在第一次提交form之后创建的ActionForm(JavaBean)要存放在session中，那么下次再次提交的时候，有些数据域没有给新值。这样就需要使用reset先进行置空，然后在进行新值的封装。  
5. 调用ActionForm的set方法对其各个属性域赋予新的值。  
6. 如果启用了validate机制，就会调用ActionForm等待validate()方法验证ActionForm中的值是否合规。在表单提交的功能中，ActionForm(JavaBean)中，无论接收封装的数据是什么样的，一律都使用String类型来接收表单提交的各个数据域。原因在于，如果我们在ActionForm中使用了例如int类型来接收表单中的某一个数据，当用户输入错误的话，属于一个字符，这样我们的ActionForm中的这个属性就不能接收表单中的这个输入，导致报错。难以解决。所以，我们使用String来接收一切输入，因为任何的输入都可以被String接收(String的优势就是可以接收一切类型)，然后我们再在validate()方法中对String接收的各个数据域进行验证，看是否满足要求。这就是我们的宗旨，无论输入正确、格式合适与否，首先必须全部保证能够接收下来，然后才是验证是否合适正确。  
7. 验证完成之后，将ActiomForm对象传递给execute()方法，作为其参数。  

注意：在request、session等中存放的不仅可以是字符串，还可以是对象，也就是说，可以是request.setAttribute("people","zhangsan"),这里的"zhangsan"是一个字符串。还可以是request.setAttribute("people",zhangsan),这里的zhangsan是一个对象。

##DispatchAction
当我们发出请求，只希望转发到另外一个JSP页面，而且也要通过ActionServlet中央控制器时，该action不用给其配置ActionForm，该action配置可以写作：<action path="/test" forward="/te.jsp"></action>，这样的请求无需action去处理其数据，而是可以直接通过actionservlet转发给目的页面。  
当我们希望对多个相似的请求(这几个不同的请求指向同一个ActionServlet，但是请求内容还是不同的)在同一个Action中的不同方法进行处理时，就可以使用DispatchAction，对不同但类似的请求进行分发，使用不同的方法进行处理。我们创建一个Action继承自DispatchAction，然后在其里面创建不同的方法，以处理不同的请求内容。因为这个DispatchAction可以依据请求内容进行分发。这个dispatchAction的作用就是，当我们在一个JSP页面中发出CRUB请求的时候，可以将这个几个请求全部都发送给一个DispatchAction的子类。这个子类中有多个方法，每一个方法都对应处理不同的GRUB请求，然后在Struts-config.xml文件中对这个DispatchAction子类进行配置，需要使用parameter参数。  
实际上的工作原理就是：在DispatchAction中的execute()方法(excute是底层必须执行的方法)里面，根据请求的参数不同，调用我们自己写的不同的处理方法。  也就是说，调用我们自己写的方法的底层还是excute()方法，在execute()方法里面根据GRUB的不同要求调用我们自己写的针对GRUB进行操作的各自的方法。  


前面所讲的反射机制的作用，在于，在这里我们可以通过读取web.xml文件及Struts-Config.xml文件中的标签获得属性值，然后通过属性值(都是字符串类型)来调用方法、类。这就是反射机制的使用。不是通过对象进行常规的方法调用。而是通过方法的名字字符串进行调用。  

注意：jsp文件中form表单的name属性值对应了后面的actionform子类(JavaBean)的名字，也是config文件中action标签中的name属性值，form表单的action属性值对应了后面的action子类的名字。这actionform子类在config文件的form-bean中配置，action子类在config文件的action中配置。
##Struts标签
前面我们讨论的都是Struts流程的内容。现开始讨论Struts中的标签库。其使用的标签库与JavaWeb中的JSTL标签库的原理是一样的。  
Struts标签库：  
1. struts-HTML.tld:html标签库。html标签是主要用来创建html输入表单的标记符。  
2. struts-bean.tld:bean标签库。bean标签用于访问JavaBean与他们的关联属性，也可以用于定义新的bean。类似与JSTL中的<c:set>这样的标签。  
3. struts-logic.tld：logic标签库，logic标签用于管理对输出内容的条件判断，从一个集合对象中新欢取出内容等已经应用程序的流程管理。类似于JSTL中的<c:if>这样的标签。  
4. struts-nested.tld:nested标签库，又称为嵌套标签。使用嵌套属性可以自由访问任意层的bean属性。  
5. struts-tiles.tls:建立类似与一种模板的标签库。所谓的模板就是：在网站上的页头、页尾不变的部分，就是一种模板。  
这些在Struts中使用的标签在JSTL中一般都有相应的实现。  
使用Struts标签库需要引入struts标签库的jar包。struts-taglib-1.3.8.jar包。  

###使用html标签库。  
在使用form标签的时候的注意事项如下：  
a. form标签的action属性值必须在struts-config.xml文件中必须有对应的action配置。  
b. 对于a中在xml文件中配置的action，还必须为其写一个actionForm类(JavaBean)，然后在Struts-config.xml文件中配置form-bean以与a中所配置的action相对应。  
c.总之，在JSP中使用form标签的时候，struts-config.xml中必须有action与actionform与之相对应进行匹配，如果没有写action或者actionform与之配对，就会报错。即使action与actionform都是空的，也必须在xml文件中配置出来。原因是:即使我们在JSP中没有写action与name属性，在解析该标签的时候，也会给我们加上默认的action与name属性(该name属性值就是对应path属性的action中的attribute值)，然后就要依据action与name属性去xml文件中查找对应的action与form-bean。如果在struts-config中没有配置对应的action与form-bean，就会报错。form标签中的action属性指向xml文件中的action。form标签中没有name属性，所以不能指定这个form指向的JavaBean(ActionForm子类)。  
在html标签中，输入组件里的name属性，指的就是JavaBean(ActionForm子类)的名字，这也就是为什么html:form标签中没有name属性，因为form标签中name属性指定的javabean由各个输入组件的name属性各自指定而不是有form标签中指定。而property指的是JavaBean中对应的属性名。如：  
	<html:text name="EmpForm" property="username"></html:text>
表示这个text组件输入的对应是EmpForm这个ActionForm类的username属性值。  
form标签的focus属性表示这个form中组件的定焦位置。  
在Struts中怎样设置回显？所谓的回显就是：JSP页面显示的时候，就已经显示了输入组件中的初始值。这就是所谓的回显。回显实现的原理就是在JavaBean中给各个参数赋予初始值。即可自动设置为回显值。  
HTML标签库使用代码示例：  




###Bean标签库



注意：在JSP中，什么时候该写"/"什么时候不该写"/",当请求的是servlet(如action等)，就不需要写，如果请求(无论是转发还是其他)的是一个JSP页面之类的，就需要写"/"甚至为了准确还要写成"PageContext.request.ContextPath/wel.jsp"
这样的形式。