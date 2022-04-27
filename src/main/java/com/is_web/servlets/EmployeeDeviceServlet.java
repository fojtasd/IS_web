package com.is_web.servlets;

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


@WebServlet(name = "EmployeesDeviceServlet", value = "/employees_device")
public class EmployeeDeviceServlet extends IsServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Repository<Product> productRepo = getRepositoryFactory().create(Product.class);
            Repository<StbType> stbTypeRepo = getRepositoryFactory().create(StbType.class);

            HttpSession session = request.getSession();
            Integer id = (Integer) session.getAttribute("employeeIdDevice");
            request.setAttribute("page", "employees_device");
            request.setAttribute("employeeId", id);
            request.setAttribute("employeesDevices", productRepo.where("employees_id", id));
            request.setAttribute("stbTypeRepo", stbTypeRepo);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/layout.jsp");


            dispatcher.forward(request, response);

        } catch (RepositoryException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect("/is_foj0105_web_war/employees_device");

    }
}

