#JDBC连接池
传统的开发基于数据库的Web程序时，都是通过客户端请求Servlet，servlet调用Dao，Dao与数据库进行连接。一般步骤是：  
1. 在主程序中建立数据库连接。  
2. 进行sql操作。  
3. 断开数据库连接。 

但是这种模式的缺陷在于：  
使用普通JDBC连接数据库时，需要使用DriverManager来获取connection。每次连接的时候都需要将Connection加载如内存中，在验证用户名与密码(需要花费0.05~1s）。每次需要数据操作的时候，都需要重新连接、断开连接。会消耗大量资源，连接资源没有得到良好利用。如果同时有大量的连接、断开操作，就会占用大量资源，甚至是服务器崩溃。  
同时，连接用完没有断开，可能会导致内存泄露。  

所以，可以使用连接池，连接池中可以存放多个连接。客户端要使用连接的时候，直接从连接池获取连接，然后使用。当客户端使用完连接时，再把连接放回到连接池中。也就是说，连接池中的连接与数据库之间一直没有断开。允许应用程序可以多次重复使用同一个数据库连接，而不是每次都为程序创建新的连接。  
在服务器启动的时候，就会自动在连接池中创建、初始化n个连接。  
同时有一个最小空闲数，使得连接数是弹性变化的。应对访问量变大时的性能开销。  
还有一个最大连接数，也就是连接数不能无限制增加。  
后来的客户端在达到最大连接数之后，只能等待其他客户端放弃连接。这里依靠最大等待时间，达到时间之后，长时间占用连接没有操作就会断开这个连接。  
当同时有大量的连接被释放的时候，不能将所有的连接都释放了，而是会保留一部分，也就是存在一个最大释放数，放置把连接全部都释放了。  

数据库连接池  
编写连接池需实现java.sql.DataSource接口。DataSource接口中定义了两个重载的getConnection方法：  
Connection getConnection()  
Connection getConnection(String username, String password)  

实现DataSource接口，并实现连接池功能的步骤：  
在DataSource构造函数中批量创建与数据库的连接，并把创建的连接加入LinkedList对象中。  
实现getConnection方法，让getConnection方法每次调用时，从LinkedList中取一个Connection返回给用户。  
当用户使用完Connection，调用Connection.close()方法时，Collection对象应保证将自己返回到LinkedList中,而不要把conn还给数据库。  
Collection保证将自己返回到LinkedList中是此处编程的难点。  

两种开源的数据库连接池：  
1. DBCP连接池  
该连接池来自Apache，是对数据库连接词DataSouce接口的具体实现。其实Tomcat服务器的数据库连接池连接。可以与服务器一起使用，也可以单独使用。我们实际使用中不必自己重新对DataSource接口进行实现，使用DBCP连接池即可。  
需要使用几个包：JDBC驱动包、common pools包、DBCP包、Junit包（Junit包用于测试）、logging包。  
DBCP包中的BasicDataSource类就是实现了java.sql.DataSource接口，所以，我们实际使用中就是使用BasicDataSource类完成数据连接池。这个BasicDataSource类对象实际上就是一个数据源，也就是一个数据连接池对象。  
代码示例：  
>方法1：	
	//创建一个数据库连接池对象
	BasicDataSource bds = new BasicDataSource(); 
	//设置连接池属性
	bds.setInitialSize(10);
	bds.setMaxTotal(10);
	bds.setMaxIdle(5);
	bds.setMinIdle(3);
	bds.setMaxWaitMillis(10000);
	//获得连接
	bds.setUsername("root");
	bds.setPassword("wt312041990");
	bds.setUrl("jdbc:mysql://localhost:3306/ygcbook");
	bds.setDriverClassName("com.mysql.jdbc.Driver");
	try {
		Connection conn =bds.getConnection();
		System.out.println(conn);
	} catch (Exception e) {
		e.printStackTrace();
	}
>方法2：
	public static void main(String[] args) throws Exception {
		Properties properties = new Properties();
		properties.setProperty("username", "root");
		properties.setProperty("password", "wt312041990");
		properties.setProperty("url", "jdbc:mysql://localhost:3306/ygcbook");
		properties.setProperty("driverClassName", "com.mysql.jdbc.Driver");
		properties.setProperty("initialSize", "10");
		properties.setProperty("maxTotal", "10");
		properties.setProperty("maxIdle", "10");
		properties.setProperty("minIdle", "3");
		properties.setProperty("maxWaitMillis", "10000");
>		
		BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
		Connection conn = dataSource.getConnection();
		System.out.println(conn);
	}
>方法3：填写一个property文件，里面的键值对都是我们所需要的连接数据库要用的值。  
	public static void main(String[] args) {
		InputStream instream = DBCPTest2.class.getResourceAsStream("com\trilever\wt\DBCP.properies");
		Properties properties = new Properties();
		properties.load(inStream);
		BasicDataSource dataSource = BasicDataSourceFactory.createDataSource(properties);
		Connection conn = dataSource.getConnection();
		System.out.println(conn);
	}
>实际中DBCP连接的使用：  
	public static void main(String[] args) throws PropertyVetoException, SQLException
	{
		Connection conn = null;
		try
		{
			//创建一个数据库连接池对象
			BasicDataSource bds = new BasicDataSource(); 
			//设置连接池属性
			bds.setInitialSize(10);
			bds.setMaxTotal(10);
			bds.setMaxIdle(5);
			bds.setMinIdle(3);
			bds.setMaxWaitMillis(10000);
			//获得连接
			bds.setUsername("root");
			bds.setPassword("wt312041990");
			bds.setUrl("jdbc:mysql://localhost:3306/ygcbook");
			bds.setDriverClassName("com.mysql.jdbc.Driver");
			try {
				Connection conn = bds.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement("select * from booktype");
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next())
				{
					System.out.println(rs.getInt(1));
				}
			} catch (Exception e)
			{
				e.printStackTrace();
			}finally
			{
				//使用完之后，将连接归还给连接池，这里的close()方法与以前的close()方法不同，以前的close()方法是直接关闭连接的功能。
				conn.close();
			}
	}

2. C3P0数据库连接
需要4个包：c3p0-0.9.5-pre8.jar、c3p0-oracle-thin-extras-0.9.5-pre8.jar、mchange-commons-java-0.2.7.jar、mysql-connector-java-5.1.31-bin.jar。  
代码示例：  
>
	public static void main(String[] args) throws PropertyVetoException, SQLException
	{
		Connection conn = null;
		try
		{
			ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
			//设置连接池属性
			comboPooledDataSource.setInitialPoolSize(10);
			comboPooledDataSource.setMaxPoolSize(10);
			comboPooledDataSource.setMaxIdleTime(5);
			comboPooledDataSource.setMinPoolSize(3);
			comboPooledDataSource.setMaxConnectionAge(10000);
			comboPooledDataSource.setUser("root");
			comboPooledDataSource.setPassword("wt312041990");
			comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/ygcbook");
			comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
>			
			conn = comboPooledDataSource.getConnection();
			System.out.println(conn);
			PreparedStatement preparedStatement = conn.prepareStatement("select * from booktype");
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getInt(1));
			}
		} catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			//使用完之后，将连接归还给连接池，这里的close()方法与以前的close()方法不同，以前的close()方法是直接关闭连接的功能。
			conn.close();
		}
	}


3. JDBC中没有提供数据库连接池的功能。但是在常用的Web服务器中，是具有数据库连接池功能的，但是用服务器中自带的数据库连接池功能的缺点就是，只能在C/S项目中使用。调试比较麻烦，每次调试连接池功能必须要启动服务器，也就是说必须是Java Web项目才能使用，而不像我们使用DBCP或者C3P0，在任何项目中都可以使用，无论是C/S架构还是B/S架构中都行。一般我们在Java Web中都是以DBCP为主。  
4. 使用Web服务器提供的DataSource接口的实现类的时候，不用再像DBCP等那样，创建一个DataSource对象，而是可以通过添加一些配置，然后通过Java的JNDI进行使用。  
几个名词解释：  
命名服务：计算机系统中的一个基本功能，其是将名字与计算机中的一个对象相联系。通过名字可以找到对应的对象，例如，在计算机的文件系统中就使用了命名服务，这样，就可以通过暴露出来的文件名找到对应的文件对象。  
目录服务：目录服务是命名服务的延伸扩展，其不但可以保持名字与其对应的对象的匹配，而且保存了这个对象的属性。还能够对这个对象的属性进行增、删、改、查操作。
JNDI：就是Java命名与目录接口，是一些标准API接口。Java程序可以通过这些API访问命名目录服务。就可以通过命名目录服务使用名称访问对象。  
JNDI的定义不依赖与任何独立的命名目录服务器，对于各种命名目录服务器都可以通过统一的JNDI接口进行调用。  
JNDI在系统运行时会在内存中构建一个层次结构，通过程序访问到这个JNDI管理器的InitialContext对象就拥有了这个JNDI的上下文，就可以查找要用的对象，并使用它。  
可以简单地将JNDI理解为一种将对象与名称绑定的技术。  
对象工厂负责产生对象，这些对象都与唯一的名字绑定，外部程序可以通过名字来过的某个对象的引用。  
javax.naming包中提供了Context接口。  
使用Tomcat服务器中自带的连接池步骤：  
A. 为整个Tomcat中所有Web项目配置一个连接池  
1. 在服务器中的conf/context.xml文件中配置：  
>
	<Resource name="jdbc/mysql"
            auth="Container"
            type="javax.sql.DataSource"
            username="root"
            password="wt312041990"
            driverClassName="com.mysql.jdbc.Driver"
            url="jdbc:mysql://localhost:3306/ygcbook"
            maxActive="8"
            initialSize="5"
            maxIdle="4"/>
            
2. 第1步中的配置就是创建了一个连接池对象，这个连接池对象时Tomcat服务器帮助我们创建的。该对象的名称就是其name属性对于的值。外部可以通过其名称来使用该连接池对象。当服务器启动的时候，就会在服务器的公有区域中创建一个连接池对象。这个公有区域就是Java的命名环境上下文。注意，这里使用Tomcat自带的连接池，需要数据库的驱动JDBC包，但是这个包不能放在该项目自己的lib目录下，*而是要放在Tomcat服务器的lib目录下*。  
在JSP中使用连接池代码示例：  
>
	<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
	<%@ page import="javax.naming.Context" %>
	<%@ page import="javax.naming.InitialContext" %>
	<%@ page import="javax.sql.DataSource" %>
	<%@ page import="java.sql.*" %>
	<%
		//InitialContext类表示名称与对象的绑定，其是一个类。
		//**这里创建Context对象。其实就是执行命名操作的上下文对象。
		Context initCtx = new InitialContext();
		//这一句是固定写法，java:comp/env表示Java的环境命名上下文。可以将其看成为Tomcat服务器空间汇总的共有区域。
		//当服务器启动的时候，就会在服务器的公有区域中创建一个连接池对象。
		//这个公有区域就是Java的命名环境上下文。
		//**利用初始化上下文对象查找Java的环境命名上下文。
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		//**在java环境命名上下文中查找连接池对象,转换为javax.sql.DataSource类型
		DataSource ds = (DataSource)envCtx.lookup("jdbc/mysql");
		//通过datasource获得connection对象
		Connection conn = ds.getConnection();
		try
		{
			String sql = "select * from booktype";
			PreparedStatement preparedStatement = conn.prepareStatement(sql);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next())
			{
				out.println(rs.getInt(1));
			}
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				conn.close();
			} catch (SQLException e)
			{
				e.printStackTrace();
			}
		}
	%>

B. 单独为Tomcat中某个Web项目配置连接池  
1. 在conf/catilina/localhost目录下创建xml文件，文件名就是该Web项目的项目名。然后将以下配置内容context部分放在创建的xml文件中。  
>
	<?xml version='1.0' encoding='utf-8'?>
	//docBase指向Web应用的绝对路径。reloadable：表示当app中的类发生变化的时候，服务器自动加载。
	<Context docBase="D:\JavaWeb\JDNITest\WebRoot" reloadable="true">
	    <WatchedResource>WEB-INF/web.xml</WatchedResource>
		<Resource name="jdbc/mysql"
	            auth="Container"
	            type="javax.sql.DataSource"
	            username="root"
	            password="wt312041990"
	            driverClassName="com.mysql.jdbc.Driver"
	            url="jdbc:mysql://localhost:3306/ygcbook"
	            maxActive="8"
	            initialSize="5"
	            maxIdle="4"/>
	</Context>

2. 后面的使用与前面的使用连接池是一样的。JDBC也是应该放在服务器的lib中。