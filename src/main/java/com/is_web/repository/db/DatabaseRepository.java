package com.is_web.repository.db;

import com.is_web.entities.Entity;
import com.is_web.repository.Repository;
import com.is_web.repository.RepositoryException;
import com.is_web.repository.WhereOperator;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Partial implementation of Repository. Provides common behaviour for database repositories.
 *
 * @param <T> Entity type
 */
public abstract class DatabaseRepository<T extends Entity> implements Repository<T> {

    protected <S> Object adjustWhereValue(WhereOperator operator, S attributeValue) {
        if (operator == WhereOperator.Includes) {
            return "%" + attributeValue + "%";
        }
        return attributeValue;
    }

    protected String createSqlOperator(WhereOperator operator) throws RepositoryException {
        switch (operator) {
            case Equals:
                return "=";
            case GreaterOrEqual:
                return ">=";
            case Greater:
                return ">";
            case LessOrEqual:
                return "<=";
            case Less:
                return "<";
            case Includes:
                return "LIKE";
        }
        throw new RepositoryException(new Exception(), "Unknown where operator");
    }

    protected abstract T createEntity(ResultSet resultSet) throws SQLException;

    protected abstract String getTableName();

    @Override
    public T findById(int id) throws RepositoryException {
        try {
            java.sql.Connection connection = Connection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + getTableName() + " WHERE id = ?");
            statement.setInt(1, id);

            ResultSet rs = statement.executeQuery();
            rs.next();

            return createEntity(rs);
        } catch (Exception e) {
            throw new RepositoryException(e, "Couldn't find entity: " + e.getMessage());
        }
    }

    @Override
    public List<T> getAll() throws RepositoryException {
        try {
            List<T> entities = new ArrayList<>();

            java.sql.Connection connection = Connection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM " + getTableName() + " WHERE is_deleted = 'false'");
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                entities.add(createEntity(rs));
            }

            return entities;
        } catch (Exception e) {
            throw new RepositoryException(e, "Couldn't get all: " + e.getMessage());
        }
    }

    @Override
    public <S> List<T> where(String attributeName, S attributeValue) throws RepositoryException {
        return where(attributeName, attributeValue, WhereOperator.Equals);
    }

    @Override
    public <S> List<T> where(String attributeName, S attributeValue, WhereOperator operator) throws RepositoryException {
        try {
            List<T> entities = new ArrayList<>();

            java.sql.Connection connection = Connection.getInstance().getConnection();
            String textOperator = createSqlOperator(operator);

            PreparedStatement statement = connection.prepareStatement(
                    "SELECT * FROM " + getTableName() + " WHERE " + attributeName + " " + textOperator + " ? AND is_deleted = 0");

            Object adjustedAttributeValue = adjustWhereValue(operator, attributeValue);
            statement.setObject(1, adjustedAttributeValue);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                entities.add(createEntity(rs));
            }

            return entities;
        } catch (Exception e) {
            throw new RepositoryException(e, "Couldn't find entity: " + e.getMessage());
        }
    }

    @Override
    public void delete(T entity) throws RepositoryException {
        try {
            java.sql.Connection connection = Connection.getInstance().getConnection();
            PreparedStatement statement = connection.prepareStatement("UPDATE " + getTableName() + " SET is_deleted = 1 WHERE id = ?");
            statement.setInt(1, entity.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            throw new RepositoryException(e, "Couldn't delete entity: " + e.getMessage());
        }
    }

    @Override
    public T saveOrUpdate(T entity) throws RepositoryException {
        try {
            Connection dbConnection = Connection.getInstance();
            java.sql.Connection connection = dbConnection.getConnection();

            if (entity.getId() == null) {
                PreparedStatement statement = createInsertStatement(entity);
                int affectedRows = statement.executeUpdate();

                if (affectedRows == 0) {
                    throw new SQLException("Creating entity failed, no rows affected.");
                }

                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        entity.setId(generatedKeys.getInt(1));
                    } else {
                        throw new SQLException("Creating entity failed, no ID obtained.");
                    }
                }
            } else {
                PreparedStatement statement = createUpdateStatement(entity);
                statement.executeUpdate();
            }
            return entity;
        } catch (Exception e) {
            throw new RepositoryException(e, "Couldn't save entity: " + e.getMessage());
        }
    }

    protected abstract PreparedStatement createInsertStatement(T entity) throws SQLException;

    protected abstract PreparedStatement createUpdateStatement(T entity) throws SQLException;
}
