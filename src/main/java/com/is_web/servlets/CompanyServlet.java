package com.is_web.servlets;

import com.is_web.entities.Company;
import com.is_web.repository.Repository;
import com.is_web.repository.RepositoryException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CompanyServlet", value = "/companies")
public class CompanyServlet extends IsServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Repository<Company> companyRepository = getRepositoryFactory().create(Company.class);
            request.setAttribute("page", "companies");
            request.setAttribute("companies", companyRepository.getAll());

            String idParam = request.getParameter("id");
            if (idParam != null) {
                int companyId = Integer.parseInt(idParam);
                Company company = companyRepository.findById(companyId);
                request.setAttribute("companyToEdit", company);
            }


        } catch (RepositoryException e) {
            e.printStackTrace();
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/layout.jsp");
            dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Repository<Company> companyRepository = getRepositoryFactory().create(Company.class);

            if (request.getParameter("deleteCompany") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Company company = companyRepository.findById(id);
                companyRepository.delete(company);
                response.sendRedirect("/is_foj0105_web_war/companies");
            }

            if (request.getParameter("addNewCompany") != null) {
                String name = request.getParameter("setName");
                String vat = request.getParameter("setVat");
                String city = request.getParameter("setCity");
                String street = request.getParameter("setStreet");
                String phone = request.getParameter("setPhone");
                String postal = request.getParameter("setPostal");
                String email = request.getParameter("setEmail");
                Company company = new Company(name, vat, city, street, postal, phone, email);
                companyRepository.saveOrUpdate(company);
                response.sendRedirect("/is_foj0105_web_war/companies");
            }

            if (request.getParameter("editCompany") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Company company = companyRepository.findById(id);
                String name = request.getParameter("changeName");
                String vat = request.getParameter("changeVat");
                String city = request.getParameter("changeCity");
                String street = request.getParameter("changeStreet");
                String postal = request.getParameter("changePostal");
                String email = request.getParameter("changeEmail");
                String phone = request.getParameter("changePhone");
                company.setName(name);
                company.setVat(vat);
                company.setCity(city);
                company.setStreet(street);
                company.setPostal(postal);
                company.setEmail(email);
                company.setPhone(phone);
                companyRepository.saveOrUpdate(company);
                response.sendRedirect("/is_foj0105_web_war/companies");
            }



        } catch (RepositoryException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }
}
