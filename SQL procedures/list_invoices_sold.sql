CREATE PROCEDURE list_invoices_sold
(@v_company_id int)
AS
BEGIN 
	SELECT invoices_sold.id, invoices_sold.invoices_code, invoices_sold.stb_type_id, invoices_sold.amount, invoices_sold.price, companies.name, companies.vat, companies.city, companies.email 
	FROM dbo.invoices_sold
	JOIN dbo.companies ON dbo.companies.id = invoices_sold.companies_id
	WHERE dbo.invoices_sold.companies_id = @v_company_id;
END
