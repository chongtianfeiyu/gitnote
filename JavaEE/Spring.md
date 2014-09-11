#基础知识
---
Hibernate中封装数据访问的细节，体现了OOP的思想，被称为ORM。学习Hibernate是为了把JDBC忘掉。Hibernate是持久层框架。  
Struts是封装了JSP，JavaBean这些东西，学习Struts是为了将JSP、JavaBean这些都忘掉。Struts是表现层框架。  
Spring是业务层框架。管理Bean的。Spring的核心组件是容器。  

Spring是一个开源的控制反转(Inversion of Control ,IoC)和面向切面(AOP)的容器框架.它的主要目得是简化企业开发.  
依赖注入：在运行期，由外部容器动态地将依赖对象注入到组件中  
控制反转：应用本身不负责依赖对象的创建及维护，依赖对象的创建及维护是由外部窗口负责得。这样控制权就由应用转移到了外部容器，控制权的转移就是所谓的反转。  
依赖注入机制减轻了组件之间的依赖关系，同时也大大提高了组件的可移植性，这意味着，组件得到重用
的机会将会更多。  
#学习路径
---
控制反转/依赖注入->面向切片编程->与其他主流框架(Struts2,Hibernate)的整合、管理
#使用Spring框架步骤
---
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
#创建bean的方法
##通过使用类直接生成
##bean静态工厂
>工厂类  
>
	package com.trilever.wt;
	public class animalImpFactory {
		public static Animal AnimalRetu()
		{
			return new Animal();
		}
	}
>bean配置
>
	<bean id="Animal" class="com.trilever.wt.animalImpFactory" factory-method="AnimalRetu"></bean>	
##bean实例工厂
>工厂类  
>
	package com.trilever.wt;
	public class animalImpFactory {
		public Animal AnimalRetu()
		{
			return new Animal();
		}
	}
>bean配置
>
	<bean id="animalFactory" class="com.trilever.wt.animalImpFactory"></bean>
	<bean id="Animal" factory-bean="animalFactory" factory-method="AnimalRetu"></bean>

#bean的作用域
singleton:返回bean 的同一个实例，也是默认的作用域（无状态bean 使用此作用域）  
prototype:每次请求都会创建一个实例（有状态bean 使用此作用域）  
request、session、global session 这三个作用域主要用在web 应用中  
#IOC思想
inverse of control  
DI：dependency injection：依赖注入。实际上就是IOC的另一个名字，就是将一个类对象注入成另一个类的属性。  
如上例所示，我们创建了两个JavaBean对象，而且两个JavaBean对象之间具有依赖关系。如果是一般的方式中，我们必须先new出两个JavaBean对象，然后使用set方法来装配两个对象之间的依赖关系。这就是正常的获得建对象的顺序。  
但是在Spring框架里面，我们使用了容器来创建对象，也就是使用了所谓的反转控制思想，就是IOC思想，就是获得对象的方式被反转了。Spring框架中是先创建各种对象，然后将这些对象组装起来。  
使用容器创建对象的原理：  
当我们创建容器ApplicationContext的对象的时候，就会根据加载的xml文件创建里面所描述的所有类对象，也就是JavaBean对象，同时，容器还帮助我们对这些JavaBean对象之间的关系进行装配。所以，我们自己不用去一个个地new出对象，再装配对象之间的关系。当我们需要使用某一个对象的时候，使用getBean()方法就可以取出放在容器中的JavaBean对象。然后就可以直接使用该JavaBean对象了。  
#BeanFactory
另外，BeanFactory也可以叫做容器，因为ApplicationContext接口就是BeanFactory接口的子接口。他们都可以叫做容器。  
ApplicationContext的功能更加丰富。  
创建BeanFactory对象的时候，就是创建一个容器。但BeanFactory只是将xml文件读取进来，此时并没有创建xml里面定义的对象，只是一个空容器而已。只有当要获得对象的时候(使用getBean方法)才会创建对象(优先实例化单例的Bean对象)，这样是为了节省资源。这就是BeanFactory的特点。  
ApplicationContext是更加高级的容器，功能强大，其不用等到要使用JavaBean对象的时候才创建JavaBean对象，而是在加载xml文件创建ApplcationContext对象的时候，就创建所有的单例的JavaBean对象，放入到容器中。实际中，只有在很少的时候，才会使用BeanFactory，例如在移动设备上，为了节省资源。  

#配置单例的JavaBean
当我需要某一个JavaBean采用单例模式，就需要在xml文件中进行相应的配置。添加scope属性即可。  
>
	<bean id="greetingService" class="com.trilever.wt.GreetingServie" scope="singleton">
	<property name="greetWord">
	<value>
		hello world
	</value>
	</property>
	</bean>
	
#Spring工作原理及步骤
见图Spring_Process.png。
