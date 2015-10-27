package disks2.dao;

import disks2.domain.User;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by adminvl on 21.10.2015.
 */
@Repository
public class UserDAOImpl implements UserDAO {
    @Autowired
    private SessionFactory sessionFactory;

    public User getUser(String userName, String userPassword){
        Session session = sessionFactory.openSession();
        String SQL_QUERY = "from User as o where o.login=? and o.password=?";
        Query query = session.createQuery(SQL_QUERY);
        query.setParameter(0, userName);
        query.setParameter(1, userPassword);
        User result = (User) query.uniqueResult();
        session.close();
        return result;
    }
}
