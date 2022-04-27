package com.is_web.repository;

import javax.management.ReflectionException;

public class RepositoryException extends ReflectionException {
    public RepositoryException(Exception e) {
        super(e);
    }

    public RepositoryException(Exception e, String message) {
        super(e, message);
    }
}
