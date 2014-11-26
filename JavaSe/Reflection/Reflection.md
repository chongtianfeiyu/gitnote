#Java中的反射
反射：能够分析类所拥有的能力的程序称为反射。  
#反射的作用  
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

#Java类的class属性
程序运行的时候，每一个类(如Person类)在加载的时候，JVM都会为这个类创建一个static的class成员属性，这个class属性里面记载的都是这个程序里面所有Person类对象的信息。  
同时我们可以通过Person类的class属性获得这个Person类的所有情况，如：Person类里面的所有方法对象，Person类里面的所有属性，Person类里面的所有构造方法对象。  
这个class属性的类型就是Class.  

---

获取类的Class对象。  
Java中的所有类型包括基本类型(int, long, float等等)，即使是数组都有与之关联的Class类的对象。  

1. 如果你在编译期知道一个类的名字的话，那么你可以使用如下的方式获取一个类的Class对象:  

     Class myObjectClass = MyObject.class;
     
2. 如果你在编译期不知道类的名字，但是你可以在运行期获得到类名的字符串,那么你则可以这么做来获取Class对象:  

    String className = ... ;//在运行期获取的类名字符串  
    Class class = Class.forName(className);  
    
在使用Class.forName()方法时，你必须提供一个类的全名，这个全名包括类所在的包的名字。例如MyObject类位于com.jenkov.myapp包，那么他的全名就是com.jenkov.myapp.MyObject。       

----------

#类的class属性的作用
用于**动态获取类中的成员**：  
1. 可以获取这个类的所有方法对象(注意：Java中一切都是对象，包括方法，一个类中的成员，即使连方法也是对象。) 可以通过这个方法对象来使这个方法被执行，将一个类对象作为方法对象的参数传入即可。  
2. 也可以获取这个类的所有成员。  
3. 获取类的名称
4. 获取类的修饰符
5. 获取类所在的包
6. 获取父类的class属性
7. 获取类的构造器
等等

#获得的构造器的使用
1. 获取构造器有两种方法。  
2. 获取构造器的参数  
3. 使用构造器来创建类对象  

#访问类的私有变量即私有方法
访问私有变量

----------

要想获取私有变量你可以调用Class.getDeclaredField(String name)方法或者Class.getDeclaredFields()方法。Class.getField(String name)和Class.getFields()只会返回公有的变量，无法获取私有变量。下面例子定义了一个包含私有变量的类，在它下面是如何通过反射获取私有变量的例子：
    public class PrivateObject {
      private String privateString = null;
      public PrivateObject(String privateString) {
      this.privateString = privateString;
      }
    }
    PrivateObject privateObject = new PrivateObject("The Private Value");
    Field privateStringField = PrivateObject.class.
    getDeclaredField("privateString");
    privateStringField.setAccessible(true);
    String fieldValue = (String) privateStringField.get(privateObject);
    System.out.println("fieldValue = " + fieldValue);
    
这个例子会输出”fieldValue = The Private Value”，The Private Value是PrivateObject实例的privateString私有变量的值，注意调用  PrivateObject.class.getDeclaredField(“privateString”)方法会返回一个私有变量，这个方法返回的变量是定义在PrivateObject类中的而不是在它的父类中定义的变量。  注意privateStringField.setAccessible(true)这行代码，通过调用setAccessible()方法会关闭指定类Field实例的反射访问检查，这行代码执行之后不论是私有的、受保护的以及包访问的作用域，你都可以在任何地方访问，即使你不在他的访问权限作用域之内。但是你如果你用一般代码来访问这些不在你权限作用域之内的代码依然是不可以的，在编译的时候就会报错。  

访问私有方法

----------

访问一个私有方法你需要调用 Class.getDeclaredMethod(String name, Class[] parameterTypes)或者Class.getDeclaredMethods() 方法。 Class.getMethod(String name, Class[] parameterTypes)和Class.getMethods()方法，只会返回公有的方法，无法获取私有方法。下面例子定义了一个包含私有方法的类，在它下面是如何通过反射获取私有方法的例子：
    public class PrivateObject {
      private String privateString = null;
      public PrivateObject(String privateString) {
    this.privateString = privateString;
      }
      private String getPrivateString(){
    return this.privateString;
      }
    }
    PrivateObject privateObject = new PrivateObject("The Private Value");
    Method privateStringMethod = PrivateObject.class.
    getDeclaredMethod("getPrivateString", null);
    privateStringMethod.setAccessible(true);
    String returnValue = (String)
    privateStringMethod.invoke(privateObject, null);
    System.out.println("returnValue = " + returnValue);
这个例子会输出”returnValue = The Private Value”，The Private Value是PrivateObject实例的getPrivateString()方法的返回值。
PrivateObject.class.getDeclaredMethod(“privateString”)方法会返回一个私有方法，这个方法是定义在PrivateObject类中的而不是在它的父类中定义的。
同样的，注意Method.setAcessible(true)这行代码，通过调用setAccessible()方法会关闭指定类的Method实例的反射访问检查，这行代码执行之后不论是私有的、受保护的以及包访问的作用域，你都可以在任何地方访问，即使你不在他的访问权限作用域之内。但是你如果你用一般代码来访问这些不在你权限作用域之内的代码依然是不可以的，在编译的时候就会报错。
#Java中的注解
----------------
例如：在重写一个方法的时候，在方法上面加上`@Override`，在编译阶段就保证编译成功。这就是注解(`Anotation`)的作用，这是和注释不一样的。  
`Anotation`是一个接口，`Override`之类的都是它的实现类。
获取注解对象只有一种方法：反射。



#反射与数组
1. 使用反射技术创建数组  
    int[] intArray = (int[]) Array.newInstance(int.class, 3);
这个例子创建一个int类型的数组。Array.newInstance()方法的第一个参数表示了我们要创建一个什么类型的数组。第二个参数表示了这个数组的空间是多大。  

----------

2. 使用反射技术访问数组内的内容
具体可以使用Array.get(…)和Array.set(…)方法来访问数组内的内容。下面是一个例子：
>
    int[] intArray = (int[]) Array.newInstance(int.class, 3);
    Array.set(intArray, 0, 123);
    Array.set(intArray, 1, 456);
    Array.set(intArray, 2, 789);
    System.out.println("intArray[0] = " + Array.get(intArray, 0));
    System.out.println("intArray[1] = " + Array.get(intArray, 1));
    System.out.println("intArray[2] = " + Array.get(intArray, 2));
  

----------
3. 获取数组对象的class属性
如果不通过反射的话你可以这样来获取数组的Class对象：

    Class stringArrayClass = String[].class;
如果使用Class.forName()方法来获取数组的Class对象则不是那么简单。比如你可以像这样来获得一个原生数据类型（primitive）int数组的Class对象：  

    Class intArray = Class.forName("[I");
在JVM中字母I代表int类型，左边的‘[’代表我想要的是一个int类型的数组，这个规则同样适用于其他的原生数据类型。  
对于普通对象类型的数组有一点细微的不同：

    Class stringArrayClass = Class.forName("[Ljava.lang.String;");
注意‘[L’的右边是类名，类名的右边是一个‘;’符号。这个的含义是一个指定类型的数组。  

4. 获取普通原生数据类型的class属性
需要注意的是，你不能通过Class.forName()方法获取一个原生数据类型的Class对象。下面这两个例子都会报ClassNotFoundException：  

    Class intClass1 = Class.forName("I");
    Class intClass2 = Class.forName("int");
通常会用下面这个方法来获取普通对象以及原生对象的Class对象：  
    public Class getClass(String className){
      if("int" .equals(className)) return int.class;
      if("long".equals(className)) return long.class;
      ...
      return Class.forName(className);
    }
一旦你获取了类型的Class对象，你就有办法轻松的获取到它的数组的Class对象，你可以通过指定的类型创建一个空的数组，然后通过这个空的数组来获取数组的Class对象。这样做有点讨巧，不过很有效。如下例：  

    Class theClass = getClass(theClassName);
    Class stringArrayClass = Array.newInstance(theClass, 0).getClass();
这是一个特别的方式来获取指定类型的指定数组的Class对象。无需使用类名或其他方式来获取这个Class对象。
为了确保Class对象是不是代表一个数组，你可以使用Class.isArray()方法来进行校验：
    Class stringArrayClass = Array.newInstance(String.class, 0).getClass();
    System.out.println("is array: " + stringArrayClass.isArray());

5. 获取数组的成员的class属性
一旦你获取了一个数组的class属性，你就可以通过class.getComponentType()方法获取这个数组的成员类型。  
成员类型就是数组存储的数据类型。例如，数组int[]的成员类型就是一个Class对象int.class。String[]的成员类型就是java.lang.String类的Class对象。  
下面是一个访问数组成员的class属性的例子：  

    String[] strings = new String[3];
    Class stringArrayClass = strings.getClass();
    Class stringArrayComponentType = stringArrayClass.getComponentType();
    System.out.println(stringArrayComponentType);
