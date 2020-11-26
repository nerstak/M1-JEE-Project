package control.sessionBeans;

import models.FinalReportEntity;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless(name = "FinalReportSessionEJB")
public class FinalReportSessionBean extends ModelBean<FinalReportEntity> {
    @Override
    public FinalReportEntity find(UUID id) {
        return em.find(FinalReportEntity.class, id);
    }
}
