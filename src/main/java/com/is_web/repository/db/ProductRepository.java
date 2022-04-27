package com.is_web.repository.db;

import com.is_web.entities.Employee;
import com.is_web.entities.Product;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProductRepository extends DatabaseRepository<Product> {

    @Override
    protected Product createEntity(ResultSet resultSet) throws SQLException {
        Product entity = new Product();

        int id = resultSet.getInt("id");
        String serial_number = resultSet.getString("serial_number");
        Integer employees_id = resultSet.getInt("employees_id");
        int stb_type_id = resultSet.getInt("stb_type_id");
        int invoice_sold_id = resultSet.getInt("invoices_sold_id");
        int invoice_bought_id = resultSet.getInt("invoices_bought_id");
        boolean is_deleted = resultSet.getBoolean("is_deleted");

        if(stb_type_id != 0) {
            entity.setId(id);
            entity.setSerial_number(serial_number);
            entity.setEmployee_id(employees_id);
            entity.setStb_type_id(stb_type_id);
            entity.setInvoice_bought_id(invoice_bought_id);
            entity.setInvoice_sold_id(invoice_sold_id);
            entity.setIs_deleted(is_deleted);
            return entity;
        }
        else{
            return null;
        }

    }

    @Override
    protected String getTableName() {
        return "dbo.products";
    }

    @Override
    protected PreparedStatement createInsertStatement(Product entity) throws SQLException {
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO " + getTableName() + " (serial_number, employees_id, stb_type_id, invoices_sold_id, invoices_bought_id, is_deleted) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);

        statement.setString(1, entity.getSerial_number());

        if(entity.getEmployee_id() == null){
            statement.setNull(2, java.sql.Types.INTEGER);
        }
        else statement.setInt(2, entity.getEmployee_id());

        statement.setInt(3, entity.getStb_type_id());

        if (entity.getInvoice_sold_id() == null){
            statement.setNull(4, java.sql.Types.INTEGER);
        }
        else statement.setInt(4, entity.getInvoice_sold_id());

        statement.setInt(5, entity.getInvoice_bought_id());
        statement.setBoolean(6, entity.getIs_deleted());

        return statement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Product entity) throws SQLException {
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE " + getTableName() + " SET serial_number = ?, employees_id = ?, stb_type_id = ?, invoices_sold_id = ?, invoices_bought_id = ? WHERE id = ?");

        statement.setString(1, entity.getSerial_number());

        if(entity.getEmployee_id() == null){
            statement.setNull(2, java.sql.Types.INTEGER);
        }
        else{
            statement.setInt(2, entity.getEmployee_id());
        }

        statement.setInt(3, entity.getStb_type_id());

        if(entity.getInvoice_sold_id() == null){
            statement.setNull(4, java.sql.Types.INTEGER);
        }
        else{
            statement.setInt(4, entity.getInvoice_sold_id());
        }

        statement.setInt(5, entity.getInvoice_bought_id());
        statement.setInt(6, entity.getId());

        return statement;
    }


    @Override
    public void execTransactionCreateNewEmployee(Product newEmployee) throws SQLException {

    }

    @Override
    public void execTransactionUpdateEmployee(Product newEmployee) throws SQLException {

    }

    @Override
    public void execTransactionDeleteEmployee(Employee entity) throws SQLException {

    }

    @Override
    public void execTransactionCreateNewLocality(Product newEmployee) throws SQLException {

    }
}