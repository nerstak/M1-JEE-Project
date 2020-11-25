package control.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless(name = "SkillsSessionEJB")
public class SkillsSessionBean {
    @PersistenceContext
    EntityManager em;

    public List getSkills() {
        Query q = em.createNamedQuery("Skills.SelectAll");
        return q.getResultList();
    }
}
