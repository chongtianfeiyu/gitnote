#软件开发
------------
程序员开发的都是应用程序，分为两种：`B/S`与`C/S`。  
`C/S`有两种：基于图形界面的与基于命令行的。服务器端需要一个数据库服务器。  每一个客户端都是有一份代码。  
`B/S`：浏览器连接`Web`服务器，`Web`服务器后面连接的是数据库服务器(也就是说需要两个服务器)。代码只在`Web`服务器中才有一份。  
`Java`的强项是开发`B/S`。  
`JSP/Servlet`是`Java`开发`B/S`的技术。  
`Hibernate/Spring/JPA`既可以开发`C/S`也可以开发`B/S`。  
`JSP、Servlet、Struts1、Structs2、JSF、Ajax、Flex、Jquery`是`Java`开发`B/S`的技术、

#Java中的重构
---------
重构的作用：提高已经写好的软件的代码可重用性(某些模块可以被其他软件再次使用)，可扩展性(可以方便地添加新功能)，可维护性(代码脉络清晰)。  
例如：从`DAO1.0`到`DAO2.0`，抽取出`DAOManager`模块，就是一个重构过程，因为使得`DAOManager`模块可以被其他软件复用。  
面向对象编码要求：  
1. 面向对象的思维方式，也就是对于软件中所需要的实体，要专门写一个类，例如：对于一个简单的登陆输入账号密码验证，应该写出一个用户类，然后将这个用户类中的`Field`设为账号与密码，而不能直接在业务逻辑中使用账号与密码，应该使用的是这个用户类对象，这样才是面向对象的思维。  
2. 要将业务逻辑从`main()方`法中分离开来，这样才能保证业务逻辑(如、账号密码的判断)被以后的软件复用。  
3. 要考虑用户的所有使用错误，例如，输入错误，没有输入，输入空格等行为。我们的代码中必须对此予以判断。

总之，实体对象要抽取出来做一个类，业务逻辑部分也要抽取出来做一个类。
在重构过程中我们要使用的一些方法与思想，成为设计模式。

#Java中的设计模式：
----
什么叫设计模式？
`Answer`:我们平常遇到的各种问题，往往可以用相同的思想但是细节不同的方法予以解决，这就是所谓的设计模式。实际上就是编写软件、处理问题时的高级算法。

设计模式按照功能的不同分为3类：
1. 创建模式：创建对象，类在实例化时使用的模式。  
2. 结构模式：类(对象)怎样组合在一起组成较大的结构。  
3. 行为模式：关注类与对象之间的联系，各自作用。  

##单态模式：
用于一个类只产生一个类对象的模式，例如，在`C/S`架构中，一个客户端只能与服务器产生一个`Connection`对象，这里就应该使用单态模式。
某些时候，我们只想为某个类创建一个对象。这时候就需要使用单例类。
  
要实现单例：  
1. 不能暴露构造器。否则可以自由创建多个实例。所以，需要人工写一个无参构造器，且设为`private`形式，这样可以防止`JDK`自己创建默认构造器。且构造器只能在类内使用。  
2. 设置一个静态`field`用于缓存在3中的方法所创建的实例，且这个`field`可用于检测保证仅创建了一个实例。  
3. 暴露一个方法，用该方法来返回一个实例。设为`public`。由于调用这个方法的时候没有实例，所以需要使用类来调用该方法。所以这个方法必须是`static`的。同时，该方法内部必须在创建一个实例之前先检测*2*中的`field`，保证只存在一个实例，这样才能保证这是一个单例类。  
4. 使用*3*中创建的方法来创建一个对象。  
单态模式有两种：懒汉式单态类与饿汉式单态类  

饿汉式单态类示例如下：只有在调用了创建对象的方法是才会有类对象。
>
	public class singleTon
	{
		public int a;
		private singleTon() //创建private构造器，防止JDK自己创建构造器
		{
		}
>
		private static singleTon h=null; //创建static field，用于缓存创建的单例对象，同时检测是否单例。
>	
		public static singleTon f() //创建static方法，以创建单例对象，返回该对象引用。
		{
			if(h==null)
			{
				h=new singleTon();
			}
			return h;
	    }
		public static void main(String[] args)
		{
			System.out.println(singleTon.f().a); //使用static单例对象创建方法来创建单例。
		}
>		
		{
			a=10;
		}
	}

懒汉式单态类实例如下：只要加载了这个类就会有单态类对象。  
>
	public class singleTon
	{
		public int a;
		private singleTon() //创建private构造器，防止JDK自己创建构造器
		{
		}
>
		private static singleTon h=new singleTon(); //创建static field，用于缓存创建的单例对象，同时检测是否单例。
>	
		public static singleTon f() //创建static方法，以创建单例对象，返回该对象引用。
		{
			return h;
	    }
		public static void main(String[] args)
		{
			System.out.println(singleTon.f().a); //使用static单例对象创建方法来创建单例。
		}
>		
		{
			a=10;
		}
	}


##工厂模式
`Factory`模式
当我们的程序中需要多种类对象的时候，可以创建一个`Factory`类，专门用于创建类对象。这些类具有相同的特性，都实现了某一个接口，然后面向接口编程。即可实现工厂模式。

##观察者模式
`Observer`模式：是对象的行为模式，又称为发布-订阅模式(`Public/Subscribe`),源-监听模式(`Source/Lisetener`)模式。  

`AWT/Swing`的事件处理中使用了观察者模式。使用了委托事件模型：对事件的处理并不是由事件源本身去处理的，而是委托交由其它的对象进行处理。

几个概念：  
1. 事件源：产生事件的源头。事件发生的目的对象。也即是**被观察者**  
2. 事件对象：封装了事件的详细信息的对象。  
3. 事件处理者:对事件的处理并不是由事件源本身完成的，而是委托事件处理者进行处理的。也就是**观察者**，观察者可以有多个。  
事件处理者能处理事件的前提是：事件处理者(观察者)在事件源(被观察者)那里进行过注册。

观察者模式定义了一个一对多的依赖关系，让一个或多个观察者对象监视一个呗观察者。  
这样，当一个被观察者在状态上的变化能够通知所有的依赖与该被观察者对象的那些观察者对象的时候，这些观察者对象就能够对此做出反应。

观察者模式示例如下：  
a.被观察者：
>
	package com.trilever;
	import java.util.LinkedList;
	import java.util.List;
	public class Arvil
	{
		//事件本身
		Event e = new Event();
		List<Iboy> Iboys = new LinkedList<>();
		//注册观察者
		public void addListenner()
		{
			Iboys.add(new Jim());
			Iboys.add(new Lilei());
		}
		//产生事件
		public void setEvent(int i)
		{
			e.happy=i;
		}
		public List<Iboy> getBoys()
		{
			return Iboys;
		}
	}
	
b. 观察者接口，因为一个被观察者可以被多个观察者观察。
>
	package com.trilever;
	//观察者接口，相当于Swing中的ActionListenner接口，我们需要使用匿名内部类来获得这个ActionListenner接口的实例对象
	public interface Iboy
	{
		public void makeHappy(Event e);
	}

c. 观察者1
>
	package com.trilever;
	//实际观察者1
	public class Jim implements Iboy
	{
		@Override
		public void makeHappy(Event e)
		{
			switch (e.happy)
			{
			case 0:
				System.out.println("jim0");
				break;
>	
			case 1:
				System.out.println("jim1");
				break;
>	
			case 2:
				System.out.println("jim2");
				break;
>	
			default:
				break;
			}
		}
	}

d. 观察者2
>
	package com.trilever;
	//观察者2
	public class Lilei implements Iboy
	{
		@Override
		public void makeHappy(Event e)
		{
			switch (e.happy)
			{
			case 0:
				System.out.println("lilei0");
				break;
>	
			case 1:
				System.out.println("lilei1");
				break;
>	
			case 2:
				System.out.println("lilei2");
				break;
>	
			default:
				break;
			}
		}
	}

e. 事件：
>
	package com.trilever;
	//事件对象，对事件的封装，目的是提供给观察者。
	public class Event
	{
		int happy = 0;
		public void setHappy(int i)
		{
			this.happy = i;
		}
	}

f. 测试类：
>
	package com.trilever;
	//测试类
	public class ObserverLTest
	{
		public static void main(String[] args)
		{
			Arvil a = new Arvil();
			a.addListenner();
			a.setEvent(2);
			for (Iboy i : a.getBoys())
			{
				i.makeHappy(a.e);
			}
		}
	}


##适配器模式
当我们的接口中有很多个方法的时候，我们在实现这个接口时，会需要实现所有的这写方法，这样才不至于创建一个`Abstract`类。但是，这些方法并不是都有用的，全部实现是一种浪费。  
所以，有鉴于此，我们先写一个`Adapter`类，这个类对接口中所有的方法都予以实现，不过都是空实现，然后当我们需要使用这个接口的时候，直接继承这个`Adapter`类即可。继承这个`Adapter`类时，只需要对自己想要的方法于是重写，而不必重构写所有的方法。

在`AWT`编程中即使用了`Adapter`模式。
