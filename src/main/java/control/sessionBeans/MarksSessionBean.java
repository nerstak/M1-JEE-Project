package control.sessionBeans;

import modelsEntities.MarksEntity;
import modelsEntities.StudentEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless(name = "MarksSessionEJB")
public class MarksSessionBean extends ModelBean<MarksEntity>{
    public MarksEntity find(UUID id) {
        return em.find(MarksEntity.class, id);
    }
}
