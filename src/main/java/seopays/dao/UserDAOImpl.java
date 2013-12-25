package seopays.dao;

import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import seopays.domain.Authorities;
import seopays.domain.User;

@Repository
public class UserDAOImpl implements UserDAO {

    @Autowired
    private SessionFactory sessionFactory;

    // TODO Add validation for removeUser

    public boolean addUser(User user) {

        Session session = sessionFactory.openSession();

        Transaction transaction = null;

        try {

            transaction = session.beginTransaction();

            Authorities au = new Authorities();

            au.setAuthority("ROLE_USER");
            au.setUsername(user.getUsername());
            au.setUser(user);

            user.getAuthorities().add(au);

            session.save(user);
            session.save(au);

            transaction.commit();

        } catch (Exception e) {

            if (transaction != null) {
                transaction.rollback();

                return false;
            }
        }  finally {

            session.close();
        }

        return true;
    }


    // TODO Develop edit function

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
