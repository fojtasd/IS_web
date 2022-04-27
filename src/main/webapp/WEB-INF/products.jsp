<%@ page import="java.util.List" %>
<%@ page import="com.is_web.entities.*" %>
<%@ page import="com.is_web.repository.Repository" %>
<%@ page import="com.is_web.repository.db.StbTypeRepository" %>
<%@ page import="com.is_web.repository.db.EmployeeRepository" %>
<%@ page import="com.is_web.repository.db.ProductRepository" %>
<%@ page import="com.is_web.repository.RepositoryException" %>
<%@ page import="com.is_web.repository.db.Invoice_boughtRepository" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">

<h1>Products</h1>
<div class="container-fluid">
    <div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Serial number</th>
                <th scope="col">Box type</th>
                <th scope="col">Employee</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
            </tr>

            </thead>

            <tbody>
            <%

                Repository<Employee> employeeRepository = (Repository<Employee>) request.getAttribute("employees");
                Repository<StbType> stbTypeRepository = (Repository<StbType>) request.getAttribute("stbTypes");
                for (Product product : (List<Product>) request.getAttribute("productsRepo")) {
                %>
            <tr>
                <th scope="row"><%= product.getId() %></th>
                <td>
                    <%= product.getSerial_number() %>
                </td>
                <td>
                    <%= stbTypeRepository.findById(product.getStb_type_id()).getName() %>
                </td>
                <%
                    String employeeName = "-";
                    try {
                        Employee owner = employeeRepository.findById(product.getEmployee_id());
                        employeeName = owner.getId() + ", " + owner.getSurname() + " " + owner.getName();
                    } catch (Exception e) {
                    }
                %>
                <td><%= employeeName %></td>
                <td>
                    <a class="btn btn-primary" href="/is_foj0105_web_war/products?id=<%=product.getId()%>">Edit</a>
                </td>
                <td>
                    <form action="/is_foj0105_web_war/products" method="POST">
                        <input onclick="return confirm('Do you really want to delete this product?');"
                               name="deleteProduct" type="submit" class="btn btn-danger" value="Delete"/>
                        <input name="id" type="hidden" value="<%= product.getId() %>"/>
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
                      action="/is_foj0105_web_war/products">
                    <h2>
                        Add new product
                    </h2>
                    <table>
                        <tr>
                            <td>
                                <label>
                                    Box name
                                </label>
                            </td>
                            <td>
                                <select name="stb_type_id" class="form-control">
                                    <%
                                    for(StbType stbType : stbTypeRepository.getAll()) { %>
                                    <option value="<%= stbType.getId()%>">
                                        <%=stbType.getName()%>
                                    </option>
                                    <% } %>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Serial number
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="serial_number"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Employee assignment
                                </label>
                            </td>
                            <td>
                                <select name="employees_id" class="form-control">
                                    <option value="-1">
                                        None
                                    </option>
                                    <% for (Employee employee : (List<Employee>) request.getAttribute("allEmployees")) { %>
                                    <option value="<%= employee.getId() %>">
                                        ID: <%= employee.getId() %>,
                                        Last name: <%= employee.getSurname() %>,
                                        First name: <%= employee.getName() %>
                                    </option>
                                    <% } %>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Invoice - bought
                                </label>
                            </td>
                            <td>
                                <select name="invoice_bought_id" class="form-control">
                                    <% for (Invoice_bought invoice_bought : (List<Invoice_bought>) request.getAttribute("allInvoiceBought")) { %>
                                    <option value="<%= invoice_bought.getId() %>">
                                        ID: <%= invoice_bought.getId() %>,
                                        invoice number: <%= invoice_bought.getInvoice_code()%>
                                    </option>
                                    <% } %>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Invoice - sold
                                </label></td>
                            <td>
                                <select name="invoice_sold_id" class="form-control">
                                    <option value="-1">
                                        None
                                    </option>
                                    <% for (Invoice_sold invoice_sold : (List<Invoice_sold>) request.getAttribute("allInvoiceSold")) { %>
                                    <option value="<%= invoice_sold.getId() %>">
                                        ID: <%= invoice_sold.getId() %>,
                                        invoice number: <%= invoice_sold.getInvoice_code()%>
                                    </option>
                                    <% } %>
                                </select>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input name="addNewProduct" type="submit" class="btn btn-success" value="Add"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>


            <div class="col-6">
                <%
                    Product productToEdit = (Product) request.getAttribute("productToEdit");
                    if (productToEdit != null) {
                %>
                <form method="POST"
                      action="/is_foj0105_web_war/products">
                    <h2>
                        Edit product
                    </h2>
                    <table>
                        <tr>
                            <td>
                                <label>
                                    Box name
                                </label>
                            </td>

                            <td>
                                <select name="stbTypeId" class="form-control">
                                    <% try {
                                        for(StbType stbType : stbTypeRepository.getAll()) { %>
                                    <option <%= productToEdit.getStb_type_id() == stbType.getId() ? "selected" : "" %> value="<%=stbType.getId()%>">
                                        <%=stbType.getName()%>
                                    </option>
                                    <% }
                                    } catch (RepositoryException e) {
                                        e.printStackTrace();
                                    } %>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Serial number
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="serial_number" value="<%= productToEdit.getSerial_number() %>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Employee assigned
                                </label>
                            </td>
                            <td>
                                <select name="employeeId" class="form-control">
                                    <option value="-1">
                                        None
                                    </option>
                                    <% try {
                                        for(Employee employee : employeeRepository.getAll()) { %>
                                    <option <%= (int)productToEdit.getEmployee_id() == employee.getId() ? "selected" : "None" %> value="<%=employee.getId()%>">
                                        <%= employee.getName()%>, <%= employee.getSurname()%>, ID: <%= employee.getId()%>
                                    </option>
                                    <% }
                                    } catch (RepositoryException e) {
                                        e.printStackTrace();
                                    } %>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Invoice - bought
                                </label>
                            </td>

                            <td>
                                <select name="invoice_bought_id" class="form-control">
                                    <%
                                        for (Invoice_bought invoice_bought : (List<Invoice_bought>) request.getAttribute("allInvoiceBought")) { %>
                                    <option value="<%= invoice_bought.getId() %>">
                                        <%= invoice_bought.getInvoice_code() %>
                                    </option>
                                    <% } %>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Invoice - sold
                                </label>
                            </td>

                            <td>
                                <select name="invoice_sold_id" class="form-control">
                                    <option value="-1">None</option>
                                    <%
                                        for (Invoice_sold invoice_sold : (List<Invoice_sold>) request.getAttribute("allInvoiceSold")) { %>
                                    <option <%= productToEdit.getInvoice_sold_id() == invoice_sold.getId() ? "selected" : "" %> value="<%= invoice_sold.getId() %>">
                                        Invoice number <%= invoice_sold.getInvoice_code() %>
                                    </option>
                                    <% } %>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <input name="editProduct" type="submit" class="btn btn-primary" value="Save changes"/>
                                <input name="id" type="hidden" value="<%=productToEdit.getId()%>"/>
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