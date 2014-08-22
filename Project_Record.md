#Java中的DAO层
----
按照软件的三层架构，分为表示层、业务层、持久层。(见图：`SoftWare_Structure.png`)  
其中的持久层负责处理软件用户的要求，如：运算，从数据库中存、取数据等功能。所以持久层也叫数据存取层也就是`DAO`层。  
一般而言，有几张表就有几个`DAO`类。
那么，怎样写`DAO`层？  
`DAO`层实际上就是一个类，这个类里面提供了对该`DAO`层所对应的表的增、删、改、查操作的所有方法。  
一个`DAO`层的类原型如下：  
>
	public class StudentDao
	{
		public void add() 
		{
	
		}
	
		public void delete()
		{
	
		}
	
		public void update()
		{
	
		}
	
		public void find() throws SQLException
		{
			
		}
	}

这个类就是一个`DAO`层，对应与`student`表，作用是：操作`student`表中的数据(增、删、改、查)。

如下即是一个针对`student`表的`DAO`层的具体实现： 

1.0版本：  
a. `DAO`层接口代码：
>
	package com.trilever.DAO;
	import java.sql.*;
	import com.trilever.DAOManager.*;
	public class StudentDao
	{
		public void add() throws SQLException
		{
			String sql = "insert into student (stu_Id,stu_Name,stu_Age,stu_Ger,teachId,group_Id) values(7,'wsdf',35,0,22,2)";
			DaoManager fdm = new DaoManager();
			int row = fdm.insertManager(sql);
			System.out.println(row);
		}
>	
		public void delete() throws SQLException
		{
			String sql = "delete from student where stu_Id=7";
			DaoManager fdm = new DaoManager();
			int row = fdm.deleteManager(sql);
			System.out.println(row);
		}
>	
		public void update() throws SQLException
		{
			String sql = "update student set stu_Age=stu_Age+10 where stu_Name like \"[a z]\" ";
			DaoManager fdm = new DaoManager();
			int row = fdm.updateManager(sql);
			System.out.println(row);
		}
>	
		public void find() throws SQLException
		{
			String sql = "select stu_Id,stu_Name,stu_Age from student";
			DaoManager fdm = new DaoManager();
			ResultSet mk = fdm.findManager(sql);
			while (mk.next())
			{
				System.out.print(mk.getString("stu_Id") + " ");
				System.out.print(mk.getString("stu_Name") + " ");
				System.out.println(mk.getString("stu_Age"));
			}
			//对于查询而言，不能再使用结果集之前就关闭了结果集，所以要在DaoManager类中增加一个closeFunc()方法，以用于在使用了结果集ResultSet之后再关闭它
			fdm.closeFunc();
		}
	}

b. `DAO`层具体实现代码：
>
	package com.trilever.DAOManager;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	public class DaoManager
	{
		private Connection conn = null;
		private Statement stat = null;
		private ResultSet rs = null;
		private String driver = "com.mysql.jdbc.Driver";
		private String url = "jdbc:mysql://localhost:3306/class";
		private String user = "root";
		private String password = "wt312041990";
		private int row = 0;
		public ResultSet findManager(String sql) throws SQLException
		{
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				// 创建用于数据运输的车
				stat = conn.createStatement();
				// 执行sql查询语句
				rs = stat.executeQuery(sql);
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			}
			return rs;
		}
>		
		public int  insertManager(String sql) throws SQLException
		{
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				// 创建用于数据运输的车
				stat = conn.createStatement();
				// 执行sql查询语句
				row = stat.executeUpdate(sql);
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			}
			finally
			{
				this.closeFunc();
			}
			return row;
		}
>	
		public int deleteManager(String sql) throws SQLException
		{
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				// 创建用于数据运输的车
				stat = conn.createStatement();
				// 执行sql查询语句
				row = stat.executeUpdate(sql);
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			}
			finally
			{
				this.closeFunc();
			}
			return row;
		}
>		
		public int updateManager(String sql) throws SQLException
		{
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				// 创建用于数据运输的车
				stat = conn.createStatement();
				// 执行sql查询语句
				row = stat.executeUpdate(sql);
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			}
			finally
			{
				this.closeFunc();
			}
			return row;
		}
		//对于数据库的查询，返回的结果集ResultSet，在使用完结果集之前不能关闭该结果集，所以要专门用一个方法来专门关闭结果集。
		public void closeFunc()
		{
			try
			{
				if (rs != null)
				{
					rs.close();
					rs = null;
				}
				if (stat != null)
				{
					stat.close();
					stat = null;
				}
				if (conn != null)
				{
					conn.close();
					conn = null;
				}
			} catch (Exception e2)
			{
				e2.printStackTrace();
				System.out.println("关闭失误！");
			}
		}
	} 

c. `DAO`层测试代码：
>
	package com.trilever.daoTest;
	import java.sql.SQLException;
	import com.trilever.DAO.*;
	public class DAOTest
	{
		public static void main(String[] args) throws SQLException
		{
			new StudentDao().find();
			new StudentDao().add();
			new StudentDao().delete();
			new StudentDao().update();
		}
	}



2.0版本：  
a. `DAO`层接口代码：  
>
	/*
	 * 本类即是针对于student表的DAO层，主要提供功能就是数据的增删改查。
	 */
	package com.trilever.DAO;
	import java.sql.*;
	import com.trilever.DAOManager.*;
	import com.trilever.entity.*;
	public class StudentDao
	{
		public boolean add(Student stu) throws SQLException
		{
			String sql = "insert into student (stu_Id,stu_Name,stu_Age,stu_Ger,teachId,group_Id) values("
					+ stu.getStu_Id()
					+ ","
					+ "'"
					+ stu.getStu_Name()
					+ "'"
					+ ","
					+ stu.getStu_Age()
					+ ","
					+ stu.isStu_Ger()
					+ ","
					+ stu.getTeachId() + "," + stu.getGroup_Id() + ")";
			System.out.println(sql);
			DaoManager fdm = new DaoManager();
			int row = fdm.insertManager(sql);
			if (row != 0)
				return true;
			else
				return false;
		}
>	
		public boolean delete(int id) throws SQLException
		{
			String sql = "delete from student where stu_Id=" + id;
			System.out.println(sql);
			DaoManager fdm = new DaoManager();
			int row = fdm.deleteManager(sql);
			if (row != 0)
				return true;
			else
				return false;
		}
>	
		public boolean update(Student stu) throws SQLException
		{
			String sql = "update student set stu_Age =" + stu.getStu_Age()
					+ " where stu_Name='we'";
			System.out.println(sql);
			DaoManager fdm = new DaoManager();
			int row = fdm.updateManager(sql);
			if (row != 0)
				return true;
			else
				return false;
		}
>	
		public Student find(int id) throws SQLException
		{
			String sql = "select stu_Id,stu_Name,stu_Age,teachId, group_Id from student";
			DaoManager fdm = new DaoManager();
			ResultSet mk = fdm.findManager(sql);
			Student stu = new Student();
			while (mk.next())
			{
				stu.setStu_Id(mk.getInt("stu_Id"));
				stu.setStu_Name(mk.getString("stu_Name"));
				stu.setStu_Age(mk.getInt("stu_Age"));
				stu.setStu_Ger(true);
				stu.setTeachId(mk.getInt("teachId"));
				stu.setGroup_Id(mk.getInt("group_Id"));
			}
			// 对于查询而言，不能再使用结果集之前就关闭了结果集，所以要在DaoManager类中增加一个closeFunc()方法，以用于在使用了结果集ResultSet之后再关闭它
			fdm.closeFunc();
			return stu;
		}
	}


b. `DAO`层实体类代码：代表数据库中的任意一个实体，使代码更加面向对象。  
	
	package com.trilever.entity;
	public class Student
	{
		private int stu_Id;
		private String stu_Name;
		private int stu_Age;
		private boolean stu_Ger;
		private int teachId;
		private int group_Id;
		public Student()
		{
			super();
		}
		public int getStu_Id()
		{
			return stu_Id;
		}
		public void setStu_Id(int stu_Id)
		{
			this.stu_Id = stu_Id;
		}
		public Student(int stu_Id, String stu_Name, int stu_Age, boolean stu_Ger,
				int teachId, int group_Id)
		{
			super();
			this.stu_Id = stu_Id;
			this.stu_Name = stu_Name;
			this.stu_Age = stu_Age;
			this.stu_Ger = stu_Ger;
			this.teachId = teachId;
			this.group_Id = group_Id;
		}
		public Student(String stu_Name, int stu_Age, boolean stu_Ger, int teachId,
				int group_Id)
		{
			super();
			this.stu_Name = stu_Name;
			this.stu_Age = stu_Age;
			this.stu_Ger = stu_Ger;
			this.teachId = teachId;
			this.group_Id = group_Id;
		}
		@Override
		public String toString()
		{
			return "Student [stu_Name=" + stu_Name + ", stu_Age=" + stu_Age
					+ ", stu_Ger=" + stu_Ger + ", teachId=" + teachId
					+ ", group_Id=" + group_Id + "]";
		}
		@Override
		public boolean equals(Object arg0)
		{
			// TODO Auto-generated method stub
			return super.equals(arg0);
		}
		public String getStu_Name()
		{
			return stu_Name;
		}
		public void setStu_Name(String stu_Name)
		{
			this.stu_Name = stu_Name;
		}
		public int getStu_Age()
		{
			return stu_Age;
		}
		public void setStu_Age(int stu_Age)
		{
			this.stu_Age = stu_Age;
		}
		public int isStu_Ger()
		{
			if (stu_Ger = true)
				return 1;
			else
				return 0;
		}
		public void setStu_Ger(boolean stu_Ger)
		{
			this.stu_Ger = stu_Ger;
		}
		public int getTeachId()
		{
			return teachId;
		}
		public void setTeachId(int teachId)
		{
			this.teachId = teachId;
		}
		public int getGroup_Id()
		{
			return group_Id;
		}
		public void setGroup_Id(int group_Id)
		{
			this.group_Id = group_Id;
		}
	}

c. `DAO`层具体实现代码：
>
	/*
	 * 本类就是DAO层增、删、改、查各方法的具体实现
	 */
	package com.trilever.DAOManager;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	public class DaoManager
	{
		private Connection conn = null;
		private Statement stat = null;
		private ResultSet rs = null;
		private String driver = "com.mysql.jdbc.Driver";
		private String url = "jdbc:mysql://localhost:3306/class";
		private String user = "root";
		private String password = "wt312041990";
		private int row = 0;
		public ResultSet findManager(String sql) throws SQLException
		{
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				// 创建用于数据运输的车
				stat = conn.createStatement();
				// 执行sql查询语句
				rs = stat.executeQuery(sql);
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			}
			return rs;
		}
>
		public int insertManager(String sql) throws SQLException
		{
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				// 创建用于数据运输的车
				stat = conn.createStatement();
				// 执行sql查询语句
				row = stat.executeUpdate(sql);
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			}
			finally
			{
				this.closeFunc();
			}
			return row;
		}
>	
		public int deleteManager(String sql) throws SQLException
		{
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				// 创建用于数据运输的车
				stat = conn.createStatement();
				// 执行sql查询语句
				row = stat.executeUpdate(sql);
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			}
			finally
			{
				this.closeFunc();
			}
			return row;
		}
>		
		public int updateManager(String sql) throws SQLException
		{
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				// 创建用于数据运输的车
				stat = conn.createStatement();
				// 执行sql查询语句
				row = stat.executeUpdate(sql);
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			}
			finally
			{
				this.closeFunc();
			}
			return row;
		}
		//对于数据库的查询，返回的结果集ResultSet，在使用完结果集之前不能关闭该结果集，所以要专门用一个方法来专门关闭结果集。
		public void closeFunc()
		{
			try
			{
				if (rs != null)
				{
					rs.close();
					rs = null;
				}
				if (stat != null)
				{
					stat.close();
					stat = null;
				}
				if (conn != null)
				{
					conn.close();
					conn = null;
				}
			} catch (Exception e2)
			{
				e2.printStackTrace();
				System.out.println("关闭失误！");
			}
		}
	}

d. `DAO`层测试代码：有一种软件编写方法是面向测试的编程，即先写出测试代码，然后再写主程序。  
>
	/*
	 * 本类就是DAO层的测试类
	 */
	package com.trilever.daoTest;
	import java.sql.SQLException;
	import com.trilever.DAO.*;
	import com.trilever.entity.*;
	public class DAOTest
	{
		public static void main(String[] args) throws SQLException
		{
			DAOTest dt = new DAOTest();
			dt.findTest();
		}
>	
		public void addTest() throws SQLException
		{
			Student stu = new Student(13, "wssgsdgs", 35, false, 23, 2);
			StudentDao stuD = new StudentDao();
			boolean result = stuD.add(stu);
			if (result == true)
			{
				System.out.println("增加数据成功");
			} else
				System.out.println("增加数据失败");
		}
>	
		public void deleteTest() throws SQLException
		{
			int id = 10;
			StudentDao stuD = new StudentDao();
			boolean result = stuD.delete(id);
			if (result == true)
			{
				System.out.println("刪除数据成功");
			} else
				System.out.println("刪除数据失败");
		}
>	
		public void updateTest() throws SQLException
		{
			Student stu = new Student(12, "wssgsdgs", 35, false, 23, 2);
			StudentDao stuD = new StudentDao();
			boolean result = stuD.update(stu);
			if (result == true)
			{
				System.out.println("修改数据成功");
			} else
				System.out.println("修改数据失败");
		}
>	
		public void findTest() throws SQLException
		{
			StudentDao stuD = new StudentDao();
			Student result = stuD.find(1);
			System.out.println(result);
		}
	}


`DAO`3.0版：使用了`PreparedStatement`预编译技术。还为`FindAll()`方法提供了从结果集向对象转换的接口。使此方法可以返回一个集合。  
a. `DAO`层接口代码： 
> 
	/*
	 * 本类即是针对于student表的DAO层，主要提供功能就是数据的增删改查。
	 */
	package com.trilever.DAO;
	import java.sql.*;
	import java.util.List;
	import com.trilever.DAOManager.*;
	import com.trilever.entity.*;
	public class StudentDao
	{
		public boolean add(Student stu) throws SQLException
		{
			String sql = "insert into student (stu_Id,stu_Name,stu_Age,stu_Ger,teachId,group_Id) values(?,?,?,?,?,?)";
			System.out.println(sql);
			Object[] obs = new Object[]
			{ stu.getStu_Id(), stu.getStu_Name(), stu.getStu_Age(),
					stu.isStu_Ger(), stu.getTeachId(), stu.getGroup_Id() };
			DaoManager fdm = new DaoManager();
			int row = fdm.insertManager(sql, obs);
			if (row != 0)
				return true;
			else
				return false;
		}
>	
		public boolean delete(int id) throws SQLException
		{
			String sql = "delete from student where stu_Id= ?";
			System.out.println(sql);
			DaoManager fdm = new DaoManager();
			int row = fdm.deleteManager(sql, id);
			if (row != 0)
				return true;
			else
				return false;
		}
>	
		public boolean update(Student stu) throws SQLException
		{
			String sql = "update student set stu_Age = ? where stu_Name=?";
			System.out.println(sql);
			Object[] obs = new Object[]
			{ stu.getStu_Age(), "trilever" };
			DaoManager fdm = new DaoManager();
			int row = fdm.updateManager(sql, obs);
			if (row != 0)
				return true;
			else
				return false;
		}
>	
		public Student find(int id) throws SQLException
		{
			String sql = "select stu_Id,stu_Name,stu_Age,stu_Ger,teachId, group_Id from student where stu_Id = ?";
			DaoManager fdm = new DaoManager();
			ResultSet mk = fdm.findManager(sql, id);
			Student stu = new Student();
			while (mk.next())
			{
				stu.setStu_Id(mk.getInt("stu_Id"));
				stu.setStu_Name(mk.getString("stu_Name"));
				stu.setStu_Age(mk.getInt("stu_Age"));
				stu.setStu_Ger(mk.getByte("stu_Ger"));
				stu.setTeachId(mk.getInt("teachId"));
				stu.setGroup_Id(mk.getInt("group_Id"));
			}
			// 对于查询而言，不能再使用结果集之前就关闭了结果集，所以要在DaoManager类中增加一个closeFunc()方法，以用于在使用了结果集ResultSet之后再关闭它
			fdm.closeFunc();
			return stu;
		}
>	
		public List<Student> findAll() throws SQLException
		{
			String sql = "select * from student";
			DaoManager fdm = new DaoManager();
			List<Student> mk = fdm.findAllManager(sql);
			// 对于查询而言，不能再使用结果集之前就关闭了结果集，所以要在DaoManager类中增加一个closeFunc()方法，以用于在使用了结果集ResultSet之后再关闭它
			fdm.closeFunc();
			return mk;
		}
	}

b. `DAO`层实体类代码：代表数据库中的任意一个实体，使代码更加面向对象。 
>
	package com.trilever.entity;
	public class Student
	{
		private int stu_Id;
		private String stu_Name;
		private int stu_Age;
		private byte stu_Ger;
		private int teachId;
		private int group_Id;
>	
		public Student()
		{
			super();
		}
>	
		/**
		 * @return the stu_Id
		 */
		public int getStu_Id()
		{
			return stu_Id;
		}
>	
		/**
		 * @param stu_Id
		 *            the stu_Id to set
		 */
		public void setStu_Id(int stu_Id)
		{
			this.stu_Id = stu_Id;
		}
>	
		public Student(int stu_Id, String stu_Name, int stu_Age, byte stu_Ger,
				int teachId, int group_Id)
		{
			super();
			this.stu_Id = stu_Id;
			this.stu_Name = stu_Name;
			this.stu_Age = stu_Age;
			this.stu_Ger = stu_Ger;
			this.teachId = teachId;
			this.group_Id = group_Id;
		}
>	
		public Student(String stu_Name, int stu_Age, byte stu_Ger, int teachId,
				int group_Id)
		{
			super();
			this.stu_Name = stu_Name;
			this.stu_Age = stu_Age;
			this.stu_Ger = stu_Ger;
			this.teachId = teachId;
			this.group_Id = group_Id;
		}
>	
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#toString()
		 */
		@Override
		public String toString()
		{
			return "Student [stu_Id=" + stu_Id + ",stu_Name=" + stu_Name
					+ ", stu_Age=" + stu_Age + ", stu_Ger=" + stu_Ger
					+ ", teachId=" + teachId + ", group_Id=" + group_Id + "]";
		}
>	
		/*
		 * (non-Javadoc)
		 * 
		 * @see java.lang.Object#equals(java.lang.Object)
		 */
		@Override
		public boolean equals(Object arg0)
		{
			// TODO Auto-generated method stub
			return super.equals(arg0);
		}
>	
		/**
		 * @return the stu_Name
		 */
		public String getStu_Name()
		{
			return stu_Name;
		}
>	
		/**
		 * @param stu_Name
		 *            the stu_Name to set
		 */
		public void setStu_Name(String stu_Name)
		{
			this.stu_Name = stu_Name;
		}
>	
		/**
		 * @return the stu_Age
		 */
		public int getStu_Age()
		{
			return stu_Age;
		}
>	
		/**
		 * @param stu_Age
		 *            the stu_Age to set
		 */
		public void setStu_Age(int stu_Age)
		{
			this.stu_Age = stu_Age;
		}
>	
		/**
		 * @return the stu_Ger
		 */
		public byte isStu_Ger()
		{
			return this.stu_Ger;
		}
>	
		/**
		 * @param stu_Ger
		 *            the stu_Ger to set
		 */
		public void setStu_Ger(byte stu_Ger)
		{
			this.stu_Ger = stu_Ger;
		}
>	
		/**
		 * @return the teachId
		 */
		public int getTeachId()
		{
			return teachId;
		}
>	
		/**
		 * @param teachId
		 *            the teachId to set
		 */
		public void setTeachId(int teachId)
		{
			this.teachId = teachId;
		}
>	
		/**
		 * @return the group_Id
		 */
		public int getGroup_Id()
		{
			return group_Id;
		}
>	
		/**
		 * @param group_Id
		 *            the group_Id to set
		 */
		public void setGroup_Id(int group_Id)
		{
			this.group_Id = group_Id;
		}
	}
 
c. `DAO`层具体实现代码：
>	
	/*
	 * 本类就是DAO层增、删、改、查各方法的具体实现
	 */
	package com.trilever.DAOManager;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.LinkedList;
	import java.util.List;
	import com.trilever.entity.*;
	public class DaoManager
	{
		private Connection conn = null;
		private Statement stat = null;
		private ResultSet rs = null;
		private String driver = "com.mysql.jdbc.Driver";
		private String url = "jdbc:mysql://localhost:3306/class";
		private String user = "root";
		private String password = "wt312041990";
		private int row = 0;
		PreparedStatement past = null;
		public ResultSet findManager(String sql, int id) throws SQLException
		{
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				past = conn.prepareStatement(sql);
				past.setInt(1, id);
				// 执行sql查询语句
				rs = past.executeQuery();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			}
			return rs;
		}
>	
		public List<Student> findAllManager(String sql) throws SQLException
		{
			List<Student> lstu = new LinkedList<>();
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				past = conn.prepareStatement(sql);
				// 执行sql查询语句
				rs = past.executeQuery();
				while (rs.next())
				{
					lstu.add(new ResultMappingEntity().MappingResultEntity(rs));
				}
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			}
			return lstu;
		}
>	
		public int insertManager(String sql, Object[] obs) throws SQLException
		{
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				past = conn.prepareStatement(sql);
				for (int i = 0; i < obs.length; i++)
				{
					past.setObject(i + 1, obs[i]);
				}
				// 执行sql查询语句
				row = past.executeUpdate();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			} finally
			{
				this.closeFunc();
			}
			return row;
		}
>	
		public int deleteManager(String sql, int id) throws SQLException
		{
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				past = conn.prepareStatement(sql);
				past.setInt(1, id);
				// 执行sql查询语句
				row = past.executeUpdate();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			} finally
			{
				this.closeFunc();
			}
			return row;
		}
>	
		public int updateManager(String sql, Object[] obs) throws SQLException
		{
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				past = conn.prepareStatement(sql);
				for (int i = 0; i < obs.length; i++)
				{
					past.setObject(i + 1, obs[i]);
				}
				// 执行sql查询语句
				row = past.executeUpdate();
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			} finally
			{
				this.closeFunc();
			}
			return row;
		}
>	
		// 对于数据库的查询，返回的结果集ResultSet，在使用完结果集之前不能关闭该结果集，所以要专门用一个方法来专门关闭结果集。
		public void closeFunc()
		{
			try
			{
				if (rs != null)
				{
					rs.close();
					rs = null;
				}
				if (stat != null)
				{
					stat.close();
					stat = null;
				}
				if (conn != null)
				{
					conn.close();
					conn = null;
				}
			} catch (Exception e2)
			{
				e2.printStackTrace();
				System.out.println("关闭失误！");
			}
		}
	}
d. 转换类，用于将`ResultSet`中的数据转换成`Student`对象。
>
	package com.trilever.DAOManager;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import com.trilever.entity.Student;
	interface ResultMappingtoEntity
	{
		public Object MappingResultEntity(ResultSet rs) throws SQLException;
	}
>
	public class ResultMappingEntity implements ResultMappingtoEntity
	{
		/*
		 * (non-Javadoc)
		 * 
		 * @see
		 * com.trilever.DAOManager.ResultMappingEntity#MappingResultEntity(java.
		 * sql.ResultSet)
		 */
		@Override
		public Student MappingResultEntity(ResultSet rs) throws SQLException
		{
			Student stu = new Student(rs.getInt("stu_Id"),
					rs.getString("stu_Name"), rs.getInt("stu_Age"),
					rs.getByte("stu_Ger"), rs.getInt("teachId"),
					rs.getInt("group_Id"));
			return stu;
		}
	}

e. `DAO`层测试代码：有一种软件编写方法是面向测试的编程，即先写出测试代码，然后再写主程序。 
>
	/*
	 * 本类就是DAO层的测试类
	 */
	package com.trilever.daoTest;
	import java.sql.SQLException;
	import java.util.List;
	import com.trilever.DAO.*;
	import com.trilever.entity.*;
	public class DAOTest
	{
		public static void main(String[] args) throws SQLException
		{
			DAOTest dt = new DAOTest();
			dt.findAllTest();
		}
>	
		public void addTest() throws SQLException
		{
			Student stu = new Student(31, "wssgsdgs", 35, (byte) 1, 23, 2);
			StudentDao stuD = new StudentDao();
			boolean result = stuD.add(stu);
			if (result == true)
			{
				System.out.println("增加数据成功");
			} else
				System.out.println("增加数据失败");
		}
>	
		public void deleteTest() throws SQLException
		{
			int id = 31;
			StudentDao stuD = new StudentDao();
			boolean result = stuD.delete(id);
			if (result == true)
			{
				System.out.println("刪除数据成功");
			} else
				System.out.println("刪除数据失败");
		}
>	
		public void updateTest() throws SQLException
		{
			Student stu = new Student(12, "wssgsdgs", 35, (byte) 1, 23, 2);
			StudentDao stuD = new StudentDao();
			boolean result = stuD.update(stu);
			if (result == true)
			{
				System.out.println("修改数据成功");
			} else
				System.out.println("修改数据失败");
		}
>	
		public void findTest() throws SQLException
		{
			StudentDao stuD = new StudentDao();
			Student result = stuD.find(1);
			System.out.println(result);
		}
>	
		public void findAllTest() throws SQLException
		{
			StudentDao stuD = new StudentDao();
			List<Student> ls = stuD.findAll();
			for (Student student : ls)
			{
				System.out.println(student);
			}
		}
	}

#Java中的PraparedStatement
-----
`PraparedStatement`这个接口继承了`Statement`接口。  
当在`Java`中依次执行多条`SQL`语句时，如果这些`SQL`语句的结构都相同只是语句的参数不同，那么在使用`Statement`进行`SQL`语句的执行就会将`SQL`语句发送给数据库服务器，然后数据库依次编译每一条`SQL`语句并执行，这样就会导致多次编译的问题，实际上这些语句的结构都是相同的，用不着多次编译。  
此时就可以使用`PraparedStatement`来执行`SQL`语句了。这样就可以使结构相同参数不同的多条语句只编译一次，提升效率。实际上就是采用预编译机制。  
使用`PraparedStatement`还有一个功效就是：防止`SQL`注入。传统的`SQL`注入会导致报错。  

传统的`SQL`注入：  
怎样登陆网站后台输入界面？  
`Answer`：`URL`后面添加`background`或者`sysadmin`。
那么`SQL`注入的目的？
无需密码即进入网站后台。

任何登陆页面(无论是网站还是程序应用app)，任何用户输入账号、密码之后都是将账号密码拼成一个`SQL`语句，然后提交给后台数据库进行处理，如果输入争取就会返回想要的结果，也就是登陆成功进入账户。  
那么，`SQL`注入的原理就是，保证无论输入什么账号和密码，都能保证我们的`SQL`语句能获得通过。
如：  
加入某应用`app`的登陆界面的`SQL`语句时：
>
	select * from users where name = aname,password = pwd;  

其中的`aname`是我们在登陆界面输入的账号字符串，`pwd`是我们在界面输入的密码字符串。  
那么，当我们在密码输入框中输入：  
>
	sss or 'a'='a'  
那么就会使得登陆界面向数据库提交的`SQL`语句是：
>
	select * from users where name = aname,password = sss or 'a'='a';

这条语句，即使我们填入的`password`是错误的，后面的删选条件依旧是正确的，也就是说依旧成立。这就是`SQL`注入的原理，让我们即使输入错误的密码，但是提交给数据库的`SQL`语句依旧是正确的。  
这样，无论是什么样的登陆，只要是通过`SQL`语句与后台数据库进行交互的，都可以使用`SQL`注入方式登陆。 
我们首先要知道这个登陆界面的`SQL`语句时怎样拼的之后才可以写出正确的`SQL`注入语句。

使用`PraparedStatement`有两种方式：  
使用示例1：
>	
	package com.trilever.SQL;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	public class PreparedStatementTest
	{
		public static void main(String[] args) throws SQLException
		{
			Connection conn = null;
			Statement stat = null;
			ResultSet rs = null;
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/class";
			String user = "root";
			String password = "wt312041990";
			int row = 0;
			PreparedStatement pst = null;
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				// 创建用于数据运输的车
				// 执行sql查询语句
				String sql = "insert into student (stu_Id,stu_Name,stu_Age,stu_Ger,teachId,group_Id) values(?,?,?,?,?,?)";
				//创建一个PreparedStatement对象，以后可以向这个对象中传递参数。
				pst = conn.prepareStatement(sql);
>	
				// 可以直接无数次向这个语句中传递参数，这样可以让数据库只编译一次这个语句，但是可以执行n次SQL语句。
				//如果这里不知道是什么类型，可以使用setObject()方法。
				pst.setInt(1, 20);
				pst.setString(2, "trilever");
				pst.setInt(3, 18);
				pst.setByte(4, (byte) 1);
				pst.setInt(5, 22);
				pst.setInt(6, 1);
				row = pst.executeUpdate();
				System.out.println(row);
>	
				// 可以直接无数次向这个语句中传递参数，这样可以让数据库只编译一次这个语句，但是可以执行n次SQL语句。
				pst.setInt(1, 21);
				pst.setString(2, "trileverwt");
				pst.setInt(3, 18);
				pst.setByte(4, (byte) 1);
				pst.setInt(5, 22);
				pst.setInt(6, 1);
				row = pst.executeUpdate();
				System.out.println(row);
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			} finally
			{
				conn.close();
				pst.close();
			}
		}
	}

示例2：使用`addBatch()`
>
	package com.trilever.SQL;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	public class BatchPreparedStatementTest
	{
		public static void main(String[] args) throws SQLException
		{
			Connection conn = null;
			Statement stat = null;
			ResultSet rs = null;
			String driver = "com.mysql.jdbc.Driver";
			String url = "jdbc:mysql://localhost:3306/class";
			String user = "root";
			String password = "wt312041990";
			int row = 0;
			PreparedStatement pst = null;
			try
			{
				// 装载、注册类
				Class.forName(driver);
				// 修建Java应用程序与数据库之间连接的路径
				conn = DriverManager.getConnection(url, user, password);
				if (!conn.isClosed())
				{
					System.out.println("connects succees!");
				}
				// 创建用于数据运输的车
				// 执行sql查询语句
				String sql = "insert into student (stu_Id,stu_Name,stu_Age,stu_Ger,teachId,group_Id) values(?,?,?,?,?,?)";
				//创建一个PreparedStatement对象，以后可以向这个对象中传递参数。
				pst = conn.prepareStatement(sql);
>	
				// 可以直接无数次向这个语句中传递参数，这样可以让数据库只编译一次这个语句，但是可以执行n次SQL语句。
				pst.setInt(1, 23);
				pst.setString(2, "trilever");
				pst.setInt(3, 18);
				pst.setByte(4, (byte) 1);
				pst.setInt(5, 22);
				pst.setInt(6, 1);
				//将参数集加入到批处理中
				pst.addBatch();
>				
				pst.setInt(1, 24);
				pst.setString(2, "trileverwt");
				pst.setInt(3, 18);
				pst.setByte(4, (byte) 1);
				pst.setInt(5, 22);
				pst.setInt(6, 1);
				//将参数集加入到批处理中,用这种批处理的方法，可以让处理大量的同结构的SQL语句更加快捷。
				pst.addBatch();
				int[] rows = pst.executeBatch();
				for(int n:rows)
				{
					System.out.println(n);
				}
			} catch (ClassNotFoundException e)
			{
				e.printStackTrace();
				System.out.println("包没有找到");
			} finally
			{
				conn.close();
				pst.close();
			}
		}
	}




  