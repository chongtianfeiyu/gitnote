#JavaBean
JavaBean的属性与普通Java类的属性不同，JavaBean中的属性是以方法定义的形式出现的。也就是说，属性名不是直接定义的，而是从属性的set、get方法名取得的(通过反射技术)。而不是我们直接在该JavaBean类中定义的属性名。我们自己写的类的属性名称只是一个临时变量。  
JavaBean的属性与普通Java类的属性的概念不一样，JavaBean的属性是以方法定义的形式出现的。  
用于对属性赋值的方法称为属性修改器或setter方法，用于读取属性值的方法称为属性访问器或getter方法。  
属性修改器必须以小写的set 前缀开始，后跟属性名，且属性名的第一个字母要改为大写，例如，nickName属性的修改器名称为setNickName，password属性的修改器名称为setPassword。  
属性访问器通常以小写的get 前缀开始，后跟属性名，且属性名的第一个字母要改为大写，例如，nickName属性的访问器名称为getNickName，password属性的访问器名称为getPassword。  
JavaBean的属性名是根据setter方法与getter方法的名称来生成的， setter方法或getter方法中除去前缀“set”和“get”后的部分即为属性名，但属性名的首字母必须小写。
属性分类：只读/读写/只写，简单/复杂 


JavaBean是一种特殊的 Java 类，它遵从一定的设计模式，开发工具和其他组件可以根据这种模式来调用JavaBean。  
JavaBean可以设计得像Swing组件一样复杂，提供方法、属性、事件、以及供开发工具将它实例化到一个GUI容器中并对其属性和行为进行定制的特性。  
JavaBean也可以设计得很简单，仅仅提供一些方法和属性供外部环境和其他组件调用。  
用作JavaBean的类必须具有一个公共的、无参数的构造方法，这个方法可以是通过编译器自动产生的那个缺省构造方法。  
JavaBean的属性通过遵循某种规范的公共方法暴露给外部，外部的其他程序可以通过Java 的反射API 来查找JavaBean中遵循这一规范的方法，从而发现JavaBean中的属性。  

符合以上条件的JavaBean具有事件处理、自省机制、持久存储特性。如果要使JavaBean能够实现持久存储，就需要将这个JavaBean进行序列化。  

JavaBean中一般使用最多的就是结果简单属性。如下例：  
>
	public class Person
	{
		private String name;
		private String gen;
		public String getName()
		{
			return name;
		}
		public void setName(String name)
		{
			this.name = name;
		}
		public String getGen()
		{
			return gen;
		}
		public void setGen(String gen)
		{
			this.gen = gen;
		}
	}
	
	
还有一种是带indexed属性的JavaBean：但是用的较少。  
>
	public class customer
	{
		private String[] hobbies={null,null,null,null};
		public String[] getHobbies()
		{
			return hobbies;
		}
		public void setHobbies(String[] hobbies)
		{
			this.hobbies = hobbies;
		}
		public String getHobby(int index)
		{
			return this.hobbies[index];
		}
		public void setHobbies(int index,String setStr)
		{
			this.hobbies[index] = setStr;
		}
	}
	
	
#在JSP中使用JavaBean
JSP自身没有实现HTML代码与Java代码的完全分离，网页设计人员和Java编程人员需要操作同一个JSP文件，不易维护和管理。  
把JSP页面中的业务逻辑代码封装到一个JavaBean中，JSP页面再调用JavaBean，则可提供一种分离显示内容和业务逻辑的简单方式，并且有利于软件的组件化、模块化。  
JSP规范专门定义了三个JSP标签：<jsp:useBean>、<jsp:setProperty>和<jsp:getPropperty>，它们分别用于创建和查找JavaBean的实例对象、设置JavaBean对象的属性、读取JavaBean对象的属性。  
对于JSP页面来说，只要一个类具有一个公共的、无参数的构造方法，就可以把这个类当作JavaBean来使用，如果类中有不接受任何参数的getter方法或只接受一个参数的setter方法，就可以把前缀“get”或“set”后面的部分当着一个属性名来引用。  
JSP页面可以像调用一个普通Java类的方式去调用JavaBean，即先使用Java代码创建JavaBean的实例对象，然后直接调用JavaBean对象的getter方法和setter方法。  

通过使用标签来使用JavaBean：  
对于上面的那个JavaBean，不是用标签的时候，直接使用。  
>
	<%
		Person p = new Person();
		p.setName("zhangsan");
	%>
	<%=p.getName()%>


useBean标签：  
<jsp:useBean>标签用于在某个指定的域范围（application、session、request、pageContext等）中查找一个指定名称的JavaBean对象，如果存在则直接返回该JavaBean对象的引用，如果不存在则实例化一个新的JavaBean对象并将它按指定的名称存储在指定的域范围中。 
常见语法：
	<jsp:useBean id="beanInstanceName " class="package.class" 
				scope="page|request|session|application" type=""/>

class属性用于指定JavaBean的完整类名（即必须带有包名）。
id属性用于指定JavaBean实例对象的引用名称和其存储在域范围中的名称。
scope属性用于指定JavaBean实例对象所存储的域范围，其取值只能是page、request、session和application等四个值中的一个，其默认值是page。
type属性指定的是，id指定的对象名的类，这个type属性可以是class类所实现的接口或其父类，也就是使用class指定的类去创建一个接口类型的变量。默认的是class指定的类。  
这里对JavaBean对象的创建可以去看JSP编译出来的Servlet即可明白。
当useBean标签里面包含有标签体的时候，这些标签体只有在创建JavaBean对象的时候才会被调用。也就是说，当再次执行这个标签的时候，已经存在这个JavaBean对象了，那么就不会执行标签体里的内容了。  
	<jsp:useBean id="p" class="com.trilever.Person" >
		-------------------------你好，这里是标签体-----------------------------
	</jsp:useBean>
	
	
注意：所谓的page作用域，就是当前页面，也就是说，当本页面发生刷新之后，就是一个新的page了，就不再在原来的page范围内了。  


setProperty标签：  
<jsp:setProperty>标签用于设置JavaBean对象的属性，也就是调用JavaBean对象的setter方法。 
语法格式：
>
	<jsp:setProperty name="beanInstanceName" 
	{ 
		property="propertyName" value="{string | <%= expression %>}" |
		property="propertyName" [ param="parameterName" ] | 
		property= "*" 
	}/>

示例：  
	<jsp:setProperty property="name" name="p" value="lisi"/>
	//取出PersonName属性值，然后将这个属性值赋给name这个property
	<jsp:setProperty property="name" name="p" param="PersonName"/>

name属性用于指定JavaBean实例对象的名称，其值应与<jsp:useBean>标签的id属性值相同。  
property属性用于指定JavaBean实例对象的属性名。  
value属性用于指定JavaBean实例对象的某个属性的值，其设置值可以是一个字符串，也可以是一个表达式。如果value属性的设置值是一个表达式，那么该表达式的结果类型必须与所要设置的JavaBean属性的类型一致。  
param属性用于将JavaBean实例对象的某个属性值设置为一个请求参数值，它可以将作为字符串类型返回的请求参数值自动转换成要设置的JavaBean属性的类型。实际上param的作用就是：将param属性值作为request.getParameter()方法的参数。然后将getParameter(param)方法的结果赋给property所表示的JavaBean属性。param属性的值就是reqeust.getParameter()方法中的参数。实际上就是取代value中的硬编码赋值的方式，而是使用一个方法从Request、application等存储结构中取出一个值对JavaBean的属性进行赋值设置。  

getProperty标签：  
<jsp:getProperty>标签用于读取JavaBean对象的属性，也就是调用JavaBean对象的getter方法，然后将读取的属性值转换成字符串后插入进输出的响应正文中。
语法：
	<jsp:getProperty name="beanInstanceName" property="PropertyName" />

使用示例：  
	//使用getProperty标签获得JavaBean的一个属性值
	<jsp:getProperty property="name" name="p"/>

name属性用于指定JavaBean实例对象的名称，其值应与<jsp:useBean>标签的id属性值相同。 
property属性用于指定JavaBean实例对象的属性名。
如果一个JavaBean实例对象的某个属性的值为null，那么，使用<jsp:getProperty>标签输出该属性的结果将是一个内容为“null”的字符串。

使用JavaBean的注意事项:  
JavaBean应放置在JSP页面的类装载器或其父级类装载器所能装载的目录中，通常放置于WEB应用程序下的 WEB-INF/classes目录中。  
有些版本的Tomcat不会自动重新加载修改过的JavaBean，如果JSP页面加载JavaBean以后又修改和重新编译了JavaBean程序，那么需要修改JSP页面或者重新启动Tomcat。  
JavaBean必须带有包名，不能用缺省包名。  
在选择存储JavaBean的域范围时，如果使用request域能够满足需求的话，则不要使用Session域。  

JavaBean的自省机制：  
为了简化JavaBean的类对象初始化步骤。可以使用以下代码来初始化JavaBean对象的属性。  
	<jsp:setProperty property="*" name="p"/>

这样，只要<form>中各个标签提交的参数名称与JavaBean中定义的属性值是完全一一对应的，就可以简便地利用一个setProperty标签及"*"来对JavaBean对象进行全部的初始化。这样就不用使用多个setProperty标签对所有的JavaBean属性进行初始化。更加方便。  

存储件的范围大小：page<request<session<application