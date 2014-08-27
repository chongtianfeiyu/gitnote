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