package control.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "CommentsSessionEJB")
public class CommentsSessionBean {
    @PersistenceContext
    EntityManager em;
}
