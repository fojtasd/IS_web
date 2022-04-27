package com.is_web.repository;

import com.is_web.entities.Entity;

import java.lang.reflect.Constructor;

/**
 * Factory for this information system
 */
public class IsRepositoryFactory implements RepositoryFactory {

    /**
     * @param type determines what database layer is used
     */
    public IsRepositoryFactory(DataLayerType type) {
        this.type = type;
    }

    private DataLayerType type;

    public void setType(DataLayerType type) {
        this.type = type;
    }

    public DataLayerType getType() {
        return type;
    }

    /**
     * Create repository which is bound to a given entity type with use of reflection.
     * @param entityType Entity class for which is repository created
     * @param <T> Entity type
     * @return repository
     * @throws RepositoryException when given entity class does not have repository or data layer type is unknown
     */
    @Override
    public <T extends Entity> Repository<T> create(Class<T> entityType) throws RepositoryException {
        try {
            String dataLayerPrefix = "";

            switch (type) {
                case Database:
                    dataLayerPrefix = "db";
                    break;
                case Xml:
                    dataLayerPrefix = "xml";
                    break;
            }

            if (dataLayerPrefix.equals("")) {
                throw new RepositoryException(new Exception(), "Data layer type is not supported");
            }


            Class<?> repositoryClass = Class.forName("com.is_foj0105_web.repository." + dataLayerPrefix + "." + entityType.getSimpleName() + "Repository");
            Constructor<?> constructor = repositoryClass.getConstructor();
            return (Repository<T>) constructor.newInstance();

        } catch (Exception e) {
            throw new RepositoryException(e, "Given entity type does not have repository");
        }
    }
}