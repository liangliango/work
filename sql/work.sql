use work;

insert into `user`(login_id, password) values ('GL123456', 'e10adc3949ba59abbe56e057f20f883e');

insert into `userwithgroup` values (1, 1, 'GL123456');

insert into `group1` values
(1,'对职员用户的增删改查','管理组'),
(2,'填写货运单','客户组'),
(3,'司机组','司机组');

insert into `page_`(page_name) values
('货运单管理'), ('运输合同管理'), ('路线管理'), ('财务管理'),
('人员管理'), ('密码服务'), ('司机服务'), ('客户服务');

insert into `pagewithgroup`(page_id, group_id) values
(1, 1), (2, 1), (3, 1), (4, 1), (5, 1), (6, 1), (7, 3), (8, 2);


