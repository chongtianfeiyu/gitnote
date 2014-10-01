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