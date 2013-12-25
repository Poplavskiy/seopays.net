package seopays.dao;

import seopays.domain.User;

public interface UserDAO {

    public boolean addUser(User user);

    public void editUser(String username, User user);

    public void removeUser(String username);


}
