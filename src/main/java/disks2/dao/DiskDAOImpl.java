package disks2.dao;

import disks2.domain.Disk;
import disks2.domain.User;
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
        String SQL_QUERY ="from Disk as d where d.user.id=? and d.fromUser.id is null";
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
        String SQL_QUERY ="from Disk as d where d.fromUser is null and d.user.id is null";
        Query query = session.createQuery(SQL_QUERY);
        for(Object o : query.list()) {
            result.add((Disk) o);
        }
        session.close();
        return result;
    }




    public Disk getDiskById(Integer diskId) {
        Session session = sessionFactory.openSession();
        String SQL_QUERY ="from Disk as d where d.id=?";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,diskId);
        Disk result =(Disk) query.uniqueResult();
        session.close();
        return result;
    }

    public User getUserById(Integer userId) {
        Session session = sessionFactory.openSession();
        String SQL_QUERY ="from User as u where u.id=?";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,userId);
        User result =(User) query.uniqueResult();
        session.close();
        return result;
    }

    public void takeFreeDisk(Integer userId, Integer diskId) {
        Disk result = getDiskById(diskId);
        result.setUser(getUserById(userId));
        result.setFromUser(null);
        sessionFactory.getCurrentSession().update(result);
    }

    public void takeFreeDiskFromUser(Integer userId, Integer diskId, Integer fromId) {
        Session session = sessionFactory.openSession();
        String SQL_QUERY ="from Disk as d where d.user.id=? and d.id=?";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,fromId);
        query.setParameter(1,diskId);
        Disk result = (Disk)query.uniqueResult();
        result.setUser(getUserById(userId));
        result.setFromUser(getUserById(fromId));
        sessionFactory.getCurrentSession().update(result);
        session.close();
    }

    public void returnDiskToUser(Integer userId, Integer diskId, Integer fromId) {
        Session session = sessionFactory.openSession();
        String SQL_QUERY ="from Disk as d where d.user.id=? and d.id=?";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,userId);
        query.setParameter(1,diskId);
        Disk result = (Disk)query.uniqueResult();
        result.setUser(getUserById(fromId));
        result.setFromUser(null);
        sessionFactory.getCurrentSession().update(result);
        session.close();
    }

    public void returnOwnDisk(Integer userId, Integer diskId) {
        Session session = sessionFactory.openSession();
        String SQL_QUERY ="from Disk as d where d.user.id=? and d.id=?";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,userId);
        query.setParameter(1,diskId);
        Disk result = (Disk)query.uniqueResult();
        result.setUser(null);
        result.setFromUser(null);
        sessionFactory.getCurrentSession().update(result);
        session.close();
    }

    public List<Disk> listOwnDisksFromAllUsers(Integer currentUserId) {
        List<Disk> result = new ArrayList<Disk>();
        Session session = sessionFactory.openSession();
        String SQL_QUERY ="from Disk as d where d.user.id !=? and d.fromUser.id is null";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,currentUserId);
        for(Object o : query.list()) {
            result.add((Disk) o);
        }
        session.close();
        return result;
    }

    public List<Disk> listTakenDisksByUser(Integer userId) {
        List<Disk> result = new ArrayList<Disk>();
        Session session = sessionFactory.openSession();
        String SQL_QUERY ="from Disk as d where d.user.id=? and d.fromUser.id is not null";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,userId);
        for(Object o : query.list()) {
            result.add((Disk) o);
        }
        session.close();
        return result;
    }

    public List<Disk> listTakenDisksFromUser(Integer userId) {
        List<Disk> result = new ArrayList<Disk>();
        Session session = sessionFactory.openSession();
        String SQL_QUERY ="from Disk as d where d.fromUser.id=?";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,userId);
        for(Object o : query.list()) {
            result.add((Disk) o);
        }
        session.close();
        return result;
    }
}
