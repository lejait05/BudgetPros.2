package com.example.budgetpros.controllers;

import com.example.budgetpros.model.Goal;
import com.example.budgetpros.model.Transaction;
import com.example.budgetpros.model.User;
import com.example.budgetpros.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Controller
public class TransactionController {

    private TransactionRepository transactionDao;
    private UserRepository usersDao;
    private GoalsRepository goalsDao;

    public TransactionController(TransactionRepository transactionDao, UserRepository usersDao, GoalsRepository goalsDao) {
        this.transactionDao = transactionDao;
        this.usersDao = usersDao;
        this.goalsDao = goalsDao;
    }

    @GetMapping("/profile")
    public String showTransactions(@RequestParam(required = false) String sortTransaction, Model model){
        model.addAttribute("transaction", new Transaction());

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       String name = principal.getUsername();
       User user = usersDao.findByUsername(name);
        model.addAttribute("user", user);


        // Conditionals for the filter feature
        if(sortTransaction == null){
            List<Transaction> reverseList = transactionDao.findByUserId(user.getId());
            Collections.reverse(reverseList);
            model.addAttribute("transactions", reverseList);
        } else if(sortTransaction.equals("1")){
            List<Transaction> reverseList = transactionDao.findByUserId(user.getId());
            Collections.reverse(reverseList);
            model.addAttribute("transactions", reverseList);
        } else if(sortTransaction.equals("2")){
            List<Transaction> transactionList = transactionDao.findByUserId(user.getId());
            model.addAttribute("transactions", transactionList);
        } else if(sortTransaction.equals("3")) {
            List<Transaction> budgetList = transactionDao.findByUserId(user.getId());
            budgetList.removeIf(transaction -> transaction.getBudgetCategories().getTitle().contains("none"));
            model.addAttribute("transactions", budgetList);
        } else if(sortTransaction.equals("4")){
            List<Transaction> goalList = transactionDao.findByUserId(user.getId());
            goalList.removeIf(transaction -> transaction.getGoal() == null);
            model.addAttribute("transactions", goalList);
        }


//        function for extra sum
        List<Transaction> extraSumList = transactionDao.findByUserId(user.getId());
        int extraSum = 0;
        for(Transaction transaction: extraSumList){
            if(transaction.getTransactionType().getName().contains("one-time expense") && transaction.getBudgetCategories().getTitle().equals("none") || transaction.getTransactionType().getName().contains("recurring expense") && transaction.getBudgetCategories().getTitle().equals("none")){
                extraSum += transaction.getAmount();

            }
            model.addAttribute("extraSum", extraSum);
        }

//        function for budget sum
        List<Transaction> budgetSumList = transactionDao.findByUserId(user.getId());
        budgetSumList.removeIf(transaction -> transaction.getBudgetCategories().getTitle().contains("none"));
        int budgetSum = 0;
        for(Transaction transaction: budgetSumList){
            budgetSum += transaction.getAmount();
        }
        model.addAttribute("budgetSum", budgetSum);

//        Account balance feature
        List<Transaction> accountBalanceList = transactionDao.findByUserId(user.getId());
        int accountBalance = 0;
        for(Transaction transaction: accountBalanceList){
            if(transaction.getTransactionType().getName().contains("one-time deposit") || transaction.getTransactionType().getName().contains("recurring income")){
                accountBalance += transaction.getAmount();
            } else if(transaction.getTransactionType().getName().contains("one-time expense") || transaction.getTransactionType().getName().contains("recurring expense")){
                accountBalance -= transaction.getAmount();
            }
            model.addAttribute("accountBalance", accountBalance);
        }

        return "profile";
    }

    @PostMapping("/transactions/create")
    public String insertTransaction(@ModelAttribute Transaction transaction, @RequestParam String createGoal){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Goal> userGoals = goalsDao.findByUserId(user.getId());
        int transAmount = (int) transaction.getAmount();
        if(!createGoal.equals("null")){
            long goalId = Long.parseLong(createGoal);
           for(Goal goal: userGoals){
               if(goalId == goal.getId()){
                   List<Transaction> goalTransactions = goal.getTransactions();
                   goalTransactions.add(transaction);
                   int goalCurrent = goal.getCurrentAmount();
                   goal.setCurrentAmount(goalCurrent + transAmount);
                   transaction.setGoal(goal);
               }
           }
        }
        transaction.setUser(user);
        transactionDao.save(transaction);

        return "redirect:/profile";
    }

    @PostMapping("/transactions/{id}/delete")
    public String deleteTransaction(@PathVariable long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Transaction transaction = transactionDao.findById(id).get();
        List<Goal> userGoals = goalsDao.findByUserId(user.getId());
        if (transaction.getGoal() != null) {
            for (Goal goal : userGoals) {
                if (transaction.getGoal().getId() == goal.getId()) {
                    int goalCurrent = transaction.getGoal().getCurrentAmount();
                    int transactionCurrent = (int) transaction.getAmount();
                    goal.setCurrentAmount(goalCurrent-transactionCurrent);
                }
            }
        }
        transactionDao.deleteById(id);
        return "redirect:/profile";
    }


    @PostMapping("/transactions/{id}/edit")
    public String submitEditTransaction(@ModelAttribute Transaction transaction, @RequestParam String detailsGoal){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Transaction transactionToUpdate = transactionDao.findById(transaction.getId()).get();
        List<Goal> userGoals = goalsDao.findByUserId(user.getId());
        if(!Objects.equals(detailsGoal, "null")){
            long goalId = Long.parseLong(detailsGoal);
            if(transactionToUpdate.getGoal().getId() != goalId){
                long oldGoalId = transactionToUpdate.getGoal().getId();
                int oldAmount = (int) transactionToUpdate.getAmount();
                int newAmount = (int) transaction.getAmount();
                for(Goal goal: userGoals){
                    if(oldGoalId == goal.getId()){
                        int goalCurrent = transactionToUpdate.getGoal().getCurrentAmount();
                        goal.setCurrentAmount(goalCurrent - oldAmount);
                    }
                    if(goalId == goal.getId()){
                        int goalNew = goal.getCurrentAmount();
                        goal.setCurrentAmount(goalNew + newAmount);
                        transactionToUpdate.setGoal(goal);
                    }
                }
            }else if(transactionToUpdate.getAmount() != transaction.getAmount()){// make case for if goal id is the same
                int oldAmount = (int) transactionToUpdate.getAmount();
                int newAmount = (int) transaction.getAmount();
                int goalAmount = transactionToUpdate.getGoal().getCurrentAmount();
                transactionToUpdate.getGoal().setCurrentAmount(goalAmount-oldAmount+newAmount);
            }
        } else{
            transactionToUpdate.setGoal(null);
        }
        transactionToUpdate.setAmount(transaction.getAmount());
        transactionToUpdate.setTitle(transaction.getTitle());
        transactionToUpdate.setMemo(transaction.getMemo());
        transactionToUpdate.setTransactionType(transaction.getTransactionType());
        transactionToUpdate.setBudgetCategories(transaction.getBudgetCategories());
        transaction.setUser(user);
        transactionDao.save(transactionToUpdate);
        return "redirect:/profile";
    }
}
