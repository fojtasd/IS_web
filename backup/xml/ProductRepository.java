package com.is_web.repository.xml;

import com.is_web.entities.Employee;
import com.is_web.entities.Product;
import com.is_web.repository.Repository;
import com.is_web.repository.RepositoryException;
import com.is_web.repository.WhereOperator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements Repository<Product> {

    private final String tableName = "products";

    @Override
    public Product findById(int id) throws RepositoryException {
        // TODO not implemented
        Product dummy = new Product(0, 0, 0, 0, 0);
        dummy.setId(1);
        return dummy;
    }

    @Override
    public List<Product> getAll() throws RepositoryException {
        // TODO not implemented
        List<Product> dummyList = new ArrayList<>();
        Product dummy = new Product(0, 0, 0, 0, 0);
        dummy.setId(1);
        dummyList.add(dummy);
        return dummyList;
    }

    @Override
    public <S> List<Product> where(String attributeName, S attributeValue, WhereOperator operator) throws RepositoryException {
        // TODO not implemented
        List<Product> dummyList = new ArrayList<>();
        Product dummy = new Product(0, 0, 0, 0, 0);
        dummy.setId(1);
        dummyList.add(dummy);
        return dummyList;
    }

    @Override
    public <S> List<Product> where(String attributeName, S attributeValue) throws RepositoryException {
        return where(attributeName, attributeValue, WhereOperator.Equals);
    }

    @Override
    public void delete(Product entity) throws RepositoryException {
        // TODO not implemented
    }

    @Override
    public Product saveOrUpdate(Product entity) throws RepositoryException {
        // TODO not implemented
        return entity;
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