
#数据库中的数据库相关
-----
## 基础知识 ##
`C/S`：所有的`app`与应用程序都是`C/S`架构  
`B/S`：我们登陆的所有网站都是`B/S`架构  

应用程序与数据库之间的关系：  
1. 应用程序响应人的操作，向数据库发出请求。显示数据库返回的数据。  
2. 数据库响应应用程序的操作，向应用程序返回相应的数据结果。  

编写应用程序的目的：*处理*从数据库中返回的数据。

什么叫做关系型数据库：就是数据库中的表之间，通过*键的形式予以连接*，例如，*学生基本信息表*中的*学生编号是主键*，在*学生考试成绩表*中，*学生编号是外键*。这两张表通过学生编号这个键予以连接，这就是关系数据库的特征。这样两张表就能够联接查询、操作。  

`DB`：`DataBase`：数据库，本质上就是存储在磁盘上的各种格式的数据文件。  
`DBMS`：`DataBase Management System`：数据管理系统。用于管理数据库。  
`DBA`：`DataBase Administrators`：数据库管理员。软件公司没有数据库管理员，只有使用软件的企业里采用数据库管理员。工作是数据控制、管理、备份等。

## 数据库软件中的数据库 ##
###系统数据库
在数据库软件安装完成之后，就会有几个**系统数据库**。  
**系统数据库**的作用就是：管理我们自己创建的用户数据库。当我们在数据库中储存自己的数据，我们自己创建的这些数据库(**用户数据库**)需要被**系统数据库**管理与控制。系统数据库不能删除不能修改。  
例如：在SQLServer数据库软件中  
有一个系统数据库：Master，作用是统计、记录我们自己创建的用户数据库的情况。  
有一个系统数据库：Modal，作用是作为一个*数据库模板*，供我们自己创建用户数据库时使用。  
还有系统数据库：msdb与tempdb数据库。

###用户数据库  
当我们做一个项目的时候，会针对这个项目做一个数据库，为这个项目提供数据的存储、操作等。这个就是**用户数据库**。 
 
一个用户数据库中有两种文件：数据文件与日志文件  
1. 数据文件：  
 a. 主数据文件(.mdf):有且仅有一个。主数据文件中存放了次数据文件和日志文件这些东西的信息。  
 b. 次数据文件(.ndf):0个或多个。  
2. 日志文件(.ldf):0个或多个。 

为一个项目新建一个数据库。  
a. 图形界面创建  
b. 语句创建，创建数据库、表的时候，一定要使用`master`数据库。  
命令语句创建数据库  
"--"是单行注释，不区分大小写。  
数据库命令行语法可以去查文档。
>
	use master
	--create a database
	create database student
	--delete a database
	drop database student

注意：在`SQL`语言中，往往不能以语句位置来确定语句的执行顺序。只能以`begin……end`来代替其他语言中的`{}`来确定语句的执行顺序。  
示例如下：
>
	if exists(select * from database student)--exit方法的返回值是true或者false，它的参数是一条select语句，如果语句执行结果有记录就是true，如果select语句执行结果为空就为false。
	  print("yes)--在这里，如果是true的话，两个语句都可以执行，但是如果是false，第一个语句不执行，第二个语句不是和第一个语句一起被控制的，它还是会执行。
	  drop database student--因为，在这里，if这个判断只能控制print这个句子，而不能控制drop这个句子，无论如何，drop这个句子都会执行。
	else
	  print("no")
>
所以，这里如果想让这两个句子一起都被`if`判断所控制，只能使用`begin……end`来让他们连在一起。
>
	if exists(select * from database student)
	begin  
	  print("yes)--在这里，使用了begin……end将这两个语句连在一起，使他们一起被if判断所控制
	  drop database student
	end
	else
	  print("no")

##数据库备份
1. 使用分离与附加。这种拷贝方法不能压缩数据库。  
2. 直接备份，可以压缩数据库。备份可用于还原


#数据库中的表相关
------
更精确来讲，数据库中的数据是存储在`表`里面。  
在一张表中，如果我们需要什么信息，就在这个表里面增加一列(也就是增加一个新字段)。

##字段约束  
对于某些字段，对这个字段的值是有限定的，如果我们输入了错误(无效)的字段值，则称为：此表失去了完整性。

那么怎样保证字段的值有效？  
`Answer`：对字段施行*完整性约束*。  
约束有以下几种：  
1. 唯一性约束，如学号字段，其允许一行数据为空。但必须唯一。  
2. 主键约束，相当于:唯一约束+非空约束。要求这个字段中的数据唯一且有值。可以给两列加主键约束，称为复合主键，但是比较少。  
3. `check`约束，值范围有约束。  
4. 默认值约束，如果没有值就会给一个默认值。  
5. 主外键约束，就是，我们一个表中的某个字段的值必须来自于另外一个表中的某一个字段的值，否则就不对，这就是主外键约束。而且这可以是一对多的关系：主键表中的主键字段中的一个值可以在外键表中的该字段中出现多次。  
例如，一张是教师表，另一张是学生表。对于教师名字这个字段而言，教师表是主键表，学生表是外键表，其中，每一个教师名字都可以在学生表中的教师名字字段出现多次(多个学生工一个老师)。

当我们建立两个表，第二个表中某一个字段的所有值都是来自于第一个表中的某个字段的值，那么，称第一个表为主键表，第二个表为外键表。相对与一个字段，该表可以是主键表，但是相对于另外一个字段，该表可以是外键表。

什么叫做约束？  
`Answer`：就是对数据库表中的**字段的值进行的限制**。约束的作用就是：保证我们在输入字段的值的时候不会输入错误的值。自动检查我们输入的值是否符合要求。


##建表
>
	--创建表
	create table student
	(
		--字段名       数据类型         约束(一般在建立一个表的时候只添加非空约束，以后再添加其他的约束)
		 stuName      varchar(20)     not null，
		 stuId    	    int           not null，
	)
	create table score
	(
		 stuId          int			  not mull，
	)
	--删除表
	drop table student


建表是依据数据字典建立的，所谓的数据字典就是：一张表中，所有的数据的要求信息，包括各个字段的名称、类型等的规定。

##修改表中的字段约束
>
	alter table student
	-- add constraint   约束名       约束类型        具体约束说明 
	   add constraint   PK_stuId    primary key    (stuId)     --为stuId这个字段添加主键约束。
	alter table student
	   add constraint   PK_stuName   UNIQUE         (stuName)   --为stuName这个字段添加唯一约束。
	alter table student
	   drop constraint  PK_stuName  --删除PK_stuName这个约束
	alter table score  --添加外键约束的时候要使用外键表
	-- add constraint   约束名       约束类型    外键表字段    REFERENCES    主键表  主键表字段
	   add constraint   FK_stuId    Foreign key(stuId)      REFERENCES    student(stuId) --为外键表的stuId这个字段添加外键约束



注：可以将所有要运行的`SQL`命令行语句放在一个`.sql`后缀的脚本文件中，然后写一个`.bat`批处理脚本，在`bat`批处理脚本中调用`osql`工具来运行这个`.sql`后缀的脚本文件，就可以不用打开`SQL`数据库软件而直接运行这些`sql`命令行。`sql`脚本里面就是我们需要执行的`sql`语句。

怎样删除一张表？  
>
	if exist(select * from sysobjects where name="student")--删除student表，sysobjects是一个对象视图，每一个数据库中都有一个，记录的是这个数据库中的详细信息，如，我们自己建立的每一个用户数据库中都有一个sysobjects对象视图，里面包含了这个数据库中所有的字段、约束等信息。
	   drop table sysobjects;

##数据库管理界面(GUI)使用
注：
1. 将某一个字段设为标识列：是将某一列作为标识。就是将这一列的值设置为自增加，需要先将这个字段设置为`primary key`，然后才能设置为`auto increment`。标识列可以不用编辑，自己会增加。  
2. 想一个外键表中添加外键字段的值的时候，必须保证在主键表中相同字段中有同样的值才能保证添加成功。

##对数据库中数据的操作
1. 增  
2. 删  
3. 改  
4. 查

`SQL`：`Structured Query Language`，任何数据库都要遵循这样的`SQL`规范。  
`T-SQL`:`SQL`的加强版。里面有些内容不是标准`SQL`。  
T-SQL的组成：  
1. DDL(数据定义语言)：用于建立数据库、表、视图等数据库对象。如，create table、drop table等。  
2. DML(数据操作语言)：用于增、删、改、查的数据库中的数据。如，select(查)、insert(增)、update(改)、delete(删)。  
3. DCL(数据控制语言):用于控制存取许可、存取权限等，如，grant、revoke等。  

注：SQL总不等于的写法：“<>”。
###数据插入
>
	insert    into     表名       (列名)                                        values(要插入的值);        --字符串需要加''。插入数据最重要的是匹配。
	insert    into    student    (stu_Id,stu_Name,stu_Age,stu_Ger,teachId)     values(4,'wt',30,1,22);   --向表student中插入单行数据
>
	insert     into     表名1       要被插入的字段      数据来源 
	insert     into    student1                   select * from student;   --将表student中的数据备份插入到表student1中,但是备份并没有备份表约束，只备份了数据与表结构
>
	insert     into    student2   (stu_Id, stu_Name,stu_Age,stu_Ger)      select stu_Id, stu_Name,stu_Age,stu_Ger from student;    --将student表中中某几个字段的数据插入到表student2中

###数据查询
####基础查询
>
	 --select     需要显示的字段		  查找数据来源		查找条件
	   select 		 * 			     from student 		where  stu_Age>33;--精确查询，大于33岁的条目
	   select 	  stu_Id,stu_Name 	 from student 		where  address like '北京%';--使用like进行模糊查询，使用了通配符 
	   select        *               from country       where Code like "A%";--查找带有A的字符串
	   select top 10 percent * from student;--查询前10%
	   select * from student order by stu_Age;--查询结果按学生年龄升序排列，默认升序，desc是降序。
查找所用通配符：
"_"：一个字符  
"%"：任意长度的字符串  
"[]"：括号内指定范围内的一个字符  
"[^]"：括号内指定范围之外的一个字符

可以利用as关键字给字段名等取别名。

####聚合函数  
所谓的聚合函数，就是指的一些统计函数。
>	
	--统计
	select avg(stu_Age) as avga, sum(stu_Age) as suma, max(stu_Age) as maxa, min(stu_Age) as mina,count(stu_Age) as counta from student;
	--group by分组
	--having：对已用聚合函数进行分组的结果进行删选(与group by合用)。where：对表中的原始数据进行删选。
	select group_Id,avg(stu_Age) from student group by group_Id having avg(stu_Age)>=30;
	
####联接查询
当我们需要在不同的表中查询一个主体的数据，显示在同一张表中，那么就需要使用联接查询。  
a. 内联接：显示外键表所有数据，并显示主键表与之对应的数据。
>
	select t.teachId,t.teaName,s.stu_Name from teacher as t inner join student as s on(t.teachId=s.teachId);

b. 外联接：分为左外联接与右外联接。  
左外联接：  
1. 先显示内联接的所有数据。  
2. 然后看左表中是否有没匹配的没有的话原栏列出，右表部分用`null`补齐。
>
	select * from teacher as t left join student as s on(t.teachId=s.teachId);

右外联接：  
1. 先显示内联接的所有数据。  
2. 然后看右表中是否有没匹配的没有的话原栏列出，左表部分用`null`补齐。
>
	select * from teacher as t right join student as s on(t.teachId=s.teachId);

注意：前面的是左表，后面的是右表。

c. 交叉联接：
>
	select * from teacher as t cross join student as s on(t.teachId=s.teachId);

内联接中的所有条目都要与外联接中所有条目进行交叉匹配，所以获得的交叉联接结果条目数是内联接结果条目数与外联接结果条目数的积。

####子查询
T-SQL编程中，变量有两种，局部变量与全局变量。
局部变量：
	--声明局部变量，以@开头，可读写。
	declare @age int；//定义
	set @age=10；//赋值
	select @age as	'年龄'；//设置列名
	print @age；  
全局变量：
	--系统声明赋值的，我们只能使用。只读，以@@开头。

所谓的子查询：将一个查询的结果作为另一个查询的数据源。
###数据修改
>
	 --update      表名          修改操作                    被修改条件
	   update     student      set stu_Age=stu_Age+10      where stu_Name like '[A Z]e';
###数据删除
>
	 --delete      删除操作数据源        删除条件
	   delete      from student       where stu_Id=2;--删除stu_Id=2的数据行
	   truncate    table student       --删除整张表里的数据

注：  
1. 在外键表中插入数据的时候，要考虑外键字段中插入的数据在主键字段中已经存在。  
2. 在主键表中删除数据的时候，要保证主键字段中要被删除的数据在外键表相应字段中没有被引用，否则不能被删掉。这样把主外键删掉会导致问题，因为关系数据库中的表都是连接起来如一张网一样。

`SQL`语句支持嵌套。

###`T-SQL`中的常用函数  
`T-SQL`中的**字符串函数**  
(注：仅在SQL server中才有，其它数据库中不与之同名):用于对字符串进行操作。
	select charindex("am","I am a boy",1);--查找已知字符串的位置
	select len(stu_Name) as length stu_Id from student ;--计算姓名字符串的长度
	select getdate();--返回当前时间

`T-SQL`中的**数学函数**  

`T-SQL`中的**系统函数**  
	select convert(varchar(2),stu_Age)--将年龄转换为varchar类型


##SQL中的视图
###视图的定义
视图是一张虚拟表，其表示的是一张表的部分数据或者多张表的综合数据，其结构和数据是建立在对原始表的查询基础上的。  
视图中并不存在数据，数据是存放在视图所引用的原始表（基表）中。  
同一张原始表，更具不同用户的不同需求(查询)，可以创建不同的视图。  
###视图的作用
当我们只想给数据库的用户查询功能的时候，就不能向用户暴露出数据表的字段结构，因为这样容易被用户篡改数据。  
所以，我们都是将一个查询做成一个视图，这样，用户在`Java`代码中直接使用了这个视图进行查询，而不用知道这个数据表中的字段。这样就保证了隔离安全。  
同时也简化了`Java`代码中进行数据查询的代码量。降低复杂度。还可以将多个数据表联立查询作为一个视图，更加方便。
还可以将多个物理数据库的联立查询创建为一个视图。

在数据库中创建视图示例如下：
>
	create view trio
	select studentId studentName from student;

以上就是建立了一个查询的视图。
在`Java`代码中使用的时候如下：
>
	select * from trio;

这样即使用`trio`视图进行了一次查询。而不必知道`student`数据表中的数据字段。保证安全、方便。  
也就是说，数据库的管理者让你查什么你就能查什么。一个视图实际上就是数据库管理者事先设定好的查询的结果。只不过这个结果是随数据库实际情形而改变的，查询条件并不会改变，但是查询结果会发生改变。  
注意：视图的作用只能是查询，增加、删除、修改都不能使用。
##SQL中的事务
由上可知，可以使用视图来使查询更安全而方便，但是其他的三种操作并不能使用视图，所以，可以使用事务进行其他的三种操作。  
###事务的定义：  
可以在执行多个`insert、update、dalet`e语句时使用。  
事务(`transcation`)：是作为*单个*逻辑工作单元执行的*一系列*操作，这些操作*作为一个整体一起向系统提交，要么都执行，要么都不执行*。  
事务是一个不可分割开的单元。
###事务的特点：
原子性：事务是一个完整的操作，其中的各步操作不可分(要么全部执行，要么全部不执行)。
一致性：当事务完成时，数据必须处于一致状态。各个数据必须都被操作了。
隔离性：各个事务之间是相互隔离的，表明事务是独立的，不会影响其他事务。  
永久性：事务完成之后，其对数据库的修改被永久保存，事务日志能够保持事务的永久性。

###事务的使用：
####在Java中使用事务
如，当我们在`Java`编程中，要**依次**使用`statement`对象去执行两条`update`语句，那么在依次执行过程中，如果第二条发生故障，那么第一条依旧是已经执行完成了，而第二条未完成。假如我们希望两条语句要么全部执行，要么全部不执行，此时就可以使用事务，进行事务回滚。在故障发生的时候，对已经执行的语句进行事务回滚。  
对于`Connection`对象`conn`而言：  
如果其`AutoCommit`属性是`true`，那么就会是自动提交事务，也就是说`jdbc`会将每一个`SQL`语句视为一个单独的事务，对各自进行自动提交。  
如果价格其`AutoCommit`属性设置为`false`，就会设置为手动提交事务，需要手动提交，将手动提交方法`commit()`方法前的所有`SQL`语句视为一整个事务进行提交执行。这就是使用事务的目的。也就是说，无异常则调用`commit()`，有异常则调用`rollback()`。
事务使用示例：
>
	try
	{
		conn=DriverManager.getConnection("……")；//获得Connection对象。
		conn.setAutoCommit(false);//conn的默认AutoCommit属性是true，也就是说不会进行回滚。会将所有SQL语句视为单独的事务。所以要将自动提交属性先设置为false。  
		stat=conn.createStatement();//获得statement语句
		sta.execUpdate(sql1);
		sta.execUpdate(sql2);
		conn.commit();//此处进行手动提交，将前面的两条SQL语句视为一个事务。如果没有异常，就执行这里的commit，如果有异常，那就执行下面的回滚。
	}
	catch(Exception e)
	{
		conn.rollback();//一旦出现异常，即进行事务回滚，取消已经执行的SQL语句的结果，回滚到原始状态。
		e.printStackTrace();
	}

以上即是事务及回滚的使用。  
注意：此处的事务回滚，只针对一条`conn`连接才可以。如果是有多条`conn`，也就是多个进程都进行操作就不能以此实现回滚了，那必须借助框架或者容器，使用`JTA(java transaction api)`才可以实现。

####在数据库中使用事务
使用`BEGIN TRANSACTION,COMMIT TRANSACTION,ROLLBACK TRANSACTION`关键字即可。  
数据库中很少使用事务。一般都在`Java`代码中使用。

##SQL中的索引
就相当于书前面的目录，可以更快地查找到想要的内容，索引是为查询服务的。  
在数据库的表中，数据实际存储是按照页存放的，一般是4Kb为一页数据，然后查询的时候就可以依据要查找数据的索引找到这个数据所在的页，就好像字典里可以依据字的拼音(就是一种索引)找到这个字所在的页码。  
在数据库中，有一个索引页，这个索引页里面放置的都是各个数据所在页的地址，类似与字典前面的目录页，放置的是各个字所在的页码地址。可以查找目录页获得这个字所在地址，再循地址找到这个字。  
通过索引，可以提高数据库检索速度。  

索引的类型：  
唯一索引：不允许两行具有相同的索引值。  
主键索引：当将一个字段定义为主键，即自动将其作为主键索引，主键索引是唯一索引的的特殊类型，主键索引中每一个值都是唯一且不为空的。  
聚集索引：表中各行的物理顺序与键值的逻辑顺序相同。每个表中只有一个。  
非聚集索引：每个表中可以有多个。  

建索引使用示例：
>
	create nonclustered index IX_name on student(stu_Name) with fillfactor=30;//建非聚集索引。那么在查询的时候，就用stu_Name这个关键字作为索引页建立依据。
>
	select * from student with(index=IX_name) where stu_Name="wt";//在查询的时候，指定使用stu_Name作为索引进行查询。

优缺点：
1. 没有使用索引，也可以查询，但是使用了查询速度会提高。  
2. 带索引的表在数据库中占更多的存储空间，因为还有索引页需要存储。  
3. 操纵修改数据的命令需要更多的时间进行处理，因为索引页也需要修改。

建立索引的原则：  
1. 该列用于频繁搜索  
2. 该列用于对数据进行排序  
3. 但列中的值的重复性高，就不要对其加索引  
4. 当数据表过短不必建立索引

##SQL中的存储过程
过程：就是方法、函数。
存储过程类似与C语言中的*函数*，用于管理任务或应用复杂业务规则，存储过程可以带参数，也可以返回结果，可以包含数据操纵语句、变量、逻辑、控制语句。  
以前业界常将其作为衡量程序员水平的标准。但是现在作为`Java`程序员，此功能被弱化，因为`Java`程序讲究的是与具体的数据库的分离。但是`.net`平台依旧很看重存储过程的时候。
当我们在编程中使用`SQL`语言进行数据库交互的时候，要将程序中`SQ`L语句传递给数据库，让数据库进行处理，效率低。  
所以，我们可以将这些`SQL`语句写在数据库中，构成一个`SQL`过程，在编程代码中，直接调用这个过程，向该过程传递`SQL`操作所需要的参数，即可。这样效率提高了。  
可以使用`T-SQL`编程，使用`if、where`等这些逻辑控制语句。

###存储过程的使用示例：  
数据库中创建转账的存储过程：
>
	create procedure zhuanzhang
		@inId int,//参数列表
		@outId int,
		@money int,//此三者是作为输入参数
		@inMoney int output,
		@outMoney int output//output关键字表明此二参数是作为输出参数的
	as
		update bank set currentMoney = currentMoney +@money where cid = @inId
		update bank set currentMoney = currentMoney -@money where cid = @outId
		select @inMoney = currentMoney from bank where cid = @inId
		select @outMoney = currentMoney from bank where cid = @outId//这里可以使用T-SQL编程，可以用流程控制等语句。

在`Java`代码中调用存储过程使用示例：
>
	try
	{
		int outId=1;
		int inId=2;
		int money=100;//此三者为输入值
		Class.forName(driver);
		conn = DriverManager.getConnection(url, user, password);
>
		CallableStatement csts=conn.prepareCall("{call zhuanzhang(?,?,?,？,?)}");//调用存储过程
>
		csts.setInt(1,inId);
		csts.setInt(2,outId);
		csts.setInt(3,money);//此三者设置输入
>
		csts.registerOutParameter(4,Types.INTEGER);
		csts.registerOutParameter(5,Types.INTEGER);//此二者设置输出
>	
		csts.execute();//执行存储过程
		int out1 = csts.getInt(4);
		int out2 = csts.getInt(5);//获得存储方法的返回值
	}

###存储过程的优势
提高速度。效率更高。  
但为什么`Java`中不推荐使用存储过程？  
`Answer`:因为在`Java`中，提倡将代码与数据库进行分离，也就是说，希望同一个代码可以适用于不同的数据库。而使用存储过程后，就需要针对不同的数据库写不同的存储过程，不适用于与`Java`的特点。

##SQL中的触发器
触发器(`trigger`)是在对表进行插入、修改、删除操作时**自动执行**的存储过程，其实质上是一种特殊的存储过程。  
前面而言的一般的存储过程需要在`Java`源代码中**手动调用执行**。  
实际中触发器用得很少。  
触发器的使用，例如，银行某账户取钱之后，会在*交易信息表*中添加一项，随之也需要在*账户信息*表中添加一项。这两个`SQL`语句可以合成一个事务进行处理。也可以使用触发器，当交易信息表发生改变之后，*不用手动*去账户信息表中执行相应`SQL`语句，而是利用触发器，让账户信息表中的`SQL`语句自动执行。也就是说，我们只用手动操作一张表，另一张表的操作会自动执行。  
再例如，在员工信息表中加上触发器之后，在退休员工表中添加几项的时候，就会自动在员工表中减去几项，这就是触发器的功能。  
触发器具有事务的功能，能够在多表之间执行特殊的业务规则。  
触发器种类：  
`delete`触发器、`insert`触发器、`update`触发器。  
`inserted`表与`deleted`表：  
当触发器触发的时候，系统会自动在内存中创建`deleted`表或者`inserted`表，这两张表只读，不允许修改，在触发器执行完成之后，自动删除。  
`inserted`表：用于临时保存插入或更新后的记录行。  
`deleted`表：用于临时保存删除或更新前的记录行。  
这两个表的记录作用是：用于回滚。


#Java中的JDBC
`JDBC`：`Java DataBase Connection`。实际上就是`Java`应用程序连接数据库的规范，具体的实现就是：类与接口的集合。这个规范是`Java`原公司制作的。
`ODBC`：在之前都是使用`ODBC`来作为连接应用程序与数据库之间的标准规范。  

##使用JDBC的步骤：
1. 将`JDBC`驱动程序的`Jar`包放入我们的程序目录下面，并加入路径。  
2. 加载类，注册类。  
3. 创建`Connection`对象。相当于`Java`程序与数据库之间的路径。  
4. 创建`Statement`对象，相当于获得运输数据的车。  
5. 使用`Statement`对象执行`sql`语句。通过`execuUpdate()`方法来对数据进行更新，包括增、删、改操作。  
   使用`executeQuery()`方法进行数据的查询，得到`ResultSet`对象，该对象就是查询数据库后获得的数据集合的封装。通过该对象的方法`getString()`取得各段结果。`getString()`方法的参数可以是列名或者下标(注意，在`JDBC`中，下标从0开始)，下标更快。  
6. 关闭释放资源。

##桥连与直连
以上的`JDBC`就是所谓的**直连**，也就是说，当一个数据库实现了`JDBC`规范，那么就可以直接通过相应的`API`来连接数据库。但是，如果某些数据库(如、微软单机数据库`Access`)没有实现`JDBC`规范，而是实现了`ODBC`规范。那么就不能进行直连了。  
桥连：`Java`中提供了`JDBC-ODBC`桥，这样就可以通过`JDBC`连接`ODBC`，然后再通过`ODBC`连接至数据库。这就是所谓的桥连。只有`Access`才用桥连，其他都用直连。



