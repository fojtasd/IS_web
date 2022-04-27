package com.is_web.servlets;

import com.is_web.entities.Employee;
import com.is_web.entities.Locality;
import com.is_web.repository.Repository;
import com.is_web.repository.RepositoryException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "LocalityServlet", value = "/localities")
public class LocalityServlet extends IsServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Repository<Locality> localityRepo = getRepositoryFactory().create(Locality.class);
            Repository<Employee> employeeRepository = getRepositoryFactory().create(Employee.class);

            request.setAttribute("page", "localities");
            request.setAttribute("allEmployees", employeeRepository.getAll());
            request.setAttribute("localities", localityRepo.getAll());
            RequestDispatcher dispatcher = request.getRequestDispatcher("/layout.jsp");

            String idParam = request.getParameter("id");
            if (idParam != null) {
                int localityId = Integer.parseInt(idParam);
                Locality locality = localityRepo.findById(localityId);
                request.setAttribute("localityToEdit", locality);
            }

            dispatcher.forward(request, response);

        } catch (RepositoryException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Repository<Locality> localityRepo = getRepositoryFactory().create(Locality.class);

            if (request.getParameter("deleteLocality") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Locality locality = localityRepo.findById(id);
                localityRepo.delete(locality);
                response.sendRedirect("/is_foj0105_web_war/localities");
            }

            if (request.getParameter("employees") != null){
                int id = Integer.parseInt(request.getParameter("id"));
                HttpSession session = request.getSession();
                session.setAttribute("employeeIdLocality", id);

                getServletContext().getRequestDispatcher("/employees_locality").forward(request,response);
            }

            if (request.getParameter("addNewLocality") != null) {
                String label = request.getParameter("label");
                String city = request.getParameter("city");
                String street = request.getParameter("street");
                String postal = request.getParameter("postal");
                String phone_office = request.getParameter("phone_office");

                Locality locality = new Locality(
                        label,
                        city,
                        street,
                        postal,
                        phone_office
                );
                localityRepo.execTransactionCreateNewLocality(locality);
                response.sendRedirect("/is_foj0105_web_war/localities");
            }

            if (request.getParameter("editLocality") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Locality locality = localityRepo.findById(id);

                String office = request.getParameter("office");
                String city = request.getParameter("city");
                String street = request.getParameter("street");
                String postal = request.getParameter("postal");
                String phone_office = request.getParameter("phone_office");

                locality.setLabel(office);
                locality.setCity(city);
                locality.setStreet(street);
                locality.setPostal(postal);
                locality.setPhone_office(phone_office);

                localityRepo.saveOrUpdate(locality);
                response.sendRedirect("/is_foj0105_web_war/localities");
            }



        } catch (RepositoryException | SQLException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }
}
