package control.sessionBeans;

import modelsEntities.StudentEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.UUID;

@Stateless(name = "StudentSessionEJB")
public class StudentSessionBean {
    @PersistenceContext
    EntityManager em;

    public boolean save(StudentEntity e) {
        em.merge(e);
        return true;
    }

    public StudentEntity find(UUID id) {
        return em.find(StudentEntity.class, id);
    }
}
