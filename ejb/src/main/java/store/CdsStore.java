package store;

import model.Cd;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import utils.ddbb.HibernateField;

import javax.persistence.EntityManager;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CdsStore {

    private static final String SELECT_ALL_CDS;

    static {
        SELECT_ALL_CDS = "from Cd ";
    }

    private static final Map<String, HibernateField> CRITERIA_NAMES_FIELDS = createCriteriaNamesRestrictions();

    private static Map<String, HibernateField> createCriteriaNamesRestrictions() {
        final Map<String, HibernateField> result = new HashMap<String, HibernateField>();
        result.put("id", new HibernateField("id", HibernateField.Restriction.EQUALS));
        result.put("title", new HibernateField("title", HibernateField.Restriction.LIKE));
        result.put("artist", new HibernateField("artist", HibernateField.Restriction.LIKE));
        result.put("year", new HibernateField("jahr", HibernateField.Restriction.LIKE));
        //result.put("iniDateMin", new HibernateField("startDate", HibernateField.Restriction.GREATER_THAN_OR_EQUALS));
        //result.put("iniDateMax", new HibernateField("startDate", HibernateField.Restriction.LOWER_THAN_OR_EQUALS));
        //result.put("endDateMin", new HibernateField("endDate", HibernateField.Restriction.GREATER_THAN_OR_EQUALS));
        //result.put("endDateMax", new HibernateField("endDate", HibernateField.Restriction.LOWER_THAN_OR_EQUALS));
        return Collections.unmodifiableMap(result);
    }


    public void persist(EntityManager em, Cd cd) {
        em.persist(cd);
    }

    public void merge(EntityManager em, Cd cd) {
        em.merge(cd);
    }

    public void remove(EntityManager em, Cd cd) {
        em.remove(cd);
    }

    public Cd findCdsById(EntityManager em, int id) {
        return em.find(Cd.class, id);
    }

    public List<Cd> findAll(EntityManager em) {
        return em.createQuery(SELECT_ALL_CDS).getResultList();
    }

    public List<Cd> findCdsByCriteria(EntityManager em, int startIndex, int length, Map<String, Object> filter) throws Exception {
        try {
            Criteria criteria = ((Session) em.getDelegate()).createCriteria(Cd.class);
            for (Map.Entry<String, Object> entry : filter.entrySet()) {
                Criterion criterion = buildCriterion(entry);
                if (criterion != null) {
                    criteria.add(criterion);
                }
            }
            criteria.addOrder(Order.desc("id"));
            criteria.setFirstResult(startIndex);
            criteria.setMaxResults(length);
            return criteria.list();
        } catch (Exception e) {
            throw e;
        }
    }

    private Criterion buildCriterion(Map.Entry<String, Object> entry) {
        HibernateField field = CRITERIA_NAMES_FIELDS.get(entry.getKey());
        return field == null ? null : field.buildCriterion(entry.getValue());
    }
}
