package com.example.budgetpros.model;

import com.example.budgetpros.model.User;

import javax.persistence.*;

@Entity
@Table(name = "budget_categories")

public class Budget_Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(nullable = false, length = 240)
    private String title;
    @Column(columnDefinition = "DECIMAL(13,2)", nullable = false)
    private double amount;

    public Budget_Categories() {
    }

    public Budget_Categories(User user, String title, double amount) {
        this.user = user;
        this.title = title;
        this.amount = amount;
    }

    public Budget_Categories(long id, User user, String title, int amount) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.amount = amount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
