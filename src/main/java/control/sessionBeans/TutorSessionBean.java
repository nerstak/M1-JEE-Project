package control.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "TutorSessionBeanEJB")
public class TutorSessionBean {
    @PersistenceContext
    EntityManager em;

    public List getTutors(String email, String pwd) {
        Query q = em.createNamedQuery("Tutor.SelectSingleTutor");
        q.setParameter("email",email);
        q.setParameter("pwd", pwd);
        return q.getResultList();
    }
}
