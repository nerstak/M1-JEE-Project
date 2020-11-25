package control.sessionBeans;

import modelsEntities.StudentEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless(name = "StudentSessionEJB")
public class StudentSessionBean {
    @PersistenceContext
    EntityManager em;

    public boolean saveStudent(StudentEntity s) {
        em.merge(s);
        return true;
    }
}
