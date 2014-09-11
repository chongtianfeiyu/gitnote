#Java中用于处理日期的类
----------
##Date类，表示一个日期。  
其未过时的构造器只有：
`Date()与Date(long date)`，当然，过时的方法等都还是可以使用。  
在Java中月份从0开始。`Date`是毫秒数，从1970开始。  
`System`类中有一个`static`方法`currentTimeMillis()`用于获取当前时间已经过的毫秒数。  
>
	//initialize the de with default empty constructor_
	Date de=new Date();
	System.out.println(de);
>		
	//get the currentTimeMillis from 1970
	System.out.println(System.currentTimeMillis());
>
	//initialize the de with default empty constructor_
	de=new Date(System.currentTimeMillis()-3*24*60*60*1000);
	System.out.println(de);//output the date three days earlier

##Calender类，也表示一个时间。  
由于`Date`类已经过时了，所以要使用`Calender`类来代替。但是`Date`类并不能完全丢弃。  
但是`Calender`的构造器不能被调用。所以不能自己调用该类的构造器去创建对象实例。
而是通过其一个`static`方法去创建一个实例。这个方法是`getInstance()`。
>
	//output the static field of clendar
	System.out.println(Calendar.DAY_OF_MONTH);
>	
	//get the object of calendar class
	Calendar cal=Calendar.getInstance();
	System.out.println(cal.getTime());
>		
	//set the time field
	cal.set(Calendar.MONTH,10);
	System.out.println(cal.getTime());
>
	//add the value to the field without the limits
	cal.add(Calendar.MONTH,12);
	System.out.println(cal.getTime());
>						
	//rollback the value to the field with the limits
	cal.roll(Calendar.MONTH,12);
	System.out.println(cal.getTime());
