 /*
--简单case函数
		case sex
		when '1' then '男'
		when '2' then '女'
		else '其他' end

--case搜索函数
		case when sex = '1' then '男'
				 when sex = '2' then '女'
				 else '其他' end

这两种方式，可以实现相同的功能。简单case函数的写法相对比较简洁，但是和case搜索函数相比，功能方面会有些限制，比如写判定式。 
还有一个需要注重的问题，case函数只返回第一个符合条件的值，剩下的case部分将会被自动忽略。

--比如说，下面这段sql，你永远无法得到“第二类”这个结果

            case when col_1 in ( 'a', 'b') then'第一类'

            when col_1 in ('a')       then '第二类'

            else '其他' end


*/
SELECT a AS '教师号' ,
				SUM(CASE WHEN c = 1 THEN 1 ELSE 0 END) AS 星期一,
				SUM(CASE WHEN c = 2 THEN 1 ELSE 0 END) AS 星期二,
				SUM(CASE WHEN c = 3 THEN 1 ELSE 0 END) AS 星期三,
				SUM(CASE WHEN c = 4 THEN 1 ELSE 0 END) AS 星期四,
				SUM(CASE WHEN c = 5 THEN 1 ELSE 0 END) AS 星期五
 FROM  t GROUP BY a
--------------------------------------------------------------------------------
 
-- SQL语句增加列、修改列、删除列 

-- 1.增加列：
 
alter table `tb_content` add `column_name` varchar(30);
alter table `tb_content` change `column_name` `column_test` varchar(40) default '001';
alter table tb_content drop column column_test;

-- 2.1. 修改列类型：
-- alter table `tb_content` alter column `column_name` varchar(40);

-- 2.2. 修改列的名称：
-- EXEC  sp_rename   `tableName.column1` , `column2; 
-- (把表名为tableName的column1列名修改为column2)

-- 3.删除列：
 
alter table `tb_content` drop column `column_name`; 
--------------------------------------------------------------------------------
-- oracle
select name from sys.default_constraints
where parent_object_id=object_id('表名')
and parent_column_id=columnproperty(object_id('表名'),'列名','columnid')

mysql 中删除约束的语法：

ALTER TABLE TABLE_name 
DROP [COLUMN] col_name
DROP PRIMARY KEY
DROP INDEX index_name
DROP FOREIGN KEY fk_symbol
没有drop constraint constraint_name， 这是标准SQL ,在mysql中不支持。
----------------------------------------------------------------------------------------
delimiter //
drop procedure if exists while_do_test //
delete from temp_test where id=0 //
create procedure while_do_test(in p_name varchar(20),in p_age int(3),in p_number int(4),out p_result int(2))
begin
declare i int;
select * from temp_test;
set i=0;
set p_result=0;
while i<5 do 
insert into temp_test(`name`,`age`,`number`) values(p_name,p_age,p_number);
set i=i+1;
set p_result=p_result+1;
end while;
select * from temp_test;
end;
//
delimiter ;
select @p_result;
set @p_name='王朝';
set @p_age=25;
set @p_number=1;
-- set @i=1;
call while_do_test(@p_name,@p_age,@P_number,@p_result);
select @p_result;

-------------------------------------------------------------------
delimiter //
drop procedure if exists if_then_else //
create procedure if_then_else(in parameter int)
begin
declare var int;
set var = parameter + 1;
select * from temp_test;
if var=0 then
insert into temp_test values(1,'小张',15,1);
end if;
select * from temp_test;
if parameter=0 then 
update temp_test set `age`=16 where `id`=1;
else 
update temp_test set `age`=17 where `id`=1;
end if;
select * from temp_test;
end;
//
delimiter ;
set @parameter=-1;
call if_then_else(@parameter);
set @parameter=0;
call if_then_else(@parameter);


/*
case语句:case when   then  else 
case
        when var=0 then
               insert into t values(30);
        when var>0 then
        when var<0 then
        else

end case
*/

/*

循环语句
while ···· end while:
而while则是执行前进行检查。
while 条件 do
--循环体
end while
*/

/*
它在执行操作后检查结果，而while则是执行前进行检查。
repeat···· end repeat：
repeat
--循环体
until循环条件     
end repeat;

*/
/*
loop ·····endloop:
loop循环不需要初始条件，这点和while 循环相似，同时和repeat循环一样不需要结束条件, leave语句的意义是离开循环。
1.  mysql > DELIMITER //  
2.  mysql > CREATE PROCEDURE proc6 ()  
3.       -> begin 
4.       -> declare v int;  
5.       -> set v=0;  
6.       -> LOOP_LABLE:loop  
7.       -> insert into t values(v);  
8.       -> set v=v+1;  
9.       -> if v >=5 then 
10.      -> leave LOOP_LABLE;  
11.      -> end if;  
12.      -> end loop;  
13.      -> end;  
14.      -> //  
15.mysql > DELIMITER ;  123456789101112131415



LABLES 标号：

标号可以用在begin repeat while 或者loop 语句前，语句标号只能在合法的语句前面使用。可以跳出循环，使运行指令达到复合语句的最后一步。



ITERATE迭代



ITERATE:

1.    通过引用复合语句的标号,来从新开始复合语句
2.  mysql > DELIMITER //  
3.  mysql > CREATE PROCEDURE proc10 ()  
4.       -> begin 
5.       -> declare v int;  
6.       -> set v=0;  
7.       -> LOOP_LABLE:loop  
8.       -> if v=3 then   
9.       -> set v=v+1;  
10.      -> ITERATE LOOP_LABLE;  
11.      -> end if;  
12.      -> insert into t values(v);  
13.      -> set v=v+1;  
14.      -> if v>=5 then 
15.      -> leave LOOP_LABLE;  
16.      -> end if;  
17.      -> end loop;  
18.      -> end;  
19.      -> //  
20.mysql > DELIMITER ; 

---------------------


*/



/*
Labels   标号和 END Labels 结束标号
在使用loop的时候，使用到的labels标号，对于labels可以用到while，loop，rrepeat等循环控制语句中。而且有必要好好认识一下lables!！
mysql> create procedure pro13()
    -> label_1:begin
    -> label_2:while 0=1 do leave label_2;end while;
    -> label_3:repeat leave label_3;until 0=0 end repeat;
    -> label_4:loop leave label_4;end loop;
    -> end;//
Query OK, 0 rows affected (0.00 sec)
    上面这里例子显示了可以在BEGIN、WHILE、REPEAT或者LOOP语句前使用语句标号，语句标号 只能在合法的语句前使用，所以LEAVE label_3意味着离开语句标号名为label_3的语句或符合语句。
    其实，也可以使用END labels来表示标号结束符。
mysql> create procedure pro14()
    -> label_1:begin
    -> label_2:while 0=1 do leave label_2;end while label_2;
    -> label_3:repeat leave label_3;until 0=0 end repeat label_3;
    -> label_4:loop leave label_4;end loop label_4;
    -> end label_1;//
Query OK, 0 rows affected (0.00 sec)
 上面就是使用了标号结束符，其实这个结束标号并不是十分有用，而且他必须和开始定义的标号名字一样，否则就会报错。
如果要养成一个良好的编程习惯方便他人阅读的话，可以使用这个标号结束符。

*/

----------------------------------
SELECT age,GROUP_CONCAT(`id`) FROM de_wight GROUP BY age;
SELECT age,GROUP_CONCAT(`name`) FROM de_wight GROUP BY age;
SELECT GROUP_CONCAT(`name`) FROM de_wight GROUP BY age;
SELECT GROUP_CONCAT(`name` order by `id` desc ) FROM de_wight GROUP BY age;

UPDATE tb_content_category SET `name` = CONCAT(`name`,'','test');
UPDATE tb_content_category SET `name` = CONCAT(`name`,'test');
UPDATE tb_content_category SET `name` = CONCAT('test',`name`) WHERE id = 30;





































