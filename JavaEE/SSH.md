#Structs1
-----
一般已经很少使用`Structs1`进行新项目了，一般都是老项目才用的。重点是`Structs2`,但二者是完全不同的东西，没有联系。  
##自定义内存数据库  
定义一个内存数据库的作用是，我们在这个内存数据库(实际上就是一个链表)中假如一些数据，以供我们后面写代码作数据库之用，免得每次都的使用数据库，进行数据库的操作，太过于麻烦。自己使用一个链表来代替数据库，方便一些。只是内存数据会在关闭程序之后就消失，回归到原始的样子。  

##基础知识
框架：就好比写简历时使用的模板。简化应用程序的开发。写简历时使用的模板就是一个框架。使我们不用从头造轮子。框架是个半成品，提供一个公共结构。有一个比较牛的组织开发的，大家都认可他们的框架，都去学习他们的框架。这就是Structs、Spring这些框架的来源。  

Structs1实际上是MVC思想的一种实现。  
Structs1刚开始出现的时候是最好的MVC框架，现在已经不是最好的。比如Structs2就比它好。我们学习它是因为它出现得最早。  
MVC思想的实现框架有：Structs1、WebWork(后变为Structs2)、Structs2、Spring MVC、JSF(实际上是一个标准，基于事件驱动，模仿ASP.net)、Tapestry(基于事件驱动)。  
Structs1使用了jsp、Servlet、xml、java解析xml、反射等技术。  

注意：在请求转发的时候，会自动给你加上项目名称，所以我们不用再加上contextPath。
总之，在Web项目中，尽量都不加ContextPath，都能自己找到。

#SSH
Structs1(2)、Spring、Hibernate三者都是轻量级框架  
Structs1(2)：实现MVC思想。主要做表示层。  
Spring：实现了AOP，面向方面编程思想。主要做业务层，但其也提供了Spring MVC可以做表示层，还提供了模板做持久化层。  
hibernate：实现ORM思想。主要做持久化层。  
三个框架的功能偏重是不同的。  

Java EE框架。实现了传统的Java EE框架(JSF
、EJB等)的特征,无需专业的Java Web容器，使用简单的Java Web服务器即可。  
轻量级的Java EE应用框架:以传统的JSP作为表现层技术，以系列开源框架作为MVC层，中间层，持久层解决方案。  
传统的Web中，需要等到服务器作出回应之后，才能继续进行请求，这就是传统的阻塞式调用。Ajax技术是一种异步处理方法，允许Servlet发动新线程去进行新的活动。这样可以避免等待。  
Model1模式下，整个Web应用都几乎都是由JSP页面构成，JSP页面接收处理客户端请求，对请求处理后直接作出回应。使用少量的JavaBean来处理数据库连接、数据库访问等操作。模式简单，适用于快速开发小规模应用项目。但是，JSP页面身兼View、Controller两种角色，将控制逻辑与表现逻辑混为一谈，导致代码复用性低，维护难度高。  
Model2模式是基于MVC架构的设计模式，在Model2中，Servlet作为前端控制器，负责接收客户端发送的请求，在Servlet中只包含控制逻辑与简单的前端处理。然后调用后端的JavaBean完成实际的逻辑处理。最后转发到相应的JSP页面处理显示逻辑。JavaBean作为Model，JSP作为View，Servlet作为Controller。   


呈现层(UI Layer/Presentation Layer)
struts
业务逻辑层（Business Layer）
spring
持久化层（Persistence Layer）
hibernate



Model1：  
以JSP为中心的开发模型，称为Model1（JSP+JAVABEAN）
业务逻辑与表示逻辑混和，不利维护与重用
HTML中嵌入了大量的JAVA代码
验证、流程控制、更新程序的状态全部在JSP中完成


Model2：  
基于MVC模式的框架
MVC将问题进行分解
模型包含应用程序的核心功能。模型封装了应用程序的状态。它对视图或控制器一无所知。 
视图提供模型的表示。它是应用程序的外观。视图可以访问模型的读方法，但不能访问写方法。此外，它对控制器一无所知。
控制器对用户的输入作出反应。它创建并设置模型。  


