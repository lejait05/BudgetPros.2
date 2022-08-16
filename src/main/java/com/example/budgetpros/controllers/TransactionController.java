package com.example.budgetpros.controllers;

import com.example.budgetpros.model.Transaction;
import com.example.budgetpros.model.User;
import com.example.budgetpros.repositories.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String showTransactions(Model model){
        List<Transaction> transactionList = transactionDao.findAll();
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("transactions", transactionList);
        return "/profile";
    }

    @GetMapping("/transactions/{id}")
    public String showTransaction(@PathVariable long id, Model model){
        Transaction transaction = transactionDao.findById(id).get();
        model.addAttribute("transaction", transaction);
        return "profile/modal/show";
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
        return "profile/modal/edit";
    }

    @PostMapping("/transactions/{id}/edit")
    public String submitEditTransaction(@ModelAttribute Transaction transaction){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        transaction.setUser(user);
        transactionDao.save(transaction);
        return "/redirect:/profile";
    }
}
