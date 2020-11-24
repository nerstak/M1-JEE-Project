package control.sessionBeans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless(name = "InternshipDataSessionEJB")
public class InternshipDataSessionBean {
    @PersistenceContext
    EntityManager em;

    public List getInternshipData() {

    }
}
