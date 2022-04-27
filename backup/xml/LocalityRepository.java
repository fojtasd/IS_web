package com.is_web.repository.xml;

import com.is_web.entities.Employee;
import com.is_web.entities.Locality;
import com.is_web.repository.Repository;
import com.is_web.repository.RepositoryException;
import com.is_web.repository.WhereOperator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LocalityRepository implements Repository<Locality> {

    private final String tableName = "localities";

    @Override
    public Locality findById(int id) throws RepositoryException {
        // TODO not implemented
        Locality dummy = new Locality("TODO", "TODO", "TODO", "TODO", "TODO");
        dummy.setId(1);
        return dummy;
    }

    @Override
    public List<Locality> getAll() throws RepositoryException {
        // TODO not implemented
        List<Locality> dummyList = new ArrayList<>();
        Locality dummy = new Locality("TODO", "TODO", "TODO", "TODO", "TODO");
        dummy.setId(1);
        dummyList.add(dummy);
        return dummyList;
    }

    @Override
    public <S> List<Locality> where(String attributeName, S attributeValue, WhereOperator operator) throws RepositoryException {
        // TODO not implemented
        List<Locality> dummyList = new ArrayList<>();
        Locality dummy = new Locality("TODO", "TODO", "TODO", "TODO", "TODO");
        dummy.setId(1);
        dummyList.add(dummy);
        return dummyList;
    }

    @Override
    public <S> List<Locality> where(String attributeName, S attributeValue) throws RepositoryException {
        return where(attributeName, attributeValue, WhereOperator.Equals);
    }

    @Override
    public void delete(Locality entity) throws RepositoryException {
        // TODO not implemented
    }

    @Override
    public Locality saveOrUpdate(Locality entity) throws RepositoryException {
        // TODO not implemented
        return entity;
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

    @Override
    public void execTransactionCreateNewLocality(Locality newEmployee) throws SQLException {

    }
}