package com.is_web.repository.db;

import com.is_web.entities.Employee;
import com.is_web.entities.StbType;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StbTypeRepository extends DatabaseRepository<StbType> {

    @Override
    protected StbType createEntity(ResultSet resultSet) throws SQLException {
        StbType entity = new StbType();

        if(resultSet != null){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            int ram = resultSet.getInt("ram");
            String wifi_module = resultSet.getString("wifi_module");
            int cpu_cores = resultSet.getInt("cpu_cores");
            int cpu_frequency = resultSet.getInt("cpu_frequency");
            boolean is_deleted = resultSet.getBoolean("is_deleted");
            entity.setId(id);
            entity.setName(name);
            entity.setRam(ram);
            entity.setWifi_module(wifi_module);
            entity.setCpu_cores(cpu_cores);
            entity.setCpu_frequency(cpu_frequency);
            entity.setIs_deleted(is_deleted);
            return entity;
        }
        else{
            return null;
        }
    }

    @Override
    protected String getTableName() {
        return "dbo.stb_type";
    }

    @Override
    protected PreparedStatement createInsertStatement(StbType entity) throws SQLException {
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO " + getTableName() + " (name, ram, wifi_module, cpu_cores, cpu_frequency, is_deleted) VALUES (?, ?, ?, ?, ?, ?)",
                Statement.RETURN_GENERATED_KEYS);
        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getRam());
        statement.setString(3, entity.getWifi_module());
        statement.setInt(4, entity.getCpu_cores());
        statement.setInt(5, entity.getCpu_frequency());
        statement.setBoolean(6, entity.getIs_deleted());

        return statement;
    }

    @Override
    protected PreparedStatement createUpdateStatement(StbType entity) throws SQLException {
        java.sql.Connection connection = Connection.getInstance().getConnection();
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE " + getTableName() + " SET name = ?, ram = ?, wifi_module = ?, cpu_cores = ?, cpu_frequency = ?,  is_deleted = ? WHERE id = ?");

        statement.setString(1, entity.getName());
        statement.setInt(2, entity.getRam());
        statement.setString(3, entity.getWifi_module());
        statement.setInt(4, entity.getCpu_cores());
        statement.setInt(5, entity.getCpu_frequency());
        statement.setBoolean(6, entity.getIs_deleted());
        statement.setInt(7, entity.getId());

        return statement;
    }

    @Override
    public void execTransactionCreateNewEmployee(StbType newEmployee) throws SQLException {

    }

    @Override
    public void execTransactionUpdateEmployee(StbType newEmployee) throws SQLException {

    }

    @Override
    public void execTransactionDeleteEmployee(Employee entity) throws SQLException {

    }

    @Override
    public void execTransactionCreateNewLocality(StbType newEmployee) throws SQLException {

    }
}