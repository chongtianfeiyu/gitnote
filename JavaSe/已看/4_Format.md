#Java中的Format类的使用
-----------------  
`Format`类有三个子类：`MessageFormat、NumberFormat、DateFormat`。  

`NumberFormat`类：用于格式化数字，所谓格式化数字，就是将数字转换成相应所要求格式的字符串。
不能使用构造器来进行构造，而是使用以下的`static`方法来获得实例对象。
>
	getCurrencyInstance（）；用于将数字格式化成货币字符串。
	getIntegerInstance（）：用于将数字格式化成整数字符串。
	getPercentInstance（）：用于将数字格式化成百分号字符串。

并且，这些方法中还可以传入`locale`对象，设置格式化成哪个国家的字符串。

得到`NumberFormat`类的实例对象之后，调用其以下方法，就可以将数字转换成相应所要的字符串。
>
	String format（double number）；

`DateFormat`类：用于格式化日期。不能直接创建实例，而是通过以下的`static`方法创建实例。
>
	getDateInstance（）；用于将日期对象格式化成日期字符串，只有日期，没有时间。
	getTimeInstance（）；用于将日期对象格式化成时间字符串，只有时间，没有日期。
	getDateTimeInstance（）；用于将日期对象格式化成日期时间字符串，都有。

并且，这些方法中还可以传入`locale`对象，设置格式化成哪个国家的字符串以及格式化风格。
>
	DateFormat  usShort=DateFormat.getDateTimeInstance(DateFormat.SHORT,DateFormat.LONG,Locale.US);

得到`DateFormat`类的实例对象之后，调用对象的以下方法，就可以将日期对象格式化成相应想要的字符串。  
>
	String format（Date date）；
>
	Date date = new Date();//创建一个时间对象实例。
	System.out.println(df.format(date));//将时间对象实例格式化成想要的String格式。


还有一个常用的`simpledateformat`是`Dateformat`的子类。
其可以进行任意自定义格式的格式化，是简单的日期时间格式器。**可以将日期格式化为字符串，也可以将字符串格式化为日期**。其具有构造器。
>
按照自己的要求*将日期对象格式化成一个日期字符串*。
>
	SimpleDateFormat sdf = new SimpleDateFormat("生日：y年M月d日,是今年的第D天");//将格式器设定为我们所需要的任何格式。
	System.out.println(sdf.format(date));//使用格式化器。
>
它的格式化是随心所欲的。
>
将一个*日期字符串格式化成一个日期对象*。
>
	String dat = "2014/5/25 13.34/12";//时间字符串
	SimpleDateFormat sdf1 = new SimpleDateFormat("y/M/d H.m/s");//创建对象
	Date da = sdf1.parse(dat);//将时间字符串转换成时间对象
>		
	System.out.println(da);//输出时间对象。
