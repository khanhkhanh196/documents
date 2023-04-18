DELIMITER //
CREATE DEFINER=`hbstudent`@`localhost` PROCEDURE `update_product`(IN id int,IN product_code varchar(10),IN category int, IN warehouse int,IN product_name nvarchar(100),
IN product_description nvarchar(100), IN image_url nvarchar(100),IN quantity int,sold_quantity int,IN created_date datetime)
BEGIN
update `hb-product`.product
set product_code = product_code,
	category = category,
    warehouse = warehouse,
    product_name = product_name,
    quantity = quantity,
    product_description =  product_description,
    image_url = image_url,
    sold_quantity = sold_quantity,
    updated_date = created_date
where product_id = id;
END
DELLIMITER;
DELIMITER //
CREATE DEFINER=`hbstudent`@`localhost` PROCEDURE `get_product_by_id`(in id int)
BEGIN
	select * from product where product_id = id;
END
DELLIMITER;
DELIMITER //
CREATE DEFINER=`hbstudent`@`localhost` PROCEDURE `get_all_product`()
BEGIN
	select * from product;
END
DELLIMITER;
DELIMITER //
CREATE DEFINER=`hbstudent`@`localhost` PROCEDURE `delete_product_by_id`(IN id int)
BEGIN
	delete from `hb-product`.product where product_id = id;
END
