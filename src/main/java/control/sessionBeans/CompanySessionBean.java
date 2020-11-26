package control.sessionBeans;

import models.CompanyEntity;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless(name = "CompanySessionEJB")
public class CompanySessionBean extends ModelBean<CompanyEntity> {
    @Override
    public CompanyEntity find(UUID id) {
        return null;
    }
}
