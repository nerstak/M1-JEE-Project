package control.sessionBeans;

import modelsEntities.CommentsEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless(name = "CommentsSessionEJB")
public class CommentsSessionBean extends ModelBean<CommentsEntity> {
    @PersistenceContext
    EntityManager em;

    public CommentsEntity find(UUID id) {
        return em.find(CommentsEntity.class, id);
    }

    public void save(CommentsEntity e) {
        em.merge(e);
    }
}
