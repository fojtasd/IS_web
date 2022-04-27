package com.is_web.repository;

import com.is_web.entities.Employee;
import com.is_web.entities.Entity;

import java.sql.SQLException;
import java.util.List;

public interface Repository<T extends Entity> {
    T findById(int id) throws RepositoryException;

    List<T> getAll() throws RepositoryException;

    <S> List<T> where(String attributeName, S attributeValue, WhereOperator operator) throws RepositoryException;
    <S> List<T> where(String attributeName, S attributeValue) throws RepositoryException;

    void delete(T entity) throws RepositoryException;
    T saveOrUpdate(T entity) throws RepositoryException;

    void execTransactionCreateNewEmployee(T newEmployee) throws SQLException;
    void execTransactionUpdateEmployee(T newEmployee) throws SQLException;
    void execTransactionDeleteEmployee(Employee entity) throws SQLException;

    void execTransactionCreateNewLocality(T newEmployee) throws SQLException;
}
