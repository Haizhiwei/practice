-- 注释里的``表示缺省的表名，列名
/*
没有主键
如果在设计表时没有指定主键， 
导入数据时可能出现重复导入， 
导致一个表中出现多条完全相同的多条记录。 
以下是解决这个问题的思路：

使用distinct语句筛选出不重复的记录存入临时表tmp；
create table tmp as (select distinct sno,sname,age,sex from s);
删除原表中的数据记录
delete from s;
将临时表中的数据插入到原表。
insert into s select * from tmp;
删除临时表
drop table tmp;
*/
DROP TABLE IF EXISTS `temp`;
CREATE TABLE `temp` AS (SELECT DISTINCT `name`,`age`,`number` FROM de_wight );

DROP TABLE IF EXISTS `de_wight_two`;
CREATE TABLE `de_wight_two`(
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `name` VARCHAR(20) DEFAULT NULL COMMENT '姓名',
  `age` INT(3) DEFAULT NULL COMMENT '年龄',
  `number` INT(4) DEFAULT '1' COMMENT '编号',
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8 COMMENT='去重表2';
INSERT INTO `de_wight_two`(`name`,`age`,`number`) SELECT * FROM temp;
/*
sql 中的关键字
注释里的``表示缺省的表名，列名
create table 
	insert into `` values(``,``,``)

	group by `` having 条件   					去重统计
	select `` from  `` order by   ``		desc (*desc降序 默认升序)
	distinct 														用于去重（独特的）select distinct `` from  `tbname` 
  
	count()         										统计
	sum ()          										求和
	AVG()           										平均数
	
	drop table 
	where `` in()  											在in()范围以内
	         between expr1  and expr2  	在expr1到expr2之间
					 like                       字符串匹配，支持通配符
					 is null
					 not in()
			     not exists()
  delete from `tbname` where 		 			清空表数据 可以加条件
	truncate table ``              			清空表比delete快

	
约束：
 not null   auto_increment            非空   自增长
 primary  ky(``)                      主键约束
 key [索引名]([列名])                 创建索引  eg: KEY `parent_id` (`parent_id`,`status`) USING BTREE, KEY `sort_order` (`sort_order`)


数据类型：
	int() 4个字节  bigint() 8个字节
	char()
	varchar()
	datetime 
	decimal(M,D)								DECIMAL和NUMERIC类型在MySQL中视为相同的类型。它们用于保存必须为确切精度的值。
# MySql中的浮点类型有float，double和real。他们定义方式为：FLOAT(M,D) 、 REAL(M,D) 、 DOUBLE PRECISION(M,D)。
# REAL就是DOUBLE ，如果SQL服务器模式包括REAL_AS_FLOAT选项，REAL是FLOAT的同义词而不是DOUBLE的同义词。
	float()
	double precision()
#	字符串类型指CHAR、VARCHAR、BINARY、VARBINARY、BLOB、TEXT、ENUM和SET。

	date               '0000-00-00'
	time               '00:00:00'
	datetime					 '0000-00-00 00:00:00'   支持的范围为'1000-01-01 00:00:00'到'9999-12-31 23:59:59'。
	timestamp          '0000-00-00 00:00:00'  TIMESTAMP类型同样包含日期和时间，范围从'1970-01-01 00:00:01' UTC 到'2038-01-19 03:14:07' UTC。
	
	
other:
# engine 引擎   charset 设置字符集  commemt 注释（评论，意见）
 ENGINE=InnoDB AUTO_INCREMENT=98 DEFAULT CHARSET=utf8 COMMENT='内容分类';                     
	
*/

-- ---------------------------------------------------------------------------------------------------------------------------------              
/*
使用 TRUNCATE TABLE 删除所有行。若要删除表中的所有行，则 TRUNCATE TABLE 语句是一种快速、无日志记录的方法。
该语句总是比不带条件的 DELETE 语句要快，因为 DELETE 语句要记录对每行的删除操作。

而 TRUNCATE TABLE 语句只记录整个数据页的释放。TRUNCATE TABLE 语句立即释放由该表的数据和索引占用的所有空间。所有索引的分发页也将释放。
与 DELETE 语句相同，使用 TRUNCATE TABLE 清空的表的定义，
同其索引和其它相关的对象一起仍保留在数据库中。必须使用 DROP TABLE 语句才能除去表的定义。
*/
DELETE FROM `tb_content`;/*delete可以加where*/
TRUNCATE TABLE `tb_content_category`;
TRUNCATE TABLE `tb_content`;
-- ---------------------------------------------------------------------------------------------------------------------------------
/*有主键*/
-- 1
 DELETE FROM `de_wight_three` WHERE `id` NOT IN (
   SELECT c.id FROM (SELECT MIN(b.`id`) AS `id` FROM `de_wight_three` b  GROUP BY b.`name`,b.`age`,b.`number`) c
); 

-- 2
DELETE FROM `de_wight_three` 
WHERE
(`name`,`age`,`number`) 
IN ( 
	SELECT b.`name`,b.`age`,b.`number` FROM ( SELECT `name`,`age`,`number` FROM `de_wight_three`
																						GROUP BY `name`,`age`,`number` HAVING count(*) > 1 ) b ) 
AND id NOT IN (
SELECT
a.id 
FROM
( SELECT min(id) AS id FROM `de_wight_three`  GROUP BY `name`,`age`,`number`  HAVING count(*) > 1 ) a 
);
-- -----------------------------------------------------------------------------------------------------------------

 

-- 查询一条重复数据的主键（最小或最大）
SELECT MIN(b.`id`) FROM `de_wight_three` b  GROUP BY b.`name`,b.`age`,b.`number`;
-- 查询所有重复记录的主键id
SELECT b.`id` FROM  `de_wight_three` b 
							WHERE
											b.`id` 
											NOT IN(SELECT c.`id` FROM (SELECT * FROM `de_wight_three` GROUP BY `name`,`age`,`number` HAVING COUNT(*)>1) AS c)  						


-- 查询重复记录一条
SELECT `name`,`age`,`number` FROM  `de_wight` GROUP BY `name`,`age`,`number` HAVING count(*)>1
-- 查询重复记录所有
select `name`,`age`,`number` from `de_wight` a where (a.`name`,a.`age`,a.`number`)
														 in 
																(select `name`,`age`,`number`  from `de_wight` group by `name`,`age`,`number` having count(*)>1);

/*
首先我们要知道where是什么：一个判断符。在SQL操作中，控制只选择指定的行。 
in的其实归类于特殊的比较运算符 
expr1 between expr2 and expr3：表示expr1的值在expr2和expr3之间 
expr in（expr2，expr3，expr4，…）表示expr1等于后面括号里面的任意一个表达式的值 
like：字符串匹配，like后的字符创支持通配符 
is null：要求指定值等于null
*/



























