package seopays.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import seopays.domain.User;

public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;


    @Override
    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    public void editUser(String username, User user) {

    }

    @Override
    public void removeUser(String username) {

        User user = (User) sessionFactory.getCurrentSession().load(
                User.class, username);

        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
}
