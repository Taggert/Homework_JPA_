package com.company.repository;

import com.company.model.Feedback;
import com.company.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Date;
import java.util.List;

@Repository
public class FeedbackRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public Feedback save(Feedback feedback){
        entityManager.persist(feedback);
        return feedback;
    }

    public Feedback update(Feedback feedback){
        entityManager.merge(feedback);
        return feedback;
    }

    public Feedback findById(Integer feedbackId){
        return entityManager.find(Feedback.class, feedbackId);
    }

    public List<Feedback> findAll(){
        return entityManager.createQuery("from Feedback u").getResultList();
    }

    public Feedback findByUser(User user){
        List feedbacks = entityManager.createQuery("from Feedback f where f.user = :paramUser")
                .setParameter("paramUser", user)
                .getResultList();
        if (!feedbacks.isEmpty()) {
            return (Feedback) feedbacks.get(0);
        }

        return null;
    }


    public Feedback findByObject(Feedback feedback){
        Query query = entityManager.createQuery("select f from Feedback f where f = :paramFeedback");
        query.setParameter("paramFeedback", feedback);
        List resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return (Feedback) resultList.get(0);
        }
        return null;
    }

    public Feedback findByTitle(String title){
        Query query = entityManager.createQuery("select f from Feedback f where f.title = :paramTitle");
        query.setParameter("paramTitle", title);
        List resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return (Feedback) resultList.get(0);
        }
        return null;
    }

    public List<Feedback> findFromDate(Date date){
        Query query = entityManager.createQuery("select f from Feedback f where f.createdOn >= :paramDate");
        query.setParameter("paramDate", date);
        List resultList = query.getResultList();
        if (!resultList.isEmpty()) {
            return resultList;
        }
        return null;
    }


}