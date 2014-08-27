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
	import javax.servlet.http.HttpServletRequest;
	import javax.servlet.http.HttpServletResponse;
	public interface Action {
		public String execute(HttpServletRequest request,HttpServletResponse response);
	}
>Action接口实现类,CustomerAction.java：  
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


在Struts中，当其带有表单提交的时候，要使用JavaBean来封装表单提交的数据，这个JavaBean对象要继承自ActionForm类。  
这里的Struts1执行流程就有一定的变化：  
1. 服务器启动时，根据Web.xml文件的配置信息进行实例化ActionServlet。  
2. ActionServlet加载struts-config.xml文件。读取信息放置在内存中。  
3. 客户端从JSP/servlet文件中发出请求，例如：请求路径是：/loginAction.do（也就是*.do格式，这样才能走ActionServlet）  
4. ActionServlet对请求路径/loginAction.do进行解析。例如，解析为/loginAction。ActionServlet是Struts框架帮我们写的。  
5. ActionServlet根据解析后的路径/loginAction，查找其在struts-config.xml文件中所对应的Action类，也就是type属性的值所对应的类。  
6. ActionServlet会依据struts-config.xml文件中第5步中所查找到的Action类的name属性，找到用于封装JavaBean类对象。并将Form表单中提交的数据封装到JavaBean对象中。  
7. ActionServlet中会自动创建5中查找到的我们自己写的Action继承类(如，com.trilever.wt.loginAction)的实例对象，并调用这个实例对象的execute()方法，以处理请求。同时会依据struts-config.xml文件中第5步中所查找到的Action类的name属性，找到用于封装JavaBean类对象。将这个类对象作为execute()方法的一个参数传递给它。这样，就在消息的处理方法中获得了Form表单提交的数据。  

以上的带表单的Struts，开发人员的工作：  
1. 在Web.xml中定义请求路径是*.do。  
2. 定义类XXXAction extends Action，并且重写execute()方法。在其里面获得JavaBean中所封装的Form表单中提交的数据。根据具体的业务处理  
3. 配置struts-config.xml文件。  





