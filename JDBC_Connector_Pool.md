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
1. DBCP连接池，该连接池来自Apache，是对数据库连接词DataSouce接口的具体实现。其实Tomcat服务器的数据库连接池连接。可以与服务器一起使用，也可以单独使用。我们实际使用中不必自己重新对DataSource接口进行实现，使用DBCP连接池即可。  
需要使用几个包：JDBC驱动包、common pools包、DBCP包、Junit包（Junit包用于测试）。  
DBCP包中的BasicDataSource类就是实现了java.sql.DataSource接口，所以，我们实际使用中就是使用BasicDataSource类完成数据连接池。这个BasicDataSource类对象实际上就是一个数据源，也就是一个数据连接池对象。
2. 


