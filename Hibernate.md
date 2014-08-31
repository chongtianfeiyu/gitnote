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
Hibernate是连接java应用程序和关系型数据库的中间件。  
对JDBC API封装，负责对象持久化。  
位于持久化层，封装所有的数据访问细节，使业务逻辑层更关注于业务逻辑。  
一种ORM映射工具。  





注意：怎样有效读取一个随意大小的文件(例如，一个图片就是一个二进制文本)？  
代码示例：  
>
	FileInputStream fis = new FileInputStream(new File(D://image.jsp));
	byte[] bis = new byte[fis.avaliable()];
	fis.read(bis);
	fis.close();

