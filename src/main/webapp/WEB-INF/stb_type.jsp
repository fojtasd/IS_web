<%@ page import="java.util.List" %>
<%@ page import="com.is_web.entities.Locality" %>
<%@ page import="com.is_web.entities.Employee" %>
<%@ page import="com.is_web.entities.Company" %>
<%@ page import="com.is_web.entities.StbType" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<h1>Set-top box types</h1>
<div class="container-fluid">
    <div>
        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Name</th>
                <th scope="col">RAM</th>
                <th scope="col">WI-FI module</th>
                <th scope="col">CPU cores</th>
                <th scope="col">CPU frequency</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <% for (StbType stbType : (List<StbType>) request.getAttribute("allStbTypes")) {

                %>
                <th scope="row"><%= stbType.getId() %></th>
                <td><%= stbType.getName() %></td>
                <td><%= stbType.getRam() %></td>
                <td><%= stbType.getWifi_module() %></td>
                <td><%= stbType.getCpu_cores() %></td>
                <td><%= stbType.getCpu_frequency() %></td>
                <td>
                    <a class="btn btn-primary" href="/is_foj0105_web_war/stb_type?id=<%= stbType.getId() %>">
                        Edit
                    </a>
                </td>
                <td>
                    <form action="/is_foj0105_web_war/stb_type" method="POST">
                        <input onclick="return confirm('Do you want to delete this item?');" name="deleteStbType" type="submit" class="btn btn-danger" value="Delete"/>
                        <input name="id" type="hidden" value="<%= stbType.getId() %>"/>
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
                      action="/is_foj0105_web_war/stb_type">
                    <h2>
                        Add new set-top box type
                    </h2>
                    <table>
                        <tr>
                            <td>
                                <label>
                                    Name
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="setName"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    RAM
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="setRam"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    WI-FI module
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="setWifi"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    CPU cores
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="setCpuC"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    CPU frequency
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="setCpuF"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <input name="addNewStbType" type="submit" class="btn btn-success" value="Add"/>
                            </td>
                        </tr>

                    </table>
                </form>
            </div>
            <div class="col-6">
                <%
                    StbType stbTypeToEdit = (StbType) request.getAttribute("stbTypeToEdit");
                    if (stbTypeToEdit != null) {
                %>
                <form method="POST"
                      action="/is_foj0105_web_war/stb_type">
                    <h2>
                        Edit Set-top box type
                    </h2>

                    <table>

                        <tr>
                            <td>
                                <label>
                                    Name
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="changeName" value="<%=stbTypeToEdit.getName()%>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    RAM
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="changeRam" value="<%=stbTypeToEdit.getRam()%>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    WI-FI module
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="changeWifi" value="<%=stbTypeToEdit.getWifi_module()%>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    CPU cores
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="changeCpuCores" value="<%=stbTypeToEdit.getCpu_cores()%>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    CPU frequency
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="changeCpuFrequency" value="<%=stbTypeToEdit.getCpu_frequency()%>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <input name="editStbType" type="submit" class="btn btn-success" value="Save changes"/>
                            </td>
                            <td>
                                <input name="id" type="hidden" value="<%= stbTypeToEdit.getId() %>"/>
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