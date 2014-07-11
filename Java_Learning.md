# 第一个阶段（java基础阶段） #

1. java语法  
2. 面向对象  
3. 常用的api  
4. 界面编程  
5. 多线程  
6. 文件io  
7. java网络编程

# 第二个阶段 (数据库阶段) #
1. oracle  
2. mysql  
3. sql server  

# 第三个阶段 (web开发阶段) #
1. html  
2. css  
3. javascript    

#第四个阶段 (j2ee 中级部分) #  
1. servlet  
2. jsp  
3. mvc  

#第五个阶段 (j2ee 高级部分)#
1. struts  
2. hibernate  
3. spring





# Java分为三个体系 
1. J2SE（Java2 Platform Standard Edition，java平台标准版）  
2. J2EE(Java 2 Platform,Enterprise Edition，java平台企业版)  
3. J2ME(Java 2 Platform Micro Edition，java平台微型版)。

# J2SE路线 #

1、面向对象的基础

封装、继承、多态

2、异常处理 参考 http://dev.yesky.com/61/8111561.shtml

3、多线程基础 主要是熟悉线程、线程池、锁的概念及使用 可以参考：http://www.cnblogs.com/rollenholt/archive/2011/08/28/2156357.html

4、IO和NIO 参考 http://www.cnblogs.com/rollenholt/archive/2011/09/11/2173787.html

5、网络编程

6、JDBC：主要是Connection、Statement、ResultSet接口及其几个常用实现类的使用

7、Swing组件

8、JVM性能调优（JVM内存结构剖析、GC分析及调优、JVM内存参数优化）

9、Java泛型

10、JDK新特性

# J2EE路线 #

学习这个之前最好先了解html、css、javascript的相关知识

1、JSP&Servlet：这是最基本的技术，主要学习request对象、response对象、session对象、application对象config对象等，这几个也是使用对多的，然后了解servlet的生命周期。

2、通过学习JSP和Servlet之后，了解下Mvc的结构，使用JSP和Servlet通过JDBC来实现一个简单的系统，或者网上搜索下相关的demo看看。这个便于熟悉之后的框架原理及使用。

3、Struts&Struts2&SpringMvc：Struts2/SpringMvc对于理解MVC非常重要，这个现在也是使用非常广泛的框架，建议先学习SpringMvc，这个学习起来相对Struts2要简单一点。

4、Hibernate&MyBatis：两种不同类型的ORM框架，了解Hibernate和Mybatis的使用，并且可以对比下这两种框架的异同。

5、FreeMarker&Velocity：模板技术的两大阵营，这两个用起来都差不多

6、OsCache&EHCache：两种不同类型的缓存机制，页面级别和对象级别，学习之后能够理解性能优化的必要性。

7、Spring：轻量级的Java 开发框架，学习这个时候，可以整合前面的struts、springMvc和Hibernate来学习，这个建议看一个以前开源的shopxx商城的源码，这个结构和代码上还是比较简单的 http://ishare.iask.sina.com.cn/f/15152496.html?from=dl

springMvc的有个叫Jeecms，可能刚开始看起来有点困难

# J2ME路线 #

这个用的比较少了，推荐还是看看android的比较好