package seopays.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import seopays.dao.UserDAO;
import seopays.domain.Authorities;
import seopays.domain.User;


@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private UserDAO userDAO;


    @Transactional
    public boolean addUser(User user) {

        return userDAO.addUser(user);
    }

    @Transactional
    public void editUser(String username, User user) {

    }

    @Transactional
    public void removeUser(String username) {

        userDAO.removeUser(username);

    }

    @Transactional
    public boolean login(String username, String password) {

        return userDAO.login(username, password);
    }

}
