package control.sessionBeans;

import modelsEntities.MarksEntity;
import modelsEntities.StudentEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless(name = "MarksSessionEJB")
public class MarksSessionBean {
    @PersistenceContext
    EntityManager em;

    public boolean save(MarksEntity e) {
        em.merge(e);
        return true;
    }

    public MarksEntity find(UUID id) {
        return em.find(MarksEntity.class, id);
    }
}
