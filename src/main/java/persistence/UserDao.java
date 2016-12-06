package persistence;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import entity.User;

/**
 * Created by Joe on 10/26/2016.
 */
public class UserDao {

    /**
     * @param username username from login servlet
     * @param password password from login servlet
     * @return returns true if login was successful
     *
     * tries to find a matching user given a username, then tests if the passwords match
     */
    public boolean authenticateUser(String username, String password) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();
        Criteria criteria = session.createCriteria(User.class);

        User user = (User) criteria.add(Restrictions.eq("username", username)).uniqueResult();

        if (password.equals(user.getPassword()))
            return true;
        return false;
    }

    public User getUser(String username) {
        Session session = SessionFactoryProvider.getSessionFactory().openSession();

        User user = (User) session.get(User.class, username);
        session.close();
        return user;
    }
}
