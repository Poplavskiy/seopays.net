package seopays.service;

import seopays.domain.User;

public interface UserService {

    public void addUser(User user);

    public void editUser(String username, User user);

    public void removeUser(String username);
}
