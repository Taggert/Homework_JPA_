package com.company.service.menu.items;

import com.company.service.RunnerService;
import com.company.service.menu.Item;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@NoArgsConstructor
@AllArgsConstructor
public class CreateFeedback extends Item {

    @Autowired
    private RunnerService runnerService;

    @Override
    public String displayedName() {
        return "Create feedback";
    }

    @Override
    public void perform() {
        runnerService.createFeedback();
    }
}