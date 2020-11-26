package control.sessionBeans;

import models.InterfaceEntity;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

public abstract class ModelBean<T extends InterfaceEntity> {
    @PersistenceContext
    protected EntityManager em;

    public void save(T e) throws EntityExistsException {
        if(!em.contains(e)) {
            em.persist(e);
        } else {
            em.merge(e);
        }
    }

    public abstract T find(UUID id);
}
