package seopays.service;

import seopays.domain.User;

public interface UserService {

    public boolean addUser(User user);

    public void editUser(String username, User user);

    public void removeUser(String username);

    public boolean login(String username, String password);

}
