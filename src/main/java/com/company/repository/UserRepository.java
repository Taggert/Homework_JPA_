package com.company.repository;

import com.company.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public User save(User user) {
        entityManager.persist(user);
        return user;
    }

    public User update(User user) {
        entityManager.merge(user);
        return user;
    }

    public User findById(Integer userId) {
        return entityManager.find(User.class, userId);
    }

    public List<User> findAll() {
        return entityManager.createQuery("from User").getResultList();
    }

    public User findByUsername(String username) {
        List users = entityManager.createQuery("from User u where u.username = :paramUsername")
                .setParameter("paramUsername", username)
                .getResultList();
        if (!users.isEmpty()) {
            return (User) users.get(0);
        }

        return null;
    }

}