package service;


import model.Cd;

import javax.persistence.EntityManager;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface CdsService {

    public void persistCd(Cd cd);

    public void mergeCd(Cd cd);

    public void removeCd(Cd cd);

    public Cd findCdById(int id);

    public Collection<Cd> findAllCds();

    public List<Cd> findCdsByCriteria(int startIndex, int length, Map<String, Object> filter) throws Exception;

}
