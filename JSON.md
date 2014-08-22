#JSON
-------
在`Ajax`中，当服务器向客户端发送复杂对象时，我们需要自己定义字符串的拼接规则，客户端获得这些无意义的字符串之后，还需要重新解析、分解，代码复杂，容易出错。  
所以，我么可以选择`JSON`格式或者`XML`格式。有点事：格式规范，用于解析。`JSON`、`XML`本质上就是一个字符串。  
`JSON`是一种轻量级的数据交换格式。易于人阅读与编写。同时易于机器解析与生成。采用了完全独立与语言的文本格式。这些特性使`JSON`成为理想的数据交换语言。  
` `是`JS`的子集。使用`JS`可以方便地解析它。  
##JSON语法
1. 名称/值对的集合。  
	<script>
		var stu={name:"wt",age:24};
		alert(stu.name+stu.age);
	<script>
2. 值的有序列表
	<script>
		var myArray={"a","b","c","d"};
		alert(myArray.length);
	<script>
3. 对象方法	
	<script>
		var stu={
				//对象的属性，也就是成员变量
				name:"wt",age:24
				favourate：["footbal"，"basketball"，"swimmig"]，
				//定义对象的方法
				say:function()
				{
					alert(this.name);
				}
				};
		//调用对象的方法
		stu.say();
	<script>
注意：使用的是{};  
一个`Json`实际上就是一个`String`对象，在`JS`中，可以使用`eval('('+str+')')`方法将这个`JSON`字符串转化为其所代表的对象。  
代码示例：  
>
	//JSON字符串，这个字符串可以来自于
	var str;
	//在JavaScript中将一个JSON字符串解析为一个stu对象。
	var stu=eval('('+str+')');
	stu.say();
	


所以我们可以在服务器中，创建一个JSON的字符串，在将这个字符串转发给客户端。客户端收到这个字符串之后，在JS代码中可以通过eval()方法将这个JSON字符串转化为一个对象，然后就可以使用这个对象的属性与方法了。生成JSON字符串，之后就可以用这个字符串进行传递、储存、转发等行为了。这种对对象的传递、储存的方法极其实用。  
Servlet中代码：  

在服务器端，怎样由一个对象生成JSON字符串？  
Answer:  
1. 手动编写。  
2. 使用JSON-lib.jar包。这个jar包如果要运行，还需要一些别的类，依赖于其他的一些jar包。  
代码示例：  
>
	Student stu;
	//使用JSON-lib包中的类将stu这个对象转换为一个JSON字符串。
	JSONObject jsobject= JSONObject.fromObject(stu);
	String jstr=jsobject.toString();

注：我们所谓请求的JSP页面都是前端页面，直接将这个JSP页面中的内容转化为Servlet，在Servlet中发送给客户端浏览器的。所以来讲，JSP页面是前段页面。只不过包含了后端的代码，这些后端的代码会将处理完的HTML发送给客户端，所以客户端并不能看到这些后端代码，而是作为处理结果的DHTML文档。  
