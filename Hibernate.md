hibernate:冬眠,让java对象在DB中冬眠.  
数据库本质上就是磁盘上的一个文件。没有什么特别的。  
持久化技术：能够实现将数据永久保存起来的技术。例如，JDBC就是持久化技术，将JDBC包装起来的DAO层也是持久化技术。IO流也是持久化技术。Hibernate技术也是持久化技术，其作用是替代掉JDBC技术，因为JDBC是有缺点的。  
JDBC缺点：写SQL语句进行GRUB。但是会有例如字段不匹配、类型不匹配这些问题，还有需求经常改变的问题，需要耗费大量体力在这个CRUD上。原因是这种JDBC技术的面向对象的粒度太细。其只是面向字符串编程，编程过程只是在拼字符串。  
所以需要使用Hibernate技术，其最强的优点就是面向对象。将数据的访问细节包装起来。很方便地进行数据访问。  
使用Hibernate步骤：  
1、使用Hibernate所需jar包：  
	antlr-2.7.6.jar
	commons-collections-3.1.jar
	dom4j-1.6.1.jar
	hibernate3.jar
	javassist-3.9.0.GA.jar
	jta-1.1.jar
	slf4j-api-1.5.8.jar
	log4j-1.2.15.jar
	slf4j-log4j12-1.5.0.jar
	mysql-connector-java-5.0.8-bin.jar(数据库驱动)
	
2、创建一个实体类entity用于包装数据库表中的数据，类似于JavaBean。  
代码示例：  
>com.trilever.wt.bookType文件：  
>
	package com.trilever.wt;
	public class bookType
	{
		int bookTypeId;//书类别id
		int parentId;//父类别
		String bookTypeName;//书类别id
		int isDelete;//书是否可用，1为可用，2为不可用。
		String context;//备注
		public bookType()
		{
			super();
		}
		public bookType(int parentId, String bookTypeName, int isDelete,
				String context)
		{
			super();
			this.parentId = parentId;
			this.bookTypeName = bookTypeName;
			this.isDelete = isDelete;
			this.context = context;
		}
		public bookType(int bookTypeId, int parentId, String bookTypeName,
				int isDelete, String context)
		{
			super();
			this.bookTypeId = bookTypeId;
			this.parentId = parentId;
			this.bookTypeName = bookTypeName;
			this.isDelete = isDelete;
			this.context = context;
		}
		/**
		 * @return the bookTypeId
		 */
		public int getBookTypeId()
		{
			return bookTypeId;
		}
		/**
		 * @param bookTypeId the bookTypeId to set
		 */
		public void setBookTypeId(int bookTypeId)
		{
			this.bookTypeId = bookTypeId;
		}
		/**
		 * @return the parentId
		 */
		public int getParentId()
		{
			return parentId;
		}
		/**
		 * @param parentId the parentId to set
		 */
		public void setParentId(int parentId)
		{
			this.parentId = parentId;
		}
		/**
		 * @return the bookTypeName
		 */
		public String getBookTypeName()
		{
			return bookTypeName;
		}
		/**
		 * @param bookTypeName the bookTypeName to set
		 */
		public void setBookTypeName(String bookTypeName)
		{
			this.bookTypeName = bookTypeName;
		}
		/**
		 * @return the isDelete
		 */
		public int getIsDelete()
		{
			return isDelete;
		}
		/**
		 * @param isDelete the isDelete to set
		 */
		public void setIsDelete(int isDelete)
		{
			this.isDelete = isDelete;
		}
		/**
		 * @return the context
		 */
		public String getContext()
		{
			return context;
		}
		/**
		 * @param context the context to set
		 */
		public void setContext(String context)
		{
			this.context = context;
		}
	}

3、在Java中，使用第三方类库、框架的条件有两个：  
1. 添加对于的jar文件。  
2. 配置文件进行配置(对象-关系映射文件)。  
创建配置文件bookType.hbm.xml,该xml文件的dtd文件去Hibernate的jar包中找。注意，该xml文件时为2中所创建的实体entity类配置的，对应于这个entity类，也对应这个entity所映射的数据表。所以，这个xml文件必须与2中所写的entity类放在一起。同一个package内。  
代码如下：  
>com.trilever.wt.bookType.hbm.xml文件：  
>
	<?xml version="1.0" encoding="UTF-8"?>
	<!DOCTYPE hibernate-mapping PUBLIC 
	    "-//Hibernate/Hibernate Mapping DTD 3.0//EN"
	    "http://hibernate.sourceforge.net/hibernate-mapping-3.0.dtd">
	<hibernate-mapping>
	<!-- name属性对应entity实体对象
		table属性对应数据库中相应的表
	 -->
	<class name="com.trilever.wt.bookType" table="bookType" lazy="true">
		<!-- 可以认为id标签是用来映射主键的 
			name属性对应与实体类中相应属性的名字
			column属性对应与数据库表中的字段
			type属性是从sql中数据字段到java中实体类中属性类型之间的映射的类型，
			既不是sql数据库中字段类型，也不是java类中对应的属性类型。
		-->
		<id name="bookTypeId" column="bookTypeId" type="integer">
			<!-- generator用于字段主键生成策略，class属性值为自增 -->
			<generator class="increment"></generator>
		</id>
		<!-- 这里的property是用于映射其他的数据字段 -->
		<property name="parentId" column="parentId" type="integer"></property>
		<property name="bookTypeName" column="bookTypeName" type="string"></property>
		<property name="isDelete" column="isDelete" type="integer"></property>
		<property name="context" column="context" type="string"></property>
	</class>
	</hibernate-mapping>
	
4、以上只是配置了本项目的entity对象与数据库中相应的表之间的对应，而没有指定是来自于哪一个数据库，所以，需要增加src/hibernate.properties用于指定数据库。  
代码示例如下：  
>src/hibernate.properties文件：  
>
	hibernate.connection.driver_class=com.mysql.jdbc.Driver
	hibernate.connection.url=jdbc:mysql://localhost:3306/ygcbook
	hibernate.connection.username=root
	hibernate.connection.password=wt312041990
	//指定方言，以使得代码对不同数据库均可用。对底层数据的自身配置。
	hibernate.dialect=org.hibernate.dialect.MySQL5Dialect
	hibernate.show_sql=true
	
5、使用Hibernate进行CRUB：  
代码示例：  
>TestHibernate.java文件:  
>
	package com.trilever.wt;
	import java.util.List;
	import org.hibernate.Query;
	import org.hibernate.Session;
	import org.hibernate.SessionFactory;
	import org.hibernate.Transaction;
	import org.hibernate.cfg.Configuration;
	public class TestHibernate {
		//注意，以前的session是客户端与服务器之间的对话的存储
		//这里的session是程序与数据库之间的对话。也叫会话工厂。连接工厂，相当于一个连接池，
		//相当于数据源，这里使用的是Hibernate自带的数据源。
		//这里的SessionFactory相当于数据源。
		public static SessionFactory sf = null;
		//初始化数据源
		static
		{
			//加载src/hibernate.properties文件进入内存
			Configuration conf = new Configuration();
			//加载和类在一起的映射文件进入内存
			conf.addClass(bookType.class);
			sf = conf.buildSessionFactory();
		}
		/**
		 * 测试CRUB
		 * @param args
		 */
		public static void main(String[] args) {
	
		}
		//使用Hibernate层的作用，就是不用自己写sql语句，方便，高效。
		/**
		 * 增加
		 * @param bk
		 */
		private static void insertBookType(bookType bk)
		{
			//获得session，这session是与数据库之间的对话
			Session s = sf.openSession();
			//开启事务。
			//事务的优点就是：
			//a:原子性
			//c:consistency，一致性
			//i:隔离性，isolate
			//d:durable，持久性，长久保存
			Transaction tx = s.beginTransaction();
			s.save(bk);
			tx.commit();
			s.close();
		}
		/**
		 *删除 
		 * @param b
		 */
		private static void delete(bookType b)
		{
			Session s = sf.openSession();
			Transaction tx = s.beginTransaction();
			s.delete(b);
			tx.commit();
			s.close();
		}
		/**
		 * 批量删除
		 */
		private static void batchDelete()
		{
			Session s = sf.openSession();
			Transaction tx = s.beginTransaction();
			Query q = s.createQuery("delete from bookType b where b<4");
			q.executeUpdate();
			tx.commit();
			s.close();
		}
		/**
		 * 修改
		 * @param b
		 */
		private static void update(bookType b)
		{
			Session s = sf.openSession();
			Transaction tx = s.beginTransaction();
			s.update(b);
			tx.commit();
			s.close();
		}
		/**
		 * 按id查找
		 * @param id
		 * @return
		 */
		private static bookType findBookType(Integer id)
		{
			Session s = sf.openSession();
			Transaction tx = s.beginTransaction();
			bookType bk= (bookType) s.load(bookType.class, id);
			tx.commit();
			s.close();
			return bk;
		}
		/**
		 * 查找全部
		 * @return
		 */
		private static List<bookType> findAllBookType()
		{
			Session s = sf.openSession();
			Transaction tx = s.beginTransaction();
			//这里是hql，不是sql语句，所以下面的booktype是类名，而不是表名。
			Query q = s.createQuery("from bookType");
			List<bookType> list = q.list();
			tx.commit();
			s.close();
			return list;
		}
	}

注意：实际上Hibernate的底层就是JDBC技术+反射技术，其是对JDBC技术的封装。  
#Hibernate
Hibernate是连接java应用程序和关系型数据库的中间件。  
对JDBC API封装，负责对象持久化。  
位于持久化层，封装所有的数据访问细节，使业务逻辑层更关注于业务逻辑。  
一种ORM映射工具。  
现代软件分层见图`SoftWare_layer.png`、`SoftWare_layer1.png`所示：  
软件开发中的模型见图`SoftWare_Model.png`：
软件开发过程中：  
1. 设计概念模型，见图`SoftWare_Model.png`所示。  
2. 根据概念模型设计关系模型。见图`SoftWare_Model2.png`所示。  
3. 以上两点是在软件分析阶段的工作，在软件设计阶段要创建域模型，其是对概念模型的细化。见图`SoftWare_Model3.png`、`SoftWare_Model4.png`、`SoftWare_Model5.png`所示。  
所谓的域对象，就是指一个实体对象。一般都与一个数据表相对应。  
域对象(实际上就是类之间的关系)之间的关系：
a.关联关系，关联是类之间的引用关系。例如，B类以A类的属性声明的方式出现在A中。  
b.依赖关系，依赖是类之间的访问关系。例如，在A类的某一个方法内部实例化B类对象或者访问了B类的某一个方法。  
c.聚集关系，整体与部分之间的关系。例如，人与手之间的关系。部分类对象的不能单独存在，其生命周期依赖于整体类对象的生命周期，整体消失的时候，部分类也随之消失。聚集关系是逻辑上约束。在实际开发的过程中，聚集关系看上去并不是那么明显。往往很模糊，很多时候可以看做关联关系，也可以看做聚集关系。所以，这是视人为规定的。  
d.一般化关系，类之间的继承关系。

域对象的持久化概念  
实体域对象在创建之后，是放在内存中，不能永久存在，将实体域对象永久性保存起来，就是持久化过程。广义持久化指CRUB。  

什么叫ORM？见图`ORM.png`  
以我们前面所写的系统为例，我们所写的entity实体类部分就是属于业务逻辑层。业务逻辑层就是采用所谓的域模型。	而，将数据库中的一个表与这个一个entity对象联系起来的部分，就是所谓的持久化层。也就是ORM的作用。我们前面所写的DAO层就是这样的功能，将entity部分与后端的数据库的表部分联系起来，互相转换。  
业务逻辑层中的域模型中的域对象与数据库中的表的对应，见图`ORM2.png`  
ORM使用方法见图`ORM3.png`  

Hibernate API介绍见图`Hibernate_API.png`、`Hibernate_API1.png`  
Hibernate是对JDBC的轻量级包装，不是对全部的JDBC的包装。Java即可以使用Hibernate对数据库进行访问，也可以使用JDBC对数据库进行访问。  
Hibernate的缺点就是，不善于大批量数据处理，因为性能方面有欠缺。有些时候，使用JDBC还有优点。  
对于一个对象而言，什么叫线程安全，就是同一个时刻，可有多个线程能够访问这个对象。  
SessionFactory对象时重量级的，需要大量缓存，用于存放定义的sql语句与映射元数据(也就是映射)。由于其已经存放了定义的sql语句，所以当后面session进行CRUB的时候，不用再次生成sql语句，而是可以去SessionFactory的缓存中找到已经生成的sql语句，然后再直接执行即可。这就是SessionFactory的缓存的作用。注意，SessionFactory的缓存既不是一级缓存，也不是二级缓存，其就叫做缓存。  
Session的缓存才叫一级缓存。用于存放当前工作单元加载的对象。例如，在查找的时候，先去一级缓存里面查找，如果没有，就去DB中继续查找。第一次查询到结果之后，就会放在一级缓存中，下一次查询这个，就直接从一级缓存中取，这就是一级缓存的工作原理。  
代码示例：  
>
	Customer c1 = (Customer)session.load(Customer.class,new Long(1));
	//c2是在一级缓存里面取出来的。
	Customer c2 = (Customer)session.load(Customer.class,new Long(1));
	//c3是在数据库中取出来的。
	Customer c3 = (Customer)session.load(Customer.class,new Long(3));
	c1 == c2 ?
	c1 ！= c3 ?
	//id标签是用于映射OID的。
	<id name=“id” type=“long” column=“ID”>
	      <generator class=“increment” />
	</id>

持久层中的持久化类：就是我们创建的用于对应数据库中的表的类，例如bookType类。  
Hibernate持久化数据流见图Hibernate_Data_Flow.png,总之，就是Hibernate部分与客户端都对持久化类进行set与get操作。见图Hibernate_Data_Flow1.png。
注意，在持久化类中的方法里面，对于使用的客户而言，只能调用public的方法(因为类外只能调用public部分)，对于Hibernate而言，持久化类中的public与private方法都可以被Hibernate所使用。所谓的客户端调用某某方法，指的是创建持久化类对象，然后set、get该对象的属性。  

#Hibernate中XML文件的设置  
主要是通过设置xml文件中的property字段映射属性完成的。  

a.设置Access属性  
Hibernate访问持久化类属性的策略  
1.Access属性的默认值为property ：表明hibernate通过getXXX和setXXX来访问类属性。推荐使用。提高域模型透明性。  
2.Access属性还可以为field:hibernate通过java反射机制直接访问类属性。对于没有javabean方法的属性可设置该访问策略。  
根据以上两点，
1.在使用可以在持久化类中不设置某一个属性，但是有针对这个属性的set与get方法(使用property属性值)。Hibernate同样可以使用这个持久化类，因为Hibernate可以通过set、get方法访问属性而与这个属性是否存在无关。也就是说，只要在xml文件中映射了，无论持久化类中有没有该属性，都能在hql语句中访问到它(使用set、get方法)  
代码示例如下：  
>
	class Customer{
	     …..
	     private String firstname ;
	     private String lastname ;
	     public String getName(){
	           return firstname + “ ” + lastname ;
	     }
	     public void setName(String name){
	            StringTokenizer t = new StringTokenizer(name);
	            firstname = t.nextToken();
	            lastname = t.nextToken();
	     }
	} 
	在持久化类的方法中加入程序逻辑在customer.hbm.xml文件中无需映射firstname和lastname属性，而是映射name属性。
	<property name =“name” column=“NAME” />  
	尽管类中并没有name属性，由于hibernate不是直接访问Name属性，而是调用get、set方法，因此建立了Firstname、Lastname和表之间的联系。

2.如果是使用filed属性，那么可以没有set、get方法而是只有这个属性，Hibernate也可以使用这个持久化类。  
xml文件中property的Access属性设置：  
>
	<property name column type 
	access:设定访问级别,默认值是property(方法级访问)|field:字段级访问(不是通过set、get方法进行访问，而是直接通过反射技术访问该属性)
	formula:sql表达式
	insert:true|false
	update:true|false>
>
	<property name="name" access="field" />
b.设置formula属性
利用<property>元素的formula属性，用来设置一个sql表达式，hibernate将根据它来计算出派生属性的值。  
>
	<property name=“totalprice” formula=“(select sum(o.PRICE) from ORDERS o where o.CUSTOMER_ID=ID)” />

c.设置insert属性
<property> insert属性，若为false，在insert语句中不包含该字段，该字段永远不能被插入。默认值true。  

##设置类的包名  
如果在一个映射文件中包含多个类，并且这些类位于同一个包中，可以设置<hibernate-mapping>元素的package属性，避免为每个类提供完整的类名。  
代码示例：  
>可以这样写
	<hibernate-mapping package=“mypack”>
	    <class name=“customer” table=“cu..” >
	          ……
	     </class>
	    <class name=“Order” table=“orders” >
	          ……
	     </class>
	</hibernate-mapping>
>以前是这样写
	<hibernate-mapping>
	    <class name=“mypack.customer” table=“cu..” >
	          ……
	     </class>
	    <class name=“mypack.Order” table=“orders” >
	          ……
	     </class>
	</hibernate-mapping>

##映射对象标识符  
Java按地址区分同一个类的不同对象.  
关系数据库用主键区分同一条记录.  
Hibernate使用OID来建立内存中的对象和数据库中记录的对应关系。对象的OID和数据库的表的主键对应。为保证OID的唯一性，应该让Hibernate来为OID付值。  
Hibernate中用对象表示符(OID)来区分对象，OID是关系数据库中的主键在java对象模型中的等价物。在运行时，hibernate根据OID来维持java对象和数据库记录的对应关系。

##关系数据库主键必备条件  
1. 不允许null  
2. 唯一，不重复  
3. 值永远不会改变  
自然主键：把具有业务含义的字段作为主键叫做自然主键。需要手动录入的。也就是说不能够自动生成。如果是那种可以自动生成的主键值，就叫代理主键。   


##Hibernate内置标示符的设置  
>
	<id name=“id” type=“long” column=“ID”>
	      <generator class=“increment”/>
	</id>
	Increment:适用于代理主键。由hibernate自动以递增的方式生成表识符，每次增量为1.
	
	
注意：怎样有效读取一个随意大小的文件(例如，一个图片就是一个二进制文本)？  
代码示例：  
>
	FileInputStream fis = new FileInputStream(new File(D://image.jsp));
	byte[] bis = new byte[fis.avaliable()];
	fis.read(bis);
	fis.close();

#映射一对多的关联关系
在实际项目中，会有很多张表，表之间也会有关系。所以，我们为每一个表创建的持久化类之间也是有关系的。类之间的关系主要有四种：继承、包含等。  
在数据库中，采用外键，将某一个表中的某一个字段设为外键，表示对应这个表的持久化类是"多"，外键目的所在的那个表所对应的持久化类是"一"。  
在Hibernate中，可以根据我们创建的持久化类，而自动地在数据库中帮我们创建对应的数据表！！！  
在hibernate.properties中进行修改。  
hibernate.hbm2ddl.auto=create
create:先删除原有表,在创建.(没有数据)
update:只在表结构发生变化时,进行修改表操作.
create-drop:先删除原有表,在创建.(没有数据),程序运行结束后,再将所有表drop掉.


Hibernate未完………………