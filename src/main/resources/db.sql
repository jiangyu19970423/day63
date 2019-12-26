#动态查询资质审核列表
#1.实现查询
select qu.*,uu.name uploadUser,cu.name checkUser
from
	qualification qu
LEFT JOIN
	sys_user uu
on
	qu.upload_user_id=uu.id
LEFT JOIN
	sys_user cu
on
	qu.check_user_id=cu.id
where
	qu.del_flag=0


#2.条件过滤
select qu.*,uu.name uploadUser,cu.name checkUser
from
	qualification qu
LEFT JOIN
	sys_user uu
on
	qu.upload_user_id=uu.id
LEFT JOIN
	sys_user cu
on
	qu.check_user_id=cu.id
where
	qu.del_flag=0
and
	qu.`check`=0
and
	qu.type=0
and
	qu.create_date>'2019-02-01'
and
	qu.create_date<'2019-10-10'


#数据库会自动进行日期类型数据转换
select YEAR(SYSDATE()) from dual;
select DATE(SYSDATE()) from dual;



#根据资质id  查询资质所在的公司id

select
	su.office_id
from
	qualification qu,sys_user su
where
	qu.upload_user_id=su.id
and
	qu.id=2



#根据条件查询考试列表信息

select
	ex.*,su.name userName,so.name officeName
from
	examine ex,sys_user su,sys_office so
where
	ex.del_flag=0
and
	ex.examine_user_id=su.id
and
	su.office_id=so.id
and
	so.id=56
and
	su.name like CONCAT('%','平台','%')
and
	ex.type=1