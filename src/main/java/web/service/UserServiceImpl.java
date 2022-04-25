package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.dao.UserDAO;
import web.model.UserService;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Transactional
    public List<User> usersList() {
        return userDAO.usersList();
    }

    @Transactional
    public User getUser(long id) {
        return userDAO.getUser(id);
    }

    @Transactional
    public void save(User user) {
        userDAO.save(user);
    }

    @Transactional
    public void update(long id, User user) {
        userDAO.update(id, user);
    }
    @Transactional
    public void delete(long id) {
        userDAO.delete(id);
    }
}
