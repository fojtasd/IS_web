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

@WebServlet(name = "ProductServlet", value = "/products")
public class ProductServlet extends IsServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Repository<Product> productsRepo = getRepositoryFactory().create(Product.class);
            Repository<Employee> employeeRepo = getRepositoryFactory().create(Employee.class);
            Repository<StbType> stbTypeRepo = getRepositoryFactory().create(StbType.class);
            Repository<Invoice_bought> invoiceBoughtRepo = getRepositoryFactory().create(Invoice_bought.class);
            Repository<Invoice_sold> invoiceSoldRepo = getRepositoryFactory().create(Invoice_sold.class);

            request.setAttribute("employees", employeeRepo.getAll());
            request.setAttribute("stbTypes", stbTypeRepo);
            request.setAttribute("allStbTypes", stbTypeRepo.getAll());
            request.setAttribute("allProducts", productsRepo.getAll());
            request.setAttribute("allInvoiceBought", invoiceBoughtRepo.getAll());
            request.setAttribute("productsRepo", productsRepo.getAll());
            request.setAttribute("employees", employeeRepo);
            request.setAttribute("allEmployees", employeeRepo.getAll());
            request.setAttribute("allInvoiceBought", invoiceBoughtRepo.getAll());
            request.setAttribute("allInvoiceSold", invoiceSoldRepo.getAll());
            request.setAttribute("page", "products");
            request.setAttribute("invoiceBought", invoiceBoughtRepo);


            String idParam = request.getParameter("id");

            if (idParam != null) {
                log(idParam);
                int productId = Integer.parseInt(idParam);
                Product product = productsRepo.findById(productId);
                request.setAttribute("productToEdit", product);
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

            if (request.getParameter("deleteProduct") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Product product = productsRepo.findById(id);
                productsRepo.delete(product);
            }

            if (request.getParameter("addNewProduct") != null) {
                String serial_number = request.getParameter("serial_number");
                int stb_type_id = Integer.parseInt(request.getParameter("stb_type_id"));
                int employees_id = Integer.parseInt(request.getParameter("employees_id"));
                int invoice_sold_id = Integer.parseInt(request.getParameter("invoice_sold_id"));
                int invoice_bought_id = Integer.parseInt(request.getParameter("invoice_bought_id"));
                Integer employee;
                int stb_type;
                Integer invoice_sold;
                Integer invoice_bought;

                if (invoice_sold_id == -1){
                    invoice_sold = null;
                }
                else {
                    invoice_sold = invoice_sold_id;
                }

                if (invoice_bought_id == -1) {
                    invoice_bought = null;
                }
                else{
                    invoice_bought = invoice_bought_id;
                }

                if (employees_id == -1){
                    employee = null;
                }
                else{
                    employee = employees_id;
                }

                if (stb_type_id != -1) {
                    stb_type = stb_type_id;
                }
                else{
                    stb_type = 0;
                }

                Product newProduct;
                newProduct = new Product(
                        serial_number,
                        employee,
                        stb_type,
                        invoice_sold,
                        invoice_bought
                );
                productsRepo.saveOrUpdate(newProduct);


            }

            if (request.getParameter("editProduct") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                Product product = productsRepo.findById(id);

                String serial_number = request.getParameter("serial_number");
                int stbTypeId = Integer.parseInt(request.getParameter("stbTypeId"));
                int employeeId = Integer.parseInt(request.getParameter("employeeId"));
                int invoiceBought = Integer.parseInt(request.getParameter("invoice_bought_id"));
                Integer invoiceSold = Integer.parseInt(request.getParameter("invoice_sold_id"));

                product.setSerial_number(serial_number);
                product.setInvoice_bought_id(invoiceBought);
                product.setInvoice_sold_id(invoiceSold);
                product.setStb_type_id(stbTypeId);

                if(invoiceSold == -1){
                    product.setInvoice_sold_id(null);
                }
                else{
                    product.setInvoice_sold_id(invoiceSold);
                }

                if(employeeId == -1){
                    product.setEmployee_id(null);
                }
                else{
                    product.setEmployee_id(employeeId);
                }

                productsRepo.saveOrUpdate(product);
            }

            response.sendRedirect("/is_foj0105_web_war/products");

        } catch (RepositoryException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }
}

