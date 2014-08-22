
#HTML
##基础知识
开发`B/S`应用的技术：  
`asp`(过时了)，`php`，`jsp`，`asp.net`。这些都是在服务器端使用的开发`B/S`架构网站的技术。  
服务器端无论是使用哪一种技术，在浏览器端都是运行解析的都是`HTML`代码(当然其中包括嵌入的`JS`代码与`CSS`代码。`HTML`是基础根本。  
`HTML`也是一种规范。`HTML5`功能强大，属于`web2.0`。其是一种解释执行的语言。

`B/S`技术的原理：  
当我们向`Web`服务器发出请求之后，`Web`服务器就会向我们的浏览器发送一个很长的字符串，也就是`HTML`代码。接收到这个`HTML`文档之后，我们的浏览器就解析使用这个`HTML`代码。

由于`HTML`语言的语法不够规范，所以出现了`XML`语言，以避免其劣势。但仍未代替`HTML`。  
目前`XML`的主要作用依旧是*写配置文件*，例如、在服务器端的`Servlet`中就需要配置`XML`文件，在`Andriod`代码中使用`XML`文件来描述试图组件。  
目前`HTML`进化为`XHTML=HTML+部分的XML`。  



**重点**：任何一种标记语言，基本上标记都是成双成对出现的。*每一对标记表示一个对象*，放在这个标记括号里面的东西，例如：`border`、`name`这些都是表示这一对标记所代表的*对象的属性*，就好比面向对象语言里面的对象与属性的关系一样。
示例如下：
>
	<table border=1>
	</table>

这一对标记表示一个**表格对象**，里面的`border`表示这个`table`的属性，就是边框的大小。

一个`HTML`文档的示例如下：
>
	<html>
	<head>
			<title>biaoti</title>
	</head>
	<body>
			zhengwen
	</body>
	</html>

`html`标记表示一个网页文档对象，`head`标记表示文档的头部，`body`标记表示文档的正文部分。这些对象都没有写属性。  
一对标记放在另一对标记里面，表示一个对象放在另外一个对象里面，就是包含的关系，不是表示一个对象的属性，而是表示一个对象是另一个对象的一部分。  
就好比，在`Java`中，类与类的关系：继承(`is a`)，属性(`has a`。一个类是另一个类的属性，表示一种描述，班级与班级名称即是如此关系)，包含(`own a`。内部类，表示内部类是外部类的一个部分，而不是一个属性，如，学生与班级的关系即是如此)。  
`HTML`标记语言里不区分大、小写。

##HTML中常用标记：  
`<html bgcolor="red" background="背景图片"></html>`:表示这是一个网页文档对象。  
`&nbsp`：表示一个空格。  
`<p></p>`:表示一个段落。段落前后有空行。  
`<br>`:换行。  
`<hr width="100" align="left">`:表示一条线，括号里的内容表示线的属性。这里的线长是绝对的，如果写作`"100%"`表示这里线长是相对的。  
注意：所有的属性值都用引号括起来，写成字符串的形式。  
`<h1~6></h1~6>`:这个标记表示这里是一个`body`段中的标题对象。  
`<ol type="I"></ol>`:表示一个列表。有序的。大写罗马数字表示。  
`<ul></ul>`:无序列表对象  
`<li></li>`:表示列表中的每一项对象。  
`<img src="地址" alt="提示">`:表示一个图片对象。当图片无法显示的时候，就会显示`alt`中的提示。  
`<a href="目标地址">显示的文字</a>`：`<a></a>`表示一个超链接对象。  
还可以使用*邮件链接*。写法：`<a href="mailto:目标地址"></a>`  
还有锚点链接。也是使用`<a></a>`标记。写法：`<a name="第三章"></a>`  
热点链接：就是将图片上的某个区域设为链接。  

也可以在一个对象中套另外一个对象。  
例如，在一个超链接对象里面套一个图片对象，这样就可以在点击图片的时候启动链接。  
再例如，在`html`对象里面可以包含`head`对象与`body`对象。  

`<META http-equiv="refresh" content="5";url=test.html>`标记，作用：告诉浏览器的一些例如默认字符集等内容。  
例如：本标记的含义是自动刷新跳转到`test.html`页面。总之，`meta`标记很有用  
`<Marquee>滚动的文字</Marquee>`：该标记表示滚动的文字或者图片。  

**注意**:那么哪种标记是一对的，哪种是一个的？  
`Answer`:当这个标记的对象要显示出文字或者图片的时候，就是一对的，中间放的是要显示的东西。如，链接对象上要显示出内容，所以是一对的。线条`hr`标记对象不用显示出东西，所以是一个的。`img`图片标记不用显示出文字内容，所以是一个的。

##表单
`HTML`页面的作用：显示与接收数据。用以与用户进行交互。  
在`HTML`页面中，存在表单(`Form`)，表单的作用就是：里面放置各种**输入控件**，以**接收用户的输入**。  
`<form></form>`:该标记表示一个表单对象。里面放的是各种控件。  
表单的属性有：  
1. `name`：表示表单的名字  
2. `action`：请求处理的目的地址(服务器端的处理页面地址) 
3. `method`：默认值有`get/post`，表示两种提交方式。`get`请求会在浏览器地址栏显示*请求内容*，而`post`请求不会显示。所以对于敏感信息的提交要使用`post`请求。默认是`get`请求。  

输入控件的种类：单行、多行、密码、单选、多选、下拉输入、提交按钮、重置按钮等。*控件对象是放在表单对象里面的*  

表单及输入控件的使用示例：  
>
	<form name="save stu" action="save_Stu.jsp" method="get">
	账号：<input name="账号" width="20" type="text" maxlength="10"  value="trilever"/>
	<br />
	密码：<input name="密码" width="20" type="password" value="wt312041990" maxlength="10" />
	<br />
	描述：<textArea rows="10" cols="10" style="background-attachment:fixed"  name="描述" >Great
	</textArea>
	<br />
	<input name="submit" type="submit"  value="Submit" size="10" />
	<input name="reset"  type="reset" value="Reset" size="10" />
	<br />
	<input name="sex"  type="radio"  checked="checked"/>Male
	<input name="sex"  type="radio"/>Female
	<br />
	<input name="a"  type="checkbox" />a
	<input name="b"  type="checkbox" />b
	<input name="c"  type="checkbox" />c
	<input name="d"  type="checkbox" />d
	<br />
	下拉<select name="city" size="3" multiple="multiple">
		<option value="北京">北京</option>
	    <option value="上海" selected="selected">上海</option>
	    <option value="广州">广州</option>
	    </select>
	<br />
	<button>按钮</button>
	</form>

控件`Readonly`属性：只读，依然可以获得焦点，可以提交。
控件`disabled`属性：无法获取焦点，也不会提交数据。  
在网页里面，还有一种图像提交域，可以通过其提交图像。  

表单隐藏域：也就是暗数据。  
就是在表单提交中，即使用户自己没有提交，依旧自动提交了这个域。  
##表格  
`<table></table>`:  
`table`里面放置的是表格内容及表名等内容:  
`<th></th>`里面放置的是表格的表头。其放置在`<tr></tr>`里面。  
`<tr></tr>`里面放的是行。  
`<tb></tb>`里面放的是列，其放置在`<tr></tr>`里面。  
`<caption></caption>`:表示的是表名。  
表格的作用：  
1. 将数据进行格式化显示。  
2. 对`HTML`中的各种元素对象进行定位、布局。  

网页设计中使用`table`进行元素的定位、布局，以前做网页使用大量的`table`进行定位与布局，现在改为使用大量的`div`。  
怎样使用`div`进行定位、布局？  
`Answer`:将网页原型中的每一块写成一个`div`，然后使用`CSS`或者直接使用`div`的属性确定这个`div`块放置在网页的那一个区域即可。  
定位思想：  
方法1. 画出一个大表格，然后依据自己想要的内容，对大表格进行细分。  
方法2. 将表格进行足够的细分，然后按照自己的需要，对小的格子进行合并。  
表格使用示例:
>
	<table height="145" border="1">
		<caption>biaoge</caption>
		<tr>
	    	<th width="78">Name</th>
	        <th width="73">Age</th>
	    </tr>
		<tr>
	    	<td width="78">1</td>
	        <td width="73">2</td>
	    </tr>
	    <tr>
	    	<td>3</td>
	        <td>4</td>
	    </tr>
	</table>

##网站布局及框架
网页一般分为4个部分：`top`、`left`：导航栏、`main`：主内容部分、`foot`：版权声明。  

当某网站设计有多个网页的时候，各个网页的`top`、`left`部分都是不变的，当网页跳转的时候，如果各个部分都要加载，就会导致降低效率，所以要使用框架，使`top`、`left`这些部分的内容可供共用。网页跳转的时候不用全部都重新加载一遍。  

框架技术就是将一个网页分割成几个网页窗口，每个窗口中显示一个网页文件。当窗口不能完全显示网页的时候，可以通过滚动条来显示被隐藏的部分。其目的是增强网页的导航功能。  

框架的构成：一个框架是有几个网页文件构成，其中一个网页文件专门负责框架设置，这个网页文件叫做**框架组**。其他的网页文件叫做**框架页**，框架页被放在到框架组中来显示。  
`<frameset></frameset>`标签。表示包含若干个框架页的框架。该标签放在框架组中。  
`<frame></frame>`标签。表示一个框架页。该标签  

框架的使用：先写出各个部分的页面，如、`top`部分写一个框架页面，`left`部分写一个框架页面……，然后写一个框架组`index`页面，再在`index`页面中加入代码将这几个框架页面添加上去。可以进行框架之间的嵌套。  
还有一种内联框架:`<iframe></iframe>`，就是在一个页面中新建一个层，然后将另一个页面作为内联框架嵌入到这个层上面即可。就是在一个页面中开辟一块区域放置另一个页面。
框架的使用示例：  
`head`部分代码：  
>
	<!doctype html>
	<html>
	<head>
	<meta charset="utf-8">
	<title>Head</title>
	</head>
>	
	<body bgcolor="#66CCCC">
		This is  the head
	</body>
	</html>

`left`部分代码：  
>
	<!doctype html>
	<html>
	<head>
	<meta charset="utf-8">
	<title>left</title>
	</head>
>	
	<body bgcolor="#CC6666">
	this is the left
	</body>
	</html>

`main`部分代码：  
>
	<!doctype html>
	<html>
	<head>
	<meta charset="utf-8">
	<title>main</title>
	</head>
>	
	<body bgcolor="#00FF00">
	this is the main
	</body>
	</html>

`foot`部分代码：  
>
	<!doctype html>
	<html>
	<head>
	<meta charset="utf-8">
	<title>foot</title>
	</head>
>	
	<body bgcolor="#9900FF">
	this is the foot
	</body>
	</html>
	
`index`部分代码：  
>
	<!doctype html>
	<html>
	<head>
	<meta charset="utf-8">
	<title>index</title>
	</head>
>	
	<body>
	<frameset rows="20%, 60%, 20%">
		<frame src="/head.html"
	    />
	    <frameset cols="30%,70%">
	    	<frame src="/left.html"
	        />
	        <frame src="/main.html"
	        />
	     </frameset>
	   <frame src="/foot.html"
	   />
	</frameset>
	</body>
	</html>

注：`Ajax`继续可以很好实现框架的技术，所以完全可以使用`Ajax`代替框架。

##HTML中的DIV层  
如果将`html`比做`C/S`中`Frame`(窗体)，`DIV`就是窗体中的面板(`Panel`)。  
`DIV`层的作用：更好管理上面的空间，为权限管理提供更好的支持。  
`DIV`层在`HTML`中相当于`Java`中的`Panel`。就是可以放置其他元素的构件。  
实际上，单纯的`DIV`层不能改变大小，`apDiv`层可以改变大小，这就是二者的不同。  

##HTML中的CSS  
`HTML`中放置的是要显示的内容，`CSS`控制内容的显示风格，如、字体、颜色等，`JS`控制的是动作、行为等。  
怎样对`HTML`中的元素对象应用我们已经设置好的`CSS`？  
`Answer`：将`HTML`中的元素对象的`class`属性设置为`CSS`样式表的名字即可。  
`CSS`一般放置在`head`标签之间，供`body`标签之间的内容调用。

依据`CSS`所在位置，`CSS`样式分为：  
1. 行内样式:不单独写`CSS`样式代码，而是在元素对象的属性中使用`style`属性进行设置，放置在元素对象中，控制这个元素对象的显示。其缺点是：内容与`CSS`部分混在一起，混乱且不利于复用。  
2. 行外样式：在`head`部分单独写`CSS`样式代码。然后在要显示的元素对象中使用`class`属性即可。行外样式有利于`CSS`代码复用。  
注：不要使用行内样式。  

实际中，我们更多的将所有的`CSS`样式提取出来，做成一个`CSS`样式表，然后在`HTML`文档中不放置样式表，而是在某个元素对象要使用某个样式表的时候，直接使用这个`CSS`样式表中的`CSS`样式(当然在`HTML`文档中需要进行一次导包导入这个`CSS`样式表即可)。

依据`CSS`的命名可以分为：  
1. 类选择器样式：通过元素对象的`class`属性来进行使用。`CSS`样式的名字前面有"."。  
2. 上下文样式：如果使用类选择器，当`HTML`中要使用某一个样式的元素对象过多，那么都使用`class`属性就过于麻烦。这时就需要使用上下文样式。其通过与某一个元素对象进行挂钩，每次创建一个该元素对象的时候，就会自动使用该上下文样式。该`CSS`样式的名字前面没有".",名称个数有限，与标签样式相同。不用通过`class`属性来加载、使用样式。  
3. 链接样式：超链接有4种状态,未访问、已访问、鼠标放上去、鼠标按上去。当我们希望对于一个链接的不同状态体现出样式的时候，就可以使用链接样式。对于不同状态链接都设置成不同的显示样式。  
4. ID选择器：针对某一个`ID`的元素对象使用，通过元素对象的`ID`属性进行使用。名字前面有"#",`ID`属性主要用于后期的客户端编程而设置的(`JS`)，主要用于`DIV`层。尽量少用。  

`CSS`样式表示例：
>
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		<style type="text/css">
		body,td,th {
			color: #D6D6D6;
			background-color: #0F0;
		}
		body {
			background-color: #C00;
		}
		//用于元素class为mudiv
		.mudiv {
			background-color:#000;
		}
		//用于元素id为apDiv1
		/#apDiv1 {
			position: absolute;
			width: 200px;
			height: 115px;
			z-index: 1;
			left: 147px;
			top: 293px;
			background-color: #0FF;
		}
		.table {
			background-color: #099;
		}
		</style>
	</head>
	
	<body>
		<table height="145" border="1">
			<caption>biaoge</caption>
			<tr>
		    	<th width="78">Name</th>
		        <th width="73">Age</th>
		    </tr>
			<tr>
		    	<td width="78">1</td>
		        <td width="73">2</td>
		    </tr>
		    <tr>
		    	<td>3</td>
		        <td>4</td>
		    </tr>
		</table>
>	
		<div id="apDiv1" ></div>
		<p class="mudiv">&nbsp;</p>
	</body>
	</html>

#JavaScript
-----
##JavaScript基础知识
基于对象，并未完全满足面向对象三个特征。但是有类与对象的概念。  
是一种弱语言：可以不声明变量类型。声明变量用`var`修饰符。区分大小写。解释执行的脚本语言。  

作用：用于客户端编程。用于在浏览器中执行。最多的用于在客户端验证、各种特效、为`Ajax`提供支持。  

`Java`、`JSP Servlet`、`PHP`代码都在服务器端运行，他们都在`Tomcat`等这些服务器容器中被编译运行后产生出`HTML`、`JS`、`CSS`代码，然后发送给客户端浏览器进行解析、执行。  
`SQL`脚本在数据库中被执行。  
`JS`代码放在在`HTML`中哪儿都可以，如果是前台，最好放`HTML`最后面。如果是是后台，随便放哪里都可以。最好放在`head`标签中。  
`JS`代码在客户端完全是可以被抓取到的，因为要被服务器端完全的发送给浏览器，由浏览器对这些`JS`代码进行解释执行。只要你写出来就可以被人看到。  
`JS`代码中带有`HTML`代码也可以被解析。声明变量全部使用`var`，可以将`var`理解为`object`。  

内置对象：`document`代表整个网页，`window`代表整个浏览器界面等。  
`parseInt()`方法：将字符串转换为数字。  


##JavaScript中的Function
`JS`中的方法都是通过事件进行调用的，当事件发生之后，就会调用方法。如，`onload`事件、`onclick`事件。都可以用于激发事件。  
方法同样可以`return`数据，方法的返回值可以用于显示、初始化其他变量等等。但是不同的是，方法的声明中无需写返回值类型。  
方法的参数列表中也无需写数据类型。只需参数名称。  

注意：如果一个内容是使用双引号("")括起来,那么这个内容中的所有需要使用""的地方都要改成''才可以。  

`JS`基础语法使用示例：  
>
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>无标题文档</title>
	<script type="text/javascript">
		document.writeln("hi")
		var name = "how are you?"
		document.writeln("hi"+name);
		alert("great");
		function test()
		{
			alert("ni");
		}
		function test1()
		{
			return "thank you";
		}
		function test2(a)
		{
			var str = test1();
			alert(str);
			alert(a);
			return a;
		}
	</script>
	</head>
	<body onload="test()">
		<input type="button" onclick="test2('good')" />
	</body>
	</html>

注意理解`HTML`中的对象模型：  
以下为示例：  
>	
	<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
	<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta http-equiv="description" content="The Calculator designed by trilever"/>
	<title>Calculator</title>
	<script type="text/javascript">
		function f(v)
		{
			var s1=parseFloat(document.Cal.Num1.value);
			var s2=parseFloat(document.Cal.Num2.value);
			var rst;
			if(checkData()==false)
			{
				return;
			}
>			
			switch (v)
			{
				case "+":
				{
					rst=(s1+s2);
					break;
				}
				case "-":
				{
					rst=(s1-s2);
					break;
				}
				case "*":
				{
					rst=(s1*s2);
					break;
				}
				case "/":
				{
					rst=(s1/s2);
					checkData("/");
					break;
				}
				case "%":
				{
					rst=(s1%s2);
					break;
				}
			}
			document.Cal.rst.value=rst;
		}
		function checkData(v)
		{
			if(document.Cal.Num1.value == "")
			{
				alert ("第一个数不能是空");
				return false;
			}
			if(document.Cal.Num2.value == "")
			{
				alert ("第二个数不能是空");
				return false;
			}
			if(isNaN(document.Cal.Num1.value))
			{
				alert ("第一个数不是数字");
				return false;
			}
			if(isNaN(document.Cal.Num2.value))
			{
				alert ("第二个数不是数字");
				return false;
			}
			if(v=="/"&&document.Cal.Num2.value==0)
			{
				alert("被除数不能为零")
				return false;
			}
			return true;
		}
	</script>
	</head>
>
	<body>
		<form name="Cal"/>
		    Num1:<input type="text" name="Num1"/><br/>
		    Num2:<input type="text" name="Num2"/><br/>
		    Result:<input type="text" value="" name="rst"/><br />
		    <input type="button" name="jia" value="+" onclick= "f('+')"/>
		    <input type="button" name="jian" value="-" onclick= "f('-')"/>
		    <input type="button" name="cheng" value="*" onclick= "f('*')"/>
		    <input type="button" name="chu" value="/" onclick= "f('/')"/>
		    <input type="button" name="yu" value="%" onclick= "f('%')"/>
	    </form>
	</body>
	</html>
	

`document`代表整个网页  
`document.Cal`就表示这个网页中的这个表单对象。  
`document.Cal.document.Cal.Num1`代表这个表单中的`Text`框对象本身。  
`document.Cal.document.Cal.Num1.value`代表`Text`框对象中的值。 
我们可以直接在`Script`中的方法中修改对象中的值。`form`对象的`name`就相当于`Java`中对象实例的名字一样，`form`对象中包含的`Text`框对象的名字相当与`Java`中的类的内部类对象的名字一样的。  


##JavaScript中的常用对象
###String对象
`String`对象的方法：`charAt()``indexOf()`等与`Java`中的`String`类对象的方法差不多。
###Date对象
`Date`对象表示一个日期对象。  
>
	var date = new Date();
`Date`对象的方法：`getsecond()`等。

###数组
创建数组：  
>
	var arr = new Array(3);
	var arr = new Array("aa","bb","cc");
####数组常用方法
	join():将数组中的内容联接成字符串。  
	reverse()：将数组中的内容反转。  
	sort()：对数组中的内容进行排序。  

思考一个问题？  
`Java`中，类的成员变量到底代表了什么？只是表示这个类的某些属性用于描述这个类(由这个类产生的类对象)的性质？类的方法到底是做什么用的？是不是只是为了对这个类的属性进行操作从而使整个类(对象)体现出不同的属性。  
`Java`中的类的内部类到底是代表了什么？是不是代表了这个类内部所拥有的细分的零件部分。这个内部类与外部类的方法之间的关系是什么样的？是不是外部类的方法可以修改内部类创建的对象的属性。也就是说，人体这个外部类可以修改手这个内部类对象的属性？  
在`Java`中的内部类的实际对应就是：内部类是外部类的一个部分，是组件，就好比**手**这个类是**人体**这个类的一个内部类一样。类的成员变量表示的是类的一个特征与属性，如：**人体的身高**这个属性、特征，是**人体**这个类的一个成员变量。所以来说，类的成员变量与类的内部类是两种不同的现实对照体。  
同样，在`HTML`中，一个`form`就相当于一个外部类，这个`form`对象自己所包含的性质写在其自己的括号里面，如`form`的位置就是这个对象自己的属性。但是其里面包含了一个`Text`对象，这个对象就是它里面的一部分，就相当于`Java`中的内部类。  

##JS中的内置对象 
`JavaScript`中区分大小写。  
所谓的内置对象，就是不需要声明不需要创建，可以直接使用。内置对象全部为`JS`关键字。
###window对象  
其代表了整个窗体，其包含了`HTML`部分(就是`Document`对象)。所谓的`HTML`部分就是用`Document`对象表示。  
`window`对象如图`window_Structure.png`所示：  

如，`alert()方法`、`isNanN()方法`、`confirm()`都是该对象的方法，可以不使用对象而是直接使用这些方法。
以下都是`window`对象所具有的方法与内部对象：
`confirm()`：显示一个对话框。  
`open("url","name")`:打开一个新页面、窗体。  
`showModalDialog()`:显示模式窗口。所谓的模式窗口就是让这个窗口永远获得焦点，在其被关闭之前。  
`close()`：关闭当前窗体。  
`window.screen`对象：是`window`的内置对象。表示客户端屏幕以及显示性能。  
`window.status`对象：也是`window`的内置对象。表示浏览器状态栏中显示的内容。  
`window.history`对象：表示客户访问过的`url`信息。该对象有3个方法：`back()`、`forward()`、`go(var indiex)`  
`window.location`对象：表示当前的`url`信息。该对象的一个内部对象`window.location.href`表示一个新的页面。例：可以使用`window.location.href = "目标url";`实现跳转到目标`url`。  

`window.document`对象：实际上表示浏览器窗口`window`对象中包含的`HTML`文档部分。该对象所拥有的方法如下：  
`write()`：向浏览器页面的`body`部分写入内容。  
`getElementById()`:获得`HTML`文档中的某一对象，如一个`Button`、一个`Div`亦可。这个方法极其有用，因为获得这个文档中的对象之后，就可以调用这个对象的方法，获得对象的属性还可以修改对象的属性，全都可以。  
`getElementByName()`：通过文档中对象名字获得对象本身。和上面的一样。  
`getElementsByName()`：获得多个对象。  
`getElementByTagName()`；通过对象的标签类型获得对象。  
`createElement("element name")`:创建一个新的节点对象。  

##JS中的DOM编程
`DOM:Docuement Object Model`,文档对象模型。就是说，可以将整个`HTML`文档视为一棵`DOM`树。
`DOM`树见图`DOM_Structure.png`所示：  

`DOM`编程就是为了可以动态地(在代码运行之后)给文档(**DOM树**)添加或删除一些节点，或者得到、修改节点的属性值。  
`DOM`编程的优点就是：可以动态地改变`HTML`文档中原有的内容。这样就可以与`JS`进行结合通过一些动作动态修改文档中的内容。修改显示的对象页面。`DOM`是`JS`可变的基础。  

注意：一个标记就是一个节点。  
我们可以将`DOM`中的每一个节点理解为一个类对象。但一般只考虑类对象的属性(而不管类对象的方法)。他们有一个共同的父类`Node`。如`img`、`button`标签都共同继承自`Node`类。类似于`Java`中每一个类都继承自`Object`类。  

由于子类拥有父类的属性与方法，只要知道`Node`类的属性与方法，那么就可以调用所有`HTML`节点的属性与方法。`HTML`文档中各个节点对象都具有属性与方法。  
例如：对于一个`button`节点而言，其自`Node`节点继承了`firstchild`属性，那么通过`button`的`firstchild`属性返回了该`button`的第一个子节点。

`event`对象：是事件对象。其也是内置对象。表示发生的某个事件。其具有两个属性：`keyCode`属性表示当前的按键编码。`srcElement`表示在哪一个控件中产生的该事件。`this`表示本控件。  

添加链接节点的时候，需要用一个文本将整个链接显示出来。这时就需要添加一个文本节点，添加一个链接节点，然后将文本节点加到链接节点对象中，再将链接节点对象添加到别的节点对象中。  
`DOM`编程代码示例：  
>
	<!doctype html>
	<html>
	<head>
	<meta charset="utf-8">
	<title>无标题文档</title>
	<script type="text/javascript">
		function addImg()
		{
			var Img = document.createElement("img");
			Img.src="./pig.gif";
			var MyDiv = document.getElementById("MyDiv");
			MyDiv.appendChild(Img);
		}
		function delImg()
		{
			var MyDiv = document.getElementById("MyDiv");
			MyDiv.removeChild(MyDiv.lastChild);
		}
	</script>
	</head>
	<body>
	<div id="MyDiv">
		<input type="button" onClick="addImg()" value="add">
	    <input type="button" onClick="delImg()" value="delete">
	</div>
	</body>
	</html>
