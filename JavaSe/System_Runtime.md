#Java中与系统有关的两个类
-------------

System类  
代表我们的`JVM`所在的操作系统，如`XP`系统。  
如果要获取操作平台的相关特性，就要使用`System`类。  
`System`类中所有的方法和`field`都是`static`的。无需对象来调用。
>
`System.out`:代表屏幕这个对象，这个`out`实际上是`System`这个类的一个`static field`，属于整个类。因为系统本来就有这个东西，而且是类拥有的。  
>
`System.in`:代表键盘这个对象，这个`in`实际上是`System`这个类的一个`static field`，属于整个类。
>
`System.exit(int status)`:使用这个`static`方法可以退出JVM，这个方法实际上是`System`这个类的一个`static`方法。遇到这个代码，该`java`程序立刻结束,退出虚拟机。
>
`System.getenv()`:获取所有环境变量。
>
`System.getenv(String name)`:获取指定环境变量值。
>
`System.getProperties()`:获取所有的系统属性。
>
`System.getProperty(String key)`：获取指定的系统属性。  

Runtime类  
代表`JVM`所在的`JRE`。  
`JRE=JVM+核心类库（JDK）`。  
若要获取`JVM（虚拟机）`的特性，就要使用`Runtime`类。

`Runtime`类的方法不是`static`的，而且这个类没有构造器。  
但是这是一个单例类。它的构造器被隐藏了，外界是看不到的。  
只能通过`getRuntime()`这个`static`方法来获得实例。然后通过这个实例来调用`Runtime`的各种方法进行操作。
>
	Runtime rt=Runtime.getRuntime();//获得runtime类的实例
	System.out.println(rt.availableProcessors());//使用这个实例调用这个类的方法，获得JVM的处理器个数

还可以通过`Runtime`类对象的方法`exex()`来调用运行操作系统（xp）命令或者其中的一个程序：
实际上只要是安装于这个系统中的所有软件都可以通过这个类对象进行调用运行。
>
	rt.exec("D:/Program Files/Bin/QQ.exe");//在虚拟机中开辟一个新的进程，然后再执行用系统中的某一个程序。注意调用时的路径问题，如果没有指定绝对路径，而只有相对路径，就回到path环境变量中进行搜索。

因此，有了这个东西，我们可以做一个有界面的程序，界面上都是本系统中拥有的所有程序的图标按钮，然后每一个按钮都和对应的程序.exe连接在一起，那么运行了这个程序之后，点击相应的按钮就可以快捷启动对应的软件。


