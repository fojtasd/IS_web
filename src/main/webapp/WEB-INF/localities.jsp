<%@ page import="java.util.List" %>
<%@ page import="com.is_web.entities.Locality" %>
<%@ page import="com.is_web.entities.Employee" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<h1>Localities</h1>
<div class="container-fluid">
    <div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Office</th>
                <th scope="col">City</th>
                <th scope="col">Street</th>
                <th scope="col">Postal</th>
                <th scope="col">Phone office</th>
                <th scope="col">Employees count</th>
                <th scope="col">Employees of the locality</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <% for (Locality locality : (List<Locality>) request.getAttribute("localities")) {
                int employeeCount = 0;
                %>
                <th scope="row"><%= locality.getId() %></th>
                <td><%= locality.getLabel() %></td>
                <td><%= locality.getCity() %></td>
                <td><%= locality.getStreet() %></td>
                <td><%= locality.getPostal() %></td>
                <td><%= locality.getPhone_office() %></td>
                    <% for (Employee employee : (List<Employee>) request.getAttribute("allEmployees"))
                    {
                        if (locality.getId() == employee.getLocalityId()){
                            employeeCount = employeeCount + 1;
                        }
                    }
                    %>

                <td><%= employeeCount %></td>
                <td>
                    <form action="/is_foj0105_web_war/localities" method="POST">
                        <input name="employees" type="submit" value="Employees" class="btn btn-info"/>
                        <input name="id" type="hidden" value="<%= locality.getId() %>"/>
                    </form>
                </td>
                <td>
                    <a class="btn btn-primary" href="/is_foj0105_web_war/localities?id=<%= locality.getId() %>">Edit</a>
                </td>
                <td>
                    <form action="/is_foj0105_web_war/localities" method="POST">
                        <input onclick="return confirm('Do you want to delete this item?');" name="deleteLocality" type="submit" class="btn btn-danger" value="Delete"/>
                        <input name="id" type="hidden" value="<%= locality.getId() %>"/>
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
                      action="/is_foj0105_web_war/localities">
                    <h2>
                        Add new locality
                    </h2>
                    <table>
                        <tr>
                            <td><label>Office</label></td>
                            <td><input class="form-control" type="text" name="label"/></td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    City
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="city"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Street
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="street"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Postal
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="postal"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Phone office
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="phone_office"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <input name="addNewLocality" type="submit" class="btn btn-success" value="Add"/>
                            </td>
                        </tr>

                    </table>
                </form>
            </div>
            <div class="col-6">
                <%
                    Locality localityToEdit = (Locality) request.getAttribute("localityToEdit");
                    if (localityToEdit != null) {
                %>
                <form method="POST"
                      action="/is_foj0105_web_war/localities">
                    <h2>
                        Edit locality
                    </h2>

                    <table>

                        <tr>
                            <td>
                                <label>
                                    Office
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="office" value="<%=localityToEdit.getLabel()%>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    City
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="city" value="<%=localityToEdit.getCity()%>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Street
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="street" value="<%=localityToEdit.getStreet()%>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Postal
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="postal" value="<%=localityToEdit.getPostal()%>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Phone office
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="phone_office" value="<%=localityToEdit.getPhone_office()%>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <input name="editLocality" type="submit" class="btn btn-success" value="Save changes"/>
                            </td>
                                <input name="id" type="hidden" value="<%= localityToEdit.getId() %>"/>
                            </td>
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