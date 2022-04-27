package com.is_web.repository.xml;

import com.is_web.entities.Employee;
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

public class EmployeeRepository implements Repository<Employee> {

    private final String tableName = "employees";

    @Override
    public Employee findById(int id) throws RepositoryException {
        Element doc = Connection.getDocument(tableName).getDocumentElement();
        NodeList employeeNodes = doc.getChildNodes();

        for (int i = 0; i < employeeNodes.getLength(); i++) {
            Node employeeNode = employeeNodes.item(i);
            if (employeeNode.getNodeName().equals("employee")) {
                Employee employee = createEntity(employeeNode);
                if (employee.getId() == id) {
                    return employee;
                }
            }
        }

        return null;
    }

    @Override
    public List<Employee> getAll() throws RepositoryException {
        List<Employee> employees = new ArrayList<>();

        Element doc = Connection.getDocument(tableName).getDocumentElement();
        NodeList employeeNodes = doc.getChildNodes();

        for (int i = 0; i < employeeNodes.getLength(); i++) {
            Node employeeNode = employeeNodes.item(i);
            if (employeeNode.getNodeName().equals("employee")) {
                Employee employee = createEntity(employeeNode);
                employees.add(employee);
            }
        }

        return employees;
    }

    @Override
    public <S> List<Employee> where(String attributeName, S attributeValue, WhereOperator operator) throws RepositoryException {
        List<Employee> employees = new ArrayList<>();

        Element doc = Connection.getDocument(tableName).getDocumentElement();
        NodeList employeeNodes = doc.getChildNodes();

        for (int i = 0; i < employeeNodes.getLength(); i++) {
            Node employeeNode = employeeNodes.item(i);
            if (employeeNode.getNodeName().equals("employee")) {

                NodeList employeeAttributes = employeeNode.getChildNodes();
                Employee employee = new Employee();

                boolean isFiltered = true;

                for (int j = 0; j < employeeAttributes.getLength(); j++) {
                    String xmlAttributeName = employeeAttributes.item(j).getNodeName();
                    String xmlAttributeValue = employeeAttributes.item(j).getTextContent();

                    if (attributeName.equals(xmlAttributeName)) {
                        switch (operator) {
                            case Equals:
                                isFiltered = !xmlAttributeValue.equals(attributeValue.toString());
                                break;
                            case GreaterOrEqual:
                                // TODO not implemented
                                break;
                            case Greater:
                                // TODO not implemented
                                break;
                            case LessOrEqual:
                                // TODO not implemented
                                break;
                            case Less:
                                // TODO not implemented
                                break;
                            case Includes:
                                isFiltered = !xmlAttributeValue.contains(attributeValue.toString());
                                break;
                        }
                    }

                    switch (xmlAttributeName) {
                        case "id":
                            employee.setId(Integer.parseInt(xmlAttributeValue));
                            break;
                        case "lname":
                            employee.setSurname(xmlAttributeValue);
                            break;
                        case "fname":
                            employee.setName(xmlAttributeValue);
                            break;
                        case "city":
                            employee.setCity(xmlAttributeValue);
                            break;
                        case "street":
                            employee.setStreet(xmlAttributeValue);
                            break;
                        case "postal":
                            employee.setPostal(xmlAttributeValue);
                            break;
                        case "localityId":
                            employee.setLocalityId(Integer.parseInt(xmlAttributeValue));
                            break;
                    }
                }

                if (!isFiltered) {
                    employees.add(employee);
                }
            }
        }

        return employees;
    }

    @Override
    public <S> List<Employee> where(String attributeName, S attributeValue) throws RepositoryException {
        return where(attributeName, attributeValue, WhereOperator.Equals);
    }

    private Employee createEntity(Node employeeNode) {
        NodeList employeeAttributes = employeeNode.getChildNodes();
        Employee employee = new Employee();

        for (int j = 0; j < employeeAttributes.getLength(); j++) {
            String attributeName = employeeAttributes.item(j).getNodeName();
            String attributeValue = employeeAttributes.item(j).getTextContent();
            switch (attributeName) {
                case "id":
                    employee.setId(Integer.parseInt(attributeValue));
                    break;
                case "lname":
                    employee.setSurname(attributeValue);
                    break;
                case "fname":
                    employee.setName(attributeValue);
                    break;
                case "city":
                    employee.setCity(attributeValue);
                    break;
                case "street":
                    employee.setStreet(attributeValue);
                    break;
                case "postal":
                    employee.setPostal(attributeValue);
                    break;
                case "localityId":
                    employee.setLocalityId(Integer.parseInt(attributeValue));
                    break;
            }
        }

        return employee;
    }

    @Override
    public void delete(Employee entity) throws RepositoryException {
        Document document = Connection.getDocument(tableName);
        Element doc = document.getDocumentElement();
        NodeList employeeNodes = doc.getChildNodes();

        for (int i = 0; i < employeeNodes.getLength(); i++) {
            Node employeeNode = employeeNodes.item(i);
            if (employeeNode.getNodeName().equals("employee")) {
                Employee employee = createEntity(employeeNode);
                if (employee.getId().equals(entity.getId())) {
                    doc.removeChild(employeeNode);
                    break;
                }
            }
        }
        Connection.saveDocument(tableName, document);
    }

    @Override
    public Employee saveOrUpdate(Employee entity) throws RepositoryException {
        Document document = Connection.getDocument(tableName);
        Element doc = document.getDocumentElement();
        NodeList employeeNodes = doc.getChildNodes();

        Node foundEmployeeNode = null;

        for (int i = 0; i < employeeNodes.getLength(); i++) {
            Node employeeNode = employeeNodes.item(i);
            if (employeeNode.getNodeName().equals("employee")) {
                Employee employee = createEntity(employeeNode);
                if (employee.getId().equals(entity.getId())) {
                    foundEmployeeNode = employeeNode;
                    break;
                }
            }
        }

        if (foundEmployeeNode != null) {
            NodeList employeeAttributes = foundEmployeeNode.getChildNodes();

            for (int j = 0; j < employeeAttributes.getLength(); j++) {
                Node employeeAttributeNode = employeeAttributes.item(j);

                switch (employeeAttributeNode.getNodeName()) {
                    case "id":
                        employeeAttributeNode.setTextContent(entity.getId().toString());
                        break;
                    case "lname":
                        employeeAttributeNode.setTextContent(entity.getSurname());
                        break;
                    case "fname":
                        employeeAttributeNode.setTextContent(entity.getName());
                        break;
                    case "city":
                        employeeAttributeNode.setTextContent(entity.getCity());
                        break;
                    case "street":
                        employeeAttributeNode.setTextContent(entity.getStreet());
                        break;
                    case "postal":
                        employeeAttributeNode.setTextContent(entity.getPostal());
                        break;
                    case "localityId":
                        employeeAttributeNode.setTextContent(String.valueOf(entity.getLocalityId()));
                        break;
                }
            }
        } else {
            Element newEmployeeNode = document.createElement("employee");

            Element attributeNode = document.createElement("id");
            int id = findLastId() + 1;
            entity.setId(id);
            attributeNode.setTextContent(String.valueOf(id));
            newEmployeeNode.appendChild(attributeNode);

            attributeNode = document.createElement("lname");
            attributeNode.setTextContent(entity.getSurname());
            newEmployeeNode.appendChild(attributeNode);

            attributeNode = document.createElement("fname");
            attributeNode.setTextContent(entity.getName());
            newEmployeeNode.appendChild(attributeNode);

            attributeNode = document.createElement("city");
            attributeNode.setTextContent(entity.getCity());
            newEmployeeNode.appendChild(attributeNode);

            attributeNode = document.createElement("street");
            attributeNode.setTextContent(entity.getStreet());
            newEmployeeNode.appendChild(attributeNode);

            attributeNode = document.createElement("postal");
            attributeNode.setTextContent(entity.getPostal());
            newEmployeeNode.appendChild(attributeNode);

            attributeNode = document.createElement("localityId");
            attributeNode.setTextContent(String.valueOf(entity.getLocalityId()));
            newEmployeeNode.appendChild(attributeNode);

            doc.appendChild(newEmployeeNode);
        }

        Connection.saveDocument(tableName, document);
        return entity;
    }

    @Override
    public void execTransactionCreateNewEmployee(Employee newEmployee) throws SQLException {

    }

    @Override
    public void execTransactionUpdateEmployee(Employee newEmployee) throws SQLException {

    }

    @Override
    public void execTransactionDeleteEmployee(Employee entity) throws SQLException {

    }

    @Override
    public void execTransactionCreateNewLocality(Employee newEmployee) throws SQLException {

    }

    private int findLastId() throws RepositoryException {
        int lastId = 1;

        for (Employee employee : getAll()) {
            int employeeId = employee.getId();
            if (employeeId > lastId) {
                lastId = employeeId;
            }
        }

        return lastId;
    }
}