package disks2.dao;

import disks2.domain.Disk;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adminvl on 21.10.2015.
 */
@Repository
public class DiskDAOImpl implements DiskDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public List<Disk> listOwnDisks(Integer userId) {
        List<Disk> result = new ArrayList<Disk>();
        Session session = sessionFactory.openSession();
        String SQL_QUERY ="Select t.disk from TakenItem as t join t.disk as d where t.fromUser is null and t.user.id=?";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,userId);
        for(Object o : query.list()) {
            result.add((Disk) o);
        }
        session.close();
        return result;
    }

    public List<Disk> listFreeDisks() {
        List<Disk> result = new ArrayList<Disk>();
        Session session = sessionFactory.openSession();
        String SQL_QUERY ="from Disk as d where d not in (Select t.disk from TakenItem as t)";
        Query query = session.createQuery(SQL_QUERY);
        for(Object o : query.list()) {
            result.add((Disk) o);
        }
        session.close();
        return result;
    }
}
