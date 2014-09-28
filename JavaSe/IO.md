#Java中输入输出
-----------------
A、键盘输入：  
输入使用`Scanner`类对象进行。涉及密码输入时使用`Console`类对象进行（不显示密码）
系统里，`System.in`代表键盘。如果直接使用这个会很麻烦，一般都会对其进行包装：
包装成`BufferedReader`，这个更安全，而且有更完善的异常机制。
JDK1.5之后，出现`Scanner`，这个更方便，但在异常机制上有不足。
>
	Scanner sr = new Scanner(System.in);//这样就将键盘这个对象包装成Scanner类对象。
	while(sr.hasNextLine())//判断是否还有下一行输入。同理还可以判断是否有下一个字符的输入
	{
		System.out.println(sr.nextLine());
	}

注：多线程最困难的地方就是：出错难以调试。因为线程调用是随机的。
B、屏幕输出：
>
	System.out.println（）方法即可输出。


文件输入与输出：

A、文件读入：
读取文件的时候，使用一个`file`对象构造出一个`Scanner`对象。
这样就将一个文件内的内容读入到内存中了。
>
	Scanner in=new Scanner（new file（“myfile.txt”））；

如果文件路径中有“\”，那么需要使用转义字符。
>
	Scanner in=new Scanner（new file（“C:\\myfile.txt”））；

这样才行。`in`这个输入流中就是这个文件中的内容。然后就可以使用`Scanner`这个类的方法对这个输入流进行读取了。

B、文件写入：
>
	PrintWriter out = new PrintWriter(“myfile.txt”);//将写入流对象与文件相连接。

定位文件时可使用绝对路径:
>
	C:\\myfile\\myfile.txt;或者/home/me/myfile.txt;

当然，流最后都需要关闭。
>	
	out.close（）；


注：输入流就相当于一个管子。管子本来是空的。实际上就是表示在内存中的内容。
输入流对象这个管子里面是什么需要内容与外界的输入进行连接，这个流对象在哪里显示需要与外界的显示输出连接起来。也就是说，一个流就是内存中的一块空间。需要在两端与外界连接。这才是流的特征，有来源有出口。

和控制台的输入相连接：
>
	Scanner in=new Scanner(System.in)；//这是将输入流对象in与系统控制台输入进行连接。
	Console inc=System.console();//这是将输入流对象inc与系统密码输入相连接。

与控制台的输出相连接：
>
	String name=in.nextLine();//将输入流对象与name这个输出的东西进行连接。	

任何一个输入、输出流对象都需要在两端进行连接，然后就可以使用。只不过流对象的功能不同。各种不同的流对象都是针对不同的两端的内容的。两端内容不同，使用不同的流对象。

#Java中的输入与输出
-----------
所谓的输入与输出，实际上就是以*内存为中心*，硬盘↔内存，内存→显示屏，键盘→内存，网络↔内存。这三者之间的信息传输的过程。
##基础知识
`File`类：代表硬盘里面的一个文件或者目录。  
`File`类的方法：  
1. `listRoots()`：列出所有的根目录。  
2. `exists()`:判断文件、目录的存在。  
3. `getPath()`:获取文件的路径。  
4. `mkdir`:创建文件。  
5. `listFiles()`：列出当前目录下所有的文件。  
6. `listFiles(FileFilter filter)`：列出当前目录下符合条件的文件与目录。  
7. `listFiles(FilenameFilter filter)`：列出当前目录下符合条件的文件与目录。  

`File`类使用示例：  
> 
	import java.io.*;
	import java.util.*;
	public class fileTest
	{
		public static void main(String[] args) 
		{
			//get the path of the file.
			File file = new File("E:/git/git_note");
			System.out.println(file.getPath());
>			
			//list the roots of the disk
			File[] roots = File.listRoots(); 
			System.out.println(Arrays.toString(roots));
>			
			//verify the existense of the directory then mkdir
			File file1 = new File("git_note");
			System.out.println(file1.exists());
			if(!file1.exists())
			{
				file1.mkdir();
			}	
>			
			File file2 = new File("E:/Books");
			//list the directories in the disk
			File[] file3 = file2.listFiles();
			for(File temp_file:file3)
			{
				System.out.println(temp_file);
			}
>					
			//list all the  files with specified suffin in the directory		
			myFilterlist(file2);
			//list all the files in the directory	
			mylist(file2);
		}
>	
		//list all the  files with specified suffin in the directory
		public static void myFilterlist(File dir) 
		{
			File[] temp = dir.listFiles(new FileFilter()
				{
				public boolean accept(File pathname) 
				{
					try
					{
						if(pathname.getCanonicalPath().endsWith(".txt"))
							{
								return true;
							}
					}
					catch(IOException ex)
					{
						ex.printStackTrace();
					}
					return false;
				}
				}
				);
				for(File f2:temp)
				{	
					System.out.println(f2);
				}
>							
				File[] temp1 = dir.listFiles();
				for(File f1:temp1)
				{
					if(f1.isDirectory())
						{
							myFilterlist(f1);
						}
				}
		}
>		
		//list all the files in the directory
		public static void mylist(File dir) 
		{
			if(dir.isDirectory())
			{
				File[] temp = dir.listFiles();
				for(File f2:temp)
				{	
					if(f2.isFile())
					{
						System.out.println(f2);
					}
					else
					mylist(f2);
				}
			}
		}
	}
 
`I/O`流：`File`类只能访问磁盘中的文件与目录*名字与路径*，但是不能*读取文件*。  
如果要读取文件，就需要使用`I/O`流。  

按**流的方向**来分：  
分为输入、输出流。  
注：输入、输出流都是在内存的角度来看的。  

按**流处理的数据类型**来分：  
>
字节流：处理的对象是字节。功能强大，例如：图片、音乐。  
字符流：处理的对象时字符。主要用于文本文件，如：`txt`文件。处理文本文件时，比字节流更方便。

按**流的角色**来分：  
>
节点流：直接与一个`I/O`的物理节点（如，磁盘上的文件、网络等）关联。  
包装流(处理流/过滤流)：以节点流为基础，包装之后得到的流。  

常用的有4个抽象流类：  
`InputStream`、`OutputStream`：字节流。  
`Reader`、`Writer`：字符流。  
所有的`I/O`流都是以上的四个流为**基础**的。

##各种流类的使用  
**一个流对象相当于一根水管**，里面的每一滴水就相当于一个数据单元，如果是字节流，那就是一个字节。如果是字符流，那就相当于一个字符。  
  
a. 对于输入流而言，创建一个输入流对象的时候，里面就有数据。  
例如:  
将一个文件`File`对象包装进一个输入流对象。里面就拥有了水滴。我们要做的就是将这个输入流对象中的那些数据传送到程序中，也就是内存中。  
b. 对于输出流而言，是不同的。创建一个输出流对象的时候，里面是没有数据的，也就是说里面没有水滴，我们所要做的就是将程序中所产生的数据传送到这个空水管中。
  
按照节点流类与包装流类进行分类：
节点流类：表示与一个外界的实体相连接的流类。如，文件、网络等。  
包装流类：不是直接与外界实体相连接，而是将节点流类进行包装之后的流类，功能更强大。

`IM:`  
节点流类与包装流类的异同点在于：  
1. 节点流类：每一个节点流类都是与特定的节点相连接的管子。用于内存与对应的节点之间相连。也就是说一个节点流类只能用于一种节点。因为每一个节点流类的构造函数只能接受一种特定的参数。  
2. 包装流类：每一个包装流类都是可以与任何种类的节点相连接的管子。其作用也是用于内存与节点之间相连接。每一个包装类是可以用于任何一种节点的。因为每一个包装类的构造函数可以接收各种不同种类的节点流类，这些不同的节点流类代表了各种不同的节点。  


怎样选择合适的流类？  
1. 对于内存而言，当要其向外输出内容的时候，要使用output、writer流类，构造时构造方法里面的参数是输出数据的目的地，无论是节点还是节点流类均有。  
2. 对于内存而言，当需要向内存中写入内容的时候，要使用input、reader流类，构造时构造方法里面的参数书输入数据的来源，无论是节点还是节点流类均有。  

###节点流类  
a. 文件节点流（与文件相连接，对文件内容进行操作）。  
b. 数组节点流（与字节、字符数组相连接，对数组内容进行操作）。  
c. 管道节点流（与管道相连接，对管道内容进行操作）。  
d. 字符串节点流（与字符串相连接，对字符串内容进行操作）。  


**文件节点流**：用于*访问文件的节点流*。如同一个管道，用于内存与文件相连接。  
`FileInputStream`、`FileOutputStream`：文件字节流。  
`FileReader`、`FileWriter`：文件字符流。  

`FileInputStream`方法：  
 `read()/read(byte[] b)`：将文件中的字节读入到内存中。  

`FileOutputStream`方法：  
 `write(byte[] b)`：将内存中的`byte`数组写入到文件中。

文件节点流类使用举例：
>
	import java.io.*;
	import java.util.*;
	public class fileStreamTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct a fileinputstream,just like get a pipe with water in it.that you can pipe the water into the RAM.
			FileInputStream fis = new FileInputStream("E:/Java_source/fileStreamTest.java");
>
			//read the file by one byte
			System.out.print((char)fis.read());
>
			//read all the file by bytes 
			byte[] buffer1 = new byte[64];
			int len1 = 1;
			while((len1 = fis.read(buffer1))!=-1)
			{
				System.out.print(new String(buffer1,0,len1));
			}
>
			//read the file by bytes into a specified length buffer arrays 
			FileInputStream fis1 = new FileInputStream("E:/Java_source/fileStreamTest.java");
			byte[] buffer = new byte[128];
			fis1.read(buffer);
			System.out.println(new String(buffer));
>
			//output into the file with the FileOutputStream class 
			FileOutputStream fis2 = new FileOutputStream("E:/Java_source/2.java");
			byte[] buffer3 = "hello".getBytes();
			fis2.write(buffer3);
			fis2.close();
		}
	}



**数组节点流类**：用于访问数组。如同一个管道，用于内存和数组相连接。  
`ByteArrayInputStream`、`ByteArrayOutputStream`：访问字节数组流类。  
`CharArrayReader`、`CharArrayWriter`：访问字符数组流类。

数组节点流类使用举例：  
>	
	import java.io.*;
	public class ArrayStreamTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct a ByteArrayOutputStream,just like get a pipe without water in it.that you can pipe water into it.
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
>
			byte[] buffer = "hello".getBytes();
			bos.write(buffer);
>		
			byte[] content = bos.toByteArray();
			for(byte b : content)
			{
				System.out.println((char)b);
			}
		}
	}


**管道节点流类**：如同一个管道，该流类用于用于线程与线程相连接。  
`PipedInputStream`、`PipedOutputStream`：访问管道字节流类。  
`PipedReader`、`PipedWriter`：访问管道流字符流类。  
所谓的管道：两个线程之间进行通信的连接。所以要使用管道节点流类，需要运行两个线程，内存中的两个线程都与管道相连接，其中一个线程具有管道输入流对象，另一个线程具有管道输出流对象。然后两个线程就可以通过管道进行线程间数据的传输。  
管道节点流使用示例：  
>
	import java.io.*;
	class sender extends Thread
	{
		private PipedOutputStream out=new PipedOutputStream();
		public  PipedOutputStream getPipeOutputStream()
		{
			return out;
		}
		public void run()
		{
			String s=new String("hi,how are you");
			try
			{
				out.write(s.getBytes());
				out.close();
			}
			catch(IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	class reciever extends Thread
	{
		private PipedInputStream in=new PipedInputStream();
		public PipedInputStream getPipeInputStream()
		{
			return in;
		}
		public void run()
		{
			byte[] buf=new byte[1024];
			String str=null;
			try
			{
				int len=in.read(buf);
				str=new String(buf,0,len);
				System.out.println(str);
				in.close();
			}
			catch(IOException e)
			{
				System.out.println(e.getMessage());
			}
		}
	}
	public class pipeStreamTestDrive
	{
		public static void main(String args[])throws Exception
		{
			sender s=new sender();
			reciever r=new reciever();
			PipedOutputStream outs=s.getPipeOutputStream();
			PipedInputStream ins=r.getPipeInputStream();
			outs.connect(ins);
			s.start();
			r.start();
		}
	}


**字符串节点流类**：用于访问字符串。如同一个管道，用于内存与字符串相连接。它们就没有字节流类了，只有字符流类。  
`StringReader`、`StringWriter`：访问字符串流类。以字符串作为节点。  

字符串节点流类使用示例：
>

`System.in`就是一个字节流对象，其就是和键盘相联系的。  
`System.out`也是一个字节流对象，是与显示器相联系的。

###包装流类
就是将*节点流类包装成新的流类*。  
`IM：`  
包装类的原理就是：*同样是使用这些流类将内存与节点相连接*，只不过相对于节点流类(直接使用节点本身进行构造)而言，这些节点都已经被包装了，而不再是赤裸外露的节点了。包装类也是一个管子，只不过同一个管子可以用于各种不同的节点，因为包装节点流类的构造方法的参数是各种各样的节点流类，也就是说它不用自己直接与节点打交道，而是与节点流类打交道。更方便了。
节点流是直接与`I/O`节点（文件、键盘、网络、磁盘等）相关联的，包装之后构成包装流类效率更高。  
包括以下几种：  
a. 缓冲流类。  
b. 过滤流类。  
c. 打印流类。  
d. 转换流类。  

注意：所有的流类，无论是节点流类还是包装流类，全部都继承自`InputStream`、`OutputStream`、`Reader`、`Writer`流类。

**缓冲流类**：由于内、外存的读取速度不一样。所以需要缓冲流类来进行缓冲。  
其实际上也是一个管子，只不过这个管子可以用于各种`I/O`节点（文件、键盘、网络、磁盘等），均可。也就是说，缓冲流类的构造方法里的参数可以是上面所言的所有种类的节点流类，可以是文件节点流类，也可以是管道节点流类，也可以是数组节点流类等。  
缓冲流类实际上是通过*将其他的节点流类包装形成的新的缓冲流类*。建立于过滤流之上。将字节流转换为字符流其好处是字符流类可以调用`readLine()`每次读取一行。  
由缓冲流类的构造方法可以看出：缓冲流类对象实际上都是`InputStream`、`OutputStream`、`Reader`、`Writer`流对象包装而成的。   
`BufferedInputStream`、`BufferedOutputStream`：实际上是`InputStream`、`OutputStream`对象包装而成的流类。  
`BufferedReader`、`BufferedWriter`：实际上是`Reader`、`Writer`对象包装而成的流类。  


**过滤流类**：实际中我们在程序中并不是直接使用节点流类，而是使用通过包装各节点流类获得的过滤流类。一般都是将*过滤流类*包装成*缓冲流类*进行使用的。  
使用过滤流的优点：  
1. 其建立在节点流的基础之上，可以消除节点流之间的差异，这样就会更加方便地进行面向过滤流编程。  
2. 使用过滤流的方法进行`I/O`更加便捷。  
`FilterInputStream`、`FilterOutputStream`:  
`FilterReader`、`FilterWriter`：  

过滤流类使用示例如下：
>

**打印流类**：用于在屏幕、打印机等上面打印的流类。
`PrintStream`、`PrintWriter`：都是用于将内存中的内容进行输出，不过前者输出至屏幕，后者输出至打印机。  
打印流类使用示例：  


**转换流类**：用于将*字节流转换为字符流*。很有用。  
`InputStreamReader`、`OutputStreamWriter`:转换流类。  
使用转换流类的原因是：字符流类可以用`ReadLine()`方法，可以一次阅读一行，而字节流类都没有这个方法。所以转换为字符流类更实用。  

转换流类、缓冲流类使用示例：
>
	import java.io.*;
	public class StreamReaderTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct args Stream class
			FileInputStream fis= new FileInputStream("E:/Java_source/fileStreamTest.java");
>		
			//transform the stream class into a reader class
			InputStreamReader reader = new InputStreamReader(fis);
>
			//transform the reader class into a Bufferedreader class
			BufferedReader br = new BufferedReader(reader);
>
			//use the readLine() func of BufferedReader to read a line in the text
			String tempstr = null;
			while((tempstr = br.readLine())!=null)
			{
				System.out.println(tempstr);
			}
		}
	}


**两个特殊的流对象**：`DataInputStream`、`DataOutputStream`,它们继承了过滤流。  
它们的特点就是：拥有的方法更多，可以读、写各种数据。  
`Data`流对象使用示例：
>
	import java.io.*;
	public class DataStreamTest
	{
		public static void main(String[] args) throws Exception
		{
			//construct a Stream class
			FileOutputStream fis= new FileOutputStream("E:/Java_source/fileStreamTest1.java");
>			
			//construct the DataOutputStream. 
			DataOutputStream dos = new DataOutputStream(fis);
>	
			//write the string into the file
			String tempstr = new String("hello");
			dos.writeBytes(tempstr);
			dos.close();
>			
			//construct a Stream class
			FileInputStream fis1= new FileInputStream("E:/Java_source/fileStreamTest.java");
>	
			//construct the DataInputStream.
			DataInputStream dis = new DataInputStream(fis1);
>			
			//read the string from the file
			byte[] by = new byte[1280];
			dis.readFully(by); 
			String str = new String(by);
			System.out.println(str);
			dis.close();
		}
	}

所有以`InputStream`结尾的流类都是*字节输入流*。  
所有以`OutputStream`结尾的流类都是*字节输出流*。  
所有以`Reader`结尾的流类都是*字符输入流*。  
所有以`Writer`结尾的流类都是*字符输出流*。  

**两个特殊的流对象**：`ObjectInputStream`、`ObjectOutputStream`，这两个类是用于序列化储存对象。见`Java`中的序列化。  

##Java中的RandomAccessFile类使用
-----------------
该类可以任意访问文件，也就是说，想访问文件中的哪个点就访问哪个点。  
特征：  
1. 可以读，可以写，相当于`inputStream`、`outputStream`的合体。还可以在末尾增加。不会覆盖原有的文件内容。  
2. `RandomAccessFile`的局限性：只能访问文件。别的都不能使用。  

在构建这个对象的时候，需要制定读写模式，("r")("rw")模式。  
体现了这个类的`Random`特性的方法是`seek(long pos)`:用于将记录指针移动到任意指定的位置。默认的记录指针在文件首部。  
1. 使用`RandomAccessFile`来在文件后面追加字符：  
a. 将记录指针移动到文件末尾   
b. 执行写入。  
2. 使用`RandomAccessFile`来在文件中插入字符：  
a. 将记录指针移动到将要插入的位置   
b. 将记录指针后面的内容读取并保存  
c. 输出要插入的内容  
d. 输入已经保存的内容  

追加与插入示例：
>
	import java.io.*;
	public class RandomAccessFileTest
	{
		public static void main(String[] args) throws Exception
		{
			//random seek the position
			RandomAccessFile raf = new RandomAccessFile("good.txt","rw");
>		
			//read the file
			byte[] br =  new byte[1024];
			raf.read(br);
			String tempstr = new String(br);
			System.out.println(tempstr);
>		
			//write into the file at the end postion of the file
			raf.seek(raf.length());
			String tempstr1 = new String("hello world!");
			byte[] br2 = tempstr1.getBytes();
			raf.write(br2);
>	
			//insert into the file at the specified position
			//save the contents after the position into the ByteArrayOutputStream object
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] br3 = new byte[1024];
			raf.seek(100);
			int hasRead = -1;
			while((hasRead=raf.read(br3))!=-1)
			{
				bos.write(br3,0,hasRead);
			}
>		
			//insert the string you want into the postion
			raf.seek(100);
			String tempstr2 = new String("hello world!");
			byte[] br4 = tempstr2.getBytes();
			raf.write(br4);
>		
			//insert the contents that have been saved into the end of the file currently
			raf.seek(raf.length());
			byte[] br5 =bos.toByteArray();
			raf.write(br5);
		}	 
	}
 

#Java中的序列化
--------------------
`Java`中的一切都是对象，放在内存中，我们常需要将`Java`中的对象通过序列化转换成二进制流来进行储存或者传输。  
转换成二进制流的目的：  
1. 我们有时候需要将对象储存在**外部存储器**中,这样，即使在程序结束运行之后，我们依旧可以从磁盘中将这个对象恢复出来。  
2. 有时候，我们需要将这些对象通过网络进行传输。  
出于以上的目的，我们就需要对这些对象进行序列化。序列化就是将内存中的对象与二进制流之间进行相互转换以进行储存或者传输。


那么Java中可序列化对象必须具有的特征：该对象的类必须实现以下任意两个接口之一：  
`Serializable`
接口(该接口中无任何方法，只是一个标志性的接口，实现该接口无需实现任何方法)、`Externalizable`
接口(该接口使用较少）。  

用于序列化的I/O流对象：  
`ObjectInputStream`：用于从二进制流中恢复对象。 最重要的方法是`readObject()`，用于从其他储存了对象的节点中读取对象，如，从文件中读取对象。  
`ObjectOutputStream`：用于将对象转换为二进制流进行储存。最重要的方法是`writeObject(Object obj)`用于将对象储存在一个`OutputStream中`，也就是储存于一个文件节点流中,如，将对象储存于文件中。

对象序列化与对象恢复使用示例：
>
	import java.io.*;
	//the class must implements the Serializable interface that it can be serialized
	class apple implements Serializable
	{
		private String name;
		private String color;
		apple()
		{
			this.name = "zhang";
			this.color = "red";
		}
		apple(String aname,String acolor)
		{
			this.name=aname;
			this.color=acolor;
		}
		public void setname(String aname)
		{
			this.name=aname;
		}
		public void setcolor(String acolor)
		{
			this.color=acolor;
		}
		public String getname()
		{
			return this.name;
		}
		public String getcolor()
		{
			return this.color;
		}
>	
		public String toString()
		{
			String str = "apple "+this.name+" "+this.color;
			return str;
		}
	}
>
	public class appleSerializableTest
	{
		public static void main(String[] args) throws Exception
			{
				apple ap = new apple("lisi","red");
				//after the finish of the program,the ap object will perish
				System.out.println(ap);
>			
				//write the ap object into the file and save it in the disk in binary stream form with the ObjectOutputStream class
				FileOutputStream fos = new FileOutputStream("apple.bin"); 
				ObjectOutputStream ops = new ObjectOutputStream(fos); 
				ops.writeObject(ap); 
>			
				//read the object from the file stored in the disk 
				FileInputStream fis = new FileInputStream("apple.bin"); 
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object bp = ois.readObject();
				apple mp = (apple)bp;
				System.out.println(mp.getname());
			}	
	}

#Java中序列化的机制
-----------
1. 对于一个要被序列化的对象，其所有的属性`Field`，也就是成员变量，都必须是可序列化的，也就是实现了`Serializable`接口。  
如：`Student`类有一个属性（成员变量）`Field`是`Teacher`。那么`Student`对象如果需要被序列化，那么除了`Student`类必须实现`Serializable`接口，它的属性`Field``Teacher`类也必须实现`Serializable`接口。这样才能保证能够被序列化。  
2. 序列化底层机制：  
a. 当我们每序列化一个对象，就会给这个对象一个编号。  
b. 如果是第一次序列化一个对象，就会真的将这个对象序列化成二进制流。  
c. 如果要序列化的对象已经在之前已经序列化过一次，那么此次就只序列化一个编号，而不会再序列化保存整个对象。也就是说只会序列化保存本次编号。  

这样的序列化机制的目的是：保证磁盘中的二进制流与内存中的对象相同。  

`transient`关键字：  
只能用于修饰属于实例对象的成员`Field`(不能是`static`修饰的类`Field`变量,因为`static`变量不储存于对象中）,表示对象的这个`Field`不能被序列化储存。  
使用`transient`防止对象的某些`Field`被序列化的原因是：对象的某些`Field`如：账号、密码等敏感信息就不能被序列化储存或者传输。所以需要使用`transient`对这些关键的`Field`进行修饰。

序列化底层机制示例：
>
	import java.io.*;
	//the class must implements the Serializable interface that it can be serialized
	class Student implements Serializable
	{
		private String name;
		private Teacher teacher;
		Student()
		{
		}
		Student(String aname,Teacher ateacher)
		{
			this.name=aname;
			this.teacher=ateacher;
		}
		public void setname(String aname)
		{
			this.name=aname;
		}
		public void setteacher(Teacher ateacher)
		{
			this.teacher=ateacher;
		}
		public String getname()
		{
			return this.name;
		}
		public Teacher getteacher()
		{
			return this.teacher;
		}
>		
		public String toString()
		{
			String str = "student "+this.name+" "+this.teacher.toString();
			return str;
		}
	}
>
		class Teacher implements Serializable
		{
			private String name;
		private int age;
		Teacher()
		{
		}
		Teacher(String aname,int aage)
		{
			this.name=aname;
			this.age=aage;
		}
		public void setname(String aname)
		{
			this.name=aname;
		}
		public void setage(int aage)
		{
			this.age=aage;
		}
		public String getname()
		{
			return this.name;
		}
		public int getage()
		{
			return this.age;
		}
>		
		public String toString()
		{
			String str = "teacher "+this.name+" "+this.age;
			return str;
		}
	}
>
	public class appleSerializableTestb
	{	
		public static void main(String[] args) throws Exception
			{
				Teacher tea = new Teacher("liu",23);
				Student stu = new Student("lisi",tea);
				//after the finish of the program,the ap object will perish
				System.out.println(stu);
>			
				FileOutputStream fos = new FileOutputStream("stu.bin"); 
				ObjectOutputStream ops = new ObjectOutputStream(fos); 
				//write the stu object into the file and save it in the disk in binary stream form with the ObjectOutputStream class
				ops.writeObject(stu); 
>			
				tea.setname("hu");
				tea.setage(50);
				//do not write the tea object into the file and do not save it in the disk in binary stream form with the ObjectOutputStream class again, because this object has been serialized in the object stu that it can not been serialized again
				ops.writeObject(tea); 
>			
				FileInputStream fis = new FileInputStream("stu.bin"); 
				ObjectInputStream ois = new ObjectInputStream(fis);
				//read the stu object from the file stored in the disk 
				Object bp = ois.readObject();
				//read the tea object from the file stored in the disk 
				//but the alteration of tea can not be read because the changed object can not be serialized 
				Object cp = ois.readObject();
				Student mp = (Student)bp;
				Teacher np = (Teacher)cp;
				System.out.println(mp.getname());
				System.out.println(np.getname());
			}	
	}

**完全自定义的序列化**：  
对于账号、密码等这些敏感信息，我们如果完全通过`transient`来阻止这些`Field`被序列化是不好的(在恢复的时候就根本没有了这些信息）。这时就可以使用定制的序列化来对这些`Field`进行加密。  

如果要使用自定义的序列化，就需要让这个**要被序列化的对象的类**实现两个方法：  
`private void writeObject(ObjectOutputStream out) throws IOException`、`private void readObject(ObjectInputStream in) throws IOException` 。  
但是要注意的是：这两个方法并不是`Serializable`接口中的方法。  
这两个方法是被**系统调用**以完成对象序列化的。注意是被系统自动调用，而不是被人为调用，我们在序列化对象的时候依旧是使用`writeObject()`、`readObject()`方法的。只不过这里实现了这两个方法表示在写入和读取的时候底层是使用我们自己定制的转换方法（可以理解为加密方法）。  

**版本号**：  
由于我们的类会经常修改、变化，所以无法知道在序列化读取的时候类是不是正确的。所以要给可序列化类一个版本号。
  
实际上上如果没有给版本号，系统也会默认给一个版本号。但是默认的这个版本号不稳定。  
使用`serialver.exe`工具以查看类的版本号: `serialver.exe UseaEncodeSerializableTest`  

自定义设置类的版本号：  
在类定义的最前面加上一句：`static final long serialVersionUID = 1.1;`即将该类的版本号设置为1，以后每修改一次这个类，就可以修改一下这个类的版本号。自定义版本号后更稳定。

使用方式示例：  
>
	import java.io.*;
	//the class must implements the Serializable interface that it can be serialized
	class user implements Serializable
	{
		//set the version of the class 
		static final long serialVersionUID = 1L;
>		
		private String name;
		private String password;
		user()
		{
			this.name = "zhang";
			this.password = "red";
		}
		user(String aname,String apassword)
		{
			this.name=aname;
			this.password=apassword;
		}
		public void setname(String aname)
		{
			this.name=aname;
		}
		public void setpassword(String apassword)
		{
			this.password=apassword;
		}
		public String getname()
		{
			return this.name;
		}
		public String getpassword()
		{
			return this.password;
		}
>	
		public String toString()
		{
			String str = "user "+this.name+" "+this.password;
			return str;
		}
>	
		//set method to encode the password by yourself that it can not be get after the serialization
		private void writeObject(ObjectOutputStream out) throws IOException
		{
			out.writeUTF(this.name);
			//you can encode the Field with the method you want
			out.writeUTF(new StringBuilder(this.password).reverse().toString());
		}
		//set method to read the fields but you can not decode the password
		private void readObject(ObjectInputStream in) throws IOException
		{
			this.name = in.readUTF();
			this.password = in.readUTF();
		}
	}	
>
	public class UseaEncodeSerializableTest
	{
		public static void main(String[] args) throws Exception
			{
				user us = new user("lisi","123");
				//after the finish of the program,the ap object will perish
				System.out.println(us);
>			
				//write the us object into the file and save it in the disk in binary stream form with the ObjectOutputStream class and the Field has been encoded then it can be saved or passed through the web
				FileOutputStream fos = new FileOutputStream("user.bin"); 
				ObjectOutputStream ops = new ObjectOutputStream(fos); 
				ops.writeObject(us);
>			
				//read the object from the file stored in the disk , but can not decode the password
				FileInputStream fis = new FileInputStream("user.bin"); 
				ObjectInputStream ois = new ObjectInputStream(fis);
				Object bp = ois.readObject();
				user mp = (user)bp;
				System.out.println(mp.getpassword());
			}	
	}	
**IM**：我们使用`I/O`流类的正常使用方法：  
1. 不会直接使用节点流类。而是将它们包装成包装类进行使用。  
2. 如果联系到键盘、文件、屏幕等节点设备，那么就是节点流类。  
a. *字节节点流类*要被包装成`BufferedInputStream`、`BufferedOutputStream`这样的包装缓冲流类使用。  
b. *字符节点流类*要被包装成`BufferedReader`、`BufferedWriter`这样的包装缓冲流类来使用。  
c. 如果是*字节节点流类*要转变成*字符包装类*使用，那么就使用`InputStreamReader`、`OutputStreamWriter`这样的转换流类进行转换为'字符包装类'。  

使用方法见下例：
>
	import java.io.*;
	public class StreamReaderTest
	{
		public static void main(String[] args) throws Exception
		{
			//this part is aimed at reading the file and printing it onto the screen
			//construct args Stream class object
			FileInputStream fis= new FileInputStream("E:/Java_source/fileStreamTest.java");
>		
			//transform the stream class object(file object) into a reader class object
			InputStreamReader reader = new InputStreamReader(fis);
>
			//transform the reader class object into a Bufferedreader class object
			BufferedReader br = new BufferedReader(reader);
>
			//use the readLine() func of BufferedReader to read a line in the text
			String tempstr = null;
			while((tempstr = br.readLine())!=null)
			{
				System.out.println(tempstr);
			}
>
>
			//this part is aimed at reading the keyboard and printing it onto the screen
			//transform the stream object(System.in is the keybord) into a reader class
			InputStreamReader reader = new InputStreamReader(System.in);
>
			//transform the reader class into a Bufferedreader class
			BufferedReader br = new BufferedReader(reader);
>
			//use the readLine() func of BufferedReader to read a line in the text
			String tempstr = null;
			while((tempstr = br.readLine())!=null)
			{
				System.out.println(tempstr);
			}
		}
	}

#Java中虚拟机读取其他进程数据
------------------
在`Java`中启动其他进程：  
`Runtime.getRuntime().exec()`;返回值是一个`Process`对象，就是一个进程，进程就是运行中的应用程序。  
注意：对于这个调用的进程而言，其输出的内容对于我们的`JVM`而言是输入，所以，`JVM`应该使用输入流。  

应用实例：  
>
	import java.io.*;
	public class ReadFromProcess
	{
		public static void main(String[] args) throws Exception
		{
			//create a process
			Runtime runtime = Runtime.getRuntime();
			Process proc = runtime.exec("javac.exe");
>
			//read from the process	
			InputStreamReader isr = new InputStreamReader(proc.getErrorStream()); 
			BufferedReader br = new BufferedReader(isr);
>		
			while(br.readLine()!=null)
			{
				System.out.println(br.readLine());
			}
		}	
	}


#Java中几个有用的类
----------
`Path`：接口。表示一个平台无关的路径。可以通过Paths工具类获得Path对象。  
传统的`File`类，既代表文件又代表路径。不好用。  
  
`Paths`：操作路径的工具类。里面有方法可以获得`Path`对象。  
`Files`：操作文件的工具类。可以直接操作文件。  
例如：要复制一个文件。不再需要使用I/O流进行复制、粘贴了。而是直接使用这个工具类即可。
`Arrays`：操作数组的工具类。  
`Collections`：操作集合的工具类。  
`Objects`：操作对象的工具类。  

`Files`类与`Paths`类使用示例：
>
	import java.nio.file.*;
	public class pathTest
	{
		public static void main(String[] args) throws Exception
		{
			//get the relative path
			Path path = Paths.get("1.java");
			Path path1 = Paths.get("4.java");
			Path path2 = Paths.get("C:/Python27/python.exe");
>			
			//get the absolute path and get the path root
			System.out.println((path.toAbsolutePath().getRoot()));  
>	
			//get the absolute path and git the path parents
			System.out.println((path.toAbsolutePath().getParent())); 
>		
			//test the file can be read
			System.out.println(Files.isReadable(path)); 
			//test the file is hidden
			System.out.println(Files.isHidden(path)); 
>		
			//copy the file in path to the file in path1
			Files.copy(path,path1,StandardCopyOption.REPLACE_EXISTING);
>		
			//test the file can be exec
			System.out.println(Files.isExecutable(path2));
		}
	}
