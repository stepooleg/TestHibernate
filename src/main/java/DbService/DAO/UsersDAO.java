package DbService.DAO;

import DbService.DataSets.UserDataSets;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UserDataSets get(long id) throws HibernateException {
        return (UserDataSets) session.get(UserDataSets.class, id);
    }

    public long getUserId(String name) throws HibernateException {
        Criteria criteria = session.createCriteria(UserDataSets.class);
        return ((UserDataSets) criteria.add(Restrictions.eq("name", name)).uniqueResult()).getId();
    }

    public long insertUser(String name) throws HibernateException {
        return (Long) session.save(new UserDataSets(name));
    }
}
