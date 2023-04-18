DROP SCHEMA IF EXISTS `hb-product`;

CREATE SCHEMA `hb-product`;

use `hb-product`;
DROP TABLE IF EXISTS `product`;
DROP TABLE IF EXISTS `warehouse`;
DROP TABLE IF EXISTS `category`;


create table `warehouse` (
`warehouse_id` int not null auto_increment,
`warehouse_code` int not null unique,
`warehouse_name` nvarchar(100) not null,
`location` nvarchar(100) default null,
`created_date` date default null,
`updated_date` date default null,

primary key (`warehouse_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table `category` (
`category_id` int not null auto_increment,
`category_code` int not null unique,
`category_name` nvarchar(100) not null,
`category_description` nvarchar(100) default null,
`created_date` date default null,
`updated_date` date default null,

primary key (`category_id`)
)  ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

create table `product` (
`product_id` int not null auto_increment,
`product_code` int not null unique,
`category` int default null,
`warehouse` int default null,
`product_name` nvarchar(100),
`product_description` nvarchar(100) default null,
`image_url` nvarchar(100) default null,
`quantity` int default null,
`sold_quantity` int default null,
`created_date` date default null,
`updated_date` date default null,

constraint `category_fk_1` foreign key (`category`)
references `category` (`category_id`) on delete cascade on update cascade,
 
constraint `warehouse_fk_1` foreign key (`warehouse`) 
references `warehouse` (`warehouse_id`) on delete set null,

primary key (`product_id`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8mb4;

