##基础知识
为了简化 JavaScript 的开发, 一些 JavsScript 库诞生了. JavaScript 库封装了很多预定义的对象和实用函数。能帮助使用者建立有高难度交互的 Web2.0 特性的富客户端页面, 并且兼容各大浏览器。  
当前流行的 JavaScript 库有:jQuery, MooTools, Prototype, Dojo, YUI, EXT_JS DWR。  
jQuery是继prototype之后又一个优秀的Javascript框架。其宗旨是——WRITE LESS,DO MORE,写更少的代码,做更多的事情。  
它是轻量级的js库(压缩后只有21k) ，这是其它的js库所不及的，它兼容CSS3，还兼容各种浏览器 （IE 6.0+, FF 1.5+, Safari 2.0+, Opera 9.0+）。  
jQuery是一个快速的，简洁的javaScript库，使用户能更方便地处理HTML documents、events、实现动画效果，并且方便地为网站提供AJAX交互。也就是说，Jquery对前面的JS、Ajax等都进行了封装，使用更加简单了。所以，学习Jquery即可。  
jQuery还有一个比较大的优势是，它的文档说明很全，而且各种应用也说得很详细，同时还有许多成熟的插件可供选择。  
jQuery能够使用户的html页保持代码和html内容分离，也就是说，不用再在html里面插入一堆js来调用命令了，只需定义id即可。 
使用Jquery之前，必须添加Jquery的类库：jquery-2.1.1.js文件，放入到项目的WebRoot/js目录下(创建一个该目录),然后在使用的时候进行引用即可：  
	<head>
		<script type="text/javascript" src="/Jquery/WebRoot/js/jquery-2.1.1.js"></script>
	</head>
	
Jquery没有语法，只需要根据API进行使用即可。  

##什么是jQuery对象  
jQuery 对象就是通过jQuery包装DOM对象后产生的对象。  
jQuery 对象是 jQuery 独有的. 如果一个对象是 jQuery 对象, 那么它就可以使用 jQuery 里的方法。  
$("#test").html();
比如：$("#test").html(),意思是指：获取ID为test的元素内的html代码。其中html()是jQuery里的方法  
这段代码等同于用DOM实现代码：document.getElementById("test").innerHTML;  
虽然jQuery对象是包装DOM对象后产生的，但是jQuery无法使用DOM对象的任何方法，同理DOM对象也不能使用jQuery里的方法.乱使用会报错  
约定：如果获取的是jQuery对象, 那么要在变量名前面加上$.  
var $variable = jQuery对象
var variable = DOM对象


注意:  
1. JS代码如果没有放在funtion中，是可以自动执行的。无需调用就可以自动执行。  
2. JS代码具体应该放在哪里，如果是要自动执行的那些代码，就放在文件的最后面，如果是要被人调用的代码(也就是function中的代码)，那就放在head中。实际上所有的JS代码，最好都是放在最后面。  

##DOM对象与JQuery对象之间的转换  
DOM对象转换为JQuery对象：  
对于已经是一个DOM对象，只需要用$()把DOM对象包装起来，就可以获得一个jQuery对象了。$(DOM对象) 。  转换后就可以使用 jQuery中的方法了。  
>
	var elementName = document.getElementById("pname");
    var $elementName = $(elementName);

Jquery对象转换为DOM对象：  
两种转换方式将一个jQuery对象转换成DOM对象：[index]和get(index);  
1 jQuery对象是一个数组对象，可以通过[index]的方法，来得到相应的DOM对象  
2 jQuery本身提供，通过.get(index)方法，得到相应的DOM对象  
代码示例如下：  
>
	<script type="text/javascript">
	    var $nameElement = $("#pname");
	    alert($nameElement.val());
	    //JQuery对象转换为DOM对象
	    var nameElement = $nameElement[0]; 
	    alert(nameElement.value);
	    //JQuery对象转换为DOM对象
	    var nameElement = $nameElement.get(0); 
	    alert(nameElement.value);
    </script>

##jQuery选择器
选择器是 jQuery 的根基, 在 jQuery 中, 对事件处理, 遍历 DOM 和 Ajax 操作都依赖于选择器。  
jQuery 选择器的优点:  
1. 简洁的写法  
2. 完善的事件处理机制  

###基本选择器
基本选择器是jQuery中最常用的选择器,也是最简单的选择器,它通过元素id,class和标签名来查找DOM元素(在网页中 id 只能使用一次, class允许重复使用).
1、#id,用法:$("#myDiv");返回值：单个元素的组成的集合  
说明: 这个就是直接选择html中的id="myDiv"  
2、Element,用法:$("div")，返回值：集合元素  
说明:element其实就是html已经定义的标签元素,例如 div, input, a 等等.  
3、class,用法: $(".myClass"),返回值:集合元素  
说明: 这个标签是直接选择html代码中class="myClass"的元素或元素组(因为在同一html页面中class是可以存在多个同样值的). 4、*,用法: $("*"),返回值:集合元素 
说明: 匹配所有元素,多用于结合上下文来搜索  
5、selector1, selector2, selectorN,用法:$("div,span,p.myClass"),返回值:集合元素  
说明: 将每一个选择器匹配到的元素合并后一起返回.你可以指定任意多个选择器, 并将匹配到的元素合并到一个结果内.其中p.myClass是表示匹配元素.p class="myClass"

