#Java中的XML
--------------
##基础知识
数据存储的几种方式：  
1. 键值对形式，`name："li"`  
2. `XML`描述  
3. 数据库，可以存储数据和操作数据  
数据量比较大的时候，使用数据库。  
`XML`:扩展性(`Extended`)标识语言，用于标识描述数据信息。其是`SGML`标识语言的简化。  
再，由于`HTML`语言的语法不够严谨，所以在`HTML`4.0.1版本之后，将其过渡至`XHTML`(扩展性`Extended`）语言,`XHTML`更加严谨、规范。`XHTML`可以理解为是`HTML`向`XML`语言过渡的中间产物。  

需求1  
两个程序间进行数据通信？例如，qq聊天的时候，我们原来使用字符串的形式进行客户端间的通信，但是字符串形式的表现能力太差，所以，实际上是使用XML文件进行客户端之间的通信。  
需求2  
给一台服务器，做一个配置文件，当服务器程序启动时，去读取它应当监听的端口号、还有连接数据库的用户名和密码?  
其作用就是：方便应用程序获得想要的信息。  
需求3  
xml可以充当小型的数据库。  
xml文件做小型数据库，也是不错的选择，我们程序中可能用到一些经常要人工配置的数据，如果放在数据库中读取不合适(因为你要增加维护数据库工作)，则可以考虑直接用xm来做小型数据库 【比如msn中保存用户聊天记录就是用xml文件的】，而且直接读取文件显然要比读取数据库快。


XML语言出现的根本目标在于描述在现实生活中经常出现的有关系的数据。  
在XML语言中，它允许用户自定义标签。一个标签用于描述一段数据；一个标签可分为开始标签和结束标签，在开始标签和结束标签之间，又可以使用其它标签描述其它数据，以此来实现数据关系的描述。  

`HTML`与`XML`之间的区别在于：  
1. `HTML`是固定标识，只能写它能识别的标识。`XML`中的标签可以自由定义。  
2. `HTML`较为随意，`XML`很严谨。  
3. `HTML`将数据和其显示结合在一起，不方便对数据进行操作。`XML`将数据与其显示分开，纯粹对数据进行描述。数据描述是数据描述，显示是显示。

`XML`的好处：  
1. 结构严谨，规范。  
2. 是标准的数据交换文件，已用于通用的配置信息文件。  
3. 结构简单，便于书写，增强阅读性。  
4. 将数据进行结构化，是数据之间具有明确的层次关系。

`XML`是被设计用于描述数据的，重点是：什么是数据，如何存放数据。描述相关。  
`HTML`是被设计用来显示数据的，重点是：显示数据以及如何更好的显示数据。显示相关。  
实际上XML文档中，元素的属性部分也可以写在元素里面作为元素自己的一个子元素(节点)进行使用的。  
注意：当一个属性的属性值既有单引号，又有双引号的时候，**“**相当于**&quot;**。'相当于aqos;

`XML`文档同样可以引用`CSS`样式表。  
`XML`文档还可以引用`XSL`样式表。  
`XML`都是表示显示的内容的，而样式表是为了控制显示样式的。


`XML`文档中**只能有一个顶层元素**。如上面的`<students>`这个元素，也叫*根元素*。也就是最外层的标签，只能有一个。

`CDATA`区：当我们的文档中，某一段数据不想被解析，只希望作为单纯的字符而已。那么，可以将这段字符放在`CDATA`区，放在`<![CDATA[ *数据* ]]>`标签里面。这样，里面的数据就能够不解析，而是作为单纯的字符。  
`XML`中所有标记的属性值必须用""或者''括起来。  
`XML`中名字可以使用字母、数字、中文（需要在`XML`声明的时候知道`encoding`属性)。  
`XML`主要的作用就是做配置文件，偶尔会有一点数据的交换。`XML`语法了解即可。不必深究、记住。  
`XML`应用：  
1. 在`AJAX`中使用`XML`回传数据。  
2. 使用`XML`存储少量数据。  
3. 使用`XML`作为表现层。用的很少。  
4. 使用`XML`作为作为数据传输的中间格式。  
5. 作配置文件。

##XML语法
`XML`文档示例：  
>
	<?xml version="1.0" encoding="UTF-8" ?>
	<?xml:stylesheet type="text/css" href="student.css" ?>
	<students>
		<student num="001">
			<name>zhangsan</name>
			<age>20</age>
		</student>
>		
		<student num="002">
			<name>lisi</name>
			<age>22</age>
		</student>
>		
		<student num="003">
			<name>wangwu</name>
			<age>21</age>
		</student>
>		
		<student num="004">
			<name>zhaoliu</name>
			<age>23</age>
		</student>
	</students>
	
第二行是样式表处理指令。使用CSS或者XSL样式表。
xml文档同样可以用CSS进行修饰。   
 
##DTD
所谓的DTD：就是对xml文件的约束，因为在实际的xml文件中，是不能出现某些元素的(因为实际中这些元素的出现是不合适的)，所以，需要DTD文件对xml文件中能够出现的元素予以约束。  
xml约束：在XML技术里，可以编写一个文档来约束一个XML文档的书写规范，这称之为XML约束。  
常用xml约束技术：XML DTD技术与XML Schema技术。  
DTD文件必须是用UTF-8字符。  
实际中，我们并不用写出DTD文件，而是需要根据DTD文件写出正确的XML文件。  
DTD元素见图`DTD元素`。  
同样可以在DTD文件中来设置限制XML文件中每一个元素的属性，见图`XML属性`  

对一个XML文件进行约束需要3个文件，DTD文件用于描述XML文件必须满足的条件，check文件用于运行，作用是检查xml文件中的元素是否满足DTD文件中的要求；xml文件就是描述数据。  我们依据DTD文件写完一个xml文件，应该再写一个check文件，当然，这个检验必须是使用IE浏览器才能使用的，因为这个check文件中使用的是IE浏览器中内嵌的一个对象进行XML文件的检验。用于判断、检查xml文件是否有错误。见git/xml目录下3个文件。  
在DTD文档中，可以定义一个实体，以后可以在XML文档中引用这个实体，实际上就是对一段内容的引用。  

示例如下：  
>
	myClass2.xml 
	<?xml version="1.0" encoding="utf-8" ?>
	<!DOCTYPE 班级 SYSTEM "myClass.dtd">
	<班级>
		<学生  学号="a123" 性别="女" 大哥="a0002">
			<名字>周星驰 &intro;</名字>
			<年龄>23</年龄>
			<介绍>学习刻苦 &intro; &intro;</介绍>
		</学生>
		<学生 住址="天津" 性别="男" 学号="a0002" 大哥="a0002 a0003">
			<名字>林青霞</名字>
			 <年龄>32</年龄>
			<介绍>&intro;是一个好学生</介绍>
		</学生>
		<学生 住址="大观园"  性别="男" 学号="a0003" 大哥="a0002">
			<名字>贾宝玉</名字>
			 <年龄>16</年龄>
			<介绍>是一个好学生 &intro;</介绍>
		</学生>
	</班级>
>
	myClass.dtd
	<!ELEMENT 班级 (学生+)>
	<!ENTITY % tagname "名字">
	<!ENTITY % tagname1 "名字gg">
	<!ENTITY % tagname2 "名字kk">
	<!ELEMENT 学生 (%tagname;,年龄,介绍)>
	<!ATTLIST 学生 
		住址 CDATA #IMPLIED
		学号 ID	 #REQUIRED
		大哥 IDREFS #REQUIRED
		性别 (男|女) #REQUIRED
		公司 CDATA #FIXED "ibm"
		>
	<!ELEMENT %tagname; (#PCDATA)>
	<!ELEMENT 年龄 (#PCDATA)>
	<!ELEMENT 介绍 (#PCDATA)>
	<!ENTITY intro "这是一个好同志呀!">	
>	
	check.html
	<html>
	<head>
	<script language="javascript">
	var xmldom= new ActiveXObject("Microsoft.XMLDOM");
	xmldom.validateOnParse=true;
	xmldom.load("myClass2.xml");
	document.writeln("错误信息是:"+xmldom.parseError.reason);
	document.writeln("错误行号:"+xmldom.parseError.line);
	</script>
	</head>
	<body>
	</body>
	</html>	
	
##Java中对XML文件的解析
XML解析分为：dom解析和sax解析  
dom：(Document Object Model, 即文档对象模型) 是 W3C 组织推荐的处理 XML 的一种方式。  
sax： (Simple API for XML) 不是官方标准，但它是 XML 社区事实上的标准，几乎所有的 XML 解析器都支持它。  

###Dom解析：
XML解析器：Crimson(sun)、Xerces (ibm->apache)、Aelfred2(dom4j)。  
XML解析API：Jaxp(sun)、dom4j，是进行DOM解析。  

DOM模型(document object model)
DOM解析器在解析XML文档时，会把文档中的所有元素，按照其出现的层次关系，解析成一个个Node对象(节点)。
在dom中，节点之间关系如下：
位于一个节点之上的节点是该节点的父节点(parent)
一个节点之下的节点是该节点的子节点（children） 
同一层次，具有相同父节点的节点是兄弟节点（sibling） 
一个节点的下一个层次的节点集合是节点后代(descendant)
父、祖父节点及所有位于节点上面的，都是节点的祖先(ancestor) 

####使用Dom4J来对XML文件进行解析
在Java中使用Dom4J来对XML文件进行解析。获得根节点之后，就可以继续向下解析。解析方法如下例：  
>
	import java.io.File;
	import java.io.FileNotFoundException;
	import org.dom4j.Document;
	import org.dom4j.DocumentException;
	import org.dom4j.Element;
	import org.dom4j.io.SAXReader;
	public class Dom4JTest
	{
		public static void main(String[] args) throws DocumentException, FileNotFoundException
		{
			SAXReader reader = new SAXReader();
			File f = new File("D://git//git_note//XML//myClass2.xml");  
	        Document document = reader.read(f); 
	        Element e = document.getRootElement();
	        String str= e.getName();
	        System.out.println(str);
		}
	}

可以使用Dom4J对XML文档中的节点进行修改、删除、增加等操作。  

XPath：路径查询语言，用于在XML文件中查找信息的语言。其通过节点与属性进行查询，简化了XML中查找节点的过程(在没有使用XPath时，查找每一个元素的时候需要一层一层地查找，速度很慢)。其语法类似与正则表达式。  
一个XPath就是一个字符串，就好比，一个正则表达式就是一个字符串，用这个字符串在一个长字符串中查找符合正则表达式的部分。  
同样，一个XPath也是个字符串，用于在XML中查找符合XPath的节点。所以，XPath有其自己的语法规则，就好比正则表达式有其自己的语法规则一样。  
要添加JXen.jar包以使用XPath。  

####使用Jaxp进行XML的Dom解析  
步骤也是先获得Document对象再使用。  
JAXP 开发包是J2SE的一部分，它由javax.xml、org.w3c.dom 、org.xml.sax 包及其子包组成
在 javax.xml.parsers 包中，定义了几个工厂类，程序员调用这些工厂类，可以得到对xml文档进行解析的 DOM 或 SAX 的解析器对象。  

使用步骤：  
1. 调用 DocumentBuilderFactory.newInstance() 方法得到创建 DOM 解析器的工厂。  
2. 调用工厂对象的 newDocumentBuilder方法得到 DOM 解析器对象。  
3. 调用 DOM 解析器对象的 parse() 方法解析 XML 文档，得到代表整个文档的 Document 对象，进行可以利用DOM特性对整个XML文档进行操作了。

Jaxp解析XML代码示例：  
>
	import java.io.File;
	import java.io.IOException;
	import javax.xml.parsers.DocumentBuilder;
	import javax.xml.parsers.DocumentBuilderFactory;
	import javax.xml.parsers.ParserConfigurationException;
	import org.w3c.dom.Document;
	import org.w3c.dom.Element;
	import org.xml.sax.SAXException;
	public class JaxpTest
	{
		public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
		{
			DocumentBuilderFactory factory =
					DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			File f = new File("D://git//git_note//XML//myClass2.xml");
			Document document = builder.parse(f);
			Element e = document.getDocumentElement();
	        String str= e.getNodeName();
	        System.out.println(str);
		}
	}

同样可以进行增加、删除等操作。  

###Sax解析
Jaxp中也可用于进行sax解析。  

在使用 DOM 解析 XML 文档时，需要读取整个 XML 文档，在内存中构架代表整个 DOM 树的Doucment对象，从而再对XML文档进行操作。此种情况下，如果 XML 文档特别大，就会消耗计算机的大量内存，严重情况下可能还会导致内存溢出。  
SAX解析允许在读取文档的时候，即对文档进行处理，而不必等到整个文档装载完才会文档进行操作。
通过继承DefaultHandler ,来开发一个sax解析器。  

sax是一种推式的机制,你创建一个sax 解析器,解析器在发现xml文档中的内容时就告诉你(把事件推给你). 如何处理这些内容，由程序员自己决定。
在基于sax 的程序中,有五个最常用sax事件处理方法：  
1. startDocument() ----> 告诉你解析器发现了文档的开始,告诉你解析器开始扫描文档.  
2. endDocument() ---> 告诉你解析器发现了文档尾  
3. startElement()------>  告诉你解析器发现了一个起始标签,该事件告诉你元素的名称,该元素所有的属性名和值.  
4. character() -----> 告诉你解析器发现了一些文本,将得到一个字符数组, 该数组的偏移量和一个长度变量,有这三个变量你可以得到解析器所发现的文本.  
5. endElement()-----> 告诉你解析器发现了一个结束标签,该事件告诉你元素的名称  


Sax解析步骤：  
1. 使用SAXParserFactory创建SAX解析工厂。`SAXParserFactory spf = SAXParserFactory.newInstance();`  
2. 通过SAX解析工厂得到解析器对象。`SAXParser sp = spf.newSAXParser();`  
3. 将解析对象和XML文件、事件处理器对象关联。`sp.parse("src/myClass.xml", new MyHander());`在使用Sax解析xml文件的时候，是采用事件驱动的解析方式，对事件的处理方法都封装在事件处理器中，例如，解析到xml文件的结尾的时候，就会启动该事件处理器对象中封装的endDocument()方法。

Sax解析一般只有读取，没有删除、修改。Sax解析是XML文件解析中最简单的读取方式了。  
Sax解析代码示例：  
>
	import java.io.File;
	import java.io.IOException;
	import javax.xml.parsers.ParserConfigurationException;
	import javax.xml.parsers.SAXParser;
	import javax.xml.parsers.SAXParserFactory;
	import org.xml.sax.Attributes;
	import org.xml.sax.SAXException;
	import org.xml.sax.helpers.DefaultHandler;
	class myHandler extends DefaultHandler
	{
		@Override
		public void startDocument() throws SAXException
		{
			System.out.println("startDocument");
			super.startDocument();
		}
		@Override
		public void endDocument() throws SAXException
		{
			System.out.println("endDocument");
			super.endDocument();
		}
		@Override
		public void startElement(String uri, String localName, String qName,
				Attributes attributes) throws SAXException
		{
			System.out.println("startElement");
			super.startElement(uri, localName, qName, attributes);
		}
		@Override
		public void endElement(String uri, String localName, String qName)
				throws SAXException
		{
			System.out.println("endElement");
			super.endElement(uri, localName, qName);
		}
	}
	public class SaxTest
	{
		public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException
		{
			SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
			SAXParser saxParser = saxParserFactory.newSAXParser();
			saxParser.parse(new File("D://git//git_note//XML//myClass2.xml"),new myHandler());
		}
	}

#XML Schema
XML Schema： 也是一种用于定义和描述 XML 文档结构与内容的模式语言，其出现是为了克服 DTD 的局限性。  
功能等价与DTD文档。同样，只要求通过Schema文件写出对应的XML文档即可。  

XML Schema VS DTD：  
XML Schema符合XML语法结构。  
DOM、SAX等XML API很容易解析出XML Schema文档中的内容。  
XML Schema对名称空间支持得非常好。  
XML Schema比XML DTD支持更多的数据类型，并支持用户自定义新的数据类型。  
XML Schema定义约束的能力非常强大，可以对XML实例文档作出细致的语义限制。  
XML Schema不能像DTD一样定义实体，比DTD更复杂，但Xml Schema现在已是w3c组织的标准，它正逐步取代DTD。  


XML Schema 文件自身就是一个XML文件，但它的扩展名通常为.xsd。
一个XML Schema文档通常称之为模式文档(约束文档)，遵循这个文档书写的xml文件称之为实例文档。
和XML文件一样，一个XML Schema文档也必须有一个根结点，但这个根结点的名称为Schema。
编写了一个XML Schema约束文档后，通常需要把这个文件中声明的元素绑定到一个ＵＲＩ地址上，在XML Schema技术中有一个专业术语来描述这个过程，即把XML Schema文档声明的元素绑定到一个名称空间上，以后XML文件就可以通过这个URI（即名称空间）来告诉解析引擎，xml文档中编写的元素来自哪里，被谁约束。

Schema文档：myclass.xsd  
>
	<?xml version="1.0" encoding="UTF-8" ?> 
	<xs:schema xmlns:xs="http://www.w3.org/2001/XMLSchema"
						  targetNamespace="http://www.itcast.cn"
						  elementFormDefault="qualified">
		<xs:element name=‘班级' >
			<xs:complexType>
				<xs:sequence maxOccurs='unbounded' >
					<xs:element name=‘学生' >
						<xs:complexType>
							<xs:sequence>
								<xs:element name=‘名字' type='xs:string' />
								<xs:element name=‘年龄' type='xs:integer' />
								<xs:element name=‘介绍' type='xs:string' />
							</xs:sequence>
						</xs:complexType>
					</xs:element>
				</xs:sequence>
			</xs:complexType>
		</xs:element>
	</xs:schema>

其中targetNamespace，表示将这个xsd文件中的所有元素都绑定到targetNamespace指向的命名空间中。类似于包的概念。  
这里的xs，指向另外一个命名空间，这个命名空间和targetNamespace这个命名空间不同，targetNamespace这个指的是自己定义的元素。而xs指向的命名空间中包含的是element、complexType这些在后面使用的元素。  


xml文档：  
>
	<?xml version="1.0" encoding="UTF-8"?>
	<itcast:班级 xmlns:itcast="http://www.itcast.cn"
					xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
					xsi:schemaLocation=“http://www.itcast.cn myclass.xsd">
		<itcast:学生>
			<itcast:名字>周星驰</itcast:名字>
			<itcast:年龄>29</itcast:年龄>
			<itcast:介绍>学习很刻苦</itcast:介绍>
		</itcast:学生>
	</itcast:班级>
	
xsi:schemaLocation：这个表示的是本XML文件引用的Schema约束文档。  
xmlns:xsi：表示所使用的命名空间，来自W3C的命名空间。  
xmlns:itcast：表示使用我们自己写的命名空间。  


语法部分参看W3C文档即可。
