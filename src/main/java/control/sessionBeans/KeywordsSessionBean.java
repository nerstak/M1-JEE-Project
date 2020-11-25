package control.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "KeywordsSessionEJB")
public class KeywordsSessionBean {
    @PersistenceContext
    EntityManager em;

    public List getKeywords() {
        Query q = em.createNamedQuery("Keywords.SelectAll");
        return q.getResultList();
    }
}
