package utils.ddbb;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import java.util.Arrays;

public class HibernateField {

    public static enum Restriction {
        LIKE {
            @Override
            protected Criterion buildCriterion(String name, Object value) {
                return Restrictions.like(name, String.valueOf(value), MatchMode.ANYWHERE);
            }
        }, EQUALS {
            @Override
            protected Criterion buildCriterion(String name, Object value) {
                return Restrictions.eq(name, value);
            }
        }, IN {
            @Override
            protected Criterion buildCriterion(String name, Object value) {
                return Restrictions.in(name, Arrays.asList(value));
            }
        },
        GREATER_THAN_OR_EQUALS {
            @Override
            protected Criterion buildCriterion(String name, Object value) {
                return Restrictions.ge(name, value);
            }

        }, LOWER_THAN_OR_EQUALS {
            @Override
            protected Criterion buildCriterion(String name, Object value) {
                return Restrictions.le(name, value);
            }
        };

        protected abstract Criterion buildCriterion(String name, Object value);
    }

    private final Restriction restriction;
    private final String name;

    public HibernateField(String name, Restriction restriction) {
        this.name = name;
        this.restriction = restriction;
    }

    public Criterion buildCriterion(Object value) {
        return restriction.buildCriterion(name, value);
    }
}
