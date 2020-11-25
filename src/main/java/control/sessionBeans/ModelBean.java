package control.sessionBeans;

import modelsEntities.InterfaceEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

public abstract class ModelBean<T extends InterfaceEntity> {
    @PersistenceContext
    protected EntityManager em;

    public boolean save(T e) {
        em.merge(e);
        return true;
    }

    public abstract T find(UUID id);
}
