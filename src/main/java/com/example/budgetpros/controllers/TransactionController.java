package com.example.budgetpros.controllers;

import com.example.budgetpros.model.Transaction;
import com.example.budgetpros.repositories.TransactionRepository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

public class TransactionController {

    private TransactionRepository transactionDao;

    public TransactionController(TransactionRepository transactionDao) {
        this.transactionDao = transactionDao;
    }

    @GetMapping("/transactions")
    public String showTransactions(Model model){
        List<Transaction> transactionList = transactionDao.findAll();

        model.addAttribute("transactions", transactionList);

        return "profile";
    }

    @GetMapping("/transactions/{id}")
    public String showTransaction(@PathVariable long id, Model model){
        Transaction transaction = transactionDao.findById(id).get();
        model.addAttribute("transaction", transaction);
        return "profile/modal";
    }

    @GetMapping("transactions/create")
    public String createTransaction(Model model){
        Transaction transaction = new Transaction();
        model.addAttribute("transaction", transaction);
        return "profile/modal";
    }

    @PostMapping("transactions/create")
    public String insertTransaction(@ModelAttribute Transaction transaction){
        transactionDao.save(transaction);
        return "redirect:/transactions";
    }



}
