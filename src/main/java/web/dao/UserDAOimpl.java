package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOimpl implements UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    public List<User> usersList() {
        return entityManager.createQuery("SELECT u from User u").getResultList();
    }

    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    public void save(User user) {
        entityManager.persist(user);
    }

    public void update(long id, User user) {
        User updateUser = entityManager.find(User.class, id);
        entityManager.detach(updateUser);

        updateUser.setAge(user.getAge());
        updateUser.setLogin(user.getLogin());
        updateUser.setSurname(user.getSurname());
        updateUser.setName(user.getName());

        entityManager.merge(updateUser);
    }

    public void delete(long id) {
        entityManager.remove(getUser(id));
    }

}
