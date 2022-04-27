<%@ page import="com.is_web.entities.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.is_web.entities.Locality" %>
<%@ page import="com.is_web.repository.Repository" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<%--<div class="d-flex justify-content-between flex-wrap flex-md-nowrap align-items-center pt-3 pb-2 mb-3 border-bottom">--%>
<h1>Employees</h1>
<div class="container-fluid">
    <div>
        <%
            Employee employeeToEdit = (Employee) request.getAttribute("employeeToEdit");
        %>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">First name</th>
                <th scope="col">Last Name</th>
                <th scope="col">Email</th>
                <th scope="col">Phone</th>
                <th scope="col">City</th>
                <th scope="col">Street</th>
                <th scope="col">Postal</th>
                <th scope="col">Locality label</th>
                <th scope="col">Devices</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
            </tr>
            </thead>
            <tbody>
            <% for (Employee employee : (List<Employee>) request.getAttribute("employees")) { %>
            <%
                Repository<Locality> localityRepo = (Repository<Locality>) request.getAttribute("localityRepo");
                Locality locality = localityRepo.findById(employee.getLocalityId());
                boolean isSelected = employeeToEdit != null && employeeToEdit.getId().equals(employee.getId());
            %>
            <tr <%= (isSelected ? "class='table-success'" : "") %>>
                <th scope="row"><%= employee.getId() %>
                </th>
                <td><%= employee.getName() %>
                </td>
                <td><%= employee.getSurname() %>
                </td>
                <td><%= employee.getEmail() %>
                </td>
                <td><%= employee.getPhone() %>
                </td>
                <td><%= employee.getCity() %>
                </td>
                <td><%= employee.getStreet() %>
                </td>
                <td><%= employee.getPostal() %>
                </td>
                <td><%= locality.getLabel() %>
                </td>
                <td>
                    <form action="/is_foj0105_web_war/employees" method="POST">
                        <input name="RedirectToOwnedEmployee_device" type="submit" value="Devices" class="btn btn-info"/>
                        <input name="id" type="hidden" value="<%= employee.getId() %>"/>
                    </form>

                </td>
                <td>
                    <a class="btn btn-primary" href="/is_foj0105_web_war/employees?id=<%= employee.getId() %>">Edit</a>
                </td>
                <td>
                    <form action="/is_foj0105_web_war/employees" method="POST">
                        <input onclick="return confirm('Do you want to delete this item?');" name="deleteEmployee"
                               type="submit" value="Delete" class="btn btn-danger"/>
                        <input name="id" type="hidden" value="<%= employee.getId() %>"/>
                    </form>
                </td>
            </tr>
            <% } %>
            </tbody>
        </table>
    </div>
</div>

<div class="container-fluid">
    <div>
        <div class="row">
            <div class="col-6">
                <form method="POST"
                      action="/is_foj0105_web_war/employees">
                    <h2>Add new employee</h2>
                    <table>
                        <tr>
                            <td><label>Last name</label></td>
                            <td><input class="form-control" type="text" name="name"></td>
                        </tr>
                        <tr>
                            <td><label>First name</label></td>
                            <td><input class="form-control" type="text" name="surname"/></td>
                        </tr>
                        <tr>
                            <td><label>Email</label></td>
                            <td><input class="form-control" type="text" name="email"/></td>
                        </tr>
                        <tr>
                            <td><label>Phone</label></td>
                            <td><input class="form-control" type="text" name="phone"/></td>
                        </tr>
                        <tr>
                            <td><label>City</label></td>
                            <td><input class="form-control" type="text" name="city"/></td>
                        </tr>
                        <tr>
                            <td><label>Street</label></td>
                            <td><input class="form-control" type="text" name="street"/></td>
                        </tr>
                        <tr>
                            <td><label>Postal</label></td>
                            <td><input class="form-control" type="text" name="postal"/></td>
                        </tr>
                        <tr>
                            <td><label>Locality</label></td>
                            <td>
                                <select class="form-control" name="localityId">
                                    <% for (Locality locality : (List<Locality>) request.getAttribute("localities")) { %>
                                    <option value="<%= locality.getId() %>">
                                        <%= locality.getLabel() %>,
                                        <%= locality.getCity() %>,
                                        <%= locality.getStreet() %>,
                                        <%= locality.getPostal() %>
                                    </option>
                                    <% } %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><input name="addNewEmployee" type="submit" class="btn btn-success"
                                       value="Add employee"/></td>
                        </tr>
                    </table>
                </form>
            </div>

            <div class="col-6">
                <%
                    if (employeeToEdit != null) {
                %>

                <form method="POST"
                      action="/is_foj0105_web_war/employees">
                    <h2>Edit employee
                        #<%= employeeToEdit.getId() %> <%= employeeToEdit.getSurname() %> <%= employeeToEdit.getName() %>
                    </h2>
                    <table>
                        <tr>
                            <td><label>Last name</label></td>
                            <td><input class="form-control" type="text" name="surname"
                                       value="<%= employeeToEdit.getSurname() %>"/></td>
                        </tr>
                        <tr>
                            <td><label>First name</label></td>
                            <td><input class="form-control" type="text" name="name"
                                       value="<%= employeeToEdit.getName() %>"/></td>
                        </tr>
                        <tr>
                            <td><label>Email</label></td>
                            <td><input class="form-control" type="text" name="phone"
                                       value="<%= employeeToEdit.getPhone() %>"/></td>
                        </tr>
                        <tr>
                            <td><label>Phone</label></td>
                            <td><input class="form-control" type="text" name="email"
                                       value="<%= employeeToEdit.getEmail() %>"/></td>
                        </tr>
                        <tr>
                            <td><label>City</label></td>
                            <td><input class="form-control" type="text" name="city"
                                       value="<%= employeeToEdit.getCity() %>"/></td>
                        </tr>
                        <tr>
                            <td><label>Street</label></td>
                            <td><input class="form-control" type="text" name="street"
                                       value="<%= employeeToEdit.getStreet() %>"/></td>
                        </tr>
                        <tr>
                            <td><label>Postal</label></td>
                            <td><input class="form-control" type="text" name="postal"
                                       value="<%= employeeToEdit.getPostal() %>"/></td>
                        </tr>
                        <tr>
                            <td><label>Locality</label></td>
                            <td>
                                <select class="form-control" name="localityId">
                                    <% for (Locality locality : (List<Locality>) request.getAttribute("localities")) { %>
                                    <option value="<%= locality.getId() %>" <% if (locality.getId().equals(employeeToEdit.getLocalityId())) { %>
                                            selected <% } %>>
                                        <%= locality.getLabel() %>,
                                        <%= locality.getCity() %>,
                                        <%= locality.getStreet() %>,
                                        <%= locality.getPostal() %>
                                    </option>
                                    <% } %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td><input name="editEmployee" type="submit" class="btn btn-success" value="Save changes"/>
                                <input name="id" type="hidden" value="<%= employeeToEdit.getId() %>"/></td>
                        </tr>
                    </table>
                </form>
                <% } %>
            </div>
        </div>
    </div>
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