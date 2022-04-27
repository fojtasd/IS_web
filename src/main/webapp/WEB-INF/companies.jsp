<%@ page import="java.util.List" %>
<%@ page import="com.is_web.entities.Locality" %>
<%@ page import="com.is_web.entities.Employee" %>
<%@ page import="com.is_web.entities.Company" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet"
      integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
<h1>Companies</h1>
<div class="container-fluid">
  <div>
    <table class="table table-hover">
      <thead>
      <tr>
        <th scope="col">#</th>
        <th scope="col">Name</th>
        <th scope="col">VAT</th>
        <th scope="col">City</th>
        <th scope="col">Street</th>
        <th scope="col">Postal</th>
        <th scope="col">Phone</th>
        <th scope="col">E-mail</th>
        <th scope="col">Edit</th>
        <th scope="col">Delete</th>
      </tr>
      </thead>
      <tbody>
      <tr>
        <% for (Company company : (List<Company>) request.getAttribute("companies")) {

        %>
        <th scope="row"><%= company.getId() %></th>
        <td><%= company.getName() %></td>
        <td><%= company.getVat() %></td>
        <td><%= company.getCity() %></td>
        <td><%= company.getStreet() %></td>
        <td><%= company.getPostal() %></td>
        <td><%= company.getPhone() %></td>
        <td><%= company.getEmail() %></td>
        <td>
          <a class="btn btn-primary" href="/is_foj0105_web_war/companies?id=<%= company.getId() %>">
            Edit
          </a>
        </td>
        <td>
          <form action="/is_foj0105_web_war/companies" method="POST">
            <input onclick="return confirm('Do you want to delete this item?');" name="deleteCompany" type="submit" class="btn btn-danger" value="Delete"/>
            <input name="id" type="hidden" value="<%= company.getId() %>"/>
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
              action="/is_foj0105_web_war/companies">
          <h2>
            Add new Company
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
                  VAT
                </label>
              </td>
              <td>
                <input class="form-control" type="text" name="setVat"/>
              </td>
            </tr>

            <tr>
              <td>
                <label>
                  City
                </label>
              </td>
              <td>
                <input class="form-control" type="text" name="setCity"/>
              </td>
            </tr>

            <tr>
              <td>
                <label>
                  Street
                </label>
              </td>
              <td>
                <input class="form-control" type="text" name="setStreet"/>
              </td>
            </tr>

            <tr>
              <td>
                <label>
                  Postal
                </label>
              </td>
              <td>
                <input class="form-control" type="text" name="setPostal"/>
              </td>
            </tr>

            <tr>
              <td>
                <label>
                  Contact phone
                </label>
              </td>
              <td>
                <input class="form-control" type="text" name="setPhone"/>
              </td>
            </tr>

            <tr>
              <td>
                <label>
                  E-mail
                </label>
              </td>
              <td>
                <input class="form-control" type="text" name="setEmail"/>
              </td>
            </tr>

            <tr>
              <td>
                <input name="addNewCompany" type="submit" class="btn btn-success" value="Add"/>
              </td>
            </tr>

          </table>
        </form>
      </div>
      <div class="col-6">
        <%
          Company companyToEdit = (Company) request.getAttribute("companyToEdit");
          if (companyToEdit != null) {
        %>
        <form method="POST"
              action="/is_foj0105_web_war/companies">
          <h2>
            Edit locality
          </h2>

          <table>

            <tr>
              <td>
                <label>
                  Name
                </label>
              </td>
              <td>
                <input class="form-control" type="text" name="changeName" value="<%=companyToEdit.getName()%>"/>
              </td>
            </tr>

            <tr>
              <td>
                <label>
                  VAT
                </label>
              </td>
              <td>
                <input class="form-control" type="text" name="changeVat" value="<%=companyToEdit.getVat()%>"/>
              </td>
            </tr>

            <tr>
              <td>
                <label>
                  City
                </label>
              </td>
              <td>
                <input class="form-control" type="text" name="changeCity" value="<%=companyToEdit.getCity()%>"/>
              </td>
            </tr>

            <tr>
              <td>
                <label>
                  Street
                </label>
              </td>
              <td>
                <input class="form-control" type="text" name="changeStreet" value="<%=companyToEdit.getStreet()%>"/>
              </td>
            </tr>

            <tr>
              <td>
                <label>
                  Postal
                </label>
              </td>
              <td>
                <input class="form-control" type="text" name="changePostal" value="<%=companyToEdit.getPostal()%>"/>
              </td>
            </tr>

            <tr>
              <td>
                <label>
                  Contact phone
                </label>
              </td>
              <td>
                <input class="form-control" type="text" name="changePhone" value="<%=companyToEdit.getPhone()%>"/>
              </td>
            </tr>

            <tr>
              <td>
                <label>
                  E-mail
                </label>
              </td>
              <td>
                <input class="form-control" type="text" name="changeEmail" value="<%=companyToEdit.getEmail()%>"/>
              </td>
            </tr>

            <tr>
              <td>
                <input name="editCompany" type="submit" class="btn btn-success" value="Save changes"/>
              </td>
              <td>
                <input name="id" type="hidden" value="<%= companyToEdit.getId() %>"/>
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