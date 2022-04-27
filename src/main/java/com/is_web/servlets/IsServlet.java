package com.is_web.servlets;

import com.is_web.repository.DataLayerType;
import com.is_web.repository.IsRepositoryFactory;
import com.is_web.repository.RepositoryFactory;

import javax.servlet.http.HttpServlet;

public class IsServlet extends HttpServlet {

    private final RepositoryFactory repositoryFactory = new IsRepositoryFactory(DataLayerType.Database);

    protected RepositoryFactory getRepositoryFactory() {
        return repositoryFactory;
    }
}
