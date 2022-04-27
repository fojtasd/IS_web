package com.is_web.servlets;


import com.is_web.entities.*;
import com.is_web.repository.Repository;
import com.is_web.repository.RepositoryException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet(name = "ReclamationServlet", value = "/reclamations")
public class ReclamationServlet extends IsServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Repository<Product> productsRepository = getRepositoryFactory().create(Product.class);
            Repository<Employee> employeeRepository = getRepositoryFactory().create(Employee.class);
            Repository<StbType> stbTypeRepository = getRepositoryFactory().create(StbType.class);
            Repository<Company> companyRepository = getRepositoryFactory().create(Company.class);
            Repository<Invoice_bought> invoiceBoughtRepository = getRepositoryFactory().create(Invoice_bought.class);
            Repository<Invoice_sold> invoiceSoldRepository = getRepositoryFactory().create(Invoice_sold.class);
            Repository<Reclamation> reclamationRepository = getRepositoryFactory().create(Reclamation.class);

            request.setAttribute("getAllReclamation", reclamationRepository.getAll());
            request.setAttribute("getAllCompanies", companyRepository.getAll());
            request.setAttribute("getProductRepository", productsRepository);
            request.setAttribute("getStbTypeRepository", stbTypeRepository);
            request.setAttribute("getCompanyRepository", companyRepository);
            request.setAttribute("page", "reclamations");



            String idParam = request.getParameter("id");

            if (idParam != null) {
                log(idParam);
                int reclamationId = Integer.parseInt(idParam);
                Reclamation reclamation = reclamationRepository.findById(reclamationId);
                request.setAttribute("reclamationToEdit", reclamation);
            }
            RequestDispatcher dispatcher = request.getRequestDispatcher("/layout.jsp");
            dispatcher.forward(request, response);

        } catch (RepositoryException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Repository<Product> productsRepo = getRepositoryFactory().create(Product.class);
            Repository<Reclamation> reclamationRepository = getRepositoryFactory().create(Reclamation.class);

            if (request.getParameter("deleteProduct") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Product product = productsRepo.findById(id);
                productsRepo.delete(product);
            }

            if (request.getParameter("addNewReclamation") != null) {
                int productToReclamation = Integer.parseInt(request.getParameter("selectProduct"));
                Product product = productsRepo.findById(productToReclamation);

                LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));

                String description = request.getParameter("addDescription");
                int companyId = Integer.parseInt(request.getParameter("companyToReclamation"));

                System.out.println(startDate);

                Reclamation reclamation = new Reclamation(
                        product.getSerial_number(),
                        false,
                        startDate,
                        null,
                        description,
                        productToReclamation,
                        companyId
                );

//                Reclamation reclamation =  reclamationRepository.findById(2);
//                reclamation.setStart_date();

                reclamationRepository.saveOrUpdate(reclamation);


            }

            if (request.getParameter("editReclamation") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Reclamation reclamation = reclamationRepository.findById(id);

                int productToEditReclamation = Integer.parseInt(request.getParameter("productToEditReclamation"));
                reclamation.setProducts_id(productToEditReclamation);

                LocalDate startDate = LocalDate.parse(request.getParameter("ChangeStartDate"));
                if(startDate == null){
                    reclamation.setStart_date(reclamation.getStart_date());
                }
                else{
                    reclamation.setStart_date(startDate);
                }

                reclamation.setStart_date(startDate);

                LocalDate endDate = LocalDate.parse(request.getParameter("ChangeEndDate"));
                reclamation.setEnd_date(endDate);

                boolean approve = Boolean.parseBoolean(request.getParameter("approve"));
                reclamation.setApproved(approve);

                String description = request.getParameter("changeDescription");
                reclamation.setDescription(description);

                int companyId = Integer.parseInt(request.getParameter("companyToReclamationChange"));
                reclamation.setCompanies_id(companyId);

                reclamationRepository.saveOrUpdate(reclamation);
            }

            response.sendRedirect("/is_foj0105_web_war/reclamations");

        } catch (RepositoryException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }
}

