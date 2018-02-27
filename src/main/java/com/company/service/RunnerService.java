package com.company.service;

import com.company.model.Feedback;
import com.company.model.User;
import com.company.repository.FeedbackRepository;
import com.company.repository.UserRepository;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

@Service
public class RunnerService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FeedbackRepository feedbackRepository;

    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

   /* @Transactional
    public void run() {
        User user = new User();
        user.setUsername("Evgen");

        userRepository.save(user);

        user.setUsername("Vasya");
        userRepository.update(user);

        System.out.println("User: "+user.getUsername());
    }*/

    @Transactional
    public void createUser(){
        User user = new User();
        user = user.builder()
                .username(getFromConsole("Input username"))
                .build();
        userRepository.save(user);
    }

    @Transactional
    public void createFeedback(){
        Feedback feedback = new Feedback();
        User user = checkUser();
        if(user == null){
            System.out.println("No such user");
            return;
        }
        feedback = feedback.builder()
                .title(getFromConsole("Input title (max 100 symbols)"))
                .content(getFromConsole("Input feedback"))
                .user(user)
                .createdOn(new Date())
                .rate(0)
                .build();

        feedbackRepository.save(feedback);
    }

    @SneakyThrows
    private String getFromConsole(String s) {
        System.out.println(s);
        String res = br.readLine();
        return res;
    }

    private User checkUser(){
        String fromConsole = getFromConsole("Input username of the author");

        try{
            User byUsername = userRepository.findByUsername(fromConsole);
            return byUsername;
        }catch (DataIntegrityViolationException e){
            return null;
        }
    }
}