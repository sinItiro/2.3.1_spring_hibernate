package web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDAO {

    @PersistenceContext
    EntityManager entityManager;

    private List<User> users;

    {
        users = new ArrayList<>();
        User user1 = new User("Alex", "Ivanov", (byte) 33, "ivanov");
        User user2 = new User( "Max", "Petrov", (byte) 60, "petrovich");
        User user3 = new User("Ann", "Ivanova", (byte) 23, "annka");
//        entityManager.persist(user1);
//        entityManager.persist();
//        entityManager.persist(user2);
//        entityManager.persist(user3);
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    @Transactional
    public List<User> usersList() {

        return entityManager.createQuery("SELECT u from User u").getResultList();
    }

    @Transactional
    public User getUser(long id) {
        return entityManager.find(User.class, id);
    }

    @Transactional
    public void save(User user) {
        entityManager.persist(user);
    }

    @Transactional
    public void update(long id, User user) {
        User updateUser = entityManager.find(User.class, id);
        entityManager.detach(updateUser);

        updateUser.setAge(user.getAge());
        updateUser.setLogin(user.getLogin());
        updateUser.setSurname(user.getSurname());
        updateUser.setName(user.getName());

        entityManager.merge(updateUser);
    }

    @Transactional
    public void delete(long id) {
        entityManager.remove(getUser(id));
        users.removeIf(p -> p.getId() == id);
    }

}
