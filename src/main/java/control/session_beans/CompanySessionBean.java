package control.sessionBeans;

import models.CompanyEntity;
import models.FinalReportEntity;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless(name = "CompanySessionEJB")
public class CompanySessionBean extends ModelBean<CompanyEntity> {
    public CompanyEntity find(UUID id) {
        return em.find(CompanyEntity.class, id);
    }
}
