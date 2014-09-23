#Model1
JSP自身没有实现HTML代码与Java代码的完全分离，网页设计人员(负责显示)和Java编程人员(负责业务逻辑)需要操作同一个JSP文件，不易维护和管理(我自己在做小的JSP代码练习的时候，在整个JSP页面中，不但有网页内容显示部分，同时还有一些包含了一些Java代码部分用于进行业务逻辑部分，这是很混乱的)。  
所以我们需要使用Model1。使用JSP进行前段显示，JavaBean部分负责业务逻辑部分。  
把JSP页面中的业务逻辑代码封装到一个JavaBean中，JSP页面再调用JavaBean，则可提供一种分离显示内容和业务逻辑的简单方式，并且有利于软件的组件化、模块化。这就是第一种模型开发Web应用的原理。JavaBean与数据库进行交互。  
JSP只负责动态生成网页，只管显示数据，生成网页所需的数据来自于JavaBean，JavaBean负责业务逻辑。Javabean可重用。  
Model1架构的Webapp的工作流程：  
1. 浏览器请求由JSP页面接收。  
2. JSP依据请求的不同需要与不同功能的JavaBean进行交互。  
3. JavaBean复制执行业务处理，操作数据库。  
4. JSP将JavaBean执行结果生成动态Web网页返回给浏览器。
上面的Model可以看出，JSP负责两项活动：显示与控制(也就是请求分发)，包含了表现层以及部分的业务层的作用。而JavaBean负责一项任务，就是业务逻辑(实际上就是持久化层)。  
JSP规范专门定义了三个JSP标签：<jsp:useBean>、<jsp:setProperty>和<jsp:getPropperty>，它们分别用于创建和查找JavaBean的实例对象、设置JavaBean对象的属性、读取JavaBean对象的属性。  
所谓的JavaBean实际上就是一个类，而不一定要是一个实体类，不一定要有各种属性各种set、get方法之类的那种类。任何一个类，里面有各种方法，都可以称之为一个JavaBean，只要其作用是提供数据，其内部的方法可以从数据库中存取数据之类的即可称之为JavaBean，而与其外观形式无关。例如，写一个类，里面有几个方法可以从数据库中增删改查数据，即可称为JavaBean。JavaBean可以理解为数据的操作口。  
JavaBean只代表一个数据源，其不代表一个实体类，只是一个与数据库交互存取数据的类而已。  
#Servlet生命
当容器在启动的时候，会构建一个用于处理http请求的线程池，并在适当的时候构造出每一个Servlet实例。有请求发生的时候，容器就从线程池中取出一个线程用于处理这个请求，如果需要Servlet处理，就将请求交给一个Servlet，然后将处理结果返回给这个处理线程，线程将结果返回给请求者，最后线程返回到线程池中等待调用。  
#JSP
就是在Html代码中插入Java代码以及JSP标记。  
JSP会编译成Servlet的。  
执行JSP时会检测本JSP是否已被转换成Servlet，如果还没有，就会转换成Servlet。Servlet会变为字节码加载到内存中。JVM中进行处理，处理结果返回给用户。  
#JDBC
实际上就是可以执行SQL语句的Java API，JDBC内部实际上是调用各种数据库驱动程序进行数据操作。  
但是这种数据库访问方式具有局限性，因为针对不同的数据库必须使用不同的驱动程序，且在JDBC API中使用SQL语句，但是不同数据库的SQL语句时有差异的，更换数据库就得更换Java代码。具有异构性。所以在JavaEE中使用框架，引入了持久层，就可以解决这个问题。  
#Model2
在Model1中，JSP既负责结果显示又负责请求处理的分发，导致功能合体。所以需要将功能分开，这就是Model2的特点，使用Servlet负责请求的分发，Servlet将请求分发给不同的JavaBean，JavaBean的业务逻辑处理完成之后，将结果发送给JSP进行显示。这就是一个MVC模型的形式。  
#过滤器
就是在调用响应的Servlet或者JSP之前后进行调用，以处理request或者response的组件。  
过滤器必须与特定的URL关联才能发挥作用，URL资源包括：JSP、Servlet。当请求相应的URL资源的时候就会自动调用这个过滤器。  
#Struts2
在Model2中使用JSP+Servlet+JavaBean的开发模式，解决了Servlet的缺点，但是引入了Servlet API,导致难度加大，所以使用了Struts2框架，以减轻工作量。  
实际上，Struts2就是一个功能经过定制、扩展的Servlet过滤器(Struts2只是一个过滤器而已，这一点在Struts的web.xml文件配置中就可以看出来，在这个文件中对Struts2的配置实际上就是配置一个Filter，是配置FilterDispatcher，用于对请求进行)，只不过这个过滤器是专门设计以用于简化Java EE开发的。  
Struts2中这个过滤器的作用就是对用户的请求进行过滤，将不同的请求分发给不同的Action(各种Action就是用于处理不同请求的类)，例如，某个请求的目的是某一个Action，那么这个请求发送出来之后，就会首先从Struts2中这个过滤器中经过，这个过滤器根据请求的目的地将这个请求发送给目的Action，然后目的Action接收到请求之后，执行exectu()方法，对这个请求进行处理，处理完成之后返回结果。  
这样，就将分发请求的功能交给了Struts2框架的过滤器了，我们自己写的Action就负责具体业务层功能（C），（这里的Struts2框架+Action的功能就是Model2中的Servlet的功能）后面的JavaBean负责数据持久化功能（M），而JSP页面负责显示功能（V）。  
一般的Model2，Servlet负责分发请求与业务层功能，JSP中负责显示。  
Struts2框架中数据流程：  
请求——》Web.xml中配置的dispatcher进行分发至对应的Action名——》在struts.xml文件中依据分发过来的Action名查找对应Action类——》执行Action类的execute()方法——》依据Action中execute()方法执行结果去struts.xml文件中查找相应的Action类的执行结果——》依据执行结果跳转到相应的结果页面。