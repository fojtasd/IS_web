package com.is_web.repository.db;

import com.is_web.entities.Employee;
import com.is_web.entities.Locality;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LocalityRepository extends DatabaseRepository<Locality> {

    @Override
    protected Locality createEntity(ResultSet resultSet) throws SQLException {
        Locality entity = new Locality();

        int id = resultSet.getInt("id");
        String label = resultSet.getString("label");
        String city = resultSet.getString("city");
        String street = resultSet.getString("street");
        String postal = resultSet.getString("postal");
        String phone_office = resultSet.getString("phone_office");
        int employees_count = resultSet.getInt("employees_count");
        boolean is_deleted = resultSet.getBoolean("is_deleted");

        entity.setId(id);
        entity.setLabel(label);
        entity.setCity(city);
        entity.setStreet(street);
        entity.setPostal(postal);
        entity.setPhone_office(phone_office);
        entity.setEmployees_count(employees_count);
        entity.setIs_deleted(is_deleted);

        return entity;
    }

    @Override
    protected String getTableName() {
        return "dbo.locality";
    }

    @Override
    protected PreparedStatement createInsertStatement(Locality entity) throws SQLException {
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO " + getTableName() + " (label, city, street, postal, phone_office, employees_count, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getLabel());
        statement.setString(2, entity.getCity());
        statement.setString(3, entity.getStreet());
        statement.setString(4, entity.getPostal());
        statement.setString(5, entity.getPhone_office());
        statement.setInt(6, entity.getEmployees_count());
        statement.setBoolean(7, entity.getIs_deleted());

        return statement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Locality entity) throws SQLException {
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE " + getTableName() + " SET label = ?, city = ?, street = ?, postal = ?, phone_office = ?, employees_count = ?, is_deleted = ? WHERE id = ?");

        statement.setString(1, entity.getLabel());
        statement.setString(2, entity.getCity());
        statement.setString(3, entity.getStreet());
        statement.setString(4, entity.getPostal());
        statement.setString(5, entity.getPhone_office());
        statement.setInt(6, entity.getEmployees_count());
        statement.setBoolean(7, entity.getIs_deleted());
        statement.setInt(8, entity.getId());

        return statement;
    }

    public void execTransactionCreateNewLocality(Locality entity) throws SQLException{
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("EXECUTE dbo.create_locality @v_label = ?, @v_city = ?, @v_street = ?, @v_postal = ?, @v_phone_office = ?;");
        statement.setString(1, entity.getLabel());
        statement.setString(2, entity.getCity());
        statement.setString(3, entity.getStreet());
        statement.setString(4, entity.getPostal());
        statement.setString(5, entity.getPhone_office());

        statement.execute();
        statement.close();
    }
    @Override
    public void execTransactionCreateNewEmployee(Locality newEmployee) throws SQLException {
    }

    @Override
    public void execTransactionUpdateEmployee(Locality newEmployee) throws SQLException {
    }

    @Override
    public void execTransactionDeleteEmployee(Employee entity) throws SQLException {
    }
}