package com.example.budgetpros.controllers;

import com.example.budgetpros.model.Goal;
import com.example.budgetpros.repositories.GoalsRepository;
import com.example.budgetpros.repositories.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class GoalsController {
    private final GoalsRepository goalsDao;
    private UserRepository userDao;

    public GoalsController(GoalsRepository goalsDao, UserRepository userDao) {
        this.goalsDao = goalsDao;
        this.userDao = userDao;
    }


    @GetMapping("/goals")
    public String getGoals(Model model) {
        List<Goal> goals = goalsDao.findAll();
        model.addAttribute("goals", goals);
        return "goalIndex";
    }

    @GetMapping("/goals/create")
    public String createGoal(Model model){
        model.addAttribute("newGoal", new Goal());
        return "goalCreate";
    }


    @PostMapping("/goals/create")
    public String create(@ModelAttribute Goal newGoal) {
//     User user = userDao.findById(1L).get();
//     newGoal.setUser(user);
     goalsDao.save(newGoal);
        return "redirect:goals";
    }

}
