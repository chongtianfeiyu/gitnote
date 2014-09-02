#Java中的枚举
-------
枚举：用于代表实例数量是固定的**特殊类**。有点像单例类，只不过**有多个实例**。枚举非常重要。  
##提出问题
实际中，有些类的值必须尤其范围的。
比如我们给Person{private String sex;} 这是如果你不控制，sex可以为任意值。但实际上，sex值是有范围的。
##解决方法
###传统解决方法1
>
	//传统解决方法1
	class Person{
		private String name;
		private String sex;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getSex() {
			return sex;
		}
		public void setSex(String sex) {
			//看看sex是不是我需要的,事后诸葛亮.
			if(!sex.matches("man|woman")){
				//不对
				throw new RuntimeException("性别值不对");
				}
			this.sex = sex;
		}
	}

###传统解决方法2
>
	//定义一个Sex类,目标是让使用者，不能让其创建Sex对象
	final class Sex{
		public static Sex man=new Sex("男");    //man->"男"
		public static Sex woman=new Sex("女");  //woman->"女";
		public static Sex middle=new Sex("ddd");
		private String val;
		public String getVal() {
			return val;
		}
		private Sex(String val){
			this.val=val;
		}
	}

###直接使用enum来解决
>
	//直接使用enum类型
	enum Sex1{
		woman,man;
	}

##使用示例	
案例:1
>
	//用枚举类型来Grade
	enum Grade{
		a("优秀"),b("良好"),c("一般"),d("及格"),e("不及格");//构造函数.
		private String val;
		public String getVal() {
			return val;
		}
		private Grade(String val){
			this.val=val;
		}
	}

案例:2, 通过内部类来实现打印对应信息
>
	enum Grade2{
		a{
			@Override
			public String getInfo() {
				// TODO Auto-generated method stub
				return "优秀";
			}
		}
		,b{
			@Override
			public String getInfo() {
				// TODO Auto-generated method stub
				return "良好";
			}
		}
		,c{
			@Override
			public String getInfo() {
				// TODO Auto-generated method stub
				return "一般";
			}
		}
		,d{
			@Override
			public String getInfo() {
				// TODO Auto-generated method stub
				return "及格";
			}
		}
		,e{
			@Override
			public String getInfo() {
				// TODO Auto-generated method stub
				return "不及格";
			}
		}
		;
		//提供一个函数去返回a对应的具体的值.
		abstract public  String getInfo();
	}

案例3:把enum如何使用到 switch
>
	Grade g=Grade.a;
		switch(g){
		case a:
			System.out.println("aaa");
			break;
		case b:
			System.out.println("bbb");
			break;
	}
	
枚举的几个常用方法举例:
>
	WeekDay wd=WeekDay.Tue;
	System.out.println(wd.name()+" "+wd.ordinal());
>			
	WeekDay wd2=wd.valueOf("Mon");
	System.out.println(wd2.getLocaleDate());
>			
	//遍历枚举值
	WeekDay wds[]=wd.values();
	for(WeekDay t: wds){
			System.out.println(t.getLocaleDate());
	}
语法示例如下：
>
	[修饰符] enum 枚举名
	{
		//必须在第一行立即写上该枚举的所有实例对象名。这里写上实例，表面上看是写出实例对象名，实际上是在调用构造器进行初始化。所以，如果本枚举类拥有无参构造器，那么直接写对象名就是调用默认构造器。但是，如果没有无参构造器，就需要使用有参构造器，就不仅仅是直接写对象名就行，还得显式调用有参构造器。
		//其他部分和普通类是同样的。同样可以有field、构造器（必须是private构造器，默认的就是private的，大部分时候不用写）、方法、内部类\内部枚举\内部接口等东西。其本质就是一个类。
	}
>
	public enum Gender
	{	
    	Male,//这是调用了该类的默认构造器，省略了括号。
		Female("woman");//显式调用有参构造器。实际上相当于：Gender Female=new Gender("woman")；
		String name;
		Gender()//默认构造器
		{
			name="man";
		}
		Gender(String agender)//有参构造器
		{
			name=agender;
		}
	}

`enum`关键字的地位与`class`这个关键字的地位是一样的。  

修饰符：`public|省略` 或者 `abstract|final`（但是二者必须出现其中之一。默认的是`final`）。  

枚举类的特征：  
默认继承了`Java`自己的枚举类，实际上还继承了`object`类。由于所有的枚举都继承自一个`Java`中的枚举，就和普通类都继承自`object`类一样，都继承了某一些方法。  
例如：所有的枚举都有`values()`方法，这个方法可以返回所有的枚举值。
  
枚举类的构造器只能用`private`来修饰，因为不能再类外随意创建实例，实例数已经固定了。这样是为了保护构造器。  
使用枚举类的实例对象的时候，直接用即可，不能再去`new`创建。
  
枚举类的方法要非常小心。这里的主要问题是：  
枚举类是特殊的类，因为它的类对象在设计类的时候就已经设定了，而且相应类对象的各个属性也已经设定了(构造类对象的时候)。所以就不能在类中方法里面随意设定属性的值。  
也就是说，在`set()`这种方法里面，设置某些属性的值的时候，在这些方法中一定要进行所要设定目标值的合理性的检验，否则就会导致违背类自己限定的对象的实际情况。或者直接不允许出现设定对象属性的方法。  
包含抽象方法的枚举类，如果某个方法有`abstract`修饰，那么就不能有`final`和`static`。  

更重要的是，当枚举类具有抽象方法的时候，那么这个枚举类就是一个抽象类，那么，这个抽象内部类怎么能够有实例对象呢？
`Answer`：对于一个抽象类而言，不能通过调用它的构造器来创造它的对象，这个时候，如果真需要使用抽象类的对象，可以通过匿名内部类来进行。
>
	abstract class mt
	{
		int f=10;
		public abstract void fo();
		public void fm()
		{
			System.out.println("abs");
		}
	}	
>
	public class abs 
	{
		public static void main(String[] args)
		{
			System.out.println("hello world");
			mt m=new mt()//这里通过一个匿名内部类来创建一个抽象类的对象。
			{
				public void fo()
				{
					System.out.println("fo");
				}
			};
			m.fm();
		}
	}

`Answer`：这时候可以使用匿名内部类，使用匿名内部类来创建包含抽象方法的枚举类的对象。这个匿名内部类继承的就是这个包含抽象方法的枚举类（需要实现枚举类中的`abstract`方法）。因为要创建这个枚举类的对象，所以就可以先创建继承本枚举类的子类，然后通过这个子类创建枚举类的对象。然后就可以随意使用我们创建的这些对象了。
>
	public enum seasons
	{
		spring()//这里就是一个匿名内部类，继承了枚举类。然后通过这个匿名内部类创建了一个对象。因为不能通过调用枚举类的构造器来初始化创建枚举类的对象，所以只能使用匿名内部类。这样可以避免抽象枚举类无法通过构造器构建对象的缺陷。
		{
			public void infor()
			{
				System.out.println("spring");
			}
		},//注意，这里的枚举中的匿名内部类后面使用的是“,”而不是在类中写匿名内部类时所使用的“;”。也就是说这里的分隔符用什么决定于这个创建的对象后面本来该用什么分割符。
		summer()
		{
			public void infor()
			{
				System.out.println("summer");
			}
		},
		autumn()
		{
			public void infor()
			{
				System.out.println("autumn");
			}
		},
		winter()
		{
			public void infor()
			{
				System.out.println("winter");
			}
		};
>	
		public abstract void infor();
>	
		public static void main(String[] args)
		{
			System.out.println("hello world");
>
			seasons.spring.infor();
			seasons.summer.infor();
			seasons.autumn.infor();
			seasons.winter.infor();
		}
	}

枚举是一个特殊类：实例数固定，且必须在类第一行就写出来。

枚举类也可以实现接口：  
1. 我们可以在枚举类里面实现接口中所有的抽象方法。  
这样可以保证枚举类不是`abstract`类，可以创建实例，然后其他的和一般的枚举类都是一样的。  
2. 我们不在枚举中实现接口中的所有的抽象方法。  
这样枚举类就是`abstract`类，然后就可以使用匿名内部类创建枚举类的对象。

枚举用于描述我们*已知一个类中的对象有那么几个的时候*，如，成绩分等级时几个等级都已知，季节中就呢么几个，性别只有那么几个。我们已知对象的具体情况的时候，无需创造无数的对象。

注：选择语句`switch`的表达式必须是以下几种之一。
>
	switch(byte|short|char|int|String|枚举);//只有这六种，如、double等都是不行的。
