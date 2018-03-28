package DbService.DAO;

import DbService.DataSets.UserDataSets;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;


public class UsersDAO {
    private Session session;

    public UsersDAO(Session session) {
        this.session = session;
    }

    public UserDataSets get(long id) throws HibernateException {
        return (UserDataSets) session.get(UserDataSets.class, id);
    }

    public long getUserId(String name) throws HibernateException {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSets> criteria = builder.createQuery(UserDataSets.class);
        Root<UserDataSets> from = criteria.from(UserDataSets.class);
        criteria.where(builder.equal(from.get("name"), name));
        Query<UserDataSets> query = session.createQuery(criteria);
        return query.uniqueResult().getId();
    }

    public long insertUser(String name) throws HibernateException {
        return (Long) session.save(new UserDataSets(name));
    }
}
