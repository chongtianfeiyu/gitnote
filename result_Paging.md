#查询结果简单分页
怎样给查询结果分页？  
查询语句时使用：  
第1页：  
select * from booktype order by id limit 0 3  
第2页：  
select * from booktype order by id limit 3 3  
第3页：  
select * from booktype order by id limit 5 3  
