package com.is_web.repository.db;

import com.is_web.entities.Employee;
import com.is_web.entities.Reclamation;

import java.sql.*;
import java.time.LocalDate;

public class ReclamationRepository extends DatabaseRepository<Reclamation> {

    @Override
    protected Reclamation createEntity(ResultSet resultSet) throws SQLException {
        Reclamation entity = new Reclamation();

        int id = resultSet.getInt("id");
        String serial_number = resultSet.getString("serial_number");
        Boolean approved = resultSet.getBoolean("approved");
        LocalDate start_date = resultSet.getDate("start_date").toLocalDate();
        Date end_date = resultSet.getDate("end_date");
        String description = resultSet.getString("description");
        int products_id = resultSet.getInt("products_id");
        int companies_id = resultSet.getInt("companies_id");
        Boolean is_deleted = resultSet.getBoolean("is_deleted");
        LocalDate endDateTemp;

        if (end_date != null){
            endDateTemp = resultSet.getDate("end_date").toLocalDate();
        }
        else{
            endDateTemp = null;
        }
        entity.setId(id);
        entity.setSerial_number(serial_number);
        entity.setApproved(approved);
        entity.setStart_date(start_date);
        entity.setEnd_date(endDateTemp);
        entity.setDescription(description);
        entity.setProducts_id(products_id);
        entity.setCompanies_id(companies_id);
        entity.setIs_deleted(is_deleted);

        return entity;
    }

    @Override
    protected String getTableName() {
        return "dbo.reclamations";
    }

    @Override
    protected PreparedStatement createInsertStatement(Reclamation entity) throws SQLException {
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO " + getTableName() + " (serial_number, approved, start_date, end_date, description, products_id, companies_id, is_deleted) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getSerial_number());
        statement.setBoolean(2, entity.getApproved());

        Date sqlStartDate = Date.valueOf(entity.getStart_date());
        statement.setDate(3, sqlStartDate);


        if(entity.getEnd_date() == null){
            statement.setDate(4, null);
        }
        else{
            Date sqlEndDate = Date.valueOf(entity.getEnd_date());
            statement.setDate(4, sqlEndDate);
        }
        statement.setString(5, entity.getDescription());
        statement.setInt(6, entity.getProducts_id());
        statement.setInt(7, entity.getCompanies_id());
        statement.setBoolean(8, entity.getIs_deleted());

        return statement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(Reclamation entity) throws SQLException {
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE " + getTableName() + " SET serial_number = ?, approved = ?, start_date = ?, end_date = ?, description = ?, products_id = ?, companies_id = ?, is_deleted = ? WHERE id = ?");

        statement.setString(1, entity.getSerial_number());
        statement.setBoolean(2, entity.getApproved());
        statement.setDate(3, java.sql.Date.valueOf(entity.getStart_date()));


        if(entity.getEnd_date() == null){
            statement.setDate(4, null);
        }
        else{
            Date sqlEndDate = Date.valueOf(entity.getEnd_date());
            statement.setDate(4, sqlEndDate);
        }

        statement.setString(5, entity.getDescription());
        statement.setInt(6, entity.getProducts_id());
        statement.setInt(7, entity.getCompanies_id());
        statement.setBoolean(8, entity.getIs_deleted());
        statement.setInt(9, entity.getId());

        return statement;
    }

    @Override
    public void execTransactionCreateNewEmployee(Reclamation newEmployee) throws SQLException {

    }

    @Override
    public void execTransactionUpdateEmployee(Reclamation newEmployee) throws SQLException {

    }

    @Override
    public void execTransactionDeleteEmployee(Employee entity) throws SQLException {

    }

    @Override
    public void execTransactionCreateNewLocality(Reclamation newEmployee) throws SQLException {

    }
}