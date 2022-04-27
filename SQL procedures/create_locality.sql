CREATE PROCEDURE create_locality
    @v_label char(15),
	@v_city char(30),
	@v_street char(30),
	@v_postal char(30),
	@v_phone_office char(15)
AS  
	BEGIN TRANSACTION
		BEGIN TRY
			INSERT INTO dbo.locality (label, city, street, postal, phone_office, employees_count, is_deleted) VALUES(@v_label, @v_city, @v_street, @v_postal, @v_phone_office, 0, 0);
			COMMIT;
		END TRY
		BEGIN CATCH
			ROLLBACK;
		END CATCH
	GO