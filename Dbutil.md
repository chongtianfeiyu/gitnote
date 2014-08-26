#Dbutil
由于在Java中使用JDBC来在数据库中进行增、删、改、查的效率较慢，而且编程效率低，所以在JavaEE中对JDBC进行封装，形成Dbutil包，这个工具就是用于进行数据库中的数据操作。  
DButil实际上就是对JDBC的封装，封装成各种方法，各个方法各自对应JDBC中的增、删、改、查。例如，封装后的查询方法直接将查询结果转换为实体对象，也就是entity。  
对于增、删、改、查都有其各自的封装。  
代码示例如下：  
>
	public static void main(String[] args) throws SQLException
	{
		DbManager dbManager = DbManager.getDbManager();
		Connection conn = dbManager.getConnection();
		String sql = "select * from booktype";
		String sql1 = "select bookTypeId from booktype";
		String sql3 = "delete from booktype where bookTypeId=112";
		QueryRunner queryRunner = new QueryRunner();
		try
		{
			//使用Dbutil直接将查询结果转化为实体对象entity，提高代码效率，DbUtil实际上是对JDBC的封装。
			//全部查询
			List<booktype> list = queryRunner.query(conn, sql, new BeanListHandler(booktype.class));
			for (booktype booktype : list)
			{
				System.out.println(booktype.getBookTypeId());
				System.out.println(booktype.getBookTypeName());
				System.out.println(booktype.getContext());
				System.out.println(booktype.getIsDelete());
				System.out.println(booktype.getParentId());
			}
			//部分查询，使用了匿内部类
			List<Integer> str = queryRunner.query(conn, sql1, new ResultSetHandler()
					{
						@Override
						public Object handle(ResultSet rs) throws SQLException
						{
							List<Integer> lis = new ArrayList<>();
							while(rs.next())
							{
								int res = rs.getInt(1);	
								lis.add(res);
							}
							return lis;
						}
					});
			System.out.println(str);
			//删除操作
			int str3 = queryRunner.update(conn, sql3);
			System.out.println(str3);
		} catch (SQLException e)
		{
			e.printStackTrace();
		}finally
		{
			dbManager.closeResource();
		}
	}
