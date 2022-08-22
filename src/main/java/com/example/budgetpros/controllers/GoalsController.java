package com.example.budgetpros.controllers;

import com.example.budgetpros.model.Goal;
import com.example.budgetpros.model.User;
import com.example.budgetpros.repositories.GoalsRepository;
import com.example.budgetpros.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class GoalsController {
    private GoalsRepository goalsDao;
    private UserRepository userDao;


    public GoalsController(GoalsRepository goalsDao, UserRepository userDao) {
        this.goalsDao = goalsDao;
        this.userDao = userDao;

    }


    @GetMapping("/goals")
    public String getGoals(Model model) {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        User user = userDao.findByUsername(name);
        model.addAttribute("user", user);
        List<Goal> goals = goalsDao.findByUserId(user.getId());
        model.addAttribute("goals", goals);
        return "/goal/goalIndex";
    }

    @GetMapping("/goals/{id}")
     public String getGoal(@PathVariable long id, Model model){
        Goal goal = goalsDao.findById(id).get();
        model.addAttribute("goal", goal);
        return "goal/goalShow";
}

    @GetMapping("/goals/create")
    public String createGoal(Model model){
        model.addAttribute("newGoal", new Goal());
        return "/goal/goalCreate";
    }


    @PostMapping("/goals/create")
    public String create(@ModelAttribute Goal newGoal, @RequestParam String endDate) {
      User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
      newGoal.setEndDate(endDate);
     newGoal.setUser(principal);
     goalsDao.save(newGoal);
        return "redirect:/goals";
    }
    @GetMapping("/goals/{id}/edit")
    public String showEditForm(@PathVariable long id, Model model) {
        Goal goalToEdit = goalsDao.findById(id).get();
        model.addAttribute("goalToEdit", goalToEdit);
        return "goal/goalEdit";
    }

    @PostMapping("/goals/{id}/edit")
    public String submitEditForm(@ModelAttribute Goal goalUpdates) {

        Goal goalToUpdate = goalsDao.findById(goalUpdates.getId()).get();
        goalToUpdate.setTitle(goalUpdates.getTitle());
        goalToUpdate.setEndDate(goalUpdates.getEndDate());
        goalToUpdate.setGoalAmount(goalUpdates.getGoalAmount());
        goalsDao.save(goalToUpdate);
        return "redirect:/goals";
    }

    @PostMapping("/goals/{id}/delete")
    public String deletePost(@PathVariable long id) {
        goalsDao.deleteById(id);
        return "redirect:/goals";
    }
}


