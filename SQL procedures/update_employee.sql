CREATE PROCEDURE update_employee
	@v_id int,
    @v_name char(30),
	@v_surname char(30),
	@v_city char(30),
	@v_street char(30),
	@v_postal char(30),
	@v_email char(30),
	@v_phone char(30),
	@v_locality_id int
AS  
	BEGIN TRANSACTION
		BEGIN TRY
			declare @v_employee_old_locality int;
			SELECT locality_id = @v_employee_old_locality FROM dbo.employees WHERE id = @v_id;
			UPDATE dbo.employees SET name = @v_name, surname = @v_surname, city = @v_city, street = @v_street, postal = @v_postal, email = @v_email, phone = @v_phone, locality_id = @v_locality_id, is_deleted = 0 WHERE id = @v_id;
			IF(@v_employee_old_locality != @v_locality_id)
				BEGIN
					UPDATE dbo.locality SET employees_count = employees_count + 1 WHERE id = @v_locality_id;
					UPDATE dbo.locality SET employees_count = employees_count - 1 WHERE id = @v_employee_old_locality;
				END
			COMMIT;
		END TRY
		BEGIN CATCH
			ROLLBACK;
		END CATCH
	GO

