package seopays.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import seopays.domain.User;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;



    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
    }


    public void editUser(String username, User user) {

    }


    public void removeUser(String username) {

        User user = (User) sessionFactory.getCurrentSession().load(
                User.class, username);

        if (null != user) {
            sessionFactory.getCurrentSession().delete(user);
        }
    }
}
