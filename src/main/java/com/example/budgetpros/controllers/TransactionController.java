package com.example.budgetpros.controllers;

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

@Controller
public class TransactionController {

    private TransactionRepository transactionDao;
    private Budget_CategoriesRepository budgetCategoriesDao;
    private GoalsRepository goalsDao;
    private TransactionTypesRepo transactionTypesDao;
    private UserRepository usersDao;

    public TransactionController(TransactionRepository transactionDao, Budget_CategoriesRepository budgetCategoriesDao, GoalsRepository goalsDao, TransactionTypesRepo transactionTypesDao, UserRepository usersDao) {
        this.transactionDao = transactionDao;
        this.budgetCategoriesDao = budgetCategoriesDao;
        this.goalsDao = goalsDao;
        this.transactionTypesDao = transactionTypesDao;
        this.usersDao = usersDao;
    }

    @GetMapping("/profile")
    public String showTransactions(@RequestParam(required = false) String sortTransaction, Model model){
        model.addAttribute("transaction", new Transaction());

        User user = usersDao.findById(1L).get();
        model.addAttribute("user", user);

//        function for extra sum
        List<Transaction> extraSumList = transactionDao.findAll();
        for(Transaction transaction: extraSumList){
            if(transaction.getTransactionType().toString().equals("1") && !transaction.getBudgetCategories().getTitle().equals("none") || transaction.getTransactionType().toString().equals("3") && !transaction.getBudgetCategories().getTitle().equals("none")){
                extraSumList.remove(transaction);
                int extraSum = 0;
                for(Transaction transaction2: extraSumList){
                    extraSum += transaction2.getAmount();
                }
                model.addAttribute("extraSum", extraSum);
            }

        }


//        function for budget sum
        List<Transaction> budgetSumList = transactionDao.findAll();
        budgetSumList.removeIf(transaction -> transaction.getBudgetCategories().getTitle().contains("none"));
        int budgetSum = 0;
        for(Transaction transaction: budgetSumList){
            budgetSum += transaction.getAmount();
        }
        model.addAttribute("budgetSum", budgetSum);

// Conditionals for the filter feature
        if(sortTransaction == null){
            List<Transaction> reverseList = transactionDao.findAll();
            Collections.reverse(reverseList);
            model.addAttribute("transactions", reverseList);
        } else if(sortTransaction.equals("1")){
            List<Transaction> reverseList = transactionDao.findAll();
            Collections.reverse(reverseList);
            model.addAttribute("transactions", reverseList);
        } else if(sortTransaction.equals("2")){
            List<Transaction> transactionList = transactionDao.findAll();
            model.addAttribute("transactions", transactionList);
        } else if(sortTransaction.equals("3")) {
            List<Transaction> budgetList = transactionDao.findAll();
            budgetList.removeIf(transaction -> transaction.getBudgetCategories().getTitle().contains("none"));
            model.addAttribute("transactions", budgetList);
        } else if(sortTransaction.equals("4")){
            List<Transaction> goalList = transactionDao.findAll();
            goalList.removeIf(transaction -> transaction.getGoal() == null);
        model.addAttribute("transactions", goalList);
        }

        return "/profile";
    }

    @PostMapping("/transactions/create")
    public String insertTransaction(@ModelAttribute Transaction transaction){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = usersDao.findById(1L).get();
        transaction.setUser(user);
        transactionDao.save(transaction);
        return "redirect:/profile";
    }

    @PostMapping("/transactions/{id}/delete")
    public String deleteTransaction(@PathVariable long id){
        transactionDao.deleteById(id);
        return "redirect:/profile";
    }

    @GetMapping("/transactions/{id}/edit")
    public String editTransaction(@PathVariable long id, Model model){
        Transaction transaction = transactionDao.findById(id).get();
        model.addAttribute("transaction", transaction);
        return "/profile";
    }

    @PostMapping("/transactions/{id}/edit")
    public String submitEditTransaction(@ModelAttribute Transaction transaction){
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Transaction transactionToUpdate = transactionDao.findById(transaction.getId()).get();
        transactionToUpdate.setAmount(transaction.getAmount());
        transactionToUpdate.setTitle(transaction.getTitle());
        transactionToUpdate.setMemo(transaction.getMemo());
        transactionToUpdate.setTransactionType(transaction.getTransactionType());
        transactionToUpdate.setBudgetCategories(transaction.getBudgetCategories());
        User user = usersDao.findById(1L).get();
        transaction.setUser(user);
        transactionDao.save(transactionToUpdate);
        return "redirect:/profile";
    }
}
