package control.sessionBeans;

import modelsEntities.SkillsEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Stateless(name = "SkillsSessionEJB")
public class SkillsSessionBean extends ModelBean<SkillsEntity> {
    public List getSkills() {
        Query q = em.createNamedQuery("Skills.SelectAll");
        return q.getResultList();
    }

    @Override
    public SkillsEntity find(UUID id) {
        return em.find(SkillsEntity.class,id);
    }
}
