#Ajax
Web1.0：内容贡献者提供内容，其他人阅读内容。不是所有人都能提供内容，进行互动。就好比我们的网上商城项目，就是一个Web1.0项目。  
Web2.0：让用户来贡献内容，任何人都可以贡献内容。如、博客就是Web2.0产品。支持RSS订阅功能。社会化网络。新的交互方式例如自动补全。  

Location对象可以用于当前页面的跳转。将该对象改成什么地址变量，当前页面就跳向什么地址变量。  

##Web1.0与Web2.0技术上的对比
此处见图所示：  

使用Web1.0技术，会有一个缺陷：只能保存客户请求发出前的那些信息。当客户发出请求之后到得到服务器端回应这段时间内(由于网络传输的网速问题，从发出请求到服务器端作出回应之间一定是有一个时间间隔的)，客户作出的任何操作都是无效的。这样就导致在用户操作体验上是间断而不是连续的。也就是说，在服务器端的响应完毕之前，客户端用户的一切操作都是无用的，用户只能处于等待状态，交互极其不连贯。交互体验差，就像和机器对话一样。  
Web1.0中提供的是完全刷新，也就是说，当服务器端作出的回应反映在客户端浏览器上是，是整个页面的刷新、重写。  
而Ajax为Web2.0提供了局部刷新，也就是说服务器端的回馈只对浏览器的局部进行刷新。其在网络传输上具有异步传输功能(免去用户等待服务器回应的缺点，提高交互水平)。  
由于Web1.0是完全刷新，所以导致在网络带宽上需要更多的资源。  

#富客户端
富客户端技术有:Ajax,Flex,JavaFX。  
客户端分以下几种：  
C/S：分为胖客户端、瘦客户端。我们的TMS就是胖客户端，其客户端代码中有界面代码，也有核心业务逻辑代码。所谓的瘦客户端就是将核心业务代码放在一个服务器中，在客户端只有界面代码，其有一个中间件服务器。  
B/S：分为0客户端、富客户端。如网上商城项目就是0客户端，其界面代码即可以操作核心业务代码，也可以直接操作数据库。而浏览器客户端值用解析界面代码。

Ajax：Asysnomous+JavaScript++XML  
*JavaScript的功能很强大*：可以调用ActiveXObject控件(就是一种COM组件)，借助这些组件以完成非常强大的功能。JS本身的功能不强。  
所谓的COM组件(技术)的思想：就是说，无论用什么语言写了一个类(一个组件)，只要将这个类在计算机的注册表中予以注册，就能在任何地方调用这个组件。  
例如：用C++语言写了一个类，在注册表中进行了注册，那么在其他java程序中，就可以通过注册表中的信息去加载这个类。  
所以，由上，JS有一个非常强大的功能，就是可以调用COM组件，实现很多强大的功能。

所以，加入用其他语言写一个病毒程序，在注册表中注册了。那么可以使用JS去调用这个组件。激活病毒。


##Ajax工作原理
Ajax：一种不用刷新整个页面便可与服务器通讯的办法。  
传统的Web系统，客户端向客户端发送一个请求，服务器返回整个页面，如此反复。  
同步传输与异步传输的不同点：  
同步传输技术中，在服务器没有处理完客户端请求之前，是不会对客户端作出回应。客户端在此阶段也不能继续发送请求。  
在异步传输中，即使服务器还没有开始处理客户端请求，只要其**接受到了**客户端请求，那么就会立刻调用回调函数告诉客户端已经接收到请求。然后服务器端再去开始处理客户端请求、响应客户端请求，而在服务器处理客户端请求的时候，客户端可以继续向服务器发送请求。  

在Ajax模型中，数据在客户端与服务器之间独立传输，服务器不再返回一整个页面。  
不用刷新整个页面既可与服务器进行通信的方法有：  
1. flash  
1. java applet  
2. 框架  
3. 隐藏的iframe  
4. xmlHttpRequest对象，也就是Ajax技术。该对象是对JS的扩展，用于网页与服务器之间的通信。是创建Ajax的最佳选择。  
在`AJax`技术中，客户端浏览器与服务器之间不是直接发送请求的，而是通过中间的一个控件。这个控件也就是Ajax引擎。  
JS中独立调用Ajax引擎。  
`Ajax`代码实际上就是`JS`代码，都是写在`JSP`文件中的`JS`代码。Ajax工作原理见图`Ajax工作原理`。  
Ajax中包含的技术见图`Ajax中包含的技术`。  
使用Ajax技术时，客户端、Ajax引擎、服务器之间传输数据是使用XML文件进行信息的传递的。  
当然Ajax技术也存在缺陷，见图`Ajax缺陷`
1. 我们要使在JS代码中，创建一个`XMLHttpRequest`对象。根据不同的浏览器核心，使用不同的代码创建该对象。在JS代码中创建该对象，然后用户通过按钮之类的调用这些JS代码创建对象，在通过这个对象将信息发送给服务器，发送完成后，就会自动调用回调函数告诉客户端已经发送。如图所示：  
2. 设置回调函数。什么叫回调函数？当服务器端返回了结果之后，需要告诉客户端返回结果已经到达，这就是回调函数的作用，服务器通知客户端返回结果已经到达。  
3. 初始化`XMLHttpRequest`组件。设置`url`、请求方式、是否异步这些属性。  
4. 发送请求。

这个`XMLHttpRequest`对象组件的作用是负责与服务器的交互。  
该组件的状态也就是：`XMLHttpRequest.readyState`。有下面几个值：0(未初始化)、1(初始化)、2(发送请求)、3(开始接受结果，也就是开始接受服务器返回结果，返回结果就是一个字符串，就是`HTML`文档)、4(接受结果完毕)。  
`XMLHttpRequest`状态发生一次改变，就调用一次回调函数。

Ajax技术，实际上就是利用JS可以通过编程DOM修改部分的页面而不用修改全部页面的特性来进行的。重点是在JS中对页面的部分元素进行操作，而不必等待后面的Servlet、JSP这些代码的响应。因为Servlet、JSP响应都需要刷新全局的页面，而且速度慢一些(服务器计算与网络传输的原因)。  
所以Ajax的技术原理就是：在客户端发送信息之后，在服务器**接收信息的过程中**（从开始接受到接收完毕）就通过JS中的Ajax技术里面的回调函数对页面进行局部的修改，而不必等待服务器的Servlet/JSP返回结果对整个页面进行全局的刷新。这样就提高了交互的速度。当服务器接收完请求之后，就立刻调用JS中的回调函数，与此同时，让相应的Servlet/JSP对请求进行处理。处理结果再通过Ajax技术中的XMLHttpRequest对象发送给客户端，而不是直接从Servlet这些发送给客户端。  


`Ajax`使用代码示例：  
>	
	<script type="text/javascript">
	    var xmlHttpRequest;
	    //创建xmlHttpRequest对象
	    function createXMLHttpRequest()
	    {
	    	if(window.ActiveXObject)
		    {
		    	try
		    	{
		    		xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
		    	}
		    	catch(e)
		    	{
		    		xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
		    	}
		    	return xmlHttpRequest;
		    }
		    else if(window.XMLHttpRequest)
		    {
		    	return new XMLHttpRequest();
		    }
	    }
>	    
	    function getServletDate()
	    {
	    	//1.创建xmlHttpRequest对象这个对象是客户端与服务器端之间的中介，也是缓冲区域。
	    	xmlHttpRequest = createXMLHttpRequest();
	    	//2.设置回调函数，这个函数就是当服务器端接收到客户端请求之后，调用的方法，在该方法中判断服务器接收客户端请求的状态，然后依据接收信息的状态予以响应。每一次服务器接收客户端信息的状态的改变，都会触发这个回调函数，具体会对哪些状态予以回应则决定于回调函数中的代码。也就是说，客户端每发送一次消息给服务器，就会激发、调用这个回调函数5次，因为服务器从开始接受这个请求到接受请求完成(也就是开始处理请求之前)这段时间内，这个接受信息的状态会发生5次变化(0~4),每次变化都会调用一次这个回调函数。在这个回调函数内部根据这个信息状态进行响应的处理。
	    	xmlHttpRequest.onreadystatechange = haolejiaowo;
	    	var url = "${pageContext.request.contextPath}/RandomNumberServlet";
	    	//3.初始化，建立与服务器端的连接.此处就是将请求通过这个xmlHttpRequest组件发送至一个Servlet(也就是url所指的服务器中的那个Servlet)进行处理。xmlHttpRequest只是一个中介。客户端请求不是直接发送到服务器端的Servlet中，而是先发送到xmlHttpRequest中的。再由xmlHttpRequest将这个Request发送到服务器端。
	    	xmlHttpRequest.open("get","./testServlet",true);
	    	//4.客户端通过这个xmlHttpRequest组件发送请求至服务器端的Servlet。
	    	//总之，整个方法调用顺序是：客户端发送一个请求给服务器端的xmlHttpRequest对象，在这个请求的传输过程中要调用5次回调函数，然后请求传送完毕之后，xmlHttpReques对象就会将这个请求发送给Servlet进行处理，就是调用该Servlet中的doPost()或者doGet(）方法。
	    	//send方法里传送的数据(注意只有对于open里面使用post请求的时候才能通过send方法传送数据，只是还需要在send方法之前加上一句代码。对于get请求是不能通过send方法传送数据的)JS中传递之后，可以在Servlet/JSP中通过Request中的getParameter()方法取出相应的数据。在这里给Servlet/JSP传递数据就类似与表单提交的情况一样。
	    	xmlHttpRequest.send(null);
	    }
>	    
	    function haolejiaowo()
	    {
	    	//假如这个被请求的Servlet作出回应
	    	if(xmlHttpRequest.readyState==4&&xmlHttpRequest.status==200)
	    	{
	    		//将服务器端Servlet的回应取回来，取到xmlHttpRequest对象中。再将回应发送给客户端。也就是说，Servlet中的回应不是直接发送到客户端的，而是发送到xmlHttpRequest中的，然后再在回调方法中，取出Servlet的回应发送给客户端。
	    		var responseText = xmlHttpRequest.responseText;
	    		document.getElementById("testDate").innerHTML=responseText;
	    	}
	    }
    </script>
    <body>
    	<div id="testDate"></div>
    	//客户端的请求不是直接发送到Servlet中的，而是要发送到xmlHttpRequest对象中。再有其发送到Servlet。
    	<input type="button" name="bt1" onclick="getServletDate()">
	</body>

##Ajax的缓存(Cookie)问题
各个浏览器为了提供更好的用户体验，会在浏览器中缓存已经访问过的页面信息，当再次向同一个`url`中发送请求的时候，就不会再次提交请求而是使用浏览器中的缓存。  
这个特性就会对`Ajax`产生问题，由于`Ajax`是局部刷新的，中间的`xmlHttpRequest`组件不停地向服务器发送请求，一刻不停的从服务器端接收数据。而且是向同一个`url`，这样就导致发送请求之后，就是即使在服务器端返回的局部数据已经发生了变化，但是由于浏览器的这种缓存机制，导致显示在页面上的依旧是以前缓存的数据内容(存储于浏览器中的`Cookie`里面)，`Ajax`的局部刷新特性完全体现不出来。导致了问题。  

这个问题的解决方法：  
1. 禁用`Cookie`，但是会导致浏览器变慢。而且客户并不知道去禁用`Cookie`。  
2. 只发送`post`请求，因为浏览器缓存(`Cookie`)只对`get`请求有效。  
3. 每次新的请求都在`url`中假如不同的无意义参数，让浏览器认为是不同的`url`，从而不进行缓存。url=目标地址+请求参数。每次新的请求都在请求参数部分添加无意义的参数。即可。例如，每次都在请求参数中加入当前请求的时间，就会使`url`每次都不一样。  
>
	var url = "${pageContext.request.contextPath}/RandomNumberServlet? a=" + new Date().valueOf();
4. 在服务器端响应的`Servlet/JSP`中添加回应头内容，指明不使用缓存。  
>
	public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException 
		{
			response.addHeader("pragma", "no-cache");
			response.addHeader("cache-control", "no-cache");
			response.addHeader("expires", "0");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(new Date().getSeconds());
			out.flush();
			out.close();
		}
5. 在`Ajax`发送请求前加上一句。
>
	xmlHttpRequest.setRequestHeader("if-Modified-Since","0");

什么叫做局部刷新？  
Answer：就是说，当客户端发出一次请求，会将请求发送给中间的`xmlHttpRequest`组件，然后由这个组件代理一切与服务器之间的交流。这个组件会一刻不停地进行刷新(实际上就是一刻不停地向客户端发送请求)，探测到客户端发出的任何请求之后就会将这个请求发送给服务器端(无论请求是否不同，都要发送给服务器)，同时也会一刻不停的探测服务器端返回的任何回应(一刻不停地接收服务器端的回应)，无论是探测到请求还是回应，都会*立刻*将此端探测到的东西发送给另一端。对于服务器返回的内容，哪部分发生了改变就立刻发送那部分(如果没有发生改变就不需要发送)，这就是局部刷新的原理，这个中间组件一刻不停地探测，一刻不停的发送刷新(发送请求)。这也就是Ajax的工作原理。  
什么是异步传输(I/O)?  
Answer:就是说，中间的那个组件一刻不停地探测两端的请求与回应，任何一方有动静，就立刻告诉另一方，而不必按照传统的方式必须等待一方的请求或者回应完成才传输另一方的请求或者回应，而是`即有即传`。传输请求与传输回应是动态的，没有一定的规则要求。都不必等待对方完成才继续自己的传输。这就是异步传输。  


##Ajax技术与一般技术的比较
见图所示：  
Ajax技术的最大优点就是：实现了*即时操作即时显示结果*，而无关于网速限制的数据传输。这就提高了用户体验。

##Ajax制作省市联动效果
代码示例：  
JavaScript部分：  
>
	<script type="text/javascript">
	    var xmlHttpRequest;
	    //创建xmlHttpRequest对象的方法
	    function createXMLHttpRequest()
	    {
	    	if(window.ActiveXObject)
		    {
		    	try
		    	{
		    		xmlHttpRequest=new ActiveXObject("Microsoft.XMLHTTP");
		    	}
		    	catch(e)
		    	{
		    		xmlHttpRequest = new ActiveXObject("Msxml2.XMLHTTP");
		    	}
		    	return xmlHttpRequest;
		    }
		    else if(window.XMLHttpRequest)
		    {
		    	return new XMLHttpRequest();
		    }
	    }
	    function haolejiaowo()
	    {
	    	//假如这个被请求的Servlet作出回应
	    	if(xmlHttpRequest.readyState==4&&xmlHttpRequest.status==200)
	    	{
	    		//将服务器端Servlet的回应取回来，取到xmlHttpRequest对象中。再将回应发送给客户端。也就是说，Servlet中的回应不是直接发送到客户端的，而是发送到xmlHttpRequest中的，然后再在回调方法中，取出Servlet的回应发送给客户端。
	    		var responseText = xmlHttpRequest.responseText;
	    		clear();
	    		if(responseText.length!=2)
	    		{
		    		var shinames=responseText.split(",");
		    		for(var i=0;i<shinames.length;i++)
		    		{
		    			var op = document.createElement("option");
		    			op.text=shinames[i];
		    			document.getElementById("shi").appendChild(op);
		    		}
	    		}
	    	}
	    	endLoadimage();
	    }
	    function clear()
	    {
	    	var shi = document.getElementById("shi"); 
	    	shi.options.length=0;
	    	shi.options[0]=new Option("==请选择==",0);
	    }
	   	function xianshishi()
	   	{
	   		var sheng=document.getElementById("sheng");
	   		xmlHttpRequest = createXMLHttpRequest();
	    	//2.设置回调函数，这个函数就是当服务器端作出回应之后，调用的方法，在该方法中取出服务器端返回给xmlHttpRequest的回应，然后将这个回应返回给客户端
	    	xmlHttpRequest.onreadystatechange = haolejiaowo;
	    	var url = "${pageContext.request.contextPath}/liandong?shengname=" +sheng.value;
	    	xmlHttpRequest.open("get",url,true);
	    	//4.客户端通过这个xmlHttpRequest组件发送请求至服务器端的Servlet。
	    	xmlHttpRequest.send(null);
	    	loadimage();
	   	}
	   	var image=null;
	   	function loadimage()
	   	{
		   	if(image==null)
		   	{
		   		image=document.createElement("img");
		   	}
	   	
	   		image.src="loading.gif";
	   		image.setAttribute("width", "20");
	   		image.setAttribute("height", "20");
	   		document.getElementById("shi").parentNode.insertBefore(image, document.getElementById("shi"));
	   		image.style.display="inline";
	   	}
	   	function endLoadimage()
	   	{
	   		image.style.display="none";
	   	}
	</script>

`HTML`部分：  
>
	<div>
		省：<select id="sheng" onchange="xianshishi()">
				<option value="qingxuanzhe">==请选择==</option>
				<option value="hubei">湖北</option>
				<option value="hunan">湖南</option>
				<option value="sichuan">四川</option>
		</select>
		市：<select id="shi" >
		</select>
	</div>
	
`Servlet`部分：  
	public class liandong extends HttpServlet 
	{
		List<String> listsheng;
		Map<String, List> shenshimap=new HashMap<>();
		@Override
		public void init(ServletConfig config) throws ServletException 
		{
			super.init(config);
			listsheng=new ArrayList<>();
			listsheng.add("武汉");
			listsheng.add("黄冈");
			shenshimap.put("hubei",listsheng);
>			
			listsheng=new ArrayList<>();
			listsheng.add("长沙");
			listsheng.add("涟源");
			shenshimap.put("hunan",listsheng);
>			
			listsheng=new ArrayList<>();
			listsheng.add("成都");
			listsheng.add("江由");
			shenshimap.put("sichuan",listsheng);
		}
		public void doGet(HttpServletRequest request, HttpServletResponse response)
				throws ServletException, IOException 
		{
			response.setContentType("text/html ;charset=gbk");
			PrintWriter out = response.getWriter();
			String shengname = request.getParameter("shengname");
			if(!shengname.equals("qingxuanzhe"))
			{
				List<String> ls = shenshimap.get(shengname);
				StringBuilder sb = new StringBuilder();
				for(String str:ls)
				{
					sb.append(str);
					sb.append(",");
				}
				System.out.println(sb);
				sb.deleteCharAt(sb.length()-1);
				out.println(sb.toString());
				out.flush();
				out.close();
			}
		}
	}
	
##使用Ajax实现自动补全
注：onchange事件不是在内容一改变就立即触发的，而是这个元素失去焦点之后才会触发。

##我们自己写的Ajax框架
##DWR
我们自己写的`Ajax`框架有各种问题。市面上也有很多种。比较有名的就是`DWR`。  
`DWR`：`Direct Web Remoting`  
优点：开源免费，功能强大。  
###DWR框架使用步骤
1. 将必须使用的2个jar包导入到web/inf/lib目录之下。  
2. 配置Web.xml文件。这里对Java类的配置与Servlet的配置是一样的。  
3. 在该框架中，可以使用普通的Java类，不是Servlet亦可。所以就需要针对这个Java类创建并配置dwr.xml文档。  
4. 
示例代码如下：  
创建的要被DWR框架使用的类代码：  
>
	package com.trilever
	class Hello
	{
		public String sayHello()
		{
			return "hello";
		}
	}
	
`dwr.xml`中的配置代码：  
>
	<dwr>
		<allow>
			<create creator="new" javascript="JHello">
				<param name="class" value="com.trilever,Hello" />
				<include method="sayHello" />
			</create>
		</allow>
	</dwr>



#总结 
浏览器的功能是编译`HTML`文件中的内容，如果`HTML`中同时含有客户端执行的描述性语言例如：`JavaScript`，那么浏览器同样会对其进行编译。最后将整份网页的执行结果呈现与浏览器窗口中。  
实际上，所有的网页都是一份存储在网页服务器中的文件。  
静态网页：单纯使用`HTML`语法构成的网页。  
动态网页，依据执行程序所处的位置分为"客户端处理"、"服务器处理"。  
客户端处理：在`HTML`语法中假如`JavaScript`语法。从而让网页产生一些多媒体效果。就是让`JS`程序在客户计算机中进行处理。  
服务器端处理：使用`PHP`、`JSP`语言等。当客户发出要求的时候，网页服务器会先将这个网页文件进行运行处理(例如`JSP`网页会先在`Java`虚拟机中进行处理)，在将处理后的结果发送给客户端的浏览器。服务器处理的有点就是能与用户进行互动，并且能够访问数据库，将执行结果实时响应给用户。`JavaScript`、`JSP`这些都是内嵌与`HTML`的程序语言。  
`JavaScript`虽然能够达到与客户进行互动的目的，但是功能上有缺陷，就是无法使用整合服务器上的资源，例如文件操作与数据库访问。  
使用JS的网页只算单纯的动态网页，在客户端执行动态效果。服务器一旦将网页送出，就再也无法与其沟通，无法达到真正的交互目的。客户无法通过客户端的JS来进行服务器上的操作，所以，发展出服务器端网页语言(`PHP`，`JSP`等)以解决相关问题。  

在`JSP`中调用另一个`JSP`或者`Servlet`的时候，比如，在表单的`action`中设置为另一个`Servlet`或者`JSP`的时候，实际上就是重定向。  

 
类中的静态块一般不推荐使用。  

代码`${pageContext.request.contextPath}`的作用是取出部署的应用程序名，这样不管如何部署，所用路径都是正确的。 