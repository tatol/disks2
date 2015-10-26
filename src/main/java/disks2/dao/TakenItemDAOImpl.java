package disks2.dao;

import disks2.domain.Disk;
import disks2.domain.TakenItem;
import disks2.domain.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by adminvl on 23.10.2015.
 */
@Repository
public class TakenItemDAOImpl implements TakenItemDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public Disk getDiskById(Integer diskId) {
        org.hibernate.classic.Session session = sessionFactory.openSession();
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
        TakenItem result = new TakenItem();
        result.setDisk(getDiskById(diskId));
        result.setUser(getUserById(userId));
        result.setFromUser(null);
        Session session = sessionFactory.openSession();
        session.save(result);
        session.close();
    }

    public void takeFreeDiskFromUser(Integer userId, Integer diskId, Integer fromId) {
        Session session = sessionFactory.openSession();
        String SQL_QUERY ="from TakenItem as t where t.user.id=? and t.disk.id=?";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,fromId);
        query.setParameter(1,diskId);
        TakenItem deleteResult = (TakenItem)query.uniqueResult();
        TakenItem addResult = new TakenItem();
        addResult.setDisk(getDiskById(diskId));
        addResult.setUser(getUserById(userId));
        addResult.setFromUser(getUserById(fromId));
        session.save(addResult);
        session.close();
        sessionFactory.getCurrentSession().delete(deleteResult);
    }

    public void returnDiskToUser(Integer userId, Integer diskId, Integer fromId) {
        Session session = sessionFactory.openSession();
        String SQL_QUERY ="from TakenItem as t where t.user.id=? and t.disk.id=?";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,userId);
        query.setParameter(1,diskId);
        TakenItem deleteResult = (TakenItem)query.uniqueResult();
        TakenItem addResult = new TakenItem();
        addResult.setDisk(getDiskById(diskId));
        addResult.setUser(getUserById(fromId));
        addResult.setFromUser(null);
        session.save(addResult);
        session.close();
        sessionFactory.getCurrentSession().delete(deleteResult);
    }

    public void returnOwnDisk(Integer userId, Integer diskId) {
        Session session = sessionFactory.openSession();
        String SQL_QUERY ="from TakenItem as t where t.user.id=? and t.disk.id=?";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,userId);
        query.setParameter(1,diskId);
        TakenItem result = (TakenItem)query.uniqueResult();
        session.close();
        sessionFactory.getCurrentSession().delete(result);
    }

    public List<TakenItem> listOwnDisksFromAllUsers(Integer currentUserId) {
        List<TakenItem> result = new ArrayList<TakenItem>();
        org.hibernate.classic.Session session = sessionFactory.openSession();
        String SQL_QUERY ="from TakenItem as t where t.user.id !=? and t.fromUser.id is null";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,currentUserId);
        for(Object o : query.list()) {
            result.add((TakenItem) o);
        }
        session.close();
        return result;
    }

    public List<TakenItem> listTakenDisksByUser(Integer userId) {
        List<TakenItem> result = new ArrayList<TakenItem>();
        org.hibernate.classic.Session session = sessionFactory.openSession();
        String SQL_QUERY ="from TakenItem as t where t.user.id=? and t.fromUser.id is not null";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,userId);
        for(Object o : query.list()) {
            result.add((TakenItem) o);
        }
        session.close();
        return result;
    }

    public List<TakenItem> listTakenDisksFromUser(Integer userId) {
        List<TakenItem> result = new ArrayList<TakenItem>();
        org.hibernate.classic.Session session = sessionFactory.openSession();
        String SQL_QUERY ="from TakenItem as t where t.fromUser.id=?";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0,userId);
        for(Object o : query.list()) {
            result.add((TakenItem) o);
        }
        session.close();
        return result;
    }
}
