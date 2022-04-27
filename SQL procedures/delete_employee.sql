CREATE PROCEDURE delete_employee
	@v_id int
AS
	BEGIN TRANSACTION
		BEGIN TRY
			DECLARE @device int;
			SET @device = (SELECT COUNT(*) FROM dbo.products WHERE employees_id = @v_id);
			IF(@device = 0)
				BEGIN
					UPDATE dbo.employees SET is_deleted = 1 WHERE id = @v_id;
					UPDATE dbo.locality SET employees_count = employees_count - 1 WHERE id = (SELECT locality_id FROM dbo.employees WHERE id = @v_id);
				END
			ELSE
				BEGIN
					UPDATE dbo.products SET employees_id = NULL WHERE employees_id = @v_id;
					UPDATE dbo.employees SET is_deleted = 1 WHERE id = @v_id;
					UPDATE dbo.locality SET employees_count = employees_count - 1 WHERE id = (SELECT locality_id FROM dbo.employees WHERE id = @v_id);
				END
			COMMIT;
		END TRY
		BEGIN CATCH
			ROLLBACK;
		END CATCH;
GO

			



