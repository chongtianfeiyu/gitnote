#Junit
是一个测试框架，一个测试工具。  
当我们编写了一个类的时候，就需要对这个类进行测试，也就是说，针对我们自己写的每一个类，都需要针对其编写一个测试类。  
也就是所谓的单元测试。  
##Junit3.8
在3.8版本风格之下，每一个测试类都要继承自TestCase类，该类中所有用于测试的方法必须是以test开头。使用Assert类的static方法来进行测试。
被测试类代码：  
>
	public class Computer
	{
		public int add(int a,int b)
		{
			return a+b;
		}
	
		public int minus(int a,int b)
		{
			return a-b;
		}
	}


在测试代码中，对于每一个我们所需要使用的对象，可以在各个测试方法之外进行初始化(使用setUp方法)，然后在各个测试代码中使用。还可以在测试代码之外对初始化的对象进行销毁(使用tearDown方法)。实际运行测试代码时，会自动先运行setUp方法，再运行我们自己写的测试方法，然后在运行tearDown方法。  
Junit测试单元：  
>
	import junit.framework.TestCase;
	import org.junit.Assert;
	public class Test1 extends TestCase
	{
		Computer c = null;
		@Override
		protected void setUp() throws Exception
		{
			c = new Computer();
			System.out.println("setup-------");
			super.setUp();
		}
		@Override
		protected void tearDown() throws Exception
		{
			System.out.println("teardown-------");
			super.tearDown();
		}
		public void testadd()
		{
			Assert.assertEquals(0, c.add(0,0));
			Assert.assertEquals(1, c.add(1,0));
		}
		public void testminus()
		{
			Assert.assertEquals(0, c.minus(0,0));
			Assert.assertEquals(1, c.minus(2,0));
		}
	}
	
	

#Junit4
在Junit4.4风格下，要使用注解，而不再需要让测试类继承自testCase类。测试类中的各个测试方法使用@Test注解，测试类中的初始化方法使用@Before注解。@After注解的方法用于释放资源，也就是在测试方法完成自后才执行。@BeforeClass方法在所有方法执行之前执行，@AfterClass方法在所有方法执行结束之后执行。但是beforeClass与afterClass注解的方法必须是static void方法。@Ignore注解用于忽略对此方法的测试。  

对于上例的computer类的4.4版本Junit单元测试类为：  
>
	import org.junit.After;
	import org.junit.AfterClass;
	import org.junit.Assert;
	import org.junit.Before;
	import org.junit.BeforeClass;
	import org.junit.Test;
	import com.trilever.wt.Junit1.Computer;
	public class Junit4test
	{
		Computer c = null;
		@BeforeClass
		public static void beforeClass()
		{
			System.out.println("beforeclass");
		}
		@AfterClass
		public static void afterClass()
		{
			System.out.println("afterClass");
		}
		@Before
		public void before()
		{
			c = new Computer();
			System.out.println("before");
		}
		@After
		public void after()
		{
			System.out.println("after");
		}
		@Test
		public void add()
		{
			Assert.assertEquals(0, c.add(0,0));
		}
	}