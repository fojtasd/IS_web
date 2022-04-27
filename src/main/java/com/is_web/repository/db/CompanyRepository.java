package com.is_web.repository.db;

import com.is_web.entities.Company;
import com.is_web.entities.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class CompanyRepository extends DatabaseRepository<Company> {

    @Override
    protected Company createEntity(ResultSet resultSet) throws SQLException {
        Company entity = new Company();

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String vat = resultSet.getString("vat");
        String city = resultSet.getString("city");
        String street = resultSet.getString("street");
        String postal = resultSet.getString("postal");
        String phone = resultSet.getString("phone");
        String email = resultSet.getString("email");
        Boolean is_deleted = resultSet.getBoolean("is_deleted");

        entity.setId(id);
        entity.setName(name);
        entity.setVat(vat);
        entity.setCity(city);
        entity.setStreet(street);
        entity.setPostal(postal);
        entity.setPhone(phone);
        entity.setEmail(email);
        entity.setIs_deleted(is_deleted);

        return entity;
    }

    @Override
    protected String getTableName() {
        return "dbo.companies";
    }

    @Override
    protected PreparedStatement createInsertStatement(Company entity) throws SQLException {
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO " + getTableName() + " (name, vat, city, street, postal, phone, email, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getVat());
        statement.setString(3, entity.getCity());
        statement.setString(4, entity.getStreet());
        statement.setString(5, entity.getPostal());
        statement.setString(6, entity.getPhone());
        statement.setString(7, entity.getEmail());
        statement.setBoolean(8, entity.getIs_deleted());

        return statement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Company entity) throws SQLException {
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE " + getTableName() + " SET name = ?, vat = ?, city = ?, street = ?, postal = ?, phone = ?, email = ?, is_deleted = ? WHERE id = ?");

        statement.setString(1, entity.getName());
        statement.setString(2, entity.getVat());
        statement.setString(3, entity.getCity());
        statement.setString(4, entity.getStreet());
        statement.setString(5, entity.getPostal());
        statement.setString(6, entity.getPhone());
        statement.setString(7, entity.getEmail());
        statement.setBoolean(8, entity.getIs_deleted());
        statement.setInt(9, entity.getId());

        return statement;
    }

    @Override
    public void execTransactionCreateNewEmployee(Company newEmployee) throws SQLException {
    }

    @Override
    public void execTransactionUpdateEmployee(Company newEmployee) throws SQLException {
    }

    @Override
    public void execTransactionDeleteEmployee(Employee entity) throws SQLException {
    }

    @Override
    public void execTransactionCreateNewLocality(Company newEmployee) throws SQLException {

    }
}