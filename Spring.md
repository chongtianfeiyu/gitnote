Hibernate中封装数据访问的细节，体现了OOP的思想，被称为ORM。学习Hibernate是为了把JDBC忘掉。Hibernate是持久层框架。  
Struts是封装了JSP，JavaBean这些东西，学习Struts是为了将JSP、JavaBean这些都忘掉。Struts是表现层框架。  
Spring是业务层框架。管理Bean的。Spring的核心组件是容器。  

#使用Spring框架步骤
1. 添加类库  
--spring的核心类库 在spring文档的dist下
dist\spring.jar
--引入的第三方类库 都spring文档的lib下
lib\jakarta-commons\commons-logging.jar
如果使用了切面编程(AOP),还需要下列jar文件
lib/aspectj/aspectjweaver.jar和aspectjrt.jar
lib/cglib/cglib-nodep-2.1_3.jar
如果使用了JSR-250中的注解,如@Resource/@PostConstruct/@PreDestroy,还需要下列jar文件
lib\j2ee\common-annotations.jar
2. 创建JavaBean
代码如下：  
>第一个JavaBean，其引用了第二个JavaBean来作为它的属性。  
>
	package com.trilever.wt;
	public class GreetingServie {
		private String greetWord;
		private String greetWord1;
		private byeService bs;
		public byeService getBs() {
			return bs;
		}
		public void setBs(byeService bs) {
			this.bs = bs;
		}
		public String getGreetWord1() {
			return greetWord1;
		}
		public void setGreetWord1(String greetWord1) {
			this.greetWord1 = greetWord1;
		}
		public String getGreetWord() {
			return greetWord;
		}
		public void setGreetWord(String greetWord) {
			this.greetWord = greetWord;
		}
		public void sayGreeting()
		{
			bs.sayBye();
			System.out.println(greetWord);
		}
		public void sayGreeting1()
		{
			System.out.println(greetWord1);
		}
	}
>第二个JavaBean
>
	package com.trilever.wt;
	public class byeService {
		private String byeWord;
		public String getByeWord() {
			return byeWord;
		}
		public void setByeWord(String byeWord) {
			this.byeWord = byeWord;
		}
		public void sayBye()
		{
			System.out.println(byeWord);
		}
	}
	
3. 配置applicationContext.xml文件
>applicationContext.xml文件中对这两个JavaBean的配置
>
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	           http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">
	<!-- 配置Spring的一个bean，也就是我们自己创建的类 -->
	<bean id="greetingService" class="com.trilever.wt.GreetingServie">
	<property name="greetWord">
	<value>
		hello world
	</value>
	</property>
	<property name="greetWord1" value="hi">
	</property>
	<!-- 这里的属性是外来的类,这里引用的是外面的bean。两个bean是依赖的关系。
	也就是说，当一个JavaBean里面引用了另外一个JavaBean，那么就需要使用ref属性来设置这个JavaBean
	的property标签 -->
	<property name="bs" ref="byeService"></property>
	</bean>
	<bean id="byeService" class="com.trilever.wt.byeService">
	<property name="byeWord" value="later"></property>
	</bean>
	</beans>
4. 使用	
>测试类
>
	package com.trilever.wt;
	import org.springframework.context.ApplicationContext;
	import org.springframework.context.support.ClassPathXmlApplicationContext;
	public class TestApp {
		public static void main(String[] args) {
			//初始化应用上下文。实际上这个ac对象就是前面所说的容器，可以通过这个容器创建各种JavaBean对象。
			//一般情况下创建JavaBean对象时通过new创建的。而且还要使用set方法设置属性值。
			//这里使用容器来创建各种JavaBean对象，方便，而且不用自己去设置属性值，也不用手动去装配类之间的依赖关系(一个类中使用另一个类作为参数)。都是在xml文件中就进行配置好了。
			ApplicationContext ac = 
					new ClassPathXmlApplicationContext("applicationContext.xml");
			//获得bean的对象，使用xml中配置的id
			GreetingServie gc = (GreetingServie) ac.getBean("greetingService");
			gc.sayGreeting1();
			gc.sayGreeting();
			byeService bs = (byeService) ac.getBean("byeService");
			bs.sayBye();
		}
	}

3. 创建配置文件  
applicationContext.xml文件。  
代码如下：  
>
	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
	       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	       xsi:schemaLocation="http://www.springframework.org/schema/beans
	           http://www.springframework.org/schema/beans/spring-beans-2.5.xsd">
	<!-- 配置Spring的一个bean，也就是我们自己创建的类 -->
	<bean id="greetingService" class="com.trilever.wt.GreetingServie">
	<property name="greetWord">
	<value>
		hello world
	</value>
	</property>
	</bean>
	</beans>

#IOC思想
