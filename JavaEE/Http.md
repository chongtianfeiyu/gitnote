#Http协议
--------------------
Http协议：WEB浏览器与WEB服务器之间的一问一答的交互过程必须遵循一定的规则，这个规则就是HTTP协议。  
HTTP是hypertext transfer protocol（超文本传输协议）的简写，它是TCP/IP协议集中的一个应用层协议，用于定义WEB浏览器与WEB服务器之间交换数据的过程以及数据本身的格式。  
浏览器与服务器之间的能够连接（所谓的连接是TCP连接，所谓的请求是HTTP请求）数目是有限的(这也就是所谓的并发数量)，所以，每一次连接之后，就需要断开连接。也就是说一个连接不能长时间占用连接资源。每一个HTTP请求都需要使用其下层的TCP连接。  

当我们浏览一个网页的时候，首先发出一次请求，请求了整个网页的部分内容（例如网页的文字内容），然后依次还有很多次请求被发出，用于请求这个网页中所包含的其他内容，如网页中图片等内容，这些图片内容不是在第一次就请求的，而是在后面有多次请求的。  

在Http1.0与Http1.1的区别在于：  
1.0中，每次请求的到回应之后，就断开连接，例如上例，第一次请求整个网页的文字内容得到回应之后，就会断开此次连接。然后在请求这个网页中的一个图片的时候，就会再次连接，然后获得回应，再断开连接。也就是每次请求都使用一个单独的连接。（没一个HTTP请求都需要一个TCP连接）。浏览器与WEB服务器的连接过程是短暂的，每次连接只处理一个请求和响应。对每一个页面的访问，浏览器与WEB服务器都要建立一次单独的连接。浏览器到WEB服务器之间的所有通讯都是完全独立分开的请求和响应对  
1.1中，在一个TCP连接上可以传送多个HTTP请求和响应。多个请求和响应过程可以重叠进行。增加了更多的请求头和响应头。也就是说，一次TCP连接可以用于多次HTTP请求。  

Http请求的格式：  
请求消息的结构：一个请求行、若干消息头、以及实体内容，其中的一些消息头和实体内容都是可选的，消息头和实体内容之间要用空行隔开。  
请求消息结构示例：  
>
	GET /books/java.html HTTP/1.1   //请求行
	Accept: */*
	Accept-Language: en-us
	Connection: Keep-Alive
	Host: localhost
	Content-Length: 0
	User-Agent: Mozilla/4.0
	Accept-Encoding: gzip, deflate   //多个消息头
>	
	……     //实体内容


Http响应的格式：一个状态行、若干消息头、以及实体内容，其中的一些消息头和实体内容都是可选的，消息头和实体内容之间要用空行隔开。实际上显示在网页上的姿势一部分的返回内容。  
响应消息的实体内容就是网页文件的内容，也就是在浏览器中使用查看源文件的方式所看到的内容。  
一个使用GET方式的请求消息中不能包含实体内容，只有使用POST、PUT和DELETE方式的请求消息中才可以包含实体内容。  
对于HTTP 1.1来说，如果HTTP消息中包括实体内容，且没有采用chunked传输编码方式，那么消息头部分必须包含内容长度的字段，否则，客户和服务程序就无法知道实体内容何时结束。  
在HTTP协议中，还可以使用简单的请求消息和响应消息，它们都没有消息头部分。简单的请求消息只能用于GET方式，且请求行中不用指定HTTP版本号。对于简单的请求消息，服务器返回简单的响应消息，简单的响应消息中只返回实体内容。  


使用HttpWatch工具或者fireBug工具以查看一次Http请求、Http响应的内容等信息。  
Http请求中，请求行与消息头中不能出现中文，如果有中文，就会将其变为URL编码，实际上就是中文字符的十六进制编码格式。Java中有一个类，用于转换URL编码。  



对数据库的操作就是CRUD：增删改查：create，read，update，delete。


