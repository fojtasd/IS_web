package com.is_web.repository;

import com.is_web.entities.Entity;

public interface RepositoryFactory {
    <T extends Entity> Repository<T> create(Class<T> entityType) throws RepositoryException;
}

