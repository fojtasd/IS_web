<%@ page import="com.is_web.entities.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.is_web.entities.Locality" %>
<%@ page import="com.is_web.entities.Product" %>
<%@ page import="com.is_web.repository.Repository" %>
<%@ page import="com.is_web.entities.StbType" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<%--<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">--%>

<h1>Assigned devices to the employee with id #<%= session.getAttribute("employeeIdDevice") %></h1>
<div class="container-fluid">
    <div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Serial number</th>
                <th scope="col">Stb type</th>
                <th scope="col">Invoice sold id</th>
                <th scope="col">Invoice bought id</th>
            </tr>
            </thead>
            <tbody>
            <% for (Product product : (List<Product>) request.getAttribute("employeesDevices")) { %>
            <%
                Repository<StbType> stbTypeRepository = (Repository<StbType>) request.getAttribute("stbTypeRepo");
                boolean isSelected = false;
            %>
            <tr <%= (isSelected ? "class='table-success'" : "") %>>
                <th scope="row">
                    <%= product.getId() %>
                </th>
                <td>
                    <%= product.getSerial_number() %>
                </td>
                <td>
                    <%= stbTypeRepository.findById(product.getStb_type_id()).getName() %>
                </td>
                <td>
                    <%= product.getInvoice_sold_id() %>
                </td>
                <td>
                    <%= product.getInvoice_bought_id() %>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>



<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"
        integrity="sha384-q2kxQ16AaE6UbzuKqyBE9/u/KzioAlnx2maXQHiDX9d4/zp8Ok3f+M7DPm+Ib6IU"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.min.js"
        integrity="sha384-pQQkAEnwaBkjpqZ8RU1fF1AKtTcHJwFl3pblpTlHXybJjHpMYo79HY3hIi4NKxyj"
        crossorigin="anonymous"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>