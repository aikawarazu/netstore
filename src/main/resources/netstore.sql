create  database netmarket;
use netmarket;

create table if not exists book_info
(
	book_id bigint auto_increment
		primary key,
	book_name varchar(50) charset latin1 null,
	book_author varchar(50) charset latin1 null,
	book_version varchar(50) charset latin1 null,
	price double not null,
	category_id bigint null,
	publisher_name varchar(100) null,
	description varchar(500) null,
	pic varchar(100) null,
	picture2 varchar(100) null
)
engine=InnoDB;

create table if not exists category
(
	id bigint auto_increment
		primary key,
	name varchar(50) not null,
	description varchar(500) not null
)
comment '商品的类别' engine=InnoDB collate=utf8_bin;

create table if not exists mng_resource
(
	resource_id varchar(50) not null
		primary key,
	resource_url varchar(100) null
)
engine=InnoDB;

create table if not exists mng_role_resource
(
	role_id bigint not null
		primary key,
	resource_id varchar(50) null
)
engine=InnoDB;

create table if not exists permission
(
	permission_id int auto_increment comment '权限ID'
		primary key,
	permission_name varchar(20) charset latin1 not null comment '权限名称，唯一标识',
	permission_desc varchar(50) charset latin1 default '****' not null comment '权限描述',
	constraint `Index 2`
		unique (permission_name)
)
engine=InnoDB;

create table if not exists persistent_logins
(
	user_id bigint not null,
	series varchar(64) charset latin1 not null
		primary key,
	token varchar(64) charset latin1 not null,
	last_used timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP
)
comment '用户登陆认证信息保存' engine=InnoDB;

create table if not exists user_identification
(
	identify varchar(45) charset latin1 not null,
	user_id bigint null,
	login_type enum('TEL', 'EMAIL', 'QQ', 'WECHAT', 'WEIBO') charset latin1 null,
	login_info varchar(200) charset latin1 null,
	bind_time timestamp null,
	unbind_time timestamp null,
	binded tinyint(1) not null,
	constraint identify_UNIQUE
		unique (identify)
)
engine=InnoDB;

create table if not exists user_info
(
	user_id bigint auto_increment comment '用户唯一标识'
		primary key,
	password varchar(100) charset latin1 not null comment '密码',
	show_id varchar(100) charset latin1 null comment '唯一标识，用于链接中展示',
	gender enum('MALE', 'FEMALE', 'UNKNOWN', 'null') charset latin1 null comment '性别',
	birthday date null,
	telphone varchar(20) charset latin1 null,
	create_time timestamp default CURRENT_TIMESTAMP not null,
	username varchar(20) not null,
	head_icon varchar(20) charset latin1 not null
)
engine=InnoDB;

create table if not exists user_info_detail
(
	user_id bigint null,
	introduce varchar(50) null,
	country varchar(50) null,
	province varchar(50) not null,
	city varchar(50) null,
	brief varchar(1000) null,
	headicon varchar(100) null
)
engine=InnoDB;

create table if not exists user_permission
(
	user_id bigint not null,
	permission_id int not null,
	constraint FK_PERMISSION_ID
		foreign key (permission_id) references permission (permission_id)
			on update cascade on delete cascade,
	constraint user_id
		foreign key (user_id) references user_info (user_id)
			on update cascade on delete cascade
)
engine=InnoDB;

create index `Index 1`
	on user_permission (user_id, permission_id);

create table if not exists user_product
(
	id bigint auto_increment
		primary key,
	product_id bigint default '0' not null comment '产品ID',
	title varchar(50) not null comment '商品名',
	price decimal(10,2) not null comment '价格',
	discount decimal(10,2) not null comment '折扣',
	create_time datetime default CURRENT_TIMESTAMP not null comment '创建时间',
	update_time datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
comment '用户产品表' engine=InnoDB;

create table if not exists user_salt
(
	user_id bigint not null
		primary key,
	salt varchar(50) charset latin1 not null
)
engine=InnoDB;

