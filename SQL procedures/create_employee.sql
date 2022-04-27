CREATE PROCEDURE create_employee
    @v_name char(30),
	@v_surname char(30),
	@v_city char(30),
	@v_street char(30),
	@v_postal char(30),
	@v_email char(30),
	@v_phone char(15),
	@v_locality_id int,
	@v_product_id int
AS  
	BEGIN TRANSACTION
		BEGIN TRY
			INSERT INTO dbo.employees(name, surname, city, street, postal, email, phone, locality_id, is_deleted) VALUES(@v_name, @v_surname, @v_city, @v_street, @v_postal, @v_email, @v_phone, @v_locality_id, 0);
			UPDATE dbo.locality SET employees_count = employees_count + 1 WHERE id = @v_locality_id;
			DECLARE @v_employee_new_id int;
			SET @v_employee_new_id = (SELECT TOP 1 id FROM dbo.employees ORDER BY id DESC);
			UPDATE dbo.products SET employees_id = @v_employee_new_id WHERE id = @v_product_id;
			COMMIT;
		END TRY
		BEGIN CATCH
			ROLLBACK;
		END CATCH
	GO 

