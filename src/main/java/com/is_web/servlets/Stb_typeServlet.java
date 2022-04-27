package com.is_web.servlets;

import com.is_web.entities.StbType;
import com.is_web.repository.Repository;
import com.is_web.repository.RepositoryException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Stb_typeServlet", value = "/stb_type")
public class Stb_typeServlet extends IsServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Repository<StbType> stbTypeRepository = getRepositoryFactory().create(StbType.class);
            request.setAttribute("page", "stb_type");
            request.setAttribute("allStbTypes", stbTypeRepository.getAll());

            String idParam = request.getParameter("id");
            if (idParam != null) {
                int stbTypeId = Integer.parseInt(idParam);
                StbType stbType = stbTypeRepository.findById(stbTypeId);
                request.setAttribute("stbTypeToEdit", stbType);
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
            Repository<StbType> stbTypeRepository = getRepositoryFactory().create(StbType.class);

            if (request.getParameter("deleteStbType") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                StbType stbType = stbTypeRepository.findById(id);
                stbTypeRepository.delete(stbType);
                response.sendRedirect("/is_foj0105_web_war/stb_type");
            }

            if (request.getParameter("addNewStbType") != null) {
                String name = request.getParameter("setName");
                int ram = Integer.parseInt(request.getParameter("setRam"));
                String wifi = request.getParameter("setWifi");
                int cpuCores = Integer.parseInt(request.getParameter("setCpuC"));
                int cpuFrequency = Integer.parseInt(request.getParameter("setCpuF"));
                StbType stbType = new StbType(name, ram, wifi, cpuCores, cpuFrequency);

                stbTypeRepository.saveOrUpdate(stbType);
                response.sendRedirect("/is_foj0105_web_war/stb_type");
            }

            if (request.getParameter("editStbType") != null) {
                int id = Integer.parseInt(request.getParameter("id"));
                StbType stbType = stbTypeRepository.findById(id);
                String name = request.getParameter("changeName");
                String wifi = request.getParameter("changeWifi");
                int cpuCores = Integer.parseInt(request.getParameter("changeCpuCores"));
                int cpuFrequency = Integer.parseInt(request.getParameter("changeCpuFrequency"));
                int ram = Integer.parseInt(request.getParameter("changeRam"));
                stbType.setName(name);
                stbType.setWifi_module(wifi);
                stbType.setCpu_cores(cpuCores);
                stbType.setCpu_frequency(cpuFrequency);
                stbType.setRam(ram);
                stbTypeRepository.saveOrUpdate(stbType);
                response.sendRedirect("/is_foj0105_web_war/stb_type");
            }



        } catch (RepositoryException e) {
            throw new ServletException(e.getMessage(), e);
        }
    }
}
