package control.sessionBeans;

import modelsEntities.TutorEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Stateless(name = "InternshipSessionEJB")
public class InternshipSessionBean {
    @PersistenceContext
    EntityManager em;

    public List getInternshipData(UUID tutorId, int year, String name, String keyword) {
        Query q = em.createNamedQuery("Internship.SelectList");
        q.setParameter("tutor",tutorId);
        //q.setParameter("year",year);
        //q.setParameter("name",name);
        //q.setParameter("keyword",keyword);
        return q.getResultList();
    }
}
