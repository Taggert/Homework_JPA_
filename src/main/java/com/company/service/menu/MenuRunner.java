package com.company.service.menu;

import com.company.service.menu.items.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class MenuRunner {

    static InputOutput inputOutput = new ConsoleInputOutput();

    @Autowired
    CreateUser createUser;
    @Autowired
    CreateFeedback createFeedback;


    public void startConsoleFront() {
        ArrayList<Item> items = getItems();
        Menu menu = new Menu(items, inputOutput);
        menu.runMenu();
    }


    private ArrayList<Item> getItems() {
        ArrayList<Item> res = new ArrayList<>();
        res.add(createUser);
        res.add(createFeedback);
        res.add(new ExitItem(inputOutput));



        return res;
    }
}