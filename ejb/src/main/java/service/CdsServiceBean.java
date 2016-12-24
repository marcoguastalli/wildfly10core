package service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import model.Cd;
import store.CdsStore;

@Stateless
public class CdsServiceBean implements CdsService {

    @PersistenceContext(unitName = "MySqlDS")
    private EntityManager em;

    private CdsStore store;

    @PostConstruct
    public void injectMembers() {
        this.store = new CdsStore();
    }

    public void persistCd(Cd cd) {
        this.store.persist(this.em, cd);
    }

    public void mergeCd(Cd cd) {
        this.store.merge(this.em, cd);
    }

    public void removeCd(Cd cd) {
        this.store.remove(this.em, cd);
    }

    public Cd findCdById(int id) {
        return this.store.findCdsById(this.em, id);
    }

    public Collection<Cd> findAllCds() {
        return this.store.findAll(this.em);
    }

    public List<Cd> findCdsByCriteria(int startIndex, int length, Map<String, Object> filter) throws Exception {
        return this.store.findCdsByCriteria(this.em, startIndex, length, filter);
    }
}
