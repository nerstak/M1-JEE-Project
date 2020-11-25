package control.sessionBeans;

import modelsEntities.StudentEntity;

import javax.ejb.Stateless;
import java.util.UUID;

@Stateless(name = "StudentSessionEJB")
public class StudentSessionBean extends ModelBean<StudentEntity>{
    public StudentEntity find(UUID id) {
        return em.find(StudentEntity.class, id);
    }
}
