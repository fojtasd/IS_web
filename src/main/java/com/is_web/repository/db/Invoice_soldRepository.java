package com.is_web.repository.db;

import com.is_web.entities.Employee;
import com.is_web.entities.Invoice_sold;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Invoice_soldRepository extends DatabaseRepository<Invoice_sold> {

    @Override
    protected Invoice_sold createEntity(ResultSet resultSet) throws SQLException {
        Invoice_sold entity = new Invoice_sold();

        int id = resultSet.getInt("id");
        String invoice_code = resultSet.getString("invoice_code");
        int companies_id = resultSet.getInt("companies_id");
        int stb_type_id = resultSet.getInt("stb_type_id");
        int amount = resultSet.getInt("amount");
        int price = resultSet.getInt("price");
        boolean is_deleted = resultSet.getBoolean("is_deleted");

        entity.setId(id);
        entity.setInvoice_code(invoice_code);
        entity.setCompanies_id(companies_id);
        entity.setStb_type_id(stb_type_id);
        entity.setAmount(amount);
        entity.setPrice(price);
        entity.setIs_deleted(is_deleted);

        return entity;
    }

    @Override
    protected String getTableName() {
        return "dbo.invoices_sold";
    }

    @Override
    protected PreparedStatement createInsertStatement(Invoice_sold entity) throws SQLException {
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO " + getTableName() + " (invoice_code, companies_id, stb_type_id, amount, price, is_deleted) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getInvoice_code());
        statement.setInt(2, entity.getCompanies_id());
        statement.setInt(3, entity.getStb_type_id());
        statement.setInt(4, entity.getAmount());
        statement.setInt(5, entity.getPrice());
        statement.setBoolean(6, entity.getIs_deleted());

        return statement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Invoice_sold entity) throws SQLException {
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE " + getTableName() + " SET invoice_code = ?, companies_id = ?, stb_type_id = ?, amount = ?, price = ?, is_deleted = ? WHERE id = ?");

        statement.setString(1, entity.getInvoice_code());
        statement.setInt(2, entity.getCompanies_id());
        statement.setInt(3, entity.getStb_type_id());
        statement.setInt(4, entity.getAmount());
        statement.setInt(5, entity.getPrice());
        statement.setBoolean(6, entity.getIs_deleted());
        statement.setInt(7, entity.getId());

        return statement;
    }

    @Override
    public void execTransactionCreateNewEmployee(Invoice_sold newEmployee) throws SQLException {
    }

    @Override
    public void execTransactionUpdateEmployee(Invoice_sold newEmployee) throws SQLException {
    }

    @Override
    public void execTransactionDeleteEmployee(Employee entity) throws SQLException {
    }

    @Override
    public void execTransactionCreateNewLocality(Invoice_sold newEmployee) throws SQLException {
    }
}