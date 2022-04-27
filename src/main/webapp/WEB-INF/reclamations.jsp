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

<h1>Reclamations</h1>
<div class="container-fluid">
    <div>

        <table class="table table-hover">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Serial number</th>
                <th scope="col">Box type</th>
                <th scope="col">Company</th>
                <th scope="col">Start date</th>
                <th scope="col">End date</th>
                <th scope="col">Description</th>
                <th scope="col">Approved</th>
                <th scope="col">Edit</th>
                <th scope="col">Delete</th>
            </tr>

            </thead>

            <tbody>
            <%

                Repository<Product> productRepository = (Repository<Product>) request.getAttribute("getProductRepository");
                Repository<StbType> stbTypeRepository = (Repository<StbType>) request.getAttribute("getStbTypeRepository");
                Repository<Company> companyRepository = (Repository<Company>) request.getAttribute("getCompanyRepository");
                for (Reclamation reclamation : (List<Reclamation>) request.getAttribute("getAllReclamation")) {
                    String endDate;
                    if (reclamation.getEnd_date() == null){
                        endDate = "---";
                    }
                    else{
                        endDate = reclamation.getEnd_date().toString();
                    }
            %>
            <tr>
                <th scope="row">
                    <%= reclamation.getId() %>
                </th>
                <td>
                    <%= reclamation.getSerial_number() %>
                </td>
                <td>
                    <%
                        Product product = productRepository.findById(reclamation.getProducts_id());
                        StbType stb = stbTypeRepository.findById(product.getStb_type_id());
                    %>
                    <%= stb.getName() %>
                </td>
                <td>
                    <%= companyRepository.findById(reclamation.getCompanies_id()).getName() %>
                </td>
                <td><%= reclamation.getStart_date() %></td>
                <td><%= endDate %></td>
                <td><%= reclamation.getDescription() %></td>
                <td><%= reclamation.getApproved() %></td>
                <td>
                    <a class="btn btn-primary" href="/is_foj0105_web_war/reclamations?id=<%=reclamation.getId()%>">Edit</a>
                </td>
                <td>
                    <form action="/is_foj0105_web_war/reclamations" method="POST">
                        <input onclick="return confirm('Do you really want to delete this product?');"
                               name="deleteReclamation" type="submit" class="btn btn-danger" value="Delete"/>
                        <input name="id" type="hidden" value="<%= reclamation.getId() %>"/>
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
                      action="/is_foj0105_web_war/reclamations">
                    <h2>
                        Add new reclamation
                    </h2>
                    <table>
                        <tr>
                            <td>
                                <label>
                                    Product
                                </label>
                            </td>
                            <td>
                                <select name="selectProduct" class="form-control">
                                    <%
                                        for(Product product : productRepository.getAll()) { %>
                                    <option value="<%= product.getId()%>">
                                        S/N: <%=product.getSerial_number()%>, <%=stbTypeRepository.findById(product.getStb_type_id()).getName()%>
                                    </option>
                                    <% } %>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Start date of reclamation
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="date" name="startDate" placeholder="yyyy-mm-dd"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Company
                                </label>
                            </td>
                            <td>
                                <select name="companyToReclamation" class="form-control">
                                    <% for (Company company : (List<Company>) request.getAttribute("getAllCompanies")) { %>
                                    <option value="<%= company.getId() %>">
                                        <%= company.getName() %>
                                    </option>
                                    <% } %>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Description
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="addDescription"/>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <input name="addNewReclamation" type="submit" class="btn btn-success" value="Add"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>


            <div class="col-6">
                <%
                    Reclamation reclamationToEdit = (Reclamation) request.getAttribute("reclamationToEdit");
                    if (reclamationToEdit != null) {
                %>
                <form method="POST"
                      action="/is_foj0105_web_war/reclamations">
                    <h2>
                        Edit reclamation
                    </h2>
                    <table>
                        <tr>
                            <td>
                                <label>
                                    Product
                                </label>
                            </td>

                            <td>
                                <select name="productToEditReclamation" class="form-control">
                                    <option selected value="<%=reclamationToEdit.getProducts_id()%>">
                                        S/N: <%= productRepository.findById(reclamationToEdit.getProducts_id()).getSerial_number()%>, Type: <%=stbTypeRepository.findById(productRepository.findById(reclamationToEdit.getProducts_id()).getStb_type_id()).getName()%>
                                    </option>
                                    <% try {
                                        for(Product product : productRepository.getAll()) { %>
                                    <option value="<%=product.getId()%>">
                                        S/N: <%= product.getSerial_number()%>, Type: <%= stbTypeRepository.findById(product.getStb_type_id()).getName()%>
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
                                    Start date of reclamation
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="date" name="changeStartDate" placeholder="yyyy-mm-dd" value="<%=reclamationToEdit.getStart_date()%>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    End date of reclamation
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="date" name="changeEndDate" placeholder="yyyy-mm-dd" value="<%=reclamationToEdit.getEnd_date()%>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Company
                                </label>
                            </td>
                            <td>
                                <select name="companyToReclamation" class="form-control">
                                    <% for (Company company : (List<Company>) request.getAttribute("getAllCompanies")) { %>
                                    <option value="<%= company.getId() %>">
                                        <%= company.getName() %>
                                    </option>
                                    <% } %>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Description
                                </label>
                            </td>
                            <td>
                                <input class="form-control" type="text" name="changeDescription" value="<%=reclamationToEdit.getDescription()%>"/>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <label>
                                    Approved?
                                </label>
                            </td>
                            <td>
                                <select name="approve" class="form-control">
                                    <option value="false">
                                        False
                                    </option>
                                    <option value="true">
                                        True
                                    </option>
                                </select>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <input name="editReclamation" type="submit" class="btn btn-primary" value="Save changes"/>
                                <input name="id" type="hidden" value="<%=reclamationToEdit.getId()%>"/>
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