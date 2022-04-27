package com.is_web.servlets;

import com.is_web.entities.Employee;
import com.is_web.entities.Locality;
import com.is_web.repository.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet(name = "EmployeeServlet", value = "/employees")
public class EmployeeServlet extends IsServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Repository<Employee> employeeRepo = getRepositoryFactory().create(Employee.class);
            Repository<Locality> localityRepo = getRepositoryFactory().create(Locality.class);

            request.setAttribute("localityRepo", localityRepo);
            request.setAttribute("employees", employeeRepo.getAll());
            request.setAttribute("page", "employees");
            request.setAttribute("localities", localityRepo.getAll());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/layout.jsp");

            String idParam = request.getParameter("id");
            if (idParam != null) {
                int employeeId = Integer.parseInt(idParam);
                Employee employee = employeeRepo.findById(employeeId);
                request.setAttribute("employeeToEdit", employee);
            }

            dispatcher.forward(request, response);

        } catch (RepositoryException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Repository<Employee> employeeRepo = getRepositoryFactory().create(Employee.class);

            if (request.getParameter("deleteEmployee") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Employee soonToBeDeleted = employeeRepo.findById(id);
                employeeRepo.execTransactionDeleteEmployee(soonToBeDeleted);
                response.sendRedirect("/is_foj0105_web_war/employees");
            }

            if (request.getParameter("RedirectToOwnedEmployee_device") != null){
                int id = Integer.parseInt(request.getParameter("id"));
                HttpSession session = request.getSession();
                session.setAttribute("employeeIdDevice", id);

                getServletContext().getRequestDispatcher("/employees_device").forward(request,response);
            }

            if (request.getParameter("addNewEmployee") != null) {
                String name = request.getParameter("name");
                String surname = request.getParameter("surname");
                String city = request.getParameter("city");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String street = request.getParameter("street");
                String postal = request.getParameter("postal");
                int localityId = Integer.parseInt(request.getParameter("localityId"));

                Employee newEmployee = new Employee(
                        name,
                        surname,
                        email,
                        phone,
                        city,
                        street,
                        postal,
                        localityId
                );
                employeeRepo.execTransactionCreateNewEmployee(newEmployee);
                response.sendRedirect("/is_foj0105_web_war/employees");
            }

            if (request.getParameter("editEmployee") != null) {
                int id = Integer.parseInt(request.getParameter("id"));

                Employee employee = employeeRepo.findById(id);

                String firstName = request.getParameter("name");
                String lastName = request.getParameter("surname");
                String email = request.getParameter("email");
                String phone = request.getParameter("phone");
                String city = request.getParameter("city");
                String street = request.getParameter("street");
                String postal = request.getParameter("postal");
                int localityId = Integer.parseInt(request.getParameter("localityId"));

                employee.setName(firstName);
                employee.setPhone(phone);
                employee.setEmail(email);
                employee.setSurname(lastName);
                employee.setCity(city);
                employee.setStreet(street);
                employee.setPostal(postal);
                employee.setLocalityId(localityId);

                employeeRepo.execTransactionUpdateEmployee(employee);
                response.sendRedirect("/is_foj0105_web_war/employees");
            }



        } catch (RepositoryException | SQLException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }
}

