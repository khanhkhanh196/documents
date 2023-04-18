DELIMITER //
CREATE DEFINER=`hbstudent`@`localhost` PROCEDURE `update_warehouse`(IN id int,IN warehouse_code varchar(10),IN warehouse_name nvarchar(100),
IN location nvarchar(100),IN updated_date datetime)
BEGIN
update `hb-product`.product
set  
	warehouse_code = warehouse_code,
	warehouse_name = warehouse_name,
	location = location,
	updated_date = updated_date
where warehouse_id = id;
END
DELLIMITER;
DELIMITER //
CREATE DEFINER=`hbstudent`@`localhost` PROCEDURE `get_warehouse_by_id`(in id int)
BEGIN
	select * from warehouse where warehouse_id = id;
END
DELLIMITER;
DELIMITER //
CREATE DEFINER=`hbstudent`@`localhost` PROCEDURE `get_all_warehouse`()
BEGIN
	select * from warehouse;
END
DELLIMITER;
DELIMITER //
CREATE DEFINER=`hbstudent`@`localhost` PROCEDURE `delete_warehouse_by_id`(IN id int)
BEGIN
	delete from `hb-product`.warehouse where warehouse_id = id;
END