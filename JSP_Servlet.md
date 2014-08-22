#Java Web
-----
##基础知识
所谓的`Java Web`,就是用`Java`做网站。  
动态网站：能与数据库进行交换的网站。  
`WebServer`：就是用来运行`WebApp`应用程序的服务器。
技术流派如下：  
1. `JSP`网站空间贵，`ASP`技术已经淘汰，但是空间便宜。  
2. `PHP`语法借鉴了`C`、`Java`等语言，装在`Apache`服务器上，语言简单，开发简单。功能没有`Java`、`.Net`强大。做超大型项目还是不行。依赖于`Apache`服务器。
注意：`Apache`服务器是一种服务器，`TomCat`服务器也是一种服务器，二者平级。但是两种服务器可以进行整合，使得`TomCat`服务器可以使用`Apache`服务器的功能。
3. `JSP/Servelet`:学习`Java`语言做`B/S`的技术。学习`Java`做`C/S`的技术就是`AWT/Swing`。  
4. `ASP.NET`:就是`ASP`的升级版，这就是学习`C#`语言做`B/S`的技术，学习`C#`做`C/S`的技术就是`winforms`。微软的技术特点就是什么事都帮你做好，具体内部实行的细节保密不会告诉你。`Java`的技术特点就是告诉你方向，细节部分都得自己去做细节部分可以暴露出来，所以`Java`内部的东西都是可控的，任何东西都是自己明白的。`ASP.net`必须运行在`IIS`服务器上，但`IIS`依赖于`Windows`服务器。


不要去那种做网站的小公司工作。
#JSP/Servlet
----
##基础知识
`JSP/Servlet`是`SUN`制定的用`Java`开发`Web`应用程序的规范。  
`JSP`规范是建立在`Servelet`规范之上的。先有`Servelet`,后有`JSP`。  
`Servlet`：是一个可以部署到`WebServer`服务器上的可以供客户端访问、处理客户端请求的`Java`类。其作用是接受客户端的请求负责某一方面的工作。  
`JSP`：文件后缀`jsp`，`JSP`页面=`DHTML(HTML+CSS+JS)+Java`。该页面转译后就是一个`Servlet`。  
我们要做的事：依据`JSP/Servlet`这个规范开发出应用组件(就是`Web`应用程序)，然后将这组件放在容器中(`Tomcat`)，让其运行。供客户端访问调用我们写的程序。对客户的请求作出回应。  

##`TomCat`的配置
`config`目录下的`Server.XML`文件用于配置`TomCat`端口号。  
其`TomCat User.XML`文件用于配置登陆用户的账号等信息。在`TomCat`中添加账号的时候，不但要添加用户还需要添加角色。  
如下：  
>
	<role rolename="manager-gui"/>
    <user password="123" roles="manager-gui" username="trilever"/>

使用`MyElipse`插件进行`Web`应用开发，其是`Eclipse`的插件。  
一个`Web`服务器中可以放有多个应用程序项目。一个应用程序项目中可以有多个`Servelet`，各个`Servelet`负责不同的任务。  
要学会`TomCat`的自动部署和手动部署。  
手动部署：就是将你自己创建的`Web`项目中的`WebRoot`目录下的文件全部复制到`TomCat`的`WebApps`目录下与项目同名的文件夹中(要在`WebApps`目录下先建立一个与项目同名的文件夹才可以，然后将那些文件复制进去)。
自动部署：`IDE`会自动将手动部署的复制这些步骤全部自动完成。
我们自己写代码都是写在我们项目的文件夹中，而不是在`TomCat`的目录中。但是如果是放在项目的`WebRoot`目录下，就会自动被同步到`TomCat`的`WebApps`目录下面。

注：`JSP`页面就是`DHTML+Java`。其页面中包含这两种代码。在运行的时候，会自动将`JSP`代码转化为`Servlet`进行运行。  

#Java Web中的Servlet
##基础知识
其本质就是一个特殊的`Java`类。
`Servlet`类的继承结构如图`Servlet_Inherit_Structure.png`所示：  

`Servlet`实例的创建(`new`)、销毁都是由容器完成的，而不需要人工手动执行(一般的类都需要人工手动创建实例)。  
`Servlet`类与普通类的区别见图`Servlet_With_Ordinary_Class.png`所示：  

##Servlet的生命周期
所谓的`Servlet`类对象的生命周期：就是指一个`Servlet`类对象创建(由`Web`容器`Tomcat`完成)到消亡的整个过程。  
`Servlet`类生命周期见图`Servlet_Life_Period1.png`、`Servlet_Life_Period2.png`所示：  

由于我们自己创建的`Servlet`类继承自`HttpServlet`类，也就是说继承了`server()`方法、`init()`方法、`destory()`方法。在`HttpServlet`类中，`server()`方法中实际上就是`doPost()`、`doGet()`方法。该对象会依据接收的是`post`请求还是`get`请求而决定在`server()`方法中实际上是调用`doPost()`还是`doGet()`方法。  
`HttpServlet`类的生命周期见图`HttpServlet_Life_Period.png`所示：  

那么，当服务器第一次接收到调用这个`HttpServelet`的类对象请求的时候，就会由容器创建一个`HttpServlet`类实例对象。  
然后服务器中就会调用`init()`方法(一个生命周期中`init()`方法只调用一次)进行初始化。  
然后调用`server()`(一个生命周期中`server()`方法要调用多次)方法，以后每次接收到新的服务请求的时候，都会调用`server()`方法。  
当关闭服务器或者关闭这个`HttpServlet`(在`Tomcat`控制台中停止一个`Web`应用就会关闭这个应用中所有的`Servlet`)的时候，就会调用`destory()`方法。  
注意：当一个`Servlet`部署完成后，*启动`Web`服务器的时候*，就是一个`Web`应用程序(其中包含多个`Servlet`)启动的时候。即使此时并没有来自客户端的服务请求，但是此时的`Web`应用程序项目还是启动了。然后当接收到来自客户端的请求的时候，就会调用其中包含的各个`Servlet`的`init()`方法等。  

怎样修改一个`Web`应用程序(包含多个`Servlet`)启动的时候的行为？例如，在`Web`应用程序启动(也就是启动`Web`服务器的时候或者在`Tomcat`控制台中启动一个`Web`应用项目(一个`Web`应用项目中包含多个`Servlet`))，就调用`init()`方法而不是等到接收到客户端请求的时候才调用该方法。  
`Answer`：修改`web.xml`配置文件。  
>	
	<servlet>
      <servlet-name>doctor</servlet-name>
      <servlet-class>com.trilever.Doc</servlet-class>
	  <load-on-startup>1</load-on-startup>//此句用于设置在Web服务器启动本项目的时候即调用本Servlet的init()方法。0或1或2……用于指定在启动Web服务器时所要具体调用哪个Servlet的init()方法，例如：如果有多个Servlet，都设置为startup时启动，那么就要依据这里的数字顺序进行启动。
    </servlet>
	<servlet-mapping>
      <servlet-name>doctor</servlet-name>
      <url-pattern>/doctors</url-pattern>
    </servlet-mapping>
###init()方法中的ServletConfig参数对象
`init()`方法中的`ServletConfig`参数包含了这个`Servlet`对象的所有`init-param`配置信息，这个`init-param`配置信息从`web.xml`中读出来的。  
所谓的`init-param`配置参数就是在写这个`HttpServlet`时就设置好的参数，不是从客户端获得的，而是程序员在写代码的时候就预先进行配置好的参数。  
关于`Servlet`参数，见图`ServletConfig_Parameter.png`：  

`Web.xml`中配置`Servlet`中的`init-param`参数示例：  
>
	<servlet>
	        <servlet-name>zhangshan</servlet-name>
	        <servlet-class>com.trilever.zhangsan</servlet-class>
	        <init-param>
	          <param-name>age</param-name>
	          <param-value>10</param-value>
	        </init-param>
	        <init-param>
	          <param-name>gen</param-name>
	          <param-value>true</param-value>
	        </init-param>
	        <load-on-startup>1</load-on-startup>
	</servlet>
	
	
在`init()`方法中通过`config`参数获得`Servlet`的`init-param`配置参数代码示例：  
>
	String age;
	@Override
	public void init(ServletConfig config) throws ServletException 
	{
		super.init(config);
		this.age=config.getInitParameter("age");
		//通过config获得存储空间application对象，跨Web app的数据存储空间。
		ServletContext application = config.getServletContext();
		Enumeration<String> e = config.getInitParameterNames();
		while(e.hasMoreElements())
		{
			System.out.println(e.nextElement());
		}
	}
	
###HttpServlet里doPost()、doGet()方法中的request参数对象
在我们的`doPost()`或者`doGet()`方法中，会传递给它们一个`HttpServletRequest`参数对象。  
这个对象是`Web`服务器(也就是容器`Tomcat`)将*客户端发送的请求的所有信息进行包装*而获得。  
`HttpServletReqest`是一个接口，这里是面向接口编程，实际传递给方法的是它的实现类对象(具体实现是有容器帮我们进行的，因为对请求信息的包装是容器帮助我们进行的)。以后可以通过`request`对象的方法来获得其封装的内容。  
`request`对象的方法：
1. `getparameter(name)`：获得文本框、单选框等`HTML`中`Form`元素提交的内容。`name`是这些元素的名字。  
2. `getparametervalues(name)`:获得多选框中提交的内容，`name`是多选框的名字。  
3. `getparameternames()`:获得`Form`中所有元素的名字，当我们不知道`Form`中有那些元素的时候可以使用。  
4. `getConTextpath()`:获得`WebApp`的名称，也称为虚拟路径，一般是项目名称。举例：`Tomcat`容器好比一个医院，我们一个网站(也就是一个`Web`应用程序)相当于一个科室，每个`Tomcat`服务器中可以放多个网站。每一个`Servlet`就相当于一个医生(负责对每一个客户端的请求作出回应)，每个科室有好多个医生。就好比一个网站有很多个`Servlet`用于接收处理各种各样的请求。  
5. `getServletPath()`：如果是请求的一个`Servlet`，那么返回这个`Servlet`的`url-parttern`。如果请求的是一个`jsp`页面，那么返回`jsp`文件名。  
6. `getRequestURI()`:获得`Servlet`的相对路径。  
7. `getRequestURL()`:获得`Servlet`的绝对路径，包括`IP`、端口号等等。  
8. `getScheme()`:获得使用的协议。  
9. `getRemoteAddr()`:获得客户端的`IP`地址。  

`request`参数使用示例：  
>
	public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException 
		{
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(this.age);
			out.println(request.getContextPath());
			out.println(request.getServletPath());
			out.println(request.getRequestURL());
			out.println(request.getScheme());
			out.println(request.getRemoteAddr());
			out.flush();
			out.close();
		}

因此我们可以在`Servlet`中通过这一个参数获得所接收请求的内容数据，如，获得一个`Form`中提交的各种数据，然后针对这些请求的内容予以回应并发送给客户端。这就是交互式网页的思维方式。因此，我们可以知道**一个`Servlet`的作用功能就是针对客户端`HTML`文档中的某一个表单或者一个超链接所提交的内容的一个回应。**  
我们所言的框架，其底层就是对这些`Servlet`的包装、组合。  

###Servlet中的方法重写
我们自己写的`Servlet`中需要重写`doPost()`方法与`doGet()`方法的意义何在？  
`Answer`：因为我们自己写的`Servlet`类中继承了`HttpServlet`中的`server()`方法，而这个`server()`方法中会依据客户端发送的请求是`post`还是`get`类型去选择调用`Servlet`类中的`doPost()`方法或者`doGet()`方法，所以我们自己重写了`doPost()`方法与`doGet()`方法就相当于重写了`server()`方法。  
所以当服务器接收到客户端的一个请求之后，就会自己调用该`Servlet`的`server()`方法，然后这个`server()`方法会依据接收到的请求类型确定应该调用`doPost()`还是`doGet()`方法去处理这个请求。  

在一个网页中发出请求访问`Servlet`的方法？
1. 通过网页中的超链接进行`Servlet`的请求访问。链接目的地址使用`Servlet`的`url`进行访问请求的发送。但是用这种方法的请求方式只有`get`方法。
2. 通过`Form`表单的提交进行`Servlet`的请求访问。在`action`属性中用`Servlet`的`url`以进行访问请求的发送。这种方式的请求可以是`get`也可以是`post`方法。
3. 直接在地址栏中填写`Servlet`的`url`地址进行请求访问。


###建立一个`Servelet`的操作步骤：
1. 创建一个类，继承自`HttpServelet`。  
2. `OverRide`两个方法，`doGet()`与`doPost()`。  
3. 去`web.xml`配置文件中进行注册配置`servlet`。此步很重要。  

创建`Servlet`的示例代码：  
>
	package com.trilever;
	public class Doc extends HttpServlet
	{
		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException
		{
			super.doGet(req, resp);
			//使用System.out是在服务器段进行打印，而不是在客户端。
			System.out.println("mark");
			//使用PrintWriter类是在客户端打印，也就是想客户端输出
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.write("<html>");
			out.write("<head>");
			out.write("godo");
			out.write("</head>");
			out.write("<br>");
			out.write("<body>");
			out.write("love");
			out.write("<br>");
			out.write("make");
			out.write("</body>");
			out.write("</html>");
		}
>	
		@Override
		protected void doPost(HttpServletRequest req, HttpServletResponse resp)
				throws ServletException, IOException
		{
			super.doPost(req, resp);
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter(); 
			out.write("<html>");
			out.write("<head>");
			out.write("godo");
			out.write("</head>");
			out.write("<br>");
			out.write("<body>");
			out.write("love");
			out.write("<br>");
			out.write("make");
			out.write("</body>");
			out.write("</html>");
		}
	}
	
>
`Web.XML`文件配置：  
>
	<servlet>
      <servlet-name>doctor</servlet-name>
      <servlet-class>com.trilever.Doc</servlet-class>
    </servlet>
	<servlet-mapping>
      <servlet-name>doctor</servlet-name>
      <url-pattern>/doctors</url-pattern>
    </servlet-mapping>


在浏览器中输入：http://localhost:8080/Hos/doctors 即可在服务器中调用`doGet()`方法，其向客户端发送我们想要的内容。该文件中的`url-pattern`就是向客户端暴露的用于访问该`Servlet`的地址路径。


###访问调用一个`Servlet`时的工作原理  
1. `Ip+Port`：调用服务器的服务。  
2. 加上项目名：调用这个项目。找到这个应用程序所在的项目。一个项目中可以有多个`Servlet`类。  
3. 通过`Web.XML`文件中的配置信息，找到我们想要的类(`Servlet`)。查找步骤是:通过配置文件中的`url-parttern`找到`servlet-name`，通过`servlet-name`找到`servlet-class`，这个就是我们想要找到的类。然后，这个类(就是前面我们自己创建的继承了`HttpServelet`类的那个类)就会自动调用`doGet()`或者`doPost()`(具体调用哪一个是依据我们`request`的模式进行选择)对我们的请求进行回应。这两个方法的回应返回给我们的浏览器，然后在浏览器中就可以接收到这两个方法中发出的`DHTML`，并进行解析从而获得页面。


##`WebApp`的请求应答模式：  
1. 用户输入地址或者填写表单上的数据提交之后，这些数据会被送到服务器上，定位服务器上的资源文件的地址就是`URL`。客户端就是依据`URL`去找到服务器上的资源的。  
2. 当服务器收到用户请求之后，就会调用相应的程序进行回应，这样结果就是一些`HTML`文档被这些相应的方法返回给客户端。例如、`Servelet`中的`doGet()、doPost()`方法。这两种方法中就是向客户端返回`HTML`文档内容。  
3. 当客户端浏览器接收到那些方法中返回的`HTML`文本后，就会进行解析，向用户呈现出网页。  
4. 浏览器与服务器之间的通信使用`HTTP`协议进行通信。

所谓的`Web`服务器就是`Tomcat`这样的服务器。是一个容器，也是`Servlet`引擎，也称之为容器。

所谓的集群服务器，就是使用多个服务器。  
1. 防止其中一台当机之后，可以使用其他的机器接替他的工作。  
2. 可以做负载均衡，将单机的流量分配到多个机器上，就是所谓的负载均衡。  

注：当有多个请求访问同一个`Servlet`的时候，会为每一个请求创建一个线程。但是`Servlet`同一时刻只有一个示例，每个线程都可以访问该`Servlet`装入时的初始化变量。

##请求转发与重定向
在`Java Web`中，`Servlet`与`JSP`的功能是一样的，都是用于处理客户请求的，无论客户是请求`Servlet`还是请求`JSP`，都是让这两种组件处理问题的。他们是同级的关系。  
`Servlet`或者`JSP`的作用是处理客户端的请求，那么各个`Servlet`/`JSP`之间是怎样进行通信的？就好比，科室(`Web`应用程序)中的各个医生(`Servlet`/`JSP`)在其中一个不能诊断的时候怎样将病人交给另一个病人。  
`Answer`:通过请求转发与重定向。  
也就是说一个客户端请求可以顺次调用多个不同的`Servlet`/`JSP`。

**请求转发**：由前可知，一个客户端发送给服务器的请求信息被包装成一个`request`对象发送给目的`Servlet`/`JSP`。  
一个`request`对象中包含两部分的内容：`parameter`和`attribute`。  
其中的`parameter`部分包含的信息对于接收的`Servlet`而言是可以查看但不能修改的，如客户端的`IP`地址、`URL`等。  
而`attribute`部分的内容对于接收的`Servlet`/`JSP`而言是可以修改的，每一个`Servlet`/`JSP`对这个`request`对象的修改都反映在`attribute`中而不是在`parameter`部分。  
而且多个`Servlet`/`JSP`都可以顺次接收这个`request`并且对这个`request`的`attribute`部分进行修改，这个`request`在各个`Servlet`/`JSP`之间进行传递就是请求转发（请求转发是通过一个对象进行的)。`parameter`是只读的，而`attribute`是可读写的。  
就好比，当看一个医生A的时候，病历就相当于`request`，其中的病人名字、年龄等内容就是在`parameter`部分，是不能被医生修改的，医生诊断结果就写在`attribute`部分，当一个医生无法确定的时候就会让护士将这个病人交给医生B，医生B就可以修改A医生在病历上所作的诊断结果(`attribute`部分)。将病人交给医生B的过程就是请求转发，其中的护士就是负责进行请求转发的对象。  
在请求转发中使用的对象就是`RequestDispatcher`类对象。  
注：`request`可以看作是封装了`map`，里面都是键值对，如、`parameter`中就是一个个的键值对在里面，`attribute`中也是键值对在里面。
请求转发使用代码示例：  
`Servlet1`中的`doGet()`方法重写：
>
	public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
		{
			//Servlet对象Bailang对request对象的修改体现在其中的Attribute上。
			request.setAttribute("zhanghao", "123");
			//通过RequestDispatcher对象对request进行转发。转发给Servlet对象Heigou 
			RequestDispatcher rd = request.getRequestDispatcher("/Heigou");
			rd.forward(request, response);
		}

`Servlet2`中的`doGet()`方法重写：
>
	public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
		{
			//获得转发的request对象中的attribute值。
			String zhanghao = (String) request.getAttribute("zhanghao");
			System.out.println(zhanghao);
			//Servlet对象重写设置attribute值，并将request对象转发给Servlet对象Yanjingshe。
			request.setAttribute("zhanghao","456");
			RequestDispatcher rd = request.getRequestDispatcher("/Yanjingshe");
			rd.forward(request, response);
		}

`Servlet3`中的`doGet()`方法重写：
>
	public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException
		{
			String zhanghao = (String) request.getAttribute("zhanghao");
			System.out.println(zhanghao);
			//Servlet对象重写设置attribute值，并将request对象转发给Servlet对象Yanjingshe。
			request.setAttribute("zhanghao","456");
		}

`Attribute`用于在本`Web App`之中的各个`Servlet`与`JSP`之间保存储存数据对象。  
当，后端某个`Servlet`不能完全处理问题，就会将这个问题转发给另一个后端的`Servlet`，让它去解决问题。当一串`Servlet`将问题解决之后，就会将结果存于`Attribute`中，转发给前端的`JSP`，让`JSP`对结果进行向客户端显示(实际上`JSP`也是按照确定规则转换为`Servlet`，该`Servet`的作用就是将`JSP`中要显示的内容以`DHTML`的形式发送出去。)  

**重定向**：  
1. 在请求转发中，`request`在几个`Servlet`/`JSP`间传递的时候，`request`中的参数是会被传递的。而在`response`的重定向中，`request`中的参数是不会被传递的，而是由客户端向重定向目的地发送一模一样的`request`值,重定向目的地也拥有了与第一个`Servlet/JSP`一样的`request`。而不受第一个`Servlet/JSP`对`request`的修改的影响这就是二者的区别所在。  
2. 在请求转发中，客户端的`URL`地址不发生改变，因为在请求转发中，客户端只向第一个`Servlet`/`JSP`发送了请求，后面的转发与其无关。但是在重定向中`URL`地址会发生变化，因为重定向中，客户端还得向每一个重定向目的`Servlet`/`JSP`发送一模一样的请求。所以，请求转发会导致一个问题，对于客户端的表单提交，当发生刷新的时候，就会发生重复提交，因为`URL`地址是不变的。此时可以使用重定向防止表单重复提交。  
3. 请求转发中不用加`WebApp`项目名称，而重定向中，要么加`WebApp`项目名称要么使用去掉"/"的重定向后`Servlet`/`JSP`名称。无论是请求转发还是重定向，我们统一使用的是目标`Servlet`或者`JSP`的名字即可。  
4. 请求转发只能在同一个`WebApp`中的各个`Servlet`/`JSP`之间进行转发，而重定向则可以定向到不同的`WebApp`中的`Servlet`/`JSP`中，因为重定向不涉及参数的传递(因为参数传递会发生丢失)。但是，如果在使用重定向的时候确实想传递`request`参数，也是有办法的，那就是在`response`的重定向中加上要传递的那些参数即可。  
5. 在请求转发中，第一个`Servlet`/`JSP`接收到`post`请求，那么请求转发后接收到的`Servlet`/`JSP`全部都接收到`post`请求。反之亦然。但是，如果是重定向，即使第一个`Servlet`/`JSP`接收到的是`post`请求，那么重定向后接收到的`Servlet`/`JSP`依旧都是`get`请求。因为任何一次转发默认的是`get`请求。对于请求转发而言，即使多次转发，依旧是只算一次请求在各个`Servlet`/`JSP`之间传递(只有一个`request`对象)。而对于重定向而言，每重定向一次，就是产生了一次新的请求(产生新的`request`对象)，所以重定向后的`Servlet`/`JSP`接收的就是新的默认`get`请求(即使第一次接收的是`post`请求，也与之没有关系)。多次请求就会产生多个`request`对象。  
重定向使用代码示例：  
>
	//重定向到Heigou这个Servlet对象。后面使用的是Servlet名字。
	response.sendRedirect("Heigou");

请求转发与重定向区别：  
在请求转发中，在第一个`Servlet`/`JSP`中，`request`被转发之前，第一个`Servlet`/`JSP`是不会向客户端作出回应的。只有当`request`/`JSP`转发到转发目的地后，目的地的代码执行完成，才会将二者的执行结果一起返回给客户端。  
在重定向中，在第一个`Servlet`/`JSP`中，`response`在重定向之前，`response`就会将结果返回给客户端，到了重定向的时候，就会告诉客户端，让客户端重新向重定向的目的地发送一个同样的`request`对象，参数和第一次发的一样。  

注意：不仅`Servlet`可以请求转发、重定向至`Servlet`，而且`Servlet`还可以请求转发、重定向至`JSP`，二者之间是可以互相请求转发、重定向的。  


那么什么时候该用请求转发什么时候该用重定向？  
`Answer`：当我们需要传递数据的时候使用请求转发，当不需要的时候使用重定向。例如，当在密码输入错误的情况下，就要使用重定向。  

##Java Web中的中文乱码的问题
###什么时候会出现中文乱码
`Answer`：  
1. `Servlet`相关：`request.getParameter()`时与`print()`时。  
2. `JSP`相关：`out.print()`时会出现。  

###出现中文乱码的原因 
中文浏览器使用`gbk`编码或者`GB-2312`编码，而`Tomcat`使用`ISO-8859-1`编码，这样就会导致编码不一致的问题。  
所以要解决这个问题的手段就是统一所使用的编码。  
常用的编码格式：`gbk(中文)`、`ISO(英文)`、`UTF-8(国际)`，所以如果我们编写面向国际的代码，那么就使用`UTF-8`的编码格式。  


无论我们在服务器端的JSP或者Servlet设置的页面字符集是什么字符集，浏览器发送给服务器的数据都是ISO编码。
###在代码层面上的解决之道
由于`Web`应用程序是浏览器与服务器之间的`对话`。所以在代码层面上解决就在于对话所使用的语言(编码格式）。  
#####服务器对浏览器的回复，向浏览器发送消息时解决中文乱码。  
a.`Servlet`回复浏览器的情况。  
设置`response`的格式：  
>
	response.setContentType("text/html;charset=gbk");//这样就将服务器端回复客户端的编码格式设置为gbk。
	
b.`JSP`回复浏览器的情况。  
注意：我们请求`JSP`页面的时候，实际上是将`JSP`页面转译成一个`Servlet`，我们实际上请求的是`Servlet`，是这个`Servlet`向我们进行回复，这个`Servlet`向客户端打印出我们想要的内容，这个内容来自于`JSP`页面中的`HTML`部分。  
设置`JSP`回复的编码格式：  
>
	<%@ page language="java" import="java.util.*" pageEncoding="gbk"%>
	
这样设置了`JSP`中的编码格式之后，由其转译的`Servlet`中编码格式同样会发生改变。  
c.在`HTML`文档中的`meta`标记中进行设置，告诉浏览器使用什么编码集进行解读。  
实际中，我们都只在`JSP`中修改编码格式。a、b两种方式都几乎不用。  

####客户端向服务器发送消息解决中文乱码问题。  
a.`Servlet`接收客户端请求的情况。  
解决方法如下：  
A. 硬编码：  
在`request.getparameter()`获得请求的参数之后，将获得的结果进行硬编码成我们想看到的编码结果。
>
	String newStr = new String(request.getparameter("name").getBytes("ISO-8859-1"),"gbk");//这样就将服务器用ISO编码读得的字符串硬编码成gbk编码的newStr。也就是说Tomcat默认是用ISO编码读取的，但是浏览器是用gbk编码发送的，所以要通过硬编码的方式将Tomcat读取的内容还原成浏览器所发送的内容。
	
这种硬编码的方式对`doGet()`与`doPost()`都有用。*繁琐但是完全有效*。  
B. 只对`doPost()`有效的方法，更方便。在`request.getparameter()`前设置`reques`的编码方式：  
>
	request.setChracterEncoder("gbk");//在getparameter()之前就设置编码方式，必须在其之前设置的。这个是用于客户端向服务器发送消息时用的。而不是response用的那个，那是服务器向客户端发送消息时用的。
	request.getparameter("name");  
	
C.只对`doGet()`方法有效的方法。修改`Server.xml`文件	。  

b.`JSP`接收客户端请求时进行设置。其和`Servlet`中的方式一样。  
c.浏览器发送请求时进行设置。

纠正一个错误观点：`Enum`关键字与`Class`关键字并不是同级的，`Enum`包含了**Class与集合**的特征。`Enum`还表示一个集合，其是几个类对象的集合。 
如， 
> 
	public Enum Seasons
	{
		Spring,
		Summer(),
		Autumn,
		Winter();
		Seasons()
		{
>	
		}
	}



#Java Web中的MVC
在`Java Web`项目中有两种架构，**模型1**与**模型2**。  
模型2就是使用了`MVC`架构。`MVC>=模型2`。  
模型1的架构见图`Java_Web_Model1.png`：  
模型1中，客户向服务器中的`JSP`发送请求，服务器内`JSP`向`JavaBean`发出请求，`JavaBean`向数据库发出请求，数据库将内容返回至`JSP`，`JSP`返回至客户端进行显示。`JavaBean`的作用就是持久化层，实际作用就是`DAO`层  

模型2的架构见图`Java_Web_Model2.png`：  
模型2中，客户服务器中`JSP`发送请求，仅仅是请求出一个初始页面用于显示表现层，模型2中的`JSP`的作用就是*表现层*。`JSP`中不涉及实际业务的处理，只是一个显示作用。然后，通过`JSP`的页面显示，客户直接向`Servlet`发出请求(如、通过`JSP`显示页面中的一个提交按钮进行发送请求)，`Servlet`的作用就是负责流程逻辑控制，实际上就是*业务层*。`Servlet`再向`JavaBean`进行请求，`JavaBean`就是`DAO`层。`DAO`层向数据库发送请求，数据库作出回应，发送到`Servlet`中。`Servlet`再请求`JSP`，`JSP`对`Servlet`中的结果予以显示。  

但是模型1已经在现实中被淘汰了。其采用了所谓的`JavaBean`，实际上就是一个`Java`类。  
实际中都是使用模型2做`Java Web`程序。  
`Java Web`中使用`MVC`协作，如图`MVC_Model`、`MVC_Coperation`所示：  

软件结构中有3层：表示层、业务层、持久化层。是一种设计思想，不是设计模式。  
##MVC框架中的构件
`MVC`架构中的组件，见图`MVC_Component.png`：  
`Model`：核心部分，存储封装数据。  
`View`：外观，与用户交互。  
`Controller`：枢纽，响应请求，处理跳转，使模型与视图保存一致。`Controller`的功能就是将前二者连接起来。一个构件模块只做一件事。

我们自己写的`MVC`框架会有一些缺点。  
例如，`TMS`系统中为了显示数据集合我们自己写了一个`Model`，使用了`MVC`的思想，但是是有缺点的。  
所以实际应用中我们要大量使用别人写的`MVC`框架。如`Structs1`、`Spring MVC`等  

##Java Web中所用的框架
表现层所用的框架有`MVC`框架还有`JSF`框架，见图`Presentation_Layer_Frame.png`：  

注意：`JSF`框架不是`MVC`框架，它是基于事件处理的框架，就好像我们在`AWT/Swing`中使用的事件监听，就是基于事件处理的框架。

#Java Web中与用户进行交互
---
##基础知识
会话：我们将每一个网站视为一个`Web`应用程序。那么我们从登陆这个网站到离开这个网站关闭其所有的页面的过程称为与此网站的一次会话。  

`Http`协议创建的连接不是长久保持的连接，不像`C/S`架构的软件那样，客户端与数据库之间的连接是可以长久保持的。而`Http`协议建立的`B/S`连接是不会在客户端与服务器之间长久保持的。  
同时`Http`协议创建的连接时*无状态*的。所谓的无状态就是：这个连接不能存储下其所连接的客户端的私有信息。也就是说，每次客户端与服务器通过`Http`协议进行连接、断开之后，这个连接并不能保存下这个客户端的私有信息。这就称为*无状态*，而*有状态*就是可以存储客户端的私有信息。  

当我们登陆一个网站(`Web app`)以完成我们的任务时，在同一次会话中发生多次请求。我们需要对这个`Web App`发出多次的请求才能完成任务。  
例如，当我们登陆一个网站购买东西的时候，就需要多次点击多次向网站`App`发送请求，而由于`Http`请求的特征，每次操作点击都是发送新的请求，发送新请求都是一次新的连接，由于`Http`协议的会话不能存储每次连接的客户端信息，那么每一次的新的连接的账号密码是从哪里来的？要知道我们并没有每一个操作要求输入账号密码。也就是说，一次登录之后，后续的所有操作都能归于这个已登录的账号，而不至于归于其他账户，这是怎么在数据库账号不断重连、断开的情况下，储存第一次登录的账号、密码而在后续阶段用这个账号密码进行重新自动连接登录的？后续的每一次操作实际上在数据库端都是进行了重新连接，这个重新连接的账号密码我们并未重新输入，那么是从哪里来的？  
`Answer`：就必须使用会话跟踪技术，使得每个客户端的信息能够被记住，这样无论其请求多少次，每次新的请求时都可以利用被记住的客户端信息进行重新连接。

##会话跟踪技术
那么怎样保存客户端与服务器连接过程中的客户端私有信息？就是使用会话跟踪技术。其用于保存同一时刻不同客户端会话的私有信息以使得可以将各个客户端分别开来。并将这个客户端的信息储存起来以用于以后的每次请求时的重新连接中。  
实现会话跟踪技术有两种：  
###Session
这是重点。所谓的`Session`就是：由服务器分配给客户端的用于保存客户端私有信息的**内存空间**。这个`Session`的编号就是`SessionID`，`SessionID`实际上就是这块内存空间的首地址。`Session`是由`Web Server`进行分配。当一个客户端第一次与这个服务器相连接也就是第一次会话时，就会给其分配一个`Session`，当第二次进行会话的时候，该客户端就可以通过`SessionID`继续使用以前分配给它的`Session`进行私有信息的存储。同时为了提高服务区端内存空间的利用率，就会为`Session`设置过期时间，`Tomcat`设置为30min，过时之后，就会进行回收。  
`Session`的作用是：储存客户端第一次登录输入的账号、密码等信息，以用于在此次会话中接下来的操作里的请求所需要的重新连接数据库。  
`Session`存储是面向无状态连接的`Http`协议会话的有状态的有益补充。  
当然`Session`是处于服务器中的一块内存空间，但是`SessionID`是储存于浏览器中，客户端可以通过浏览器中存储的`SessionID`来获得储存于服务器端的`Session`。  

####客户端怎样保存SessionID
a.`Cookie`的方式：这是`Servlet API`规范要求我们使用的方式。其运行机制对于我们而言是透明的，服务器怎样向客户端发送了`Cookie`我们不用管，`Cookie`中怎样保存了`SessionID`我们也不用管，下一次请求的时候，客户端怎样从`Cookie`中取出`SessionID`发送给服务器端我们也不用管。我们可以显式设置`Cookie`。当客户端禁用了`Cookie`的时候，关于`Cookie`的操作就不能用了。  
b.`url`重写的方式，当禁用了`Cookie`的时候，就可以使用`url`重写来保存`Session`。  
c.隐藏表单域的方式，不推荐使用。
####服务器中的Servlet中怎样获得服务器为该客户端所开辟创建的Session
在客户端第一次与服务器进行连接的时候，就为该客户端开辟了一个`Session`，返回给客户端进行储存的是`Session`的句柄`SessionID`，具体怎样开辟、储存我们都不用管。我们只需要在服务器端获得这个`Session`，并使用这个`Session`中所存储的客户端信息(例如账号密码)等信息即可。通过方法`request.getSession()`即可在`Servlet`或者`JSP`中获得为该客户端所开辟构建的`Session`对象。
####服务器中的JSP中怎样获得服务器为该客户端所开辟创建的Session
`JSP`中通过内置对象直接使用`session`，`session`是`JSP`中的一个内置对象。  
还可以通过`request.getSession()`获得`Session`对象。  
####Session对象常用方法
`setAttribute(String key,String value)`:向`Session`中存储对象。使用该方法可以将用户登录账号、密码存储起来。  
`getAttribute(String key)`：从`Session`中获取对应的对象。  
`removeAttribute(String key)`:从`Session`中删除对应的对象。  
`getAttributeName()`：获得`Session`中存储的`key`值集合。  
`invalidate()`：强制使`Session`过期，用于安全退出，保证`Session`的安全。

####Session的生命周期
第一次请求时创建`Session`。  
`Session`消亡：  
1. 退出对话之后，过了生命周期即消亡。  
2. 调用`Session`的`invalidate()`方法。  
3. `Web Server`停止运行。  

一个客户端的`Session`在这整个会话中对于这个客户端的所有请求都有效。也就是说，`Session`在这个会话中的多次请求中是可以共享的，第一次请求中存储在该`Session`中的信息是可以被这次会话中的其他请求使用。  
例如，在一次会话中，第一次请求一个`Servlet`，服务器端为这个客户端创建了`Session`，并且在这个请求中，于`Session`中存储了客户端的账号、密码。然后，客户端在第二次请求一个`JSP`，那么就可以在这个`JSP`中获得第一次请求中存储在`Session`中的账号、密码。  

注：`request`的`Attribute`通过请求转发是在一次请求中多个`Servlet`中共用(就是将本次请求的参数在各个`Servlet`间进行共用，多个`Servlet`进行回应。保存于`request`中的`Attribute`中的参数的存活期仅在于本次请求，时间短)，而`Session`的A`ttribute`是在多次请求(同一次会话)间共用(有多次请求，各个请求之间可以共用，不管各个请求各自请求`Servlet`还是`JSP`均可。保持于`Session`中的参数的存活期是很多次请求，时间长)。  

请求转发中的`request`与`Session`的不同点在于：存储共享数据的生命周期不同，共享对象不同。使用范围不同。请求转发是一次请求一个存储，`Session`是一次会话一个存储，一次会话包括了多个请求。如果能够用请求转发就能共享的数据，就使用请求转发即可，而用不着`Session`，为了节省内存空间。  
`Session`使用代码实例：  
在`Servlet`中向`Session`中储存客户端数据：
>
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
		{
			//返回给客户端的Session
			HttpSession s = request.getSession();
			s.setAttribute("name", name);
		}
		
在其它`Servlet`中取出`Session`中储存的客户端数据：
>
	public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException 
		{
			HttpSession s = request.getSession();
			System.out.println(s.getAttribute("name"));
		}

在`JSP`中取出`Session`对象(内置对象为`session`)中储存的客户端数据：
>
	<body>
		<%=session.getAttribute("name")%>
	</body>
	
####application对象
其实际上是一个对象名。是`ServletContext`类的实例对象，但在`JSP`中也是是一个内置对象(与`session`一样)。  
`Session`对象只能被同一个客户端使用。有限制。`application`对象可以被多个客户端共享。其范围最大。只要整个程序(包含多个`Servlet`)启动，即创建一个`application`，这是一个单态类，为整个程序而创建的。  

`application`使用代码实例：  
在`Servlet`中通过以下方法获得`application`对象。  
>
	ServletContext application = super.getServletContext();//获得ServletContext对象application
	或者
	ServletContext application = super.getServletConfig().getServletContext();//也可以获得ServletConText对象application
	ServletContext application = request.getServletContext();//也可以获得ServletConText对象application

在`JSP`中：`application`用于在*多个客户端之间*共享参数`sum`,这个`application`存储空间是属于整个`Web App`的。
>
	<body>
	    <% 
	    	Object sums = application.getAttribute("sum");
	    	int sum= 0;
	    	if(sums==null)
	    		sums="0";
	    	sum=Integer.parseInt(sums.toString());
	    	sum++;
	    	application.setAttribute("sum", sum);
	    %>
	    sum:<%=sum %>
	</body>

在`JSP`中，`application`就是一个内置对象。可以拿来直接使用。

####Request、Session、Application三者对比
见图`Request_Session_Application_Contrast.PNG`所示：  
上述三者均可用于`Java Web`中的数据存储共享，那么具体该存放在哪一个里面的原则是：数据能储存在小范围内解决问题，就不要存放在大的范围内。  

###URL重写与Cookie
####URL重写
当客户端或者浏览器禁用`Cookie`的时候，那么我们的`SessionID`放在哪里？  
可以使用`URL`重写的方法来在禁用`Cookie`的时候使用`Session`。
`URL`重写使用实例：  

注：当我们想从一个页面中跳转到另一个页面(或者回到现在的页面)的时候，可以在`Servlet`中使用重定向。
####Cookie
客户端信息可以储存在服务器开辟的空间`Session`中。  
也可以储存在客户端开辟的空间`Cookie`中。  
要注意：无论是在`Servlet`还是`JSP`中，第二次使用`Cookie`都需要从`request`中获得，因为第一次是创建`Cookie`，创建`Cookie`后再将其发送至客户端，下一次使用`Cookie`的时候就需要客户端将`Cookie`与`request`一起发送给`Servlet`或者`JSP`。  

`Cookie`使用代码示例：  
在`Servlet`中使用`Cookie`的示例，将客户请求中发送过来的信息存储到`Cookie`中：  
>
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
		{
			String nameInfo=request.getParameter("name");
			//创建Cookie，然后给其添加内容，注意，存储几个数据，就要创建几个Cookie
			Cookie c = new Cookie("nameinfo",nameInfo);
			Cookie d = new Cookie("ageinfo","15");
			//设置Cookie的存活时间
			c.setMaxAge(10000000);
			d.setMaxAge(10000000);
			//将我们创建好的所有Cookie都发送给客户端
			response.addCookie(c);
			response.addCookie(d);
>			
			//重定向跳转到新的页面上
			response.sendRedirect("MyJsp.jsp");
		}

`JSP`中从`Cookie`中取出存储内容的示例，注意，当我们重定向至`JSP`的时候，`JSP`中同样会收到`request`对象，这里的这个`request`对象中包含了`Cookie`对象，也即是说，客户端发送请求的时候，会将`Cookie`一起发送给服务器：  
>
	<body>
	    <% 
	    	//取出Cookie
	    	Cookie[] co = request.getCookies();
	    	//读取Cookie中的内容。
	    	for(Cookie c:co)
	    	{
	    		System.out.println(c.getValue());
	    	}
	    %>
	</body>


关于Cookie，与Session、application这些不同的是，Session、application都只有一个，无论存几个数据，都只有一个
但是对于Cookie，存几个数据，就要new几个，而且每一个都要使用response进行发送。  

使用`Session`、`Cookie`存储用户信息的比较，见图`Session_Cookie_Contrast.PNG`：

#Java Web中的JSP
---
`JSP`技术是建立于`Servlet`之上的。  
`JSP！=Java+DHTML`  
`JSP页面=Java+DHTML`  
`JSP`实际上就是一个`Servlet`类。  
`JSP`代码运行原理如图`JSP_Fundamental.PNG`所示：  
第一次运行的时候：`.jsp`首先转译为`.java`(也就是`Servlet`代码)，再编译为`.class`代码。  
第二次运行的时候：直接请求`.class`代码。  


##JSP组成
`JSP`页面包括：  
1. 注释：`HTML`注释、`Java`注释、`JSP`注释。  
2. 模板：就是界面原型，界面显示的外形，美工关心的。就是`HTML`部分的内容。  
3. 元素：脚本元素、指令元素、动作元素。  

一个`JSP`页面，无论其开始时怎么写的，在运行之后都会被转换为一个`Servlet`类。JSP页面中的各种内容会被转化为这个类中的各个部分，如，类中成员变量、类方法中的变量。
###脚本元素
脚本元素有3种：声明部分，小脚本，表达式。  
>
	<%!
		//声明部分，声明变量与方法，使用！作为标识，表明该部分转换为Servlet后直接变为Java代码。此处声明的变量是全局的，转换后作为类中的成员变量。
		int a = 1;
		int sum(int a,int b)
		{
			return a+b;
		}
	%>	
	<%
		//小脚本，不用标识，在转换为Servlet之后直接变为Java代码。但是与声明部分不同的是：小脚本中声明的变量是局部的，转换后放在某一个方法中。
		int reuslt = sum(10,20)；
	%>
		//表达式，表达式部分使用=进行标识，表明该部分转换为Servlet之后放在out.write()之中，直接在HTML中显示。将JSP中的变量(无论是类成员变量还是方法的局部变量)内容在print里面显示出来。注：其后无分号。
	<%=
		result
	%>
	
`JSP`转换代码示例：  
`JSP`代码中脚本元素：  
>
	<%!
		String path1 = "hi";
		String basePath1 = "hello";
		int sum(int a,int b)
		{
			return a+b;
		}
	%>
>									
	<%
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		int sum = sum(1,2);
	%>
>
	<%=path1 %>
	
转换为`Servlet`之后的类代码：  
>		
	public final class index_jsp extends org.apache.jasper.runtime.HttpJspBase
	    implements org.apache.jasper.runtime.JspSourceDependent 
		{	
			//此三部分来至于JSP中的声明部分
			String path1 = "hi";
			String basePath1 = "hello";
			int sum(int a,int b)
			{
				return a+b;
			}
			public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
	        throws java.io.IOException, javax.servlet.ServletException 
			{
				//此三部分来自于JSP的小脚本部分
				String path = request.getContextPath();
				String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
				int sum = sum(1,2);
				//此部分来自于表达式部分，将JSP中的变量(无论是类成员变量还是方法的局部变量)内容在print里面显示出来。
				out.print(path1 );
			}
		}

###指令元素
以`<%@ %>`为标志。所谓的指令元素的作用，就是告诉`Web`服务器，在编译本`JSP`文件为`.class`文件的时候需要哪些准备。有`page`指令、`include`指令、`taglib`指令，见图`Commander_Elements.PNG`所示：  

####page指令
在`JSP`的任何地方、以任何顺序，一个页面中可以包含任意数量的`page`指令。`page`指令的作用：设置本`JSP`页面的属性状况。如、文本字符集、编译导入包等。  
除了`import`，任何的属性/值对只能出现一次。  
`page`指令的属性有:`language、import、contentType、errorPage、isErrorPage`，见图`Page_Attributes.PNG`所示:  
`errorpage`属性用于设置`404`或者`500`错误时跳转处理页面。  

`page`指令使用代码示例：  
>
	<%@ page language="java" import="java.util.*" contentType="text/html; charset=gbk" errorPage="MyError.jsp"%>
	
####include指令
当网站的多个页面中具有某些相同元素，例如各个页头是相同的，那么为了节省代码，可以使用`HTML`中的框架，将相同的那一部分拆开为一个单独的部分进行使用。  
也可以使用`JSP`中的`include`指令，将各个页面中的相同部分写成一个页面，然后再各个页面中使用`include`指令将他们包含进来。  
`include`指令用于在运行的时候将其他`HTML`文档或者`JSP`页面嵌入到本`JSP`页面。  
`include`之类的属性有：`file`属性。  
但是要注意页面中不要定义了相同变量，也就是被嵌入的页面与嵌入页面中不要有同名变量的定义。  
`include`指令使用代码示例：  
>
	<%@ include file = "文件名" %>//将其他的一个文件放入本文件中。

####taglib指令

###动作元素
动作元素是鸡肋知识，没什么用了。主要为模型1服务，模型2基本不用。主要是方便在`JSP`页面中使用`JavaBean`。  
以`<jsp: >`为标志。  
动作元素见图`Action_Elements.PNG`所示：  

##JSP的隐藏对象
**隐藏对象与脚本元素中的小脚本合用，威力无穷。**  
在`JSP`的*脚本元素*中可以直接使用`JSP`的隐藏对象。  
这样就可以在`JSP`中与`Servlet`一样使用`request`、`response`这些元素对象，进而可以使用`Session、Cookie、application`这样的用于会话跟踪技术，还有请求转发、重定向这些技术了。使得二者功能上的共通。  
隐藏对象无需使用者声明、创建。由容器维护、管理。  
例如，`session`、`request`、`application`对象就可以在`JSP`的脚本元素(*除了声明之外*)中使用。  
为什么不能在`JSP`的声明中使用？  
`Answer`：因为声明部分转译为`Servlet`类中的成员变量部分。而使用`JSP`隐藏对象就是为了使用这些隐藏对象的各种方法，但是在类的成员变量声明部分是不允许出现方法调用的，例如，在类的成员变量声明部分不能出现`int a= sum(1,3)；`这样的语句，这样的语句只能出现在类方法中，也就是对应的`JSP`的小脚本部分。  
所以，这些隐藏对象只能出现在`JSP`的小脚本部分。这是有转译后的`Servlet`类中的内容决定的。  
`JSP`的隐藏对象见图`JSP_Inner_Object.PNG`所示：  
使用代码示例：  
	<%
		//在小脚本中使用隐藏对象，使request对象发挥与在Servlet中相同的效果
		String strName = request.getparameter("name");
	%>
	<%=
		//显示变量
		strName
	%>	

注：关于`response`对象的方法使用，我们很少再使用`out.print()`来向客户端发送内容。而是直接使用重定向的技术：`response.sendRedirect();`转发至我们想要发送的内容的那个页面即可。而不要直接输出。

##JSP中异常处理
若发生异常，可以跳转至异常显示页面。这里就可以使用指令元素中的`page`指令，使用其`errorpage`属性。以处理`404`或者`500`错误。

#Java Web中的过滤器
----
在`Java Web`中，除了`JSP`、`Servlet`可以接收客户端请求，处理客户端请求之外，过滤器也可接收客户端请求。  
过滤器是在`Servlet`、`JSP`响应前、后进行动作、添加新功能的组件。  
我们的一个`Web App`中有多个`Servlet`或者`JSP`，其中有些`Servlet`中的某些操作是相同的。那么我们可以将这些操作提取出来成为一个新的类，在客户请求到达后面的那些`Servlet/JSP`之前就将那些相同的操作通过新的类予以实现。这个新的类就发挥了过滤器的作用。也就是说在那些`Servlet/JSP`对客户请求做出响应之前就对之予以过滤。就是精简了`Servlet/JSP`的功能，减小代码冗余。过滤器作为一个预备处理功能。  
注意:过滤器不仅可用于`Servlet`还可以用于`JSP`。

开发一个过滤器需要实现一个接口(`Filter`接口)。  
在做项目时，最多的是使用前过滤，也就是在过滤器中的`doFilter()`之前完成想要的过滤功能(操作)，很少使用后过滤。  

过滤器使用步骤：
1. 实现`Filter`接口。重写`doFilter()`方法。  
2. 配置`Web.xml`文件，配置方法与`Servlet`类似。因为过滤器就是一个类，和`Servlet`一样都是一个类，所以必须去`Web.xml`文件中进行注册，这样才可以如`Servlet`一样被使用(接受客户端请求)。  

过滤器使用示例：  
未使用过滤器的示例：  
`Servlet` `xizhao`代码：  
>
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("tuoyifu");
		System.out.println("xizhao");
		System.out.println("chuanyifu");
	}
	
`Servlet` `anmo`代码：
>
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("tuoyifu");
		System.out.println("anmo");
		System.out.println("chuanyifu");
	}

在这两个`Servlet`的操作中，有一些操作是一样的，也就是有代码冗余。  

使用过滤器的示例：  
`Servlet` `xizhao`代码：  
>
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("xizhao");
	}
	
`Servlet` `anmo`代码：  
>
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
	{
		System.out.println("anmo");
	}
	
过滤器`PreFilter`的代码：
>
	//过滤器抽取了前面两个Servlet中相同的操作
	public class PreFilter implements Filter 
	{
		@Override
		public void init(FilterConfig arg0) throws ServletException 
		{
		}
		@Override
		public void doFilter(ServletRequest request, ServletResponse response,
				FilterChain filterchain) throws IOException, ServletException 
		{
			//此句就是将在Servlet中的冗余代码提取出来，也就是前过滤，在Servlet操作执行之前即执行的那些代码
			System.out.println("tuoyifu");
			//doFilter()方法是一个分界线，之前的代码表示是之前过滤的，之后的代码表示是之后过滤的。该方法表示请求转向Servlet的doPost()或者doGet()方法进行执行。
			filterchain.doFilter(request, response);
			//此句就是Servlet中的方法执行完成之后才继续执行本Filter中的方法。
			System.out.println("chuanyifu");
		}
		@Override
		public void destroy() 
		{
		}		
	}
	
`Web.xml`配置：作用是如`Servlet`一样进行注册，以使得我们可以使用这个`Filter`  
>
	<filter>
		<filter-name>PreFilter</filter-name>
		<filter-class>com.trilever.PreFilter</filter-class>
	</filter>
	<filter-mapping>
		//以下配置使得此Filter对于两个Servlet都可自动调用。
		<filter-name>PreFilter</filter-name>
		<url-pattern>/xizhao</url-pattern>
		<url-pattern>/anmo</url-pattern>
	</filter-mapping>
	
使用过滤器的代码示例：  
>
	<body>
		//这样直接调用xizhao这个url之后，就会使用这个Filter。当然也执行了xizhao这个Servlet中的操作内容。
		<form action="xizhao" name="form1">
			<input type="text" name="mingzhi">
			<input type="submit">
		</form>
		//这样直接调用anmo这个url之后，就会使用这个Filter。当然也执行了anmo这个Servlet中的操作内容。
		<form action="anmo" name="form2">
			<input type="text" name="mingzhi2">
			<input type="submit">
		</form>
	</body>
注：在`Web.xml`对这个`Filter`进行注册之后，在使用注册`url-pattern`进行调用的时候，不再是直接去调用`Servlet`了，而是直接调用注册的`Filter`，然后在这个`Filter`中调用`Servlet`中的操作方法。  
如此，即将两个`Servlet`中的相同部分抽取到过滤器中进行自动调用(因为已经在`Web.xml`文件中进行配置)。

由前面可知，当我们的`Servlet`需要接收、处理客户端请求，就需要处理中文乱码的问题。对于很多`Servlet`都需要这一操作，那么就可以将中文乱码处理部分进行抽取，作为一个中文乱码处理过滤器。然后在`Web.xml`中配置对于所有的`Servlet`都适用。就可以解决所有的`Servlet`的中文乱码问题。  

注意：在`Filter`中的`init()`方法的参数`FilterConfig`可以用于从`Web.xml`文件中读取我们预先设置的数据，就好比在`Servlet`中，`init()`方法中的`ServletConfig`参数也可以从`Web.xml`文件中读取预先配置好的数据，见前面的`ServletConfig`部分的介绍。这些数据可以是整个项目的配置信息，当需要修改的时候，可以很方便地在`Web.xml`中修改。在全局的`Servlet`都可以读取使用。`Filter`中`init()`方法的参数`FilterConfig`的常用方法见图`FilterConfig.PNG`所示：  

##Filter的生命周期
`Filter`的生命周期见图`Filter.PNG`所示：  

##Java Web中过滤器链
所谓的过滤器链：就是存在多个过滤器，一起发挥作用。当然过滤器既可以过滤`Servlet`也可以过滤`JSP`。  
那么各个过滤器执行流程是怎么样的？  
`Answer`:前过滤器执行顺序(也就是`filterchain.doFilter(request, response);`方法之前的代码)是依据`Web.xml`中的`mapping`顺序确定的。当其中一个过滤器中的前过滤器执行完之后，如果其后面还有过滤器那就先执行其后面的过滤器的前过滤器部分，如果后面没有了过滤器，那就执行`Servlet`中的部分。执行完`Servlet`之后，再转向过滤器中的后过滤器部分，当然，各个后过滤器部分的执行顺序与前过滤器部分的顺序相反。执行流程见图`Filter_Chain_Process.PNG`所示：

在过滤链的使用过程中，怎样实现`Filter`之间的链式调用？  
`Answer`:依靠在`Web.xml`文件中对各个`Filter`的注册，要实现链式调用，就需要一环套一环的`Filter`的`url-pattern`配置。牵一发而动全身。  

###在过滤器联中使用适配器模式
在实际使用中，过滤器中的`init()`方法与`destory()`方法往往是没有用的，只需要进行空实现即可。但是当要大量使用过滤器的时候，太多的空实现就会导致代码冗余。此时就可以使用适配器模式予以解决。先创建过滤器适配器，对`Filter`类进行空实现。然后所有的过滤器都继承自这个适配器即可不必实现全部的方法。  

注意：字符串处理方法`split()`，如果是用"."进行分割，必须写成"\\."才可以。  
前面我们做了一个用于处理中文乱码的过滤器，现在创建一个客户端地址过滤器，就是限制某些地址访问我们的`Servlet`。  
地址过滤器代码示例：  
>
	public class RemoteAddressFilter extends FilterAdapter 
	{
		@Override
		public void doFilter(ServletRequest request, ServletResponse response,
				FilterChain chain) throws IOException, ServletException
		{
			String addr = request.getRemoteAddr();
			String[] strArray = addr.split("\\.");
			int lastindex = Integer.parseInt(strArray[3]);
			if(lastindex>10&&lastindex<50)
				response.getWriter().println("you are not authorized to this page");
			else
				chain.doFilter(request, response);			
		}
	}

还有客户端登陆账号过滤器示例：  

网站`log`修改过滤器示例：  


##AOP-面向方面编程
什么叫做方面：例如我们的代码需要解决中文乱码的问题。那么这个问题就是一个方面。  
我们将处理中文乱码的那部分代码提取出来，构成一个过滤器，这就是面向方面编程，称为`AOP`。也叫面向切面编程。  
就好像过滤器一样，每一个过滤器都像在请求传递到`Servlet/JSP`之前的路上的切面，将必要的东西予以过滤、切除。称之为面向切面编程。  
过滤器、拦截器(后面`Structs2`再讲)的作用就是：让后面的`Servlet/JSP`负责他们自己的工作，只做他们自己的工作即可。其他的工作由过滤器、拦截器这些东西完成。  

#Java Web中的EL
`Expression Language`:表达式语言。在`JSP`页面中使用。   
以前是`JSTL`(`JSP`标准标签库)的一部分。现在`JSF`也将其纳入。  

`EL`的作用:主要是用于输出、显示变量的值(见下面的例子)，与`JSTL`组合代替`JSP`页面中的脚本元素与动作元素。  
主要是使用`EL`中的隐式对象来取出我们储存在`page`、`session`、`application`、`request`这些空间中的变量值。以前都是在`JSP`的小脚本中使用`request`、`session`、`application`等这些隐藏对象来获得其储存的变量的值，如:  
>	
	<% String name = request.getAttribute("name");
	   String age = session.getAttribute("age");
	%>  
以前都是使用小脚本来取得存储的客户端数据。现在可以使用`EL`的隐式对象来获得这些客户端数据了：  
>	
	${requestScope.name}//取出request中存储的name变量。  
	${sessionScope.gen}//取出session中储存的gen变量。  
	${applicationScope.age}//取出application中存储的age变量。  
	
语法:${EL语言}  

##EL的隐式对象
`EL`中的隐式对象，实际上就是将`JSP`中相应的隐式对象以`EL`语法重新包装，方便使用。  
注意：`request`、`application`这些9个对象都是`JSP`中的隐式对象。这里的各种`Scope`才是`EL`中的隐式对象。
见图`EL_Hidden_Object.PNG`所示：  
`EL`中隐式对象如下：  
`pageConText`:网页运行环境的相关信息。  
`pageScope`：取得本`page`范围内特定属性的值。  
`requestScope`：取得本`request`范围内特定属性的值。  
`sessionScope`：取得本`session`范围内特定属性的值。  
`applicationScope`：取得本`application`范围内特定属性的值。  
`param`对象:作用相当于`request.getParameter()`,如、获得`form`中某个元素提交返回的值，但只返回一个单值。获得`request`传过来的单一参数值。  
`paramValues`对象:作用相当于`request.getParameterValues()`,如、获得`form`中的`checkBox`返回的值，`checkBox`返可以回多个值。获得`request`传过来的多参数值。  
`cookie`:获得`request`传过来的`cookie`。  
`initParam`:获得网页运行的初始参数值，就是在`Web.xml`中传过来的值。  
以上，`param`、`paramValues`与`request`的区别在于，如果获得的是`null`，那么`param`会显示出空白，而`request`会显示出`null`。
注意：这两个隐式对象只在模型1中才使用。在模型2中没有使用。此二者无需研究。
示例：  
>
	${param.name}//返回form中某个元素对象的值。
	${param.pwd}
>	
	${pageScope.username}:取出本page范围内的username变量。  
	${requestScope.username}:取出request中存储的username变量。  
	${sessionScope.username}:取出session中存储的username变量。  
	${applicationScope.username}:取出application中存储的username变量。  

原来设置`page、request、session、application`范围内的某个变量值：
>
	pageContext.setAttribute("pagename","pagevalue")；
	request.setAttribute("requestname","requestvalue");
	session.setAttribute("sessionname","sessionvalue");
	application.setAttribute("applicationname","applicationvalue");

获取`page、request、session、application`范围内存储的某个变量值：
>
	page.getAttribute("pagename")；
	request.getAttribute("requestname");
	session.getAttribute("sessionname");
	application.getAttribute("applicationname");
	
以上使用`get`方法从变量中取值，相当于以下使用`EL`中的`Scope`隐式变量：  
>
	${pageScope.pagename}//取出page范围内的pagename变量。  
	${requestScope.requestname}//取出requestScope范围内的requestname变量。  
	${sessionScope.sessionname}//取出sessionScope范围内的sessionname变量。  
	${applicationScope.applicationname}//取出applicationScope范围内的applicationname变量。

使用`EL`的优点是可以节省代码。  

注意：当我们在创建一个实体类的时候，前3个字母都不要大写，否则在`Structs`里面会出现问题。

对于一个实体类对象而言(如，`Student`类的对象`mStudent`)，`EL`的用法还包括：  
>
	${requestScope.mStudent.name}//取出request中存储的mStudent变量的name成员变量值。  

甚至当`Student`类中包括了`birth`类成员变量，`birth`类包括`year、month、day`成员变量，还可以使用：  
>
	${requestScope.mStudent.birth.year}//取出requestScope范围内的mStudent变量的Student对象的birth成员变量的year成员变量值。  
	
甚至还有用法如下：  
>
	${mStudent.birth.year}//取出requestScope范围内的mStudent变量的Student对象的birth成员变量的year成员变量值。这里我们没有指定requesScope这个范围，会自动从pageScope向applicationScope范围进行对这个对象的查找。也就是说，如果在pageScope范围内就有mStudent这个变量，那么在pageScope中查找到即停止查找，不再向下继续查找。  
以上这种用法这就是`EL`的功能强大之处，实际上调用的都是`getAttribute()`方法。  

`EL`使用代码示例：  
>
	<% 
		appliction.setAttribute("name","wt");
		application.setAttribute("age","24");
	%>
	//直接使用application里面存储的变量
	${name}
	${age}
	//输出字符串
	${"hello"}
	
关于4个`Scope`隐式对象的，如图`Scope_Objects.PNG`所示：  

`EL`中还有一个隐式对象`pageContext`。其包含本页面的所有信息，如、`request`对象、`session`对象。通过其拥有的`request`对象就可以获得`ServletPath`变量值等。  
`EL`中的重点就是这4个`Scope`隐式对象、1个`pageContext`隐式对象与`param、paramValues`隐式对象。后二者是为模型1服务的，所以使用少。重点是前面四个`Scope`。  
注意一个问题：  
在`JSP`中，有9个隐式对象：  
`request`:取得客户端提交的数据与系统的信息。  
`response`:响应给客户端信息。  
`application`：记录与处理所有上网者共享的数据。  
`session`：记录与处理上网者个人的数据。  
`page`：代表目前这个`JSP`网页对象。  
`pageContext`：访问与处理系统执行时期的各项信息。  
`config`：取得`JSP`编译之后`Servlet`的信息。  ‘
`out`：控制数据输出的操作。  
`exception`:异常处理机制。  
其中，`pageContext`不仅自己可以存储数据，而且，其还封装了其他的8个对象。也就是说，`pageContext`对象可以设置、获取、存储自己的变量值，而且其还可以获得其他的八个隐式对象，进而通过这些获得的对象去修改、获取、设置、存储这些变量中存储的变量值。  
`page`、`pageContext`、`config`这三个对象可以用于访问网页执行期间的各种环境信息。同时将当前访问网页当做一个对象进行操作。  

>	
	PageContext.APPLICATION_SCOPE//获得application对象
	PageContext.SESSION_SCOPE//获得session对象
	PageContext.REQUEST_SCOPE//获得request对象
	PageContext.PAGE_SCOPE //获得page对象
	
总之，`PageContext`具有最大的域范围，包含了其他的八个域。  
   
注：无论是网页中out对象输出还是JavaSe中的输出。一般都使用了缓冲区的概念。也就是说，输出的时候，不是立刻输出到目的地，而是先输出到缓冲区，再输出到目的地。  
这里就有几个针对缓冲区的操作。无论是哪里的输出，都有这几个方法对缓冲区进行操作。  
1. `clear()`：用于清空缓冲区，如果在这个方法之前有输出内容，那么使用`clear()`方法之后，那些内容就被从缓冲区清除，不会显示在目的地了。  
如：  
>
	out.println("hello");
	out.clear();//这样，页面不会有任何的显示，因为使用了clear将缓冲区中储存的hello清空了，所以不会显示出来。
	
2. `clearBuffer()`:作用与`clear()`一样，也是清空缓冲区。但是区别在于，`clear()`方法如果缓冲区是空的，就会报出异常。而`clearBuffer()`不会报出异常。  
3. 	`flush()`：作用是清空缓冲区，但是会将缓冲区的内容全部显示在目的地。这就是它与前两个方法的不同之处。  
如：  
>
	out.println("hello");
	out.flush();//这样，页面会显示出hello字符串，因为使用了flush]将缓冲区中储存的hello显示出来。
	
##EL中的运算符
与`Java`中的运算符没有什么区别。差不多的。

##EL在实际中的应用
以后在实际的`JSP`中就不要使用`request.getAttribute()`这样的代码了，可以全部使用`EL`代码予以代替。`EL`的作用就是代替`Java`进行数据的访问操作。  
注意，无论是`JSP`中的脚本元素、指令元素、动作元素还是`EL`、`JSTL`都可以在`JSP`中的`任何地方`出现。  

#Java Web中的JSTL
`JSTL`的重要作用就是完成流程控制。与`EL`配合代替`JSP`中的脚本元素与动作元素。  
由上可知：`EL`的作用就是从`page、request、session、application`中取出各种储存的数据，而对这些数据的处理、控制就需要使用`JSTL`进行完成。  

`JSTL`:`JavaSever Pages Standard Tag Library`,`JSP`标准标签函数库。  
`JSTL`其实际上包括：
核心标签库、I18N格式标签库、SQL标签库、XML标签库、函数标签库。  
但重点掌握的是*核心标签库*。其他的都几乎不用。  
`JSTL`与`EL`一样，都是用在`JSP`页面中。  
`JSP`中的指令元素(见图`Commander_Elements.PNG`所示)中有一个`taglib`元素，示例:  

`JSTL`中的**重点**是：各种流程控制语句如`foreach、if、while`等。语法见电子书。  
实际上，无论是`EL`还是`JSTL`，其实际上都是`JSP`的一种子语言而已，目的是为了满足一定的任务与作用，例如，`EL`的作用就是为了取出、输出变量的值，而`JSTL`是为了提供流程控制语句。其作用也和`Java`中相应部分的作用是一样的。都是`JSP`中的一部分。就好比`JSP`也是一种语言一样。  
`EL`与`JSTL`的作用就是代替`JSP`中的脚本元素与动作元素。所以，我们应该将`JSP`文件中的所有的脚本元素与动作元素(当然指令元素是不能换的)换成`EL、JSTL`语句才好。`JSP`中的这两种元素都可以使用`EL`与`JSTL`予以代替。  

`tag`标签库使用代码示例：  
>
	<html>
		<%@ taglib uri="http://www.tag.net/mytag" prefix="tag"%>
			//使用tag标签
			<tag:fortag start="1" end="5">
				//执行语句……
			</tag:fortag>
	</html>

`JSTL`中核心标签库使用代码示例：  
>
	<html>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
			//使用JSTL核心标签
			//输出一个值
			<c:out value="hello" />
			</c:out>
>			
			//将变量mk的值设为hello。
			<c:set value="hello" var="mk" />
			</c:set>
>			
			//将变量mk移除
			<c:remove var="mk" />
			</c:remove>
>			
			//在此处显示一个url地址
			<c:url value="www.sina.com" />
			</c:url>
>			
			//在此处显示一个url地址,当在页面中点击这个链接的时候，会将后面的指定参数发送过去。
			<c:url value="www.sina.com" />
				<c:param value="hello" var="mk"/>
				</c:param>
			</c:url>	
>
			//在此处导入一个外部文件(注：只是导入，而不是使用，就好比java中的import功能一样),并将文件内容放在一个变量中。在本文件中就可以使用这个变量将文件内容输出了。
			<c:import url="hello.jsp" var="varString"/>
			</c:import>
>			
			//本网页被浏览是，被直接重定向到新网页。当然，这里也可以在其里面使用param标签，重定向时传递参数给新网页
			<c:redirect url="hello.jsp" />
			</c:redirect>
>			
			//使用forEach循环
			<c:forEach var="theItem" begin="1" end="10"/>
				${theItem}
			</c:forEach>
	</html>

注意：不要误以为`Session`、`request`、`application`这些里面只能放少量的数据，其可以放任何的数据，而且可以传递。例如，就可以在`request`里面放一个`map`或者`list`，再请求转发出去，在另外一个页面中进行使用。都是可以的。

##总结
对于`JSP`而言，对于用户输入的数据，不论是以何种方式予以获取，都是字符串类型。所以往往都需要装换为其他的数据类型。  
`JSP`中提供了5种构建网页内容所需的元素：  
脚本元素：有`Java`代码构成的`JSP`程序块。  
指令元素：用于网页相关信息、属性的访问与设置，包括`page(用于设置JSP的网页特性，此指令一般放在JSP最前面)、include(用于将外部HTML、JSP、文本文件加载到当前的JSP网页，可以放在JSP任何地方)、taglib(用于自定义JSP标签)`这几个指令。  
注意：使用`include`指令时，外部文件中如果有中文字符，那么在外部文件中也要使用`page`指令设置中文字符集。  

在大型`Web`应用程序中，一般都不会直接将`JSP`代码与`HTML`混杂在一起，`HTML`主要负责构建网页内容。`JSP`中的`Java`代码会被封装成类，由`JSP`网页调用。简化代码内容，将网页和逻辑层分开。  
`JSP`的脚本元素的`<%! %>`中，不仅可以定义成员变量、成员方法，还可以定义类。和`Java`中定义类是一样的，可以在其他部分进行使用，如`<% %>`，`<%= %>`里都可以使用。  
同时，为了将代码分离开来，我们可以将`Java`代码，例如脚本元素中的定义部分(类定义、成员变量定义、成员方法定义均可)，写成一个单独的`JSP`文件。如果其他`JSP`页面需要使用，就使用指令元素中的`include`指令进行导入，然后在这个页面中就可以使用已定义的类、成员变量、成员方法等都没有问题。当然如果导入了小脚本的内容也都会执行。

`request.getContextPath()`方法：获得的是`webRoot`这个目录。当我们不写目录的时候，默认的就是`WebRoot`这个目录。  

动作元素：  
`EL`元素：用于简化脚本元素的网页编写。  
`JSTL`元素：为`JSP`定义的专门提供网页制作的标签函数库。包含网页运行的所需功能，例如、循环、流程控制、文字格式化、`XML`文件处理、数据库访问等功能。相当于别人替我们定义好了的宏，我们直接使用就可以了。  

注：在`JSP`中，`form`表单里，如果表单属性的`action`没有设置，那么就会默认将本`JSP`页面作为响应页面。  
在`JSP`中，可以设置字符集格式的地方有三个。  
1. 文件头，`page`指令中的`ContentType`。  
2. `request`接收请求的字符集。`request.setContentType()`;  
3. `response`返回字符集。`response.setContentType()`;功能与`page`指令中`ContentType`是相同的。  

关于`response`的状态码，如果打开的网页正确无误，就会自动发送一个状态码，值为200。  

`response`中同样与所有的输出一样，具有缓冲区的概念。例如`flushBuffer()`方法等。就是将缓冲区的内容全部输出。  

#Java Web中的自定义标记
标记示例：  
>
	<jsp:include page="index.jsp">  
		<jsp:param value="zhangshan" name="name"/>
	</jsp:include>
	
`jsp:include`，此称为标记，`jsp`是标记的前缀，`include`是标记的后缀，中间以冒号分割。  
`page`:标记的属性，`index.jsp`是属性值。  
`<jsp:param>`称为`<jsp:include>`的子标记。  

##自定义标记
开发自定义标记的目的：借助自定义标记对应的`Java`类(标记处理器)完成某些功能，完成`jsp`页面的开发。  
其优点是：有助于模块化编程，提高程序的可复用性等。  
自定义标记的组成：
1. 自定义标记。例：`<wt:hello></wt:hello>`。  
2. 标记处理器。就是一个`Java`类，当容器解析运行至标记`<wt:hello></wt:hello>`时，会自动调用该`Java`类的相关方法，完成某项功能。  
3. 标记描述符。是一个后缀为`tld`但文档内容格式为`xml`的文件，它负责完成一个自定义标记到标记处理器`Java`类的绑定过程。

##标签处理器
标签处理器：就是创建一个类，其继承自`BodyTageSupport`或者`TagSupport`。  
其实现了两个方法：`doStartTag()`、`doEndTag()`。当容器遇到开始标记`<wt:hello>`时，就会执行`doStartTag()`方法，当容器遇到结束标记`</wt:hello>`时，就会执行`doEndTag()`方法。  

##标记描述符
创建一个`tld`文件。其实际上是一个`xml`文件，其内部将我们的自定义标记的名称与我们创建的标签处理器对应起来。以后当我们在`JSP`解析中遇到了这个标记时，会通过这个`tld`文件找到对应的标签处理器，运行其里面的`doStartTag()`方法与`doEndTag()`方法。  
注：这个文件必须放在`WEB-INF`目录或其子目录之下。  

##在JSP中使用自定义标记
在`JSP`中使用自定义标记时，要先导入标签库。使用`taglib`指令元素。  
>	
	<%@ taglib prefix="wt" uri="http://trilever.com" %>
	
使用时，在`JSP`文件中写了这个标签就会自动调用标签处理器中相应的那两个的方法。

注：自定义标记的作用，就相当于一个宏一样，通过一个标记来调用与之对应的两个方法。这样就可以实现模块化编程。将`JSP`中的代码抽取出来，放入在模块(标记处理器中的两个方法)之中。  

所谓的`JSTL`就是`Sun`公司给我们开发供我们使用的标签库。我们不用对里面的标签进行自定义，而是可以直接使用。JSTL就相当于别人已经替我们定义好了的宏。  
`Sun`公司已经将这个标签库(核心标签库)里面的标签所对应的标签处理器都写好了(放在`JSTL`的`jar`包里面)。我们直接使用`JSTL`标签即可。  
这就是为什么我们要使用`JSTL`时必须要导入`JSTL`的`jar`包，因为这个`jar`包里包含了这个标签库里的标签所对应的`tld`文件，该文件里面的就是`JSTL`标签所对应的标签处理器(里面包含了我们想使用的方法)。


#Java Web中的监听器
所谓的监听器就是：当我们想在`application`、`session`、`request`这三个对象创建、消亡或者向其中添加、删除、修改`Attribute`的时候执行某些代码，就使用监听器来完成。这就是监听器的作用，用于监听这三个对象的变化。  
##监听器的分类
1. `ServletContextAttributeListener`:监听对`application`的`Attribute`的操作，如、增加、删除、修改等操作。  
2. `ServletContextListener`:监听`application`对象的创建与消亡。  
3. 

##使用监听器步骤
a. 实现以上的那些监听器接口。实现里面的方法  
对于监听创建与消亡的监听器，需要实现`init()`与的`destory()`方法。  
对于监听`Attribute`变动的监听器，需要实现`attributeAdded()`、`attributeRemoved()`、`attributeReplaced()`方法。  
b. 在`Web.xml`中对这个监听器进行相应的注册配置。  


