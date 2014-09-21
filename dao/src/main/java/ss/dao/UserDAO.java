package ss.dao;

import ss.domain.User;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

@Repository
public class UserDAO extends AbstractDAO<User> implements UserDataSource {
    public UserDAO() {
        super(User.class);
    }

    public User getByUsername(final String username) {
        return new HibernateHelper<User>() {
            @Override
            User operation() {
                return (User) getSession().createCriteria(User.class).add(Restrictions.eq("username", username)).uniqueResult();
            }
        }.execute();
    }
}