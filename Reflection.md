#Java中的Class类
--------
程序运行的时候，每一个类(如Person类)在加载的时候，JVM都会为这个类创建一个Class对象，这个Class对象里面记载的都是这个程序里面所有Person类对象的信息。同时我们可以通过Person类的Class对象获得这个Person类的所有情况，如：Person类里面的所有方法对象，Person类里面的所有Filed，Person类里面的所有构造方法对象。

#Java中的反射
----
反射：能够分析类能力的程序称为反射。对程序员没什么用处，只对工具设计者有用。

用于**动态获取类中的成员**：  
1. 可以获取一个类的`Class`  
2. 可以获取这个类的所有方法对象(注意：Java中一切都是对象，包括方法，一个类中的成员，即使连方法也是对象。) 可以通过这个方法对象来使这个方法被执行，将一个类对象作为方法对象的参数传入即可。  
3. 也可以获取这个类的所有Field对象。

反射的作用在于：  
当我们在使用一个已有的类的时候，在主代码的main()方法中使用别的已有的类的时候，如果被使用的类发生了改变，那么导致我们的main()方法中也要修改大量的代码，以适应被使用的类的修改。这个时候，如果在main()方法使用了反射(reflect)，那么就可以通过被使用的类的名字来获取这个被使用的类的所有情况(方法、Field等)，这样就不必修改我们的主程序了。只需在被使用类的配置文件中写上被使用类的名字，在mian()方法中通过读取这个配置文件获得这个被使用类的名字即可通过反射的方法使用这个类。即使修改了要使用的类的名字或者实现，所有的修改也只体现在这个类的配置文件中，将需要修改的地方减至最少，提升了类的可扩展性。

反射作用使用示例：

被使用类与使用类：
>
	import java.io.*;
	interface eatable
	{
		public void eat();
	}
>	
	class person implements eatable
	{	
		public void eat()
		{
			System.out.println("person EAT");
		}
	}
>	
	class animal implements eatable
	{	
		public void eat()
		{
			System.out.println("animal EAT");
		}
	}
>	
	public class ClassTest
	{
		public static void main(String[] args)throws Exception
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File("file.txt")) ));
			Class className = Class.forName(br.readLine());
>			
			//使用了anima类
			eatable ea= (eatable)className.newInstance(); 
			ea.eat();
		}
	}
>
被使用类的配置文件：`file.txt`
>
	animal
各种框架里面大量使用了反射，但是只对用户暴露了类名与该类的配置文件，防止被修改。


#Java中的注解
----------------
例如：在重写一个方法的时候，在方法上面加上`@Override`，在编译阶段就保证编译成功。这就是注解(`Anotation`)的作用，这是和注释不一样的。  
`Anotation`是一个接口，`Override`之类的都是它的实现类。
获取注解对象只有一种方法：反射。
