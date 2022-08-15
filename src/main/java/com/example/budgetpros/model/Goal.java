package com.example.budgetpros.model;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "goals")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn (name="user_id")
    private User user;

    @Column(length = 100, nullable = false)
    private String title;

    @Column(name = "dateCreated")
    @CreationTimestamp
    private LocalDateTime dateCreated;

    @Column(length = 100, nullable = false)
    private String dateCompleted;

    @Column(columnDefinition = "INT", nullable = false)
    private int goalAmount;

    @Column(columnDefinition = "INT", nullable = false)
    private int currentAmount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "goal")
    private List<Transaction> transactions;

    public Goal() {
    }

    public Goal(User user, String title, LocalDateTime dateCreated, String dateCompleted, int goalAmount, int currentAmount, List<Transaction> transactions) {
        this.user = user;
        this.title = title;
        this.dateCreated = dateCreated;
        this.dateCompleted = dateCompleted;
        this.goalAmount = goalAmount;
        this.currentAmount = currentAmount;
        this.transactions = transactions;
    }

    public Goal(long id, User user, String title, LocalDateTime dateCreated, String dateCompleted, int goalAmount, int currentAmount, List<Transaction> transactions) {
        this.id = id;
        this.user = user;
        this.title = title;
        this.dateCreated = dateCreated;
        this.dateCompleted = dateCompleted;
        this.goalAmount = goalAmount;
        this.currentAmount = currentAmount;
        this.transactions = transactions;
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

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public void setDateCompleted(String dateCompleted) {
        this.dateCompleted = dateCompleted;
    }

    public int getGoalAmount() {
        return goalAmount;
    }

    public void setGoalAmount(int goalAmount) {
        this.goalAmount = goalAmount;
    }

    public int getCurrentAmount() {
        return currentAmount;
    }

    public void setCurrentAmount(int currentAmount) {
        this.currentAmount = currentAmount;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }
}
