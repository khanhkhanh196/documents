DELIMITER //
CREATE DEFINER=`hbstudent`@`localhost` PROCEDURE `update_category`(IN id int,IN warehouse_code varchar(10),IN warehouse_name nvarchar(100),
IN category_description nvarchar(100),IN updated_date datetime)
BEGIN
update `hb-product`.product
set  
	category_code = warehouse_code,
	category_name = warehouse_name,
	category_description = category_description,
	updated_date = updated_date
where warehouse_id = id;
END
DELLIMITER;
DELIMITER //
CREATE DEFINER=`hbstudent`@`localhost` PROCEDURE `get_category_by_id`(in id int)
BEGIN
	select * from category where category_id = id;
END
call get_category_by_id(1)
DELLIMITER;
DELIMITER //
CREATE DEFINER=`hbstudent`@`localhost` PROCEDURE `get_all_category`()
BEGIN
	select * from category;
END
DELLIMITER;
DELIMITER //
CREATE DEFINER=`hbstudent`@`localhost` PROCEDURE `delete_category_by_id`(IN id int)
BEGIN
	delete from `hb-product`.category where category_id = id;
END

