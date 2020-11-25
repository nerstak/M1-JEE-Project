package control.sessionBeans;

import modelsEntities.KeywordsEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Stateless(name = "KeywordsSessionEJB")
public class KeywordsSessionBean{
    @PersistenceContext
    EntityManager em;

    public List getKeywords() {
        Query q = em.createNamedQuery("Keywords.SelectAll");
        return q.getResultList();
    }

    public KeywordsEntity find(UUID id) {
        em.find(KeywordsEntity.class,id);
        return null;
    }

    public boolean save(KeywordsEntity e) {
        em.merge(e);
        return false;
    }
}
