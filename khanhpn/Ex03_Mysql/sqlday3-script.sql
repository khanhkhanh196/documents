use `hb-product`;


insert into `warehouse` (`warehouse_code`,`warehouse_name`,`location`,`created_date`,`updated_date`) values
( 1, "Apple Ware House", "California",'2020-12-4', null),
( 2, "Samsung Ware House", "Korean",'2020-12-4', null),
( 3, "Dell Ware House", "China",'2020-12-4', null),
( 4, "MSI Ware House", "Viet Nam",'2020-12-4', null);

insert into `category` (`category_code`,`category_name`,`category_description`,`created_date`,`updated_date`) values
(1,"Apple",null,null,null),
(2,"Samsung",null,null,null),
(3,"Dell",null,null,null),
(4,"MSI",null,null,null);

insert into `product` (`product_code`,`category`,`warehouse`,`product_name`,`product_description`,`image_url`,`quantity`,`sold_quantity`,`created_date`,`updated_date`) values
(1,1,1,"Điện Thoại iPhone 5", null, null, 10, 10, null, null),
(2,1,1,"Điện thoại iPhone 6", null, null, 40, 10, null, null),
(3,1,1,"Điện Thoại iPhone 7", null, null, 70, 10, null, null),
(4,1,1,"Điện thoại iPhone 8", null, null, 90, 10, null, null),
(5,1,1,"Điện thoại iPhone 9", null, null, 100, 10, null, null),
(6,1,1,"Laptop MacBook 2020", null, null, 10, 10, null, null),
(7,1,1,"MacBook 2019", null, null, 40, 10, null, null),

(10,2,2,"Điện Thoại Samsung Galaxy 7", null, null, 1000, 100, null, null),
(11,2,2,"Điện Thoại Samsung Galaxy 8 ", null, null, 999, 588, null, null),
(12,2,2,"Điện thoại Samsung Galaxy 9", null, null, 17000, 10767, null, null),
(13,2,2,"Laptop Samsung", null, null, 10420, 900, null, null),

(15,3,3,"PC Dell Elite 1", null, null, 123573, 100, null, null),
(16,3,3,"PC Dell Elite 2", null, null, 32356, 788, null, null),
(17,3,3,"PC Dell Elite 3", null, null, 2331423, 611, null, null),
(18,3,3,"Monitor Dell P2413H", null, null, 525234, 433, null, null),
(19,3,3,"MonitorDell P2419Q", null, null, 123421, 225, null, null),

(22,4,4,"Monitor MSI PRP MP 7", null, null, 12345, 443, null, null),
(23,4,4,"Monitor MSI GSX G271", null, null, 12323, 913, null, null),
(24,4,4,"Monitor MSI OPTIC AG32V", null, null, 54667, 234, null, null);

update `hb-product`.product
set product_code = 25,
	category = 4,
    warehouse = 4,
    product_name = "Monitor MSI OPTIC",
    quantity = 100,
    sold_quantity = 90
where product_id = 1;
delete from `hb-product`.product where product_id = 1;

select * from `hb-product`.warehouse;
update `hb-product`.warehouse
set 
	warehouse_code = 5,
    warehouse_name = "A new Warehouse",
    location = "Ha Noi",
    created_date = '2020-1-1',
    updated_date = '2020-12-04'
where warehouse_id = 1;

delete from `hb-product`.warehouse where warehouse_id = 1;


update `hb-product`.category
set category_code = 5,
	category_name = "LG",
    category_description = "Hãng này thật là thú vị",
    created_date = '2020-11-04',
    updated_date = '2020-12-04'
where category_id = 3;

select * from `hb-product`.product p
where p.product_name like '%Điện Thoại%' and p.category = 1;

select c.category_id, sum(p.quantity) from `hb-product`.product p
left join `hb-product`.category c
on p.category = c.category_id
group by c.category_id
order by sum(p.quantity) desc;

SET autocommit = 0;  
start transaction;
	savepoint my_savepoint;
	delete from `hb-product`.category
	where category_id = 1;
commit;


start transaction;
	savepoint my_savepoint;
	delete from `hb-product`.category
	where category_id = 2;
    rollback to my_savepoint;
    release savepoint my_savepoint;
commit;

CREATE PROCEDURE `getTop10SoldItem`()
BEGIN
	select p.product_name,p.sold_quantity from `hb-product`.product p
	order by p.sold_quantity desc limit 10;
END
call getTop10SoldItem();
