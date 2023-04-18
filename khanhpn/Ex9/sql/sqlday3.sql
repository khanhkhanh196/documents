DROP SCHEMA IF EXISTS `hb-product`;

CREATE SCHEMA `hb-product`;

use `hb-product`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `warehouse`;
DROP TABLE IF EXISTS `category`;


create table `warehouse` (
`warehouse_id` int not null auto_increment,
`warehouse_code` varchar(20) not null unique,
`warehouse_name` nvarchar(100) not null,
`location` nvarchar(100) default null,
`created_date` datetime default null,
`updated_date` datetime default null,

primary key (`warehouse_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table `category` (
`category_id` int not null auto_increment,
`category_code` varchar(20) not null unique,
`category_name` nvarchar(100) not null,
`category_description` nvarchar(100) default null,
`created_date` datetime default null,
`updated_date` datetime default null,

primary key (`category_id`)
)  ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table `product` (
`product_id` int not null auto_increment,
`product_code` varchar(50) not null unique,
`category_detail_id` int default null,
`warehouse_detail_id` int default null,
`product_name` nvarchar(100),
`product_description` nvarchar(100) default null,
`image_url` nvarchar(100) default null,
`quantity` int default null,
`sold_quantity` int default null,
`created_date` datetime default null,
`updated_date` datetime default null,

constraint `category_fk_1` foreign key (`category_detail_id`)
references `category` (`category_id`) on delete cascade on update cascade,
 
constraint `warehouse_fk_1` foreign key (`warehouse_detail_id`) 
references `warehouse` (`warehouse_id`) on delete set null,

primary key (`product_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;


DROP TABLE IF EXISTS `product_payload`;
create table `product_payload` (
`id` int not null,
`sold_quantity` bigint default null,
`created_date` datetime default null,
`updated_date` datetime default null
);


DROP TABLE IF EXISTS `user`;
create table `user` (
`id` int not null auto_increment,
`username` nvarchar(50),
`password` nvarchar(100),
`role` nvarchar(50),

primary key (`id`)
);

insert into `user`(
`username`,
`password`,
`role` ) values ('admin','$2y$12$YjviNEfCr1FRbB9zZtCIUeqQDceDcyz8xR1z35LIJuTi3ph0D8PYW','ROLE_ADMIN')