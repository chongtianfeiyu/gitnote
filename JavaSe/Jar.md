#Java中的jar命令
---------------
作用：把多个文件打包成一个压缩包。与`winzip`压缩格式是一样的，只不过jar压缩结果中在各种`class`文件之外会添加一个`meta-inf`的文件夹，其中有一个`menifest.mf`文件，这个文件就是清单文件。  

通常得到的压缩包有3种:  
1. `*.jar`:它里面包括的是n个`class`文件。  
2. `*.war`:它是一个`web`应用打包生成的文件。与`jar`的格式是一样的，只是后缀不同而已。  
3. `*.ea`r:它是一个企业应用打包生成的包。

`jar`命令的用法：
某个工程在一个文件夹中，里面都是`class`文件。将所有的`class`文件压缩成一个`jar`文件压缩包。  
> 
 `jar -cvf myname.jar *.class `  //f:表示后面的是目标`jar`文件名。`*.class`表示将文件夹所有的`class`文件都压缩起来。

如果不生成清单文件：
>
>
	jar -cMf myname.jar *.class

查看`jar`压缩包的内容：
>
	jar -tf myname.jar
	jar -tvf myname.jar:查看详细内容

解压：
>
	jar -xf myname.jar:将jar压缩包解压。

更新压缩包：将新class添加到原来的jar包中。
>
	jar -uvf myname.jar *.class //将文件夹中的新的class加入到原来的jar包中。


为什么做jar包？  
因为一个真实项目中会有很多`class`文件，所以存在系统管理不方便、性能低（`jvm`需要依次加载不同的`class`文件，效率很低）。  
所以可以将多个`class`文件打包成一个`jar`包，这样的好处就是：多个`class`文件统一成一个`jar`包方便管理，`jvm`不用将`jar`包解压再加载，而是可以加载整个`jar`包（加载所有的`class`），这样效率高。


将整个项目打包成可执行的`jar`包：  
我们将所有的`class`打包成`jar`文件后，这些`class`中只有一个`class`中的`main`方法才可以执行，也就是说项目是从这个`class`文件开始执行的，但是`jvm`不知道哪一个才是可以开始执行的`class`文件，所以，需要打包成可执行的`jar`包。
通过`-e`选项告诉`jvm`哪一个`class`才是整个项目的主类。
>
	jar -cvfe myname.jar hello *.class//后面的说明hello.class是主类，*.class表示压缩文件的来源为目录下所有的class文件。
产生的文件在已经装了`JVM`的情况下可以点击运行。  
如果没有安装JVM，可以使用：
>
	java -jar my.jar来进行运行。//用于运行控制台程序。
	javaw -jar my.jar//这是用于运行界面程序。

`Ant`工具是一个更强大的生成`jar`包的工具。

Java中的入口方法
------------------------
系统`JVM`在调用这个`main()`方法的时候，`JVM`与我们的类不会在同一个包中，`JVM`也不会是我们这个类的子类。所以只能用`public`修饰`main()`方法。  
也不能先创建实例来调用`main()`方法，只能用类拥有的方法，这样在`JVM`载入这个类的时候，就自动加载这个类所拥有的方法。所以`main()`方法就是一个`static`方法。  
`main()`方法的参数：`args`，这是一个`String`数组，默认长度是0；可以在运行的时候，接参数，将参数值传递进入`main()`方法。  
>
`java 主类名 参数1 参数2 参数3……`

