package com.example.budgetpros.model;

import javax.persistence.*;

@Entity
@Table(name="transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn (name="user_id")
    private User user;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = true)
    private String memo;

    @Column(columnDefinition = "DATETIME", nullable = false)
    private String date;

    @Column(columnDefinition = "DECIMAL(13,2)", nullable = false)
    private double amount;

    @ManyToOne
    @JoinColumn (name = "transaction_types_id")
    private Transaction_Types transactionType;

    @ManyToOne
    @JoinColumn (name = "budget_categories_id")
    private Budget_Categories budgetCategories;

    @ManyToOne
    @JoinColumn (name = "goal_id")
    private Goal goal;

    public Transaction() {
    }

    public Transaction(User user, String title, String memo, String date, double amount, Transaction_Types transactionType, Budget_Categories budgetCategories, Goal goal) {
        this.user = user;
        this.title = title;
        this.memo = memo;
        this.date = date;
        this.amount = amount;
        this.transactionType = transactionType;
        this.budgetCategories = budgetCategories;
        this.goal = goal;
    }

    public Transaction(long id, User user, String title, String memo, String date, double amount, Transaction_Types transactionType, Budget_Categories budgetCategories, Goal goal) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.memo = memo;
        this.date = date;
        this.amount = amount;
        this.transactionType = transactionType;
        this.budgetCategories = budgetCategories;
        this.goal = goal;
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

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Transaction_Types getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(Transaction_Types transactionType) {
        this.transactionType = transactionType;
    }

    public Budget_Categories getBudgetCategories() {
        return budgetCategories;
    }

    public void setBudgetCategories(Budget_Categories budgetCategories) {
        this.budgetCategories = budgetCategories;
    }

    public Goal getGoal() {
        return goal;
    }

    public void setGoal(Goal goal) {
        this.goal = goal;
    }
}
