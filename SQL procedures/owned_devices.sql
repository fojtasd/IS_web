CREATE PROCEDURE owned_devices
(@v_employee_id int)
AS
BEGIN 
	SELECT * FROM dbo.products
	JOIN dbo.employees ON employees.id = products.employees_id
	JOIN dbo.locality ON employees.locality_id = locality.id
	WHERE products.employees_id = @v_employee_id;
END
