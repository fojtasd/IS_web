package com.is_web.servlets;

import com.is_web.entities.Employee;
import com.is_web.entities.Locality;
import com.is_web.entities.Product;
import com.is_web.entities.StbType;
import com.is_web.repository.Repository;
import com.is_web.repository.RepositoryException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet(name = "EmployeesLocalityServlet", value = "/employees_locality")
public class EmployeeLocalityServlet extends IsServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Repository<Employee> employeeRepo = getRepositoryFactory().create(Employee.class);
            Repository<Locality> localityRepo = getRepositoryFactory().create(Locality.class);
            Repository<Product> productRepo = getRepositoryFactory().create(Product.class);
            Repository<StbType> stbTypeRepo = getRepositoryFactory().create(StbType.class);

            HttpSession session = request.getSession();
            Integer id = (Integer) session.getAttribute("employeeIdLocality");

            request.setAttribute("page", "employees_locality");
            request.setAttribute("employeeId", id);
            request.setAttribute("employeesLocality", employeeRepo.where("locality_id", id));
            request.setAttribute("employeeRepo", employeeRepo);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/layout.jsp");


            dispatcher.forward(request, response);

        } catch (RepositoryException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/is_foj0105_web_war/employees_locality");

    }
}

