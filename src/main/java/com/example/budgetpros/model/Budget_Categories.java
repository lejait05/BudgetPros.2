package com.example.budgetpros.model;

import com.example.budgetpros.model.User;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "budget_categories")

public class Budget_Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, length = 240)
    private String title;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budgetCategories")
    private List<Transaction> transactions;


    public Budget_Categories() {
    }

    public Budget_Categories(String title) {
        this.title = title;

    }

    public Budget_Categories(long id, String title, List<Transaction> transactions) {
        this.id = id;
        this.title = title;
        this.transactions = transactions;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
