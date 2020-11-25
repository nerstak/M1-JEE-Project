package control.sessionBeans;

import modelsEntities.FinalReportEntity;
import modelsEntities.VisitEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless(name = "FinalReportSessionEJB")
public class FinalReportSessionBean {
    @PersistenceContext
    EntityManager em;

    public FinalReportEntity find(UUID id) {
        return em.find(FinalReportEntity.class, id);
    }

    public boolean save(FinalReportEntity e) {
        em.merge(e);
        return true;
    }
}
