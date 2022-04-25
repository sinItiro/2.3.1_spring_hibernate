package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    public List<User> usersList();
    public User getUser(long id);
    public void save(User user);
    public void update(long id, User user);
    public void delete(long id);
}
