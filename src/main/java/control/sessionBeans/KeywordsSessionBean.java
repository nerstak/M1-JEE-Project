package control.sessionBeans;

import modelsEntities.KeywordsEntity;

import javax.ejb.Stateless;
import javax.persistence.Query;
import java.util.List;
import java.util.UUID;

@Stateless(name = "KeywordsSessionEJB")
public class KeywordsSessionBean extends ModelBean<KeywordsEntity>{
    public List getKeywords() {
        Query q = em.createNamedQuery("Keywords.SelectAll");
        return q.getResultList();
    }

    @Override
    public KeywordsEntity find(UUID id) {
        em.find(KeywordsEntity.class,id);
        return null;
    }
}
