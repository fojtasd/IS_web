package com.is_web.repository.db;

import com.is_web.entities.Employee;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EmployeeRepository extends DatabaseRepository<Employee> {

    @Override
    protected Employee createEntity(ResultSet resultSet) throws SQLException {
        Employee entity = new Employee();

        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String surname = resultSet.getString("surname");
        String city = resultSet.getString("city");
        String street = resultSet.getString("street");
        String postal = resultSet.getString("postal");
        String email = resultSet.getString("email");
        String phone = resultSet.getString("phone");
        Integer locality_id = resultSet.getInt("locality_id");
        boolean is_deleted = resultSet.getBoolean("is_deleted");

        entity.setId(id);
        entity.setSurname(surname);
        entity.setName(name);
        entity.setCity(city);
        entity.setStreet(street);
        entity.setEmail(email);
        entity.setPhone(phone);
        entity.setPostal(postal);
        entity.setLocalityId(locality_id);
        entity.setIs_deleted(is_deleted);

        return entity;
    }

    @Override
    protected String getTableName() {
        return "dbo.employees";
    }

    @Override
    protected PreparedStatement createInsertStatement(Employee entity) throws SQLException {
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO " + getTableName() + " (name, surname, city, street, postal, phone, email, locality_id, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getSurname());
        statement.setString(3, entity.getCity());
        statement.setString(4, entity.getStreet());
        statement.setString(5, entity.getPostal());
        statement.setString(6, entity.getPhone());
        statement.setString(7, entity.getEmail());
        statement.setInt(8, entity.getLocalityId());
        statement.setBoolean(9, entity.getIsDeleted());

        return statement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Employee entity) throws SQLException {
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE " + getTableName() + " SET name = ?, surname = ?, city = ?, street = ?, postal = ?, phone = ?, email = ?, localityId = ?, is_deleted = ? WHERE id = ?");

        statement.setString(1, entity.getName());
        statement.setString(2, entity.getSurname());
        statement.setString(3, entity.getCity());
        statement.setString(4, entity.getStreet());
        statement.setString(5, entity.getPostal());
        statement.setString(6, entity.getPhone());
        statement.setString(7, entity.getEmail());
        statement.setInt(8, entity.getLocalityId());
        statement.setBoolean(9, entity.getIsDeleted());

        return statement;
    }

    public void execTransactionCreateNewEmployee(Employee entity) throws SQLException{
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("EXECUTE dbo.create_employee @v_name = ?, @v_surname = ?, @v_city = ?, @v_street = ?, @v_postal = ?, @v_email = ?, @v_phone = ?, @v_locality_id = ?, @v_product_id = ?;");
        statement.setString(1, entity.getName());
        statement.setString(2, entity.getSurname());
        statement.setString(3, entity.getCity());
        statement.setString(4, entity.getStreet());
        statement.setString(5, entity.getPostal());
        statement.setString(6, entity.getEmail());
        statement.setString(7, entity.getPhone());
        statement.setInt(8, entity.getLocalityId());
        statement.setInt(9, 1);

        statement.execute();
        statement.close();
    }

    public void execTransactionUpdateEmployee(Employee entity) throws SQLException{
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("EXECUTE dbo.update_employee @v_id = ?, @v_name = ?, @v_surname = ?, @v_city = ?, @v_street = ?, @v_postal = ?, @v_email = ?, @v_phone = ?, @v_locality_id = ?;");
        statement.setInt(1, entity.getId());
        statement.setString(2, entity.getName());
        statement.setString(3, entity.getSurname());
        statement.setString(4, entity.getCity());
        statement.setString(5, entity.getStreet());
        statement.setString(6, entity.getPostal());
        statement.setString(7, entity.getEmail());
        statement.setString(8, entity.getPhone());
        statement.setInt(9, entity.getLocalityId());

        statement.execute();
        statement.close();
    }

    public void execTransactionDeleteEmployee(Employee entity) throws SQLException{
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement("EXECUTE dbo.delete_employee @v_id = " + entity.getId() + ";");
        statement.execute();
        statement.close();
    }

    @Override
    public void execTransactionCreateNewLocality(Employee newEmployee) throws SQLException {

    }
}