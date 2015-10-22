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

    public List<Disk> listOwnDisks(Integer userid) {
        List<Disk> result = new ArrayList<Disk>();
        Session session = sessionFactory.openSession();
        String SQL_QUERY ="select u.takenList from User as u join Disk as d where u.id=? and d.fromUser is null";
        Query query = session.createQuery(SQL_QUERY);
        for(Object o : query.list()) {
            result.add((Disk) o);
        }
        session.close();
        return result;
    }
}
