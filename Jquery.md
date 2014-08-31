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
基本选择器是jQuery中最常用的选择器,也是最简单的选择器,它通过元素id,class和标签名等来查找DOM元素(在网页中id只能使用一次, class允许重复使用).  
1、#id,用法:$("#myDiv");返回值：单个元素的组成的集合  
说明: 这个就是直接选择html中的id="myDiv"根据id查找DOM对象。  
$("#myDiv")：查找id为myDiv的元素。  
2、Element,用法:$("div")，返回值：集合元素  
说明:element其实就是html已经定义的标签元素,例如 div, input, a 等等.根据元素类型查找DOM对象。  
例如：$("div")：查找所有div元素  
3、class,用法:$(".myClass"),返回值:集合元素  
说明: 这个标签是直接选择html代码中class="myClass"的元素或元素组(因为在同一html页面中class是可以存在多个同样值的). 例如：$(".myClass")：查找所有class名为myClass的元素。  
4、*,用法: $("*"),返回值:集合元素 
说明: 匹配所有元素,多用于结合上下文来搜索  
例如：$("*")：查找所有元素。  
5、selector1, selector2, selectorN,用法:$("div,span,p.myClass"),返回值:集合元素  
说明: 将每一个选择器匹配到的元素合并后一起返回.你可以指定任意多个选择器, 并将匹配到的元素合并到一个结果内.其中p.myClass是表示匹配元素.p，class="myClass"。也就是查找满足多个选择条件之一的所有的元素。注意是查找并集。  
例如：$("div,span,p.myClass")：用于查找符合多个条件之一的所有元素对象。求并集。  
###层次选择器
如果想通过DOM元素之间的层次关系来获取特定元素, 例如后代元素, 子元素, 相邻元素, 兄弟元素等, 则需要使用层次选择器.  1 、ancestor descendant  
用法: $("form input");   返回值  集合元素  
说明: 在给定的父元素下匹配所有后代元素.这个要下面讲的”parent > child”区分开.注意是后代，也就是孙辈也包括。  
例如：$("body div")：查找body里面所有的div。包括孙辈的div。  
2、parent > child  
用法: $("form > input");    返回值  集合元素
说明: 在给定的父元素下匹配所有子元素.注意:要区分好后代元素与子元素。子元素只包括子辈，而不包括孙辈的。  
3、prev + next
用法: $("label + input") ;返回值  集合元素
说明: 匹配所有紧接在 prev 元素后的 next 元素  
例如;$("div + input"):返回在div标签后面第一个input标签。  
4、prev ~ siblings
用法: $("form ~ input") ;    返回值  集合元素
说明: 匹配 prev 元素之后的所有 siblings 兄弟元素.注意:是匹配之后的元素,不包含该元素在内,并且siblings匹配的是和prev同辈的元素,其后辈元素不被匹配.  
注意:("prev ~ div") 选择器只能选择"#prev"元素*后面的同辈元素*; 而jQuery中的方法siblings()与前后位置无关, 只要是同辈节点就可以选取.  
###过滤选择器
过滤选择器主要是通过特定的过滤规则来筛选出所需的 DOM 元素, 该选择器都以":"开头。  
按照不同的过滤规则, 过滤选择器可以分为基本过滤, 内容过滤, 可见性过滤, 属性过滤, 子元素过滤和表单对象属性过滤选择器.
####基础过滤选择器
1、:first用法: $("tr:first");返回值  单个元素的组成的集合
说明: 匹配找到的第一个元素  
例如：$("div:first")：返回第一个div。
2、:last用法: $("tr:last");返回值  集合元素
说明:匹配找到的最后一个元素.与 :first 相对应.  
例如：$("div:last")：返回最后一个div。
3、:not(selector)用法:$("input:not(:checked)");返回值:集合元素  
说明:去除所有与给定选择器匹配的元素.有点类似于”非”,意思是没有被选中的input(当input的type="checkbox").这里可以有选择器的嵌套。  
例如：$("div:not(.one)"):选择所有class不为one的div。  
4、:even用法: $("tr:even");返回值  集合元素
说明: 匹配所有索引值为偶数的元素，从 0 开始计数.js的数组都是从0开始计数的.例如要选择table中的行,因为是从0开始计数,所以table中的第一个tr就为偶数0.  
例如：$("div:even"):选择所有索引值为偶数的div。  
5、:odd用法: $("tr:odd") 返回值  集合元素
说明: 匹配所有索引值为奇数的元素,和:even对应,从 0 开始计数.  
例如：$("div:odd"):选择所有索引值为奇数的div。  
6、:eq(index)用法:$("tr:eq(0)");返回值  集合元素
说明: 匹配一个给定索引值的元素.eq(0)就是获取第一个tr元素.括号里面的是索引值,不是元素排列数.  
7、:gt(index)用法: $("tr:gt(0)");返回值  集合元素
说明: 匹配所有大于给定索引值的元素.  
8、:lt(index)用法: $("tr:lt(2)");返回值  集合元素  
说明: 匹配所有小于给定索引值的元素.  
9、:header(固定写法)用法: $(":header").css("background", "#EEE")    返回值  集合元素
说明: 匹配如 h1, h2, h3之类的标题元素.这个是专门用来获取h1,h2这样的标题元素.查找所有标题元素。  
10、:animated(固定写法),返回值  集合元素
说明: 匹配所有正在执行动画效果的元素  
####内容过滤选择器
内容过滤选择器的过滤规则主要体现在它所包含的子元素和文本内容上。  
1、:contains(text)，用法: $("div:contains('John')")，返回值  集合元素  
说明: 匹配包含给定文本的元素.这个选择器比较有用，当我们要选择的不是dom标签元素时,它就派上了用场了,它的作用是查找被标签”围”起来的文本内容是否符合指定的内容的.  
2、:empty,用法: $("td:empty"),返回值  集合元素  
说明: 匹配所有不包含子元素或者文本的空元素  
3、:has(selector)  
用法: $("div:has(p)").addClass("test"),返回值  集合元素  
说明: 匹配含有选择器所匹配的元素的元素.这个解释需要好好琢磨,但是一旦看了使用的例子就完全清楚了:给所有包含p元素的div标签加上class="test".  
4、:parent,用法: $("td:parent"),返回值  集合元素  
说明: 匹配含有子元素或者文本的元素.注意:这里是":parent",可不是".parent"哦!感觉与上面讲的":empty"形成反义词.
####可见度过滤选择器
可见度过滤选择器是根据元素的可见和不可见状态来选择相应的元素  
1、:hidden，用法: $("tr:hidden"),返回值  集合元素  
说明: 匹配所有的不可见元素，input 元素的 type 属性为 "hidden"的话也会被匹配到.意思是css中display:none和input type="hidden"的都会被匹配到.同样,要在脑海中彻底分清楚冒号":", 点号"."和逗号","的区别.  
2、:visible,用法: $("tr:visible"),返回值  集合元素  
说明: 匹配所有的可见元素.  
####属性过滤选择器
属性过滤选择器的过滤规则是通过元素的属性来获取相应的元素
1、[attribute],用法:$("div[id]");返回值  集合元素  
说明: 匹配包含给定属性的元素. 例子中是选取了所有带”id”属性的div标签.  
2、[attribute=value],用法: $("input[name='newsletter']").attr("checked", true);返回值  集合元素  
说明: 匹配给定的属性是某个特定值的元素.例子中选取了所有 name 属性是newsletter 的 input 元素.  
3、[attribute!=value],用法: $("input[name!='newsletter']").attr("checked", true);返回值  集合元素  
说明: 匹配所有不含有指定的属性，或者属性不等于特定值的元素.此选择器等价于:not([attr=value]),要匹配含有特定属性但不等于特定值的元素,请使用[attr]:not([attr=value]).之前看到的 :not 派上了用场.  
4、[attribute^=value],用法: $("input[name^='news']"),返回值  集合元素  
说明: 匹配给定的属性是以某些值开始的元素.,我们又见到了这几个类似于正则匹配的符号.现在想忘都忘不掉了吧?!
####子元素过滤选择器
1、:nth-child(index/even/odd/equation),用法: $("ul li:nth-child(2)"),返回值  集合元素  
说明: 匹配其父元素下的第N个子或奇偶元素.这个选择器和之前说的基础过滤(Basic Filters)中的 eq() 有些类似,不同的地方就是前者是从0开始,后者是从1开始.  
2、:first-child,用法: $("ul li:first-child"),返回值  集合元素  
说明: 匹配第一个子元素.":first" 只匹配一个元素,而此选择符将为每个父元素匹配一个子元素.这里需要特别点的记忆下区别.  3、:last-child,用法: $("ul li:last-child"),返回值  集合元素  
说明: 匹配最后一个子元素.":last"只匹配一个元素,而此选择符将为每个父元素匹配一个子元素.  
 4、: only-child,用法: $("ul li:only-child"),返回值  集合元素  
说明: 如果某个元素是父元素中唯一的子元素,那将会被匹配.如果父元素中含有其他元素,那将不会被匹配.意思就是:只有一个子元素的才会被匹配!
####表单对象属性过滤选择器
此选择器主要对所选择的表单元素进行过滤  
1、:enabled,用法: $("input:enabled"),返回值  集合元素  
说明: 匹配所有可用元素.意思是查找所有input中不带有disabled="disabled"的input.不为disabled,当然就为enabled啦.
2、:disabled,用法: $("input:disabled"),返回值  集合元素  
说明: 匹配所有不可用元素.与上面的那个是相对应的.  
3、:checked,用法: $("input:checked"),返回值  集合元素  
说明: 匹配所有选中的被选中元素(复选框、单选框等，不包括select中的option).这话说起来有些绕口.  
4、:selected,用法: $("select option:selected")   返回值  集合元素  
说明: 匹配所有选中的option元素.
####表单选择器
1、:input,用法: $(”:input”) ;   返回值  集合元素
说明:匹配所有 input, textarea, select 和 button 元素  
2、:text,用法: $(”:text”) ;  返回值  集合元素  
说明: 匹配所有的单行文本框.  
3、:password,用法: $(”:password”) ; 返回值  集合元素
说明: 匹配所有密码框.  
4、:radio,用法: $(”:radio”) ; 返回值  集合元素  
说明: 匹配所有单选按钮.  
5、:checkbox,用法: $(”:checkbox”) ; 返回值  集合元素  
说明: 匹配所有复选框  
6、:submit,用法: $(”:submit”) ;   返回值  集合元素  
说明: 匹配所有提交按钮.  
7、:image,用法: $(”:image”)   返回值  集合元素  
说明: 匹配所有图像域.  
8、:reset,用法: $(”:reset”) ;  返回值  集合元素  
说明: 匹配所有重置按钮.  
9、:button,用法: $(”:button”) ;  返回值  集合元素  
说明: 匹配所有按钮.这个包括直接写的元素button.  
10、:file,用法: $(”:file”) ;  返回值  集合元素  
说明: 匹配所有文件域.  
11、:hidden,用法: $(”input:hidden”) ; 返回值  集合元素  
说明: 匹配所有不可见元素，或者type为hidden的元素.这个选择器就不仅限于表单了,除了匹配input中的hidden外,那些style为hidden的也会被匹配.   
注意: 要选取input中为hidden值的方法就是上面例子的用法,但是直接使用 “:hidden” 的话就是匹配页面中所有的不可见元素,包括宽度或高度为0的,  


注意，以上的这些选择器时可以联合使用的。  

循环遍历选择器选择的结果：对选择结果集继续遍历，并施以回调函数。  
>
	var $inputs=$("input:hidden");
	$input.each(function(){
		alert("3");
	})
对于遍历，还有另外一个方法：  
>
	//OBJ是需要遍历的对象，callBack是对对象元素施行的回调函数。
	//index,设置遍历起点
	//data,遍历中的当前元素对象
	JQuery.each(OBJ,callBack(index,data));

在JQuery中，$input.val()是取出元素的值，$input.val(value)是设置元素的值。  

总之，无论是JS还是JS库如JQuery，作用都是对HTML中的DOM对象进行操作，如，增加一个DOM对象，修改某个DOM对象的样式等。  对HTML中的DOM对象进行操作，目的是改变HTML文档在用户面前的显示风格、样式。  


##JQuery中的DOM操作
DOM(Document Object Model—文档对象模型)：一种与浏览器, 平台, 语言无关的接口, 使用该接口可以轻松地访问页面中所有的标准组件。  
DOM 操作的分类:
1. DOM Core: DOM Core 并不专属于 JavaScript, 任何一种支持 DOM 的程序设计语言都可以使用它. 它的用途并非仅限于处理网页, 也可以用来处理任何一种是用标记语言编写出来的文档, 例如: XML。  
2. HTML DOM: 使用 JavaScript 和 DOM 为 HTML 文件编写脚本时,　有许多专属于 HTML-DOM 的属性。  
3. CSS-DOM:针对于 CSS 操作, 在 JavaScript 中, CSS-DOM 主要用于获取和设置 style 对象的各种属性。  


###DOM操作
####创建节点
使用 jQuery 的工厂函数$(html); 会根据传入的 html 标记字符串创建一个 DOM 对象, 并把这个 DOM 对象包装成一个 jQuery 对象返回.  
注意:  
1. 动态创建的新元素节点不会被自动添加到文档中, 而是需要使用其他方法将其插入到文档中;  
2. 当创建单个元素时, 需注意闭合标签和使用标准的 XHTML 格式. 例如创建一个<p>元素, 可以使用 $(“<p/>”) 或 $(“<p></p>”), 但不能使用 $(“<p>”) 或 $(“</P>”)  
3. 创建文本节点就是在创建元素节点时直接把文本内容写出来; 创建属性节点也是在创建元素节点时一起创建
例如：  
>
	val $input=$("<input/>");//创建了一个input节点。
	$input.attr("id","i1");
	$input.attr("name","s");//设置input节点的属性
####内部插入节点
append(content) :向每个匹配的元素的内部的结尾处追加内容  
appendTo(content) :将每个匹配的元素追加到指定的元素中的内部结尾处  
prepend(content):向每个匹配的元素的内部的开始处插入内容  
prependTo(content) :将每个匹配的元素插入到指定的元素内部的开始处  
####外部插入节点
after(content) :在每个匹配的元素之后插入内容  
before(content):在每个匹配的元素之前插入内容  
insertAfter(content):把所有匹配的元素插入到另一个、指定的元素元素集合的后面  
insertBefore(content) :把所有匹配的元素插入到另一个、指定的元素元素集合的前面  
以上方法不但能将新创建的 DOM 元素插入到文档中, 也能对原有的 DOM 元素进行移动.  
####查找节点
查找属性节点: 通过 jQuery 选择器完成.查找到所需要的元素之后, 可以调用 jQuery 对象的 attr() 方法来获取它的各种属性值  
####删除节点
remove(): 从 DOM 中删除所有匹配的元素,采用选择器选择出来的节点对象调用这个方法之后，就会删除自身。当某个节点用 remove() 方法删除后, 该节点所包含的所有后代节点将被同时删除. 这个方法的返回值是一个指向已被删除的节点的引用.  
empty(): 清空节点 – 清空元素中的所有后代节点(不包含属性节点).但节点中的属性不会被删除。  
####复制节点
clone(): 克隆匹配的 DOM 元素, 返回值为克隆后的副本. 但此时复制的新节点不具有任何行为.  
clone(true): 复制元素的同时也复制元素中的的事件。   

