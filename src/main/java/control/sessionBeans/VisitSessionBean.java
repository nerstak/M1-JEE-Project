package control.sessionBeans;

import modelsEntities.CommentsEntity;
import modelsEntities.VisitEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless(name = "VisitSessionEJB")
public class VisitSessionBean {
    @PersistenceContext
    EntityManager em;

    public VisitEntity find(UUID id) {
        return em.find(VisitEntity.class, id);
    }

    public boolean save(VisitEntity e) {
        em.merge(e);
        return true;
    }
}
